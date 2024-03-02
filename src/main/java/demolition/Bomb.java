package demolition; 

import processing.core.PImage;
import processing.core.PApplet;

/** 
* Represents a bomb that can be placed by the BombGuy.
*/

public class Bomb extends GameEntity{

    /** 
    * Bombs have an array of sprites while they are unexploded.
    */
    private PImage[] sprites; 
    /** 
    * Bombs have an array of explosion sprites.
    */
    private PImage[] explosions;

    /** 
    * At any one time, the bomb has a current sprite which it cycles through for animation. 
    */
    private PImage currentSprite;

    /** 
    * Bombs have a tick-counter, which is a countdown until explosion. 
    */
    private int tickCounter;
    /** 
    * Bombs have a countdown for how long they are exploding. 
    */
    private int explodeCounter; 

    /** 
    * Bombs can be active or inactive. 
    */
    private boolean active;  

    /** 
    * Bombs can either be ticking or not ticking.  
    */
    private boolean ticking; 

    /** 
    * Bombs can either be exploding or not exploding. 
    */
    private boolean exploding; 

    /** 
    * Bombs have a left explosion radius. 
    */
    private int left; 

    /** 
    * Bombs have a right explosion radius. 
    */
    private int right; 

    /** 
    * Bombs have an upwards explosion radius. 
    */
    private int up; 

    /** 
    * Bombs have a downwards explosion radius. 
    */
    private int down;

    /**
    * Constructor for a bomb. 
    * Bombs require an x-coordinate, a y-coordinate, an array of unexploded sprites and an array of explosion sprites.
    * @param x x-coordinate of the bomb
    * @param y y-coordinate of the bomb
    * @param sprites unexploded sprites
    * @param explosions explosion sprites
    */
    public Bomb(int x, int y, PImage[] sprites, PImage[] explosions) {
        super(x, y);
        this.sprites = sprites;
        this.currentSprite = this.sprites[0]; 

        this.explosions = explosions;

        this.tickCounter = 0; 

        this.explodeCounter = 0; 
        this.ticking = true; 
        this.exploding = false; 
        this.active = true;   
    }
    /** 
    * Dictates the activity of the bomb depending on its current state.
    * If the bomb is active, it will be drawn to screen.
    * If the bomb reaches the end of its ticking cycle, it will explode and its explosion will be drawn to screen.
    * @param app the application window which the bomb is drawn to
    * @param map the map within which the bomb is placed
    * @param player the player who drops the bomb
    * @param level the level within which the bomb is placed
    */
    public void tick(PApplet app, Map map, BombGuy player, Level level) {
        if (this.isActive()) {
            this.draw(app); 
            if (this.isTicking() == false) {
                if (explodeCounter == 0) {
                    this.explode(map, player, level);
                this.explodeCounter++; 
                } 
            }
        }
    }
    /**
    * Clears the bomb's activity, setting its active, ticking and exploding status to false.
    */ 
    public void clear() {
        this.active = false; 
        this.ticking = false;
        this.exploding = false; 
    }
    /** 
    * Returns true if the bomb is ticking.
    * @return whether the bomb is ticking
     */
    public boolean isTicking() {
        return this.ticking;
    }

    /** 
    * Returns true if the bomb is active.
    * @return whether the bomb is active
     */

    public boolean isActive() {
        return this.active;  
    }
    /** 
    * Returns true if the bomb is exploding.
    * @return whether the bomb is exploding
     */
    public boolean isExploding() {
        return this.exploding; 
    }

    /** 
    * Sets the upwards radius of the bomb's explosions.
    * @param up the upwards radius of the bomb's explosion
    */
    public void setUp(int up){
        this.up = up; 
    }

    /** 
    * Sets the downwards radius of the bomb's explosion.
    * @param down the downwards radius of the bomb's explosion
    */

    public void setDown(int down) {
        this.down = down;
    }

    /** 
    * Sets the left radius of the bomb's explosion.
    * @param left the left radius of the bomb's explosion
    */
    public void setLeft(int left) {
        this.left = left; 
    }

    /** 
    * Sets the right radius of the bomb's explosion.
    * @param right the right radius of the bomb's explosion
    */
    public void setRight(int right) {
        this.right = right; 
    }

    /** 
    * Returns the upwards radius of the bomb's explosion.
    * @return the upwards radius of the bomb's explosion
    */
    public int getUp(){
        return this.up; 
    }

    /** 
    * Returns the downwards radius of the bomb's explosion.
    * @return the downwards radius of the bomb's explosion
    */
    public int getDown(){
        return this.down; 
    }

    /** 
    * Returns the left radius of the bomb's explosion.
    * @return the upwards radius of the bomb's explosion
    */

    public int getLeft(){
        return this.left;
    }

    /** 
    * Returns the right radius of the bomb's explosion.
    * @return the right radius of the bomb's explosion
    */
    public int getRight(){
        return this.right; 
    }

    /** 
    * Returns the tick counter of the bomb, to indicate time before explosion.
    * @return the bomb's tick counter
    */
    public int getTickCounter() {
        return this.tickCounter; 
    }

