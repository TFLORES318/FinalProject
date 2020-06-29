import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CommentRating } from '../models/comment-rating';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { NgForm } from '@angular/forms';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CommentRatingService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/comments/ratings';

  constructor(
    private http: HttpClient,
    private auth: AuthService
  ) { }

  getCommentRatingsForAUser(userId: number){
    return this.http.get<CommentRating[]>(this.url +'/'+userId)
    .pipe(
      catchError((err:any)=>{
        console.log('cannot retrieve comment ratings');
        return throwError('cannot retrieve comment ratings');
      })
    )
  }

  createCommentRating(commentRating: CommentRating){
    const credentials = this.auth.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    }
    return this.http.post<CommentRating>(this.url, commentRating, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log('cannot create comment rating');
        return throwError('cannot create comment rating');
      })
    )

  }
}
