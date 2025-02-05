package test;

import logic.game.GameManager;

import logic.stone.Crystal;
import logic.stone.Gear;
import logic.stone.HardStone;
import logic.stone.Stone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import render.MapManager;

public class GearTest {

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
    public void noParameterConstructorTest() {
        Gear g = new Gear(stonePosX, stonePosY);
        assertEquals(stonePosX, g.getPosX());
        assertEquals(stonePosY, g.getPosY());
        assertEquals(2, g.getDurability());
        assertEquals(1, g.getUpgradeValue());
    }

    @Test
    public void parameterConstructorTest() {
        Gear g = new Gear(stonePosX, stonePosY, 3);
        assertEquals(stonePosX, g.getPosX());
        assertEquals(stonePosY, g.getPosY());
        assertEquals(2, g.getDurability());
        assertEquals(3, g.getUpgradeValue());

        Gear g1 = new Gear(stonePosX, stonePosY, 1);
        assertEquals(1, g1.getUpgradeValue());

        Gear g2 = new Gear(stonePosX, stonePosY, -1);
        assertEquals(1, g2.getUpgradeValue());
    }

    @Test
    public void getterSetterTest() {
        Gear g = new Gear(stonePosX, stonePosY, 3);
        assertEquals(3, g.getUpgradeValue());

        g.setUpgradeValue(-1);
        assertEquals(1, g.getUpgradeValue());

        g.setUpgradeValue(40);
        assertEquals(40, g.getUpgradeValue());
    }

    @Test
    public void destroyTest() {
        int oldDigPower = gameManager.getDigPower();
        Gear g = new Gear(stonePosX, stonePosY, 3);
        mapManager.placeStoneAtPos(stonePosX, stonePosY, g);
        assertEquals(2, g.getDurability());
        assertEquals(mapManager.getStoneAt(stonePosX, stonePosY), g);

        g.dig(1);
        assertEquals(1, g.getDurability());

        g.dig(3);
        assertEquals(0, g.getDurability());

        assertEquals(oldDigPower + 3, gameManager.getDigPower());
        assertNull(mapManager.getStoneAt(stonePosX, stonePosY));
    }

}
