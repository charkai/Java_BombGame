package demolition; 

import processing.core.PImage;
import processing.core.PApplet;

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

import java.util.NoSuchElementException;

/** 
Represents the BombGuy, the player which the user controls.
*/

public class BombGuy extends Character {

    /** 
    * Constructs A BombGuy. BombGuys require an x and y coordinate, an array of upwards, downwards, left and right sprites, and a number of lives.
    * @param x x-coordinate
    * @param y y-coordinate
    * @param spriteUp array of upwards sprites
    * @param spriteDown array of downwards sprites
    * @param spriteLeft array of left sprites
    * @param spriteRight array of right sprites
    * @param lives number of lives
    */ 
    public BombGuy(int x, int y, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight, int lives) {
        super(x, y, spriteUp, spriteDown, spriteLeft, spriteRight, lives);
    
    }
    /**
    * Dictates the behaviour of the BombGuy. If the BombGuy is still alive, it is drawn to screen.
    * @param app the application which the BombGuy is displayed in
     */
    public void tick(PApplet app) {
        if (this.lives > 0) {
            this.draw(app); 
        }
    }
    /** 
    * Reads the starting coordinates of the BombGuy from a map file. Scans for an instance of the letter 'P' and will set the coordinates to this position.
    * @param filepath the path of the mapfile which will be read from
    * @throws NoSuchElementException if there is no player icon in the file (the map is thus invalid)
    * @return an integer array representing the coordinates of the player in the map
     */
    public int[] startingCoords(String filepath) throws NoSuchElementException{
        File f = new File(filepath); 
        int[] coords = new int[2]; 
        try {
            Scanner scan = new Scanner(f);                
            for (int i = 0; i < 13; i++) {
                String line = scan.nextLine(); 
                for (int k = 0; k < 15; k++) {
                    char letter = line.charAt(k); 
                    if (letter == 'P') {
                        int startingX = k*32; 
                        int startingY = (i+2)*32; 
                        
                        coords[0] = startingX;
                        coords[1] = startingY; 
                        this.startingX = startingX;
                        this.startingY = startingY; 
                    }
                }
            }
        }
        catch (FileNotFoundException e){
             return null; 
        }
        if (coords[1] == 0) {
            // Due to the layout of the map, the player cannot have a ycoordinate of 0 as this would be in the buffer bar at the top of the screen
            // If the coordinate is 0 this indicates that no player icon was found in the file, therefore it is an invalid map file
            throw new NoSuchElementException(); 
        }
        return coords; 
    }
    /** 
    Resets the status of the player. 
    * Sets the x-coordinate to its starting x-coordinate, the y-coordinate to its starting y-coordinate and sets the player facing downwards.
    */
    public void reset() {
        this.x = this.startingX; 
        this.y = this.startingY; 
        this.down = true; 
        this.up = false; 
        this.left = false; 
        this.right = false;
    }
}