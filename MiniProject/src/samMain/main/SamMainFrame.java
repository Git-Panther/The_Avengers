package samMain.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import samMain.frame.SamFrame;
import samMain.play.PlayMain;
import samMain.study.StudyMain;

public class SamMainFrame extends SamFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7658020125300307115L;
//	JFrame mainFrame = new JFrame("샘과 함께");
	JButton study,play;
//	private BackgroundClip bgm;
	
	public SamMainFrame(){
//		mainFrame.setBounds(350, 80, 1210, 930);
//		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		mainFrame.setResizable(false);
//		mainFrame.setLayout(null);	
		
//		setBounds(350, 80, 1210, 930);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setResizable(false);
//		setLayout(null);	
//		
//		bgm = BackgroundClip.getClip(); // 클립 가져오기
//		if(!bgm.getBGM().isOpen()) { // 닫혀있다면 새로 열어준다.
//			bgm.setBGM("유민.wav");
//		}
		super();
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/메인화면.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
//		mainFrame.add(background);
		add(background);
		
		study = new JButton(new ImageIcon(new ImageIcon("png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));
		study.setBounds(28, 243, 318, 178);
		study.setBorderPainted(false);
		study.setFocusPainted(false);
//		mainFrame.add(study); 
		add(study);
			
		study.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(study)){
					new StudyMain().setVisible(true);
//					mainFrame.setVisible(false);
					dispose();
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
//		mainFrame.add(play); 
		add(play);
		
		play.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(play)){
//					mainFrame.dispose();
					new PlayMain().setVisible(true);
					dispose();
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
		
//		mainFrame.addWindowListener(new FrameAdapter());
//		addWindowListener(new FrameAdapter());
	}
}
