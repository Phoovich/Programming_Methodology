package application.shop.actions.forge;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import card.type.DamageSpellCard;

public class ForgeDamageSpellCard extends ShopApp {
	public ForgeDamageSpellCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging Damage Spell Card");
	}
	
	public DamageSpellCard runForge() {
		in.nextLine();
		out.print("Input spell name: ");
		String name = in.nextLine();
		
		out.print("Input flavor text: ");
		String flavortext = in.nextLine();
		
		out.print("Input blood cost: ");
		int bloodCost = in.nextInt();
		
		int spellSpeedChoice = this.getChoice(1, 2, false, () -> this.promptSpellSpeed());
		
		out.print("Input damage this spell deal: ");
		int damage = in.nextInt();
	
		out.print("Damage Spell Card : ");
		return new DamageSpellCard(name, flavortext, bloodCost, spellSpeedChoice==1, damage);
	}
	
	public void promptSpellSpeed() {
		out.println("Choose this spell speed? :");
		out.println("1: Burst (Doesn't end your turn when cast)");
		out.println("2: Slow (End your turn when cast)");
	}
}