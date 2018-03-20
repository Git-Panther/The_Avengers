package project.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.game.Game;

public class GameFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2589215222057371650L;

	// 이미지는 레이블 안에 넣고 레이블의 부모는 그때그때 패널로 바꿔준다.

	private Game game; // 게임 객체
	private JLabel questionMessage; // 문제 메시지

	public GameFrame(Game game) {
		// 화면 생성
		super("샘과 함께");
		this.setGame(game);
		setBounds(360, 90, 1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);

		Image bgImage = new ImageIcon(game.getBackgroundLocation()).getImage().getScaledInstance(1200, 900, 0);
		JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
		setContentPane(bgLabel);
		// 배경 세팅. 사실 여기서부터는 Game 객체에서 해결하는 것이 좋다.
		
		questionMessage = new JLabel();
		questionMessage.setForeground(new Color(47, 79, 79));
		questionMessage.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		questionMessage.setHorizontalAlignment(SwingConstants.CENTER);
		questionMessage.setBounds(0, 650, 1200, 100);
		getContentPane().add(questionMessage);
		
		game.start(this);
		repaint();

		JButton changeButton = new JButton("게임 바꾸기");
		changeButton.setBounds(775, 800, 150, 50);
		changeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameChoiceFrame();
				dispose();
			}
		});

		JButton mainButton = new JButton("메인 화면");
		mainButton.setBounds(950, 800, 150, 50);
		mainButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});

		JButton checkButton = new JButton("완료");
		checkButton.setBounds(525, 800, 150, 50);
		checkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (game.check(GameFrame.this)) {
					System.out.println("정답");
				} else {
					System.out.println("오답");
				}
			}
		});

		getContentPane().add(mainButton);
		getContentPane().add(changeButton);
		getContentPane().add(checkButton);

		setVisible(true);
		// System.out.println("창 완료");
	}
	
	public JLabel getQuestionMessage() { // 문제 레이블 반환
		return questionMessage;
	}

	public void setQuestionMessage(JLabel questionMessage) { // 문제 레이블 설정
		this.questionMessage = questionMessage;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
