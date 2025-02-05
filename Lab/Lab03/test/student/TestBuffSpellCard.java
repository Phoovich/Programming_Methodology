package test.student;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import card.base.SpellCard;
import card.type.BuffSpellCard;
import card.type.NormalUnitCard;

public class TestBuffSpellCard {
	NormalUnitCard unit;

	SpellCard spell0 = new BuffSpellCard("Spell0", "+1", 0, false, 1);
	BuffSpellCard spell1 = new BuffSpellCard("Spell1", "+1", 0, false, 1);
	BuffSpellCard spell2 = new BuffSpellCard("Spell2", "+2", 1, false, 2);
	BuffSpellCard spell3 = new BuffSpellCard("Spell3", "+3", 0, true, 3);
	BuffSpellCard spell4 = new BuffSpellCard("Spell4", "+4", 0, true, 4);
	BuffSpellCard spellN = new BuffSpellCard("SpellN", "+", 0, true, 0);

	@BeforeEach
	void setUp() {
		unit = new NormalUnitCard("Unit", "I'm a unit", 0, 1, 1);
	}

	@Test
	void testConstructor() {

		assertEquals("Spell1", spell1.getName());
		assertEquals("+1", spell1.getFlavorText());
		assertEquals(0, spell1.getBloodCost());
		assertEquals(false, spell1.isBurstSpeed());
		assertEquals(1, spell1.getPowerIncrease());

	}

	@Test
	void testConstructorNegativeValue() {

		assertEquals("SpellN", spellN.getName());
		assertEquals("+", spellN.getFlavorText());
		assertEquals(0, spellN.getBloodCost());
		assertEquals(true, spellN.isBurstSpeed());
		assertEquals(1, spellN.getPowerIncrease());

	}

	@Test
	void testSetPowerIncrease() {
		spell4.setPowerIncrease(3);
		assertEquals(3, spell4.getPowerIncrease());

		spell4.setPowerIncrease(-3);
		assertEquals(1, spell4.getPowerIncrease());

	}

	@Test
	void testCastSpell() {

		spell1.castSpell(unit);
		assertEquals(2, unit.getPower());
		assertEquals(1, unit.getHealth());
		spell2.castSpell(unit);
		assertEquals(4, unit.getPower());
		assertEquals(1, unit.getHealth());
		spell3.castSpell(unit);
		assertEquals(7, unit.getPower());
		assertEquals(1, unit.getHealth());

	}
}
