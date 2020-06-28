import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Post } from '../models/post';
import { AuthService } from './auth.service';
import { Stock } from '../models/stock';
import { identifierModuleUrl } from '@angular/compiler';
import { User } from '../models/user';
import { Webinar } from '../models/webinar';

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
    };
    return this.http.put<User>(this.url + '/update', user, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in user serv update user');
        return throwError('ehhrror in user serv update user');
      })
    );
  }

  adminUpdateUser(user: User){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.put<User>(this.url + '/admin/update', user, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in user serv admin update user');
        return throwError('error in adminUpdateUser()');
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

 disableUser(user: User){
  const credentials = this.auth.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  };
  return this.http.put<User>(this.url + '/delete', user, httpOptions)
  .pipe(
    catchError((err: any) => {
      console.log('account not deleted');
    return throwError('account not deleted')
    })
  )
 }

 getAnotherUser(userId: number) {
  // const credentials = this.auth.getCredentials();
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Authorization': `Basic ${credentials}`,
  //     'X-Requested-With': 'XMLHttpRequest'
  //   })
  // };
   return this.http.get<User>(this.url + '/' + userId)
   .pipe(
     catchError((err:any) => {
       console.log('cannot get user');
       return throwError('error in getting specific user');
     })
   );
 }

 allWebinarsForUser(userId:number) {
  const credentials = this.auth.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  }
  return this.http.get<Webinar[]>(this.url + '/webinars', httpOptions).pipe(
    catchError((err: any) => {
      console.log('cannot retrieve webinars');
      return throwError('error in getting webinars for user');
    })
  );
 }

 webinarsUserIsHosting() {
  const credentials = this.auth.getCredentials();
  const httpOptions = {
    headers: new HttpHeaders({
      'Authorization': `Basic ${credentials}`,
      'X-Requested-With': 'XMLHttpRequest'
    })
  }
  return this.http.get<Webinar[]>(this.url + '/webinarshosting', httpOptions).pipe(
    catchError((err:any) => {
      console.log('cannot retrieve webinars user is hosting');
      return throwError('error in getting webinars user is hosting');
    })
  );
 }

 allWebinarsForOtherUser(userId: number){
  // const credentials = this.auth.getCredentials();
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Authorization': `Basic ${credentials}`,
  //     'X-Requested-With': 'XMLHttpRequest'
  //   })
  // }
  return this.http.get<Webinar[]>(this.url +'/'+ userId + '/webinars')
  .pipe(
    catchError((err:any) => {
      console.log('cannot retrieve other user webinars');
      return throwError('cannot retireve other users webinars');
    })
  )
 }

 allPostsForOtherUser(userId: number){
  // const credentials = this.auth.getCredentials();
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Authorization': `Basic ${credentials}`,
  //     'X-Requested-With': 'XMLHttpRequest'
  //   })
  // }
  return this.http.get<Post[]>(this.url +'/'+userId+'/posts')
  .pipe(
    catchError((err:any) => {
      console.log('cannot retrieve other user posts');
      return throwError('cannot retireve other users posts');
    })
  )
 }



}
