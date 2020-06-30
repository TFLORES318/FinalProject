import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AuthService } from 'src/app/services/auth.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // modal field:
  closeResult = '';

  constructor(
    private auth: AuthService,
    private router: Router,
    private userServ: UserService,
    private modalService: NgbModal
  ) { }

  ngOnInit(): void {
    window.scroll(0,0);
  }

  // modal function:
  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  // modal function:
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
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
            console.log('you have successfully logged in')
          },
          err => {
            console.error('error in login - login componenet');

          }
        )
          },
          error => {
            console.error('RegisterComponent.register(): error logging in.');
          }
        );
      }
}
