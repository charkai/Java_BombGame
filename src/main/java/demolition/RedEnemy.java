package demolition; 

import processing.core.PImage;
import processing.core.PApplet;

import java.util.Scanner; 
import java.io.File; 
import java.io.FileNotFoundException;
import java.util.ArrayList; 

import java.util.Random;

/** 
Represents a Red Enemy in the level.
*/

public class RedEnemy extends Enemy {

    /** 
    * Constructs a Red Enemy. Red Enemies require an x and y coordinate, an array of upwards, downwards, left and right sprites, and a number of lives
    * @param x x-coordinate
    * @param y y-coordinate
    * @param spriteUp array of upwards sprites
    * @param spriteDown array of downwards sprites
    * @param spriteLeft array of left sprites
    * @param spriteRight array of right sprites
    * @param lives number of lives
    */
    public RedEnemy(int x, int y, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight, int lives) {
        super(x, y, spriteUp, spriteDown, spriteLeft, spriteRight, lives);
    }

    /** 
    * Instantiates all of the Red Enemies in a map file. The static method scans the file for instances of the letter 'R' and will create a red enemy at this position.
    * @param filepath the name of the file which will be scanned for red enemies
    * @param spriteUp the upwards sprites to instantiate each red enemy
    * @param spriteDown the downwards sprites to instantiate each red enemy
    * @param spriteLeft the left sprites to instantiate each red enemy
    * @param spriteRight the right sprites to instantiate each red enemy
    * @return an ArrayList of all of the red enemies retrieved from the file
    */
    public static ArrayList<RedEnemy> getEnemies(String filepath, PImage[] spriteUp, PImage[] spriteDown, PImage[] spriteLeft, PImage[] spriteRight){
        ArrayList<RedEnemy> enemies = new ArrayList<>(); 
        File f = new File(filepath);  
        try {
            Scanner scan = new Scanner(f);
            for (int i = 0; i < 13; i++) {
                String line = scan.nextLine(); 
                for (int k = 0; k < 15; k++) {
                    char letter = line.charAt(k); 
                    if (letter == 'R') {
                        RedEnemy enemy = new RedEnemy((k*32), (i+2)*32, spriteUp, spriteDown, spriteLeft, spriteRight, 1);
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
    * Changes the direction of the Red Enemy. 
    * The new direction of the red enemy is determined by randomisation.
    */
    public void changeDirection(){

        // A random number generator is used to determine the new direction of the enemy. 
        Random rand = new Random(); 
        int upperBound = 4; 
        int direction = rand.nextInt(upperBound); 

        // Specific numbers correspond to different directions
        if (direction == 0) {
            this.up = true; 
            this.down = false; 
            this.left = false;
            this.right = false; 
        } 
        else if (direction == 1) {
            this.up = false;
            this.down = true; 
            this.left = false;
            this.right = false; 
        }
        else if (direction == 2) {
            this.up = false;
            this.down = false;
            this.left = true; 
            this.right = false;
        }
        else if (direction == 3) {
            this.up = false; 
            this.down = false;
            this.left = false; 
            this.right = true; 
        }
    }


}