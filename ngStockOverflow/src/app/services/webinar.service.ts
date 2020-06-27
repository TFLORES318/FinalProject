import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Webinar } from '../models/webinar';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class WebinarService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/webinars';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getWebinars(){
    return this.http.get<Webinar[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log('error getting all webinars');
        return throwError('error getting all webinars')
      })
    )
  }

  getWebinarById(webinarId: number){
    return this.http.get<Webinar>(this.url + '/' + webinarId)
    .pipe(
      catchError((err: any) =>{
        console.log('error getting webinar by id');
        return throwError('error getting webinar by id')
      })
    )
  }

  createWebinar(webinar: Webinar){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.post<Webinar>(this.url, webinar, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('create webinar error');
        return throwError('create webinar error');
      })
    )
  }

  updateWebinar(webinar: Webinar, webinarId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.put<Webinar>(this.url + '/' + webinarId, webinar, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error updating webinar');
        return throwError('error in updating webinar')
      })
    )
  }

  deleteWebinar(webinarId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.put<Webinar>(this.url+'/delete/'+webinarId, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error deleting webinar');
        return throwError('error deleting webinar');
      })
    )
  }

  signUpUser(webinarId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.put<User[]>(this.url+'/'+webinarId+'/signUp', webinarId, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('sign up is broken');
        return throwError ('sign up is broken')
      })
    )
  }

}
