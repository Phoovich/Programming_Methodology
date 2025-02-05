package utils;

import logic.stone.Stone;
import logic.stone.WalkingStone;
import render.GameMap;
import render.MapManager;

import java.util.ArrayList;

public class GameUtilities {
    // You don't need to read codes inside the functions

    // Get all the stones that are adjacent to given position
    // Use posX and posY of Stone as parameters
    public static ArrayList<Stone> getAdjacentStones(int x, int y) {
        MapManager mapManager = MapManager.getInstance();
        return mapManager.getAdjacentStonesAtPos(x, y);
    }

    // Remove stone input from the game map
    public static void removeStone(Stone stone) {
        MapManager mapManager = MapManager.getInstance();
        GameMap gameMap = mapManager.getGameMap();
        int x = gameMap.getStoneX(stone);
        int y = gameMap.getStoneY(stone);
        mapManager.removeStoneAtPos(x, y);
    }

    // Move Walking Rock to Next Position
    public static void moveWalkingRock(WalkingStone walkingStone) {
        MapManager mapManager = MapManager.getInstance();
        GameMap gameMap = mapManager.getGameMap();
        int playerX = mapManager.getPlayerX();
        int playerY = mapManager.getPlayerY();
        int oldX = gameMap.getStoneX(walkingStone);
        int oldY = gameMap.getStoneY(walkingStone);
        if (mapManager.getStoneAt(oldX, oldY) == null) {
            // This happens when walking rock is already destroyed.
            return;
        }
        int dx = playerX - oldX;
        int dy = playerY - oldY;
        int direction;
        if (dx != 0) {
            direction = (dx > 0) ? 1 : -1;
            if (mapManager.checkEmptyTile(oldX + direction, oldY)) {
                gameMap.moveStone(walkingStone, oldX + direction, oldY);
                return;
            }
        }
        if (dy != 0) {
            direction = (dy > 0) ? 1 : -1;
            if (mapManager.checkEmptyTile(oldX, oldY + direction)) {
                gameMap.moveStone(walkingStone, oldX, oldY + direction);
            }
        }
    }

}
