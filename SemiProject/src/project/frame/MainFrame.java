package project.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5264326050881840346L;

	public MainFrame() {
		// 메인 화면 구성 및 이벤트 추가
		super("샘과 함께");
		setBounds(360, 90, 1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		Image bgImage = new ImageIcon("resource/image/bg/title.png").getImage().getScaledInstance(1200, 900, 0);
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
//			
//		};
		
		JButton studyButton = new JButton("학습하기");
		studyButton.setBounds(30, 250, 300, 150);
//		studyButton.setOpaque(false);
		studyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new StudyChoiceFrame();
				dispose();
			}
		});
		
		JButton gameButton = new JButton("놀이학습");
		gameButton.setBounds(370, 250, 300, 150);
//		gameButton.setOpaque(false);
		gameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameChoiceFrame();
				dispose();
			}
		});
		
//		JScrollPane bgPane = new JScrollPane(bgPanel);
//		setContentPane(bgPane);
	
		getContentPane().add(studyButton);
	    getContentPane().add(gameButton);
		
		setVisible(true);
	}
}
