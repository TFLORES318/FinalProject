import { Component, OnInit, AfterViewInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { PostService } from 'src/app/services/post.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit, AfterViewInit {

  search: string = null;
  postComments = [];
  posts: Post[] = [];
  user: User = new User();
  constructor(
    private auth: AuthService,
    private postSvc: PostService,
    private commentSvc: CommentService,
    private route: Router
  ) { }

  ngOnInit(): void {
  }

  ngAfterViewInit(){
    this.auth.showUser().subscribe(
      data => {
        this.user = data;
        console.log(this.user);
      },
      err => {
        console.error('profile view ng after view broken');
      }
    );
  }

  loggedIn(){
    return this.auth.checkLogin();
  }

  // post search
  searchForPost(form: NgForm){
    this.search = form.value.searchWord;

    this.postSvc.allPostsByTitleSearch(this.search).subscribe(
      data => {
        this.posts = data;
        this.posts.forEach(value => {
          this.loadComments(value);
        })
        this.route.navigateByUrl('/forum', {state: this.posts});
      },
      err => {
        console.error('searchForPost() in nav ts error');
      }
    );
  }

  loadComments(post: Post){
    this.commentSvc.index(post.id).subscribe(
      data => {
        post.comments = data;
        console.log('load comment success in forum comp ts');
        console.log(this.postComments);
      },
      err => {
        console.error('load comments in forum comp ts error');

      }
    )
  }
}

