package samMain.study;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamBgmClip;
import samMain.main.SamMain;


public class StudyMain extends JFrame {

	private static final long serialVersionUID = -4247468068526345561L;
	JLabel background;
	JButton fruit,animal,tool,body,color,vehicle,end,help;
	public static int again=0;
	private SamBgmClip bgm = SamBgmClip.getClip(); // BGM 클립
	public StudyMain(){
		
		bgm.on();
		setTitle("샘과함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		setBounds(350, 60, 1200, 930);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		setLayout(null);		
		setResizable(false);
		
		background = new JLabel(new ImageIcon(new ImageIcon("mainView/학습하기.png").getImage()));
		background.setBounds(0, 0, 1200, 900);
		add(background);
		
		fruit = new JButton(new ImageIcon(new ImageIcon("mainView/png/과일.png").getImage().getScaledInstance(240, 127, 0))); //버튼에 이미지삽입
		fruit.setBounds(220, 220, 240, 127); //x,y,크기
		fruit.setBorderPainted(false); // 테두리없애는것
		fruit.setFocusPainted(false); 
		add(fruit); //프레임에 올리기
		
		fruit.addActionListener(new ActionListener() {			//클릭했을때 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(fruit)){
					setAgain(1);  //생략하셔도됨
					FruitStudy fruitStudy = new FruitStudy();
					fruitStudy.setVisible(true);
					setVisible(false);
				}				
			}
		});
		
