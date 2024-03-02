package demolition;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PFont; 
import processing.data.JSONObject;
import processing.data.JSONArray; 
import java.util.ArrayList; 
/**  
* This is the Application window with which users interact to play Demolition Man.
*/
public class App extends PApplet {

    /** Application window has a width */
    public static final int WIDTH = 480;
    /** Application window has a height */
    public static final int HEIGHT = 480;
    /** Application window has a framerate */
    public static final int FPS = 60;

    /** The application has a player */
    public BombGuy player; 
    /** The application has an ArrayList of mapTitles */
    public ArrayList<String> mapTitles; 
    /** The application has an ArrayList of levels */
    public ArrayList<Level> levels;  
    /** The application has an ArrayList of timers corresponding to each level */
    public ArrayList<Integer> timers; 

    /** The application has a configuration file */
    public String config; 
    /** The application has a level which it is currently playing */
    public Level currentLevel; 
    /** The application has an integral representation of the level it is currently playing */
    public int levelCounter; 
    /** The application has a sprite for the player icon at the top of the screen */
    public PImage playerIcon; 
    /** The application has a sprite for the time icon at the top of the screen */
    public PImage timeIcon; 
    /** The application has a default font */
    public PFont font; 

    /** The application has a boolean representing whether it is being tested */
    public boolean testing; 

    /** Constructs an application. The application is initialised in a non-testing state. */
    public App() {
        this.testing = false; 
    }
    /** Sets the window size to the specified height and width. */
    public void settings() {
        size(WIDTH, HEIGHT);
    }
    /** Loads in the sprites and sets the levels, maps, framerate and player. */
    public void setup() {

        // if the config file has not been changed, then the application is not in testing state.  
        if (this.testing == false) {
            this.config = "config.json";
        }
        frameRate(FPS);

        JSONObject json = loadJSONObject(this.config); 
        JSONArray levelsArray = json.getJSONArray("levels"); 
        int lives = json.getInt("lives");

        this.mapTitles = new ArrayList<String>(); 
        this.timers = new ArrayList<Integer>(); 
        for (int i = 0; i < levelsArray.size(); i++) {
            JSONObject level = levelsArray.getJSONObject(i);
            String path = level.getString("path");
            int time = level.getInt("time");
            mapTitles.add(path); 
            timers.add(time);
        }
        this.font = this.createFont("src/main/resources/PressStart2P-Regular.ttf", 20); 
        this.playerIcon = this.loadImage("src/main/resources/icons/player.png");
        this.timeIcon = this.loadImage("src/main/resources/icons/clock.png");
        
        PImage[] playerSpriteDown = new PImage[]{this.loadImage("src/main/resources/player/player1.png"), this.loadImage("src/main/resources/player/player2.png"), this.loadImage("src/main/resources/player/player3.png"), this.loadImage("src/main/resources/player/player4.png")};
        PImage[] playerSpriteUp = new PImage[]{this.loadImage("src/main/resources/player/player_up1.png"), this.loadImage("src/main/resources/player/player_up2.png"), this.loadImage("src/main/resources/player/player_up3.png"), this.loadImage("src/main/resources/player/player_up4.png")};
        PImage[] playerSpriteLeft = new PImage[]{this.loadImage("src/main/resources/player/player_left1.png"), this.loadImage("src/main/resources/player/player_left2.png"), this.loadImage("src/main/resources/player/player_left3.png"), this.loadImage("src/main/resources/player/player_left4.png")};
        PImage[] playerSpriteRight = new PImage[]{this.loadImage("src/main/resources/player/player_right1.png"), this.loadImage("src/main/resources/player/player_right2.png"), this.loadImage("src/main/resources/player/player_right3.png"), this.loadImage("src/main/resources/player/player_right4.png")};
        
        PImage[] bombSprites = new PImage[]{this.loadImage("src/main/resources/bomb/bomb1.png"), this.loadImage("src/main/resources/bomb/bomb2.png"), this.loadImage("src/main/resources/bomb/bomb3.png"), this.loadImage("src/main/resources/bomb/bomb4.png"), this.loadImage("src/main/resources/bomb/bomb5.png"), this.loadImage("src/main/resources/bomb/bomb6.png"), this.loadImage("src/main/resources/bomb/bomb7.png"), this.loadImage("src/main/resources/bomb/bomb8.png")};
        PImage[] explosions = new PImage[]{this.loadImage("src/main/resources/explosion/centre.png"), this.loadImage("src/main/resources/explosion/horizontal.png"), this.loadImage("src/main/resources/explosion/vertical.png")};
        
        PImage[] yellowSpriteDown = new PImage[]{this.loadImage("src/main/resources/yellow_enemy/yellow_down1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_down4.png")};
        PImage[] yellowSpriteUp = new PImage[]{this.loadImage("src/main/resources/yellow_enemy/yellow_up1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_up4.png")};
        PImage[] yellowSpriteLeft = new PImage[]{this.loadImage("src/main/resources/yellow_enemy/yellow_left1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_left4.png")};
        PImage[] yellowSpriteRight = new PImage[]{this.loadImage("src/main/resources/yellow_enemy/yellow_right1.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right2.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right3.png"), this.loadImage("src/main/resources/yellow_enemy/yellow_right4.png")};
        
        PImage[] redSpriteDown = new PImage[]{this.loadImage("src/main/resources/red_enemy/red_down1.png"), this.loadImage("src/main/resources/red_enemy/red_down2.png"), this.loadImage("src/main/resources/red_enemy/red_down3.png"), this.loadImage("src/main/resources/red_enemy/red_down4.png")};
        PImage[] redSpriteUp = new PImage[]{this.loadImage("src/main/resources/red_enemy/red_up1.png"), this.loadImage("src/main/resources/red_enemy/red_up2.png"), this.loadImage("src/main/resources/red_enemy/red_up3.png"), this.loadImage("src/main/resources/red_enemy/red_up4.png")};
        PImage[] redSpriteLeft = new PImage[]{this.loadImage("src/main/resources/red_enemy/red_left1.png"), this.loadImage("src/main/resources/red_enemy/red_left2.png"), this.loadImage("src/main/resources/red_enemy/red_left3.png"), this.loadImage("src/main/resources/red_enemy/red_left4.png")};
        PImage[] redSpriteRight = new PImage[]{this.loadImage("src/main/resources/red_enemy/red_right1.png"), this.loadImage("src/main/resources/red_enemy/red_right2.png"), this.loadImage("src/main/resources/red_enemy/red_right3.png"), this.loadImage("src/main/resources/red_enemy/red_right4.png")};

        PImage solid = this.loadImage("src/main/resources/wall/solid.png");
        PImage broken = this.loadImage("src/main/resources/broken/broken.png");
        PImage goal = this.loadImage("src/main/resources/goal/goal.png");
        PImage empty = this.loadImage("src/main/resources/empty/empty.png");
        
        this.player = new BombGuy(0, 0, playerSpriteUp, playerSpriteDown, playerSpriteLeft, playerSpriteRight, lives); 
        int[] startingCoordinates = this.player.startingCoords(mapTitles.get(0)); 
        int startingX = startingCoordinates[0]; 
        int startingY = startingCoordinates[1]; 
        this.player.setX(startingX); 
        this.player.setStartingX(startingX);
        this.player.setStartingY(startingY);
        this.player.setY(startingY);
        
        this.levels = new ArrayList<>(); 
        for (int i = 0; i < mapTitles.size(); i++) { //levels are instantiated for each of the maptitles 
            Level level = new Level(mapTitles.get(i), bombSprites, explosions, solid, broken, empty, goal, timers.get(i)); 
            level.setYellowEnemies(YellowEnemy.getEnemies(mapTitles.get(i),yellowSpriteUp, yellowSpriteDown, yellowSpriteLeft, yellowSpriteRight));
            level.setRedEnemies(RedEnemy.getEnemies(mapTitles.get(i), redSpriteUp, redSpriteDown, redSpriteLeft, redSpriteRight)); 
            level.setPlayer(this.player); 
            levels.add(level); 
        }
        this.levelCounter = 0; 
        this.currentLevel = levels.get(this.levelCounter); 
    }

