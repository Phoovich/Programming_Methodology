package test;

import logic.game.GameManager;

import logic.stone.Dynamite;
import logic.stone.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import render.MapManager;

public class GameManagerTest {

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
        stonePosX = playerPosX + 1;
        stonePosY = playerPosY;
    }

    @Test
    public void digStoneTest() {
        assertEquals(20, gameManager.getBatteryLeft());
        Stone s = new Stone(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, s);
        gameManager.digStone(s);
        assertNull(mapManager.getStoneAt(s.getPosX(),s.getPosY()));
        assertEquals(19, gameManager.getBatteryLeft());
    }

    @Test
    public void digDynamiteTest() {
        gameManager.setGameScore(20);
        assertEquals(20, gameManager.getGameScore());
        Stone s = new Dynamite(stonePosX, stonePosY);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, s);
        gameManager.digStone(s);
        assertNull(mapManager.getStoneAt(s.getPosX(),s.getPosY()));
        assertEquals(15, gameManager.getGameScore());
        assertEquals(19, gameManager.getBatteryLeft());
    }
}
