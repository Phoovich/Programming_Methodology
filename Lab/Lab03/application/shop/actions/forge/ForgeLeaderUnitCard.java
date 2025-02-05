package application.shop.actions.forge;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import card.type.LeaderUnitCard;

public class ForgeLeaderUnitCard extends ShopApp {

	public ForgeLeaderUnitCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging Leader Unit Card");
	}
	
	public LeaderUnitCard runForge() {
		in.nextLine();
		out.print("Input spell name: ");
		String name = in.nextLine();
		
		out.print("Input flavor text: ");
		String flavortext = in.nextLine();
		
		out.print("Input blood cost: ");
		int bloodCost = in.nextInt();
		
		out.print("Input power: ");
		int power = in.nextInt();
		
		out.print("Input health: ");
		int health = in.nextInt();
		
		out.print("Input buff power: ");
		int buffPower = in.nextInt();
		
		out.print("Input buff health: ");
		int buffHealth = in.nextInt();
		
		out.print("Leader Unit Card : ");
		return new LeaderUnitCard(name, flavortext, bloodCost,  power, health, buffPower, buffHealth);
	}
	

}