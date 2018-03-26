package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.frame.SamFrame;


public class ColorStudy extends SamFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1546661114853130498L;
	JLabel color;
//	StudyEnd studyEnd = new StudyEnd();
//	JFrame mainFrame;
	
	public ColorStudy(){
//		setBounds(350, 80, 1210, 940);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		setLayout(null);
		super();
		
		color = new JLabel(new ImageIcon(new ImageIcon("study/색/검은색.png").getImage().getScaledInstance(1200, 900, 0)));
		color.setBounds(0, 0, 1200, 900);
		add(color);
		
		new ChangeBg().start();

	}
	
//	public ColorStudy(JFrame mainFrame) {
//		this();
//		this.mainFrame = mainFrame;
//	}
	
	class ChangeBg extends Thread {
		String colorArr[] = {"노란색","보라색","분홍색","빨간색","주황색","초록색","파란색","하늘색","회색"};
		@Override
		public void run() {
			int count = 0;
			for(int i=0; i<colorArr.length; i++) {
				try {
					Thread.sleep(2000);
					Image img = new ImageIcon("study/색/"+colorArr[i]+".png").getImage().getScaledInstance(1200, 900, 0);
					color.setIcon(new ImageIcon(img));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				if(count==9){
					new StudyEnd().setVisible(true);
//					studyEnd.setVisible(true);
					dispose();
					break;
				}
			}
		}
	}
}
