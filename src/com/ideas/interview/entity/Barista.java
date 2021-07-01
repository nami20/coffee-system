package com.ideas.interview.entity;

import com.ideas.interview.enums.CoffeeAdditionals;
import com.ideas.interview.enums.CoffeeType;

import java.util.ArrayList;
import java.util.List;

public class Barista {

	private CoffeeType servedCoffee;
	List<CoffeeAdditionals> additionals;
	double price;

	public void serve(CoffeeType coffeeType) {
		servedCoffee = coffeeType;
	}

	public CoffeeType servedCoffee() {
		return servedCoffee;
	}
	public Barista() {
		this.servedCoffee = CoffeeType.BLACK;
		List<CoffeeAdditionals> additionals = new ArrayList<>();
		additionals.add(CoffeeAdditionals.CREAM_SUGAR);
		this.additionals =  additionals;
	}

	public Barista(CoffeeType coffeeType, List<CoffeeAdditionals> additionals) {
		this.servedCoffee = coffeeType;
		this.additionals = additionals;
	}

	public Barista(CoffeeType coffeeType, List<CoffeeAdditionals> additionals, double price) {
		this.servedCoffee = coffeeType;
		this.additionals = additionals;
		this.price = price;
	}

	public List<CoffeeAdditionals> getAdditionals() {
		return additionals;
	}

	public void setAdditionals(List<CoffeeAdditionals> additionals) {
		this.additionals = additionals;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
