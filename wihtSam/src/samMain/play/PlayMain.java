package samMain.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamBgmClip;
import samMain.main.SamMain;
import samMain.play.zoo.frame.GameFrame;
import samMain.play.zoo.game.ZooGame;

public class PlayMain extends JFrame {
	private static final long serialVersionUID = -5006176008933513740L;
	JFrame mainFrame = new JFrame("샘과함께");
	JButton fruitShop,cleanRoom,body,zoo,end;
	SamMain samMain;
	private SamBgmClip bgm = SamBgmClip.getClip(); // BGM 클립
	
	public PlayMain(){
		setTitle("샘과함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		setBounds(350, 60, 1210, 940);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);		
		
		if(!bgm.isOn()) {
			bgm.on();
			bgm.setBGM();	
		}
		
		JLabel background = new JLabel(new ImageIcon(new ImageIcon("mainView/놀이학습.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		add(background);

		fruitShop = new JButton(new ImageIcon(new ImageIcon("mainView/png/황가네과일가게.png").getImage().getScaledInstance(261, 148, 0)));
		fruitShop.setBounds(152, 150, 261, 148);
		fruitShop.setBorderPainted(false);
		fruitShop.setFocusPainted(false);
		add(fruitShop); 
		
		fruitShop.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(fruitShop)){
					bgm.pauseBGM();
					bgm.getBGM().close();
					bgm.off();
					new FruitShop();
					dispose();
				}				
			}
		});
		
		fruitShop.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				fruitShop.setIcon(new ImageIcon(new ImageIcon("mainView/gif/황가네과일가게.gif").getImage().getScaledInstance(261, 148, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				fruitShop.setIcon(new ImageIcon(new ImageIcon("mainView/png/황가네과일가게.png").getImage().getScaledInstance(261, 148, 0)));	
				
			}
			
		});
		
		cleanRoom = new JButton(new ImageIcon(new ImageIcon("mainView/png/뽀로로의정리정돈.png").getImage().getScaledInstance(261, 148, 0)));
		cleanRoom.setBounds(420, 150, 261, 148);
		cleanRoom.setBorderPainted(false);
		cleanRoom.setFocusPainted(false);
		add(cleanRoom); 
		
		cleanRoom.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(cleanRoom)){
					bgm.pauseBGM();
					bgm.getBGM().close();
					bgm.off();
					new CleanRoomMain();
					dispose();
					
				}				
			}
		});
		
		cleanRoom.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				cleanRoom.setIcon(new ImageIcon(new ImageIcon("mainView/gif/뽀로로의정리정돈.gif").getImage().getScaledInstance(261, 148, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				cleanRoom.setIcon(new ImageIcon(new ImageIcon("mainView/png/뽀로로의정리정돈.png").getImage().getScaledInstance(261, 148, 0)));	
				
			}
			
		});
		
		body = new JButton(new ImageIcon(new ImageIcon("mainView/png/머리어깨무릎발무릎발.png").getImage().getScaledInstance(261, 148, 0)));
		body.setBounds(152, 308, 261, 148);
		body.setBorderPainted(false);
		body.setFocusPainted(false);
		add(body); 
		
		body.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(body)){
					bgm.pauseBGM();
					bgm.getBGM().close();
					bgm.off();
					new BodyGame();
					dispose();
					
				}				
			}
		});
		
		body.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				body.setIcon(new ImageIcon(new ImageIcon("mainView/gif/머리어깨무릎발무릎발.gif").getImage().getScaledInstance(261, 148, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				body.setIcon(new ImageIcon(new ImageIcon("mainView/png/머리어깨무릎발무릎발.png").getImage().getScaledInstance(261, 148, 0)));	
				
			}
			
		});
		
		zoo = new JButton(new ImageIcon(new ImageIcon("mainView/png/오늘은내가조련사.png").getImage().getScaledInstance(261, 148, 0)));
		zoo.setBounds(420, 308, 261, 148);
		zoo.setBorderPainted(false);
		zoo.setFocusPainted(false);
		add(zoo); 
		
		zoo.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(zoo)){
					bgm.pauseBGM();
					bgm.getBGM().close();
					bgm.off();
					new GameFrame(new ZooGame());
					dispose();
				}				
			}
		});
		
		zoo.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				zoo.setIcon(new ImageIcon(new ImageIcon("mainView/gif/오늘은내가조련사.gif").getImage().getScaledInstance(261, 148, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				zoo.setIcon(new ImageIcon(new ImageIcon("mainView/png/오늘은내가조련사.png").getImage().getScaledInstance(261, 148, 0)));	
				
			}
			
		});
		
		end = new JButton(new ImageIcon(new ImageIcon("mainView/png/이전화면.png").getImage().getScaledInstance(107, 52, 0)));
		end.setBounds(17, 844, 107, 52);
		end.setBorderPainted(false);
		end.setFocusPainted(false);
		add(end); 
		
		end.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(end)){
					samMain = new SamMain();
					setVisible(false);			
				}				
			}
		});
		
		addWindowListener(new WindowListener() {
			
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
				if(bgm.isOn())
					bgm.resumeBGM();
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.pauseBGM();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.pauseBGM();
//				BackgroundClip.getClip().getBGM().close();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				if(bgm.isOn())
					bgm.resumeBGM();
			}
		});
	
	
		
	}

}
