package demolition; 

import processing.core.PApplet;
import processing.core.PImage;

/**
* Represents a Goal tile in the map. 
 */
public class Goal extends Tile{

    /**
    * Constructs a new Goal tile. 
    * Goal tiles require an x position, a y position, and a sprite.
    * @param x x-coordinate of the tile
    * @param y y-coordinate of the tile
    * @param sprite sprite of the tile
    */
    public Goal(int x, int y, PImage sprite) {
        super(x, y, sprite); 
    }
}