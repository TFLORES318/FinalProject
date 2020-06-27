package com.skilldistillery.stockoverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.stockoverflow.entities.Stock;
import com.skilldistillery.stockoverflow.entities.User;
import com.skilldistillery.stockoverflow.repositories.StockRepository;
import com.skilldistillery.stockoverflow.repositories.UserRepository;

@Service
public class StockServiceImpl implements StockService {

	@Autowired
	private StockRepository stockRepo;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private UserService userServ;

	@Override
	public List<Stock> findAllStocks() {
		return stockRepo.findAll();
	}

	@Override
	public Stock findById(String stockSymbol) {
		Optional<Stock> stockOpt = stockRepo.findById(stockSymbol);
		Stock stock = null;
		if (stockOpt.isPresent()) {
			stock = stockOpt.get();
		}
		return stock;
	}

	@Override
	public Stock findByCompanyName(String companyName) {
		return stockRepo.findByCompanyName(companyName);
	}

	@Override
	public Stock createStock(String username, Stock stock) throws Exception {
		Optional<Stock> existingStock = stockRepo.findById(stock.getSymbol());
		User managedUser = userRepo.findByUsername(username);
		if (managedUser != null) {
			if (existingStock.isPresent()) {
				Stock stockExists = existingStock.get();
				if (!managedUser.getStocks().contains(stockExists)) {
					userServ.addUserStock(username, stockExists);
				}
				return stockExists;
			} else {
				System.out.println(stock);
				stockRepo.saveAndFlush(stock);
				System.out.println(stock);
				managedUser.addStock(stock);
				userRepo.saveAndFlush(managedUser);
				return stock;
			}
		} else {
			return null;
		}
	}

	@Override
	public Stock updateStock(String stockSymbol, Stock stock) {
		Optional<Stock> stockOpt = stockRepo.findById(stockSymbol);
		Stock managedStock = stockOpt.get();
		managedStock.setCompanyName(stock.getCompanyName());
		managedStock.setExchange(stock.getExchange());
//		managedStock.setSymbol(stock.getSymbol());
		stockRepo.saveAndFlush(managedStock);

		return managedStock;
	}

	@Override
	public boolean destroyStock(String stockSymbol, String username) {
//		Optional<Stock> stockOpt = stockRepo.findById(stockSymbol);
//		Stock toDelete = stockOpt.get();
//		boolean deleted;
//		try {
//			stockRepo.delete(toDelete);
//			deleted = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//			deleted = false;
//		}
//		return deleted;
		boolean removed = false;
		User managedUser = userRepo.findByUsername(username);
		Optional<Stock> stockOpt = stockRepo.findById(stockSymbol);
		if (stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			managedUser.removeStock(stock);
			userRepo.saveAndFlush(managedUser);
			removed = true;
		}
		return removed;
	}

}
