package project.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		
		Image bgImage = new ImageIcon("resource/image/bg/title.png").getImage();
		JPanel bgPanel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6410213718089658288L;

			@Override
			public void paintComponent(Graphics bg) {
				// TODO Auto-generated method stub
				bg.drawImage(bgImage, 0, 0, 1200, 900, null);
				setOpaque(false);
				super.paintComponent(bg);
			}
			
		};
		
		JButton studyButton = new JButton("학습하기");
		studyButton.setBounds(30, 250, 300, 150);
		studyButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JButton gameButton = new JButton("놀이학습");
		gameButton.setBounds(370, 250, 300, 150);
		gameButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane bgPane = new JScrollPane(bgPanel);
		setContentPane(bgPane);
		add(studyButton);
		studyButton.setVisible(true);
		add(gameButton);
		gameButton.setVisible(true);
		
		setVisible(true);
	}
}
