package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AnimalStudy extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8970594428948683850L;
	JLabel animal;
	StudyEnd studyEnd = new StudyEnd();
	JFrame mainFrame;
	
	public AnimalStudy(){
		setBounds(350, 80, 1210, 940);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);
		
		animal = new JLabel(new ImageIcon(new ImageIcon("study/동물/강아지.png").getImage().getScaledInstance(1200, 900, 0)));
		animal.setBounds(0, 0, 1200, 900);
		add(animal);
		
		new ChangeBg().start();

	}
	public AnimalStudy(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	class ChangeBg extends Thread {
		String animalArr[] = {"고양이","곰","기린","부엉이","양","얼룩말","여우","코알라","호랑이"};
		int randomArr[] = new int[animalArr.length];
		@Override
		public void run() {
			int count = 0;
			for(int i=0;i<animalArr.length;i++){				
				randomArr[i] = (int)(Math.random()*animalArr.length);		
				for(int j=0;j<i;j++){
					if(randomArr[j] == randomArr[i]){
						i--;
						break;
					}
				}
			}
			for(int i=0; i<animalArr.length; i++) {
				try {
					Thread.sleep(2000);
					Image img = new ImageIcon("study/동물/"+animalArr[randomArr[i]]+".png").getImage().getScaledInstance(1200, 900, 0);
					animal.setIcon(new ImageIcon(img));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				if(count==9){
					studyEnd.setVisible(true);
					setVisible(false);

					break;
			}
		}
	}

	}
}
