package application;

import java.io.PrintStream;
import java.util.Scanner;

import player.Player;
import deck.Deck;
import deck.PremadeDeck;

public class PlayerSelection extends ShopApp {
	public PlayerSelection(PrintStream out, Scanner in) {
		super(out, in);
	}	
		
	public Deck runDeckSelection() {
		int choice;
		out.println("Select your Deck: ");
		choice = this.getChoice(1, PremadeDeck.premadeDecks.length, () -> this.printDecks(), index -> PremadeDeck.premadeDecks[index-1].getName());

		return PremadeDeck.premadeDecks[choice - 1];
	}
	public Deck runOpponentDeckSelection() {
		int choice;
		out.println("Select your opponent's Deck: ");
		choice = this.getChoice(1, PremadeDeck.premadeDecks.length, () -> this.printDecks(), index -> PremadeDeck.premadeDecks[index-1].getName());

		return PremadeDeck.premadeDecks[choice - 1];
	}
	
	private void printDecks() {
		int index = 1;
		for (Deck deck : PremadeDeck.premadeDecks) {
			out.println(index + ": " + deck.toString());
			index += 1;
		}
	}	
}