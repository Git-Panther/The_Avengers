package project.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StudyChoiceFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2131121917217892812L;

	public StudyChoiceFrame() {
		// 창 생성. 뒤로 가면 메인 화면, 선택하고 확인하면 공부화면
		super("샘과 함께");
		setBounds(360, 90, 1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		Image bgImage = new ImageIcon("resource/image/bg/study.png").getImage();
		JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
		setContentPane(bgLabel);
//		JPanel bgPanel = new JPanel() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 6410213718089658288L;
//
//			@Override
//			public void paintComponent(Graphics bg) {
//				// TODO Auto-generated method stub
//				bg.drawImage(bgImage, 0, 0, 1200, 900, null);
//				setOpaque(false);
//				super.paintComponent(bg);
//			}
//		};
		
		JButton mainButton = new JButton("메인 화면");
		mainButton.setBounds(800, 700, 200, 100);
		mainButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		
		JButton[][] buttons = new JButton[2][3];
		
		buttons[0][0] = new JButton("과일");
		buttons[0][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		buttons[0][1] = new JButton("동물");
		buttons[0][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		buttons[0][2] = new JButton("도구");
		buttons[0][2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		buttons[1][0] = new JButton("신체부위");
		buttons[1][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		buttons[1][1] = new JButton("색깔");
		buttons[1][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		buttons[1][2] = new JButton("탈것");
		buttons[1][2].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		
		int baseX = 240, baseY = 240, width = 200, height = 100;
		for(JButton[] buttonGroup : buttons) {
			for(JButton button : buttonGroup) {
				button.setBounds(baseX, baseY, width, height);
				baseX+=260;
			}
			baseX = 240;
			baseY+=180;
		}
		
//		JScrollPane bgPane = new JScrollPane(bgPanel);
//		setContentPane(bgPane);
		for(JButton[] buttonGroup : buttons) {
			for(JButton button : buttonGroup) {
				getContentPane().add(button);
			}
		}
		add(mainButton);
		
		setVisible(true);
	}
}
