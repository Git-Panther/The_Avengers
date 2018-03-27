package samMain.play.zoo.game;

import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.play.zoo.frame.GameFrame;

public abstract class Game {
	protected String bgLocation; // 경로
	protected LinkedList<ImageIcon> imageObjects = new LinkedList<ImageIcon>(); // 이미지 저장용
	protected String quiz; // 문제 메시지
	protected GameFrame frame; // 컨텐츠를 주입할 프레임
	protected JLabel quizLabel; // 퀴즈 레이블
	
	public String getQuiz() {
		return quiz;
	}

	public void setQuiz(String quiz) {
		this.quiz = quiz;
	}

	public String getBackgroundLocation() {
		return bgLocation;
	}
	
	public void setBackgroundLocation(String bgLocation) {
		this.bgLocation = bgLocation;
	}
	
	public abstract void start();
	public abstract void restart();
	public abstract void tryAgain();
	public abstract boolean check();

	public void setGameFrame(GameFrame gameFrame) {
		// TODO Auto-generated method stub
		this.frame = gameFrame;
	}
	
	public GameFrame getGameFrame() {
		return frame;
	}

	public JLabel getQuizLabel() {
		return quizLabel;
	}

	public void setQuizLabel(JLabel quizLabel) {
		this.quizLabel = quizLabel;
	}
	
}
