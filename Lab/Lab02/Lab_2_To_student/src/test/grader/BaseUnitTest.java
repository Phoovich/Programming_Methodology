package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.unit.BaseUnit;
import logic.unit.FlyingUnit;

class BaseUnitTest {

	BaseUnit larva1;
	BaseUnit larva2;
	BaseUnit larva3;
	BaseUnit larva4;
	BaseUnit larva5;  
	
	@BeforeEach
	void setUp() throws Exception {
		larva1 = new BaseUnit(2,1,true,"lv1");
		larva2 = new BaseUnit(1,2,false,"lv2");
		larva3 = new BaseUnit(-1,-1,true,"lv3");
		larva4 = new BaseUnit(5,5,true,"lv4");
		larva5 = new BaseUnit(5,5,true,"lv5");
	}

	@Test
	void testConstructor() {
		assertEquals(2, larva1.getColumn());
		assertEquals(1, larva1.getRow());
		assertEquals(true, larva1.isWhite());
		assertEquals("lv1", larva1.getName());
		assertEquals(2, larva1.getHp());
		assertEquals(1, larva1.getPower());
		assertEquals(false, larva1.isFlying());
	
		assertEquals(1, larva2.getColumn());
		assertEquals(2, larva2.getRow());
		assertEquals(false, larva2.isWhite());
		assertEquals("lv2", larva2.getName());
		assertEquals(2, larva2.getHp());
		assertEquals(1, larva1.getPower());
		assertEquals(false, larva1.isFlying());
		
		assertEquals(0, larva3.getColumn());
		assertEquals(0, larva3.getRow());
		
		assertEquals(4, larva4.getColumn());
		assertEquals(4, larva4.getRow());
	}

	@Test
	void testMoveLegal() {
		//direction 0
		assertTrue(larva1.move(0));    //Must have assertTrue !!!!!!!!!!!!!!!
		assertEquals(2, larva1.getColumn());
		assertEquals(2, larva1.getRow());
	
		//direction 1
		assertTrue(larva1.move(1));
		assertEquals(3, larva1.getColumn());
		assertEquals(2, larva1.getRow());
		
		//direction 2
		assertTrue(larva1.move(2));
		assertEquals(3, larva1.getColumn());
		assertEquals(1, larva1.getRow());
		
		//direction 3
		assertTrue(larva1.move(3));
		assertEquals(2, larva1.getColumn());
		assertEquals(1, larva1.getRow());
	}
	
	@Test
	void testMoveIllegal() {  //new case
		larva1.setColumn(0);
		larva1.setRow(1);
		assertFalse(larva1.move(3));
		
		larva1.setColumn(1);
		larva1.setRow(0);
		assertFalse(larva1.move(2));
		
		larva1.setColumn(3);
		larva1.setRow(4);
		assertFalse(larva1.move(0));
		
		larva1.setColumn(4);
		larva1.setRow(2);
		assertFalse(larva1.move(1));
		assertFalse(larva1.move(4));
		
	}
	
	@Test
	void testAttack() {  // I add more detail! 
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(larva2);
		targetList.add(larva3);
		targetList.add(larva4);
		targetList.add(larva5);
		
		larva1.setColumn(1);
		larva1.setRow(2);
		larva4.setColumn(1);
		larva4.setRow(2);
		
		larva1.attack(targetList);
		assertEquals(1,larva2.getHp());
		assertEquals(2,larva3.getHp());
		assertEquals(1,larva4.getHp());
		assertEquals(2,larva5.getHp());
		
	}
	
	@Test //comment this method out if FlyingUnit is not finished.
	void testAttackFlying() { 
		FlyingUnit mul1 = new FlyingUnit(1,2,false,"mul1");
		FlyingUnit mul2 = new FlyingUnit(1,2,false,"mul2");
		
		larva1.setColumn(1);
		larva1.setRow(2);
		ArrayList<BaseUnit> targetList = new ArrayList<BaseUnit>();
		targetList.add(mul1);
		targetList.add(larva2);
		targetList.add(mul2);
		
		
		larva1.attack(targetList);
		
		assertEquals(1,larva2.getHp());
		
		assertEquals(2,mul1.getHp());
		assertEquals(2,mul2.getHp());
		
	}
	
	@Test
	void testSetter() {
		larva1.setRow(-1);
		assertEquals(0, larva1.getRow());
		larva1.setRow(6);
		assertEquals(4, larva1.getRow());
		
		larva1.setColumn(-1);
		assertEquals(0, larva1.getColumn());
		larva1.setColumn(6);
		assertEquals(4, larva1.getColumn());
		
	}
}
