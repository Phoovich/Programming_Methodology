package application;

import java.util.ArrayList;
import java.util.Scanner;

import logic.game.GameSystem;
import logic.unit.BaseUnit;

public class Main {

	private static boolean whiteTurn = true;
	private static Scanner sc;
	
	public static void main(String args[]) {
		
		System.out.println("Welcome to zerg chess!");
		sc = new Scanner(System.in);
		
		while(!GameSystem.getInstance().isGameEnd()) {
			System.out.println("White pieces:");
			GameSystem.getInstance().printBoardStatus(GameSystem.getInstance().getAllWhitePieces());
			System.out.println("Red pieces:");
			GameSystem.getInstance().printBoardStatus(GameSystem.getInstance().getAllRedPieces());
			if(whiteTurn) {
				
				
				System.out.println("This is white turn.");
				System.out.println("Avaiable Pieces:");
				
				turnFlow(GameSystem.getInstance().getAllWhitePieces());
			}
			else if(!whiteTurn) {
				System.out.println("This is red turn.");
				System.out.println("Avaiable Pieces:");
				
				turnFlow(GameSystem.getInstance().getAllRedPieces());
			}
			GameSystem.getInstance().removeDeadPieces(GameSystem.getInstance().getAllRedPieces());
			GameSystem.getInstance().removeDeadPieces(GameSystem.getInstance().getAllWhitePieces());
			whiteTurn = !whiteTurn;
		}
		System.out.println("Game end.");
		if(GameSystem.getInstance().getAllRedPieces().size() <=2 && GameSystem.getInstance().getAllWhitePieces().size() <=2) {
			System.out.println("Draw!");
			
		}
		else if(GameSystem.getInstance().getAllWhitePieces().size() <=2) {
			System.out.println("Red win!");
		}
		else {
			System.out.println("White win!");
		}
	}
	
	private static void turnFlow(ArrayList<BaseUnit> allPieces) {
		
		for(int i = 0;i < allPieces.size();i++) {
			System.out.println("<"+i+"> "+ allPieces.get(i).getName());
		}
		int pieceNum = sc.nextInt();
		System.out.println("Please enter direction");
		int direction = sc.nextInt();
		while(!allPieces.get(pieceNum).move(direction)) {
			System.out.println("direction invalid");
			direction = sc.nextInt();
		}
		if(whiteTurn) {
			allPieces.get(pieceNum).attack(GameSystem.getInstance().getAllRedPieces());
		}
		else {
			allPieces.get(pieceNum).attack(GameSystem.getInstance().getAllWhitePieces());
		}
		
		if(allPieces.get(pieceNum).getRow() == 2 && allPieces.get(pieceNum).getClass().getSimpleName().equals("BaseUnit")) {
			System.out.println("Your unit got promoted!");
			System.out.println("Selecting promoting unit");
			System.out.println("<0> MeleeUnit");
			System.out.println("<1> RangeUnit");
			System.out.println("<2> FlyingUnit");
			int choice = sc.nextInt();
			while((choice>=3)&&(choice<0)) {
				System.out.println("Please enter 0-2 number");
				choice = sc.nextInt();
			}
			allPieces.add(GameSystem.getInstance().promote(allPieces.get(pieceNum),choice));
			allPieces.remove(pieceNum);
		}
	}
	
}
