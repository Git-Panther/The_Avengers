package samMain.play.zoo.frame;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamMain;
import samMain.play.PlayMain;
import samMain.play.zoo.dialog.WarningDialog;
import samMain.play.zoo.game.Game;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 2589215222057371650L;
	// 위 시리얼 id 없으면 warning 창 뜸.
	
	private Game game; // 게임 객체
	private Clip bgm; // 배경음
	
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 900;
	public static final int MONITOR_WIDTH = 1920;
	public static final int MONITOR_HEIGHT = 1080;
	// 창 사이즈 및 화면 사이즈 기호 상수로 정해서 편하게 쓸 수 있다. 
	
	public static final int BUTTON_WIDTH = 180;
	public static final int BUTTON_HEIGHT = 114;
	// 버튼 사이즈
	
	private boolean isClicked = false;
	
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
	}
	
	public void setDefault() { // 기본적인 세팅
		Image bgImage = new ImageIcon(game.getBackgroundLocation()).getImage().getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, 0);
		JLabel bgLabel = new JLabel(new ImageIcon(bgImage));
		setContentPane(bgLabel);
		// 배경 세팅. 사실 여기서부터는 Game 객체에서 해결하는 것이 좋다.
		getContentPane().add(game.getQuizLabel());
		// 문제 세팅
		repaint();
		game.start(); // Game 객체에게 주입받는 형식으로 생성

		JLabel changeButton = new JLabel(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_choice.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
		changeButton.setBounds(WINDOW_WIDTH - 439, WINDOW_HEIGHT - 156, BUTTON_WIDTH, BUTTON_HEIGHT);
		changeButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				changeButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_choice.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				if(isClicked) {
					WarningDialog dialog = new WarningDialog(GameFrame.this, "게임 바꾸기", "정말로 게임 선택 창으로 이동하시겠습니까?");
					dialog.getNegativeButton().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							dialog.dispose();
						}
					});
					
					dialog.getPositiveButton().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							dialog.dispose();
							GameFrame.this.dispose();
							new PlayMain().setVisible(true);
						}
					});
					
					dialog.setVisible(true);
					isClicked = false;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				changeButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_choice_press.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				changeButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_choice.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				changeButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_choice_focus.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel mainButton = new JLabel(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_main.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
		mainButton.setBounds(WINDOW_WIDTH - 222, WINDOW_HEIGHT - 156, BUTTON_WIDTH, BUTTON_HEIGHT);
		mainButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mainButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_main.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				if(isClicked) {
					WarningDialog dialog = new WarningDialog(GameFrame.this, "메인 화면으로 이동", "정말로 메인 화면으로 이동하시겠습니까?");
					dialog.getNegativeButton().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							dialog.dispose();
						}
					});
					
					dialog.getPositiveButton().addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e1) {
							// TODO Auto-generated method stub
							dialog.dispose();
							GameFrame.this.dispose();
							new SamMain();
						}
					});
					
					dialog.setVisible(true);
					isClicked = false;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				mainButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_main_press.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				mainButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_main.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				mainButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/back_main_focus.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		JLabel checkButton = new JLabel(new ImageIcon(new ImageIcon("resource/image/zoo/button/check.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
		checkButton.setBounds(WINDOW_WIDTH - 666, WINDOW_HEIGHT - 156, BUTTON_WIDTH, BUTTON_HEIGHT);
		checkButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/check.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				if(isClicked) {
					game.check();
					isClicked = false;
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/check_press.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = true;
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/check.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
				isClicked = false;
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(new ImageIcon(new ImageIcon("resource/image/zoo/button/check_focus.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0)));
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
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
