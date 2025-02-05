package logic.game;

import java.util.ArrayList;

import logic.unit.BaseUnit;
import logic.unit.FlyingUnit;
import logic.unit.MeleeUnit;
import logic.unit.RangeUnit;

public class GameSystem {
	
	private ArrayList<BaseUnit> allWhitePieces;
	private ArrayList<BaseUnit> allRedPieces;
	private boolean gameEnd;

	private static GameSystem instance = null;
	
	public static GameSystem getInstance() {
		if(instance == null) {
			instance = new GameSystem();
		}
		return instance;
	}
	
	private GameSystem() {
		
		allWhitePieces  = new ArrayList<BaseUnit>();
		allRedPieces = new ArrayList<BaseUnit>();
		
		BaseUnit white1 = new BaseUnit(0, 0, true,"w1");
		BaseUnit white2 = new BaseUnit(1, 0, true,"w2");
		BaseUnit white3 = new BaseUnit(2, 0, true,"w3");
		BaseUnit white4 = new BaseUnit(3, 0, true,"w4");
		BaseUnit white5 = new BaseUnit(4, 0, true,"w5");
		
		BaseUnit red1 = new BaseUnit(0, 4, false,"r1");
		BaseUnit red2 = new BaseUnit(1, 4, false,"r2");
		BaseUnit red3 = new BaseUnit(2, 4, false,"r3");
		BaseUnit red4 = new BaseUnit(3, 4, false,"r4");
		BaseUnit red5 = new BaseUnit(4, 4, false,"r5");
		
		//add all pieces to check pieces that on the board
		allWhitePieces.add(white1);
		allWhitePieces.add(white2);
		allWhitePieces.add(white3);
		allWhitePieces.add(white4);
		allWhitePieces.add(white5);
		
		allRedPieces.add(red1);
		allRedPieces.add(red2);
		allRedPieces.add(red3);
		allRedPieces.add(red4);
		allRedPieces.add(red5);

		setGameEnd(false);
	}
	
	public boolean isGameEnd() {
		if(allRedPieces.size()<=2 || allWhitePieces.size()<=2) setGameEnd(true);
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
	}
	
	public ArrayList<BaseUnit> getAllWhitePieces() {
		return allWhitePieces;
	}

	public ArrayList<BaseUnit> getAllRedPieces() {
		return allRedPieces;
	}
	

	public void printBoardStatus(ArrayList<BaseUnit> allPieces) {
		for(int i = 0;i<allPieces.size();i++) {
			BaseUnit temp = allPieces.get(i);
			System.out.println(temp.getClass().getSimpleName() + " " + temp.getName()+ ": ("+ temp.getColumn()+", "+ temp.getRow()+")" + " hp: " + temp.getHp());
		}
		
	}
	
	public void removeDeadPieces(ArrayList<BaseUnit> allPieces) {
		for(int j = allPieces.size()-1;j >= 0;j--) {
			if(allPieces.get(j).getHp() <= 0) {
				allPieces.remove(j);
			}
		}
		
	}

}
