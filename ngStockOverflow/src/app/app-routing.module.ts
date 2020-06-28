import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { ForumComponent } from './components/forum/forum.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { WebinarsComponent } from './components/webinars/webinars.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AdminComponent } from './components/admin/admin.component';
import { OtherUserProfileComponent} from './components/other-user-profile/other-user-profile.component';


const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home'},
  { path: 'home', component: HomeComponent},
  { path: 'about', component: AboutComponent },
  { path: 'settings', component: AccountSettingsComponent },
  { path: 'forum', component: ForumComponent },
  { path: 'portfolio', component: PortfolioComponent },
  { path: 'profile', component: ProfileViewComponent },
  { path: 'profile/:id', component: OtherUserProfileComponent },
  { path: 'resources', component: ResourcesComponent },
  { path: 'webinars', component: WebinarsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'admin', component: AdminComponent },
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
