package demolition; 

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;


import java.util.ArrayList; 

import processing.core.PImage;
import processing.core.PApplet;

/** 
Represents a map that can be loaded into a level.
*/
public class Map {
    /** Each map has a filepath */
    private String filepath; 

    /** Each map has a multi-dimensional array of Tiles representing the grid. */
    private Tile[][] map; 
   
    /** Maps control the sprites of empty tiles so they can be initialised */
    private PImage emptySprite; 

    /** Maps control the sprites of solid tiles so they can be initialised */
    private PImage solidSprite; 

    /** Maps control the sprites of broken tiles so they can be initialised */
    private PImage brokenSprite; 

    /** Maps control the sprites of goal tiles so they can be initialised */
    private PImage goalSprite; 
    
    /** 
    * Constructs a map object. Maps require a filepath, as well as sprites for its solid, broken, goal and empty tiles.
    * A new multi-dimensional array for the tiles is also intialised. 
    * @param filepath the path which the map is read in from
    * @param solidSprite the sprite of the solid tiles in the map
    * @param brokenSprite the sprite of the broken tiles in the map
    * @param goalSprite the sprite of the goal tiles in the map
    * @param emptySprite the sprite of the empty tiles in the map
    */
    public Map(String filepath, PImage solidSprite, PImage brokenSprite, PImage goalSprite, PImage emptySprite) {
        this.filepath = filepath; 
        this.map = new Tile[13][15]; 
        this.emptySprite = emptySprite; 
        this.solidSprite = solidSprite; 
        this.brokenSprite = brokenSprite; 
        this.goalSprite = goalSprite; 
        
    }
    /** 
    * Reads in the map layout from the given filepath. The layout of the map is determined by different characters represented in the filepath. 
    * @throws NoSuchElementException if there is no goal tile in the map, and is therefore an invalid map */
    public void setUp() throws NoSuchElementException{
        File f = new File(filepath);
        try {
            Scanner scan = new Scanner(f); 
            for (int i = 0; i < 13; i++) {
                String line = scan.nextLine();
                for (int k = 0; k < 15; k++) {
                    char letter = line.charAt(k); 
                    Tile tile = null; 
                    if (letter == 'W'){
                        // the x and y coordinates are equal to the position in the array multiplied by 32. 
                        // for the y-coordinate, we must account for the offset of 64 units for the buffer bar at the top of the screen.
                        tile = new SolidWall(k*32, (i+2)*32, this.solidSprite); 
                    }
                    else if (letter == 'B') {
                        tile = new BrokenWall(k*32, (i+2)*32, this.brokenSprite);     
                    }
                    else if (letter == ' ' || letter ==  'Y' || letter == 'R' || letter == 'P') {
                        tile = new Empty(k*32, (i+2)*32, this.emptySprite); 
                    }
                    else if (letter == 'G') {
                        tile = new Goal(k*32, (i+2)*32, this.goalSprite); 
                
                    }
                    this.map[i][k] = tile;  
                }
            }
        } catch (FileNotFoundException e) {
            return; 
        }
        // if the goal tile is not present in the map, it is invalid. 
        boolean goalInMap = false;
            boolean playerInMap = false;
            for (int i = 0 ; i < 13; i++) {
                for (int k = 0; k < 15; k++) {
                    if (this.map[i][k] instanceof Goal) {
                        goalInMap = true;
                    }
                }    
            }   
            if (goalInMap == false) {
                throw new NoSuchElementException(); 
            }
    }
    /** 
    * Draws the map to screen
    * @param app the application window which the map is drawn into 
    */
    public void draw(PApplet app) {    
        for (int i = 0; i < 13; i++) {
            for (int k = 0; k < 15; k++) {
                Tile tile = this.map[i][k]; 
                tile.draw(app);  
            }
        }
    }
    /** 
    * Returns the multi-dimensional array of the map's tiles. 
    * @return the multi-dimensional tile array of the map.
    */
    public Tile[][] getTiles(){
        return this.map; 
    }
    /**
    * Converts the tile at a specific index in the multi-dimensional array to an empty tile
    * @param x the index of the inner array that will be converted to empty, representing the horizontal position in the map
    * @param y the index of the outer array that will be converted to empty, representing the vertical position in the map */
    public void makeEmpty(int x, int y) {
        Empty empty = new Empty(x*32, (y+2)*32, this.emptySprite); 
        this.map[y][x] = empty; 
    }
    /** 
    * Returns the filepath of the map.
    * @return the String of the filepath of the map */
    public String getFilepath() {
        return this.filepath; 
    }

}