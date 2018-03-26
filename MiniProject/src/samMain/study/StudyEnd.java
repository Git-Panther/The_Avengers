package samMain.study;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import samMain.frame.SamFrame;
import samMain.main.SamMainFrame;

public class StudyEnd extends SamFrame{

	private static final long serialVersionUID = 711648181291015576L;
	JButton againBt;
	JButton mainBt;
	JButton studyBt;
//	StudyMain studyMain = new StudyMain();
	
	public StudyEnd(){
		super();		
//		setBounds(350, 100, 1210, 940);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		setLayout(null);		
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/돌아가기화면.png").getImage().getScaledInstance(1200, 900, 0)));
		background.setBounds(0, 0, 1200, 900);
		add(background);
		
		studyBt = new JButton(new ImageIcon(new ImageIcon("png/학습하기_끝.png").getImage().getScaledInstance(233, 146, 0)));
		studyBt.setBounds(256, 297, 233, 146);
		studyBt.setBorderPainted(false);
		studyBt.setFocusPainted(false);
		add(studyBt); 
		
		studyBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(studyBt)){					
					new StudyMain().setVisible(true);
					dispose();
				}				
			}
		});
		
		studyBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				studyBt.setIcon(new ImageIcon(new ImageIcon("gif/학습하기_끝.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				studyBt.setIcon(new ImageIcon(new ImageIcon("png/학습하기_끝.png").getImage().getScaledInstance(233, 146, 0)));				
			}	
		});
		
		againBt = new JButton(new ImageIcon(new ImageIcon("png/다시보기.png").getImage().getScaledInstance(244, 154, 0)));
		againBt.setBounds(480, 133, 244, 154);
		againBt.setBorderPainted(false);
		againBt.setFocusPainted(false);
		add(againBt); 
		
		againBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(againBt)){
					switch(StudyMain.getAgain()){
						case 1 : 
							new FruitStudy().setVisible(true);
							dispose();
							break;
						case 2 : new StudyMain().animal.setVisible(true);
							new AnimalStudy().setVisible(true);
							dispose();
							break;
						case 3 : new StudyMain().tool.setVisible(true);
							new ToolStudy().setVisible(true);
							dispose();
							break;
						case 4 : new StudyMain().body.setVisible(true);
							new BodyStudy().setVisible(true);
							dispose();
							break;
						case 5 : new StudyMain().color.setVisible(true);
							new ColorStudy().setVisible(true);
							dispose();
							break;
						case 6 : new StudyMain().vehicle.setVisible(true);
							new VehicleStudy().setVisible(true);
							dispose();
							break;
					}		
				}				
			}
		});
		
		againBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				againBt.setIcon(new ImageIcon(new ImageIcon("gif/다시보기.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				againBt.setIcon(new ImageIcon(new ImageIcon("png/다시보기.png").getImage().getScaledInstance(244, 154, 0)));				
			}		
		});
	
		mainBt = new JButton(new ImageIcon(new ImageIcon("png/메인화면.png").getImage().getScaledInstance(233, 146, 0)));
		mainBt.setBounds(720, 297, 233, 146);
		mainBt.setBorderPainted(false);
		mainBt.setFocusPainted(false);
		add(mainBt); 
		
		mainBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(mainBt)){
					new SamMainFrame().setVisible(true);;
					dispose();			
				}				
			}
		});
		
		mainBt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {			
				mainBt.setIcon(new ImageIcon(new ImageIcon("gif/메인화면.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainBt.setIcon(new ImageIcon(new ImageIcon("png/메인화면.png").getImage().getScaledInstance(244, 154, 0)));		
			}	
		});
	}
}
