package com.lucasj.gamedev.essentials.ui.components;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import com.lucasj.gamedev.essentials.Game;

public class GradientText {
    private String text;
    private Color startColor;
    private Color endColor;
    private int x, y;
    private int fontSize;
    private float gradientPosition = 0.0f;
    private Game game;
    private float gradientSpeed = 0.05f;

    public GradientText(Game game, String text, Color startColor, Color endColor, int x, int y, int fontSize) {
        this.game = game;
        this.text = text;
        this.startColor = startColor;
        this.endColor = endColor;
        this.x = x;
        this.y = y;
        this.fontSize = fontSize;
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Set the font
        g2d.setFont(game.font.deriveFont((float) fontSize));

        // Get text dimensions
        FontMetrics metrics = g2d.getFontMetrics();
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getAscent();

        // Extend the gradient over twice the text width for smooth looping
        float startX1 = x + (gradientPosition * textWidth * 2) % (textWidth * 2);
        float endX1 = startX1 + textWidth; 

        // Create first gradient
        GradientPaint gradient1 = new GradientPaint(startX1, y - textHeight, startColor, endX1, y, endColor, true);
        
        // Apply the gradient and draw the first part of the text
        g2d.setPaint(gradient1);
        g2d.drawString(text, x, y);

        // Create and draw a second gradient that continues smoothly
        float startX2 = startX1 - textWidth * 2;
        float endX2 = startX2 + textWidth;

        GradientPaint gradient2 = new GradientPaint(startX2, y - textHeight, startColor, endX2, y, endColor, true);
        g2d.setPaint(gradient2);
        g2d.drawString(text, x, y);
    }

    public void update() {
        // Update gradient position
        gradientPosition += gradientSpeed;

        // Wrap around smoothly when it exceeds the boundary
        if (gradientPosition > 1.0f) {
            gradientPosition -= 1.0f; // Ensures a continuous flow effect
        }
    }


    
    public GradientText setSpeed(float speed) {
    	this.gradientSpeed = speed;
    	return this;
    }
}