import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommentRating } from '../models/comment-rating';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommentRatingService {

  private baseUrl = 'http://localhost:8090/';
  private url = this.baseUrl + 'api/comments/ratings';

  constructor(
    private http: HttpClient
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
}
