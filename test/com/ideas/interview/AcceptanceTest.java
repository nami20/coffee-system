package ideas.interview;

import com.ideas.interview.CoffeeMachine;
import com.ideas.interview.CoffeeMachineImpl;
import com.ideas.interview.Controller;
import com.ideas.interview.Display;
import com.ideas.interview.enums.CoffeeAdditionals;
import com.ideas.interview.enums.CoffeeType;
import com.ideas.interview.enums.Coin;
import org.junit.Test;
import com.ideas.interview.entity.Barista;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class AcceptanceTest {

	private Controller controller;
	private Display display;
	private Barista barista;
	private CoffeeMachine machine;

	@Test
	public void dispenseBlackCoffeeForFree() {
		powerUp();
		assertEquals("Please select a coffee type", display.currentMessage());
		userWants(CoffeeType.BLACK);
		assertEquals("Great Choice!", display.currentMessage());
		controller.dispense();
		assertEquals(CoffeeType.BLACK, barista.servedCoffee());
		assertEquals("Please collect your delicious coffee", display.currentMessage());
	}

	@Test
	public void dispenseBlackCoffeewithsugarForFree() {
		powerUp();
		assertEquals("Please select a coffee type", display.currentMessage());
		barista.setAdditionals(Arrays.asList(CoffeeAdditionals.SUGAR));
		userWantsCoffee(barista);
		assertEquals("Amount to be paid 0.35", display.currentMessage());
		controller.payAmount(Coin.PENNY);
		assertEquals("Coin successfully inserted", display.currentMessage());
		controller.CollectCoffeeAndChange();
		assertEquals(CoffeeType.BLACK, barista.servedCoffee());
		assertEquals("thank you here's your coffee " + CoffeeType.BLACK + ". Collect your coins", display.currentMessage());
	}

	private AcceptanceTest userWantsCoffee(Barista barista) {
		controller.userWantsCoffee(barista);
		return this;
	}

	private AcceptanceTest userWants(CoffeeType coffeeType) {
		controller.userWants(coffeeType);
		return this;
	}

	private void powerUp() {
		display = new Display();
		barista = new Barista();
		controller = new Controller(display, barista, new CoffeeMachineImpl());
	}
}
