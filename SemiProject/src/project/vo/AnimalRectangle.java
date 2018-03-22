package project.vo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import project.frame.GameFrame;
import project.game.ZooGame;

public class AnimalRectangle extends Rectangle {
	private static final long serialVersionUID = 7098386684760262672L;

	public static final int ZOO_WIDTH = 3;
	public static final int ZOO_HEIGHT = ZooGame.AMOUNT_OF_ANIMAL / ZOO_WIDTH;
	// 동물원 칸의 가로 세로 (가로 m개, 세로 n개)
	// 세로는 가로 개수로 나눈 값. 가로 * 세로가 성립해야 하기 때문.
	
	public static final int ZOO_HORIZONTAL = AnimalImage.SIZE_HORIZONTAL * ZOO_WIDTH;
	public static final int ZOO_VERTICAL = AnimalImage.SIZE_VERTICAL * ZOO_HEIGHT;
	// 동물원 우리 하나의 크기는 동물의 m * n배(넓이 m * n배)
	
	public static final int ZOO_LABEL_HEIGHT = 30; // 동물원 우리 이름표의 세로 길이
	
	private Rectangle[] subRectangle = new Rectangle[ZooGame.AMOUNT_OF_ANIMAL]; // 작은 n개의 사각형
	private AnimalLabel[] animals = new AnimalLabel[ZooGame.AMOUNT_OF_ANIMAL]; // 작은 n개 사각형에 들어갈 동물들
	private String answer; // 우리의 정답
	private Point point; // 좌상의 좌표
	private JLabel zooLabel; // 해당 우리가 무슨 우리인지 표시한다. 자고로 문제 창에는 안 쓰일 예정
	
	public AnimalRectangle(String answer, Point point) { // 표준 생성자 1(모든 생성자는 여기로 통한다)
		this.answer = answer;
		this.point = point;
		setBounds(this.point.x, this.point.y, ZOO_HORIZONTAL, ZOO_VERTICAL);
		zooLabel = new JLabel();
		zooLabel.setBounds(this.point.x, this.point.y + ZOO_VERTICAL, ZOO_HORIZONTAL, ZOO_LABEL_HEIGHT);
		zooLabel.setForeground(new Color(47, 79, 79));
		zooLabel.setHorizontalAlignment(SwingConstants.CENTER);
		zooLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		addSubRectangles();
	}
	
	public AnimalRectangle(Point point) { // 표준 생성자 2(좌상 좌표만 주었을 때)
		this("None", point);
	}
	
	public AnimalRectangle(String answer, int x, int y) { // 표준 생성자 3(정답은 주되, 포인트가 아닌 x와 y를 따로 줄 때)
		this(answer, new Point(x, y));
	}
	
	public AnimalRectangle() { // 중앙에 배치될 것의 생성자(문제 틀 : 시작할 때 동물들이 모여있음)
		this("None", new Point((GameFrame.WINDOW_WIDTH - ZOO_HORIZONTAL) / 2 , (GameFrame.WINDOW_HEIGHT - ZOO_VERTICAL) / 2));
	}
	
	public void addSubRectangles() { // 작은 사각형 n개(동물들이 들어갈 곳)의 좌상 좌표 잡기
		int index = 0;
		for(int x = 0; x <= AnimalImage.SIZE_VERTICAL * (ZOO_WIDTH - 1); x+= AnimalImage.SIZE_VERTICAL) {
			for(int y = 0; y <= AnimalImage.SIZE_HORIZONTAL * (ZOO_HEIGHT - 1); y+=AnimalImage.SIZE_HORIZONTAL) {
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
				animals[x] = animal;	
				animals[x].setOriginalPoint(subRectangle[x].getLocation());
				animals[x].setLocation(animals[x].getOriginalPoint());
				animals[x].repaint();
				return x;
			}
		}
		
		return -1; // 배열이 꽉 찼으면 -1 반환하여 추가에 실패했음을 표시
	}
	
	public int removeAnimal(AnimalLabel animal) { // 동물을 뺄 때. 그리고 인덱스도 반환
		for(int x = 0; x < animals.length; x++) {
			if(animal.equals(animals[x])) {
				animals[x] = null;
				return x;
			}
		}
		
		return -1; // 제거에 실패했을 때 -1 반환하여 표시
	}
	
	public AnimalLabel getAnimal(int index) { // 인덱스로 동물 추출
		return animals[index];
	}
	
	public void addAnimalAt(AnimalLabel animal, int index) { // 지정된 위치에 넣어주고 들어간 동물의 위치를 넣어준 위치에 맞게 재설정
		animals[index] = animal;
		animals[index].setOriginalPoint(subRectangle[index].getLocation());		
		animals[index].setLocation(animals[index].getOriginalPoint());
		animals[index].repaint();
	}

	public JLabel getZooLabel() { // 동물원 우리 타이틀 반환
		return zooLabel;
	}

	public void setZooLabel(JLabel zooLabel) {
		this.zooLabel = zooLabel;
	}

	public void removeAll() { // 우리 안에 저장된 모든 동물들을 퇴출한다.
		// TODO Auto-generated method stub
		for(int x = 0; x < animals.length; x++)
			animals[x] = null;
	}
}
