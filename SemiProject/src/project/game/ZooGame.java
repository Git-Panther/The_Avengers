package project.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import project.frame.GameFrame;
import project.vo.AnimalImage;
import project.vo.AnimalLabel;
import project.vo.AnimalRectangle;

public class ZooGame extends Game {

	private LinkedList<AnimalRectangle> zooRectangle = new LinkedList<AnimalRectangle>(); // 동물원 우리
//	private LinkedList<AnimalImage> animalImages = new LinkedList<AnimalImage>(); // 랜덤으로 할당된 동물들
	private LinkedList<AnimalLabel> animalLabels = new LinkedList<AnimalLabel>(); // 랜덤으로 할당된 동물들을 감쌀 레이블
	private AnimalRectangle startingRectangle; // 랜덤으로 할당된 동물의 레이블이 들어갈 공간

	private AnimalLabel movingLabel; // 움직이는 레이블(또는 이미지)	
	private AnimalRectangle movingLabelParent; // 움직이는 레이블이 움직이기 전에 있던 사각형 범위. 9개 합친 큰 덩어리. 빽할 때 필요.
	private int movingLabelIndex; // 뺐을 때 해당 레이블이 있었던 인덱스. 원위치 시킬 때 필요.
	
	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표
	
	public ZooGame() {		
		bgLocation = "resource/image/bg/zoo.jpg";
		quiz = "동물들을 우리로 돌려보내세요.";
		// 문제의 동물들
		imageObjects.add(new AnimalImage("Dog", "resource/image/character/dog.png"));
		imageObjects.add(new AnimalImage("Giraffe", "resource/image/character/giraffe.png"));
		imageObjects.add(new AnimalImage("Hare", "resource/image/character/hare.png"));
		imageObjects.add(new AnimalImage("Lion", "resource/image/character/lion.png"));
		imageObjects.add(new AnimalImage("Monkey", "resource/image/character/monkey.png"));
		imageObjects.add(new AnimalImage("Owl", "resource/image/character/owl.png"));
		
		// 정답 공간
		zooRectangle.add(new AnimalRectangle("Dog", new Point(50, 0)));
		zooRectangle.add(new AnimalRectangle("Giraffe", new Point(450, 0)));
		zooRectangle.add(new AnimalRectangle("Hare", new Point(850, 0)));
		zooRectangle.add(new AnimalRectangle("Lion", new Point(50, 300)));
		zooRectangle.add(new AnimalRectangle("Monkey", new Point(900, 450)));
		zooRectangle.add(new AnimalRectangle("Owl", new Point(50, 600)));
		
		movingLabel = null; // 초기화
		movingLabelParent = null;
		movingLabelIndex = -1;
		
		// 문제의 동물들 배치공간
		startingRectangle = new AnimalRectangle();
	}
	