    /** Sets the configuration file of the application. This method is used only for testing 
    * @param filepath the path of the configuration file to be tested */
    public void setConfig(String filepath) {
        this.config = filepath;
        this.testing = true; 
    }
    /**  Draws the application to screen. If there are still levels remaining, the level is drawn to screen.
    * If the user has completed all levels, the win screen is displayed. If the user loses all of their lives or time runs out, the game over screen is displayed. */
    public void draw() {
        if (this.player.livesLeft() > 0 && this.currentLevel.getTimer() > 0 ) {
            if (currentLevel.isActive()) {
                background(239, 129, 0); 
                this.textFont(this.font);
                this.text(this.player.livesLeft(), 160, 38);
                this.text(this.currentLevel.getTimer(), 305, 38);
                this.fill(0, 0, 0);                    
                this.image(this.playerIcon, 110, 10);
                this.image(this.timeIcon, 260, 10);

                this.currentLevel.getMap().draw(this); 
                for (int i = 0; i < this.currentLevel.getBombs().size(); i++) {
                    if (this.currentLevel.getBombs().get(i).isActive()) {this.currentLevel.getBombs().get(i).tick(this, this.currentLevel.getMap(), this.currentLevel.getPlayer(), this.currentLevel);}
                }
                for (int i = 0; i < currentLevel.getYellowEnemies().size(); i++) {this.currentLevel.getYellowEnemies().get(i).tick(this, this.currentLevel);}
                for (int i = 0; i < currentLevel.getRedEnemies().size(); i++) {this.currentLevel.getRedEnemies().get(i).tick(this, this.currentLevel);}
                this.currentLevel.getPlayer().tick(this); 
                if (this.frameCount % 60 == 0) {this.currentLevel.timeDown();}
            } 
            else {
                this.levelCounter++; 
                try {
                    this.currentLevel = levels.get(levelCounter);
                    int[] startingCoords = this.player.startingCoords(mapTitles.get(levelCounter));
                    this.player.setX(startingCoords[0]);
                    this.player.setY(startingCoords[1]);
                }
                catch (IndexOutOfBoundsException e){
                    background(239, 129, 0);
                    this.textFont(this.font); 
                    this.text("YOU WIN", 160, 240);
                }
            }
        }
        else {
            background(239, 129, 0);
            this.textFont(this.font); 
            this.text("GAME OVER", 140, 240);
        }
    }
    /** Determines the action of the player based on the keys that the user presses */
    public void keyPressed() {
        if (this.keyCode == UP) {this.currentLevel.playerAction(1);} 
        else if (this.keyCode == DOWN) {this.currentLevel.playerAction(-1);} 
        else if (this.keyCode == RIGHT) {this.currentLevel.playerAction(2);}
        else if (this.keyCode == LEFT) {this.currentLevel.playerAction(-2);}
        else if (this.keyCode == ' ') {this.currentLevel.playerAction(0);}
    }  
    /** Runs the application. 
    * @param args command line arguments */
    public static void main(String[] args) {
        PApplet.main("demolition.App");
    }
}