package test.student;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.base.UnitCard;
import card.type.NormalUnitCard;

public class TestNormalUnitCard {
	NormalUnitCard normalUnit1;
	UnitCard normalUnit2;
	NormalUnitCard normalUnit3;
	NormalUnitCard negativeTestUnit;
	NormalUnitCard oppoUnit1;

	
	@BeforeEach
	void setUp() {
		normalUnit1 = new NormalUnitCard("Normal1" , "He's normal" , 0 , 1 , 2 );
		normalUnit2 = new NormalUnitCard("Normal2" , "He's really normal" , 1 , 2 , 3 );
		normalUnit3 = new NormalUnitCard("Normal3" , "He's super normal" , 2 , 3 , 4 );
		negativeTestUnit = new NormalUnitCard("negativeTestUnit" , "I'm negative, or...." , -2 , -1 , -3 );
		oppoUnit1 = new NormalUnitCard("Opponent", "I'm an opponent", 0 , 1, 10);

	}
	
	@Test
	void testConstructor() {
		
		assertEquals("Normal1", normalUnit1.getName());
		assertEquals("He's normal", normalUnit1.getFlavorText());
		assertEquals(0, normalUnit1.getBloodCost());
		assertEquals(1, normalUnit1.getPower());
		assertEquals(2, normalUnit1.getHealth());
	}
	
	@Test
	void testNonPositiveConstructor() {
		
		assertEquals("negativeTestUnit", negativeTestUnit.getName());
		assertEquals("I'm negative, or....", negativeTestUnit.getFlavorText());
		assertEquals(0, negativeTestUnit.getBloodCost());
		assertEquals(0, negativeTestUnit.getPower());
		assertEquals(0, negativeTestUnit.getHealth());
	}
	
	@Test
	void testAttack() {
		
		assertEquals(1, normalUnit1.attackUnit(oppoUnit1));
		assertEquals(9, oppoUnit1.getHealth());
		assertEquals(2, normalUnit2.attackUnit(oppoUnit1));
		assertEquals(7, oppoUnit1.getHealth());
		assertEquals(3, normalUnit3.attackUnit(oppoUnit1));
		assertEquals(4, oppoUnit1.getHealth());
		assertEquals(3, normalUnit3.attackUnit(oppoUnit1));
		assertEquals(1, oppoUnit1.getHealth());
		assertEquals(1, normalUnit3.attackUnit(oppoUnit1));
		assertEquals(0, oppoUnit1.getHealth());
	}

}
