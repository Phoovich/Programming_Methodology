package card.base;

//You CAN modify the first line
public class SpellCard extends Card {


	public SpellCard(String name, String flavorText, int bloodCost, boolean isBurstSpeed) {

	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getName() + "\tCost: " + this.getBloodCost() +
				"\t(" + (this.getFlavorText()) + ")";
	}

}
