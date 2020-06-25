import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StockTickerWidgetComponent } from './stock-ticker-widget.component';

describe('StockTickerWidgetComponent', () => {
  let component: StockTickerWidgetComponent;
  let fixture: ComponentFixture<StockTickerWidgetComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StockTickerWidgetComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StockTickerWidgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
