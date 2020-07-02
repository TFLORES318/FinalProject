import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Stock } from '../models/stock';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StockService {
  // private baseUrl = 'http://localhost:8090/';
  private url = environment.baseUrl + 'api/stocks';

  constructor(private http: HttpClient, private authService: AuthService) { }

  index() {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Stock[]>(this.url, httpOptions).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('Error with loading stocks');
      })
    );
  }

  showStock(stockSymbol:string) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Stock>(this.url + '/' + stockSymbol, httpOptions).pipe(
      catchError((err:any) => {
        console.log(err);
        return throwError('Error with showing stock in stock service');
      })
    );
  }

  createStock(newStock: Stock) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.post<Stock>(this.url, newStock, httpOptions).pipe(
      catchError((err:any) => {
        console.error('error in stock service for creating stock');
        return throwError('Error with creating stock');
      })
    );
  }

  updateStock(stockSymbol:string, stockToEdit:Stock) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };

    return this.http.put<Stock>(this.url + '/' + stockSymbol, stockToEdit, httpOptions).pipe(
      catchError((err:any) => {
        console.error('error in update stock service')
        return throwError('Error in updating stock requested')
      })
    );
  }

  destroyStock(stockSymbol:string) {
    const credentials = this.authService.getCredentials();
    const httpOptions = {
      headers: new HttpHeaders({
        'Authorization': `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.delete(this.url + '/' + stockSymbol, httpOptions).pipe(
      catchError((err: any) => {
        console.error('error in deleting stock');
        return throwError('Error in deleting stock in stock service');
      })
    );
  }
}
