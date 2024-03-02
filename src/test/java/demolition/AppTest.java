package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import processing.core.PApplet;

public class AppTest {
    @Test
    public void testPlayerMovement() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        // These tests are simulating the action of a key being pressed - directly triggering a player's movement
    
        assertEquals(app.player.getX(), 160); 
        assertEquals(app.player.getY(), 160);

        app.currentLevel.playerAction(1);
        //moving up
      
        assertEquals(app.player.getX(), 160);
        assertEquals(app.player.getY(), 128);
        
        app.currentLevel.playerAction(-1);
        // moving down
      
        assertEquals(app.player.getX(), 160);
        assertEquals(app.player.getY(), 160);

        app.currentLevel.playerAction(2); 
        // moving right
        
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 160);

        app.currentLevel.playerAction(-2); 
        // moving left
        
        assertEquals(app.player.getX(), 160);
        assertEquals(app.player.getY(), 160); 

        app.currentLevel.playerAction(0); 
        // placing bombs
        
        assertEquals(app.player.getX(), 160);
        assertEquals(app.player.getY(), 160);
        assertEquals(app.currentLevel.getBombs().size(), 1);
        
    } 
    @Test
    public void testPlayerMovement2() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);
        
        // The player moves 2 spaces upwards when they are met with a solid wall
        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(1);
        assertEquals(app.player.getX(), 160);
        assertEquals(app.player.getY(), 96);

        // the player should not change their position as a wall has prevented them from moving up 
        app.currentLevel.playerAction(1);
        assertEquals(app.player.getX(), 160); 
        assertEquals(app.player.getY(), 96); 

        // the player also is unable to move right as there is a broken wall here which should prevent them from being able to move
        app.currentLevel.playerAction(2);
        assertEquals(app.player.getX(), 160); 
        assertEquals(app.player.getY(), 96);

        // the player walks left until they hit a wall
        app.currentLevel.playerAction(-2);
        app.currentLevel.playerAction(-2);
        app.currentLevel.playerAction(-2);
        app.currentLevel.playerAction(-2);
        assertEquals(app.player.getX(), 32);
        assertEquals(app.player.getY(), 96); 

        // the player should not be able to move left again as a wall is there
        app.currentLevel.playerAction(-2); 
        assertEquals(app.player.getX(), 32); 
        assertEquals(app.player.getY(), 96);
     
        // player walks down until they hit a broken wall
        app.currentLevel.playerAction(-1);
        app.currentLevel.playerAction(-1);
        assertEquals(app.player.getX(), 32);
        assertEquals(app.player.getY(), 160);

        // the player should not be able to move down again as there is a wall
        app.currentLevel.playerAction(-1);
        assertEquals(app.player.getX(), 32);
        assertEquals(app.player.getY(), 160);
    }
    @Test
    public void testPlayersCannotWalkThroughBrokenWalls() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config8.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move upwards into a broken wall
        app.currentLevel.playerAction(1); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256); 

        // there is no change in position if the player tries to move downwards into a broken wall
        app.currentLevel.playerAction(-1); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move left into a broken wall
        app.currentLevel.playerAction(-2); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move right into a broken wall
        app.currentLevel.playerAction(2); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);
      
    }

    @Test
    public void testPlayersCannotWalkThroughSolidWalls() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config4.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);   

        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move upwards into a solid wall
        app.currentLevel.playerAction(1); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256); 

        // there is no change in position if the player tries to move downwards into a solid wall
        app.currentLevel.playerAction(-1); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move left into a solid wall
        app.currentLevel.playerAction(-2); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);

        // there is no change in position if the player tries to move right into a solid wall
        app.currentLevel.playerAction(2); 
        assertEquals(app.player.getX(), 192); 
        assertEquals(app.player.getY(), 256);
    }
    @Test
    public void testGoal() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config2.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        // player spawns next to the goal and turns right
        app.currentLevel.playerAction(2); 
        app.draw(); 

        // the level is no longer active (the player has passed the level)
        assertFalse(app.levels.get(0).isActive());
        app.draw(); 
        
        // player spawns next to the goal and moves left
        app.currentLevel.playerAction(-2); 
        app.draw(); 
        // player has passed this level
        assertFalse(app.levels.get(1).isActive());

        // player spawns next to the goal and moves down
        app.currentLevel.playerAction(-1);
        app.draw(); 

        // player has passed this level 
        assertFalse(app.levels.get(2).isActive()); 

        // player spawns next to the goal and moves up
        app.currentLevel.playerAction(1); 
        app.draw(); 

        // player has passed the final level
        assertFalse(app.levels.get(3).isActive());

    }
    @Test
    public void resetLevel() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);
        
        // Assert the starting coordinates of the player
        assertEquals(app.player.getX(), 160); 
        assertEquals(app.player.getY(), 160);

        // the player moves right
        app.currentLevel.playerAction(2); 

        app.currentLevel.reset(); 
        app.delay(4000);
    
        // the player is back to their starting position
        assertEquals(app.player.getX(),160); 
        assertEquals(app.player.getY(), 160);

    }
    @Test
    public void testForPlayerCollisionWithEnemy(){
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config3.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

         
        app.currentLevel.playerAction(1); 
        // the player is killed as it has walked directly upwards into an enemy
        assertEquals(app.player.livesLeft(), 2);
    }
    @Test
    public void testExplosionsKillPlayersAndBreaksWalls() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.delay(4000);

        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(0); 
        app.currentLevel.getBombs().get(0).explode(app.currentLevel.getMap(), app.player, app.currentLevel); 
        
        // assert that the player has lost a life 
        assertEquals(app.player.livesLeft(), 2);

        // assert that the explosion only does not travel upwards (because of solid walls)
        // and only travels 1 unit right due to the broken wall
        assertEquals(app.currentLevel.getBombs().get(0).getUp(), 0);
        assertEquals(app.currentLevel.getBombs().get(0).getRight(), 1);
        assertEquals(app.currentLevel.getBombs().get(0).getLeft(), 2);
        assertEquals(app.currentLevel.getBombs().get(0).getDown(), 2);
        
    }
    @Test
    public void testExplosionsAreStoppedByWalls() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config4.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.delay(4000);

        app.currentLevel.playerAction(0);
        app.currentLevel.getBombs().get(0).explode(app.currentLevel.getMap(), app.player, app.currentLevel); 
        
        // the explosion direction should be inhibited by the broken walls- it should onlny have a centre explosion and 
        // not expand outwards 
        assertEquals(app.currentLevel.getBombs().get(0).getUp(), 0);
        assertEquals(app.currentLevel.getBombs().get(0).getRight(), 0);
        assertEquals(app.currentLevel.getBombs().get(0).getLeft(), 0);
        assertEquals(app.currentLevel.getBombs().get(0).getDown(), 0);
    }
    @Test
    public void testExplosionsDestroyBrokenWalls() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config5.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.delay(4000);

        app.currentLevel.playerAction(-1);
        app.currentLevel.playerAction(-1); 
        app.currentLevel.playerAction(-1); 

        app.currentLevel.playerAction(0);

        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(1);

        // the tiles initially start as broken walls


        assertTrue(app.currentLevel.getMap().getTiles()[6][5] instanceof BrokenWall);        
        assertTrue(app.currentLevel.getMap().getTiles()[7][6] instanceof BrokenWall);
        assertTrue(app.currentLevel.getMap().getTiles()[6][7] instanceof BrokenWall);



        app.currentLevel.getBombs().get(0).explode(app.currentLevel.getMap(), app.player, app.currentLevel); 
        
        // the bomb will have a radius of 1 in each direction as it will explode through the solid wall
        assertEquals(app.currentLevel.getBombs().get(0).getUp(), 2);
        assertEquals(app.currentLevel.getBombs().get(0).getRight(), 1);
        assertEquals(app.currentLevel.getBombs().get(0).getLeft(), 1);
        assertEquals(app.currentLevel.getBombs().get(0).getDown(), 1);


        // the left tile has been turned into an empty tile 
        assertTrue(app.currentLevel.getMap().getTiles()[6][5] instanceof Empty);

        // the bottom tile has been turned into an empty tile
        assertTrue(app.currentLevel.getMap().getTiles()[7][6] instanceof Empty);

        // the right tile has been turned into an empty tile 
        assertTrue(app.currentLevel.getMap().getTiles()[6][7] instanceof Empty);

        // player moves and places a bomb underneth the broken tile on the left side of the map
        app.currentLevel.playerAction(-1);
        app.currentLevel.playerAction(-1);
        app.currentLevel.playerAction(-1); 
        app.currentLevel.playerAction(-2);

        app.currentLevel.playerAction(0); 

        // player moves back to safety
        app.currentLevel.playerAction(2); 
        app.currentLevel.playerAction(1); 
        app.currentLevel.playerAction(1);
        app.currentLevel.playerAction(1);

        // the upper tile starts broken
        assertTrue(app.currentLevel.getMap().getTiles()[5][5] instanceof BrokenWall);

        app.currentLevel.getBombs().get(1).explode(app.currentLevel.getMap(), app.player, app.currentLevel); 

        // the tile should now have been broken and is now empty 
        assertTrue(app.currentLevel.getMap().getTiles()[5][5] instanceof Empty);
    }
    @Test
    public void testEnemyMovement() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(1000);
        app.setup();
        app.delay(4000);

    
        YellowEnemy enemy = app.currentLevel.getYellowEnemies().get(0);
        assertEquals(enemy.getX(), 160);
        assertEquals(enemy.getY(), 352); 

        enemy.move(app.currentLevel); 
        // the enemy should start by moving down

        assertEquals(enemy.getX(), 160); 
        assertEquals(enemy.getY(), 384); 

        enemy.move(app.currentLevel);

        assertEquals(enemy.getX(), 160);
        assertEquals(enemy.getY(), 416); 

        enemy.move(app.currentLevel); 
        // the enemy should then turn left and move in thsi direction as it will be going clockwise
        // the enemy shoudl continue left until it hits the wall which will be after 4 movements left

        assertEquals(enemy.getX(), 128); 
        assertEquals(enemy.getY(), 416);

        enemy.move(app.currentLevel);

        assertEquals(enemy.getX(), 96); 
        assertEquals(enemy.getY(), 416);

        enemy.move(app.currentLevel);

        assertEquals(enemy.getX(), 64); 
        assertEquals(enemy.getY(), 416);

        enemy.move(app.currentLevel); 
        assertEquals(enemy.getX(), 32); 
        assertEquals(enemy.getY(), 416);
        
        // the enemy will then move up (clockwise again) as it hits the wall after 2 upwards steps

        enemy.move(app.currentLevel); 
        assertEquals(enemy.getX(), 32); 
        assertEquals(enemy.getY(), 384); 

        enemy.move(app.currentLevel);
        assertEquals(enemy.getX(), 32); 
        assertEquals(enemy.getY(), 352); 

        // the enemy will move right (clockwise change of direction) until it hits the wall after 4 steps

        enemy.move(app.currentLevel);
        assertEquals(enemy.getX(), 64);
        assertEquals(enemy.getY(), 352); 
    
        enemy.move(app.currentLevel); 
        assertEquals(enemy.getX(), 96);
        assertEquals(enemy.getY(), 352); 

        enemy.move(app.currentLevel);
        assertEquals(enemy.getX(), 128);
        assertEquals(enemy.getY(), 352); 

        enemy.move(app.currentLevel);
        assertEquals(enemy.getX(), 160);
        assertEquals(enemy.getY(), 352); 

        // finally, the enemy will move down again as it has hit a wall
        enemy.move(app.currentLevel); 
        assertEquals(enemy.getX(), 160);
        assertEquals(enemy.getY(), 384);

    }
    @Test
    public void gameOver() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config6.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        app.currentLevel.playerAction(1); 
        app.draw();

        // confirm that the player is killed if it walks straight into an enemy
        assertEquals(app.player.livesLeft(), 0); 
      
    }
    @Test
    public void checkPlayerAnimationCycle() {

        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        // DOWN 

        // CHECKING that the animation loop starts at 0
        assertEquals(app.player.getLoopCounter(), 0); 

        for (int i = 1; i < 4; i++) {
            app.frameCount = 12*i; 
            app.player.draw(app); 
            assertTrue(app.player.isDown());
            assertEquals(app.player.getLoopCounter(), i);
        }
        // CHECKING that the animation loop comes back to 0
        app.frameCount = 48; 
        app.player.draw(app); 
        assertEquals(app.player.getLoopCounter(), 0);


        // RIGHT

        app.currentLevel.playerAction(2); 
        assertEquals(app.player.getLoopCounter(), 0); 

        for (int i = 1; i < 4; i++) {
            app.frameCount = 12*i; 
            app.player.draw(app); 
            assertTrue(app.player.isRight()); 
            assertEquals(app.player.getLoopCounter(), i);

        }

        app.frameCount = 48; 
        app.player.draw(app); 
        assertEquals(app.player.getLoopCounter(), 0);

        // LEFT

        app.currentLevel.playerAction(-2); 
        assertEquals(app.player.getLoopCounter(), 0); 

        for (int i = 1; i < 4; i++) {
            app.frameCount = 12*i; 
            app.player.draw(app); 
            assertTrue(app.player.isLeft());
            assertEquals(app.player.getLoopCounter(), i);
        }

        app.frameCount = 48; 
        app.player.draw(app); 
        assertEquals(app.player.getLoopCounter(), 0); 

        // UP

        app.currentLevel.playerAction(1); 

        assertEquals(app.player.getLoopCounter(), 0);

        for (int i = 1; i < 4; i++) {
            app.frameCount = 12*i; 
            app.player.draw(app); 
            assertTrue(app.player.isUp()); 
            assertEquals(app.player.getLoopCounter(), i);
        }

        app.frameCount = 48; 
        app.player.draw(app); 

    
        assertEquals(app.player.getLoopCounter(), 0);
    }

    @Test 
    public void timeDown() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        app.frameCount = 60; 
        app.draw(); 
        assertEquals(app.currentLevel.getTimer(), 179);

        // run out the time 
        for (int i = 2; i < 180; i++) {
            app.frameCount = 60*i; 
            app.draw(); 
            assertEquals(app.currentLevel.getTimer(), 180-i);
        }

        app.frameCount = 60*180; 

        app.draw(); 
        assertEquals(app.currentLevel.getTimer(), 0);

    }
    @Test
    public void testEnemyWalls1() {
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config7.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        YellowEnemy yEnemy = app.currentLevel.getYellowEnemies().get(0); 

        yEnemy.move(app.currentLevel);

        // the player has been killed as the enemy moved down on top of them
        assertEquals(app.player.livesLeft(), 0); 

        // the draw function will not draw a player to screen as they are dead
        app.player.draw(app);
        assertEquals(app.player.livesLeft(), 0);

    }
    @Test
    public void testBombAnimationCycle() {
        
        App app = new App();
        app.noLoop();
        app.setConfig("src/test/resources/config1.json");
        PApplet.runSketch(new String[] {"App"}, app);
        app.delay(4000);
        app.setup();
        app.delay(1000);

        app.currentLevel.playerAction(0); 
        Bomb bomb = app.currentLevel.getBombs().get(0); 

        app.frameCount = 0;

        assertTrue(bomb.isActive());
        assertTrue(bomb.isTicking()); 
        assertFalse(bomb.isExploding());
        assertEquals(bomb.getTickCounter(), 0); 

        for (int i = 1; i < 8; i++) {
            // Every 15 frames, a new frame in the animation cycle should be rendered
            app.frameCount = 15*i; 
            app.draw(); 
            assertTrue(bomb.isTicking());
            assertFalse(bomb.isExploding());
            assertEquals(bomb.getTickCounter(), i); 
        }
        // assume the bomb explodes in all directions (this is tested in another test)
        bomb.setUp(2); 
        bomb.setDown(2); 
        bomb.setLeft(2); 
        bomb.setRight(2);

        bomb.tick(app, app.currentLevel.getMap(), app.player, app.currentLevel); 
        assertTrue(bomb.isExploding());
        for (int i = 1; i < 30; i++) {
            // the explosion sprites stay on screen until 30 frames
            app.frameCount = i; 
            app.draw(); 
            assertTrue(bomb.isExploding());
        }

        app.frameCount = 30;
        // on the 30th frame, the bomb stops exploding 
        app.draw();
        assertFalse(bomb.isExploding());
       
    }
}