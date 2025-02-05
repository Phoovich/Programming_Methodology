package application.shop.actions;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import application.ShopApp;
import card.base.*;
import card.type.*;
import deck.Deck;
import deck.InsertCardFailedException;
import deck.PremadeDeck;
import deck.RemoveCardFailedException;
import player.Player;

public class PlayTest  extends ShopApp {

	Player[] opponentList = {
			new Player("Void"),
			new Player("Cat"),
			new Player("Tree" ),
			new Player("Forest" ),
			new Player("Noxas"),
			new Player("Freljord"),
			new Player("Arcane"),
	};
	private Player player;
	private Player opponent;
	private UnitCard playerField[] = {null,null,null,null};
	private UnitCard enemyField[] = {null,null,null,null};
	
	private List<Card> playerHand = new ArrayList<Card>();
	
	private boolean playerWincondition;
	
	public PlayTest(PrintStream out, Scanner in, Player player) {
		super(out, in);
		out.println("Begin Play Testing");
		this.player = player;
	}
	public void run()  {
		int choice;
		//Choose opponent
		choice = this.getChoice(1, opponentList.length, () -> this.prompt_ChooseOppo(), index -> opponentList[index-1].getName());
		opponent = opponentList[choice - 1];
		//enemyField = PremadeDeck.premadeField[choice-1];
		
		for(int i = 0 ; i < 4 ; i++) {	
			if((PremadeDeck.premadeField[choice-1][i]) != null) {
				try {
					enemyField[i] = (UnitCard) ((PremadeDeck.premadeField[choice-1][i]).clone());
				}catch(CloneNotSupportedException c){}  
			}
		}		
		

		
		out.println(opponent.getName() +  " is ready to fight! ");
		
		//ShuffleDeck
		player.setDeck(shuffleArray(player.getDeck()));
		
		//Gameplay
			// Start the game
		out.println("You draw five card");
		DrawCard(4);
		while (winConCheck() == 0 ) {
			out.println("Your Move!!!");
			out.println("Draw!!!");
			DrawCard(1);
			printBoardStatus();
			UseCard ();
			Attack_Phase();
			OpponentAttack_Phase();
			/*
			if(winConCheck()  == 1) {
				out.println("YOU WIN!!!!!!!!");
				break;
			}
			Opponent_Phase();
			if(winConCheck() == 2) {
				out.println("YOU LOSE!!!!!!!!");
				break;
			}
			*/
		}
					
	}
	
	int winConCheck() { // 0 no over, 1 player win, 2 opponent win
		if (player.getCurrentDamagePoint()-opponent.getCurrentDamagePoint() >= 5)
			return 2;
		else if (player.getCurrentDamagePoint()-opponent.getCurrentDamagePoint() <= -5)
			return  1;
		return 0;
	}
	
	void DrawCard ( int count)
	  {
		List<Card> newHand = 
				playerHand;
		for(int i = 0 ; i < count ; i++) {
			out.println("You drew " + player.getDeck().getDeckList()[player.getDeck().getDeckList().length-1] + "card");
			try {
				//out.println(player.getDeck().removeCard(player.getDeck().getDeckList().length-1));
				newHand.add(player.getDeck().removeCard(player.getDeck().getDeckList().length-1));		
			} catch (RemoveCardFailedException e) {
				out.println("Remove Card failed, " + e.message);
			}
			//newHand = Arrays.copyOf(newHand, newHand.size() +1);
			//newHand.get(newHand.size()-1) = player.getDeck().getDeckList()[player.getDeck().getDeckSize()];
			

		}
		playerHand = newHand;
		
	  }
	
