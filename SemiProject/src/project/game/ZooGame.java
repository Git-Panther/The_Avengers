package project.game;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.swing.JButton;

import project.dialog.ResultDialog;
import project.frame.GameChoiceFrame;
import project.frame.GameFrame;
import project.frame.MainFrame;
import project.vo.AnimalImage;
import project.vo.AnimalLabel;
import project.vo.AnimalRectangle;

public class ZooGame extends Game {

	private LinkedList<AnimalRectangle> zooRectangle = new LinkedList<AnimalRectangle>(); // 동물원
																							// 우리
	private LinkedList<AnimalLabel> animalLabels = new LinkedList<AnimalLabel>(); // 동물 레이블. 실제로 이걸 옮긴다.
	private AnimalRectangle startingRectangle; // 랜덤으로 할당된 동물의 레이블이 들어갈 공간

	private AnimalLabel movingLabel; // 움직이는 레이블(또는 이미지)
	private AnimalRectangle movingLabelParent; // 움직이는 레이블이 움직이기 전에 있던 사각형 범위.  9개 합친 큰 덩어리. 빽할 때 필요.
	private int movingLabelIndex; // 뺐을 때 해당 레이블이 있었던 인덱스. 원위치 시킬 때 필요.

	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표
	
	public ZooGame() {
		bgLocation = "resource/image/bg/zoo.jpg";
		quiz = "동물들을 제 우리에 맞게 돌려보내세요.";
		// 동물 10종
		imageObjects.add(new AnimalImage("곰", "resource/image/animal/bear.png"));
		imageObjects.add(new AnimalImage("고양이", "resource/image/animal/cat.png"));
		imageObjects.add(new AnimalImage("개", "resource/image/animal/dog.png"));
		imageObjects.add(new AnimalImage("여우", "resource/image/animal/fox.png"));
		imageObjects.add(new AnimalImage("기린", "resource/image/animal/giraffe.png"));
		imageObjects.add(new AnimalImage("코알라", "resource/image/animal/koala.png"));
		imageObjects.add(new AnimalImage("부엉이", "resource/image/animal/owl.png"));
		imageObjects.add(new AnimalImage("양", "resource/image/animal/sheep.png"));
		imageObjects.add(new AnimalImage("호랑이", "resource/image/animal/tiger.png"));
		imageObjects.add(new AnimalImage("얼룩말", "resource/image/animal/Zebra.png"));
		
		initializeFields(); // 주요 변수 초기화
		startingRectangle = new AnimalRectangle(); // 문제의 동물들 배치공간
	}

	class ZooGameListener implements MouseListener, MouseMotionListener {
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로. 대신 접근한 곳이 원 부모와 같은
			// 곳이면 원위치로.
			if (movingLabel != null && movingLabelParent != null) { 
				AnimalRectangle targetRectangle = null;
				Rectangle[] targetSubRectangle = null;
				// int addingIndex = -1; // 넣었을 때의 자리 인덱스. 순서대로 채우기 위함.

				// 문제 영역에 접근했을 때
				targetRectangle = startingRectangle;
				targetSubRectangle = targetRectangle.getSubRectangle();
				if (collocateAnimal(targetRectangle, targetSubRectangle, e)) 
					return;

				// 우리 영역 n개 중 하나이면
				Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
				while (itrRectangle.hasNext()) {
					targetRectangle = itrRectangle.next();
					targetSubRectangle = targetRectangle.getSubRectangle();
					if (collocateAnimal(targetRectangle, targetSubRectangle, e)) 
						return;
				}
				// 사각형들이 아닌 공간에서 뗀 것이라면 이하의 빽이 발생할 것이다.
				back();
			} else {
				// System.out.println("옮기기 실패");
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			// 마우스를 눌렀을 때의 좌표 저장
			// JPanel에 담기에는 애매. 왜냐하면 패널 안에서 따로 좌표를 따질 수도 있는 위험성이 존재
			// 그리고 패널이 좌표를 가지니까 패널 옮기는 방식으로 바꿔야 됨.
			// 따라서 이미지=>레이블=>패널 이런 식으로 해야해서 더러워짐
			// 사실 Panel이나 Label이나 addMouseListener()로 읽을 수 있음 ㅋ
			AnimalRectangle targetRectangle = null;
			Rectangle[] targetSubRectangle = null;

			// 문제 영역에서 눌렀을 때
			targetRectangle = startingRectangle;
			targetSubRectangle = targetRectangle.getSubRectangle();
			if (collocatePoint(targetRectangle, targetSubRectangle, e)) 
				return;

			// 동물원 우리 1~n 중 하나일 때
			Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
			while (itrRectangle.hasNext()) {
				targetRectangle = itrRectangle.next();
				targetSubRectangle = targetRectangle.getSubRectangle();
				if (collocatePoint(targetRectangle, targetSubRectangle, e)) 
					return;
			}
			// 만일 위에서 탈출 못했으면 movingLabel, movingLabelParent가 null이므로 작동 안 할 것이다.
			// System.out.println("인식 실패");
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
			// 마우스를 드래그하고 있을 때 그린다.
			if (isDragged && movingLabel != null && movingLabelParent != null) {
				movingLabel.setLocation(e.getX() - mouseX, e.getY() - mouseY);
				frame.getContentPane().repaint();
				movingLabel.repaint();
			}
		}

	}
	
