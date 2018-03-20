package project.vo;

import javax.swing.ImageIcon;

public class AnimalImage extends ImageIcon { // 선택 가능한 이미지
	
	public static final int SIZE_HORIZONTAL = 80;
	public static final int SIZE_VERTICAL = 80;
	// 위는 고정 크기
	
	private String name; // 해당 이미지의 이름
//	private Point currentPoint; // 해당 이미지가 동물원에 있을 때의 좌표. 움직일 때마다 바뀜
	private String imageLocation; // 해당 이미지의 이미지 경로
	
	public AnimalImage(String name, String imageLocation) {
		super(new ImageIcon(imageLocation).getImage().getScaledInstance(SIZE_HORIZONTAL, SIZE_VERTICAL, 0));
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageLocation() {
		return imageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.imageLocation = imageLocation;
	}

//	public Point getCurrentPoint() {
//		return currentPoint;
//	}
//
//	public void setCurrentPoint(Point point) {
//		this.currentPoint = point;
//	}



	/**
	 * 
	 */
	private static final long serialVersionUID = 4513748695459482282L;
	
}
