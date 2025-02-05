package application.shop.actions;

import java.io.PrintStream;
import java.util.Scanner;

import application.ShopApp;
import application.shop.actions.forge.*;

import card.base.*;


public class ForgeCard extends ShopApp {

	public ForgeCard(PrintStream out, Scanner in) {
		super(out, in);
		out.println("Forging new card");
	}

	public Card runForge() {
		int choice = this.getChoice(1, 7, false, () -> this.promptItemType());
		switch (choice) {
		case 1:
			return new ForgeNormalUnitCard(this.out, this.in).runForge();
		case 2:
			return new ForgeDebuffUnitCard(this.out, this.in).runForge();
		case 3:
			return new ForgeLeaderUnitCard(this.out, this.in).runForge();
		case 4:
			return new ForgeVenomUnitCard(this.out, this.in).runForge();
		case 5:
			return new ForgeBuffSpellCard(this.out, this.in).runForge();
		case 6:
			return new ForgeDamageSpellCard(this.out, this.in).runForge();
		case 7:
			return null;
		}
		return null;
	}

	public void promptItemType() {
		out.println("Select card to crate :");
		out.println("1: Leader Unit Card");
		out.println("2: Debuff Unit Card");
		out.println("3: Overwhelm Unit Card");
		out.println("4: Venom Unit Card");
		out.println("5: Overwhelm Unit Card");
		out.println("6: Venom Unit Card");
		out.println("7: Cancel");
	}
}
