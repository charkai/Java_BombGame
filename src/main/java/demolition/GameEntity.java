package demolition;


/** 
* Abstract class representing all entities in the map.
*/
public abstract class GameEntity {

    /**  All entities have an x-coordinate. */
    protected int x;

    /** All entities have a y-coordinate. */ 
    protected int y; 

    /** Constructor for each game entity. 
    * Entites require an x-coordinate and a y-coordinate.
    * @param x x-coordinate
    * @param y y-coordinate
    */
    public GameEntity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /** 
    * Sets the x-coordinate of the entity.
    * @param x the x-coordinate to be set
    */
    public void setX(int x) {
        this.x = x;
    }

    /** 
    * Sets the y-coordinate of the entity.
    * @param y the y-coordinate to be set
     */

    public void setY(int y) {
        this.y = y;
    }
    /** 
    * Returns the x-coordinate of the entity.
    * @return x-coordinate of entity
     */
    public int getX() {
        return this.x;
    }
    /** 
    * Returns the y-coordinate of the entity. 
    * @return y-coordinate of the entity
    */
    public int getY() {
        return this.y;
    }
}
