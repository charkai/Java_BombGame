package demolition; 

import processing.core.PApplet;
import processing.core.PImage;

/**
* Represents a Solid wall in the map. 
 */
public class SolidWall extends Tile{

     /**
    * Constructs a new Solid wall. 
    * Solid Walls require an x position, a y position, and a sprite.
    * @param x x-coordinateof the tile
    * @param y y-coordinate of the tile
    * @param sprite sprite of the tile
    */
    public SolidWall(int x, int y, PImage sprite) {
        super(x, y, sprite); 
    }
}