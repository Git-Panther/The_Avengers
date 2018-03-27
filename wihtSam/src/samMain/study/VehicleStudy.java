package samMain.study;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class VehicleStudy extends JFrame{
	
	private static final long serialVersionUID = -5098769906974067686L;
	JLabel vehicle;
	StudyEnd studyEnd = new StudyEnd();
	JFrame mainFrame;
	JButton before,start;
	boolean flag;
	
	public VehicleStudy(){
		setTitle("샘과함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		setBounds(350, 60, 1200, 930);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);
		setResizable(false);
		
		vehicle = new JLabel(new ImageIcon(new ImageIcon("mainView/study/탈것/탈것.png").getImage().getScaledInstance(1200, 900, 0)));
		vehicle.setBounds(0, 0, 1200, 900);
		add(vehicle);
		
		start = new JButton(new ImageIcon(new ImageIcon("mainView/png/시작하기.png").getImage().getScaledInstance(430, 110, 0)));
		start.setBounds(384, 700, 430, 110);
		start.setBorderPainted(false);
		start.setFocusPainted(false);
		add(start); 
		
		start.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(start)){
					new ChangeBg().start();	
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
		vehicle.add(before); 
		
		before.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(before)){
					StudyMain studyMain = new StudyMain();
					studyMain.setVisible(true);
					setVisible(false);
					flag=true;
					
				}				
			}
		});	

	}
	
	public VehicleStudy(JFrame mainFrame) {
		this();
		this.mainFrame = mainFrame;
	}
	
	class ChangeBg extends Thread {
		String vehicleArr[] = {"경찰차","구급차","기차","배","버스","비행기","소방차","자동차","택시","헬리콥터"};
		int randomArr[] = new int[vehicleArr.length];
		@Override
		public void run() {
			int count = 0;
			for(int i=0;i<vehicleArr.length;i++){				
				randomArr[i] = (int)(Math.random()*vehicleArr.length);		
				for(int j=0;j<i;j++){
					if(randomArr[j] == randomArr[i]){
						i--;
						break;
					}
				}
			}
			for(int i=0; i<vehicleArr.length; i++) {
				try {
					start.setVisible(false);
					Image img = new ImageIcon("mainView/study/탈것/"+vehicleArr[randomArr[i]]+".png").getImage().getScaledInstance(1200, 900, 0);
					vehicle.setIcon(new ImageIcon(img));
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
