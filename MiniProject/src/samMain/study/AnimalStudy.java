package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.frame.SamFrame;

public class AnimalStudy extends SamFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8970594428948683850L;
	JLabel animal;
//	JFrame mainFrame;
//	private BackgroundClip bgm;
	
	public AnimalStudy(){
//		setBounds(350, 80, 1210, 940);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		setLayout(null);
//		setTitle("샘과 함께");
//		
//		bgm = BackgroundClip.getClip(); // 클립 가져오기
//		if(!bgm.getBGM().isOpen()) { // 닫혀있다면 새로 열어준다.
//			bgm.setBGM("유민.wav");
//		}
		super();
		
		animal = new JLabel(new ImageIcon(new ImageIcon("study/동물/강아지.png").getImage().getScaledInstance(1200, 900, 0)));
		animal.setBounds(0, 0, 1200, 900);
		add(animal);
		
		new ChangeBg().start();

//		addWindowListener(new FrameAdapter());
	}
//	public AnimalStudy(JFrame mainFrame) {
//		this();
//		this.mainFrame = mainFrame;
//	}
	
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
					new StudyEnd().setVisible(true);
					dispose();
					break;
				}
			}
		}
	}
}
