package com.lucasj.gamedev.utils;

import java.awt.Color;

public class RandomUtilities {
	
	public static Color oppositeColor(Color color) {
		return new Color(
				255-color.getRed(),
				255-color.getGreen(),
				255-color.getBlue());
	}
	
	// Method to generate a shadow color
    public static Color getShadowColor(Color foreground, Color background, float darknessFactor) {
        // Darken the foreground color
        Color darkenedForeground = darkenColor(foreground, darknessFactor);

        // Blend the darkened foreground with the background color
        return blendColors(darkenedForeground, background, 0.5f); // 0.5f means equal blending
    }

    // Helper method to darken a color
    private static Color darkenColor(Color color, float factor) {
        int red = (int) (color.getRed() * (1 - factor));
        int green = (int) (color.getGreen() * (1 - factor));
        int blue = (int) (color.getBlue() * (1 - factor));

        // Ensure the values are within the valid range (0-255)
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        return new Color(red, green, blue);
    }

    // Helper method to blend two colors
    private static Color blendColors(Color color1, Color color2, float ratio) {
        float inverseRatio = 1.0f - ratio;

        int red = (int) (color1.getRed() * ratio + color2.getRed() * inverseRatio);
        int green = (int) (color1.getGreen() * ratio + color2.getGreen() * inverseRatio);
        int blue = (int) (color1.getBlue() * ratio + color2.getBlue() * inverseRatio);

        // Ensure the values are within the valid range (0-255)
        red = Math.max(0, Math.min(255, red));
        green = Math.max(0, Math.min(255, green));
        blue = Math.max(0, Math.min(255, blue));

        return new Color(red, green, blue);
    }
}
