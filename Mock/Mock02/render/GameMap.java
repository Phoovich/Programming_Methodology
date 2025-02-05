package render;


import logic.stone.*;

import java.util.ArrayList;

public class GameMap {

    private static int[][] mapData =
            {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 3, 1, 4, 1, 1},
            {1, 1, 1, 3, 1, 1, 0, 0, 0, 1, 1, 1, 1, 6, 1},
            {1, 1, 1, 1, 1, 1, 0, 9, 0, 1, 5, 1, 1, 1, 1},
            {1, 1, 3, 1, 1, 1, 0, 0, 0, 1, 1, 1, 4, 1, 1},
            {1, 1, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 6, 1},
            {1, 1, 4, 1, 1, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};

    private final ArrayList<ArrayList<Stone>> mapSheet;
    private final ArrayList<WalkingStone> walkingStones;


    private int startX;
    private int startY;

    public GameMap() {
        mapSheet = new ArrayList<>();
        walkingStones = new ArrayList<>();
        loadMap("");
        for (int i = 0; i < mapData.length; i++) {
            ArrayList<Stone> temp = new ArrayList<>();
            for (int j = 0; j < mapData[i].length; j++) {
                Stone stone = switch (mapData[i][j]) {
                    case 1 -> new Stone(j,i);
                    case 2 -> new HardStone(j,i,2);
                    case 3 -> new Gear(j,i);
                    case 4 -> new Crystal(j,i);
                    case 5 -> new Dynamite(j,i);
                    case 6 -> new WalkingStone(j,i);
                    default -> null;
                };
                temp.add(stone);
                if (stone instanceof WalkingStone) {
                    walkingStones.add((WalkingStone) stone);
                }
                if (mapData[i][j] == 9) {
                    startX = j;
                    startY = i;
                }
            }
            mapSheet.add(temp);
        }
    }

    public static void loadMap(String mapName){
        switch (mapName){
            case("testMap1"):
                mapData = MapData.testMap1;
                break;
            case("gameMap"):
                mapData = MapData.gameMap;
                break;
            case("walkingStone"):
                mapData = MapData.WalkingStoneMapTest;
                break;
            default:
                mapData = MapData.gameMap;
        }
    }

    public ArrayList<ArrayList<Stone>> getMapSheet() {
        return mapSheet;
    }
    public Stone getStone(int posX, int posY){
        return getMapSheet().get(posY).get(posX);
    }


    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStoneX(Stone stone) {
        return stone.getPosX();
    }

    public int getStoneY(Stone stone) {
        return stone.getPosY();
    }

    public void moveStone(Stone stone, int newX, int newY) {
        int oldX = stone.getPosX();
        int oldY = stone.getPosY();
        mapSheet.get(oldY).set(oldX, null);
        stone.setPosX(newX);
        stone.setPosY(newY);
        mapSheet.get(newY).set(newX, stone);
    }

    public ArrayList<WalkingStone> getWalkingRocks() {
        return walkingStones;
    }

}