	@Override
	public void start() { // 새로 시작, 게임 엎을 때
		// TODO Auto-generated method stub
		frame.getQuestionMessage().setText(getQuiz());
		// 문제 텍스트

		// 문제 시작시 배치되는 항목 모아놓는 곳
		putAnimals(); // 공간 n개, 동물 x마리 무작위 선출하여 화면에 쑤신다.
		frame.getContentPane().repaint();
		// 동물들을 문제 창에 모아놓는다.
		frame.getContentPane().addMouseMotionListener(new ZooGameListener());
		frame.getContentPane().addMouseListener(new ZooGameListener());
	}

	@Override
	public void retry() { // 실패했을 때 재시도
		// TODO Auto-generated method stub

		// 동물원 검사
		Iterator<AnimalRectangle> zooItr = zooRectangle.iterator();
		AnimalRectangle zoo = null;
		AnimalLabel[] zooLabels = null;
		AnimalLabel swapingAnimal = null;
		while (zooItr.hasNext()) {
			zoo = zooItr.next();
			zooLabels = zoo.getAnimals();
			for (int x = 0; x < zooLabels.length; x++) {
				if (zooLabels[x] == null) // 다 왔으면 다음 타자로
					continue; // 위치 선정이 자유로워진만큼 모든 배열의 인덱스를 검사해야 한다.

				if (!zooLabels[x].getName().equals(zoo.getAnswer())) { // 오답인 것들은 문제창으로
					swapingAnimal = zooLabels[x];
					zoo.removeAnimal(swapingAnimal); // 해당 우리에서 제거
					startingRectangle.addAnimal(swapingAnimal);	// 시작 창으로 다시 삽입
				}

			}
		}

	}

