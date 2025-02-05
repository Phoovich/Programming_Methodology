package card.base;

//You CAN modify the first line
public class  Card implements Cloneable {	

	/// You can modify code below ///
	public Card(String name, String flavorText, int bloodCost) {	
	}
		
	public String toString();
	
	public String getName() {

	}

	public int getBloodCost() {

	}

	public String getFlavorText() {

	}
	
	/// You can modify code above ///
	
	public boolean equals(UnitCard other) {
		return this.getName().equals(other.getName());
	}
	public Object clone()throws CloneNotSupportedException{  
		return super.clone();  
		}  

	
}