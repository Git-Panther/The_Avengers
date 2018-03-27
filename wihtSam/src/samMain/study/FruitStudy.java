package samMain.study;

import java.awt.Image;
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


public class FruitStudy extends JFrame{

	private static final long serialVersionUID = 9144186580967728512L;
	JLabel fruit;
	StudyEnd studyEnd = new StudyEnd();
	JFrame mainFrame;
	JButton before,start;
	ChangeBg cbThread;
	boolean flag;
	private SamBgmClip bgm = SamBgmClip.getClip(); // BGM 클립
	
	public FruitStudy(){
		setTitle("샘과함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		setBounds(350, 60, 1200, 930);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);
		setResizable(false);

		fruit = new JLabel(new ImageIcon(new ImageIcon("mainView/study/과일/과일.png").getImage().getScaledInstance(1200, 900, 0)));
		fruit.setBounds(0, 0, 1200, 900);
		add(fruit);

		
		start = new JButton(new ImageIcon(new ImageIcon("mainView/png/시작하기.png").getImage().getScaledInstance(430, 110, 0)));
		start.setBounds(384, 700, 430, 110);
		start.setBorderPainted(false);
		start.setFocusPainted(false);
		add(start); 
		
		start.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(start)){
					cbThread = new ChangeBg();
					cbThread.start();
				}				
			}
		});	
		
		start.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				start.setIcon(new ImageIcon(new ImageIcon("mainView/gif/시작하기.gif").getImage().getScaledInstance(430, 110, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				start.setIcon(new ImageIcon(new ImageIcon("mainView/png/시작하기.png").getImage().getScaledInstance(430, 110, 0)));					
			}
			
		});
		
		before = new JButton(new ImageIcon(new ImageIcon("mainView/png/이전화면.png").getImage().getScaledInstance(107, 52, 0)));
		before.setBounds(17, 845, 107, 52);
		before.setBorderPainted(false);
		before.setFocusPainted(false);
		fruit.add(before); 
		
		before.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(before)){
					setVisible(false);
					StudyMain studyMain = new StudyMain();
					studyMain.setVisible(true);
					flag=true;
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
	
	
	public FruitStudy(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	


	class ChangeBg extends Thread {
		
		String fruitArr[] = {"딸기","바나나","복숭아","사과","수박","오렌지","체리","키위","파인애플","포도"};
		int randomArr[] = new int[fruitArr.length];
		
		@Override
		public void run() {
			int count = 0;
			for(int i=0;i<fruitArr.length;i++){				
				randomArr[i] = (int)(Math.random()*fruitArr.length);		
				for(int j=0;j<i;j++){
					if(randomArr[j] == randomArr[i]){
						i--;
						break;
					}
				}
			}
			for(int i=0; i<randomArr.length; i++) {
				try {					
					start.setVisible(false);
					Image img = new ImageIcon("mainView/study/과일/"+fruitArr[randomArr[i]]+".png").getImage().getScaledInstance(1200, 900, 0);
					fruit.setIcon(new ImageIcon(img));				
					Thread.sleep(2000);
					if(flag==true){
						break;
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				if(count==10){
					studyEnd.setVisible(true);
					setVisible(false);					
					break;
				}
			}
		
		}
	}
	}
	
