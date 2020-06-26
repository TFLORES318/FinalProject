import { Component, OnInit, AfterViewInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit, AfterViewInit {

  // editUser: User = new User();
  user: User = new User();
  userList: User[] = null;

  constructor(
    private userServ: UserService,
    private authServ: AuthService
  ) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  ngAfterViewInit(){
    this.authServ.showUser().subscribe(
      data => {
        this.user = data;
        console.log(this.user);
      },
      err => {
        console.error('prfile view ng after view broken');
      }
    );
  }

  getAllUsers(){
    this.userServ.getAllUsers().subscribe(
      data => {
        this.userList = data;
        console.log(this.userList);
      },
      err => {
        console.log('error in admin component getAllUsers()');
      }
    );
  }

  updateUserStatus(editUser: User){
    editUser.enabled = !editUser.enabled;
    console.log(editUser);
    this.userServ.adminUpdateUser(editUser).subscribe(
      data => {
        editUser = data;
        console.log(editUser);
        this.getAllUsers();
        console.log('user status changed');
      },
      err => {
        console.log('error in admin component updateUserStatus()');
      }
    );
  }

}
