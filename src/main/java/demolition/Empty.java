package demolition; 

import processing.core.PApplet;
import processing.core.PImage;

/** 
 * Represents an Empty tile in the map. 
 */
public class Empty extends Tile{
    /**
    * Constructs a new Empty tile. 
    * Empty tiles require an x position, a y position, and a sprite.
    * @param x x-coordinate of the tile
    * @param y y-coordinate of the tile
    * @param sprite sprite of the tile
    */
    public Empty(int x, int y, PImage sprite) {
        super(x, y, sprite); 
    }
}