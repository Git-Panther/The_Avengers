package project.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import project.game.CartGame;
import project.game.CleaningRoomGame;
import project.game.TouchingBodyGame;
import project.game.ZooGame;

public class GameChoiceFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 976798513365226583L;

	public GameChoiceFrame() {
		// 화면 생성. 뒤로 가면 메인 화면, 선택하고 진행하면 게임 화면
		super("샘과 함께");
		setBounds(360, 90, 1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		Image bgImage = new ImageIcon("resource/image/bg/game.png").getImage();
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
		
		JButton[][] buttons = new JButton[2][2];
		
		buttons[0][0] = new JButton("슈퍼마켓");
		buttons[0][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameFrame(new CartGame());
				dispose();
			}
		});
		buttons[0][1] = new JButton("정리정돈");
		buttons[0][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameFrame(new CleaningRoomGame());
				dispose();
			}
		});
		buttons[1][0] = new JButton("신체부위");
		buttons[1][0].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameFrame(new TouchingBodyGame());
				dispose();
			}
		});
		buttons[1][1] = new JButton("동물원");
		buttons[1][1].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameFrame(new ZooGame());
				dispose();
			}
		});
		
		int baseX = 160, baseY = 160, width = 250, height = 125;
		for(JButton[] buttonGroup : buttons) {
			for(JButton button : buttonGroup) {
				button.setBounds(baseX, baseY, width, height);
				baseX+=270;
			}
			baseX = 160;
			baseY+=160;
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
