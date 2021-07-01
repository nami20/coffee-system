package com.ideas.interview;

import com.ideas.interview.entity.Barista;
import com.ideas.interview.entity.Response;
import com.ideas.interview.enums.CoffeeType;
import com.ideas.interview.enums.Coin;
import java.util.List;

public interface CoffeeMachine {
	double selectItemsAndGetPrice(Barista baristaCoffee) throws Exception;
	void payAmount(Coin coin);
	Response dispenseCoffee() throws Exception;
}