		fruit.addMouseListener(new MouseAdapter() { //버튼이미지 움직이게하는것

			@Override
			public void mouseEntered(MouseEvent e) {			//마우스 들어왔을때
				fruit.setIcon(new ImageIcon(new ImageIcon("mainView/gif/과일.gif").getImage().getScaledInstance(240, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) { //다시나갔을때
				fruit.setIcon(new ImageIcon(new ImageIcon("mainView/png/과일.png").getImage().getScaledInstance(240, 127, 0)));	
				
			}
			
		});
			
		animal = new JButton(new ImageIcon(new ImageIcon("mainView/png/동물.png").getImage().getScaledInstance(245, 127, 0)));
		animal.setBounds(477, 214, 245, 127);
		animal.setBorderPainted(false);
		animal.setFocusPainted(false);
		add(animal); 
		
		animal.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(animal)){
					setAgain(2);
					AnimalStudy animalStudy = new AnimalStudy();
					setVisible(false);
					animalStudy.setVisible(true);
				}				
			}
		});
		
		animal.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				animal.setIcon(new ImageIcon(new ImageIcon("mainView/gif/동물.gif").getImage().getScaledInstance(245, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				animal.setIcon(new ImageIcon(new ImageIcon("mainView/png/동물.png").getImage().getScaledInstance(245, 127, 0)));	
				
			}
			
		});
		
		tool = new JButton(new ImageIcon(new ImageIcon("mainView/png/학용품.png").getImage().getScaledInstance(245, 127, 0)));
		tool.setBounds(736, 216, 245, 127);
		tool.setBorderPainted(false);
		tool.setFocusPainted(false);
		add(tool); 
		
		tool.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(tool)){
					setAgain(3);
					ToolStudy toolStudy = new ToolStudy();
					toolStudy.setVisible(true);
					setVisible(false);
				}				
			}
		});
		
		tool.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				tool.setIcon(new ImageIcon(new ImageIcon("mainView/gif/학용품.gif").getImage().getScaledInstance(245, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				tool.setIcon(new ImageIcon(new ImageIcon("mainView/png/학용품.png").getImage().getScaledInstance(245, 127, 0)));	
				
			}
			
		});
	
		body = new JButton(new ImageIcon(new ImageIcon("mainView/png/신체부위.png").getImage().getScaledInstance(245, 127, 0)));
		body.setBounds(213, 405, 245, 127);
		body.setBorderPainted(false);
		body.setFocusPainted(false);
		add(body); 
		
		body.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				setAgain(4);
				if(e.getSource().equals(body)){
					BodyStudy bodyStudy = new BodyStudy();
					setVisible(false);
					bodyStudy.setVisible(true);
				}				
			}
		});
		
		body.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				body.setIcon(new ImageIcon(new ImageIcon("mainView/gif/신체부위.gif").getImage().getScaledInstance(245, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				body.setIcon(new ImageIcon(new ImageIcon("mainView/png/신체부위.png").getImage().getScaledInstance(245, 127, 0)));	
				
			}
			
		});
	
		color = new JButton(new ImageIcon(new ImageIcon("mainView/png/색.png").getImage().getScaledInstance(245, 127, 0)));
		color.setBounds(472, 404, 245, 127);
		color.setBorderPainted(false);
		color.setFocusPainted(false);
		add(color); 
		
		color.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(color)){
					setAgain(5);
					ColorStudy colorStudy = new ColorStudy();
					setVisible(false);
					colorStudy.setVisible(true);
				}				
			}
		});
		
		color.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				color.setIcon(new ImageIcon(new ImageIcon("mainView/gif/색.gif").getImage().getScaledInstance(245, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				color.setIcon(new ImageIcon(new ImageIcon("mainView/png/색.png").getImage().getScaledInstance(245, 127, 0)));	
				
			}
			
		});
		
		
		
		vehicle = new JButton(new ImageIcon(new ImageIcon("mainView/png/탈것.png").getImage().getScaledInstance(245, 127, 0)));
		vehicle.setBounds(734, 404, 245, 127);
		vehicle.setBorderPainted(false);
		vehicle.setFocusPainted(false);
		add(vehicle); 
		
		vehicle.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(vehicle)){
					setAgain(6);
					VehicleStudy vehicleStudy = new VehicleStudy();
					setVisible(false);
					vehicleStudy.setVisible(true);
				}				
			}
		});
		
		vehicle.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {			
				vehicle.setIcon(new ImageIcon(new ImageIcon("mainView/gif/탈것.gif").getImage().getScaledInstance(245, 127, 0)));		
			}
			@Override
			public void mouseExited(MouseEvent e) {
				vehicle.setIcon(new ImageIcon(new ImageIcon("mainView/png/탈것.png").getImage().getScaledInstance(245, 127, 0)));	
				
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
					new SamMain();
					setVisible(false);
					
				}				
			}
		});	
		
		help = new JButton(new ImageIcon(new ImageIcon("mainView/png/도움말.png").getImage().getScaledInstance(80, 42, 0)));
		help.setBounds(1100, 850, 80, 42);
		help.setBorderPainted(false);
		help.setFocusPainted(false);
		background.add(help); 
		
		help.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource().equals(help)){
					new ExplainDialog();				
				}				
			}
		});	
		
		
		addWindowListener(new WindowAdapter(){
			
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
				bgm.resumeBGM();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.resumeBGM();
			}
		});
		
	
		
	}

	
	 class ExplainDialog extends JDialog {
		private static final long serialVersionUID = -3378020648978160487L;

		public ExplainDialog() {
	         // 다이얼로그 위치 게임화면 중앙.
	         setBounds((background.getWidth() - 540)  + background.getX(), (background.getHeight() - 100) / 2 + background.getY(), 500,
	               200);
	         setLayout(null);
	         setUndecorated(true);
	         setBackground(new Color(0, 0, 0, 1));
	         setModal(true);

	         ImageIcon bg = new ImageIcon(new ImageIcon("images/myDialogBgImg.png").getImage());
	         JLabel bgLabel = new JLabel(bg);
	         bgLabel.setBounds(0, 0, getWidth(), getHeight());
	         setContentPane(bgLabel);
	         
	         JLabel textLabel = new JLabel("각 주제별 10개의 카드가 랜덤으로 나타납니다.");
	         JLabel textLabel2 = new JLabel("화면에 집중하세요!!");
	         textLabel.setBounds(85, 0, 400, 150);
	         textLabel.setFont(new Font("상상토끼 신비는 일곱살", Font.PLAIN, 34));
	         textLabel2.setBounds(170, 30, 400, 150);
	         textLabel2.setFont(new Font("상상토끼 신비는 일곱살", Font.PLAIN, 35));
	         getContentPane().add(textLabel);
	         getContentPane().add(textLabel2);
	         
	         JButton yesBtn = new JButton(new ImageIcon("images/ok.png"));
	         yesBtn.setContentAreaFilled(false);
	         yesBtn.setBorderPainted(false);
	         yesBtn.setFocusPainted(false);

	         yesBtn.setBounds(200, 130, 63, 50);
	         
	         getContentPane().add(yesBtn);
	         
	         yesBtn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	               dispose();
	            }
	         });
	         
	         yesBtn.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	               yesBtn.setIcon(new ImageIcon("images/pressedOk.png"));
	            }
	            @Override
	            public void mouseExited(MouseEvent e) {
	               yesBtn.setIcon(new ImageIcon("images/ok.png"));
	            }
	         });
	         setVisible(true);
	      
	      }
	   }

	public int getAgain() {
		return StudyMain.again;
	}

	public void setAgain(int again) {
		StudyMain.again = again;
	}

}