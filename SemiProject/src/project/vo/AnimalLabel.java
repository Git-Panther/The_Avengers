package project.vo;

import java.awt.Point;

import javax.swing.JLabel;

public class AnimalLabel extends JLabel { // 동물 담는 레이블

	/**
	 * 
	 */
	private static final long serialVersionUID = 1136494144146454580L;

	private String name; // 동물 이름
	private Point originalPoint; // 해당 이미지의 원래 좌표. 되돌아가거나 최종 좌표를 찍을 때 씀
	
	public AnimalLabel(AnimalImage image) {
		super(image);
		name = image.getName();
	}
	
	public String getName() {
		return name;
	}
	
	public Point getOriginalPoint() {
		return originalPoint;
	}

	public void setOriginalPoint(Point point) {
		this.originalPoint = point;
	}
}
