import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { PostService } from 'src/app/services/post.service';
import { User } from 'src/app/models/user';
import { Post } from 'src/app/models/post';

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

  selected = null;

  constructor(private userService: UserService, private authService: AuthService, private currentRoute: ActivatedRoute, private router: Router, private postService: PostService,
    ) { }




  ngOnInit(): void {
    this.loadPosts();
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

}
