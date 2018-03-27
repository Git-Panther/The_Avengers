package samMain.play.zoo.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import samMain.play.zoo.Adapter.ZooMouseAdapter;
import samMain.play.zoo.button.ZooButton;
import samMain.play.zoo.dialog.ResultDialog;
import samMain.play.zoo.frame.GameFrame;
import samMain.play.zoo.vo.AnimalImage;
import samMain.play.zoo.vo.AnimalLabel;
import samMain.play.zoo.vo.AnimalRectangle;

public class ZooGame extends Game {
	private LinkedList<AnimalRectangle> zooRectangle = new LinkedList<AnimalRectangle>(); // 동물원 우리
	private LinkedList<JLabel> zooNames = new LinkedList<JLabel>(); // 동물 우리 팻말
	
	private LinkedList<Point> startingPoints = new LinkedList<Point>(); // 이제는 우리 밖의 동물들은 이 포인트의 순서에 따라 다를 것이다.
	private LinkedList<AnimalLabel> outerAnimals = new LinkedList<AnimalLabel>(); // 우리 밖의 동물들. 시작할 때 배치되는 동물들도 여기 소속
	
	private AnimalLabel movingAnimal; // 움직이는 레이블(또는 이미지)
	private LinkedList<AnimalLabel> movingAnimalParent; // 움직이는 레이블이 움직이기 전에 있던 큰 사각형의 동물 리스트. 또는 바깥의 리스트.	
	private Point movingAnimalPoint; // 움직이는 동물의 이전 좌표
	
	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표
	
	public static final int AMOUNT_OF_ZOO = 6; // 동물원 우리 개수(동시에 문제에 제시되는 동물 종류 개수)
	public static final int AMOUNT_OF_ANIMAL = 12; // 문제로 제시되는 동물 수
	
	public static final int ZOO_NAME_WIDTH = 126; // 동물원 우리 이름표의 가로 길이
	public static final int ZOO_NAME_HEIGHT = 42; // 동물원 우리 이름표의 세로 길이
	
