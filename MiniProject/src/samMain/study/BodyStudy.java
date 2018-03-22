package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BodyStudy extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8882026988811346267L;
	JLabel body;
	StudyEnd studyEnd = new StudyEnd();
	JFrame mainFrame;
	
	public BodyStudy(){
		setBounds(350, 100, 1210, 940);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);

		body = new JLabel(new ImageIcon(new ImageIcon("study/신체부위/1눈.png").getImage().getScaledInstance(1200, 900, 0)));
		body.setBounds(0, 0, 1200, 900);
		add(body);
		
		new ChangeBg().start();
		
	}
	
	public BodyStudy(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	class ChangeBg extends Thread {
		String bodyArr[] = {"2코","3입","4귀","5목","6팔","7손","8배","9다리","9발"};
		@Override
		public void run() {
			int count = 0;
			for(int i=0; i<bodyArr.length; i++) {
				try {
					Thread.sleep(2000);
					Image img = new ImageIcon("study/신체부위/"+bodyArr[i]+".png").getImage().getScaledInstance(1200, 900, 0);
					body.setIcon(new ImageIcon(img));
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
