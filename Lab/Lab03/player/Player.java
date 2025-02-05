package player;

import java.util.Arrays;

import card.*;
import card.base.*;

import deck.Deck;
import deck.PremadeDeck;


public class Player {
	// TODO: constructor
	private String name;
	private int currentDamagePoint;
	private Deck deck;

	public Player(String name) {
		super();
		this.name = name;
		this.deck = PremadeDeck.premadeDecks[0];

		this.currentDamagePoint = 0;
		//this.CharacterCard = 
	}

	public String toString() {
		return new StringBuilder()
				.append("{").append(this.getName()).append(":")
				.append("(Deck Name = ").append(this.getDeck()).append(" )")
				.append(":  [")
				.append("DMG:").append(this.getCurrentDamagePoint())
				.append("] ")
				.toString();
	}
	public String toStringShortVer() {
		return new StringBuilder()
				.append("{").append(this.getName()).append(":")
				.append("DMG:").append(this.getCurrentDamagePoint())
				.append("] ")
				.toString();
	}

	/* GETTERS & SETTERS */
	public String getName() {
		return name;
	}

	public void takeDamage(int damage) {
		currentDamagePoint += damage;	
	}
	public void resetHealth() {
		currentDamagePoint = 0;		
	}
	
	public Deck getDeck() {
		return deck;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public int getCurrentDamagePoint() {
		return currentDamagePoint;
	}

	public void setCurrentDamagePoint(int currentDamagePoint) {
		this.currentDamagePoint = currentDamagePoint;
	}


	
}