	@Override
	public void start(GameFrame frame) { // 새로 시작, 게임 엎을 때
		// TODO Auto-generated method stub	
		frame.getQuestionMessage().setText(getQuiz());
		// 문제 텍스트
		
		// 문제 시작시 배치되는 항목 모아놓는 곳	
		putAnimals(); // 동물 9마리 무작위 선출 및 레이블에 저장
		Iterator<AnimalLabel> itrLabel = animalLabels.iterator();
		startingRectangle.setAnimals(animalLabels.toArray(new AnimalLabel[9]));
		
		while (itrLabel.hasNext()) { // 9마리 넣는다.
			frame.getContentPane().add(itrLabel.next());
		}
		
//		// 패널 보이는지 테스트
//		Iterator<AnimalLabel> itrL = animalLabels.iterator();
//		while (itrL.hasNext()) { // 9마리 넣는다.
//			inputtigLabel = itrL.next();
//			inputtingPanel = new JPanel();
//			inputtingPanel.add(inputtigLabel);
//			inputtingPanel.setLocation(inputtigLabel.getLocation());
//			frame.add(inputtingPanel);
//		}
		
		frame.getContentPane().repaint();
		// 동물들을 문제 창에 모아놓는다.
		
		class ZooListener implements MouseListener, MouseMotionListener { // Area에 부여할 리스너. 이제는 Jframe에 부여

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로.
				if(movingLabel != null && movingLabelParent != null) {					
//					if(e.getSource() instanceof AnimalImage) {
//					System.out.println("동물임");
//					}
//					else if(e.getSource() instanceof AnimalArea) {
//						System.out.println("동물공간");
//					}  else if(e.getSource() instanceof AnimalLabel) {
//						System.out.println("동물 레이블");
//					}
//					else {
//						System.out.println("형상변환 불가");
//					}
					// 위를 통해 동물 공간(JPanel)만 인식함을 알 수 있다.
					AnimalRectangle targetRectangle = null;
					Rectangle[] targetSubRectangle = null;
					int addingIndex = -1; // 넣었을 때의 자리 인덱스. 순서대로 채우기 위함.
					
					// 문제 영역에 접근했을 때
					targetRectangle = startingRectangle;
					targetSubRectangle = targetRectangle.getSubRectangle();
					for(int x = 0; x < targetSubRectangle.length; x++) {
						if(targetSubRectangle[x].contains(e.getPoint()) && targetRectangle.getAnimal(x) == null) {
							// 영역에 들어갔는데 그 영역에 동물이 없을 때
							addingIndex = targetRectangle.addAnimal(movingLabel); // 다시 넣어준다. 그리고 넣었을 때의 인덱스 얻는다.
							movingLabel.setOriginalPoint(targetSubRectangle[addingIndex].getLocation());
//							movingLabel.setOriginalPoint(new Point(targetSubRectangle[addingIndex].x, targetSubRectangle[addingIndex].y));
							// 원 좌표를 빈 공간이었던 곳의 좌표로 변경
							movingLabel.setLocation(movingLabel.getOriginalPoint()); // 원 좌표 변경으로 반영
							frame.getContentPane().repaint();
							movingLabel.repaint();
							// 다시 그려준다.
							isDragged = false;
							movingLabel = null;
							movingLabelParent = null; 
							movingLabelIndex = -1;
							System.out.println("옮기기 성공");
							return;
							// 값 초기화하고 찾기 종료
						} else {
							System.out.println(x + "번째 아님");
						}
					}
					
					// 우리 영역 6개 중 하나이면
					Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
					while(itrRectangle.hasNext()) {
						targetRectangle= itrRectangle.next();
						targetSubRectangle = targetRectangle.getSubRectangle();
						for(int x = 0; x < targetSubRectangle.length; x++) {
							if(targetSubRectangle[x].contains(e.getPoint())) {
								addingIndex = targetRectangle.addAnimal(movingLabel); // 다시 넣어준다. 그리고 넣었을 때의 인덱스 얻는다.
								movingLabel.setOriginalPoint(targetSubRectangle[addingIndex].getLocation());
//								movingLabel.setOriginalPoint(new Point(targetSubRectangle[addingIndex].x, targetSubRectangle[addingIndex].y));
								// 원 좌표를 빈 공간이었던 곳의 좌표로 변경
								movingLabel.setLocation(movingLabel.getOriginalPoint()); // 원 좌표 변경으로 반영
								frame.getContentPane().repaint();
								movingLabel.repaint();
								// 다시 그려준다.
								isDragged = false;
								movingLabel = null;
								movingLabelParent = null; 
								movingLabelIndex = -1;
								System.out.println("옮기기 성공");
								return;
								// 값 초기화하고 찾기 종료
							} else{
								System.out.println("다음!");
							}
						}
					}
					
					// 이도저도 아니라면
					movingLabelParent.addAnimalAt(movingLabel, movingLabelIndex); // 원위치에 다시 삽입
					movingLabel.setLocation(movingLabel.getOriginalPoint()); // 원위치로 빽
					frame.getContentPane().repaint();
					movingLabel.repaint();
					// 이동하면 보여줘야 하니
					isDragged = false;
					movingLabel = null;
					movingLabelParent = null; 
					movingLabelIndex = -1;
					System.out.println("느그갤로");
					// 원위치로 그리고 초기화
				} else
					System.out.println("옮기기 실패");
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 눌렀을 때의 좌표 저장
				AnimalRectangle targetRectangle = null;
				Rectangle[] targetSubRectangle = null;
						
				// 문제 영역에서 눌렀을 때
				targetRectangle = startingRectangle;
				targetSubRectangle = targetRectangle.getSubRectangle();
				for(int x = 0; x < targetSubRectangle.length; x++) {
					if(targetSubRectangle[x].contains(e.getPoint())) {
						movingLabel = targetRectangle.getAnimal(x);
						movingLabelParent = targetRectangle;
						movingLabelIndex = movingLabelParent.removeAnimal(movingLabel); // 옮기려면 삭제부터(인덱스도 추출)
						// 객체 정보 저장 : 옮기고 있는 것과 그의 부모를 알기 위해
						mouseX = e.getX() - movingLabel.getX();
						mouseY = e.getY() - movingLabel.getY();
						//눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
						isDragged = true; // 드래그 시작
						frame.getContentPane().repaint();
						movingLabel.repaint();
						System.out.println("인식 성공");
						return; // 이미 찾았으므로 빠져나온다.
					}
				}
				
				// 동물원 우리 1~6 중 하나일 때
				Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
				while(itrRectangle.hasNext()) {
					targetRectangle = itrRectangle.next();
					targetSubRectangle = targetRectangle.getSubRectangle();
					for(int x = 0; x < targetSubRectangle.length; x++) {
						if(targetSubRectangle[x].contains(e.getPoint())) {
							movingLabel = targetRectangle.getAnimal(x);
							movingLabelParent = targetRectangle;
							movingLabelParent.removeAnimal(movingLabel); // 옮기려면 삭제부터
							// 객체 정보 저장 : 옮기고 있는 것과 그의 부모를 알기 위해
							mouseX = e.getX() - targetSubRectangle[x].x;
							mouseY = e.getY() - targetSubRectangle[x].y;
							//눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
							isDragged = true; // 드래그 시작
							frame.getContentPane().repaint();
							movingLabel.repaint();
							System.out.println("인식 성공");
							return; // 이미 찾았으므로 빠져나온다.
						}
					}
				}
				System.out.println("인식 실패");
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 떠났을 때
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 들어왔을 때
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 클릭했을 때
			}
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스가 움직일 때
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				// 마우스를 드래그하고 있을 때 그린다. 정확히는, 
				if(isDragged && movingLabel != null){
					movingLabel.setLocation(e.getX() - mouseX, e.getY() - mouseY);
//					System.out.println("움직이는중");
//					movingLabel.setBounds(e.getX() - mouseX, e.getY() - mouseY, movingLabel.getWidth(), movingLabel.getHeight());
					frame.getContentPane().repaint();
					movingLabel.repaint();
				}
			}
			
		}
	
		frame.getContentPane().addMouseMotionListener(new ZooListener());
		frame.getContentPane().addMouseListener(new ZooListener());
		
		// 가정 1 : 패널도 레이블만큼 만든다(...) -> 패널은 9개까지이므로 9 * 6 = 54 -> 거기서 기본 틀 + 9 => 63
		// (문제는 너무 많아져서 리스트, 이터레이터 써야 하고 좌표는 그대로 0, 0일 것이라는 거다.)
		// 가정 2 : 마우스의 좌표가 아닌, 객체 안에 들어왔는지로 검사하여 옮기게 한다
		// (문제는 코드가 꼬이너가 널 참조가 빈번히 발생할 확률 존재)
		// 가정 3 : 패널이 아닌 사각형 범위를 잡는다. 1 ~ 9번째가 들어갈 수 있는 범위를 Area가 정해준다.
		// (문제는 들어갈 이미지에다가 답을 설정하는 메소드 만들어줘야 한다는 점. 거기다가 범위도 일일히 정해야 하는 노가다. 아무튼 노가다가 제일 많음.)
		// (그래도 이게 가장 쉬울 것이다. 오류도 덜 날 것이고. 비주얼도 볼 만할 것이고.)
		// 2 -> 3 -> 1 순으로 하자. 그래도 안 되면 클릭 방식으로 변경.
		// 결과 2 : e.getSource()는 동물공간만 인식하므로 레이블에 이벤트 넣어주고 그래도 안된다면 3번 ㄱㄱ => 레이블은 조상 관련 이벤트라 ㅈㅈ
		// 3번 : 패널을 제거하고 대신에 패널을 사각형 틀로 교체. 하지만 사각형 클래스에 1~9 좌표 정해줘야 한다. 즉, 거기서도 리스트 만들어야 해(...)
		// 좌표를 정하므로 이미지가 들어갈 이전 좌표와 다음 좌표 설정하는 알고리즘도 세워야 한다.
		// 결과 3 : 객체 이미지가 보이지 않아 실패. 남은 건 1번처럼 개노가다뿐...(적어도 패널 위에서는 보였다.)
		// 1번 상세 정보 : 우리 6개를 9개의 AnimalPanel[9] 객체를 가지는 리스트에 저장. 스타팅패널 역시 [9] 배열로 따로 만듬.
		// 패널에 이름 저장해야 한다. 그리고 패널에서의 이름과 레이블에서의 이름을 비교.
		// 패널을 옮길 때, 검사 후에 패널은 원위치로 하되 접근했다면 레이블만 부모를 바꿔준다.
		// 결과 1 : 
		// 존나게 어렵다면 클릭으로 바꾸던가. 하지만 바꾸기 전에 타 팀원의 프로젝트를 참고할 것.
	}

	@Override
	public void retry(GameFrame frame) { // 실패했을 때 재시도
		// TODO Auto-generated method stub

	}

	@Override
	public boolean check(GameFrame frame) { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면 false다.
		// TODO Auto-generated method stub
	
		// 1. 문제 창 검사
		AnimalLabel[] checkingLabel = startingRectangle.getAnimals(); // 비었는지 검사하기 위해
		for(AnimalLabel label : checkingLabel) {
			if(label != null) {
				System.out.println("다 안 치웠음");
				return false; // 다 안 비었으므로 오답
			}	
		}
		
		// 2. 동물원 검사
		Iterator<AnimalRectangle> zooItr = zooRectangle.iterator();
		AnimalRectangle zoo = null;
		AnimalLabel[] zooLabels = null;
		while(zooItr.hasNext()) {
			zoo = zooItr.next();
			zooLabels = zoo.getAnimals();
			for(int x = 0; x < zooLabels.length; x++) {
				if(zooLabels[x] == null) // 다 왔으면 다음 타자로
					break;
				
				if( !zooLabels[x].getName().equals(zoo.getAnswer()) ) { // 오답이면 
					System.out.println("오답 발견!");
					return false;
				}
			}
		}
		return true; // 검사를 해서 false가 안 뜨면 true다.
	}

	public LinkedList<AnimalImage> putAnimals() {
		// TODO Auto-generated method stub
		LinkedList<AnimalImage> animalObjects = new LinkedList<AnimalImage>();
		
//		int baseX = 100, baseY = 100;
		
		for(int x = 1; x <= 3; x++) {
			for(int y = 1; y <= 3; y++) {
				animalObjects.add((AnimalImage) imageObjects.get( (int)(Math.random() * 6) ));
				animalLabels.add(new AnimalLabel(animalObjects.getLast())); // 여기서 추가해보았다.
//				animalObjects.getLast().setOriginalLocation(new Point(baseX, baseY));
//				baseX += 50;
			}	
//			baseX = 100;
//			baseY += 100;
		}
		
		return animalObjects;
	}
}
