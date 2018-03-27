package samMain.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.play.PlayMain;
import samMain.study.StudyMain;

public class SamMain{
	JFrame mainFrame = new JFrame("샘과함께");
	JButton study,play;
	private SamBgmClip bgm = SamBgmClip.getClip(); // BGM 클립

	
	public JFrame getMainFrame() {
		return mainFrame;
	}


	public void setMainFrame(JFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public SamMain(){
		mainFrame.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		mainFrame.setBounds(350, 60, 1200, 930);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		mainFrame.setLayout(null);		
		mainFrame.setResizable(false);
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/메인화면.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		mainFrame.add(background);
		
		if(!bgm.isOn()) {
			bgm.on();
			bgm.setBGM();	
		}
		
		study = new JButton(new ImageIcon(new ImageIcon("mainView/png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));
		study.setBounds(28, 243, 318, 178);
		study.setBorderPainted(false);
		study.setFocusPainted(false);
		mainFrame.add(study); 
		
		
		
		study.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(study)){
					StudyMain studyMain = new StudyMain();
					studyMain.setVisible(true);
					mainFrame.dispose();
				}				
			}
		});
		
		
		study.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				study.setIcon(new ImageIcon(new ImageIcon("mainView/gif/학습하기.gif").getImage().getScaledInstance(324, 184, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				study.setIcon(new ImageIcon(new ImageIcon("mainView/png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));	
				
			}
			
		});
		
		play = new JButton(new ImageIcon(new ImageIcon("mainView/png/놀이학습.png").getImage().getScaledInstance(316, 177, 0)));
		play.setBounds(357, 245, 316, 177);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		mainFrame.add(play); 
		
		play.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(play)){
					PlayMain playMain = new PlayMain();
					playMain.setVisible(true);
					mainFrame.dispose();
				}				
			}
		});
		
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				play.setIcon(new ImageIcon(new ImageIcon("mainView/gif/놀이학습.gif").getImage().getScaledInstance(320, 180, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				play.setIcon(new ImageIcon(new ImageIcon("mainView/png/놀이학습.png").getImage().getScaledInstance(316, 177, 0)));	
				
			}
		});
		
		mainFrame.setVisible(true);
		mainFrame.addWindowListener(new WindowAdapter(){
				
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.resumeBGM();
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.pauseBGM();
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.resumeBGM();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.resumeBGM();
			}
		});
	}
}
