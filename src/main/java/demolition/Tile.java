package demolition; 

import processing.core.PApplet; 
import processing.core.PImage; 

/** 
* Abstract class representing the game tiles on the map.
*/

public abstract class Tile extends GameEntity{
    /** 
    * Each tile has a sprite and inherits an x and a y coordinate from the GameEntity parent class.
    */
    protected PImage sprite; 

    /** 
    * Constructs a Tile. 
    * Tiles require an x position, y position, and a sprite. 
    * @param x x-coordinate of the tile
    * @param y y-coordinate of the tile
    * @param sprite sprite of the tile
     */
    public Tile(int x, int y, PImage sprite) {
        super(x, y);
        this.sprite = sprite;
    }
    /** 
    * Draws the tile to screen in an application window 
    * @param app application which will display the window
    */
    public void draw(PApplet app) {
        app.image(this.getSprite(), this.getX(), this.getY()); 
    }
    /** 
    * Returns the sprite of the tile 
    * @return the PImage of the sprite 
    */
    public PImage getSprite() {
        return this.sprite;
    }
}