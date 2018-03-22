package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ToolStudy extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2056776766774765857L;
	JLabel tool;
	StudyEnd studyEnd = new StudyEnd();
	JFrame mainFrame;
	
	public ToolStudy(){
		setBounds(350, 80, 1210, 940);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);
		
		tool = new JLabel(new ImageIcon(new ImageIcon("study/동물/강아지.png").getImage().getScaledInstance(1200, 900, 0)));
		tool.setBounds(0, 0, 1200, 900);
		add(tool);
		
		new ChangeBg().start();
		
	}
	
	public ToolStudy(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	class ChangeBg extends Thread {
		String ToolArr[] = {"고양이","곰","기린","부엉이","양","얼룩말","여우","코알라","호랑이"};
		@Override
		public void run() {
			int count = 0;
			for(int i=0; i<ToolArr.length; i++) {
				try {
					Thread.sleep(200);
					Image img = new ImageIcon("study/도구/"+ToolArr[i]+".png").getImage().getScaledInstance(1200, 900, 0);
					tool.setIcon(new ImageIcon(img));
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
