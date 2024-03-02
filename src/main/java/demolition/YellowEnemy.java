package demolition; 

import processing.core.PImage;
import processing.core.PApplet;

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

/** 
Represents a Yellow Enemy in the level.
 */
public class YellowEnemy extends Enemy{
    
    /** 
    * Constructs a Yellow Enemy. Yellow Enemies require an x and y coordinate, an array of upwards, downwards, left and right sprites, and a number of lives
    * @param x x-coordinate
    * @param y y-coordinate
    * @param spriteUp array of upwards sprites
    * @param spriteDown array of downwards sprites
    * @param spriteLeft array of left sprites
    * @param spriteRight array of right sprites
    * @param lives number of lives
    */
    public YellowEnemy(int x, int y, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight, int lives) {
        super(x, y, spriteUp, spriteDown, spriteLeft, spriteRight, lives);
    }

    /** 
    * Instantiates all of the YellowEnemies in a map file. The static method scans the file for instances of the letter 'Y' and will create a yellow enemy at this position.
    * @param filepath the name of the file which will be scanned for yellow enemies
    * @param spriteUp the upwards sprites to instantiate each yellow enemy
    * @param spriteDown the downwards sprites to instantiate each yellow enemy
    * @param spriteLeft the left sprites to instantiate each yellow enemy
    * @param spriteRight the right sprites to instantiate each yellow enemy
    * @return an ArrayList of all of the yellow enemies retrieved from the file
    */
    public static ArrayList<YellowEnemy> getEnemies(String filepath, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight){
        ArrayList<YellowEnemy> enemies = new ArrayList<>(); 
        File f = new File(filepath); 
        try {
            Scanner scan = new Scanner(f);
            for (int i = 0; i < 13; i++) {
                String line = scan.nextLine(); 
                for (int k = 0; k < 15; k++) {
                    char letter = line.charAt(k); 
                    if (letter == 'Y') {
                        YellowEnemy enemy = new YellowEnemy((k*32), (i+2)*32, spriteUp, spriteDown, spriteLeft, spriteRight, 1); 
                        enemy.setStartingX(k*32); 
                        enemy.setStartingY((i+2)*32); 
                        enemies.add(enemy); 
                    }
                }
            }
        }
        catch (FileNotFoundException e){
             return null; 
        }
        return enemies; 
    }
    /** 
    * Changes the direction of the yellow enemy. 
    * The yellow enemy moves in a clockwise direction, so the new direction is determined by the current direction
    */
    public void changeDirection(){
        if (this.left == true) {
            this.up = true; 
            this.down = false; 
            this.left = false;
            this.right = false; 
        }
        else if (this.right == true) {
            this.up = false;
            this.down = true; 
            this.left = false;
            this.right = false; 
        }
        else if (this.down == true) {
            this.up = false;
            this.down = false;
            this.left = true; 
            this.right = false;
        }
        else if (this.up == true) {
            this.up = false; 
            this.down = false;
            this.left = false; 
            this.right = true; 
        }
    }
}