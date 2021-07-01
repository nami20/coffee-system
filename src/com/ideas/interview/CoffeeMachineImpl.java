package com.ideas.interview;

import com.ideas.interview.entity.Barista;
import com.ideas.interview.enums.CoffeeAdditionals;
import com.ideas.interview.enums.CoffeeType;
import com.ideas.interview.enums.Coin;
import com.ideas.interview.entity.Response;

import java.util.*;

public class CoffeeMachineImpl implements CoffeeMachine {

	private Inventory<Coin> cashInventory = new Inventory<Coin>();
	private Inventory<CoffeeType> coffeeInventory = new Inventory<CoffeeType>();
	private Inventory<CoffeeAdditionals> coffeeAdditionalsInventory = new Inventory<CoffeeAdditionals>();
	private long totalSales;
	private Barista currentItem;
	private double currentBalance = 0.0;

	public CoffeeMachineImpl(){
		initialize();
	}

	private void initialize(){

		for(Coin c : Coin.values()){
			cashInventory.put(c, 10);
		}

		for(CoffeeType i : CoffeeType.values()){
			coffeeInventory.put(i, 10);
		}

		for(CoffeeAdditionals i : CoffeeAdditionals.values()){
			coffeeAdditionalsInventory.put(i, 10);
		}
	}

	@Override public double selectItemsAndGetPrice(Barista baristaCoffee) throws Exception {
		if(coffeeInventory.hasItem(baristaCoffee.servedCoffee())){
			currentItem = baristaCoffee;
			double price = 0.0;
			for(CoffeeAdditionals add : CoffeeAdditionals.values()){
				if(!coffeeAdditionalsInventory.hasItem(add)){
					throw new Exception("Sold Out, Please buy another item");
				} else {
					price += add.getPrice();
				}
			}
			currentItem.setPrice(price + currentItem.servedCoffee().getPrice());
			return currentItem.getPrice();
		}
		throw new Exception("Sold Out, Please buy another item");
	}

	@Override
	public void payAmount(Coin coin) {
		currentBalance = currentBalance + coin.getDenomination();
		cashInventory.add(coin);
	}

	@Override
	public Response dispenseCoffee() throws Exception {
		Barista item = check();
		List<Coin> change = collectChange();

		return new Response<Barista, List<Coin>>(item, change);
	}

	private Barista check() throws Exception{
		if(isFullPaid()){
			if(hasSufficientChange()){
				coffeeInventory.deduct(currentItem.servedCoffee());
				for(CoffeeAdditionals i : CoffeeAdditionals.values()){
					coffeeAdditionalsInventory.deduct(i);
				}
				return currentItem;
			}
			throw new Exception("Not Sufficient change in Inventory");

		}
		double remainingBalance = currentItem.getPrice() - currentBalance;
		throw new Exception("Price not full paid, remaining : " + remainingBalance);
	}

	private List<Coin> collectChange() throws Exception {
		double changeAmount = currentBalance - currentItem.getPrice();
		List<Coin> change = getChange(changeAmount);
		updateCashInventory(change);
		currentBalance = 0;
		currentItem = null;
		return change;
	}


	private boolean isFullPaid() {
		if(currentBalance >= currentItem.getPrice()){
			return true;
		}
		return false;
	}


	private List<Coin> getChange(double amount) throws Exception{
		List<Coin> changes = Collections.EMPTY_LIST;

		if(amount > 0){
			changes = new ArrayList<Coin>();
			double balance = amount * 100;
			while(balance > 0){
				if(balance >= Coin.QUARTER.getDenomination()
						&& cashInventory.hasItem(Coin.QUARTER)){
					changes.add(Coin.QUARTER);
					balance = balance - Coin.QUARTER.getDenomination();
					continue;

				}else if(balance >= Coin.DIME.getDenomination()
						&& cashInventory.hasItem(Coin.DIME)) {
					changes.add(Coin.DIME);
					balance = balance - Coin.DIME.getDenomination();
					continue;

				}else if(balance >= Coin.NICKLE.getDenomination()
						&& cashInventory.hasItem(Coin.NICKLE)) {
					changes.add(Coin.NICKLE);
					balance = balance - Coin.NICKLE.getDenomination();
					continue;

				}else if(balance >= Coin.PENNY.getDenomination()
						&& cashInventory.hasItem(Coin.PENNY)) {
					changes.add(Coin.PENNY);
					balance = balance - Coin.PENNY.getDenomination();
					continue;

				}else{
					throw new Exception("NotSufficientChange, Please try another product");
				}
			}
		}

		return changes;
	}


	private boolean hasSufficientChange(){
		return hasSufficientChangeForAmount(currentBalance - currentItem.getPrice());
	}

	private boolean hasSufficientChangeForAmount(double amount){
		boolean hasChange = true;
		try{
			getChange(amount);
		}catch(Exception e){
			return hasChange = false;
		}

		return hasChange;
	}

	private void updateCashInventory(List<Coin> change) {
		for(Coin c : change){
			cashInventory.deduct(c);
		}
	}

}
