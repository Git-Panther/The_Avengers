package project.vo;

import java.awt.Point;

import javax.swing.ImageIcon;

public class AnimalImage extends ImageIcon { // 선택 가능한 이미지
	
	public static final int SIZE_HORIZONAL = 75;
	public static final int SIZE_VERTICAL = 75;
	// 위는 고정 크기
	
	private String name; // 해당 이미지의 이름
	private Point originalLocation; // 해당 이미지의 원래 좌표. 쓸 일 없을 듯?
	private Point currentLocation; // 해당 이미지가 동물원에 있을 때의 좌표. 쓸 일 없을 듯?
	private String imageLocation; // 해당 이미지의 이미지 경로
	
	public AnimalImage(String name, String imageLocation) {
		super(new ImageIcon(imageLocation).getImage().getScaledInstance(SIZE_HORIZONAL, SIZE_VERTICAL, 0));
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getLocation() {
		return originalLocation;
	}

	public void setOriginalLocation(Point location) {
		this.originalLocation = location;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 4513748695459482282L;
	
}
