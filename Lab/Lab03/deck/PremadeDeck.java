package deck;

import java.util.ArrayList;
import java.util.List;

import card.base.Card;
import card.base.UnitCard;
import card.type.*;

public class PremadeDeck {

	//Leader
	static LeaderUnitCard darius = new LeaderUnitCard("Darius", "For Noxas!!", 2, 6, 5,2,0);
	static LeaderUnitCard ezreal = new LeaderUnitCard("Ezreal", "Who needs a map!!", 1, 2, 3,1,0);
	static LeaderUnitCard braum = new LeaderUnitCard("Braum", "Take Heart", 1, 1, 5,0,1);
	
	//0 cost
	static NormalUnitCard cat = new NormalUnitCard("Cat", "Meow", 0, 1, 1);
	static NormalUnitCard dog = new NormalUnitCard("Dog", "Woof", 0, 1, 1);
	static NormalUnitCard bird = new NormalUnitCard("Bird", "Chip", 0, 1, 1);
	static NormalUnitCard log = new NormalUnitCard("Log", "I'm a wood", 0, 0, 2);
	static VenomUnitCard poisonFlower = new VenomUnitCard("Poison Flower", "I harm you", 0, 0, 3);
	static BuffSpellCard potion = new BuffSpellCard("Potion", "+1 power/Slow", 0,false, 1);
	static DamageSpellCard arrow = new DamageSpellCard("Arrow", "Deal 1/Slow", 0,false, 1);
	
	//1 cost
	static NormalUnitCard fighter = new NormalUnitCard("Solider", "Generic unit", 1, 1, 3);
	
	static NormalUnitCard blackRose = new NormalUnitCard("Black Rose", "Noxas unit", 1, 3, 2);
	static NormalUnitCard solider = new NormalUnitCard("Solider", "Noxas unit", 1, 2, 3);
	static DebuffUnitCard iceWitch = new DebuffUnitCard("Ice Witch", "Freljord unit", 1, 1, 3,1);
	static DebuffUnitCard iceWolf = new DebuffUnitCard("Ice Wolf", "Freljord unit", 1, 1, 2,2);
	static VenomUnitCard madDoc = new VenomUnitCard("Mad Doctor", "PnZ unit", 2, 1, 1);
	static BuffSpellCard hiPotion = new BuffSpellCard("Hi Potion", "+3 power/Slow", 1,false, 3);
	static BuffSpellCard fastPotion = new BuffSpellCard("Fast Potion", "+2 power/Burst", 1,true, 2);
	static DamageSpellCard superArrow = new DamageSpellCard("Super Arrow", "Deal 3/Slow", 1,false, 3);
	static DamageSpellCard gun = new DamageSpellCard("Gun", "Deal 2/Burst", 1,true, 2);
	
	//2 cost
	static NormalUnitCard minotaur = new NormalUnitCard("Minotaur", "Noxas unit", 2, 4, 3);
	static NormalUnitCard warrior = new NormalUnitCard("Warrior", "Noxas unit", 2, 3, 4);
	static NormalUnitCard guardian = new NormalUnitCard("Guardian", "Freljord unit", 2, 1, 6);
	static DebuffUnitCard iceWizard = new DebuffUnitCard("Ice Wizard", "Freljord unit", 2, 1, 4,2);
	static DebuffUnitCard iceBear = new DebuffUnitCard("Ice Wolf", "Freljord unit", 1, 2, 4,1);
	static VenomUnitCard experi = new VenomUnitCard("Experiment", "PnZ unit", 2, 3, 2);
	static BuffSpellCard megaPotion = new BuffSpellCard("Mega Potion", "+4 power/Burst", 1,true, 4);
	static DamageSpellCard cannon = new DamageSpellCard("Cannon", "Deal 4/Burst", 2,true, 4);
	
