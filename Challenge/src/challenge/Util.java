package challenge;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Util {
	
	//Convenience function to pull all permutations of single byte array.
	//Representation of colour will parse as #00ff00 as #RRGGBB 
	Set<String> PermutationColours(byte[] colorIn, int paramLength) 
	{ 
		//Using set to preserve uniqueness of final hash but not necessary.
		Set<String> colours = new HashSet();
		int n = colorIn.length;  
		PermutationColour(colours, colorIn, "", n, paramLength); 
		return colours;
	} 

	// Recursion method to populate Set of Strings with all permutations of 
	void PermutationColour(Set<String> colours, byte[] colorIn, String prefix, int total, int current) 
	{ 
	
		if (current == 0) { //If the iterator hits 0 weve grabbed all 3 values for RGB
			colours.add(prefix);
			return; 
		} 
		// Recursivly traverse the bytes and format string output via prepending
		for (int i = 0; i < total; ++i) { 
			String newPrefix = prefix + String.format("%02X", colorIn[i]);  
			PermutationColour(colours, colorIn, newPrefix, total, current - 1);  
		} 
	} 
	
	// Implementing simple Fisher–Yates shuffle
	void shuffleArray(byte[] hexColours)
	{
		Random random = new Random();
		for (int i = hexColours.length - 1; i > 0; i--)
		{
			int index = random.nextInt(i + 1);
			// Simple swap
			byte hexColor = hexColours[index];
			hexColours[index] = hexColours[i];
			hexColours[i] = hexColor;
		}
	}
}
