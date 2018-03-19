package project.vo;

import java.awt.CardLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Area extends JPanel { // 들어갈 수 있는 공간
	
	public static final int SIZE_HORIZONAL = 200;
	public static final int SIZE_VERTICAL = 200;
	// 위는 고정 크기
	
	private String answer; // 들어갈 수 있는 아이템들의 정답. 이름과 비교 예정
	private Point direction; // 해당 공간의 좌표
	
	public Area(String answer, Point direction) {
		this.answer = answer;
		this.direction = direction;
		this.setLayout(new CardLayout());
		setBounds(direction.x, direction.y, SIZE_HORIZONAL, SIZE_VERTICAL);
		setOpaque(false);
	}
	
	public void addImage(JLabel label) {
		this.add(label);
	}
	
	public void removeImage(JLabel label) {
		this.remove(label);
	}	
	
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Point getDirection() {
		return direction;
	}

	public void setDirection(Point direction) {
		this.direction = direction;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3221744701630260882L;

}
