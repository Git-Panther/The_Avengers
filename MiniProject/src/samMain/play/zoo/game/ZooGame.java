package samMain.play.zoo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import samMain.main.SamMain;
import samMain.play.PlayMain;
import samMain.play.zoo.dialog.ResultDialog;
import samMain.play.zoo.frame.GameFrame;
import samMain.play.zoo.vo.AnimalImage;
import samMain.play.zoo.vo.AnimalLabel;
import samMain.play.zoo.vo.AnimalRectangle;

public class ZooGame extends Game {
	private LinkedList<AnimalRectangle> zooRectangle = new LinkedList<AnimalRectangle>(); // 동물원 우리
//	private LinkedList<AnimalLabel> animalLabels = new LinkedList<AnimalLabel>(); // 동물 레이블. 실제로 이걸 옮긴다.
	private LinkedList<JLabel> zooLabels = new LinkedList<JLabel>(); // 동물 우리 팻말
	
	private LinkedList<Point> startingPoints = new LinkedList<Point>(); // 이제는 우리 밖의 동물들은 이 포인트의 순서에 따라 다를 것이다.
	private LinkedList<AnimalLabel> outerAnimals = new LinkedList<AnimalLabel>(); // 우리 밖의 동물들. 시작할 때 배치되는 동물들도 여기 소속
	
	private AnimalLabel movingAnimal; // 움직이는 레이블(또는 이미지)
	private LinkedList<AnimalLabel> movingAnimalParent; // 움직이는 레이블이 움직이기 전에 있던 큰 사각형의 동물 리스트. 또는 바깥의 리스트.	
	
	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표
	
	public static final int AMOUNT_OF_ZOO = 6; // 동물원 우리 개수(동시에 문제에 제시되는 동물 종류 개수)
	public static final int AMOUNT_OF_ANIMAL = 10; // 문제로 제시되는 동물 수
	
	public static final int ZOO_LABEL_WIDTH = 100; // 동물원 우리 이름표의 가로 길이
	public static final int ZOO_LABEL_HEIGHT = 30; // 동물원 우리 이름표의 세로 길이
	
