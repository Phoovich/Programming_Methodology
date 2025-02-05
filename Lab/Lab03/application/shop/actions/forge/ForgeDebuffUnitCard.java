package application.shop.actions.forge;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import card.type.DebuffUnitCard;

public class ForgeDebuffUnitCard extends ShopApp {

	public ForgeDebuffUnitCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging Debuff Unit Card");
	}
	
	public DebuffUnitCard runForge() {
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
		
		out.print("Input debuff power: ");
		int debuffPower = in.nextInt();
		
		out.print("Debuff Unit Card : ");
		return new DebuffUnitCard(name, flavortext, bloodCost,  power, health, debuffPower);
	}
	

}