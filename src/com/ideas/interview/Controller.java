package com.ideas.interview;

import com.ideas.interview.entity.Barista;
import com.ideas.interview.enums.CoffeeType;
import com.ideas.interview.enums.Coin;
import com.ideas.interview.entity.Response;
import com.ideas.interview.CoffeeMachine;

import java.util.*;

public class Controller {
	private final Display display;
	private final Barista barista;
	private CoffeeMachine machine;
	private CoffeeType coffeeType;

	public Controller(Display display, Barista barista, CoffeeMachine machine) {
		this.display = display;
		this.barista = barista;
		display.show("Please select a coffee type");
		this.machine = machine;
	}

	public void userWants(CoffeeType coffeeType) {
		this.coffeeType = coffeeType;
		display.show("Great Choice!");
	}

	public void dispense() {
		barista.serve(coffeeType);
		display.show("Please collect your delicious coffee");
	}

	public void userWantsCoffee(Barista barista) {
		String message = "";
		try {
			double amount = machine.selectItemsAndGetPrice(barista);
			message = "Amount to be paid " + amount;
		} catch (Exception e) {
			message = "Exception happened" + e.getMessage();
		}
		display.show(message);
	}

	public void payAmount(Coin coin) {
		String message = "";
		try {
			machine.payAmount(coin);
			message = "Coin successfully inserted";
		} catch (Exception e) {
			message = "Exception happened" + e.getMessage();
		}
		display.show(message);
	}

	public void CollectCoffeeAndChange() {
		String message = "";
		try {
			Response response = machine.dispenseCoffee();
			Barista baristaCofee = (Barista) response.getFirst();
			List<Coin> coins = (List<Coin>) response.getSecond();
			System.out.print(coins.toString());
			message = "thank you here's your coffee " + baristaCofee.servedCoffee() + ". Collect your coins";
		} catch (Exception e) {
			message = "Exception happened" + e.getMessage();
		}
		display.show(message);
	}
}
