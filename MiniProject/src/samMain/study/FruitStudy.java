package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.frame.SamFrame;


public class FruitStudy extends SamFrame{

	private static final long serialVersionUID = 165216307393933956L;
	JLabel fruit;
//	StudyEnd studyEnd = new StudyEnd();
//	JFrame mainFrame;
	
	public FruitStudy(){
//		setBounds(350, 100, 1210, 940);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		setLayout(null);
		super();

		fruit = new JLabel(new ImageIcon(new ImageIcon("study/과일/딸기.png").getImage().getScaledInstance(1200, 900, 0)));
		fruit.setBounds(0, 0, 1200, 900);
		add(fruit);
		
		new ChangeBg().start();
		
	}
	
//	public FruitStudy(JFrame mainFrame) {
//		this();
//		this.mainFrame = mainFrame;
//	}

	class ChangeBg extends Thread {
		String fruitArr[] = {"바나나","복숭아","사과","수박","오렌지","체리","키위","파인애플","포도"};
		int randomArr[] = new int[fruitArr.length];
		@Override
		public void run() {
			int count = 0;
			for(int i=0;i<fruitArr.length;i++){				
				randomArr[i] = (int)(Math.random()*fruitArr.length);		
				for(int j=0;j<i;j++){
					if(randomArr[j] == randomArr[i]){
						i--;
						break;
					}
				}
			}
			for(int i=0; i<randomArr.length; i++) {
				try {
					Thread.sleep(2000);
					Image img = new ImageIcon("study/과일/"+fruitArr[randomArr[i]]+".png").getImage().getScaledInstance(1200, 900, 0);
					fruit.setIcon(new ImageIcon(img));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				if(count==9){
					new StudyEnd().setVisible(true);
					dispose();
					break;
				}
			}
		}
	}
}
