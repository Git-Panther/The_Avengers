package samMain.play.zoo.frame;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import samMain.main.SamMainFrame;
import samMain.play.PlayMain;
import samMain.play.zoo.Adapter.FrameAdapter;
import samMain.play.zoo.button.BGMButton;
import samMain.play.zoo.button.EventButton;
import samMain.play.zoo.clip.BackgroundClip;
import samMain.play.zoo.cursor.ZooCursor;
import samMain.play.zoo.dialog.WarningDialog;
import samMain.play.zoo.game.Game;

public class GameFrame extends JFrame{
	private static final long serialVersionUID = 2589215222057371650L;
	// 위 시리얼 id 없으면 warning 창 뜸.
	
	private Game game; // 게임 객체
	private BackgroundClip bgm; // BGM 클립
	
	public static final int WINDOW_WIDTH = 1210;
	public static final int WINDOW_HEIGHT = 930;
	public static final int MONITOR_WIDTH = 1920;
	public static final int MONITOR_HEIGHT = 1080;
	// 창 사이즈 및 화면 사이즈 기호 상수로 정해서 편하게 쓸 수 있다. 
	
	private EventButton changeButton; // 게임 선택 창으로
	private EventButton mainButton; // 메인 화면으로
	private EventButton checkButton; // 문제 검사
	private BGMButton bgmButton; // 브금 켜기 / 끄기 버튼
	
	public GameFrame(Game game) {
		// 화면 생성
		super("샘과 함께");
		this.setGame(game);
		game.setGameFrame(this);
		setBounds( (MONITOR_WIDTH - WINDOW_WIDTH) / 2 , (MONITOR_HEIGHT - WINDOW_HEIGHT) / 2 , WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		getContentPane().setLayout(null);
		bgm = BackgroundClip.getClip("resource/sound/bgm/zoo.wav"); // BGM 클립
//		if(!bgm.getBGM().isOpen()) { // 닫혀있다면 새로 열어준다.
//		if(!bgm.getBGM().isActive()) {
//		bgm.setBGM("resource/sound/bgm/zoo.wav");
//		}
		setCursor(ZooCursor.getCursor());
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

		changeButton = new EventButton(this, new PlayMain());
		changeButton.setNormalLocation("resource/image/zoo/button/back_choice.png");
		changeButton.setFocusLocation("resource/image/zoo/button/back_choice_focus.gif");
//		changeButton.setPressLocation("resource/image/zoo/button/back_choice_press.png");
		changeButton.setDialog(new WarningDialog(changeButton.getParent(), changeButton.getNextFrame(), "게임 선택 창으로 이동", "정말로 게임 선택 창으로 이동할까요?"));
		changeButton.setOneSide(WINDOW_WIDTH - 439, WINDOW_HEIGHT - 156, EventButton.BUTTON_WIDTH, EventButton.BUTTON_HEIGHT);
		add(changeButton);
		
		mainButton = new EventButton(this, new SamMainFrame().getFrame());
		mainButton.setNormalLocation("resource/image/zoo/button/back_main.png");
		mainButton.setFocusLocation("resource/image/zoo/button/back_main_focus.gif");
//		mainButton.setPressLocation("resource/image/zoo/button/back_main_press.png");
		mainButton.setDialog(new WarningDialog(mainButton.getParent(), mainButton.getNextFrame(),"메인 화면으로 이동", "정말로 메인 화면으로 이동할까요?"));
		mainButton.setOneSide(WINDOW_WIDTH - 222, WINDOW_HEIGHT - 156, EventButton.BUTTON_WIDTH, EventButton.BUTTON_HEIGHT);
		add(mainButton);
		
		checkButton = new EventButton();
		checkButton.setParent(this);
		checkButton.setNormalLocation("resource/image/zoo/button/check.png");
		checkButton.setFocusLocation("resource/image/zoo/button/check_focus.gif");
//		checkButton.setPressLocation("resource/image/zoo/button/check_press.png");
		checkButton.setBounds(WINDOW_WIDTH - 666, WINDOW_HEIGHT - 156, EventButton.BUTTON_WIDTH, EventButton.BUTTON_HEIGHT);
		checkButton.setButtonImages();
		// checkButton은 일반적인 다이얼로그가 아니므로 이벤트는 따로 추가해준다.
		checkButton.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(checkButton.getNormal());
				repaint();
				if(checkButton.isEntered()) {
					game.check();
					checkButton.setEntered(false);
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
//				checkButton.setIcon(checkButton.getPress());
//				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(checkButton.getNormal());
				checkButton.repaint();
				repaint();
				checkButton.setEntered(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				checkButton.setIcon(checkButton.getFocus());
				repaint();
				checkButton.setEntered(true);
			}
		});
		add(checkButton);

		bgmButton = new BGMButton((WINDOW_WIDTH / 2) + 450, (WINDOW_HEIGHT / 2) + 175, 90, 90);
		bgmButton.addDefaultEventListener();
		add(bgmButton);
		
		addWindowListener(new FrameAdapter(bgm) {
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				if(bgm.isOn())
					bgm.resumeBGM();
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				if(bgm.isOn())
					bgm.resumeBGM();
			}
			
			@Override
			public void windowClosing(WindowEvent e) { // 닫히고 있을 때. closing -> 다른 창 opening -> closed이니 주의.
				// TODO Auto-generated method stub
				bgm.pauseBGM();
//				bgm.setContinuous(false);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				bgm.getBGM().close();
			}
		});
	}	
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public EventButton getChangeButton() {
		return changeButton;
	}

	public void setChangeButton(EventButton changeButton) {
		this.changeButton = changeButton;
	}

	public EventButton getMainButton() {
		return mainButton;
	}

	public void setMainButton(EventButton mainButton) {
		this.mainButton = mainButton;
	}

	public EventButton getCheckButton() {
		return checkButton;
	}

	public void setCheckButton(EventButton checkButton) {
		this.checkButton = checkButton;
	}

	public BGMButton getBgmButton() {
		return bgmButton;
	}

	public void setBgmButton(BGMButton bgmButton) {
		this.bgmButton = bgmButton;
	}
}
