package samMain.study;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.frame.SamFrame;


public class VehicleStudy extends SamFrame{

	private static final long serialVersionUID = 6944290025300142557L;
	JLabel vehicle;
//	StudyEnd studyEnd = new StudyEnd();
//	JFrame mainFrame;
	
	public VehicleStudy(){
//		setBounds(350, 80, 1210, 940);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
//		setLayout(null);
		super();
		
		vehicle = new JLabel(new ImageIcon(new ImageIcon("study/탈것/경찰차.png").getImage().getScaledInstance(1200, 900, 0)));
		vehicle.setBounds(0, 0, 1200, 900);
		add(vehicle);
		
		new ChangeBg().start();

	}
	
//	public VehicleStudy(JFrame mainFrame) {
//		this();
//		this.mainFrame = mainFrame;
//	}
	
	class ChangeBg extends Thread {
		String vehicleArr[] = {"구급차","기차","배","버스","비행기","소방차","자동차","택시","헬리콥터"};
		@Override
		public void run() {
			int count = 0;
			for(int i=0; i<vehicleArr.length; i++) {
				try {
					Thread.sleep(2000);
					Image img = new ImageIcon("study/탈것/"+vehicleArr[i]+".png").getImage().getScaledInstance(1200, 900, 0);
					vehicle.setIcon(new ImageIcon(img));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				count++;
				if(count==9){
					new StudyEnd().setVisible(true);
					setVisible(false);
					break;
				}
			}
		}	
	}
}
