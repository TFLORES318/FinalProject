import { Component, OnInit, AfterViewInit } from '@angular/core';
import { PostService } from 'src/app/services/post.service';
import { Router } from '@angular/router';
import { Post } from 'src/app/models/post';
import { Comment } from 'src/app/models/comment';
import { AuthService } from 'src/app/services/auth.service';
import { CommentService } from 'src/app/services/comment.service';
import { NgForm } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user';

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.css']
})
export class ForumComponent implements OnInit, AfterViewInit{

  allPosts: Post[] = [];

  newPost = new Post();

  postComments = [];

  selectedPost = null;

  comment = new Comment();

  userCheck = new User();

  postToEdit = null;

  commentToEdit = null;

  selectedComment = null;


  constructor(
    private postService: PostService,
    private router: Router,
    private auth: AuthService,
    private commentService: CommentService,
    private userService: UserService
  ) { }

  ngOnInit(): void {
    this.loadAllPosts();
  }


  ngAfterViewInit(){
    this.auth.showUser().subscribe(
      data => {
        this.userCheck = data;
        console.log(this.userCheck);
      },
      err => {
        console.error('prfile view ng after view broken');

      }
    )

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

  createPost(postForm: NgForm){
    const post: Post = postForm.value;

    this.postService.create(post).subscribe(
      data => {
        console.log('post creation success');
        this.loadAllPosts();
      },
      err => {
        console.error('post creation failure');
      }
    )
    postForm.reset();
  }

  setEditPost() {
    this.postToEdit = Object.assign({}, this.selectedPost);
  }

  updatePost(post: Post){
    this.postService.update(post, post.id).subscribe(
      data => {
        console.log('post update success');
        this.selectedPost = this.postToEdit;
        this.postToEdit = null;
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

  postDetails(post:Post) {
    this.selectedPost = post;
  }

  displayPosts() {
    this.selectedPost = null;
  }


  postComment(commentForm: NgForm, postId: number, post: Post) {
    const comment: Comment = commentForm.value;
    this.commentService.createNewComment(comment, postId).subscribe(
      data => {
        this.comment = data;
        console.log('comment is posted')
        this.loadComments(post);
      },
      err => {
        console.error('comment did not get posted');

      }
      )
      commentForm.reset();
    }

    commentDetails(comment:Comment) {
      this.selectedComment = comment;
    }

    setEditComment() {
      this.commentToEdit = Object.assign({}, this.selectedComment);
    }

    updateComment(comment: Comment, postId: number, post: Post) {
      this.commentService.updateComment(comment.id, comment, postId).subscribe(
        data => {
          this.selectedComment = this.commentToEdit;
          this.commentToEdit = null;
          this.loadComments(post);
        },
        err => {
          console.error('dun work it dun wok');

        }
      )
    }

    deleteComment(comment: Comment, postId: number, post:Post) {
      this.commentService.destroyComment(comment.id, comment, postId).subscribe(
        data => {
          console.log('comment deleted');
          this.loadComments(post);
        },
        err => {
          console.error('this is not working wah');
        }
      )
    }

  }


