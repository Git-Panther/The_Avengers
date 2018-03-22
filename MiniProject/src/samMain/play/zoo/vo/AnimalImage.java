package samMain.play.zoo.vo;

import javax.swing.ImageIcon;

public class AnimalImage extends ImageIcon { // 선택 가능한 이미지
	
	public static final int SIZE_HORIZONTAL = 80;
	public static final int SIZE_VERTICAL = 80;
	// 이미지 크기
	
	private String name; // 해당 이미지의 이름
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

	private static final long serialVersionUID = 4513748695459482282L;
	
}
