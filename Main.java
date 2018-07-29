package newGame;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		GamePlay GP = new GamePlay();
		JFrame ob = new JFrame();
		
		ob.setBounds(5, 5, 300, 400);
		ob.setTitle("Hello");
		ob.setVisible(true);
		ob.setResizable(false);
		ob.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ob.add(GP);
	}

}
