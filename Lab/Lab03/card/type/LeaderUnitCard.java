package card.type;

import card.base.UnitCard;
import player.Player;

//You CAN modify the first line
public class LeaderUnitCard extends UnitCard {
	
	public LeaderUnitCard(String name, String flavorText, int bloodCost, int power, int health, int buffPower, int buffHealth) {

	}
	
	@Override
	public String toString() {
		return super.getName() + " (POW: " + super.getPower() + ", HP: " + super.getHealth() + 
				" | POW Inc: "+ this.getBuffPower() + ", HP Inc: " + this.getBuffHealth() + ")";
	}

}