	void UseCard () {
		while(true) {
			boolean nextTurnValid = false;
			Card card;
			int choice;
			while(true) {
				//Card card = getCardChoice(playerHand, () -> this.prompt_ChooseHandCard());
				int cardOnFieldCount = 0;
				for (Card card2 : playerField) {
					if(card2 != null ) cardOnFieldCount++;
				}

				choice = this.getChoice(0, playerHand.size(), () -> this.prompt_ChooseHandCard());
				if(choice!=0) {
					card = playerHand.get(choice-1);
					if(  (cardOnFieldCount >=card.getBloodCost()  &&  card instanceof UnitCard ) 
							||  (playerHand.size() >=card.getBloodCost()  &&  card instanceof SpellCard )  ) {
						playerHand.remove(choice-1);
						nextTurnValid = true;
						break;	
					}
				}
				else {
							
					card = playerHand.get(1);
					break;
				}
				out.println("Your chosen card can't be played. You don't have enough card on a field to pay blood cost");
			}
			if(choice == 0) break;
			Card card2 = card;
			for(int i = 0 ; i < card.getBloodCost() ; i++) {
				int a = i;
				if(card instanceof UnitCard) {
					int bChoice = this.getChoice(1, playerHand.size(), () -> this.prompt_UnitCardBloodCost(a,card2.getBloodCost()));
					if(playerField[bChoice-1] != null) {
						playerField[bChoice-1] = null;
					}
					else i--;
				}
				else if(card instanceof SpellCard) {
					int bChoice = this.getChoice(1, playerHand.size(), () -> this.prompt_SpellCardBloodCost(a,card2.getBloodCost()));
					if(playerHand.get(bChoice-1) != null) {
						playerHand.remove(bChoice-1);
					}
					else i--;
				}
				

			}
		
			if(card instanceof UnitCard) {
				int choiceC = this.getChoice(1, 4, () -> this.prompt_PlayUnitCard());
				try {
				playerField[choiceC-1] = (UnitCard) (((UnitCard)card).clone());
				}catch(CloneNotSupportedException c){}  
				if(card instanceof LeaderUnitCard) {
					((LeaderUnitCard)card).buffUnit(playerField);
				}
			}
			
			else if(card instanceof BuffSpellCard) {		
				int choiceC = this.getChoice(1, 4, () -> this.prompt_SpellCard(false,((BuffSpellCard)card2).getPowerIncrease() ));
				if(playerField[choiceC-1] != null) {
					((SpellCard)card).castSpell(playerField[choiceC-1]);					
					break;
				}
				out.println("Your choose an empty slot");

			}
			else if(card instanceof DamageSpellCard) {		
			
				int choiceC = this.getChoice(1, 4, () -> this.prompt_SpellCard(true,((DamageSpellCard)card2).getDamage() ));
				if(enemyField[choiceC-1] != null) {
					((SpellCard)card).castSpell(enemyField[choiceC-1]);	
					if(enemyField[choiceC-1].getHealth()<=0) {
						out.println(enemyField[choiceC-1].getName() + " is dead" );
						enemyField[choiceC-1] = null;
					}
					break;
				}
				out.println("Your choose an empty slot");
			}
			if(nextTurnValid) break;
			out.println("Please select different option");
		}
		

	
	}
	
	public void prompt_ChooseOppo() {
		out.println("Select your opponent");
		int index = 1;
		for (UnitCard[] opponent : PremadeDeck.premadeField) {
			out.println(index + ": " + opponentList[index-1].getName() + " - \t["+opponent[0]+","+opponent[1]+","+opponent[2]+","+opponent[3]+"]");
			index += 1;
		}
	}
	
	public void prompt_ChooseHandCard() {
		out.println("Select your card to play");
		out.println("0: Pass" );
		int index = 1;
		for (Card card : playerHand) {
			out.println(index + ": " + card.toString());
			index += 1;
		}
	}
	
	public void prompt_UnitCardBloodCost(int count, int cost) {
		out.println("Select a unit card to pay a blood cost (" + count + "/" + cost );
		int index = 1;
		for (Card card : playerField) {
			if(card==null) out.println(index + ": Can't choose");
			else out.println(index + ": " + card.toString());
			index += 1;
		}
	}
	public void prompt_SpellCardBloodCost(int count, int cost) {
		out.println("Select a hand card to pay a blood cost (" + count + "/" + cost );
		int index = 1;
		for (Card card : playerHand) {
			if(card==null) out.println(index + ": Can't choose");
			else out.println(index + ": " + card.toString());
			index += 1;
		}
	}
	
	public void prompt_PlayUnitCard() {
		out.println("Select a space to place a unit");
		int index = 1;
		for (Card card : playerField) {
			if(card==null) out.println(index + ": " + "Empty Space");
			else out.println(index + ": " + card.toString());
			index += 1;
		}
	}

	
	
	public void prompt_SpellCard(boolean targetOppo, int value) {
		if(targetOppo) {
			out.println("Select an opponent unit card to deal " + value + " damage");
			int index = 1;
			for (Card card : enemyField) {
				if(card==null) 
					out.println(index + ": " + "Can't choose");
				else
					out.println(index + ": " + card.toString());
				index += 1;
			}
		}
		else {
			out.println("Select an opponent unit card to increase attack by " + value);
			int index = 1;
			for (Card card : playerField) {
				if(card==null) 
					out.println(index + ": " + "Can't choose");
				else
					out.println(index + ": " + card.toString());
				index += 1;
			}
		}
		
	}
	

	static Deck shuffleArray(Deck deck)
	  {
		
		Card[] ar = deck.getDeckList();
	    // If running on Java 6 or older, use `new Random()` on RHS here
	    Random rnd = ThreadLocalRandom.current();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      Card a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	    Deck shuffle = new Deck(deck.getName(), ar);
	    return shuffle;
	  }
	
