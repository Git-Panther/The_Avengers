package samMain.play.zoo.vo;

import javax.swing.ImageIcon;

public class AnimalImage extends ImageIcon { // 선택 가능한 이미지
	private static final long serialVersionUID = 4513748695459482282L;
	
	public static final int ANIMAL_WIDTH = 90;
	public static final int ANIMAL_HEIGHT = 90;
	// 이미지 크기
	
	private String name; // 해당 이미지의 이름
	private String imageLocation; // 해당 이미지의 이미지 경로
	
	public AnimalImage(String name, String imageLocation) {
		super(new ImageIcon(imageLocation).getImage().getScaledInstance(ANIMAL_WIDTH, ANIMAL_HEIGHT, 0));
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
}
