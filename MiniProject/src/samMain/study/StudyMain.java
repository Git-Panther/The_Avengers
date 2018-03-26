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


public class StudyMain extends SamFrame {

	private static final long serialVersionUID = 8171178786340925231L;
	
//	JFrame mainFrame = new JFrame("샘과 함께");
	JButton fruit,animal,tool,body,color,vehicle,end;
	public static int again = 0;
//	private BackgroundClip bgm;
	
	public StudyMain(){
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
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/학습하기_수정본.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		add(background);
		
		fruit = new JButton(new ImageIcon(new ImageIcon("png/과일.png").getImage().getScaledInstance(242, 126, 0)));
		fruit.setBounds(220, 220, 240, 124);
		fruit.setBorderPainted(false);
		fruit.setFocusPainted(false);
		add(fruit); 
		
		fruit.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(fruit)){
					setAgain(1);
					new FruitStudy().setVisible(true);
					dispose();
				}				
			}
		});
		
		fruit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				fruit.setIcon(new ImageIcon(new ImageIcon("gif/과일.gif").getImage().getScaledInstance(241, 125, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				fruit.setIcon(new ImageIcon(new ImageIcon("png/과일.png").getImage().getScaledInstance(242, 126, 0)));	
				
			}
			
		});
			
		animal = new JButton(new ImageIcon(new ImageIcon("png/동물.png").getImage().getScaledInstance(254, 133, 0)));
		animal.setBounds(477, 214, 254, 133);
		animal.setBorderPainted(false);
		animal.setFocusPainted(false);
		add(animal); 
		
		animal.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(animal)){
					setAgain(2);
					new AnimalStudy().setVisible(true);
					dispose();
				}				
			}
		});
		
		animal.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				animal.setIcon(new ImageIcon(new ImageIcon("gif/동물.gif").getImage().getScaledInstance(254, 133, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				animal.setIcon(new ImageIcon(new ImageIcon("png/동물.png").getImage().getScaledInstance(254, 133, 0)));	
				
			}
			
		});
		
		tool = new JButton(new ImageIcon(new ImageIcon("png/도구.png").getImage().getScaledInstance(254, 133, 0)));
		tool.setBounds(735, 215, 254, 133);
		tool.setBorderPainted(false);
		tool.setFocusPainted(false);
		add(tool); 
		
		tool.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(tool)){
					setAgain(3);
					new ToolStudy().setVisible(true);;
					dispose();
				}				
			}
		});
		
		tool.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				tool.setIcon(new ImageIcon(new ImageIcon("gif/도구.gif").getImage().getScaledInstance(254, 133, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tool.setIcon(new ImageIcon(new ImageIcon("png/도구.png").getImage().getScaledInstance(254, 133, 0)));	
				
			}
			
		});
	
		body = new JButton(new ImageIcon(new ImageIcon("png/신체부위.png").getImage().getScaledInstance(254, 133, 0)));
		body.setBounds(212, 405, 254, 133);
		body.setBorderPainted(false);
		body.setFocusPainted(false);
		add(body); 
		
		body.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setAgain(4);
				if(e.getSource().equals(body)){
					new BodyStudy().setVisible(true);
					dispose();
				}				
			}
		});
		
		body.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				body.setIcon(new ImageIcon(new ImageIcon("gif/신체부위.gif").getImage().getScaledInstance(254, 133, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				body.setIcon(new ImageIcon(new ImageIcon("png/신체부위.png").getImage().getScaledInstance(254, 133, 0)));	
				
			}
			
		});
	
		color = new JButton(new ImageIcon(new ImageIcon("png/색.png").getImage().getScaledInstance(254, 133, 0)));
		color.setBounds(471, 403, 254, 133);
		color.setBorderPainted(false);
		color.setFocusPainted(false);
		add(color); 
		
		color.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(color)){
					setAgain(5);
					new ColorStudy().setVisible(true);
					setVisible(false);
				}				
			}
		});
		
		color.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				color.setIcon(new ImageIcon(new ImageIcon("gif/색.gif").getImage().getScaledInstance(254, 133, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				color.setIcon(new ImageIcon(new ImageIcon("png/색.png").getImage().getScaledInstance(254, 133, 0)));	
				
			}
			
		});
		
		
		
		vehicle = new JButton(new ImageIcon(new ImageIcon("png/탈것.png").getImage().getScaledInstance(254, 133, 0)));
		vehicle.setBounds(734, 404, 254, 133);
		vehicle.setBorderPainted(false);
		vehicle.setFocusPainted(false);
		add(vehicle); 
		
		vehicle.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(vehicle)){
					setAgain(6);
					new VehicleStudy().setVisible(true);
					dispose();
				}				
			}
		});
		
		vehicle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				vehicle.setIcon(new ImageIcon(new ImageIcon("gif/탈것.gif").getImage().getScaledInstance(254, 133, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				vehicle.setIcon(new ImageIcon(new ImageIcon("png/탈것.png").getImage().getScaledInstance(254, 133, 0)));	
				
			}
			
		});
		
		end = new JButton(new ImageIcon(new ImageIcon("png/이전화면.png").getImage().getScaledInstance(107, 52, 0)));
		end.setBounds(17, 844, 107, 52);
		end.setBorderPainted(false);
		end.setFocusPainted(false);
		add(end); 
		
		end.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(end)){
					new SamMainFrame().setVisible(true);
					dispose();
				}				
			}
		});	
		
//		addWindowListener(new FrameAdapter());
	}

	public static int getAgain() {
		return again;
	}

	public static void setAgain(int again) {
		StudyMain.again = again;
	}
	
}
