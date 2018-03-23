package samMain.play.zoo.vo;

import java.awt.Point;

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

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return contains(new Point(x, y));
	}
	
	@Override
	public boolean contains(Point point) { // 오버라이드하여 범위 내에 들어왔으면 옮기는걸로 ㅋ
		// TODO Auto-generated method stub
		if(getLocation().x <= point.x 
				&& getLocation().x + AnimalImage.ANIMAL_WIDTH >= point.x
				&& getLocation().y <= point.y
				&& getLocation().y + AnimalImage.ANIMAL_HEIGHT >= point.y)
			return true;
		else
			return false;
	}
}
