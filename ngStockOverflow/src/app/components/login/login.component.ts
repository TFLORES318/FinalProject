import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private auth: AuthService,
    private router: Router,
    private userServ: UserService
  ) { }

  ngOnInit(): void {
    window.scroll(0,0);
  }


  login(form: NgForm){
    const user = form.value.username;
    const pw = form.value.password;
    this.auth.login(user, pw).subscribe(
      data => {
        console.log('LoginComponent.login(): user logged in.');
        this.router.navigateByUrl('/home');
        this.auth.showUser().subscribe(
          data => {
            console.log('yay you are here')
          },
          err => {
            console.error('whyyyyyyYYyy');

          }
        )
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      }
}
