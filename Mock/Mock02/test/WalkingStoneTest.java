package test;

import logic.game.GameManager;

import logic.stone.WalkingStone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import render.MapManager;

public class WalkingStoneTest {

    GameManager gameManager;
    MapManager mapManager;

    int playerPosX;
    int playerPosY;

    int stonePosX;
    int stonePosY;

    @BeforeEach
    public void setUp() {
        gameManager = GameManager.resetInstance();
        mapManager = MapManager.resetInstance();
        playerPosX = mapManager.getPlayerX();
        playerPosY = mapManager.getPlayerY();
        stonePosX = playerPosX + 2;
        stonePosY = playerPosY;
    }

    @Test
    public void constructorTest() {
        WalkingStone w = new WalkingStone(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, w);
        assertEquals(mapManager.getStoneAt(stonePosX, stonePosY), w);
    }

    @Test
    public void digTest() {
        WalkingStone w = new WalkingStone(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, w);
        assertEquals(mapManager.getStoneAt(stonePosX, stonePosY), w);
        assertEquals(0, gameManager.getGameScore());

        w.dig(1);
        assertNull(mapManager.getStoneAt(stonePosX, stonePosY));
        assertEquals(0, gameManager.getGameScore());

        WalkingStone w2 = new WalkingStone(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, w2);
        assertEquals(mapManager.getStoneAt(stonePosX, stonePosY), w2);

        w2.dig(2);
        assertEquals(1, gameManager.getGameScore());
        assertNull(mapManager.getStoneAt(stonePosX, stonePosY));
    }

    @Test
    public void walkTest() {
        WalkingStone w = new WalkingStone(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, w);
        assertEquals(mapManager.getStoneAt(stonePosX, stonePosY), w);
        w.walk();
        assertNull(mapManager.getStoneAt(stonePosX, stonePosY));
        assertEquals(mapManager.getStoneAt(stonePosX - 1, stonePosY), w);
    }

}