	public ZooGame() {
		bgLocation = "resource/image/bg/zoo.png";
		quiz = "동물들을 우리로 돌려보내세요";
		quizLabel = new JLabel(quiz);
		quizLabel.setForeground(new Color(47, 79, 79));
		quizLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		quizLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quizLabel.setBounds(0, 10, GameFrame.WINDOW_WIDTH, 100);
		// 위는 매우 기초적인 세팅
		
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
		
		// 동물원 우리 팻말 6개
		for(int x = 0; x < AMOUNT_OF_ZOO; x++){
			zooLabels.add(new JLabel(""));
			zooLabels.getLast().setSize(ZOO_LABEL_WIDTH, ZOO_LABEL_HEIGHT); // 사이즈만 통일. 좌표는 따로 지정해야 한다.
			zooLabels.getLast().setForeground(new Color(47, 79, 79));
//			zooLabels.getLast().setHorizontalAlignment(SwingConstants.CENTER);
			zooLabels.getLast().setFont(new Font("맑은 고딕", Font.BOLD, 20));
		}
	
		// 동물원 우리 팻말 6개 위치 지정.
		// 주의 : 팻말을 게임 프레임에 넣는 타이밍은 start()가 호출되었을 때 해야 널 참조 안 함.
		Iterator<JLabel> itrZooLabels = zooLabels.iterator();
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 450, (GameFrame.WINDOW_HEIGHT / 2 ) - 250);
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) + 150, (GameFrame.WINDOW_HEIGHT / 2 ) - 150);
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) + 400, (GameFrame.WINDOW_HEIGHT / 2 ) - 175);
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 500, (GameFrame.WINDOW_HEIGHT / 2 ) + 50);
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) + 475, (GameFrame.WINDOW_HEIGHT / 2 ) + 150);
		itrZooLabels.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 175, (GameFrame.WINDOW_HEIGHT / 2 ) + 350);
		
		initializeFields(); // 주요 변수 초기화
		
		// 문제의 동물들이 위치하게 될 초기 위치
		int startingWidth = 5;
		int startingHeight = 2;
		int startingX = (GameFrame.WINDOW_WIDTH - (AnimalImage.ANIMAL_WIDTH * startingWidth) ) / 2 ; // 가로 5마리
		int startingY = (GameFrame.WINDOW_HEIGHT - (AnimalImage.ANIMAL_HEIGHT * startingHeight) ) / 2; // 세로 2마리
		for(int x = 0; x < startingHeight; x++) {
			for(int y = 0; y < startingWidth; y++) {
				startingPoints.add(new Point(startingX + (y * AnimalImage.ANIMAL_WIDTH), startingY + (x * AnimalImage.ANIMAL_HEIGHT)));
			}
		}
	}

	class ZooGameListener implements MouseListener, MouseMotionListener { // frame에 넣을 이벤트
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			AnimalRectangle targetRectangle = null;
			Iterator<AnimalRectangle> itrRectangle = null;
			// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로. 대신 접근한 곳이 원 부모와 같은 곳이면 원위치로.
			if (movingAnimal != null && movingAnimalParent != null) { 	// 널 값이 아니어야 진행
				// 우리 영역 n개 중 하나에서 마우스를 뗐는지 검사
				itrRectangle = zooRectangle.iterator();
				while (itrRectangle.hasNext()) {
					targetRectangle = itrRectangle.next();
					if (collocateAnimal(targetRectangle.getAnimals(), e)) // 접근했다는 게 밝혀지면 처리하고 끝
					{
						initializeFields();
						return;
					}
				}			
				// 더는 원위치할 필요도, 서로를 바꿀 필요도 없다.
				// 하지만 위에서 우리에 접근하지 못했다면 당연하게도 우리 밖으로 꺼내야 한다.
				if(!movingAnimalParent.equals(outerAnimals)) { // 옮기고 있는 동물의 부모가 아우터가 아니면 리스트에서 빼줘야 한다.
					movingAnimalParent.remove(movingAnimal);
					outerAnimals.add(movingAnimal); // 밖 소속으로 변경
				}
				initializeFields(); // 변수 초기화는 필수
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			// 마우스를 눌렀을 때의 좌표 저장
			// 사실 Panel이나 Label이나 addMouseListener()로 읽을 수 있음 ㅋ
			AnimalRectangle targetRectangle = null;
			// 우리 밖에서 옮긴다면 그리 한다. 주의할 점은 레이블의 좌표 안에 있냐다.
			if(collocatePoint(outerAnimals, e)) {
				return;
			}
			// 동물원 우리 1~n 중 하나일 때
			Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
			while (itrRectangle.hasNext()) {
				targetRectangle = itrRectangle.next();
				if (collocatePoint(targetRectangle.getAnimals(), e)) {
					return;
				}		
			}
			// 만일 위에서 false밖에 안 뜨면 옮기는 대상, 옮기는 대상의 부모 정보는 null이 된다.
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
			if (isDragged && movingAnimal != null && movingAnimalParent != null) { // 움직이는 중이며 널 값이 아니어야 가능.
				movingAnimal.setLocation(e.getX() - mouseX, e.getY() - mouseY);
				frame.getContentPane().repaint();
				movingAnimal.repaint();
			}
		}

	}
	
	@Override
	public void start() { // 새로 시작
		// TODO Auto-generated method stub
		Iterator<JLabel> itrZooLabels = zooLabels.iterator();
		while(itrZooLabels.hasNext()) {
			frame.getContentPane().add(itrZooLabels.next()); // 팻말들을 프레임에 추가	
		}
		// 프레임에 이벤트 추가
		frame.getContentPane().addMouseMotionListener(new ZooGameListener());
		frame.getContentPane().addMouseListener(new ZooGameListener());

		putAnimals(); // 공간 n개, 동물 x마리 무작위 선출하여 화면에 배치한다.			
	}

	@Override
	public void retry() { // 실패했을 때 재시도
		// TODO Auto-generated method stub
		// 동물원 검사
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 우리 이터레이터
		AnimalRectangle targetZoo = null; // 검사할 우리
		LinkedList<AnimalLabel> targetZooAnimals = null; // 검사할 우리의 동물 리스트
		AnimalLabel targetAnimal = null; // 검사할 우리의 동물 리스트의 동물 중 하나
		Iterator<AnimalLabel> itrTargetZooAnimals = null; // 검사할 우리의 동물 리스트의 이터레이터
		Iterator<Point> itrStartingPoints = startingPoints.iterator(); // 시작 지점들의 이터레이터
		while (itrZooRectangle.hasNext()) {
			targetZoo = itrZooRectangle.next(); // 동물원 추출
			targetZooAnimals = targetZoo.getAnimals(); // 링크드리스트 추출
			itrTargetZooAnimals = targetZooAnimals.iterator(); // 동물 이터레이터 추출
			while(itrTargetZooAnimals.hasNext()) {
				targetAnimal = itrTargetZooAnimals.next();
				if(!targetAnimal.getName().equals(targetZoo.getAnswer())) { // 오답만 제거
					targetZooAnimals.remove(targetAnimal);
					outerAnimals.add(targetAnimal); // 오답은 문제 시작 장소로
					outerAnimals.getLast().setLocation(itrStartingPoints.next()); // 오답을 제거할 때마다 이터레이터를 넘기므로 안심하라.
				}
			}
		}
	}

	@Override
	public boolean check() { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면 false다.
		// TODO Auto-generated method stub
		// 여기서 다이얼로그 만들어줘야 자유롭게 조절 가능(...)	
		ResultDialog dialog = new ResultDialog(frame, "미정");		
		// 1. 우리 밖 검사. 여기서 안 비었으면 오답 처리
		Iterator<AnimalLabel> itrOuterAnimals = outerAnimals.iterator();
		while(itrOuterAnimals.hasNext()) {
			if(itrOuterAnimals.next() != null) {
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
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 동물원 우리의 이터레이터
		AnimalRectangle targetZoo = null;  // 동물원 우리에서 검사하는 우리
		LinkedList<AnimalLabel> targetZooAnimals = null;  // 동물원 우리에서 검사하는 우리의 동물 리스트
		Iterator<AnimalLabel> itrTargetZooAnimals = null; // 검사하는 우리의 동물 리스트의 이터레이터
		
		while (itrZooRectangle.hasNext()) {
			targetZoo = itrZooRectangle.next();
			targetZooAnimals = targetZoo.getAnimals();
			itrTargetZooAnimals = targetZooAnimals.iterator();
			while(itrTargetZooAnimals.hasNext()) {
				if(!itrTargetZooAnimals.next().getName().equals(targetZoo.getAnswer())) { // 해당 우리에 들어가면 안 될 동물이 들어갔으면
					dialog.setCorrect(false);
					dialog.setTitle("땡! 틀렸습니다!");
					dialog.getMessageLabel1().setText("저런, 동물들이 잘못 들어갔아요!");	
					dialog.getMessageLabel2().setText("잘못 들어간 동물들을 다시 돌려보내세요!");
					
					JButton retryButton = new JButton("재시도");
					retryButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							dialog.dispose(); // 다이얼로그 종료
							ZooGame.this.retry();
						}
					});
					dialog.getButtonPanel().add(retryButton);
					
					buildDialog(dialog);
					
					dialog.setVisible(true);
					return false;
				}			
			}
		}

		// 정답이라면 위의 오답인지 검사하는 과정에서 빠져나오지 못하고 여기까지 올 것이다.
		dialog.setCorrect(true);
		dialog.setTitle("정답!");	
		dialog.getMessageLabel1().setText("정말 잘했어요! 동물들이 모두 돌아왔어요!");			
		dialog.getMessageLabel2().setText("다시 한 번 도전해볼까요?");
		
		JButton restartButton = new JButton("다시 하기");
		restartButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dialog.dispose(); // 다이얼로그 종료
				ZooGame.this.restart();
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
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 큰 사각형 이터레이터
		AnimalRectangle removingZoo = null; // 제거 대상이 되는 동물원 우리 저장용
		Iterator<JLabel> itrZooLabels = zooLabels.iterator(); // 팻말 이터레이터
		Iterator<AnimalLabel> itrRemovingZooAnimals = null; // 제거 대상이 되는 동물원 우리의 동물 리스트의 이터레이터
		
		while(itrZooRectangle.hasNext()) { // 참고로 팻말 수랑 동물원 수는 같다.
			itrZooLabels.next().setText(""); // 팻말이 사각형에서 ZooGame으로 옮겨짐에 따라 텍스트만 제거
			removingZoo = itrZooRectangle.next();
			itrRemovingZooAnimals = removingZoo.getAnimals().iterator();
			while(itrRemovingZooAnimals.hasNext()) { // 화면상에 동물 우리 안에 있는 동물들을 차례대로 제거하는 과정
				frame.remove(itrRemovingZooAnimals.next()); // 리스트의 동물들은 중간에 null 값 없이 다 땡겨지므로 차례차례 빼버리면 된다.
			}
		}	
		
		initializeFields(); // 최중요 필드값 초기화(레이블 옮기는 데 큰 영향)
		zooRectangle.clear(); // 동물원 우리 다 비워버림(새로 채우기 위해)
		outerAnimals.clear(); // 시작할 때 배치하는 곳에 동물 리스트가 남아있을 때를 대비하여 전부 비움
		frame.getContentPane().repaint(); // 화면 반영
		putAnimals(); // 다시 동물들과 우리를 채워넣는다. 다 비워버렸으므로 처음 시작이나 다름없다.
	}
	
	public void putAnimals() { // 무작위 동물 m마리 넣음
		// TODO Auto-generated method stub
		// 1. 무작위 동물 n종류를 Set 컬렉션에 넣는다.
		LinkedHashSet<AnimalImage> animalSet = new LinkedHashSet<AnimalImage>();
		while(animalSet.size() < AMOUNT_OF_ZOO) { // 중복 저장 안 되니까 6종류 채워질 때까지 무작위로 저장
			animalSet.add( (AnimalImage) imageObjects.get( (int) (Math.random() * imageObjects.size() ) ) );
		}	
		// 2. AnimalRectangle을 n개만큼 생성하고(zooRectangle에 삽입) 이름은 Set 컬렉션에 들어간 동물의 순서대로 넣어준다	
		zooRectangle // 1번째 우리
		.add(new AnimalRectangle( new Point(0, 0), 350, 200));
		zooRectangle // 2번째 우리
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 - 200, 0), 350, 200));
		zooRectangle // 3번째 우리
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 + 200, 25), 400, 200));
		zooRectangle // 4번째 우리
		.add(new AnimalRectangle(new Point(0, (GameFrame.WINDOW_HEIGHT) / 2 - 200), 350, 200));
		zooRectangle // 5번째 우리
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 + 250, (GameFrame.WINDOW_HEIGHT) / 2 + 50), 350, 200));
		zooRectangle // 6번째 우리
		.add(new AnimalRectangle(new Point(0,  (GameFrame.WINDOW_HEIGHT) / 2 + 200), 400, 200));
		// 이터레이터를 통해 우리의 이름판을 적용한다.
		Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator(); // 동물원 우리
		Iterator<AnimalImage> itrImage = animalSet.iterator(); // 동물 종류 이미지
		Iterator<JLabel> itrZooLabels = zooLabels.iterator(); // 동물원 팻말
		AnimalRectangle nextRectangle = null;
		while(itrRectangle.hasNext()) { // 동물원 우리와 동물원 팻말 개수는 같으므로 문제 없음.
			nextRectangle = itrRectangle.next();
			nextRectangle.setAnswer(itrImage.next().getName()); // 우리의 이름을 지어준다.
			itrZooLabels.next().setText(nextRectangle.getAnswer()); // 우리의 이름을 우리 레이블(이름판)에 적용한다.
		}
		frame.getContentPane().repaint();		
		// 3. animalLabels에 x마리만큼 Set 컬렉션에서 무작위로 추출하여 새로 생성
		ArrayList<AnimalImage> animalImages = new ArrayList<AnimalImage>(); // animalSet에 저장된 무작위 동물 6마리 배열(인덱스 개념을 쓰기 위함)
		animalImages.addAll(animalSet); // 리스트로 옮겨서 인덱스 쓸 수 있게 한다.
		
		Iterator<Point> itrStartingPoints = startingPoints.iterator(); // 시작점들의 이터레이터
		for (int x = 0; x < AMOUNT_OF_ANIMAL; x++) { // for문은 AMOUNT_OF_ANIMAL 마리만큼
			outerAnimals.add(new AnimalLabel( animalImages.get( (int) (Math.random() * AMOUNT_OF_ZOO) ) )); // 동물 0~m-1번째 중 무작위로 n개 넣음
			outerAnimals.getLast().setLocation(itrStartingPoints.next());
		}
		// 4. 이터레이터 사용하여 화면에 넣는다.
		Iterator<AnimalLabel> itrOuterAnimals = outerAnimals.iterator(); // 시작점 위치의 동물 레이블 이터레이터
		AnimalLabel targetOuterAnimal = null; // 화면 초기화 겸 안전장치
		while (itrOuterAnimals.hasNext()) { // 시작점 위치의 동물들을 전부 화면에 넣어준다.
			targetOuterAnimal = itrOuterAnimals.next();
			frame.getContentPane().add(targetOuterAnimal);
			targetOuterAnimal.repaint();
		}
		frame.getContentPane().repaint(); // 주입이 끝났으니 화면 다시 그림
	}

	public void initializeFields() { // 필드 원래 값으로 초기화
		isDragged = false;
		movingAnimal = null;
		movingAnimalParent = null;
		mouseX = -1;
		mouseY = -1;
	}

	public boolean collocatePoint(LinkedList<AnimalLabel> animalList, MouseEvent e) { // 마우스 포인터 좌표 잡기 위한 계산식
		Iterator<AnimalLabel> itrAnimalList = animalList.iterator(); // 해당 우리의 동물 리스트의 이터레이터
		AnimalLabel targetAnimal = null; // 해당 우리의 동물 리스트 중 특정 동물(0 ~ 끝)
		while(itrAnimalList.hasNext()) {
			targetAnimal = itrAnimalList.next();
			if(targetAnimal.contains(e.getPoint())) // 마우스 포인터가 동물 안에 들어왔을 때. 참고로 전부 리스트 형식으로 교체했기에 특정 번호가 null일 일이 없다.
			{
				movingAnimal = targetAnimal; // 옮길 동물 = 타깃이 된 동물
				movingAnimalParent = animalList; // 옮길 동물의 부모 = 타깃이 된 동물의 부모
				// 이제 리스트에서 삭제는 우리를 옮겼을 경우에만 한다.
				mouseX = e.getX() - movingAnimal.getX();
				mouseY = e.getY() - movingAnimal.getY();
				// 눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
				isDragged = true; // 드래그 시작
				movingAnimal.repaint();
				frame.getContentPane().repaint();
				return true; // 이미 찾았으므로 빠져나온다.
			} else { // issue : 레이블과 마우스의 위치가 괴리감이 있다. 패널로 감싸야 할 듯.
				System.out.println(e.getPoint());
				System.out.println(targetAnimal.getSize());
				System.out.println(targetAnimal.getLocation());
				System.out.println("여기 아냐!");
			}
		}
		return false; // 못 찾았으면 false
		// 만일 이미지를 기준으로 해야 한다면
		// 1. 이미지의 범위 안으로 마우스가 들어왔을 때
		// 2. 이전 정보들을 저장하고 잡은 이미지는 이전 사각형으로부터 제외
		// 3. 마우스 좌표 설정은 위와 동일
	}

	public boolean collocateAnimal(LinkedList<AnimalLabel> animalList, MouseEvent e) { // 동물 레이블 위치 결정
		// 만일 교체에 성공했다면 true, 아니면 false
		Iterator<AnimalRectangle> itrZoo = zooRectangle.iterator(); // 모든 동물원 우리의 이터레이터	
		AnimalRectangle targetZoo = null; // 모든 동물원 우리 중 순서대로 확인
		while(itrZoo.hasNext()) {
			targetZoo = itrZoo.next();
			if(targetZoo.contains(e.getPoint())) { // 마우스 포인터를 기준으로 동물원 우리 안에 들어왔을 때. 이게 직관성이 떨어지면 옮기고 있는 동물의 좌상 좌표를 비교할 것.			
				if(!targetZoo.equals(movingAnimalParent)) // 부모 안에서 움직이는 거라면 바꿀 필요도 없다. 그렇지 않다면 바꿔줘야 한다.
				{
					movingAnimalParent.remove(movingAnimal); // 옮기고 있는 동물을 원래 부모 우리의 동물 리스트에서 제외
					animalList.add(movingAnimal); // 그 다음에 새로 옮긴 동물원 우리의 동물 리스트에 추가(내지는 아우터에 추가)
				}		
				return true;
			} 
		}

		return false; // 다 하고도 못했으면 false
		// 위의 조건을 다르게 바꾸는 방법
		// 1. 큰 사각형 범위 안에 동물 레이블의 좌상 좌표(.getLocation())가 들어가면
		// 2. 현재 좌표(e.getPoint())를 동물 레이블의 좌표로 설정(기존 좌표를 변경)
		// 3. 그리고 큰 사각형의 동물 레이블 리스트에 새로 저장(배열 쓸 필요 없어짐)
	}
	
	public void buildDialog(ResultDialog dialog) // 기본적인 커스텀 다이얼로그(다 비웠는데 오답, 정답일 때) 생성
	{
		JButton choiceButton = new JButton("다른 게임");
		choiceButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new PlayMain().setVisible(true);
				dialog.dispose(); // 다이얼로그 종료
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
				new SamMain();
				frame.dispose();
			}
		});
		dialog.getButtonPanel().add(mainButton);
	}
}
