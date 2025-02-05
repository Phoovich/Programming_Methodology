package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.unit.BaseUnit;
import logic.unit.FlyingUnit;
import logic.unit.RangeUnit;

class RangeUnitTest {

	RangeUnit hydra1;
	BaseUnit hydra2;
	RangeUnit hydra3;
	RangeUnit hydra4;
	
	@BeforeEach
	void setUp() throws Exception {
		hydra1 = new RangeUnit(2, 1, true, "hydra1");
		hydra2 = new RangeUnit(1, 2, false, "hydra2");
		hydra3 = new RangeUnit(-1, -1, false, "hydra3");
		hydra4 = new RangeUnit(5, 5, false, "hydra4");
	}

	@Test
	void testConstructor() {
		assertEquals(2, hydra1.getColumn());
		assertEquals(1, hydra1.getRow());
		assertEquals(true, hydra1.isWhite());
		assertEquals("hydra1", hydra1.getName());
		assertEquals(2, hydra1.getHp());
		assertEquals(1, hydra1.getPower());
		assertEquals(false, hydra1.isFlying());
		
		assertEquals(0, hydra3.getColumn());
		assertEquals(0, hydra3.getRow());
		
		assertEquals(4, hydra4.getColumn());
		assertEquals(4, hydra4.getRow());

	}

	@Test
	void testParentChild() {
		assertEquals(1, hydra2.getColumn());
		assertEquals(2, hydra2.getRow());
		assertEquals(false, hydra2.isWhite());
		assertEquals("hydra2", hydra2.getName());
		assertEquals(2, hydra2.getHp());
		assertEquals(1, hydra2.getPower());
		assertEquals(false, hydra2.isFlying());

		assertEquals("RangeUnit", hydra2.getClass().getSimpleName());
	}


	@Test
	void testMove() {
		// direction 0
		assertTrue(hydra1.move(0));
		assertEquals(2, hydra1.getColumn());
		assertEquals(2, hydra1.getRow());

		// direction 1
		assertTrue(hydra1.move(1));
		assertEquals(3, hydra1.getColumn());
		assertEquals(2, hydra1.getRow());

		// direction 2
		assertTrue(hydra1.move(2));
		assertEquals(3, hydra1.getColumn());
		assertEquals(1, hydra1.getRow());

		// direction 3
		assertTrue(hydra1.move(3));
		assertEquals(2, hydra1.getColumn());
		assertEquals(1, hydra1.getRow());
	}
	
	@Test
	void testMoveIllegal() {  //new case
		hydra1.setColumn(0);
		hydra1.setRow(1);
		assertFalse(hydra1.move(3));
		
		hydra1.setColumn(1);
		hydra1.setRow(0);
		assertFalse(hydra1.move(2));
		
		hydra1.setColumn(3);
		hydra1.setRow(4);
		assertFalse(hydra1.move(0));
		
		hydra1.setColumn(4);
		hydra1.setRow(2);
		assertFalse(hydra1.move(1));
		assertFalse(hydra1.move(4));
		
	}
	
	@Test
	void testAttackWhite() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(hydra2);
		targetList.add(hydra3);
		targetList.add(hydra4);
		
		hydra4.setColumn(1);
		hydra4.setRow(2);
		hydra1.setColumn(1);
		hydra1.setRow(1);
		hydra1.attack(targetList);
		
		assertEquals(1,hydra2.getHp());
		assertEquals(2,hydra3.getHp());
		assertEquals(1,hydra4.getHp());
	}
	
	@Test
	void testAttackRed() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(hydra1);
		targetList.add(hydra3);
		targetList.add(hydra4);
		
		hydra4.setColumn(2);
		hydra4.setRow(1);
		hydra2.setColumn(2);
		hydra2.setRow(2);
		hydra2.attack(targetList);
		
		assertEquals(1,hydra1.getHp());
		assertEquals(2,hydra3.getHp());
		assertEquals(1,hydra4.getHp());
	}
	
	@Test //comment this method out if FlyingUnit is not finished
	void testAttackFlying() {
		FlyingUnit mul1 = new FlyingUnit(2,2,false,"mul1");
		FlyingUnit mul2 = new FlyingUnit(2,1,false,"mul2");
		
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(mul1);
		targetList.add(mul2);
		targetList.add(hydra3);
		targetList.add(hydra2);
		
		hydra2.setColumn(2);
		hydra2.setRow(2);
		
		hydra1.attack(targetList);
		
		assertEquals(1,mul1.getHp());
		assertEquals(2,mul2.getHp());
		assertEquals(1,hydra2.getHp());
		assertEquals(2,hydra3.getHp());
		
	}
}
