import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators'

@Injectable({
  providedIn: 'root'
})
export class CommentService {
  private url = 'http://localhost:8090'
  private baseUrl = this.url + 'api/posts/{postId}/comments'


  constructor(private http: HttpClient) { }


  index() {
    return this.http.get<Comment[]>(this.url).pipe(
      catchError((err : any) => {
        console.log(err);
        return throwError('Error with fetching Comments');
      })
    );
  }

  createNewComment(newComment: Comment) {
    return this.http.post<Comment>(this.url, newComment).pipe(
      catchError((err: any) => {
        console.error('Error in service to create a comment');
        return throwError('Error with creating a comment');
      })
    );
  }

  updateComment(commentId: number, commentToEdit: Comment) {
    return this.http.put<Comment>(this.url + '/' + commentId, commentToEdit).pipe(
      catchError((err: any) => {
        console.error('error in update comment service');
        return throwError('error in update');
      })
    );
  }

  destroyComment(commentId:number, commentToDelete: Comment) {
    return this.http.put<Comment>(this.url + '/delete/' + commentId, commentToDelete).pipe(
      catchError((err:any) => {
        console.error('error in delete comment');
        return throwError('destroy comment for comment service not working');
      })
    );
  }

}
