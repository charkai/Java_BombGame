package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList; 
import processing.core.PImage;

public class RedEnemyTest {
    @Test
    public void constructorTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        assertNotNull(new RedEnemy(10, 10, up, down, left, right, 1));
    }

    @Test
    public void changeDirectionTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        RedEnemy enemy = new RedEnemy(10, 10, up, down, left, right,1);
        
        // As the red enemy's direction is random, it is hard to ensure that all cases are accounted for 
        for (int i = 0; i < 20; i++) {

            enemy.changeDirection();
            assertTrue(enemy.isUp() || enemy.isDown() || enemy.isLeft() || enemy.isRight()); 
    
        }
    }
    @Test
    public void getEnemiesTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        assertNull(RedEnemy.getEnemies("", up, down, left, right)); 
    }
    @Test
    public void checkPlayerCollisionTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy player1 = new BombGuy(3, 3, up, down, left, right, 1);
        BombGuy player2 = new BombGuy(1, 1, up, down, left, right, 1);
        BombGuy player3 = new BombGuy(2, 3, up, down, left, right, 1);
        BombGuy player4 = new BombGuy(3, 2, up, down, left, right, 1);
        RedEnemy enemy = new RedEnemy(3, 3, up, down, left, right, 1); 
        assertTrue(enemy.checkPlayerCollision(player1));
        assertFalse(enemy.checkPlayerCollision(player2));
        assertFalse(enemy.checkPlayerCollision(player3));
        assertFalse(enemy.checkPlayerCollision(player4));

    }
    @Test
    public void tickTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        RedEnemy enemy = new RedEnemy(3, 3, up, down, left, right, 0); 
        enemy.tick(null, null);
    }

    @Test
    public void resetTest() {
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        RedEnemy enemy = new RedEnemy(3, 3, up, down, left, right, 1); 
        enemy.setStartingX(20);
        enemy.setStartingY(20);

        enemy.reset();
        assertEquals(enemy.getX(), 20);
        assertEquals(enemy.getY(), 20);
        assertTrue(enemy.isDown());
        assertFalse(enemy.isUp());
        assertFalse(enemy.isLeft());
        assertFalse(enemy.isRight());
    }
    
}