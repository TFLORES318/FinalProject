import { Component, OnInit, AfterViewInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit, AfterViewInit {

  userUpdate: User = new User();

  constructor(
    private userServ: UserService,
    private authServ: AuthService
  ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(){
    this.authServ.showUser().subscribe(
      data => {
        this.userUpdate = data;
        console.log(this.userUpdate);
      },
      err => {
        console.error('prfile view ng after view broken');

      }
    )

  }

  updateUser(form: NgForm){
    const userToUpdate = form.value;
    this.userServ.updateUser(userToUpdate).subscribe(
      data => {
        console.log('user update success');
        window.alert('Profile has been updated succesfully');
      },
      err => {
        console.error('user update failure');

      }
    )
  }
}