	public static List<Card> initialCardStock = new ArrayList<Card>(List.of(
			darius,
			ezreal,
			braum,
			cat,
			dog,
			bird,
			log,
			poisonFlower,
			potion,
			arrow,
			fighter,
			blackRose,
			solider,
			iceWitch,
			iceWolf,
			madDoc,
			hiPotion,
			fastPotion,
			superArrow,
			gun,
			minotaur,
			warrior,
			guardian,
			iceWizard,
			iceBear,
			experi,
			megaPotion,
			cannon
			));
	
	
	
	
	static Card[] defaultD = { 
			cat,cat,cat,cat
			,dog,dog,dog,dog
			,bird,bird,bird,bird
			,log,log,log,log
			,potion,potion
			,arrow,arrow
			
			,fighter,fighter,fighter,fighter
			,solider,solider,solider
			,iceWolf,iceWolf,iceWolf
			,madDoc,madDoc,madDoc
			,fastPotion,fastPotion

			,guardian,guardian
			,minotaur
			,iceWizard
			,experi
};
	
	static Card[] noxasD= { 			
			darius,darius,darius,darius
			
			,cat,cat,cat,cat
			,dog,dog,dog,dog
			,bird,bird,bird,bird
			,log,log
			,potion
			,arrow,arrow,arrow,arrow
			
			,fighter,fighter,fighter,fighter
			,solider,solider,solider,solider
			,blackRose,blackRose,blackRose,blackRose
			,superArrow,superArrow,superArrow
			,fastPotion

			,guardian
			,minotaur,minotaur
			,warrior,warrior
			,cannon		
	};
	static Card[] PnZD = { 			
			ezreal,ezreal,ezreal,ezreal
			
			,cat,cat,cat,cat
			,dog,dog,dog,dog
			,bird,bird,bird,bird
			,log,log,log,log
			,potion,potion,potion,potion
			,arrow,arrow,arrow,arrow
			
			,fighter,fighter
			,solider,solider
			,madDoc,madDoc,madDoc
			,superArrow,superArrow,superArrow
			,gun,gun,gun,gun
			,fastPotion,fastPotion,fastPotion
			,hiPotion,hiPotion

			,experi,experi
			,cannon,cannon,cannon
			,megaPotion
	};
	static Card[] freljordD = { 			
			braum,braum,braum,braum
			
			,cat,cat,cat,cat
			,dog,dog,dog,dog
			,bird,bird
			,log,log,log,log
			,potion,potion,potion,potion
			,arrow
			
			,fighter,fighter,fighter,fighter
			,iceWitch,iceWitch,iceWitch,iceWitch
			,iceWolf,iceWolf,iceWolf,iceWolf
			,superArrow
			,fastPotion,fastPotion,fastPotion

			,guardian
			,iceWizard,iceWizard
			,iceBear,iceBear
			,megaPotion		
	};
	static Deck defaultDeck = new Deck("Default Deck" , defaultD);
	static Deck noxasDeck = new Deck("Noxas Deck" , noxasD);
	static Deck arcaneDeck = new Deck("Arcane Deck" , PnZD);
	static Deck freljordDeck = new Deck("Freljord Deck" , freljordD);
	
	static NormalUnitCard cat1 = new NormalUnitCard("Cat", "Meow", 0, 1, 1);
	static NormalUnitCard cat2 = new NormalUnitCard("Cat", "Meow", 0, 1, 1);
	static NormalUnitCard log1 = new NormalUnitCard("Log", "I'm a wood", 0, 0, 2);
	
	
	static UnitCard voidField[] = {null,null,null,null};
	static UnitCard catField[] = {cat,null,cat,null};
	static UnitCard treeField[] = {log,log,log,log};
	static UnitCard forestField[] = {cat,log,log,dog};
	static UnitCard noxasField[] = {solider,null,null,dog};
	static UnitCard freljordField[] = {iceWitch,iceWitch,null,log};
	static UnitCard arcaneField[] = {madDoc,dog,log,log};
	
	public static Deck[] premadeDecks = {
			defaultDeck,
			noxasDeck,
			arcaneDeck,
			freljordDeck
	};
	
	public static UnitCard[][] premadeField = {
			voidField,
			catField,
			treeField,
			forestField,
			noxasField,
			freljordField,
			arcaneField
	};
}
