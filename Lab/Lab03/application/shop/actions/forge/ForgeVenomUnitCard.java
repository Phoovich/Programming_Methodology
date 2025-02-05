package application.shop.actions.forge;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import card.type.VenomUnitCard;

public class ForgeVenomUnitCard extends ShopApp {

	public ForgeVenomUnitCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging Venom Unit Card");
	}
	
	public VenomUnitCard runForge() {
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
		
		out.print("Vemon Unit Card : ");
		return new VenomUnitCard(name, flavortext, bloodCost,  power, health);
	}
	

}