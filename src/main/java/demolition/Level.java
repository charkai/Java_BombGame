package demolition; 

import java.util.ArrayList; 
import processing.core.PImage; 

/** 
Represents a level that the player must progress through.
*/

public class Level {
    /** Levels have a map */
    private Map map; 
    /** A player exists in the level */
    private BombGuy player; 
    /** Each level has an ArrayList of yellow enemies that exist within the level*/
    private ArrayList<YellowEnemy> yellowEnemies; 
    /** Each level has an ArrayList of red enemies that exist within the level*/
    private ArrayList<RedEnemy> redEnemies;

    /** The level has an ArrayList of bombs that exist within the level */
    private ArrayList<Bomb> bombs; 

    /** The level controls the ticking sprites of the bomb so that bombs can be placed within the level */
    private PImage[] bombSprites; 
    /** The level controls the explosion sprites of the bomb so that bombs can be placed within the level */
    private PImage[] explosionSprites; 

    /** The level has an associated filename which it retrieves the map from */
    private String levelPath; 

    /** The level controls the solid wall sprite so that a map can be initialised by the level*/
    private PImage solid; 
    /** The level controls the broken wall sprite so that a map can be initialised by the level*/
    private PImage broken; 
    /** The level controls the goal tile sprite so that a map can be initialised by the level*/
    private PImage goal; 
    /** The level controls the empty tile sprite so that a map can be initialised by the level*/
    private PImage empty; 

    /** The level is either active (and therefore being played), or it is not active */
    private boolean active; 

    /** The level has an intial time limit */
    private int initialTimer;

    /** The level also has a timer which decreases every second */
    private int timer;

    
    /** 
    * Constructs a level. A level requires a filepath, an array of sprites of the bomb and an array of sprites for bomb explosions, sprites for the solid, broke, goal and empty tile types, and a timer.
    * A map is initialised utilising the tile sprites and the filepath, and this map is read in. The level is initialised as active.
    * ArrayLists for enemies and bombs are initialised. 
    * @param levelPath the filepath that the level's map is drawn from
    * @param bombSprites the sprites of the bombs in the level
    * @param explosionSprites the sprites of the bomb's explosion
    * @param solid the sprite for the solid tiles in the level
    * @param broken the sprite for the broken tiles in the level
    * @param goal the sprite for the goal tiles in the level
    * @param empty the sprite for the empty tiles in the level
    * @param timer the amount of time a player has to complete the level
    */
    public Level(String levelPath, PImage[] bombSprites, PImage[] explosionSprites, PImage solid, PImage broken, PImage goal, PImage empty, int timer) {
        this.levelPath = levelPath; 
        this.solid = solid;
        this.broken = broken; 
        this.empty = empty; 
        this.goal = goal; 
        this.initialTimer = timer;
        this.timer = timer; 
        

        this.map = new Map(levelPath, solid, broken, empty, goal); 
        this.map.setUp(); 
        this.bombs = new ArrayList<Bomb>(); 
        this.yellowEnemies = new ArrayList<YellowEnemy>(); 
        this.redEnemies = new ArrayList<RedEnemy>(); 
        this.bombSprites = bombSprites; 
        this.explosionSprites = explosionSprites;
        this.active = true; 

    }
    /** 
    * Sets the player of the level.
    * @param player the BombGuy who will traverse through the level
    */
    public void setPlayer(BombGuy player) {
        this.player = player;
    }
    /** 
    *Decreases the timer by one second
     */
    public void timeDown() {
        this.timer--; 
    }
    /** 
    * Returns the current state of the level's timer, representing the time remaining in the level.
    *@return the time remaining
     */
    public int getTimer() {
        return this.timer;
    }
    /** 
    * Sets the yellow enemies in the level.
    * @param enemies the ArrayList of yellow enemies that will be assigned to the level */
    public void setYellowEnemies(ArrayList<YellowEnemy> enemies) {
        this.yellowEnemies = enemies; 
    }
    /** 
    * Retrives the yellow enemies in the level.
    *@return the ArrayList of yellow enemies existing within the level */
    public ArrayList<YellowEnemy> getYellowEnemies() {
        return this.yellowEnemies;
    }
    /** 
    * Sets the red enemies in the level.
    * @param enemies the ArrayList of red enemies that will be assigned to the level */
    public void setRedEnemies(ArrayList<RedEnemy> enemies) {
        this.redEnemies = enemies; 
    }
    /** 
    * Retrives the red enemies in the level.
    *@return the ArrayList of red enemies existing within the level */
    public ArrayList<RedEnemy> getRedEnemies() {
        return this.redEnemies; 
    }
    /**
    * Retrives the player of the level.
    *@return the BombGuy who is playing the level
     */
    public BombGuy getPlayer(){
        return this.player; 
    }
    /** 
    *Retrieves the map of the level.
    *@return the level's map
     */
    public Map getMap() {
        return this.map; 
    }
    /** 
    * Retrieves the bombs in the level.
    * @return an ArrayList of the bombs in the level.
     */
    public ArrayList<Bomb> getBombs(){
        return this.bombs; 
    }
    /**
    * Returns true if the level is still active.
    * @return whether the level is still active. 
    */
    public boolean isActive() {
        return this.active; 
    }
    /**
    * Enacts movement of the player depending on keys pressed by the user. The player is not allowed to move through solid walls. 
    * Checks to see if the player has finished the level by stepping on the goal tile, in which case the level is no longer active. 
    * If the player collides with an enemy, the player loses a life and the level is reset. 
    * If the player walks into the radius of an explosion, the player loses a life and the level is reset. 
    * @param action the action of the player as determined by the user's keypress */

