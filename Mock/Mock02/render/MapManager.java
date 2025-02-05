package render;

import logic.stone.*;

import java.util.ArrayList;

public class MapManager {

    private static MapManager instance;

    private final GameMap gameMap;
    private int playerX;
    private int playerY;
    private final int mapSizeX;
    private final int mapSizeY;
     


    public MapManager() {
        
        gameMap = new GameMap();
        playerX = gameMap.getStartX();
        playerY = gameMap.getStartY();
        mapSizeY = gameMap.getMapSheet().size();
        mapSizeX = gameMap.getMapSheet().get(0).size();
    }

    public static MapManager getInstance() {
        if (instance == null) {
            instance = new MapManager();
        }
        return instance;
    }

    public static MapManager resetInstance() {
        instance = new MapManager();
        return instance;
    }

    public void renderMap() {
        ArrayList<ArrayList<Stone>> mapSheet = gameMap.getMapSheet();
        for (int i = 0; i < mapSheet.size(); i++) {
            for (int j = 0; j < mapSheet.get(i).size(); j++) {
                if (playerX == j && playerY == i) {
                    System.out.print("(P)");
                    continue;
                }
                System.out.print(getStoneIcon(mapSheet.get(i).get(j)));
            }
            System.out.println();
        }
    }


    private String getStoneIcon(Stone stone) {
        if (stone == null) return "   ";
        if (stone instanceof Crystal) return "{C}";
        if (stone instanceof Gear) return "{G}";
        if (stone instanceof Dynamite) return "<D>";
        if (stone instanceof WalkingStone) return "<W>";
        if (stone instanceof HardStone) return "[H]";
        return "[-]";
    }

    public String getStoneName(Stone stone) {
        if (stone == null) return "???";
        if (stone instanceof Crystal) return "Crystal";
        if (stone instanceof Gear) return "Gear";
        if (stone instanceof Dynamite) return "Dynamite";
        if (stone instanceof WalkingStone) return "Walking Rock";
        if (stone instanceof HardStone) return "Hard Stone";
        return "Stone";
    }

    private boolean checkOutbound(int x, int y) {
        return x < 0 || x >= mapSizeX || y < 0 || y >= mapSizeY;
    }

    public boolean checkIsStone(int x, int y) {
        if (checkOutbound(x, y)) return false;
        return gameMap.getMapSheet().get(y).get(x) != null;
    }

    public boolean checkEmptyTile(int x, int y) {
        if (checkOutbound(x, y)) return false;
        if (x == playerX && y == playerY) return false;
        return gameMap.getMapSheet().get(y).get(x) == null;
    }

    public Stone getStoneAt(int x, int y) {
        if (checkOutbound(x, y)) return null;
        return gameMap.getMapSheet().get(y).get(x);
    }

    public ArrayList<Stone> getAdjacentStonesAtPos(int x, int y) {
        ArrayList<Stone> stones = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == j) continue;
                if (getStoneAt(i, j) == null) continue;
                stones.add(getStoneAt(i, j));
            }
        }
        return stones;
    }

    public void placeStoneAtPos(int x, int y, Stone stone ){
        if(playerX == x && playerY == y) return;
        if(stone instanceof WalkingStone){
            gameMap.getWalkingRocks().add((WalkingStone) stone);
        }

        gameMap.getMapSheet().get(y).set(x, stone);
    }

    public void removeStoneAtPos(int x, int y) {
        gameMap.getMapSheet().get(y).set(x, null);
    }

    public void movePlayer(int x, int y) {
        if (checkEmptyTile(x, y)) {
            playerX = x;
            playerY = y;
        }
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

}
