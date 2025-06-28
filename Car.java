package org.ProjectGurukul;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JPanel;
                          
public class Car extends GameMap implements ActionListener, KeyListener {

    private Timer t;

    private BufferedImage playerCar;
    private BufferedImage secondCar;
    private BufferedImage thirdCar;
    
    private int playerCarX = 980;
    private int playerCarY = 730;
    private int secondCarX = 200;
    private int secondCarY = 500;
    private int thirdCarX = 400;
    private int thirdCarY = 0;
    
    
    

    private int speed = 35
    		;
    		
    		
    

    private int velocityX = 0;
    private int velocityY = 0;
    
    private int score =0;

    public Car() {
        super.setDoubleBuffered(true);
        t = new javax.swing.Timer(0, this);
        JOptionPane.showMessageDialog(this, "Press Ok to start the game","Car Race",JOptionPane.INFORMATION_MESSAGE);
        t.start();
        this.addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }

    public Timer getTimer() {
        return t;
    }

    public int getPlayerCarX() {
        return playerCarX;
    }

    public int getPlayerCarY() {
        return playerCarY;
    }

    public int getSecondCarX() {
        return secondCarX;
    }

    public int getSecondCarY() {
        return secondCarY;
    }

    public int getThirdCarX() {
        return thirdCarX;
    }

    public int getThirdCarY() {
        return thirdCarY;
    }

                         
                         
                         
                         
                         
                         
    public BufferedImage getPlayerCar() {
        return playerCar;
    }


    public BufferedImage getSecondCar() {
        return secondCar;
    }


    public BufferedImage getThirdCar() {
        return thirdCar;
    }
    
    
    
    
    
    
    
    public void OtherCars() {
        try {
           // playerCar = ImageIO.read(new File("images/PlayerCar.png"));
           // secondCar = ImageIO.read(new File("images/secondCar.png"));
           
        	// thirdCar = ImageIO.read(new File("images/thirdCar.png"));
        playerCar =ImageIO.read(GameMap.class.getResource("/images/PlayerCar.png"));
        secondCar =ImageIO.read(GameMap.class.getResource("/images/secondCar.png")); 
        thirdCar =ImageIO.read(GameMap.class.getResource("/images/thirdCar.png"));
        
        } catch (IOException ex) {
            System.out.println("Image not found");
        }

    }

    public void paintComponent(Graphics g) {
        OtherCars();
        super.paintComponent(g);
        g.drawImage(getPlayerCar(), getPlayerCarX(), getPlayerCarY(), null);
        g.drawImage(getSecondCar(), getSecondCarX(), getSecondCarY(), null);
        g.drawImage(getThirdCar(), getThirdCarX(), getThirdCarY(), null);
        
        g.setColor(Color.white);
		g.setFont( new Font("Arial",Font.BOLD, 40));
		g.drawString("Score: "+score, 300, 320);
		
		

				
		if(!getTimer().isRunning()) {
			 	g.setColor(Color.RED);
				g.setFont( new Font("Arial",Font.BOLD, 80));
				g.drawString("Game Over", 400, 500);
		}
    }

    
    public void move() {
        secondCarY = secondCarY + speed;
        thirdCarY = thirdCarY + speed;
       
        if (secondCarY >= 980) {
            secondCarY = 0;
            score++;
        }
        if (thirdCarY == 980) {
            thirdCarY = 0;
            score++;
        }
        
        
        if(checkCollision()) {
        	getTimer().stop();
        }
        
        repaint();
        

    }

//    Method to check the collision of cars
    public boolean checkCollision() {
        Rectangle playerRect = new Rectangle(playerCarX+65, playerCarY, getPlayerCar().getWidth()-130, getPlayerCar().getHeight());
        Rectangle secondRect = new Rectangle(secondCarX, secondCarY, getSecondCar().getWidth()-75, getSecondCar().getHeight());
        Rectangle thirdRect = new Rectangle(thirdCarX+65, thirdCarY, getThirdCar().getWidth()-100, getThirdCar().getHeight());
        

        if (playerRect.intersects(secondRect) || playerRect.intersects(thirdRect)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if (c == KeyEvent.VK_LEFT) {
            velocityX = -70;
            velocityY = 0;
        }
        if (c == KeyEvent.VK_UP) {
            velocityX = 0;
            velocityY = -70;
        }
        if (c == KeyEvent.VK_RIGHT) {
            velocityX = 70;
            velocityY = 0;
        }
        if (c == KeyEvent.VK_DOWN) {
            velocityX = 0;
            velocityY = 70;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
//Method to limit the car's movement to the road only
    public void limit() {
        if (playerCarX < 100) {
            velocityX = 0;
            playerCarX = 100;
        }
        if (playerCarX > 450) {
            velocityX = 0;
            playerCarX = 450;
        }
        if (playerCarY < 0) {
            velocityY = 0;
            playerCarY = 780;
        }
      /* if (playerCarY > 780) {
            velocityY = 0;
            playerCarY = 780;
        }*/

       if (playerCarY > 780) {
        	velocityY=0;
        	playerCarY = 780;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OtherCars();
    	move();
        limit();
        this.addKeyListener(this);
        playerCarX = playerCarX + velocityX;
        velocityX = 0;
        playerCarY = playerCarY + velocityY;
        velocityY = 0;
        repaint();
    }
}