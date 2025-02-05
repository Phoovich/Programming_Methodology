package card.base;

import player.Player;

//You CAN modify the first line
public class UnitCard extends Card  {
		
	public UnitCard(String name, String flavorText, int bloodCost, int power, int health) {

	}
	

	public int attackPlayer(Player opponent) {
		/*
		 Hint:
			Use takeDamage(int)
			from Player class
		 */
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getName() + "\tCost: " + this.getBloodCost() +"\t(POW: " + this.getPower() + ", HP: " + this.getHealth() + ")";
	}

}
