import { Component, OnInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit {

  allPosts: Post[] = [];

  newPost = new Post();

  postComments = [];

  constructor(
    private postService: PostService,
    private router: Router,
    private auth: AuthService,
    private commentService: CommentService
  ) { }

  ngOnInit(): void {
    this.loadAllPosts();
  }

  loadAllPosts(){
    this.postService.index().subscribe(
      data => {
        this.allPosts = data;
        this.allPosts.forEach(value => {
          this.loadComments(value);
        })
      },
      err => {
        console.log('error in load all posts form component');

      }
    )
  }

  createPost(post: Post){
    this.postService.create(post).subscribe(
      data => {
        console.log('post creation success');
        this.loadAllPosts();
      },
      err => {
        console.error('post creation failure');
      }
    )
  }

  updatePost(post: Post){
    this.postService.update(post).subscribe(
      data => {
        console.log('post update success');
        this.loadAllPosts();
      },
      err => {
        console.error('post update failure');
      }
    )
  }

  deletePost(postId: number){
    this.postService.disable(postId).subscribe(
      data => {
        console.log('post delete success');
        this.loadAllPosts();
      },
      err => {
        console.error('post deletion failure');

      }
    )
  }

  loadComments(post: Post){
    this.commentService.index(post.id).subscribe(
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
