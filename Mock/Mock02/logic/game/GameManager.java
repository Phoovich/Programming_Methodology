package logic.game;

import logic.stone.Dynamite;
import logic.stone.Stone;
import logic.stone.WalkingStone;
import render.MapManager;

public class GameManager {

    private static GameManager instance;
    private int digPower;
    private int gameScore;
    private int batteryLeft;
    private boolean isGameEnded;

    public GameManager() {
        digPower = 1;
        gameScore = 0;
        batteryLeft = 20;
        isGameEnded = false;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public static GameManager resetInstance() {
        instance = new GameManager();
        return instance;
    }

    public void doNextStep(char action) {
        MapManager mapManager = MapManager.getInstance();
        int posX = mapManager.getPlayerX();
        int posY = mapManager.getPlayerY();
        posX += switch (action) {
            case 'A', 'a' -> -1;
            case 'D', 'd' -> 1;
            default -> 0;
        };
        posY += switch (action) {
            case 'W', 'w' -> -1;
            case 'S', 's' -> 1;
            default -> 0;
        };
        doAction(posX, posY);


        for (WalkingStone walkingStone : MapManager.getInstance().getGameMap().getWalkingRocks()) {
            walkingStone.walk();
        }
    }

    private void doAction(int x, int y) {
        MapManager mapManager = MapManager.getInstance();
        if (mapManager.checkEmptyTile(x, y)) {
            mapManager.movePlayer(x, y);
        } else if (mapManager.checkIsStone(x, y)) {
            digStone(mapManager.getStoneAt(x, y));
        }
    }

   // TODO implement this method
    public void digStone(Stone stone) {
        // TODO implement this method


        
    }

    public void endGame() {
        isGameEnded = true;
    }

    public void useBattery(int amount) {
        if (amount > 0) {
            batteryLeft -= amount;
        }
        if (batteryLeft <= 0) {
            endGame();
        }
    }

   

    public void addDigPower(int amount) {
        if (amount > 0) {
            digPower += amount;
        }
    }

    public void setGameScore(int gameScore) {
        if (gameScore < 0) {
            gameScore = 0;
        }
        this.gameScore = gameScore;
    }

    public int getGameScore() {
        return gameScore;
    }

    public int getBatteryLeft() {
        return batteryLeft;
    }

    public int getDigPower() {
        return digPower;
    }

    public boolean getGameEnded() {
        return isGameEnded;
    }

}
