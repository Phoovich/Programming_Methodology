package application.shop.actions.forge;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import card.type.BuffSpellCard;

public class ForgeBuffSpellCard extends ShopApp {
	public ForgeBuffSpellCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging Buff Spell Card");
	}
	
	public BuffSpellCard runForge() {
		in.nextLine();
		out.print("Input spell name: ");
		String name = in.nextLine();
		
		out.print("Input flavor text: ");
		String flavortext = in.nextLine();
		
		out.print("Input blood cost: ");
		int bloodCost = in.nextInt();
		
		int spellSpeedChoice = this.getChoice(1, 2, false, () -> this.promptSpellSpeed());
		
		out.print("Input power increase from this card: ");
		int powerIncrease = in.nextInt();
	
		return new BuffSpellCard(name, flavortext, bloodCost, spellSpeedChoice==1, powerIncrease);
	}
	
	public void promptSpellSpeed() {
		out.println("Choose this spell speed? :");
		out.println("1: Burst (Doesn't end your turn when cast)");
		out.println("2: Slow (End your turn when cast)");
	}
}