	@Override
	public boolean check() { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면 false다.
		// TODO Auto-generated method stub
		// 여기서 다이얼로그 만들어줘야 자유롭게 조절 가능(...)
		
		ResultDialog dialog = new ResultDialog(frame, "미정");
		
		// 1. 문제 창 검사. 여기서 안 비었으면 오답 처리
		AnimalLabel[] checkingLabel = startingRectangle.getAnimals(); // 비었는지 검사하기 위한 용도
		for (AnimalLabel label : checkingLabel) {
			if (label != null) {
				dialog.setCorrect(false);
				dialog.setTitle("저런! 다 안 옮겼네요?");
				dialog.getMessageLabel1().setText("아직 동물들이 밖에 남아있어요!");
				dialog.getMessageLabel2().setText("모든 동물들을 돌려보내세요!");
								
				JButton acceptButton = new JButton("확인");
				acceptButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						dialog.dispose(); // 다이얼로그 종료
					}
				});
				dialog.getButtonPanel().add(acceptButton);
				dialog.setVisible(true);
				return false; // 다 안 비었으므로 오답
			}
		}

		// 2. 동물원 검사
		Iterator<AnimalRectangle> zooItr = zooRectangle.iterator();
		AnimalRectangle zoo = null;
		AnimalLabel[] zooLabels = null;
		while (zooItr.hasNext()) {
			zoo = zooItr.next();
			zooLabels = zoo.getAnimals();
			for (int x = 0; x < zooLabels.length; x++) {
				if (zooLabels[x] == null) // 다 왔으면 다음 타자로
					continue; // 위치 선정이 자유로워진만큼 모든 배열의 인덱스를 검사해야 한다.

				if (!zooLabels[x].getName().equals(zoo.getAnswer())) { // 오답이면
					dialog.setCorrect(false);
					dialog.setTitle("땡! 틀렸습니다!");
					dialog.getMessageLabel1().setText("저런, 동물들이 잘못 들어갔아요!");	
					dialog.getMessageLabel2().setText("잘못 들어간 동물들을 다시 돌려보내세요!");
					
					JButton retryButton = new JButton("재시도");
					retryButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							ZooGame.this.retry();
							dialog.dispose(); // 다이얼로그 종료
						}
					});
					dialog.getButtonPanel().add(retryButton);
					
					buildDialog(dialog);
					
					dialog.setVisible(true);
					return false;
				}
			}
		}

		dialog.setCorrect(true);
		dialog.setTitle("정답!");	
		dialog.getMessageLabel1().setText("정말 잘했어요! 동물들이 모두 돌아왔어요!");			
		dialog.getMessageLabel2().setText("다시 한 번 할까요?");
		
		JButton restartButton = new JButton("다시 하기");
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ZooGame.this.restart();
				dialog.dispose(); // 다이얼로그 종료
			}
		});
		dialog.getButtonPanel().add(restartButton);
		buildDialog(dialog);
		
		dialog.setVisible(true);
		return true;
	}

	@Override
	public void restart() { // 재시작
		// TODO Auto-generated method stub
		// 배열 같은 거 싹 다 비우고 시작
		Iterator<AnimalRectangle> itrRect = zooRectangle.iterator();
		AnimalRectangle removingRect = null;
		while(itrRect.hasNext()) {
			removingRect = itrRect.next();
			frame.remove(removingRect.getZooLabel());
			for(AnimalLabel animal : removingRect.getAnimals()) {
				if(animal != null)
					frame.remove(animal);
			}		
		}	
		
		initializeFields();
		zooRectangle.clear();
		animalLabels.clear();
		startingRectangle.removeAll();
		frame.getContentPane().repaint();	
		start();
	}
	
	public void putAnimals() { // 무작위 동물 9마리 쑤심
		// TODO Auto-generated method stub
		// 1. 무작위 동물 n종류를 Set 컬렉션에 넣는다.
		LinkedHashSet<AnimalImage> animalSet = new LinkedHashSet<AnimalImage>();

		while(animalSet.size() < 6) { // 중복 저장 안 되니까 6개 채워질 때까지
			animalSet.add( (AnimalImage) imageObjects.get( (int) (Math.random() * imageObjects.size() ) ) );
		}	
		// 2. AnimalRectangle을 n개만큼 생성하고(zooRectangle에 삽입) 이름은 Set 컬렉션에 들어간 동물의 순서대로 넣어준다.
		int startX = 40;
		int startY = 40;		
		
		zooRectangle
		.add(new AnimalRectangle(new Point(startX, startY)));
		zooRectangle
		.add(new AnimalRectangle(new Point((GameFrame.WINDOW_WIDTH - AnimalRectangle.ZOO_HORIZONTAL) / 2, startY)));
		zooRectangle
		.add(new AnimalRectangle(new Point(GameFrame.WINDOW_WIDTH - startX - (AnimalRectangle.ZOO_HORIZONTAL), startY)));
		zooRectangle
		.add(new AnimalRectangle(new Point(startX
				, (GameFrame.WINDOW_HEIGHT - (AnimalRectangle.ZOO_VERTICAL + AnimalRectangle.ZOO_LABEL_HEIGHT )) / 2 )));
		zooRectangle
		.add(new AnimalRectangle(new Point(GameFrame.WINDOW_WIDTH - (startX + AnimalRectangle.ZOO_HORIZONTAL)
				, (GameFrame.WINDOW_HEIGHT - (AnimalRectangle.ZOO_VERTICAL + AnimalRectangle.ZOO_LABEL_HEIGHT)) / 2 )));
		zooRectangle
		.add(new AnimalRectangle(new Point(startX
				, GameFrame.WINDOW_HEIGHT - (AnimalRectangle.ZOO_VERTICAL + startY + AnimalRectangle.ZOO_LABEL_HEIGHT))));
		Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
		Iterator<AnimalImage> itrImage = animalSet.iterator();
		AnimalRectangle nextRectangle = null;
		while(itrRectangle.hasNext()) {
			nextRectangle = itrRectangle.next();
			nextRectangle.setAnswer(itrImage.next().getName());
			nextRectangle.getZooLabel().setText(nextRectangle.getAnswer());
			frame.getContentPane().add(nextRectangle.getZooLabel());
//			System.out.println(nextRectangle.getAnswer()); // 확인용
		}
		frame.getContentPane().repaint();
//		System.out.println();
		
		// 3. animalLabels에 x마리만큼 Set 컬렉션에서 무작위로 추출하여 새로 생성
		AnimalImage[] animalImages = animalSet.toArray(new AnimalImage[6]); // animalSet에 저장된 무작위 동물 6마리 배열
//		LinkedList<AnimalImage> animalObjects = new LinkedList<AnimalImage>();
		for (int x = 1; x <= 9; x++) { // for문은 x마리만큼
//			animalObjects.add( (AnimalImage) imageObjects.get((int) (Math.random() * 6)) );
			animalLabels.add(new AnimalLabel( animalImages[(int) (Math.random() * 6)] )); // 동물 0~5번째 중 무작위로 9개 넣음
		}
		// 4. 이터레이터 사용하여 화면에 넣는다.
		Iterator<AnimalLabel> itrLabel = animalLabels.iterator();
		startingRectangle.setAnimals(animalLabels.toArray(new AnimalLabel[9]));

		while (itrLabel.hasNext()) { // 9마리 넣는다. 주의사항은 setBounds(); 처리가 되었는지 잘 볼 것.
			frame.getContentPane().add(itrLabel.next());
		}
		
	}

	public void initializeFields() { // 필드 원래 값으로 초기화
		isDragged = false;
		movingLabel = null;
		movingLabelParent = null;
		movingLabelIndex = -1;
		mouseX = -1;
		mouseY = -1;
	}

	public boolean collocatePoint(AnimalRectangle targetRectangle, Rectangle[] targetSubRectangle, MouseEvent e) { // 마우스 포인터 좌표 잡기 위한 계산식
		for (int x = 0; x < targetSubRectangle.length; x++) {
			if (targetSubRectangle[x].contains(e.getPoint()) && targetRectangle.getAnimal(x) != null) {
				movingLabel = targetRectangle.getAnimal(x);
				movingLabelParent = targetRectangle;
				movingLabelIndex = movingLabelParent.removeAnimal(movingLabel); // 옮기려면 삭제 후 인덱스 저장
				// x번째가 이미 movingLabel의 인덱스인데 굳이 위처럼 추출할 필요가 있었나..(물론 사각형에서 제외시키는 건 맞음)
				// 객체 정보 저장 : 옮기고 있는 것과 그의 부모를 알기 위해
				mouseX = e.getX() - movingLabel.getX();
				mouseY = e.getY() - movingLabel.getY();
				// 눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
				isDragged = true; // 드래그 시작
				frame.getContentPane().repaint();
				movingLabel.repaint();
				return true; // 이미 찾았으므로 빠져나온다.
			}
		}

		return false;
		// 만일 이미지를 만지게끔 해야 한다면
		// 1. 이미지의 범위 안으로 마우스가 들어왔을 때
		// 2. 이전 정보들을 저장하고 잡은 이미지는 이전 사각형으로부터 제외
		// 3. 마우스 좌표 설정은 위와 동일
	}

	public boolean collocateAnimal(AnimalRectangle targetRectangle, Rectangle[] targetSubRectangle, MouseEvent e) { // 동물 레이블 위치 결정
		// 만일 교체에 성공했다면 true, 아니면 false
		for (int x = 0; x < targetSubRectangle.length; x++) {
			if (targetSubRectangle[x].contains(e.getPoint())) { // 작은 사각형 영역에 접근했을 때		
				if (targetRectangle.equals(movingLabelParent)) { // 2. 두 객체 간 접근 안 했는데 원래 부모 안에서 이동하려 했다면
					back(); // 빽시킴
					return true;
				}
				else if (targetRectangle.getAnimal(x) != null) { // 1. 두 객체를 교환해야 할 때
					swap(targetRectangle, targetRectangle.getAnimal(x), x); 
					return true; // 교체가 끝났으므로 리턴
				} 
				else { // 3. 원래 부모와 다르면서 접근 영역이 비어있다면
					// 방법 1 : 비어있는 가장 앞 칸에 넣는다.
//					targetRectangle.addAnimal(movingLabel);
					// 방법 2 : 접근된 칸에 쑤셔 넣는다. 참고로 접근된 작은 사각형에 이미 동물이 있다면 swap()하므로 걱정할 필요 없다.
					targetRectangle.addAnimalAt(movingLabel, x);
					// 좌표 변경은 addAnimal(AnimalLabel)에서 해준다.
					frame.getContentPane().repaint();
					// 다시 그려준다.
					initializeFields(); // 무빙이 끝났으므로 무빙에 쓰인 값들은 초기화해준다.
					return true;
					// 값 초기화하고 찾기 종료
				}
			} else {

			}
		}

		return false; // 다 하고도 못했으면 false
		// 위의 조건을 다르게 바꾸는 방법
		// 1. 큰 사각형 범위 안에 동물 레이블의 좌표가 들어가면
		// 2. 현재 좌표를 동물 레이블의 좌표로 설정(기존 좌표를 변경)
		// 3. 그리고 큰 사각형의 동물 레이블 리스트에 새로 저장(배열 쓸 필요 없어짐)
	}

	public void swap(AnimalRectangle targetAnimalParent, AnimalLabel targetAnimal, int targetIndex) {
		movingLabelParent.removeAnimal(movingLabel);
		targetAnimalParent.removeAnimal(targetAnimal);
		// 둘을 우선 비워준다.
		
		movingLabelParent.addAnimalAt(targetAnimal, movingLabelIndex);
		// 접근당한 동물은 옮기고 있던 동물의 인덱스 번째로 옮기고 있던 동물의 부모 사각창으로
		targetAnimalParent.addAnimalAt(movingLabel, targetIndex);
		// 옮기고 있던 동물은 접근당한 동물의 인덱스 번째로 접근당한 동물의 부모 사각창으로
		frame.getContentPane().repaint();
	}

	public void back() { // 동물 레이블을 원위치로 돌리는 메소드
		movingLabelParent.addAnimalAt(movingLabel, movingLabelIndex);
		frame.getContentPane().repaint();
		// 이동하면 보여줘야 하니
		initializeFields();
		// 원위치로 그리고 초기화
	}
	
	public void buildDialog(ResultDialog dialog) // 기본적인 커스텀 다이얼로그 생성
	{
		JButton choiceButton = new JButton("다른 게임");
		choiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose(); // 다이얼로그 종료
				new GameChoiceFrame();
				frame.dispose();
			}
		});
		dialog.getButtonPanel().add(choiceButton);
		
		JButton mainButton = new JButton("메인 화면");
		mainButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose(); // 다이얼로그 종료
				new MainFrame();
				frame.dispose();
			}
		});
		dialog.getButtonPanel().add(mainButton);
	}
}
