import javax.swing.JFrame;

public class Main {
	
		public static void main(String[] args) {
			JFrame frame = new JFrame();
			Gameplay gamePlay = new Gameplay();
			frame.add(gamePlay);
			
			frame.setTitle("Brick Breaker");
			frame.setSize(710,600);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			frame.setResizable(false);
			

		}

}
