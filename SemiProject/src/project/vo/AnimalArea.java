package project.vo;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;

public class AnimalArea extends Rectangle{ // 들어갈 수 있는 공간. 무언가 들어왔을 때 이벤트 발생
	
	public static final int SIZE_HORIZONAL = 250;
	public static final int SIZE_VERTICAL = 250;
	// 위는 고정 크기
	
	private String answer; // 들어갈 수 있는 아이템들의 정답. 이름과 비교 예정
	private Point direction; // 해당 공간의 좌표
	
	public AnimalArea() {
		answer = "None";
	}
	
	public AnimalArea(String answer, Point direction) {
		this.answer = answer;
		this.direction = direction;
		setBounds(direction.x, direction.y, SIZE_HORIZONAL, SIZE_VERTICAL);
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
