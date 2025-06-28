package org.ProjectGurukul;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;

public class GameMap extends JPanel {
    private BufferedImage grass;
    private BufferedImage highway;
    private JLabel scoreLabel = new JLabel("Score");

    
    
    public BufferedImage getGrass() {
        return grass;
    }

    public BufferedImage getHighway() {
        return highway;
    }


    public void drawApp() {
    	try {
           // grass = ImageIO.read(new File("images/grass_tile.png"));
            // highway = ImageIO.read(new File("images/highway.png"));
        grass=ImageIO.read(GameMap.class.getResource("/images/grass_tile.png"));
        highway =ImageIO.read(GameMap.class.getResource("/images/highway.png"));
    	} catch (IOException e) {
            System.out.println("Image not  found");
            System.exit(0);
        }

    }

    public void paintComponent(Graphics g) {
        drawApp();
        for (int i = 0; i < 150; i+=32) {
        	for (int j = 0; j < 1000; j+=32) {
        		g.drawImage(getGrass(), i, j, null);
                g.drawImage(getGrass(), 620+i, j, null);
			}
        	
		}
        g.drawImage(getGrass(), 0, 0, null);
        g.drawImage(getGrass(), 620, 0, null);
        
        g.drawImage(getHighway(),150, 0, null);
    }
}
