package samMain.play.zoo.vo;

import javax.swing.JLabel;

public class AnimalLabel extends JLabel { // 동물 담는 레이블. 이걸 직접 조종한다.
	private static final long serialVersionUID = 1136494144146454580L;

	private String name; // 동물 이름
	
	public AnimalLabel(AnimalImage image) {
		super(image);
		name = image.getName();
		setSize(AnimalImage.ANIMAL_WIDTH, AnimalImage.ANIMAL_HEIGHT); // 사이즈 설정
	}
	
	public String getName() {
		return name;
	}
}
