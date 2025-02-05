package test;

import logic.game.GameManager;
import logic.stone.*;


import logic.stone.Crystal;
import logic.stone.Dynamite;
import logic.stone.HardStone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import render.MapManager;

public class DynamiteTest {
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
    public void constructorTest() {
        Dynamite d = new Dynamite(stonePosX, stonePosY);
        assertEquals(stonePosX, d.getPosX());
        assertEquals(stonePosY, d.getPosY());
    }

    @Test
    public void destroyTest() {
        Dynamite d = new Dynamite(stonePosX, stonePosY);
        // Place
        mapManager.placeStoneAtPos(d.getPosX(), d.getPosY(), d);
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                if (i == 0 && j == 0) continue;
                int px = d.getPosX() + i;
                int py = d.getPosY() + j;
                mapManager.placeStoneAtPos(px, py, new Stone(px, py));
            }
        }
        d.destroy();

        // Check null
        for (int j = -1; j <= 1; j++) {
            for (int i = -1; i <= 1; i++) {
                int px = d.getPosX() + i;
                int py = d.getPosY() + j;
                assertNull(mapManager.getStoneAt(px, py));
            }
        }

    }

}
