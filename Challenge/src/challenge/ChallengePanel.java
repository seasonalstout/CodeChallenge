package challenge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.JPanel;

public class ChallengePanel extends JPanel {
	//Image size of 256 x 128 with 32 x 32 tiles means 8 x 4 tiles
    public static final int WIDTH = 256;  
    public static final int HEIGHT = 128;
	
	private static final int TILE_WIDTH = 32;
	private static final int TILE_HEIGHT = 32;

	public static Set<String> colors = new HashSet();
	Iterator<String> colorIterator;
	
    byte[] base = new byte[256];

	enum Direction {
        Right,
        Up,
        Left,
        Down
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics); 
		colorIterator = colors.iterator();

		//Number of tiles X and Y as  total size / tile size
		int tilesX = WIDTH / TILE_WIDTH;
		int tilesY = HEIGHT / TILE_HEIGHT;
		
		//8 by 4 tiles (32x32) total size 256 x 128
		//Grouping TILE_WIDTH
		for (int currentY = 0; currentY < tilesY ; currentY++)
		{
			//Grouping TILE_HEIGHT
			for (int currentX = 0; currentX < tilesX ; currentX++) 
			{
				Point startPosition = new Point(currentX *TILE_WIDTH + TILE_WIDTH/2, 
												currentY *TILE_HEIGHT + TILE_HEIGHT/2);
				
				Direction d = Direction.Right;
				int currentSegmentLength = 1;
		
				//Iterate over all pixels in a tile. Draw spiraling outwards
				for (int i = 0; i < TILE_WIDTH; i ++) {
					drawLine(graphics, startPosition, d, currentSegmentLength);
					d = changeDirection(d);
					if (i!= TILE_WIDTH-1) //Spiral contains 1 extra due to even number of pixels, abort on final line.
						drawLine(graphics, startPosition, d, currentSegmentLength);
					d = changeDirection(d);

					currentSegmentLength++;
				}
			}		
		}
    }

	private void drawLine(Graphics graphics, Point currentLocation, Direction facing, int currentLength) {

        for (int i = 0; i < currentLength; i++) {
			drawPixel(graphics, currentLocation);

            switch (facing) {
				case Right: currentLocation.x++; break;
				case Up:    currentLocation.y--; break;
				case Left:  currentLocation.x--; break;
				case Down:  currentLocation.y++; break;
            }
        }
    }
	
	private void drawPixel(Graphics g, Point p) {
		if (colorIterator.hasNext())
			g.setColor(Color.decode("#" + colorIterator.next()));
        g.drawLine(p.x, p.y, p.x, p.y);
	}

    private Direction changeDirection(Direction d) {
        switch (d) {
			case Right: 
				return Direction.Up;
			case Up:    
				return Direction.Left;
			case Left:  
				return Direction.Down;
			default:  
				return Direction.Right;
        }
    }
}