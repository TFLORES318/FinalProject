import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { User } from 'src/app/models/user';
import { Post } from 'src/app/models/post';
import { Webinar } from 'src/app/models/webinar';

@Component({
  selector: 'app-other-user-profile',
  templateUrl: './other-user-profile.component.html',
  styleUrls: ['./other-user-profile.component.css']
})
export class OtherUserProfileComponent implements OnInit {

  user = new User();

  userId: number = 0;

  posts: Post[] = [];

  enabledPosts: Post[] = [];

  webinars: Webinar[] = [];

  enabledWebinars: Webinar[] = [];

  constructor(
    private userService: UserService,
    private authService: AuthService,
    private currentRoute: ActivatedRoute,
    private router: Router,
    private postService: PostService) { }

  ngOnInit(): void {
    this.currentRoute.paramMap.subscribe(
      params => {
        console.log(params.get('id'))
        this.userId = parseInt(params.get('id'));
        console.log(this.userId);
      }
    )
    this.getSpecificUser();
    this.loadPosts();
    this.loadWebinars();
  }

  getSpecificUser() {
    console.log(this.userId);
    return this.userService.getAnotherUser(this.userId).subscribe(
      data => {
        this.user = data;
        console.log(this.user);
      },
      fail => {
        console.error('error in profile component - cannot obtain user')
      }
    );
  }

  loadWebinars() {
    return this.userService.allWebinarsForUser(this.userId).subscribe(
      data => {
        this.webinars = data;
        for (let i = 0; i < this.webinars.length; i++) {
          if (this.webinars[i].enabled) {
            this.enabledWebinars.push(this.webinars[i]);
          }
        }
        console.log('webinars retrieved');
      },
      fail => {
        console.error('could not retrieve user webinars from profile view component');
      }
    );
  }

  loadPosts() {
    return this.postService.allPostsForUser(this.userId).subscribe(
      data => {
        this.posts = data;
       for (let index = 0; index < this.posts.length; index++) {
         if (this.posts[index].enabled) {
           this.enabledPosts.push(this.posts[index]);
         }

       }
        console.log(localStorage.getItem('credentials'));
      },
      fail => {
        console.error('ERRORRRR');
      }
    );
  }


}
