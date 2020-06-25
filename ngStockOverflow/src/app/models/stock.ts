export class Stock {

  symbol: string;
  companyName: string;
  exchange: string;


  constructor(symbol?:string, companyName?:string, exchange?:string) {
    this.symbol = symbol;
    this.companyName = companyName;
    this.exchange = exchange;
  }
}
