package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList; 
import processing.core.PImage;

public class YellowEnemyTest {

    @Test
    public void constructorTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        assertNotNull(new YellowEnemy(10, 10, up, down, left, right, 1));
    }

    @Test
    public void changeDirectionTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        YellowEnemy enemy = new YellowEnemy(10, 10, up, down, left, right,1);
        enemy.changeDirection();
        assertTrue(enemy.isLeft()); 
        enemy.changeDirection();
        assertTrue(enemy.isUp());
        enemy.changeDirection();
        assertTrue(enemy.isRight());
        enemy.changeDirection();
        assertTrue(enemy.isDown());

    }
    @Test
    public void getEnemiesTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        assertNull(YellowEnemy.getEnemies("", up, down, left, right)); 
    }

    @Test
    public void moveTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        YellowEnemy enemy = new YellowEnemy(2, 2, up, down, left, right, 1); 
        Level level = new Level("", null, null, null, null, null, null, 180);

    }
}
