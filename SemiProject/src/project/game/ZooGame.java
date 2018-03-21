package project.game;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.frame.GameFrame;
import project.vo.AnimalImage;
import project.vo.AnimalLabel;
import project.vo.AnimalRectangle;

public class ZooGame extends Game {

	private LinkedList<AnimalRectangle> zooRectangle = new LinkedList<AnimalRectangle>(); // 동물원
																							// 우리
	private LinkedList<AnimalLabel> animalLabels = new LinkedList<AnimalLabel>(); // 동물 레이블. 실제로 이걸 옮긴다.
	private AnimalRectangle startingRectangle; // 랜덤으로 할당된 동물의 레이블이 들어갈 공간

	private AnimalLabel movingLabel; // 움직이는 레이블(또는 이미지)
	private AnimalRectangle movingLabelParent; // 움직이는 레이블이 움직이기 전에 있던 사각형 범위.
												// 9개 합친 큰 덩어리. 빽할 때 필요.
	private int movingLabelIndex; // 뺐을 때 해당 레이블이 있었던 인덱스. 원위치 시킬 때 필요.

	private boolean isDragged; // 마우스 눌림 여부
	private int mouseX, mouseY; // 마우스의 x, y 좌표

	public ZooGame() {
		bgLocation = "resource/image/bg/zoo.jpg";
		quiz = "동물들을 제 우리에 맞게 돌려보내세요.";
		// 문제의 동물들 종류
		imageObjects.add(new AnimalImage("Dog", "resource/image/character/dog.png"));
		imageObjects.add(new AnimalImage("Giraffe", "resource/image/character/giraffe.png"));
		imageObjects.add(new AnimalImage("Hare", "resource/image/character/hare.png"));
		imageObjects.add(new AnimalImage("Lion", "resource/image/character/lion.png"));
		imageObjects.add(new AnimalImage("Monkey", "resource/image/character/monkey.png"));
		imageObjects.add(new AnimalImage("Owl", "resource/image/character/owl.png"));

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
		putAnimals(); // 공간 n개, 동물 x마리 무작위 선출하여 화면에 쑤신다.
		Iterator<AnimalLabel> itrLabel = animalLabels.iterator();
		startingRectangle.setAnimals(animalLabels.toArray(new AnimalLabel[9]));

		while (itrLabel.hasNext()) { // 9마리 넣는다. 주의사항은 setBounds(); 처리가 되었는지 잘 볼 것.
			frame.getContentPane().add(itrLabel.next());
		}

		frame.getContentPane().repaint();
		// 동물들을 문제 창에 모아놓는다.
		
		class ZooListener implements MouseListener, MouseMotionListener {
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
					if (collocateAnimal(frame, targetRectangle, targetSubRectangle, e)) 
						return;

					// 우리 영역 n개 중 하나이면
					Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
					while (itrRectangle.hasNext()) {
						targetRectangle = itrRectangle.next();
						targetSubRectangle = targetRectangle.getSubRectangle();
						if (collocateAnimal(frame, targetRectangle, targetSubRectangle, e)) 
							return;
					}
					// 사각형들이 아닌 공간에서 뗀 것이라면 이하의 빽이 발생할 것이다.
					back(frame);
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
				AnimalRectangle targetRectangle = null;
				Rectangle[] targetSubRectangle = null;

				// 문제 영역에서 눌렀을 때
				targetRectangle = startingRectangle;
				targetSubRectangle = targetRectangle.getSubRectangle();
				if (collocatePoint(frame, targetRectangle, targetSubRectangle, e)) 
					return;

				// 동물원 우리 1~n 중 하나일 때
				Iterator<AnimalRectangle> itrRectangle = zooRectangle.iterator();
				while (itrRectangle.hasNext()) {
					targetRectangle = itrRectangle.next();
					targetSubRectangle = targetRectangle.getSubRectangle();
					if (collocatePoint(frame, targetRectangle, targetSubRectangle, e)) 
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

		frame.getContentPane().addMouseMotionListener(new ZooListener());
		frame.getContentPane().addMouseListener(new ZooListener());
	}

	@Override
	public void retry(GameFrame frame) { // 실패했을 때 재시도
		// TODO Auto-generated method stub

		// 동물원 검사
		Iterator<AnimalRectangle> zooItr = zooRectangle.iterator();
		AnimalRectangle zoo = null;
		AnimalLabel[] zooLabels = null;
		while (zooItr.hasNext()) {
			zoo = zooItr.next();
			zooLabels = zoo.getAnimals();
			for (int x = 0; x < zooLabels.length; x++) {
				if (zooLabels[x] == null) // 다 왔으면 다음 타자로
					continue; // 위치 선정이 자유로워진만큼 모든 배열의 인덱스를 검사해야 한다.

				if (!zooLabels[x].getName().equals(zoo.getAnswer())) { // 오답인 것들은 문제창으로
					zoo.removeAnimal(zooLabels[x]); // 해당 우리에서 제거
					startingRectangle.addAnimal(zooLabels[x]);	// 시작 창으로 다시 삽입
				}

			}
		}

	}

	@Override
	public boolean check(GameFrame frame) { // 동물원 게임은 이름이 일치하지 않는 것이 발견되면
											// false다.
		// TODO Auto-generated method stub

		// 여기서 다이얼로그 만들어줘야 자유롭게 조절 가능(...)

		class ResultDialog extends JDialog{
			/**
			 * 
			 */
			private static final long serialVersionUID = 7488391241677890730L;
			
			private JPanel dialogPanel; // 다이얼로그 전체 패널
//			private JLabel messageLabel; // 메시지 레이블
			private JPanel buttonPanel; // 버튼들 패널
//			private JButton okBtn;
//			private JButton cancelBtn;
			
			public ResultDialog(GameFrame frame, String title) 
			{
				super(frame, title, true); // 모달(자기 끝낼 때까지 딴거 못함)
				setBounds(960 - 150, 540 - 112, 300, 225);
				setResizable(false);
				
				dialogPanel = new JPanel();
		        dialogPanel.setLayout(new BorderLayout());
		        add(dialogPanel);

//		        messageLabel = new JLabel("DEFAULT", JLabel.CENTER);
//		        dialogPanel.add(messageLabel, BorderLayout.CENTER);
		       
		        buttonPanel = new JPanel();
		        dialogPanel.add(buttonPanel, BorderLayout.SOUTH);
		        
		        addWindowListener(new WindowAdapter() { // 창 닫힐 때 닫아야 함.
		            @Override
		            public void windowClosing(WindowEvent e) {
		                super.windowClosing(e);
		                dispose(); // 다이얼로그 제거
		            }
		        });
			}

			public JPanel getDialogPanel() {
				return dialogPanel;
			}

			public JPanel getButtonPanel() {
				return buttonPanel;
			}
		}
		
		ResultDialog dialog = new ResultDialog(frame, "미정");
		
		// 1. 문제 창 검사. 여기서 안 비었으면 오답 처리
		AnimalLabel[] checkingLabel = startingRectangle.getAnimals(); // 비었는지검사하기 위한 용도
		for (AnimalLabel label : checkingLabel) {
			if (label != null) {
				dialog.setTitle("저런! 다 안 옮겼네요?");
				JLabel wrongMessage1 = new JLabel("아직 동물들이 밖에 남아있어요!", JLabel.CENTER);
//				JLabel wrongMessage2 = new JLabel("모든 동물들을 돌려보내세요!", JLabel.CENTER);
				// 이번엔 다이얼로그 조절. 두 줄 이상 출력할 수 있어야 한다.
				dialog.getDialogPanel().add(wrongMessage1);
//				dialog.getDialogPanel().add(wrongMessage2);
				
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
					dialog.setTitle("땡! 틀렸습니다!");
					
					
					dialog.setVisible(true);
					return false;
				}
			}
		}

		dialog.setTitle("정답!");
		
		
		
		dialog.setVisible(true);
		return true;
	}

	public void putAnimals() { // 무작위 동물 9마리 쑤심
		// TODO Auto-generated method stub
		// 1. 무작위 동물 n종류를 Set 컬렉션에 넣는다.
		// Set 생성 이후 넣어준다.
		// 2. AnimalRectangle을 n개만큼 생성하고(zooRectangle에 삽입) 이름은 Set 컬렉션에 들어간 동물의 순서대로 넣어준다.
		zooRectangle.add(new AnimalRectangle("Dog", new Point(50, 0)));
		zooRectangle.add(new AnimalRectangle("Giraffe", new Point(450, 0)));
		zooRectangle.add(new AnimalRectangle("Hare", new Point(850, 0)));
		zooRectangle.add(new AnimalRectangle("Lion", new Point(50, 300)));
		zooRectangle.add(new AnimalRectangle("Monkey", new Point(900, 450)));
		zooRectangle.add(new AnimalRectangle("Owl", new Point(50, 600)));
		// 3. animalLabels에 x마리만큼 Set 컬렉션에서 무작위로 추출하여 새로 생성
		LinkedList<AnimalImage> animalObjects = new LinkedList<AnimalImage>();
		for (int x = 1; x <= 9; x++) { // for문은 x마리만큼
			animalObjects.add( (AnimalImage) imageObjects.get((int) (Math.random() * 6)) );
			animalLabels.add(new AnimalLabel(animalObjects.getLast()));
		}
		// 4. 이터레이터 사용하여 화면에 넣는다. 이 경우는 프레임 패러미터 보내기 싫어서 밖으로 ㄱㄱ
	}

	public void initializeFields() { // 필드 원래 값으로 초기화
		isDragged = false;
		movingLabel = null;
		movingLabelParent = null;
		movingLabelIndex = -1;
		mouseX = -1;
		mouseY = -1;
	}

	public boolean collocatePoint(GameFrame frame, AnimalRectangle targetRectangle, Rectangle[] targetSubRectangle,
			MouseEvent e) { // 마우스 포인터 좌표 잡기 위한 계산식
		for (int x = 0; x < targetSubRectangle.length; x++) {
			if (targetSubRectangle[x].contains(e.getPoint()) && targetRectangle.getAnimal(x) != null) {
				movingLabel = targetRectangle.getAnimal(x);
				movingLabelParent = targetRectangle;
				movingLabelIndex = movingLabelParent.removeAnimal(movingLabel); // 옮기려면 삭제 후 인덱스 저장
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
	}

	public boolean collocateAnimal(GameFrame frame, AnimalRectangle targetRectangle, Rectangle[] targetSubRectangle,
			MouseEvent e) { // 동물 레이블 위치 결정
		// 만일 교체에 성공했다면 true, 아니면 false
		for (int x = 0; x < targetSubRectangle.length; x++) {
			if (targetSubRectangle[x].contains(e.getPoint())) {
				// 작은 사각형 영역에 접근했을 때
				if (targetRectangle.equals(movingLabelParent)) { // 놓았다 뗀 영역이 원래 부모와 같으면
					back(frame); // 빽시킴
					return true;
				} else if (targetRectangle.getAnimal(x) != null) { // 교체 매커니즘
					swap(frame, targetRectangle.getAnimal(x), x, targetRectangle); 
					return true; // 교체가 끝났으므로 리턴
				} else { // 원래 부모와 다르면서 접근 영역이 비어있다면
					targetRectangle.addAnimal(movingLabel); 
					// 좌표 변경은 addAnimal(AnimalLabel)에서 해준다.
					frame.getContentPane().repaint();
					// 다시 그려준다.
					initializeFields();
					return true;
					// 값 초기화하고 찾기 종료
				}
			} else {

			}
		}

		return false; // 다 하고도 못했으면 false
	}

	public void swap(GameFrame frame, AnimalLabel targetAnimal, int targetIndex, AnimalRectangle targetRectangle) {
		movingLabelParent.addAnimalAt(targetAnimal, movingLabelIndex);
		// 접근당한 동물은 옮기고 있던 동물의 인덱스 번째로 옮기고 있던 동물의 부모 사각창으로
		targetRectangle.addAnimalAt(movingLabel, targetIndex);
		// 옮기고 있던 동물은 접근당한 동물의 인덱스 번째로 접근당한 동물의 부모 사각창으로
		frame.repaint();
	}

	public void back(GameFrame frame) { // 동물 레이블을 원위치로 돌리는 메소드
		movingLabelParent.addAnimalAt(movingLabel, movingLabelIndex);
		frame.getContentPane().repaint();
		// 이동하면 보여줘야 하니
		initializeFields();
		// 원위치로 그리고 초기화
	}
}
