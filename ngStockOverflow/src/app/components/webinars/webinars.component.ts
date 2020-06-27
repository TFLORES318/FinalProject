import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { WebinarService } from 'src/app/services/webinar.service';
import { Webinar } from 'src/app/models/webinar';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-webinars',
  templateUrl: './webinars.component.html',
  styleUrls: ['./webinars.component.css']
})
export class WebinarsComponent implements OnInit {

  allWebinars: Webinar[] = [];

  selected: Webinar=null;

  loggedInUser: User = new User();

  attendees: User[] = [];

  constructor(
    private authServ: AuthService,
    private webinarServ: WebinarService
  ) { }

  ngOnInit(): void {
    this.loadWebinars();
    this.authServ.showUser().subscribe(
      data => {
        this.loggedInUser = data;
        console.log('logged in user found');
      },
      err => {
        console.error('no logged in user found');

      }
    )
  }

  loadWebinars(){
    this.webinarServ.getWebinars().subscribe(
      data => {
        this.allWebinars = data;
      },
      err => {
        console.error('webinars goofed up in component');
      }
    )
  }

  signUpUser(webinarId: number){
    console.log(this.loggedInUser);
    console.log(this.selected.usersAttending.includes(this.loggedInUser));
    this.webinarServ.signUpUser(webinarId).subscribe(
      data => {
        this.attendees = data;
        this.selected = null;
        this.loadWebinars();

      },
      error => {
        console.error('webinar component sign up broken');
      }
      )
    }

    withdrawUser(webinarId: number){
      this.webinarServ.withdrawUser(webinarId).subscribe(
        data => {
          this.attendees = data;
          this.selected = null;
          this.loadWebinars();
      },
      error => {
        console.error('withdraw user failure');

      }
    )
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  checkIfUserAlreadyAttending(webinar: Webinar){
    for(let i = 0; i < this.selected.usersAttending.length ; i++){
      if(this.selected.usersAttending[i].username === this.loggedInUser.username){
        return true;
      }
    }
    return false;
    }

}
