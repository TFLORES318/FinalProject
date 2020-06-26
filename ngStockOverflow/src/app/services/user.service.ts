import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Post } from '../models/post';
import { AuthService } from './auth.service';
import { Stock } from '../models/stock';
import { identifierModuleUrl } from '@angular/compiler';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/users';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getUserStock(){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.get<Stock[]>(this.url + '/stocks', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in get user stock user serv');
        return throwError('error in get user stock user serv')
      })
    );

  }

  showUser() {
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.get<User>(this.url + '/userpro', httpOptions).pipe(

      catchError((err: any) => {

        console.log(err);
        return throwError('ehh')
      })
      );
    }

    updateUser(user: User){
      const credentials = this.auth.getCredentials();
      const httpOptions = {
        headers: new HttpHeaders({
          'Authorization': `Basic ${credentials}`,
          'X-Requested-With': 'XMLHttpRequest'
        })
      }
      return this.http.put<User>(this.url + '/update', user, httpOptions)
      .pipe(
        catchError((err: any) => {
          console.log('error in user serv update user');
          return throwError('ehhrror in user serv update user')
      })
    );
  }

 getAllUsers(){
  const credentials = this.auth.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.get<User[]>(this.url, httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log('error in get all users()-user service');
      return throwError('error in getAllUsers() userSvc');
    })
  );
 }

}
