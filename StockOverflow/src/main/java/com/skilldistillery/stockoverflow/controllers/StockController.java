package com.skilldistillery.stockoverflow.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.stockoverflow.entities.Stock;
import com.skilldistillery.stockoverflow.services.StockService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4210"})
public class StockController {

	@Autowired
	private StockService stockServ;
	
	//get all Stocks
	@GetMapping("stocks")
	public List<Stock> getAllStocks(){
		return stockServ.findAllStocks();
	}
	
	//find stock by stock symbol
	@GetMapping("stocks/{stockSymbol}")
	public Stock getBySymbol(@PathVariable String symbol, HttpServletResponse res) {
		Stock stock = stockServ.findById(symbol);
		if (stock == null) {
			res.setStatus(404);
		}
		return stock;
	}
	
	//get stock by company name
	@GetMapping("stocks/{companyName}")
	public Stock getByCompanyName(@PathVariable String companyName, HttpServletResponse res) {
		Stock stock = stockServ.findByCompanyName(companyName);
		if (stock == null) {
			res.setStatus(404);
		}
		return stock;
	}
	
	@PostMapping("stocks")
	public Stock createStock(@RequestBody Stock stock, Principal principal, HttpServletRequest req, HttpServletResponse res) {
		try {
			stock = stockServ.createStock(principal.getName(), stock);
			res.setStatus(200);
			StringBuffer url = req.getRequestURL();
			url.append("/").append(stock.getSymbol());
			res.setHeader("Location", url.toString());
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			stock = null;
		}
		return stock;
	}
	
	@PutMapping("stocks/{stockSymbol}")
	public Stock updateStock(@PathVariable String stockSymbol, @RequestBody Stock stock, HttpServletRequest req, HttpServletResponse res) {
		try {
			stock = stockServ.updateStock(stockSymbol, stock);
			if (stock == null) {
				res.setStatus(400);
			}
		} catch (Exception e) {
			res.setStatus(400);
			e.printStackTrace();
			stock = null;
		}
		return stock;
	}
	
//	@DeleteMapping(path = "stocks/{stockSymbol}")
//	public void destroy(HttpServletRequest req, HttpServletResponse res, @PathVariable String stockSymbol, Principal principal) {
//		if(stockServ)
//	}
	
}
