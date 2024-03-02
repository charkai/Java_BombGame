package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList; 
import processing.core.PImage;

import java.util.NoSuchElementException;

public class MapTest {
    
    @Test
    public void constructorTest() {
        assertNotNull(new Map("", null, null, null, null));
    }
    @Test
    public void makeEmptyTest() {
        Map map = new Map("", null, null, null, null);
        map.makeEmpty(0, 0);
        assertTrue(map.getTiles()[0][0] instanceof Empty);
    }
    @Test
    public void filepathTest() {
        Map map = new Map("hello.txt", null, null, null, null);
        assertEquals(map.getFilepath(), "hello.txt");
    }
    @Test 
    public void mapDraw1() {
        Map map = new Map("hello.txt", null, null, null, null); 
        assertThrows(NullPointerException.class, () -> map.draw(null));
    }

    @Test
    public void invalidMap1() {
        Map map = new Map("src/test/resources/invalidmap1.txt", null, null, null, null); 
        assertThrows(NoSuchElementException.class, () -> map.setUp()); 
    }

    @Test
    public void invalidMap2() {
        Map map = new Map("src/test/resources/invalidmap2.txt", null, null, null, null);
        map.setUp(); 
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy bombGuy = new BombGuy(0, 0, up, down, left, right, 1); 
        assertThrows(NoSuchElementException.class, () -> bombGuy.startingCoords("src/test/resources/invalidmap2.txt")); 
    }
}