package demolition; 

import processing.core.PImage;
import processing.core.PApplet;
import processing.core.PFont; 

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

/** 
Abstract class representing the moving characters in the application.
*/

public abstract class Character extends GameEntity {

    /** Characters have an array of upwards sprites */
    protected PImage[] spriteUp; 
    /** Characters have an array of downwards sprites */
    protected PImage[] spriteDown;     
    /** Characters have an array of left sprites */    
    protected PImage[] spriteLeft; 
    /** Characters have an array of right sprites */
    protected PImage[] spriteRight; 
    /** Characters have a current sprite, which they cycle through to create the animation */
    protected PImage currentSprite;

    /** Characters may be facing left */
    protected boolean left; 
    /** Characters may be facing right */
    protected boolean right; 
    /** Characters may be facing up */
    protected boolean up; 
    /** Characters may be facing down */
    protected boolean down; 

    /** Characters have a loop counter representing the phase of the animation they are currently in */
    protected int loopCounter; 

    /** Characters have a starting x coordinate */
    protected int startingX; 
    /** Characters have a starting y coordinate */
    protected int startingY;

    /** Characters have a number of lives */
    protected int lives; 

    /** 
    Constructs a character. Characters require an x-coordinate, a y-coordinate, an array of upwards, downwards, left and right sprites, and a number of lives.
    They are initially set to be facing downwards, their animation cycle will initialise facing downwards, and their loopcounter will start at 0 as they have not yet begun their animation cycle. 
    * @param x x-coordinate
    * @param y y-coordinate
    * @param spriteUp array of upwards sprites
    * @param spriteDown array of downwards sprites
    * @param spriteLeft array of left sprites
    * @param spriteRight array of right sprites
    * @param lives number of lives the enemy has
    */
    public Character(int x, int y, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight, int lives) {
        super(x, y); 
        this.spriteUp = spriteUp;
        this.spriteDown = spriteDown; 
        this.spriteLeft = spriteLeft; 
        this.spriteRight = spriteRight; 

        this.loopCounter = 0; 
        this.currentSprite = spriteDown[0];
        this.down = true;
        this.up = false; 
        this.right = false; 
        this.left = false;  
        this.lives = lives; 

    }
    /** 
    Draws the character to screen, looping through their animation cycle depending on their current direction. Each sprite stays on screen for 12 frames, or 0.2 seconds. 
    *@param app the application window which the character is drawn to
    */
    public void draw(PApplet app) {
        if (this.lives > 0) {
            app.image(this.currentSprite, this.x, this.y-16); 
            if (app.frameCount % 12 == 0) {
                if (this.down) { 
                        this.currentSprite = this.spriteDown[this.loopCounter]; 
                        // loop resets after having done 1 cycle
                        if (this.loopCounter == 3) {
                            this.loopCounter = 0;
                        } else {
                            this.loopCounter++; 
                        }
                    }
                else if (this.up) { 
                    this.currentSprite = this.spriteUp[this.loopCounter]; 
                    if (this.loopCounter == 3) {
                        this.loopCounter = 0;
                    } else {
                        this.loopCounter++; 
                    }
                    }
                else if (this.left) { 
                    this.currentSprite = this.spriteLeft[this.loopCounter]; 
                    if (this.loopCounter == 3) {
                        this.loopCounter = 0;
                    } else {
                        this.loopCounter++; 
                    }
                }   
                else if (this.right) { 
                    this.currentSprite = this.spriteRight[this.loopCounter]; 
                    if (this.loopCounter == 3) {
                        this.loopCounter = 0;
                    } else {
                        this.loopCounter++; 
                    }
                } 
            } 
        }
    }
    /** 
    * Moves the character left.
    */
    public void moveLeft() {
        this.x -= 32; 
        this.left = true;
        this.right = false;
        this.up = false; 
        this.down = false;
    }
    /** 
    Moves the character upwards.
    */
    public void moveUp() {
        this.y -=32; 
        this.up = true; 
        this.down = false; 
        this.left = false; 
        this.right = false;
    }
    /** 
    Moves the character right.
    */
    public void moveRight() {
        this.x += 32; 
        this.right = true;
        this.down = false;
        this.up = false;
        this.left = false;

    }
    /** 
    Moves the character down.
    */
    public void moveDown() {
        this.y += 32;
        this.down = true;
        this.up = false;
        this.left = false;
        this.right = false;
    }
    /** 
    Deducts a life from the character.
    */
    public void kill() {
        this.lives--; 
    }
    /** 
    Returns the number of lives a character has left.
    * @return number of lives the character has
     */
    public int livesLeft() {
        return this.lives; 
    }
    /** 
    Returns the starting x-coordinate of the character.
    * @return starting x-coordinate
     */
    public int getStartingX(){
        return this.startingX; 
    }
    /** 
    Returns the starting y-coordinate of the character.
    * @return starting y-coordinate
    */
    public int getStartingY() {
        return this.startingY; 
    }
    /** 
    Sets the starting x-coordinate of the character.
    * @param x new x-coordinate
     */
    public void setStartingX(int x) {
        this.startingX = x; 
    }
    /** 
    Sets the starting y-coordinate of the character.
    * @param y new y-coordinate
     */
    public void setStartingY(int y) {
        this.startingY = y;
    }
    /** 
    Returns true the character is currently facing upwards.
    * @return whether the character is facing up
    */
    public boolean isUp() {
        return this.up; 
    }
    /** 
    Returns true if the character is currently facing downwards.
    * @return whether the character is facing down
     */
    public boolean isDown() {
        return this.down; 
    }
    /** 
    Returns true if the character is currently facing left.
    * @return whether the character is facing left
    */
    public boolean isLeft() {
        return this.left;
    }
    /** 
    Returns true if the character is currently facing right.
    * @return whether the character is facing right */
    public boolean isRight() {
        return this.right;
    }
    /** 
    Returns the loop counter of the character, representing the current stage they are at in their animation cycle.
    * @return the loopcounter of the character */
    public int getLoopCounter() {
        return this.loopCounter; 
    }
    /** 
    Abstract method to be implemented by subclasses representing when the character is reset to its initial state.
     */
    public abstract void reset();

}