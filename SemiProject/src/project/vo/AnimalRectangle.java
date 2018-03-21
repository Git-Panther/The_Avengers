package project.vo;

import java.awt.Point;
import java.awt.Rectangle;

public class AnimalRectangle extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7098386684760262672L;

	public static final int ZOO_HORIZONTAL = AnimalImage.SIZE_HORIZONTAL * 3;
	public static final int ZOO_VERTICAL = AnimalImage.SIZE_VERTICAL * 3;
	
	private Rectangle[] subRectangle = new Rectangle[9]; // 작은 9개의 사각형
	private AnimalLabel[] animals = new AnimalLabel[9]; // 작은 9개 사각형에 들어갈 동물들
	private String answer; // 우리의 정답
	private Point point; // 좌상의 좌표
	
	public AnimalRectangle(String answer, Point point) { // 표준 생성자 1 
		this.answer = answer;
		this.point = point;
		setBounds(this.point.x, this.point.y, ZOO_HORIZONTAL, ZOO_VERTICAL);
		addSubRectangles();
	}
	
	public AnimalRectangle(String answer, int x, int y) { // 표준 생성자 2
		this(answer, new Point(x, y));
	}
	
	public AnimalRectangle() { // 중앙에 배치될 것의 생성자
		this("None", new Point(600 - (ZOO_HORIZONTAL / 2) , 450 - (ZOO_VERTICAL / 2)));
	}
	
	public void addSubRectangles() { // 작은 사각형 좌상 좌표 잡기
		int index = 0;
		for(int x = 0; x <= AnimalImage.SIZE_VERTICAL * 2; x+= AnimalImage.SIZE_VERTICAL) {
			for(int y = 0; y <= AnimalImage.SIZE_HORIZONTAL * 2; y+=AnimalImage.SIZE_HORIZONTAL) {
				subRectangle[index++] = new Rectangle(point.x + y, point.y + x, AnimalImage.SIZE_HORIZONTAL, AnimalImage.SIZE_VERTICAL);
			}
		}	
	}

	public Rectangle[] getSubRectangle() {
		return subRectangle;
	}

	public void setSubRectangle(Rectangle[] subRectangle) {
		this.subRectangle = subRectangle;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public AnimalLabel[] getAnimals() {
		return animals;
	}

	public void setAnimals(AnimalLabel[] animals) { // 문제 창 초기 셋팅
		this.animals = animals;
		for(int x = 0; x < animals.length; x++) {
				animals[x].setOriginalPoint(subRectangle[x].getLocation());
				animals[x].setBounds(subRectangle[x]);
				animals[x].repaint();
		}
	}
	
	public int addAnimal(AnimalLabel animal) { // 비어있는 공간에 동물 넣어버린다. 그리고 비었던 x 좌표를 반환
		for(int x = 0; x < animals.length; x++) {
			if(animals[x] == null) {
				animals[x] = animal; // 요 추가가 빠진 게 원인(...)		
				animals[x].setOriginalPoint(subRectangle[x].getLocation());
				animals[x].setLocation(animals[x].getOriginalPoint());
				animals[x].repaint();
				return x;
			}
		}
		
		return -1;
	}
	
	public int removeAnimal(AnimalLabel animal) { // 동물을 뺄 때. 그리고 인덱스도 반환
		for(int x = 0; x < animals.length; x++) {
			if(animal.equals(animals[x])) {
				animals[x] = null;
				return x;
			}
		}
		
		return -1;
	}
	
	public AnimalLabel getAnimal(int index) { // 동물 추출
		return animals[index];
	}
	
	public void addAnimalAt(AnimalLabel animal, int index) { // 지정된 위치에 넣어주고 위치 재설정
		animals[index] = null;
		animals[index] = animal;
		animals[index].setOriginalPoint(subRectangle[index].getLocation());		
		animals[index].setLocation(animals[index].getOriginalPoint());
		animals[index].repaint();
	}
}
