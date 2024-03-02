package demolition; 

import processing.core.PImage;
import processing.core.PApplet;

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

/** Abstract class for initialising an enemy. */

public abstract class Enemy extends Character {
    
    /**  
    * Constructor for an Enemy entity. Enemies require an x-coordinate, y-coordinate, an array of upwards, downwards, left and right sprites, as well as a number of lives
    * @param x x-coordinate
    * @param y y-coordinate
    * @param spriteUp array of upwards sprites
    * @param spriteDown array of downwards sprites
    * @param spriteLeft array of left sprites
    * @param spriteRight array of right sprites
    * @param lives number of lives
    */
    public Enemy(int x, int y, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight, int lives) {
        super(x, y, spriteUp, spriteDown, spriteLeft, spriteRight, lives);
    }
    
    /** 
    * Dictates the behaviour of the enemy. 
    * If the enemy is still alive, it moves every 60 frames (1 second). 
    * If the enemy is still alive, it will be drawn to the screen.
    * @param app the application window that the enemy is displayed on
    * @param level the level that the enemy exists in
    */
    public void tick(PApplet app, Level level) {
        if (this.lives > 0) {
            if (app.frameCount % 60 == 0) {
                this.move(level);
            }
            this.draw(app);
        }
    }
    /**
    * Moves the enemy around the map. The enemy moves into a straight line until it is met with a solid or broken wall, when it then changes direction. 
    * If an enemy moves into a player, the player loses a life and the level resets. 
    * If an enemy moves into the path of an explosion, it is killed.
    *@param level the level the enemy is moving within
    */
    public void move(Level level) {

        // As the map and tile information is stored in an array, the enemy's coordinates must be converted to a position in the array
        // This means dividing by 32, as this was how the coordinates were initialised
        // For the y-coordinates, the map has a 64pixel wide buffer at the top, so the coordinates are offset by 2 units. 
        int xInArray = this.getX()/32; 
        int yInArray = this.getY()/32 -2; 
       

        // the enemy moves in a straight line so they move until they encounter a barrier 
        if (this.up == true) {
            yInArray--; 
            Tile tile = level.getMap().getTiles()[yInArray][xInArray];
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                // If the enemy encounters a wall, they change directions and the method is called recursively to ensure that the enemy still moves once every second.
                this.changeDirection(); 
                this.move(level);
                return; 
            }
            else {
                this.moveUp(); 
                
            }
        }
        else if (this.down == true) {
            yInArray++; 
            Tile tile = level.getMap().getTiles()[yInArray][xInArray];
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                this.changeDirection(); 
                this.move(level);
                return; 
            }
            else {
                this.moveDown();

            }
        }
        else if (this.right == true) {
            xInArray++; 
            Tile tile = level.getMap().getTiles()[yInArray][xInArray];
    
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                this.changeDirection(); 
                this.move(level); 
                return; 
            }
            else {
                this.moveRight();
            }
        }
        else if (this.left == true) {
            xInArray--; 
            Tile tile = level.getMap().getTiles()[yInArray][xInArray];
    
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                this.changeDirection();
                this.move(level);
                return; 
            }
            else {
                this.moveLeft(); 
    
            }
        }
        // if the enemy collides with the player, the player is killed and the level is reset.
        if (checkPlayerCollision(level.getPlayer())) {
            level.getPlayer().kill(); 
            level.reset(); 
        }
        // if the enemy walks into an explosion path, it is killed
        for (int i = 0; i < level.getBombs().size(); i++) {
            if (level.getBombs().get(i).checkCharacterCaughtInExplosion(this) && level.getBombs().get(i).isExploding()) {
                this.kill(); 
            }
        }
    }
    /** 
    * Returns true if the enemy has collided with the player.
    * @param player the BombGuy to be checked whether they have collided 
    * @return whether the enemy collides with a player
     */
    public boolean checkPlayerCollision(BombGuy player) {
        if (this.x == player.getX() && this.y == player.getY()) {
            return true;
        }
        return false; 
    }
    /** 
    * Changes the direction of the enemy. Yellow and Red Enemies have different algorithms for changing their direction
    */
    public abstract void changeDirection();

    /** 
    * Resets the status of the enemy. 
    * Sets the x-coordinate to its starting x-coordinate and the y-coordinate to its starting y-coordinate, resets the number of lives and sets the enemy facing downwards.
    */
    public void reset() {
        this.x = startingX;
        this.y = startingY; 
        this.lives = 1; 
        this.up = false; 
        this.down = true; 
        this.left = false; 
        this.right = false;
    }
}
