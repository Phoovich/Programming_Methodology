package test.student;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.base.UnitCard;
import card.type.VenomUnitCard;
import player.Player;

public class TestVenomUnitCard {
	VenomUnitCard venomUnit1;
	VenomUnitCard venomUnit2;
	VenomUnitCard venomUnit3;
	VenomUnitCard venomUnit4;
	UnitCard oppoUnit1;
	Player player;
	
	
	@BeforeEach
	void setUp() {
		venomUnit1 = new VenomUnitCard("Venom1" , "C2N14" , 1 , 2 , 1);
		venomUnit2 = new VenomUnitCard("Venom2" , "ClF3" , 2 , 4, 3);
		venomUnit3 = new VenomUnitCard("Venom3" , "C2H6Cd" , 4 , 1 , 5 );
		venomUnit4 = new VenomUnitCard("Venom4" , "XXXX" , -1 , -11 , -5 );
		oppoUnit1 = new VenomUnitCard("Opponent", "I'm an opponent", 0 , 1, 100);
		player = new Player("Player");
	}
	
	@Test
	void testConstructor() {
		
		assertEquals("Venom1", venomUnit1.getName());
		assertEquals("C2N14", venomUnit1.getFlavorText());
		assertEquals(1, venomUnit1.getBloodCost());
		assertEquals(2, venomUnit1.getPower());
		assertEquals(1, venomUnit1.getHealth());
	}
	
	@Test
	void testConstructorNegativeValue() {
		
		assertEquals("Venom4", venomUnit4.getName());
		assertEquals("XXXX", venomUnit4.getFlavorText());
		assertEquals(0, venomUnit4.getBloodCost());
		assertEquals(0, venomUnit4.getPower());
		assertEquals(0, venomUnit4.getHealth());
	}
	
	
	@Test
	void testDead() {
		
		assertEquals(2, venomUnit1.dead(player));
		assertEquals(2, player.getCurrentDamagePoint());
		assertEquals(4, venomUnit2.dead(player));
		assertEquals(6, player.getCurrentDamagePoint());
		assertEquals(1, venomUnit3.dead(player));
		assertEquals(7, player.getCurrentDamagePoint());
	}
	
	//Fill Code Here!!!
	// 1.testAttack (Must test with all 3 venomUnit on oppoUnit1 with higher than 0 health, you can use .setHealth(int))
}
