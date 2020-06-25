import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { NavigationComponent } from './components/navigation/navigation.component';
import { StockTickerWidgetComponent } from './components/stock-ticker-widget/stock-ticker-widget.component';
import { HomeComponent } from './components/home/home.component';
import { PortfolioComponent } from './components/portfolio/portfolio.component';
import { AccountSettingsComponent } from './components/account-settings/account-settings.component';
import { ProfileViewComponent } from './components/profile-view/profile-view.component';
import { ForumComponent } from './components/forum/forum.component';
import { ResourcesComponent } from './components/resources/resources.component';
import { WebinarsComponent } from './components/webinars/webinars.component';
import { FooterComponent } from './components/footer/footer.component';
import { AboutComponent } from './components/about/about.component';
import { CommentRatingService } from './services/comment-rating.service';
import { CommentService } from './services/comment.service';
import { PostService } from './services/post.service';
import { StockService } from './services/stock.service';
import { UserStockJournalService } from './services/user-stock-journal.service';
import { UserService } from './services/user.service';
import { WebinarRatingService } from './services/webinar-rating.service';
import { WebinarService } from './services/webinar.service';
import { NotFoundComponent } from './components/not-found/not-found.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationComponent,
    StockTickerWidgetComponent,
    PortfolioComponent,
    AccountSettingsComponent,
    HomeComponent,
    ProfileViewComponent,
    ForumComponent,
    ResourcesComponent,
    WebinarsComponent,
    FooterComponent,
    AboutComponent,
    NotFoundComponent
  ],
  imports: [
    NgbModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    CommentRatingService,
    CommentService,
    PostService,
    StockService,
    UserStockJournalService,
    UserService,
    WebinarRatingService,
    WebinarService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
