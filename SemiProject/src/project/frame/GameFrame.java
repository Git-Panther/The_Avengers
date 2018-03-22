package project.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
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
	private Clip bgm; // 배경음
	
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 900;
	public static final int MONITOR_WIDTH = 1920;
	public static final int MONITOR_HEIGHT = 1080;
	
	public GameFrame(Game game) {
		// 화면 생성
		super("샘과 함께");
		this.setGame(game);
		game.setGameFrame(this);
		setBounds( (MONITOR_WIDTH - WINDOW_WIDTH) / 2 , (MONITOR_HEIGHT - WINDOW_HEIGHT) / 2 , WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		setBGM();

		setDefault();
		
		setVisible(true);
		// System.out.println("창 완료");
	}
	
	public void setDefault() {
		Image bgImage = new ImageIcon(game.getBackgroundLocation()).getImage().getScaledInstance(1200, 900, 0);
		JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
		setContentPane(bgLabel);
		// 배경 세팅. 사실 여기서부터는 Game 객체에서 해결하는 것이 좋다.
		
		questionMessage = new JLabel();
		questionMessage.setForeground(new Color(47, 79, 79));
		questionMessage.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		questionMessage.setHorizontalAlignment(SwingConstants.CENTER);
		questionMessage.setBounds(0, 650, WINDOW_WIDTH, 100);
		getContentPane().add(questionMessage);
		
		game.start(); // 주입받는 형식으로 생성
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
				game.check();
			}
		});

		getContentPane().add(mainButton);
		getContentPane().add(changeButton);
		getContentPane().add(checkButton);

		addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				resumeBGM();
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				pauseBGM();
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				resumeBGM();
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
//				pauseBGM();
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				pauseBGM();
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				pauseBGM();
				bgm.close();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				resumeBGM();
			}
		});
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
	
	public void setBGM() { // 배경음
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new BufferedInputStream(
							new FileInputStream("resource/sound/bgm/zoo.wav")));
		
			bgm = AudioSystem.getClip();
//			bgm.loop(Clip.LOOP_CONTINUOUSLY);
//			bgm.loop(100);
			bgm.open(ais);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pauseBGM() { // 배경음 일시정지
		if(bgm.isRunning()) {
			bgm.stop();
		}		
	}
	
	public void resumeBGM() { // 배경음 재시작		
		if(!bgm.isRunning() || !bgm.isActive()) // 재생중이 아니면
		{
			bgm.start();
			bgm.loop(Clip.LOOP_CONTINUOUSLY);
			// 멈추고 시작할 때마다 루프가 풀리기에 위의 루프를 재생할 때마다 까줘야 한다.
		}
	}
}
