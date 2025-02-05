package test;

import logic.game.GameManager;

import logic.stone.HardStone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import render.MapManager;

public class HardStoneTest {

    GameManager gameManager;
    MapManager mapManager;

    int playerPosX;
    int playerPosY;
    @BeforeEach
    public void setUp(){
        gameManager = GameManager.resetInstance();
        mapManager = MapManager.resetInstance();
        playerPosX = mapManager.getPlayerX();
        playerPosY = mapManager.getPlayerY();
    }

    @Test
    public void constructorTest(){
        HardStone h1 = new HardStone(playerPosX + 1, playerPosY, 3);
        // Test Position
        assertEquals(playerPosX+1, h1.getPosX());
        assertEquals(playerPosY, h1.getPosY());
        // Test Normal Durability.
        assertEquals(3,h1.getDurability());

        HardStone h2 = new HardStone(playerPosX + 1, playerPosY, -1);
        // Test Negative Durability.
        assertEquals(0,h2.getDurability());

        HardStone h3 = new HardStone(playerPosX + 1, playerPosY, 10);
        // Test Negative Durability.
        assertEquals(5,h3.getDurability());
    }

    @Test
    public void durabilityGetterSetterTest(){
        HardStone h1 = new HardStone(playerPosX + 1, playerPosY, 3);
        assertEquals(3,h1.getDurability());
        h1.setDurability(1);
        assertEquals(1,h1.getDurability());

        h1.setDurability(-1);
        assertEquals(0,h1.getDurability());

        h1.setDurability(10);
        assertEquals(5,h1.getDurability());
    }

    @Test
    public void digTest(){
        HardStone h1 = new HardStone(playerPosX + 1, playerPosY, 3);
        mapManager.placeStoneAtPos(h1.getPosX(), h1.getPosY(), h1);
        h1.dig(1);
        assertEquals(2,h1.getDurability());
        h1.dig(3);
        assertEquals(0,h1.getDurability());
        assertNull(mapManager.getStoneAt(h1.getPosX(),h1.getPosY()));
    }

}
