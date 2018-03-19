package project.frame;

import javax.swing.JFrame;

public class GameChoiceFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 976798513365226583L;

	public GameChoiceFrame() {
		// 화면 생성. 뒤로 가면 메인 화면, 선택하고 진행하면 게임 화면
		super("샘과 함께");
		setVisible(true);
	}
}
