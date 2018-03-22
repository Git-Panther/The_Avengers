package samMain.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.play.PlayMain;
import samMain.study.StudyMain;

public class SamMain{
	JFrame mainFrame = new JFrame("샘과함께");
	JButton study,play;
	Clip clip1;


	
	public SamMain(){
		mainFrame.setBounds(350, 80, 1210, 930);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		mainFrame.setLayout(null);		
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/메인화면.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		mainFrame.add(background);
		
		
		study = new JButton(new ImageIcon(new ImageIcon("png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));
		study.setBounds(28, 243, 318, 178);
		study.setBorderPainted(false);
		study.setFocusPainted(false);
		mainFrame.add(study); 
		
		
		
		study.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(study)){
					StudyMain studyMain = new StudyMain();
					mainFrame.setVisible(false);
					studyMain.setVisible(true);
				}				
			}
		});
		
		
		study.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				study.setIcon(new ImageIcon(new ImageIcon("gif/학습하기.gif").getImage().getScaledInstance(324, 184, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				study.setIcon(new ImageIcon(new ImageIcon("png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));	
				
			}
			
		});
		
		play = new JButton(new ImageIcon(new ImageIcon("png/놀이학습.png").getImage().getScaledInstance(316, 177, 0)));
		play.setBounds(357, 245, 316, 177);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		mainFrame.add(play); 
		
		play.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(play)){
					PlayMain playMain = new PlayMain();
					mainFrame.setVisible(false);
					playMain.setVisible(true);
				}				
			}
		});
		
		play.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				play.setIcon(new ImageIcon(new ImageIcon("gif/놀이학습.gif").getImage().getScaledInstance(320, 180, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				play.setIcon(new ImageIcon(new ImageIcon("png/놀이학습.png").getImage().getScaledInstance(316, 177, 0)));	
				
			}
		});
		
		mainFrame.setVisible(true);
	}
	
//	public void sound(){
//		try {
//            AudioInputStream as = AudioSystem.getAudioInputStream(new File("유민.wav"));
//            clip1 = AudioSystem.getClip();
//            clip1.stop();
//            clip1.open(as);
//            clip1.start();
//         } catch (Exception e) {
//            e.printStackTrace();
//         }
//	}




	
	
	

}
