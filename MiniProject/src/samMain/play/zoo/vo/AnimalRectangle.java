package samMain.play.zoo.vo;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.LinkedList;

public class AnimalRectangle extends Rectangle {
	private static final long serialVersionUID = 7098386684760262672L;
	
	// 이제 동물원의 가로 길이와 세로 길이는 다 달라질 것이다.
	private String answer; // 우리의 정답
	private LinkedList<AnimalLabel> animals = new LinkedList<AnimalLabel>();
	
	public AnimalRectangle(String answer, Point point, int width, int height) { // 표준 생성자 1(모든 생성자는 여기로 통한다)
		this.answer = answer;
		setLocation(point);
		setBounds(getLocation().x, getLocation().y, width, height); // 이제 우리의 크기는 가변이고, 우리에 마우스 포인터가 들어가는 순간 놓아질 것이다.
	}
	
	public AnimalRectangle(Point point, int width, int height) { // 표준 생성자 2(좌상 좌표, 가로 및 세로 주었을 때)
		this("None", point, width, height);
	}
	
	public AnimalRectangle(String answer, int x, int y, int width, int height) { // 표준 생성자 3(정답은 주되, 포인트가 아닌 x와 y를 따로 줄 때)
		this(answer, new Point(x, y), width, height);
	}
	
	public AnimalLabel getAnimal(int index) { // 인덱스로 동물 추출
		return animals.get(index);
	}

	public LinkedList<AnimalLabel> getAnimals() {
		return animals;
	}

	public void setAnimals(LinkedList<AnimalLabel> animals) {
		this.animals = animals;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public boolean contains(int x, int y) {
		// TODO Auto-generated method stub
		return contains(new Point(x, y));
	}

	@Override
	public boolean contains(Point p) {
		// TODO Auto-generated method stub
		if(p.x >= getLocation().x 
				&& p.x <= getLocation().x + getBounds().width
				&& p.y >= getLocation().y
				&& p.y <= getLocation().y +getBounds().height)
			return true;
		else
			return false;
	}
}
