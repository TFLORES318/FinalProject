export class Stock {

  symbol: string;
  companyName: string;
  exchange: string;
  chartId: number;


  constructor(symbol?:string, companyName?:string, exchange?:string, chartId?:number) {
    this.symbol = symbol;
    this.companyName = companyName;
    this.exchange = exchange;
    this.chartId = chartId;
  }
}
