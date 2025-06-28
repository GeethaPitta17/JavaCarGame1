package org.ProjectGurukul;

import javax.swing.*;



public class GameFrame extends JFrame {

    public GameFrame() {
        super.setTitle("Car Race");
        super.setBounds(400, 0, 1950, 1035);
        super.add(new Car());
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setVisible(true);
    }
    public static void main(String[] args) {
		new GameFrame();
	}
}
