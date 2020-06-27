import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit, AfterViewInit {

  user: User = new User();
  constructor(
    private auth: AuthService
  ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(){
    this.auth.showUser().subscribe(
      data => {
        this.user = data;
        console.log(this.user);
      },
      err => {
        console.error('prfile view ng after view broken');
      }
    );
  }

  loggedIn(){
    return this.auth.checkLogin();
  }
}

