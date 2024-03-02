package demolition; 

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TileTest {

    @Test
    public void getSprite() {
        Empty tile = new Empty(30, 10, null); 
        assertEquals(tile.getSprite(), null); 

    }
    @Test
    public void constructSolid() {
        assertNotNull(new SolidWall(30, 10, null)); 
    }
    @Test 
    public void constructBroken() {
        assertNotNull(new BrokenWall(30, 10, null));
    }
    @Test 
    public void constructEmpty() {
        assertNotNull(new Empty(30, 10, null)); 

    }
    @Test 
    public void constructGoal() {
        assertNotNull(new Goal(30, 10, null));
    }
}
