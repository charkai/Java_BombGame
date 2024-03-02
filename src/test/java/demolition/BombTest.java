package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PImage;

public class BombTest {

    @Test
    public void constructorTest() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];
        assertNotNull(new Bomb(2, 2, sprites, explosions));
    }

    @Test
    public void clearTest() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];
        Bomb bomb = new Bomb(2, 2, sprites, explosions);
        bomb.clear(); 
        assertFalse(bomb.isTicking());
        assertFalse(bomb.isActive());
        assertFalse(bomb.isExploding());

    }
    @Test
    public void checkDirections() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];
        Bomb bomb = new Bomb(2, 2, sprites, explosions);
        bomb.setLeft(1);
        bomb.setRight(1);
        bomb.setUp(1);
        bomb.setDown(1);
        assertEquals(bomb.getLeft(), 1);
        assertEquals(bomb.getRight(), 1);
        assertEquals(bomb.getUp(), 1);
        assertEquals(bomb.getDown(), 1);
    }
    @Test
    public void checkSetXandY() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];
        Bomb bomb = new Bomb(2, 2, sprites, explosions);
        bomb.setX(1);
        bomb.setY(1);
        assertEquals(bomb.getX(), 1);
        assertEquals(bomb.getY(), 1);
    }

    @Test
    public void bombTickTest() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];
        Bomb bomb1 = new Bomb(2, 2, sprites, explosions);
        Bomb bomb2 = new Bomb(3, 3, sprites, explosions);
        bomb1.clear();
        assertDoesNotThrow(() -> bomb1.tick(null, null, null, null));
        assertThrows(NullPointerException.class, () -> bomb2.tick(null, null, null, null));
    }

    @Test
    public void checkCharacterCaughtTest() {
        PImage[] sprites = new PImage[4];
        PImage[] explosions = new PImage[3];

        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        Bomb bomb = new Bomb(64, 64, sprites, explosions);

        bomb.setUp(2);
        bomb.setDown(2);
        bomb.setLeft(2);
        bomb.setRight(2);

        BombGuy player1 = new BombGuy(64, 64, up, down, left, right, 2);
        BombGuy player2 = new BombGuy(208, 208, up, down, left, right, 2);
        BombGuy player3 = new BombGuy(32, 64, up, down, left, right, 2);
        BombGuy player4 = new BombGuy(64, 32, up, down, left, right, 2);
        BombGuy player5 = new BombGuy(96, 64, up, down, left, right, 2);
        BombGuy player6 = new BombGuy(64, 96, up, down, left, right, 2);

        assertTrue(bomb.checkCharacterCaughtInExplosion(player1));
        assertFalse(bomb.checkCharacterCaughtInExplosion(player2));
        assertTrue(bomb.checkCharacterCaughtInExplosion(player3));
        assertTrue(bomb.checkCharacterCaughtInExplosion(player4));
        assertTrue(bomb.checkCharacterCaughtInExplosion(player5));
        assertTrue(bomb.checkCharacterCaughtInExplosion(player6));

    }
}