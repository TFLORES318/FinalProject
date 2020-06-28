package com.skilldistillery.stockoverflow.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Stock {

	@Id
	private String symbol;
	
	@Column(name="company_name")
	private String companyName;
	
	private String exchange;
	
	@Column(name="pair_id")
	private int chartId;
	
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(name="user_stock",
//	joinColumns=@JoinColumn(name="stock_symbol"),
//	inverseJoinColumns=@JoinColumn(name="user_id"))
//	private List <User> users;

	// Constructors
	public Stock() {}
	

	public Stock(String symbol, String companyName, String exchange, int chartId) {
	super();
	this.symbol = symbol;
	this.companyName = companyName;
	this.exchange = exchange;
	this.chartId = chartId;
}


	// Methods
	public int getChartId() {
		return chartId;
	}

	public void setChartId(int chartId) {
		this.chartId = chartId;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

	
//	public List<User> getUsers() {
//		return users;
//	}
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}
//	
//	public void addUser(User user) {
//		if (users == null) {
//			users = new ArrayList<>();
//		}
//		if(!users.contains(user)) {
//			users.add(user);
//			user.addStock(this);
//		}
//	}
//	
//	public void removeUser(User user) {
//		if (users != null && users.contains(user)) {
//			users.remove(user);
//			user.removeStock(this);
//		}
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Stock [symbol=" + symbol + ", companyName=" + companyName + ", exchange=" + exchange + "]";
	}
	
	
}
