package com.ideas.interview.enums;

public enum CoffeeType {
	BLACK ("Black", 0.35);

	private String name;
	private double price;

	private CoffeeType(String name, double price){
		this.name = name;
		this.price = price;
	}

	public String getName(){
		return name;
	}

	public double getPrice(){
		return price;
	}
}
