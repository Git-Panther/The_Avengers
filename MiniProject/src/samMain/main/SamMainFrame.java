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
	
	private JButton study,play;
	
	public SamMainFrame(){
		super();
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/메인화면.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		add(background);
		
		study = new JButton(new ImageIcon(new ImageIcon("png/학습하기.png").getImage().getScaledInstance(319, 179, 0)));
		study.setBounds(28, 243, 318, 178);
		study.setBorderPainted(false);
		study.setFocusPainted(false);
		add(study);
			
		study.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(study)){
					new StudyMain().setVisible(true);
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
		add(play);
		
		play.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(play)){
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
	}
}
