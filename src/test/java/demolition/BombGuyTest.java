package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PImage;

public class BombGuyTest {
    @Test
    public void constructorTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        assertNotNull(new BombGuy(10, 10, up, down, left, right, 5)); 

    }
    @Test
    public void tickTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy1 = new BombGuy(10, 10, up, down, left, right, 0); 
        bombGuy1.tick(null);
        BombGuy bombGuy2 = new BombGuy(10, 10, up, down, left, right, 1);
        assertThrows(NullPointerException.class, ()->bombGuy2.tick(null));
    }
    @Test
    public void resetTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(10, 10, up, down, left, right, 0); 
        bombGuy.setStartingX(20); 
        bombGuy.setStartingY(20); 
        bombGuy.reset();
        assertEquals(bombGuy.getX(), 20); 
        assertEquals(bombGuy.getY(), 20); 

    }
    @Test
    public void startingCoordsTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(10, 10, up, down, left, right, 0); 
        assertNull(bombGuy.startingCoords(""));

    }
    @Test
    public void getAndSetStartingCoords() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(10, 10, up, down, left, right, 0); 
        bombGuy.setStartingX(1);
        bombGuy.setStartingY(2);
        assertEquals(bombGuy.getStartingX(), 1);
        assertEquals(bombGuy.getStartingY(), 2);
    }
    @Test
    public void killBombGuy() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(10, 10, up, down, left, right, 1); 
        bombGuy.kill(); 
        assertEquals(bombGuy.livesLeft(), 0);
    }
    @Test
    public void moveBombGuy() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(64, 64, up, down, left, right, 1); 
        bombGuy.moveUp(); 
        assertTrue(bombGuy.isUp());
        assertEquals(bombGuy.getY(), 32);

        bombGuy.moveDown(); 
        assertTrue(bombGuy.isDown());
        assertEquals(bombGuy.getY(), 64);

        bombGuy.moveLeft(); 
        assertTrue(bombGuy.isLeft());
        assertEquals(bombGuy.getX(), 32);

        bombGuy.moveRight(); 
        assertTrue(bombGuy.isRight());
        assertEquals(bombGuy.getX(), 64);
    }
    @Test
    public void bombGuyTick() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy1 = new BombGuy(64, 64, up, down, left, right, 0); 
        BombGuy bombGuy2 = new BombGuy(64, 64, up, down, left, right, 1); 
        assertDoesNotThrow(() -> bombGuy1.tick(null));
        assertThrows(NullPointerException.class, () -> (bombGuy2.tick(null)));
    
    }
}