package test.grader;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logic.game.GameSystem;
import logic.unit.BaseUnit;

class GameSystemTest {
	
	BaseUnit w1;
	BaseUnit r1;
	
	@BeforeEach
	void setUp() throws Exception {
		 w1 = new BaseUnit(0,0,true,"w1");
		 r1 = new BaseUnit(4,4,false,"r1");
	}

	@Test
	void testPromoteMeleeUnit() {
		BaseUnit melee = GameSystem.getInstance().promote(w1, 0);
		
		assertEquals("w1", melee.getName());
		assertEquals(0, melee.getColumn());
		assertEquals(0, melee.getRow());
		assertTrue(melee.isWhite());
		assertFalse(melee.isFlying());
		assertEquals(5,melee.getHp());
		assertEquals(2,melee.getPower());
		assertEquals("MeleeUnit",melee.getClass().getSimpleName());
		
		BaseUnit melee2 = GameSystem.getInstance().promote(r1, 0);
		
		assertEquals("r1", melee2.getName());
		assertEquals(4, melee2.getColumn());
		assertEquals(4, melee2.getRow());
		assertFalse(melee2.isWhite());
		assertFalse(melee2.isFlying());
		assertEquals(5,melee2.getHp());
		assertEquals(2,melee2.getPower());
		assertEquals("MeleeUnit",melee2.getClass().getSimpleName());
	}
	
	@Test
	void testPromoteRangeUnit() {
		BaseUnit range = GameSystem.getInstance().promote(w1, 1);
		
		assertEquals("w1", range.getName());
		assertEquals(0, range.getColumn());
		assertEquals(0, range.getRow());
		assertTrue(range.isWhite());
		assertFalse(range.isFlying());
		assertEquals(2,range.getHp());
		assertEquals(1,range.getPower());
		assertEquals("RangeUnit", range.getClass().getSimpleName());
		
		BaseUnit range2 = GameSystem.getInstance().promote(r1, 1);
		
		assertEquals("r1", range2.getName());
		assertEquals(4, range2.getColumn());
		assertEquals(4, range2.getRow());
		assertFalse(range2.isWhite());
		assertFalse(range2.isFlying());
		assertEquals(2,range2.getHp());
		assertEquals(1,range2.getPower());
		assertEquals("RangeUnit", range2.getClass().getSimpleName());
	}
	
	@Test
	void testPromoteFlyingUnit() {
		BaseUnit fly = GameSystem.getInstance().promote(w1, 2);
		
		assertEquals("w1", fly.getName());
		assertEquals(0, fly.getColumn());
		assertEquals(0, fly.getRow());
		assertTrue(fly.isWhite());
		assertTrue(fly.isFlying());
		assertEquals(2, fly.getHp());
		assertEquals(1, fly.getPower());
		assertEquals("FlyingUnit", fly.getClass().getSimpleName());
		
		BaseUnit fly2 = GameSystem.getInstance().promote(r1, 2);
		
		assertEquals("r1", fly2.getName());
		assertEquals(4, fly2.getColumn());
		assertEquals(4, fly2.getRow());
		assertFalse(fly2.isWhite());
		assertTrue(fly2.isFlying());
		assertEquals(2, fly2.getHp());
		assertEquals(1, fly2.getPower());
		assertEquals("FlyingUnit", fly2.getClass().getSimpleName());
	}
}


