package samMain.study;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamMain;

public class StudyEnd extends JFrame{

	private static final long serialVersionUID = 711648181291015576L;
	JButton againBt;
	JButton mainBt;
	JButton studyBt;
	StudyMain studyMain = new StudyMain();
	

	
	public StudyEnd(){
		setTitle("샘과함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		setBounds(350, 60, 1200, 930);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);	
		setResizable(false);
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/돌아가기화면.png").getImage().getScaledInstance(1200, 900, 0)));
		background.setBounds(0, 0, 1200, 900);
		add(background);
		
		studyBt = new JButton(new ImageIcon(new ImageIcon("mainView/png/끝_학습하기.png").getImage().getScaledInstance(233, 146, 0)));
		studyBt.setBounds(256, 297, 233, 146);
		studyBt.setBorderPainted(false);
		studyBt.setFocusPainted(false);
		add(studyBt); 
		
		studyBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(studyBt)){					
					studyMain.setVisible(true);
					setVisible(false);
			
				}				
			}
		});
		
		studyBt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				studyBt.setIcon(new ImageIcon(new ImageIcon("mainView/gif/끝_학습하기.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				studyBt.setIcon(new ImageIcon(new ImageIcon("mainView/png/끝_학습하기.png").getImage().getScaledInstance(233, 146, 0)));	
				
			}
			
		});
		
		againBt = new JButton(new ImageIcon(new ImageIcon("mainView/png/끝_다시보기.png").getImage().getScaledInstance(244, 154, 0)));
		againBt.setBounds(480, 133, 244, 154);
		againBt.setBorderPainted(false);
		againBt.setFocusPainted(false);
		add(againBt); 
		
		againBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(againBt)){
					switch(studyMain.getAgain()){
						case 1 : 
							FruitStudy fruitStudy = new FruitStudy();
							fruitStudy.setVisible(true);
							setVisible(false);
							break;
						case 2 : studyMain.animal.setVisible(true);
							AnimalStudy animalStudy = new AnimalStudy();
							animalStudy.setVisible(true);
							setVisible(false);
							break;
						case 3 : studyMain.tool.setVisible(true);
							ToolStudy toolStudy = new ToolStudy();
							toolStudy.setVisible(true);
							setVisible(false); 
							break;
						case 4 : studyMain.body.setVisible(true);
							BodyStudy bodyStudy = new BodyStudy();
							bodyStudy.setVisible(true);
							setVisible(false); 
							break;
						case 5 : studyMain.color.setVisible(true);
							ColorStudy colorStudy = new ColorStudy();
							colorStudy.setVisible(true);
							setVisible(false); 
							break;
						case 6 : studyMain.vehicle.setVisible(true);
							VehicleStudy vehicleStudy = new VehicleStudy();
							vehicleStudy.setVisible(true);
							setVisible(false);
							break;
					}
					
					
				}				
			}
		});
		
		againBt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				againBt.setIcon(new ImageIcon(new ImageIcon("mainView/gif/끝_다시보기.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				againBt.setIcon(new ImageIcon(new ImageIcon("mainView/png/끝_다시보기.png").getImage().getScaledInstance(244, 154, 0)));	
				
			}
			
		});
	
		mainBt = new JButton(new ImageIcon(new ImageIcon("mainView/png/끝_메인화면.png").getImage().getScaledInstance(233, 146, 0)));
		mainBt.setBounds(720, 297, 233, 146);
		mainBt.setBorderPainted(false);
		mainBt.setFocusPainted(false);
		add(mainBt); 
		
		mainBt.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(mainBt)){
					new SamMain();
					setVisible(false);
					
				}				
			}
		});
		
		mainBt.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				mainBt.setIcon(new ImageIcon(new ImageIcon("mainView/gif/끝_메인화면.gif").getImage().getScaledInstance(244, 154, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mainBt.setIcon(new ImageIcon(new ImageIcon("mainView/png/끝_메인화면.png").getImage().getScaledInstance(244, 154, 0)));	
				
			}
			
		});
		
		
		
	}
}
