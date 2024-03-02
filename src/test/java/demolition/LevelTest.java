package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList; 
import processing.core.PImage;

public class LevelTest {

    @Test
    public void constructorTest() {
        assertNotNull(new Level("", null, null, null, null, null, null, 180)); 
    }
    @Test 
    public void testActive() {
        Level level = new Level("", null, null, null, null, null, null, 180); 
        assertTrue(level.isActive());
    }

    @Test 
    public void timerTest() {
        Level level = new Level("", null, null, null, null, null, null, 180); 
        level.timeDown(); 
        assertEquals(level.getTimer(), 179); 
    }

    @Test
    public void playerSetAndGetTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        level.setPlayer(null); 
        assertNull(level.getPlayer());

    }
    @Test 
    public void yellowEnemySetAndGetTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        ArrayList<YellowEnemy> enemy = new ArrayList<>();
        enemy.add(null); 
        level.setYellowEnemies(enemy); 
        assertNull(level.getYellowEnemies().get(0)); 
    }   
    @Test 
    public void redEnemySetAndGetTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        ArrayList<RedEnemy> enemy = new ArrayList<>();
        enemy.add(null); 
        level.setRedEnemies(enemy); 
        assertNull(level.getRedEnemies().get(0)); 
    }   
    @Test 
    public void mapTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        assertNotNull(level.getMap());

    }
    @Test 
    public void bombTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        assertNotNull(level.getBombs());
    }
    @Test
    public void collisionTest1() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        ArrayList<RedEnemy> rEnemy = new ArrayList<>();
     
        level.setRedEnemies(rEnemy); 

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();
        
        level.setYellowEnemies(yEnemy); 

        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertFalse(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));

    }
    @Test
    public void collisionTest2() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        RedEnemy enemy1 = new RedEnemy(10, 10, up, down, left, right, 5);
        rEnemy.add(enemy1);
        level.setRedEnemies(rEnemy); 

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        level.setYellowEnemies(yEnemy); 

        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertTrue(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }
    @Test
    public void collisionTest3() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        YellowEnemy enemy1 = new YellowEnemy(10, 10, up, down, left, right, 5);
        yEnemy.add(enemy1);

        level.setYellowEnemies(yEnemy);
        level.setRedEnemies(rEnemy); 

        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertTrue(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }
    @Test
    public void collisionTest4() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        YellowEnemy enemy1 = new YellowEnemy(10, 5, up, down, left, right, 5);
        yEnemy.add(enemy1);

        level.setYellowEnemies(yEnemy);
        level.setRedEnemies(rEnemy); 
        
        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertFalse(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }
    @Test
    public void collisionTest5() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        YellowEnemy enemy1 = new YellowEnemy(10, 10, up, down, left, right, 0);
        yEnemy.add(enemy1);

        level.setYellowEnemies(yEnemy);
        level.setRedEnemies(rEnemy); 
        
        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertFalse(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }

    @Test
    public void collisionTest6() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        RedEnemy enemy1 = new RedEnemy(10, 10, up, down, left, right, 0);
        rEnemy.add(enemy1);

        level.setYellowEnemies(yEnemy);
        level.setRedEnemies(rEnemy); 
        
        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertFalse(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }

    @Test
    public void collisionTest7() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];

        ArrayList<RedEnemy> rEnemy = new ArrayList<>();

        ArrayList<YellowEnemy> yEnemy = new ArrayList<>();

        RedEnemy enemy1 = new RedEnemy(5, 10, up, down, left, right, 1);
        rEnemy.add(enemy1);

        level.setYellowEnemies(yEnemy);
        level.setRedEnemies(rEnemy); 
        
        level.setPlayer(new BombGuy(10, 10, up, down, left, right, 5));
        assertFalse(level.checkPlayerCollidesWithEnemy(level.getYellowEnemies(), level.getRedEnemies()));
    }

    @Test
    public void resetTest() {
        Level level = new Level("", null, null, null, null, null, null, 180);
        PImage[] up = new PImage[4];
        PImage[] down = new PImage[4];
        PImage[] left = new PImage[4];
        PImage[] right = new PImage[4];
        BombGuy player = new BombGuy(10, 10, up, down, left, right, 5); 
    }

}