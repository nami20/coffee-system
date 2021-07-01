package com.ideas.interview.enums;

public enum CoffeeAdditionals {
	CREAM("Cream", 0.0),
	SUGAR ("Sugar", 0.0),
	CREAM_SUGAR ("Cream_Sugar", 0.0),
	HOT_WATER ("HotWater", 0.0),
	CUPS ("Cups", 0.0),
	COFFEE_POWDER ("Coffee_Powder", 0.0);

	private String name;
	private double price;

	private CoffeeAdditionals(String name, double price){
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
