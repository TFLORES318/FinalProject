import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserService } from './user.service';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  // private baseUrl = 'http://localhost:8090/';
  private url = environment.baseUrl;

  loggedInUser: User = null;
  constructor(private http: HttpClient) { }

  login(username, password) {
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    // create request to authenticate credentials
    return this.http
      .get(this.url + 'authenticate', httpOptions)
      .pipe(
        tap((res) => {
          localStorage.setItem('credentials' , credentials);
          console.log(res);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  register(user) {
    // create request to register a new account
    return this.http.post(this.url + 'register', user)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }

  getLoggedInUser(){
    console.log(this.loggedInUser);

    return this.loggedInUser;
  }

  showUser() {
    const credentials = this.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.get<User>(this.url + 'api/users/userpro', httpOptions).pipe(
      tap((res) => {
        console.log(res);
        this.loggedInUser = res;
        console.log(this.loggedInUser);

      }),
      catchError((err: any) => {

        console.log(err);
        return throwError('error in showUser() in auth service - cannot obtain user')
      })
      );
    }
}