	void Attack_Phase() {
		out.println("=========== Player Attack Phase ============");	
		for(int i = 0 ; i < 4 ; i++) {
			if(playerField[i] != null) {
				out.println("Player's " + playerField[i].getName() + "[" + i + "]" +  " attacks" + i );			
				int damage;
				if(enemyField[i] != null) {
					damage = playerField[i].attackUnit(enemyField[i]);
					out.println(enemyField[i].getName() + " takes " + damage + " damage | health reduce to" + enemyField[i].getHealth() );
					if(enemyField[i].getHealth()<=0) {
						out.println(enemyField[i].getName() + " is dead" );
						if(enemyField[i] instanceof VenomUnitCard) {
							int selfdamage = ((VenomUnitCard)enemyField[i]).dead(opponent);
							out.println("Opponent takes " + selfdamage + " damage from Venom Unit");
						}
						enemyField[i] = null;
					}
				}
				else {
					damage = playerField[i].attackPlayer(opponent);
					out.println(opponent.getName() + " takes " + damage + " damage");
				}
				if(winConCheck()  == 1) {
					out.println("YOU WIN!!!!!!!!");
					break;
				}
				if(winConCheck() == 2) {
					out.println("YOU LOSE!!!!!!!!");
					break;
				}
			}	
		}
	}

	void OpponentAttack_Phase() {
		out.println("=========== Opponent Attack Phase ============");			
		for(int i = 0 ; i < 4 ; i++) {
			if(enemyField[i] != null) {
				out.println("Opponent's " + enemyField[i].getName() + "[" + i + "]" + " attacks" );			
				int damage;
				if(playerField[i] != null) {
					damage = enemyField[i].attackUnit(playerField[i]);
					out.println(playerField[i].getName() + " takes " + damage + " damage | health reduce to" + playerField[i].getHealth() );
					if(playerField[i].getHealth()<=0) {
						out.println(playerField[i].getName() + " is dead" );
						if(playerField[i] instanceof VenomUnitCard) {
							int selfdamage = ((VenomUnitCard)playerField[i]).dead(opponent);
							out.println("Player takes " + selfdamage + " damage from Venom Unit");
						}
						playerField[i] = null;
					}
				}
				else {
					damage = enemyField[i].attackPlayer(player);
					out.println(player.getName() + " takes " + damage + " damage");
				}
				if(winConCheck()  == 1) {
					out.println("YOU WIN!!!!!!!!");
					break;
				}
				if(winConCheck() == 2) {
					out.println("YOU LOSE!!!!!!!!");
					break;
				}
			}	
		}
	}
	

	
	/*
	int dealDamage(Player attacker, Player defender) {
		out.println(attacker.getName()+" attacks "+ defender.getName());
		int damage = attacker.getAttack() - defender.getDefense();
		damage = damage>0?damage:0;
		out.println(attacker.getName()+" deals " + damage + " damage to " + defender.getName());
		defender.setCurrentLifePoint(defender.getCurrentLifePoint()-damage);
		defender.setCurrentLifePoint(defender.getCurrentLifePoint()>0?defender.getCurrentLifePoint():0);
		out.println(defender.getName() + " has " + defender.getCurrentLifePoint() + " Life Point left");
		return damage;
	}
	*/
	void printGameStatus() {
		out.println("====================Game Status=========================");
		out.println(player.toStringShortVer() );
		out.println(opponent.toStringShortVer() );
		out.println("========================================================");
	}
	void printBoardStatus() {
		out.println("====================Board Statuc=========================");
		//Enemy Role
		out.println(opponent.toStringShortVer() );
		out.println("|" + printCardNameOnBoard(false,0) + "| " +
				"|" + printCardNameOnBoard(false,1) + "| " +
				"|" + printCardNameOnBoard(false,2) + "| " +
				"|" + printCardNameOnBoard(false,3) + "|");
		out.println("|   | |   | |   | |   |");
		out.println("|" + printCardStatOnBoard(false,0) + "| " +
				"|" + printCardStatOnBoard(false,1) + "| " +
				"|" + printCardStatOnBoard(false,2) + "| " +
				"|" + printCardStatOnBoard(false,3) + "|");
		out.println("-----------------------");
		//Player Role
		out.println("|" + printCardNameOnBoard(true,0) + "| " +
				"|" + printCardNameOnBoard(true,1) + "| " +
				"|" + printCardNameOnBoard(true,2) + "| " +
				"|" + printCardNameOnBoard(true,3) + "|");
		out.println("|   | |   | |   | |   |");
		out.println("|" + printCardStatOnBoard(true,0) + "| " +
				"|" + printCardStatOnBoard(true,1) + "| " +
				"|" + printCardStatOnBoard(true,2) + "| " +
				"|" + printCardStatOnBoard(true,3) + "|");
		out.println(player.toString() );
		out.println("========================================================");
	}
	String printCardNameOnBoard(boolean isPlayer, int slot) {
		if((isPlayer?playerField[slot]:enemyField[slot])!=null)
			return String.format("%3.3s", isPlayer?(playerField[slot].getName()):(enemyField[slot].getName()));
		else return "   ";
	}
	String printCardStatOnBoard(boolean isPlayer, int slot) {
		if((isPlayer?playerField[slot]:enemyField[slot])!=null)
			return (isPlayer?(playerField[slot].getPower()):(enemyField[slot].getPower())) + " "
					+ (isPlayer?(playerField[slot].getHealth()):(enemyField[slot].getHealth()));
		else return "   ";
	}

	
}