    /** 
    * Draws the bomb sprite to the screen, depending on whether it is ticking or exploding. 
    * If ticking, the bomb will cycle through it's unexploded sprites until it explodes, creating the animation cycle. These sprites last for 15 frames each, or 0.25 seconds.
    * If exploding, the bomb will draw its explosion sprites depending on its explosion radius. This explosion remains on screen for 30 frames, or 0.5 seconds.
    * @param app the application window that the bomb will be drawn onto
     */
    public void draw(PApplet app) {
        if (this.isTicking()) {
            app.image(this.currentSprite, this.x, this.y);
            if (app.frameCount % 15 == 0) {
                this.currentSprite = this.sprites[tickCounter];                 
                if (this.tickCounter == 7) {
                    this.ticking = false; 
                    this.exploding = true; 
                } else {
                    this.tickCounter++;   
                }         
            }
        } else if (this.isExploding()){
            if (app.frameCount % 30 != 0) {            
                app.image(this.explosions[0], this.x, this.y); 
                for (int i = 0; i < this.right; i++) {
                    app.image(this.explosions[1], this.x+((i+1)*32), this.y); 
                }
                for (int i = 0; i < this.left; i++) {
                    app.image(this.explosions[1], this.x-((i+1)*32), this.y); 
                }
                for (int i = 0; i < this.down; i++) {
                    app.image(this.explosions[2], this.x, this.y+((i+1)*32)); 
                }
                for (int i = 0; i < this.up; i++) {
                    app.image(this.explosions[2], this.x, this.y-((i+1)*32)); 
                }
            }
            else {
                this.exploding = false; 
            }
        }
    }
    /** 
    * Explodes the bomb.
    * The explosion radius is determined by the presence of solid or broken walls in the path of the explosion. 
    * If there is a solid wall in the path of the explosion, the explosion will be stopped by the wall. 
    * If there is a broken wall in the path of the explosion, the explosion will blast through the wall, destroying it, and stop after this point. 
    * If the player is caught in the explosion, they lose a life and the level is reset. 
    * If any of the enemies in the level are caught in the explosion, they are killed and removed from the level. 
    * @param map the map which the bomb is exploding in
    * @param player the player who drops the bomb
    * @param level the level the bomb is exploding in
    */
    public void explode(Map map, BombGuy player, Level level) {
        
        // As the map and tile information is stored in an array, the bomb's coordinates must be converted to a position in the array
        // This means dividing by 32, as this was how the coordinates were initialised
        // For the y-coordinates, the map has a 64pixel wide buffer at the top, so the coordinates are offset by 2 units. 
        int xInArray = this.x/32; 
        int yInArray = this.y/32 - 2; 

        this.up = 0; 
        this.down = 0; 
        this.left = 0; 
        this.right = 0; 
        
        for (int i = 0; i < 2; i++) {
            // check the tiles upwards in the map
            Tile tile = map.getTiles()[yInArray-(i+1)][xInArray];
            if (tile instanceof SolidWall) {
                break;
            } else if (tile instanceof BrokenWall) {
                map.makeEmpty(xInArray, yInArray-(i+1));
                this.up++; 
                break;
            } 
            else {
                this.up++; 
            }
        }
        for (int i = 0; i < 2; i++) {
            // check the tiles downwards in the map
            Tile tile = map.getTiles()[yInArray+(i+1)][xInArray];
            if (tile instanceof SolidWall) {
                break;
            } else if (tile instanceof BrokenWall) {
                map.makeEmpty(xInArray, yInArray+(i+1)); 
                this.down++; 
                break; 
            }
            else {
                this.down++; 
            }
        }
        for (int i = 0; i < 2; i++) {
            // check the tiles leftwards in the map
            Tile tile = map.getTiles()[yInArray][xInArray-(i+1)];
            if (tile instanceof SolidWall) {
                break;
            } 
            else if (tile instanceof BrokenWall) {
     
                map.makeEmpty(xInArray-(i+1), yInArray);
                this.left++; 
                break; 
            }
            this.left++; 
           
        }
        for (int i = 0; i < 2; i++) {
            // check the tiles rightwards in the map
            Tile tile = map.getTiles()[yInArray][xInArray+(i+1)];
            if (tile instanceof SolidWall) {
                break;
            } 
            else if (tile instanceof BrokenWall) {
                map.makeEmpty(xInArray+(i+1), yInArray); 
                this.right++; 
                break; 
            }
            else {
                this.right++; 
            }    
        }
        for (int i = 0; i < level.getYellowEnemies().size(); i++) {
            if (this.checkCharacterCaughtInExplosion(level.getYellowEnemies().get(i))) {
                level.getYellowEnemies().get(i).kill(); 
            }
        }
        for (int i = 0; i < level.getRedEnemies().size(); i++) {
            if (this.checkCharacterCaughtInExplosion(level.getRedEnemies().get(i))) {
                level.getRedEnemies().get(i).kill(); 
            }
        }
        if (this.checkCharacterCaughtInExplosion(player)) {
            player.kill();
            level.reset(); 
        }  
    }   
    /** 
    * Returns true if the given character will be caught in the radius of the bomb's explosion.
    * @param character the character who will be checked whether they are caught
    * @return whether the given character is caught in the explosion
     */
    public boolean checkCharacterCaughtInExplosion(Character character) {
    
        int centreX = this.getX(); 
        int centreY = this.getY();

        if (character.getX() == centreX && character.getY() == centreY) {
            return true; 
        } 
        for (int i = 0; i < this.up; i++) {
            if (character.getX() == centreX && character.getY() == centreY-(i+1)*32) {
                return true; 
            }
        }
        for (int i = 0; i < this.down; i++) {
            if (character.getX() == centreX && character.getY() == centreY+(i+1)*32) {
                return true; 
            }
        }
        for (int i = 0; i < this.left; i++) {
            if (character.getX() == centreX-(i+1)*32 && character.getY() == centreY) {
                return true; 
            }
        }
        for (int i = 0; i < this.right; i++) {
            if (character.getX() == centreX+(i+1)*32 && character.getY() == centreY) {
                return true; 
            }
        }
        return false;
    }

}