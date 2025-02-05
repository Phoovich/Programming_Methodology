package deck;


import java.util.Arrays;

import card.base.Card;


public class Deck {
	// TODO: constructor
	
	public Deck(String name, Card[] deckList) {
	}

	//You CAN modify the first line
		public int insertCard(Card card) throws InsertCardFailedException{
			int count = 0;
			if(count >= 4) 
				throw new InsertCardFailedException("You can only put 4 of the same cards into the deck");
			//FILL CODE HERE
			//You can use Arrays.copyOf(Original Array, New Length) to create new arrays with bigger size
			//Must return new deckSize

		}

		//You CAN modify the first line
		public Card removeCard(int slotNumber) throws RemoveCardFailedException {
			if (this.deckList.length <= slotNumber) {
				throw new RemoveCardFailedException("Number you insert exceed deck size");
			}		
			if (this.deckList[slotNumber] == null) {
				throw new RemoveCardFailedException("There is no card in that slot");
			}
			//FILL CODE HERE
			//You can use Arrays.copyOf(Original Array, New Length) to create new arrays with bigger size (Added slot is empty)
			//Once card is removed, other card down the list must rearrange to the empty slot
			//Must return card that was removed
		}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("{").append(this.getName()).append("}")
				.append("(").append(this.getDeckSize()).append(" deck size)")
				.toString();
	}

	/* GETTERS & SETTERS */

}
