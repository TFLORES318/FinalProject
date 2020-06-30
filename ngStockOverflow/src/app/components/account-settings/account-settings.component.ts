import { Component, OnInit, AfterViewInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account-settings',
  templateUrl: './account-settings.component.html',
  styleUrls: ['./account-settings.component.css']
})
export class AccountSettingsComponent implements OnInit, AfterViewInit {

  userUpdate: User = new User();

  constructor(
    private userServ: UserService,
    private authServ: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    window.scroll(0,0);
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

    applyForVerification(user:User){
      user.role = 'APPLICANT';
      this.userServ.updateUser(user).subscribe(
        data => {
          console.log('succesfully set role to applicant');
          window.alert('Thank you for your application! Your community standing will be evaluated by our admin. We will get back to you shortly.');
      }
    )
  }

  disableUser(user: User){
    this.userServ.disableUser(user).subscribe(
      data => {
        console.log('account deleted');
        window.alert('Account deleted succesfully');
        this.authServ.logout();
        this.router.navigateByUrl('/home');
      },
      err => {
        console.error('account deletion failure');

      }
    )
  }
}
