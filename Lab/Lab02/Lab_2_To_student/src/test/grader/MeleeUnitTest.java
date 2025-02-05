package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.unit.BaseUnit;
import logic.unit.MeleeUnit;

class MeleeUnitTest {

	MeleeUnit ultra1;
	BaseUnit ultra2;
	MeleeUnit ultra3;
	MeleeUnit ultra4;
	

	@BeforeEach
	void setUp() throws Exception {
		ultra1 = new MeleeUnit(2, 1, true, "ul1");
		ultra2 = new MeleeUnit(1, 2, false, "ul2");
		ultra3 = new MeleeUnit(-1, -1, false, "ul3");
		ultra4 = new MeleeUnit(5, 5, false, "ul4");
	}

	@Test
	void testConstructor() {
		assertEquals(2, ultra1.getColumn());
		assertEquals(1, ultra1.getRow());
		assertEquals(true, ultra1.isWhite());
		assertEquals("ul1", ultra1.getName());
		assertEquals(5, ultra1.getHp());
		assertEquals(2, ultra1.getPower());
		assertEquals(false, ultra1.isFlying());

		assertEquals(0, ultra3.getColumn());
		assertEquals(0, ultra3.getRow());
		
		assertEquals(4, ultra4.getColumn());
		assertEquals(4, ultra4.getRow());
	}

	@Test
	void testParentChild() {
		assertEquals(1, ultra2.getColumn());
		assertEquals(2, ultra2.getRow());
		assertEquals(false, ultra2.isWhite());
		assertEquals("ul2", ultra2.getName());
		assertEquals(5, ultra2.getHp());
		assertEquals(2, ultra2.getPower());
		assertEquals(false, ultra2.isFlying());
		assertEquals("MeleeUnit",ultra2.getClass().getSimpleName()); //correction from ultra1
	}

	@Test
	void testMoveLegal() {
		// direction 0
		assertTrue(ultra1.move(0));
		assertEquals(2, ultra1.getColumn());
		assertEquals(2, ultra1.getRow());

		// direction 1
		assertTrue(ultra1.move(1));
		assertEquals(3, ultra1.getColumn());
		assertEquals(2, ultra1.getRow());

		// direction 2
		assertTrue(ultra1.move(2));
		assertEquals(3, ultra1.getColumn());
		assertEquals(1, ultra1.getRow());

		// direction 3
		assertTrue(ultra1.move(3));
		assertEquals(2, ultra1.getColumn());
		assertEquals(1, ultra1.getRow());
	}
	
	@Test
	void testMoveIllegal() {  //new case
		ultra1.setColumn(0);
		ultra1.setRow(1);
		assertFalse(ultra1.move(3));
		
		ultra1.setColumn(1);
		ultra1.setRow(0);
		assertFalse(ultra1.move(2));
		
		ultra1.setColumn(3);
		ultra1.setRow(4);
		assertFalse(ultra1.move(0));
		
		ultra1.setColumn(4);
		ultra1.setRow(2);
		assertFalse(ultra1.move(1));
		assertFalse(ultra1.move(4));
		
	}
	
	
	@Test
	void testAttack() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(ultra2);
		targetList.add(ultra3);
		targetList.add(ultra4);
		
		ultra1.setColumn(1);
		ultra1.setRow(2);
		ultra4.setColumn(1);
		ultra4.setRow(2);
		
		ultra1.attack(targetList);
		assertEquals(3,ultra2.getHp());
		assertEquals(5,ultra3.getHp());
		assertEquals(3,ultra4.getHp());
	}

//	@Test
//	void testAttackFlying() { 
//		FlyingUnit mul1 = new FlyingUnit(1,2,false,"mul1");
//		FlyingUnit mul2 = new FlyingUnit(1,2,false,"mul2");
//		
//		ultra1.setColumn(1);
//		ultra1.setRow(2);
//		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
//		targetList.add(mul1);
//		targetList.add(ultra2);
//		targetList.add(mul2);
//		
//		
//		ultra1.attack(targetList);
//		
//		assertEquals(3,ultra2.getHp());
//		
//		assertEquals(2,mul1.getHp());
//		assertEquals(2,mul2.getHp());
//		
//	}
//	
}
