import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Post } from '../models/post';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  // private baseUrl = 'http://localhost:8090/';
  private url = environment.baseUrl + 'api/posts';
  private urlTwo = environment.baseUrl;

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  index(){
    return this.http.get<Post[]>(this.url)
    .pipe(
      catchError((err: any) => {
        console.log('post service index method is broken');
        return throwError('post service index method is broken');
      })
    )
  }

  allPostsForUser(userId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.get<Post[]>(this.urlTwo + 'api/users/posts', httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in all posts for user post service');
        return throwError(' error in all posts for user post service')
      })
    )
  }

  allPostsByTitleSearch(title: string){
    return this.http.get<Post[]>(this.url + '/search/' + title).pipe(
      catchError((err: any) => {
        console.log('error in postsByTitleSearch() in post svc');
        return throwError('error in postsByTitleSearch() in post svc');
      })
    );
  }

  getPostById(postId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.get<Post>(this.url + '/' + postId)
    .pipe(
      catchError((err: any) => {
        console.log('error in get post by id post service');
        return throwError('error in get post by id post service')
      })
    )
  }

  create(newPost: Post){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.post<Post>(this.url, newPost, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in create in post service');
        return throwError('error in create in post service');
      })
    )
  }

  update(post: Post, postId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.put<Post>(this.url + '/' + post.id, post, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('post service update is broken');
        return throwError('post service update is broken');
      })
    )
  }

  disable(postId: number){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.put(this.url + '/disable/' + postId, postId, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('error in post serv disable');
        return throwError('error in post service disable')
      })
    )
  }


}
