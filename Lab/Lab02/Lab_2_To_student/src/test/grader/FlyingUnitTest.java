package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.unit.BaseUnit;
import logic.unit.FlyingUnit;
import logic.unit.MeleeUnit;
import logic.unit.RangeUnit;

class FlyingUnitTest {

	FlyingUnit mul1;
	BaseUnit mul2;
	FlyingUnit mul3;
	FlyingUnit mul4;
	
	@BeforeEach
	void setUp() throws Exception {
		mul1 = new FlyingUnit(2, 1, true, "mul1");
		mul2 = new FlyingUnit(1, 2, false, "mul2");
		mul3 = new FlyingUnit(-1, -1, false, "mul2");
		mul4 = new FlyingUnit(5, 5, false, "mul2");
	}

	@Test
	void testConstructor() {
		assertEquals(2, mul1.getColumn());
		assertEquals(1, mul1.getRow());
		assertEquals(true, mul1.isWhite());
		assertEquals("mul1", mul1.getName());
		assertEquals(2, mul1.getHp());
		assertEquals(1, mul1.getPower());
		assertEquals(true, mul1.isFlying());
		
		assertEquals(0, mul3.getColumn());
		assertEquals(0, mul3.getRow());
		
		assertEquals(4, mul4.getColumn());
		assertEquals(4, mul4.getRow());

	}

	@Test
	void testParentChild() {
		assertEquals(1, mul2.getColumn());
		assertEquals(2, mul2.getRow());
		assertEquals(false, mul2.isWhite());
		assertEquals("mul2", mul2.getName());
		assertEquals(2, mul2.getHp());
		assertEquals(1, mul2.getPower());
		assertEquals(true, mul2.isFlying());

		assertEquals("FlyingUnit", mul2.getClass().getSimpleName());
	}


	@Test
	void testMove() {
		// direction 0
		assertTrue(mul1.move(0));
		assertEquals(2, mul1.getColumn());
		assertEquals(3, mul1.getRow());

		// direction 1
		assertTrue(mul1.move(1));
		assertEquals(4, mul1.getColumn());
		assertEquals(3, mul1.getRow());

		// direction 2
		assertTrue(mul1.move(2));
		assertEquals(4, mul1.getColumn());
		assertEquals(1, mul1.getRow());

		// direction 3
		assertTrue(mul1.move(3));
		assertEquals(2, mul1.getColumn());
		assertEquals(1, mul1.getRow());
	}
	
	@Test
	void testMoveIllegal() {  //new case
		
		mul1.setColumn(0);
		mul1.setRow(1);
		assertFalse(mul1.move(3));
		
		mul1.setColumn(1);
		mul1.setRow(1);
		assertFalse(mul1.move(3));
		
		mul1.setColumn(1);
		mul1.setRow(0);
		assertFalse(mul1.move(2));
		
		mul1.setColumn(1);
		mul1.setRow(1);
		assertFalse(mul1.move(2));
		
		mul1.setColumn(3);
		mul1.setRow(4);
		assertFalse(mul1.move(0));
		
		mul1.setColumn(3);
		mul1.setRow(3);
		assertFalse(mul1.move(0));
		
		mul1.setColumn(4);
		mul1.setRow(2);
		assertFalse(mul1.move(1));
		
		mul1.setColumn(3);
		mul1.setRow(2);
		assertFalse(mul1.move(1));
		
		assertFalse(mul1.move(4));
		
	}
	@Test
	void testAttack() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		BaseUnit larva1 = new BaseUnit(1, 2, false, "lv1");
		BaseUnit larva2 = new BaseUnit(1,2,false,"lv2");
		BaseUnit larva3 = new BaseUnit(2,1,true,"lv3");
		BaseUnit larva4 = new BaseUnit(2,3,true,"lv4");
		targetList.add(larva1);
		targetList.add(larva2);
		targetList.add(larva3);
		targetList.add(larva4);
		mul1.setColumn(1);
		mul1.setRow(2);
		mul1.attack(targetList);
		assertEquals(1,larva1.getHp());
		assertEquals(1,larva2.getHp());
		assertEquals(2,larva3.getHp());
		assertEquals(2,larva4.getHp());
	}
	
	@Test
	void testAttackFlying() { //add 1 more flying unit !!!!!!!!!!!!!!!!!!!!
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		BaseUnit larva1 = new BaseUnit(1, 2, false, "lv1");
		targetList.add(mul2);
		targetList.add(larva1);
		mul4.setColumn(1);
		mul4.setRow(2);
		targetList.add(mul4);
		
		mul1.setColumn(1);
		mul1.setRow(2);
		mul1.attack(targetList);
		assertEquals(2,mul2.getHp());
		assertEquals(1,larva1.getHp());
		assertEquals(2,mul4.getHp());
	}
	
	@Test
	void testAttackedByBaseUnit() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		BaseUnit larva1 = new BaseUnit(1, 2, false, "lv1");
		BaseUnit larva2 = new BaseUnit(1, 2, false, "lv2");
		mul1.setColumn(1);
		mul1.setRow(2);
		targetList.add(mul1);
		targetList.add(mul2);
		targetList.add(larva2);
		
		larva1.attack(targetList);
		
		assertEquals(1,larva2.getHp());
		
		assertEquals(2,mul1.getHp());
		assertEquals(2,mul2.getHp());
		
	}
	
	@Test
	void testAttackedByMeleeUnit() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		MeleeUnit ultra1 = new MeleeUnit(1, 2, false, "ul1");
		MeleeUnit ultra2 = new MeleeUnit(1, 2, false, "ul2");
		mul1.setColumn(1);
		mul1.setRow(2);
		targetList.add(mul1);
		targetList.add(mul2);
		targetList.add(ultra2);
		
		ultra1.attack(targetList);
		
		assertEquals(3,ultra2.getHp());
		
		assertEquals(2,mul1.getHp());
		assertEquals(2,mul2.getHp());
		
	}
	
	@Test
	void testAttackedByRangeUnit() {
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		RangeUnit hydra1 = new RangeUnit(1, 1, true, "hy1");
		RangeUnit hydra2 = new RangeUnit(1, 2, false, "hy2");
		RangeUnit hydra3 = new RangeUnit(3, 2, false, "hy3");
		
		targetList.add(mul1);
		targetList.add(mul2);
		targetList.add(hydra2);
		targetList.add(hydra3);
		
		hydra1.attack(targetList);
		
		assertEquals(2,mul1.getHp());
		assertEquals(1,mul2.getHp());
		assertEquals(1,hydra2.getHp());
		assertEquals(2,hydra3.getHp());
		
	}
	
	
	
	
}
