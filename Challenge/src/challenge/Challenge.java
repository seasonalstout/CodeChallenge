package challenge;

import java.awt.Canvas;
import java.awt.Dimension;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Jeremy Jarvis
 */
public class Challenge extends Canvas {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
		JPanel panel = new ChallengePanel();
		//Only using half possible bytes to create some uniformity
		//Java bytes are 8 bit signed (-127 to 127) parses nicely to 256 colour RGB
		byte[] all = new byte[128]; 
		byte startValue = Byte.MIN_VALUE;
		Util util = new Util();

		//Iterate over all possible bytes
        for(int i = 0; i < all.length; i++) {
				all[i] = startValue++;
        }
		
		//Randomize the array for fun
		util.shuffleArray(all);
		
		//Grab 32 unique bytes
		byte[] possible = Arrays.copyOfRange(all,0, 32);
		
		// Possible permutations 32^3 = 32,768 unique colours
		ChallengePanel.colors = util.PermutationColours(possible, 3);
		
        panel.setPreferredSize(new Dimension(ChallengePanel.WIDTH, ChallengePanel.HEIGHT));
		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true);
    }
}