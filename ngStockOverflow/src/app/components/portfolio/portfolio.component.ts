import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { StockService } from 'src/app/services/stock.service';
import { Stock } from 'src/app/models/stock';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {

  userStocks: Stock[] = [];

  constructor(
    private auth: AuthService,
    private userService: UserService,
    private stockService: StockService
  ) { }

  ngOnInit(): void {
    this.loadUsersStocks();
  }

  loadUsersStocks(){
    this.userService.getUserStock().subscribe(
      data => {
        this.userStocks = data;
      },
      err => {
        console.error('error in portfolio component ts');

      }
    )
  }

}
