import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { User } from 'src/app/models/user';
import { Post } from 'src/app/models/post';
import { Webinar } from 'src/app/models/webinar';

@Component({
  selector: 'app-profile-view',
  templateUrl: './profile-view.component.html',
  styleUrls: ['./profile-view.component.css']
})
export class ProfileViewComponent implements OnInit {

  post = new Post();

  user = new User();

  userShow = new User();

  posts: Post[] = [];

  enabledPosts: Post[] = [];

  webinars: Webinar[] = [];

  enabledWebinars: Webinar[] = [];

  webinarsHosting: Webinar[] = [];

  enabledHostingWebinars: Webinar[] = [];

  selected = null;

  userLookUp= null;

  userDetailsDisplay = null;

  constructor(private userService: UserService, private authService: AuthService, private currentRoute: ActivatedRoute, private router: Router, private postService: PostService,
    ) { }




  ngOnInit(): void {
    window.scroll(0,0);
    this.loadPosts();
    this.loadWebinars();
    this.loadWebinarsHosted();
    this.getUser();

  }

  getUser() {
    return this.userService.showUser().subscribe(
      data => {
        this.user = data;
      },
      fail => {
        console.error('nope');
      }
    );
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }


  loadPosts() {
    return this.postService.allPostsForUser(this.user.id).subscribe(
      posts => {
        this.posts = posts;
       for (let index = 0; index < posts.length; index++) {
         if (posts[index].enabled) {
           this.enabledPosts.push(posts[index]);
         }

       }
        console.log(localStorage.getItem('credentials'));
      },
      fail => {
        console.error('ERRORRRR');
      }
    );
  }

  loadWebinars() {
    return this.userService.allWebinarsForUser(this.user.id).subscribe(
      webinars => {
        this.webinars = webinars;
        for (let i = 0; i < webinars.length; i++) {
          if (webinars[i].enabled) {
            this.enabledWebinars.push(webinars[i]);
          }
        }
        console.log('webinars retrieved');
      },
      fail => {
        console.error('could not retrieve user webinars from profile view component');
      }
    );
  }

  loadWebinarsHosted() {
    return this.userService.webinarsUserIsHosting().subscribe(
      webinarsHosting => {
        this.webinarsHosting = webinarsHosting;
        for (let i=0; i < webinarsHosting.length; i++) {
          if (webinarsHosting[i].enabled) {
            this.enabledHostingWebinars.push(webinarsHosting[i]);
          }
        }
        console.log('webinars user is hosting retrieved');
      },
      fail => {
        console.error('could not retrieve webinars user is hosting in profile component')
      }
    )
  }

  getSpecificUser(userId: number) {
    console.log(userId);
    return this.userService.getAnotherUser(userId).subscribe(
      data => {
        this.userDetailsDisplay = data;
      },
      fail => {
        console.error('error in profile component - cannot obtain user')
      }
    );
  }

}