	public ZooGame() {
		bgLocation = "resource/image/bg/zoo.png";
		quiz = "동물들을 우리로 돌려보내세요";
		quizLabel = new JLabel(quiz);
		quizLabel.setForeground(new Color(47, 79, 79));
		quizLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		quizLabel.setHorizontalAlignment(SwingConstants.CENTER);
		quizLabel.setBounds(0, (GameFrame.WINDOW_HEIGHT / 2) - 442, GameFrame.WINDOW_WIDTH, 100);
		// 위는 매우 기초적인 세팅
		
		// 동물 10종
		imageObjects.add(new AnimalImage("곰", "resource/image/zoo/animal/bear.png"));
		imageObjects.add(new AnimalImage("고양이", "resource/image/zoo/animal/cat.png"));
		imageObjects.add(new AnimalImage("강아지", "resource/image/zoo/animal/dog.png"));
		imageObjects.add(new AnimalImage("여우", "resource/image/zoo/animal/fox.png"));
		imageObjects.add(new AnimalImage("기린", "resource/image/zoo/animal/giraffe.png"));
		imageObjects.add(new AnimalImage("코알라", "resource/image/zoo/animal/koala.png"));
		imageObjects.add(new AnimalImage("부엉이", "resource/image/zoo/animal/owl.png"));
		imageObjects.add(new AnimalImage("양", "resource/image/zoo/animal/sheep.png"));
		imageObjects.add(new AnimalImage("호랑이", "resource/image/zoo/animal/tiger.png"));
		imageObjects.add(new AnimalImage("얼룩말", "resource/image/zoo/animal/Zebra.png"));
		
		// 동물원 우리 팻말 6개
		for(int x = 0; x < AMOUNT_OF_ZOO; x++){
			zooNames.add(new JLabel(""));
			zooNames.getLast().setSize(ZOO_NAME_WIDTH, ZOO_NAME_HEIGHT); // 사이즈만 통일. 좌표는 따로 지정해야 한다.
			zooNames.getLast().setForeground(new Color(30, 66, 11));
			zooNames.getLast().setHorizontalAlignment(SwingConstants.CENTER);
			zooNames.getLast().setFont(new Font("맑은 고딕", Font.BOLD, 18));
		}
	
		// 동물원 우리 팻말 6개 위치 지정.
		// 주의 : 팻말을 게임 프레임에 넣는 타이밍은 start()가 호출되었을 때 해야 널 참조 안 함.
		Iterator<JLabel> itrZooNames = zooNames.iterator();
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 425, (GameFrame.WINDOW_HEIGHT / 2 ) - 325);
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 50, (GameFrame.WINDOW_HEIGHT / 2 ) - 200);
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) + 325, (GameFrame.WINDOW_HEIGHT / 2 ) - 250);
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 525, (GameFrame.WINDOW_HEIGHT / 2 ) - 25);
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) + 325, (GameFrame.WINDOW_HEIGHT / 2 ) + 75);
		itrZooNames.next().setLocation( (GameFrame.WINDOW_WIDTH / 2) - 225, (GameFrame.WINDOW_HEIGHT / 2 ) + 275);
		
		initializeFields(); // 주요 변수 초기화
		
		// 문제의 동물들이 위치하게 될 초기 위치
		int startingWidth = 6;
		int startingHeight = 2;
		int startingX = (GameFrame.WINDOW_WIDTH - (AnimalImage.ANIMAL_WIDTH * startingWidth) ) / 2 - 35; // 가로 x마리
		int startingY = (GameFrame.WINDOW_HEIGHT - (AnimalImage.ANIMAL_HEIGHT * startingHeight) ) / 2 - 20; // 세로 y마리
		for(int x = 0; x < startingHeight; x++) {
			for(int y = 0; y < startingWidth; y++) {
				startingPoints.add(new Point(startingX + (y * AnimalImage.ANIMAL_WIDTH), startingY + (x * AnimalImage.ANIMAL_HEIGHT)));
			}
		}
	}

	class ZooGameAdapter extends MouseAdapter { // frame에 넣을 이벤트
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			if(frame.getCheckButton().contains(new Point(e.getX(), e.getY()))
					|| frame.getChangeButton().contains(new Point(e.getX(), e.getY()))
					|| frame.getMainButton().contains(new Point(e.getX(), e.getY()))
					|| frame.getBgmButton().contains(new Point(e.getX(), e.getY()))
			) {
				moveAnimal(movingAnimalPoint.x, movingAnimalPoint.y); // 위치 롤백
				initializeFields();
				return;
			}
			// 위는 버튼 구역(완료, 선택, 메인, 브금)에 접근했을 때, 아래의 우리에 들어갔을 때와 겹칠 일은 없다.
			// 사실 접근 자체를 못하게 막아야 하는데 그런 매커니즘을 찾지 못해서 접근하고 떼면 옮기기 직전의 위치로 롤백.
			// 이렇게 막는 이유는 버튼 위치에 접근하면 이후부터 마우스 포인터를 갖다 대도 버튼을 먼저 인식하기 때문.
			
			AnimalRectangle targetRectangle = null;
			Iterator<AnimalRectangle> itrRectangle = null;
			// 마우스를 뗐을 때 이미지가 패널에 접근(e)했다면 넣고, 아니면 원위치로. 대신 접근한 곳이 원 부모와 같은 곳이면 원위치로.
			if (movingAnimal != null && movingAnimalParent != null) { // 널 값이 아니어야 진행
				// 우리 영역 n개 중 하나에서 마우스를 뗐는지 검사
				itrRectangle = zooRectangle.iterator();
				while (itrRectangle.hasNext()) {
					targetRectangle = itrRectangle.next();
					if (collocateAnimal(targetRectangle, e)) { // 접근한 우리(0~n-1)에 접근했다는 게 밝혀지면
						initializeFields();
						return;
					}
				}			
				// 위에서 우리에 접근하지 못했다면 당연하게도 우리 밖으로 꺼내야 한다.
				if(!movingAnimalParent.equals(outerAnimals)) { // 옮기고 있는 동물의 부모가 아우터가 아니면 리스트에서 빼줘야 한다.
					movingAnimalParent.remove(movingAnimal);
					outerAnimals.add(movingAnimal); // 밖 소속으로 변경
				}
			}
			initializeFields(); // 변수 초기화는 필수
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			// 마우스를 눌렀을 때의 좌표 저장
			// 우리 밖에서 옮긴다면 그리 한다. 주의할 점은 레이블의 좌표 안에 있냐다.
			if(collocatePoint(outerAnimals, e)) {
				return;
			}
			// 동물원 우리 1~n 중 하나일 때
			Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
			AnimalRectangle targetRectangle = null;
			while (itrRectangle.hasNext()) {
				targetRectangle = itrRectangle.next();
				if (collocatePoint(targetRectangle.getAnimals(), e)) {
					return;
				}		
			}
			// 만일 위에서 false밖에 안 뜨면 옮기는 대상, 옮기는 대상의 부모 정보는 null이 된다.
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			// 마우스를 드래그하고 있을 때 그린다. 단, 윈도우를 벗어날 수는 없다.
			if (isDragged && movingAnimal != null && movingAnimalParent != null) { // 움직이는 중이며 널 값이 아니어야 가능.
				int currentX = e.getX() - mouseX; // 드래그하여 적용할 x 좌표
				int currentY = e.getY() - mouseY; // 드래그하여 적용할 y 좌표
				// 프레임 영역을 벗어날 수 없다.
				if(currentX < 0) {
					currentX = 0;
				} else if(currentX > GameFrame.WINDOW_WIDTH - AnimalImage.ANIMAL_WIDTH - 15) {
					currentX = GameFrame.WINDOW_WIDTH - AnimalImage.ANIMAL_WIDTH - 15;
				}				
				if(currentY < 0) {
					currentY = 0;
				} else if(currentY > GameFrame.WINDOW_HEIGHT - AnimalImage.ANIMAL_HEIGHT - 40) {
					currentY = GameFrame.WINDOW_HEIGHT - AnimalImage.ANIMAL_HEIGHT - 40;
				}
				moveAnimal(currentX, currentY); // 움직인다.	
			}
		}
	}
	
	@Override
	public void start() { // 새로 시작
		// TODO Auto-generated method stub
		// 팻말 이미지
		int signWidth = 125;
		int signHeight = 100;
		ImageIcon sign_left = new ImageIcon(new ImageIcon("resource/image/zoo/object/sign_left.png").getImage().getScaledInstance(signWidth, signHeight, 0));
		ImageIcon sign_right = new ImageIcon(new ImageIcon("resource/image/zoo/object/sign_right.png").getImage().getScaledInstance(signWidth, signHeight, 0));
		LinkedList<JLabel> signs = new LinkedList<JLabel>();			
		Iterator<JLabel> itrZooLabels = zooNames.iterator();
		JLabel targetZooLabel = null; // 좌표 같이 지정하기 위해
		while(itrZooLabels.hasNext()) {
			targetZooLabel = itrZooLabels.next();	
			signs.add(new JLabel(sign_left));
			frame.getContentPane().add(targetZooLabel); // 팻말명 추가
			frame.getContentPane().add(signs.getLast()); // 팻말 추가
		}
		Iterator<JLabel> itrSigns = signs.iterator(); // 팻말을 정해주기 위함.
		itrZooLabels = zooNames.iterator(); // 이터레이터 초기화
		while(itrSigns.hasNext()) { // 팻말 위치를 팻말 텍스트에 맞게 정함
			targetZooLabel = itrZooLabels.next(); 
			itrSigns.next().setBounds(targetZooLabel.getLocation().x, targetZooLabel.getLocation().y - 12, signWidth, signHeight);
		}
		// 우측 모드(팻말 반전)
		signs.get(2).setBounds(zooNames.get(2).getLocation().x + 8, zooNames.get(2).getLocation().y - 12, signWidth, signHeight);
		signs.get(2).setIcon(sign_right);
		signs.get(4).setBounds(zooNames.get(4).getLocation().x + 8, zooNames.get(4).getLocation().y - 12, signWidth, signHeight);
		signs.get(4).setIcon(sign_right);	
		// 문제 가리키는 팻말
		JLabel quizSign = new JLabel(new ImageIcon(new ImageIcon("resource/image/zoo/object/wooden_sign.png").getImage().getScaledInstance(425, 75, 0)));
		quizSign.setBounds((GameFrame.WINDOW_WIDTH / 2) - 215, (GameFrame.WINDOW_HEIGHT / 2) - 420, 425, 75);
		frame.getContentPane().add(quizSign);
		// 프레임에 이벤트 추가
		frame.getContentPane().addMouseMotionListener(new ZooGameAdapter());
		frame.getContentPane().addMouseListener(new ZooGameAdapter());
		putAnimals(); // 공간 n개, 동물 x마리 무작위 선출하여 화면에 배치한다.	
		// 테스트용 : 동물원 범위가 잘 설정되었는지 확인
//		class TestComponent extends JLabel{
//			private static final long serialVersionUID = -2440666689934911210L;
//			Rectangle rect;
//			public TestComponent(Rectangle rect) {
//				this.rect = rect;
//				setOpaque(true);
//				setVisible(true);
//				setBounds(rect);
//			}
//			@Override
//			protected void paintComponent(Graphics g) {
//				// TODO Auto-generated method stub
//				super.paintComponent(g);
//				g.setColor(Color.BLACK);
//				g.drawRect(rect.x, rect.y, rect.width, rect.height);
//				repaint();
//			}		
//		}	
//		Iterator<AnimalRectangle> itrRect = zooRectangle.iterator();
//		while(itrRect.hasNext()) {
//			frame.add(new TestComponent(itrRect.next()));
//		}
	}

	@Override
	public void tryAgain() { // 실패했을 때 재시도
		// TODO Auto-generated method stub
		// 동물원 검사
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 동물원 우리의 이터레이터
		AnimalRectangle targetZoo = null;  // 동물원 우리에서 검사하는 우리
		Iterator<AnimalLabel> itrTargetZooAnimals = null; // 검사하는 우리의 동물 리스트의 이터레이터
		Iterator<Point> itrStartingPoints = startingPoints.iterator(); // 시작 지점들의 이터레이터
		AnimalLabel targetAnimal = null; // 검사하는 우리의 동물 리스트의 이터레이터 중 순서대로 검사
		LinkedList<AnimalLabel> removingAnimals = new LinkedList<AnimalLabel>(); // 중간에 이터레이터가 변하면 안되기에 미리 지울 목록 저장
		Iterator<AnimalLabel> itrRemoving = null; // 지울 목록들이 다 갱신되면 이터레이터 가져옴
		AnimalLabel removingAnimal = null; // 지울 동물	
		while (itrZooRectangle.hasNext()) {
			targetZoo = itrZooRectangle.next();
			if(!targetZoo.getAnimals().isEmpty()) { // 타깃이 된 우리 안에 동물 리스트가 비어있지 않다면
				itrTargetZooAnimals = targetZoo.getAnimals().iterator();
				while(itrTargetZooAnimals.hasNext()) {
					targetAnimal = itrTargetZooAnimals.next();
					if(!targetAnimal.getName().equals(targetZoo.getAnswer())) { // 해당 우리에 들어가면 안 될 동물이 들어갔으면
						removingAnimals.add(targetAnimal); // 우선 지울 목록 추가
					}
				}
				// itrTargetZooAnimals 종료. itrRemoving 시작
				itrRemoving = removingAnimals.iterator();
				while(itrRemoving.hasNext()) {
					removingAnimal = itrRemoving.next();
					targetZoo.getAnimals().remove(removingAnimal);
					outerAnimals.add(removingAnimal); // 오답은 문제 시작 장소로
					outerAnimals.getLast().setLocation(itrStartingPoints.next()); // 오답을 제거할 때마다 이터레이터를 넘기므로 안심하라.
				}
				// itrRemoving 종료. 따라서 이터레이터와 같이 지울 목록도 비워준다.
				itrRemoving = null;
				removingAnimals.clear();
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
				ZooButton acceptButton = new ZooButton("확인");
				dialog.getButtonPanel().add(acceptButton);
				acceptButton.addMouseListener(new ZooMouseAdapter(acceptButton) {
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						acceptButton.setIcon(ZooButton.DEFAULT);
						acceptButton.getParent().repaint();
						if(acceptButton.isEntered() && acceptButton.isPressed()) {
							acceptButton.setEntered(false);
							dialog.dispose();			
						}
						acceptButton.setPressed(false);
					}
				});
				dialog.setVisible(true);
				return false; // 다 안 비었으므로 오답
			}
		}

		// 2. 동물원 검사
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 동물원 우리의 이터레이터
		AnimalRectangle targetZoo = null;  // 동물원 우리에서 검사하는 우리
		Iterator<AnimalLabel> itrTargetZooAnimals = null; // 검사하는 우리의 동물 리스트의 이터레이터
		
		while (itrZooRectangle.hasNext()) {
			targetZoo = itrZooRectangle.next();
			if(!targetZoo.getAnimals().isEmpty()) { // 타깃이 된 우리 안에 동물 리스트가 비어있지 않다면
				itrTargetZooAnimals = targetZoo.getAnimals().iterator();
				while(itrTargetZooAnimals.hasNext()) {
					if(!itrTargetZooAnimals.next().getName().equals(targetZoo.getAnswer())) { // 해당 우리에 들어가면 안 될 동물이 들어갔으면
						dialog.setCorrect(false);
						dialog.setTitle("땡! 틀렸습니다!");
						dialog.getMessageLabel1().setText("저런, 잘못 들어간 동물이 있어요!");	
						dialog.getMessageLabel2().setText("다시 한 번 돌려보낼까요?");
						ZooButton againButton = new ZooButton("재시도");
						dialog.getButtonPanel().add(againButton);
						againButton.addMouseListener(new ZooMouseAdapter(againButton){
							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								againButton.setIcon(ZooButton.DEFAULT);
								againButton.getParent().repaint();
								if(againButton.isEntered() && againButton.isPressed()) {
									againButton.setEntered(false);
									dialog.dispose(); // 다이얼로그 종료
									ZooGame.this.tryAgain();		
								}
								dialog.repaint();
								againButton.setPressed(false);
							}
						});		
						dialog.buildZooDialog();					
						dialog.setVisible(true);
						return false;
					}
				}
			}
		}
		// 정답이라면 위의 오답인지 검사하는 과정에서 빠져나오지 못하고 여기까지 올 것이다.
		dialog.setCorrect(true);
		dialog.setTitle("정답!");	
		dialog.getMessageLabel1().setText("잘했어요! 동물들이 모두 돌아왔어요!");			
		dialog.getMessageLabel2().setText("한 판 더 하시겠어요?");
		ZooButton restartButton = new ZooButton("다시 하기");
		dialog.getButtonPanel().add(restartButton);
		restartButton.addMouseListener(new ZooMouseAdapter(restartButton) {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				restartButton.setIcon(ZooButton.DEFAULT);
				restartButton.getParent().repaint();
				if(restartButton.isEntered() && restartButton.isPressed()) {
					restartButton.setEntered(false);
					dialog.dispose(); // 다이얼로그 종료
					ZooGame.this.restart();	
				}
				dialog.repaint();
				restartButton.setPressed(false);
			}
		});	
		dialog.buildZooDialog();	
		dialog.setVisible(true);
		return true;
	}

	@Override
	public void restart() { // 재시작
		// TODO Auto-generated method stub
		// 배열 같은 거 싹 다 비우고 시작
		Iterator<AnimalRectangle> itrZooRectangle = zooRectangle.iterator(); // 큰 사각형 이터레이터
		AnimalRectangle removingZoo = null; // 제거 대상이 되는 동물원 우리 저장용
		Iterator<JLabel> itrZooLabels = zooNames.iterator(); // 팻말 이터레이터
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
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 - 250, (GameFrame.WINDOW_HEIGHT) / 2 - 350), 450, 200));
		zooRectangle // 3번째 우리
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 + 200, 25), 400, 225));
		zooRectangle // 4번째 우리
		.add(new AnimalRectangle(new Point(0, (GameFrame.WINDOW_HEIGHT) / 2 - 175), 350, 200));
		zooRectangle // 5번째 우리
		.add(new AnimalRectangle(new Point( (GameFrame.WINDOW_WIDTH) / 2 + 250, (GameFrame.WINDOW_HEIGHT) / 2 - 100), 350, 225));
		zooRectangle // 6번째 우리
		.add(new AnimalRectangle(new Point(0,  (GameFrame.WINDOW_HEIGHT) / 2 + 175), 400, 225));
		// 이터레이터를 통해 우리의 이름판을 적용한다.
		Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator(); // 동물원 우리
		Iterator<AnimalImage> itrImage = animalSet.iterator(); // 동물 종류 이미지
		Iterator<JLabel> itrZooNames = zooNames.iterator(); // 동물원 팻말
		AnimalRectangle nextRectangle = null;
		while(itrRectangle.hasNext()) { // 동물원 우리와 동물원 팻말 개수는 같으므로 문제 없음.
			nextRectangle = itrRectangle.next();
			nextRectangle.setAnswer(itrImage.next().getName()); // 우리의 이름을 지어준다.
			itrZooNames.next().setText(nextRectangle.getAnswer()); // 우리의 이름을 우리 레이블(이름판)에 적용한다.
		}	
		// 3. animalLabels에 x마리만큼 Set 컬렉션에서 무작위로 추출하여 새로 생성
		ArrayList<AnimalImage> animalImages = new ArrayList<AnimalImage>(); // animalSet에 저장된 무작위 동물 6마리 배열(인덱스 개념을 쓰기 위함)
		animalImages.addAll(animalSet); // 리스트로 옮겨서 인덱스 쓸 수 있게 한다.		
		Iterator<Point> itrStartingPoints = startingPoints.iterator(); // 시작점들의 이터레이터
		for (int x = 0; x < AMOUNT_OF_ANIMAL; x++) { // for문은 AMOUNT_OF_ANIMAL 마리만큼
			outerAnimals.add(new AnimalLabel( animalImages.get( (int) (Math.random() * AMOUNT_OF_ZOO) ) )); // 동물 0~m-1번째 중 무작위로 n개 넣음
			// 4. 이터레이터 사용하여 화면에 넣는다.
			frame.getContentPane().add(outerAnimals.getLast());
			outerAnimals.getLast().setLocation(itrStartingPoints.next());
			outerAnimals.getLast().repaint();
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
				movingAnimalParent = animalList; // 옮길 동물의 부모의 동물 리스트 = 타깃이 된 동물의 부모의 동물 리스트
				mouseX = e.getX() - movingAnimal.getX();
				mouseY = e.getY() - movingAnimal.getY();
				// 눌렀을 때 상대 좌표를 구한다. 현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
				isDragged = true; // 드래그 시작
				movingAnimalPoint = new Point(movingAnimal.getX(), movingAnimal.getY()); // 이전 좌표 저장
				frame.getContentPane().repaint();
				return true; // 이미 찾았으므로 빠져나온다.
			}
		}
		return false; // 못 찾았으면 false
	}

	public boolean collocateAnimal(AnimalRectangle targetRectangle, MouseEvent e) { // 동물 레이블 위치 결정
		if(targetRectangle.contains(e.getPoint())) {
			// 마우스 포인터를 기준으로 동물원 우리 안에 들어왔을 때. 이게 직관성이 떨어지면 옮기고 있는 동물의 좌상 좌표를 비교할 것.			
			if(!movingAnimalParent.equals(targetRectangle.getAnimals())) { 
				// 들어오기는 했으나 원래 부모(우리)가 아니면 부모(우리)를 바꿔준다. 같으면 드래그에서 작업 끝.
				movingAnimalParent.remove(movingAnimal); // 옮기고 있는 동물을 원래 부모 우리의 동물 리스트에서 제외
				targetRectangle.getAnimals().add(movingAnimal); // 그 다음에 새로 옮긴 동물원 우리의 동물 리스트에 추가(내지는 아우터에 추가)
			}	
			return true;
		} 
		return false; // 해당 우리에 들어오지 않았다면 false
	}
	
	public void moveAnimal(int x, int y) { // 동물을 움직이고 적용한다.
		movingAnimal.setLocation(x, y);
		frame.getContentPane().repaint();
	}
}