    public void playerAction(int action){

        // As the map and tile information is stored in an array, the player's coordinates must be converted to a position in the array
        // This means dividing by 32, as this was how the coordinates were initialised
        // For the y-coordinates, the map has a 64pixel wide buffer at the top, so the coordinates are offset by 2 units. 

        int xInArray = this.player.getX()/32; 
        int yInArray = this.player.getY()/32 -2; 

        // the tile in the desired direction is checked - only moves if there is no barrier.

        if (action == 1) {
            // 1 is passed to this function if the user pressed the up arrow
            yInArray--; 
            Tile tile = this.map.getTiles()[yInArray][xInArray];
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                return; 
            }
            else {
                this.player.moveUp(); 
                if (tile instanceof Goal) {
                    this.active = false; 
                }
            }
        }
        else if (action == -1){
            // -1 is passed to this function if the use pressed the down arrow 
            yInArray++; 
            Tile tile = this.map.getTiles()[yInArray][xInArray];
    
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                return; 
            }
            else {
                this.player.moveDown(); 
                if (tile instanceof Goal) {
                    this.active = false; 
                }
            }
        }
        else if (action == 2) {
            // 2 is passed to this function if the user pressed the right arrow 
            xInArray++; 
            Tile tile = this.map.getTiles()[yInArray][xInArray];
    
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                return; 
            }
            else {
                this.player.moveRight();
                if (tile instanceof Goal) {
                    this.active = false; 
                }
                
            }
        } else if (action == -2) {
            // -2 is passed to this function if the user pressed the left arrow  
            xInArray--; 
            Tile tile = this.map.getTiles()[yInArray][xInArray];
    
            if (tile instanceof BrokenWall || tile instanceof SolidWall) {
                return; 
            }
            else {
                this.player.moveLeft(); 
                if (tile instanceof Goal) {
                    this.active = false; 
                }
            }
        } else if (action == 0) {
            // 0 is passed to this function if the user pressed the space bar
            Bomb bomb = new Bomb(this.player.getX(), this.player.getY(), this.bombSprites, this.explosionSprites); 
            this.bombs.add(bomb); 
        }
        if (checkPlayerCollidesWithEnemy(this.getYellowEnemies(), this.getRedEnemies())) {
            this.player.kill(); 
            this.reset(); 
        }
        
        // ensure that the player is killed if they walk into the explosion radius of a bomb while it is exploding 
        for (int i = 0; i < this.bombs.size(); i++) {
            if (bombs.get(i).checkCharacterCaughtInExplosion(this.player) && bombs.get(i).isExploding()) {
                this.player.kill(); 
                this.reset();
            }
        }
    }
    /** 
    Resets the status of the level. Returrns the timer to its initial time, resets the map, resets the players in the map and resets the player.*/
    public void reset(){
        this.timer = this.initialTimer;
        this.map.setUp();
        for (int i = 0; i < this.yellowEnemies.size(); i++){
            this.yellowEnemies.get(i).reset();
        }
        for (int i = 0; i < this.redEnemies.size(); i++) {
            this.redEnemies.get(i).reset(); 
        }
        
        this.player.reset();
        
    } 
    /** 
    * Checks whether the player collides with any of the enemies present in the map. 
    * Returns true if the player collides with any enemies
    * @param yellows the yellow enemy to check with which the player has collided
    * @param reds the red enemy to check with which the player has collided
    * @return whether the player has collided with any enemies
     */
    public boolean checkPlayerCollidesWithEnemy(ArrayList<YellowEnemy> yellows, ArrayList<RedEnemy> reds) {
        for (int i = 0; i < yellows.size(); i++) {
            if (this.player.getX() == yellows.get(i).getX() && this.player.getY() == yellows.get(i).getY() && yellows.get(i).livesLeft() > 0) {
                return true;  
            }
        }
        for (int i = 0; i < reds.size(); i++) {
            if (this.player.getX() == reds.get(i).getX() && this.player.getY() == reds.get(i).getY() && reds.get(i).livesLeft() > 0) {
                return true; 
            }
        }
        return false; 
    }
}