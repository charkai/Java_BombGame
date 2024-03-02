package demolition; 

import processing.core.PApplet;
import processing.core.PImage;

/** 
* Represents a Broken Wall in the map. 
*/
public class BrokenWall extends Tile{

     /**
    * Constructs a new Broken Wall. 
    * Broken Walls require an x position, a y position, and a sprite.
    * @param x x-coordinate of the tile
    * @param y y-coordinate of the tile
    * @param sprite sprite of the tile
    */
    public BrokenWall(int x, int y, PImage sprite) {
        super(x, y, sprite); 
    }
}