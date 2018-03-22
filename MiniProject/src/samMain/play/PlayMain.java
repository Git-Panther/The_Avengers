package samMain.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamMain;
import samMain.play.zoo.frame.GameFrame;
import samMain.play.zoo.game.ZooGame;

public class PlayMain extends JFrame {

	private static final long serialVersionUID = -6022784738766090924L;
	JFrame mainFrame = new JFrame("샘과함께");
	JButton fruitShop,cleanRoom,body,zoo,end;
	
	public PlayMain(){
		setBounds(350, 80, 1210, 940);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);		
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/놀이학습_수정본.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		add(background);

		fruitShop = new JButton(new ImageIcon(new ImageIcon("png/과일가게.png").getImage().getScaledInstance(261, 149, 0)));
		fruitShop.setBounds(152, 150, 261, 149);
		fruitShop.setBorderPainted(false);
		fruitShop.setFocusPainted(false);
		add(fruitShop); 
		
		fruitShop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(fruitShop)){
					new FruitShop();
					setVisible(false);
					
				}				
			}
		});
		
		fruitShop.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				fruitShop.setIcon(new ImageIcon(new ImageIcon("gif/황가네과일가게.gif").getImage().getScaledInstance(264, 159, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				fruitShop.setIcon(new ImageIcon(new ImageIcon("png/과일가게.png").getImage().getScaledInstance(261, 149, 0)));	
				
			}
			
		});
		
		cleanRoom = new JButton(new ImageIcon(new ImageIcon("png/정리정돈.png").getImage().getScaledInstance(261, 149, 0)));
		cleanRoom.setBounds(420, 150, 261, 149);
		cleanRoom.setBorderPainted(false);
		cleanRoom.setFocusPainted(false);
		add(cleanRoom); 
		
		cleanRoom.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cleanRoom)){		
					new CleanRoomMain();
					setVisible(false);
					
				}				
			}
		});
		
		cleanRoom.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				cleanRoom.setIcon(new ImageIcon(new ImageIcon("gif/뽀로로의정리정돈.gif").getImage().getScaledInstance(264, 155, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cleanRoom.setIcon(new ImageIcon(new ImageIcon("png/정리정돈.png").getImage().getScaledInstance(261, 149, 0)));	
				
			}
			
		});
		
		body = new JButton(new ImageIcon(new ImageIcon("png/신체부위맞추기.png").getImage().getScaledInstance(261, 149, 0)));
		body.setBounds(152, 308, 261, 149);
		body.setBorderPainted(false);
		body.setFocusPainted(false);
		add(body); 
		
		body.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(body)){
					new BodyGame();
					setVisible(false);
					
				}				
			}
		});
		
		body.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				body.setIcon(new ImageIcon(new ImageIcon("gif/머리어깨무릎발무릎발.gif").getImage().getScaledInstance(265, 151, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				body.setIcon(new ImageIcon(new ImageIcon("png/신체부위맞추기.png").getImage().getScaledInstance(261, 149, 0)));	
				
			}
			
		});
		
		zoo = new JButton(new ImageIcon(new ImageIcon("png/동물원.png").getImage().getScaledInstance(261, 149, 0)));
		zoo.setBounds(420, 308, 261, 149);
		zoo.setBorderPainted(false);
		zoo.setFocusPainted(false);
		add(zoo); 
		
		zoo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(zoo)){
					dispose();
					new GameFrame(new ZooGame());
				}				
			}
		});
		
		zoo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				zoo.setIcon(new ImageIcon(new ImageIcon("gif/오늘은내가조련사.gif").getImage().getScaledInstance(265, 151, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoo.setIcon(new ImageIcon(new ImageIcon("png/동물원.png").getImage().getScaledInstance(261, 149, 0)));	
				
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
					new SamMain();
					setVisible(false);
					
				}				
			}
		});
	
	
		
	}

}
