package samMain.play;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import samMain.main.SamMain;


//과일가게 게임 구현 클래스
public class FruitShop { 

	JFrame frame;
	JLabel[] item = new JLabel[11];
	JLabel[] word = new JLabel[5];
	JLabel background, basket;
	JButton backToMain;
	Clip clip;

	public FruitShop() {
		// 메인 프레임 창
		frame = new JFrame();
		frame.setBounds(350, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		frame.setResizable(false);
		frame.setTitle("황가네 과일가게");

		// 마우스 커서 모양 변경
		changeMousePointer();

		// 배경 이미지
		background = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/과일가게.png").getImage().getScaledInstance(1200, 900, 0)));
		background.setBounds(0, 0, 100, 100);
		frame.add(background);

		// 아이템 이미지 (딸기)
		item[0] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/1딸기.png").getImage().getScaledInstance(100, 100, 0)));
		item[0].setBounds(290, 570, 100, 100);
		frame.add(item[0]);
		item[0].setVisible(false);

		// 아이템 이미지 (포도)
		item[1] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/2포도.png").getImage().getScaledInstance(100, 100, 0)));
		item[1].setBounds(430, 575, 100, 100);
		item[1].setName("포도");
		frame.add(item[1]);
		item[1].setVisible(false);

		// 아이템 이미지 (토마토)
		item[2] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/3토마토.png").getImage().getScaledInstance(100, 100, 0)));
		item[2].setBounds(545, 570, 100, 100);
		frame.add(item[2]);
		item[2].setVisible(false);

		// 아이템 이미지 (키위)
		item[3] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/4키위.png").getImage().getScaledInstance(100, 100, 0)));
		item[3].setBounds(410, 510, 100, 100);
		frame.add(item[3]);
		item[3].setVisible(false);

		// 아이템 이미지 (바나나)
		item[4] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/5바나나.png").getImage().getScaledInstance(100, 100, 0)));
		item[4].setBounds(210, 531, 100, 100);
		item[4].setName("바나나");
		frame.add(item[4]);
		item[4].setVisible(false);

		// 아이템 이미지 (사과)
		item[5] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/6사과.png").getImage().getScaledInstance(100, 100, 0)));
		item[5].setBounds(170, 600, 100, 100);
		item[5].setName("사과");
		frame.add(item[5]);
		item[5].setVisible(false);

		// 아이템 이미지 (수박)
		item[6] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/7수박.png").getImage().getScaledInstance(130, 130, 0)));
		item[6].setBounds(290, 515, 130, 130);
		item[6].setName("수박");
		frame.add(item[6]);
		item[6].setVisible(false);

		// 아이템 이미지 (호박)
		item[7] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/8호박.png").getImage().getScaledInstance(100, 100, 0)));
		item[7].setBounds(420, 607, 100, 100);
		frame.add(item[7]);
		item[7].setVisible(false);

		// 아이템 이미지 (파인애플)
		item[8] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/9파인애플.png").getImage().getScaledInstance(100, 150, 0)));
		item[8].setBounds(529, 470, 100, 150);
		frame.add(item[8]);
		item[8].setVisible(false);

		// 아이템 이미지 (페이크 코끼리)
		item[9] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/10페이크.png").getImage().getScaledInstance(100, 150, 0)));
		item[9].setBounds(550, 570, 100, 150);
		frame.add(item[9]);
		item[9].setVisible(false);

		// 아이템 이미지 (오렌지)
		item[10] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/11오렌지.png").getImage().getScaledInstance(100, 100, 0)));
		item[10].setBounds(293, 598, 100, 100);
		item[10].setName("오렌지");
		frame.add(item[10]);
		item[10].setVisible(false);

		// 문제 이미지 (노란색 바나나)
		word[0] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/문제노란색바나나.png").getImage().getScaledInstance(300, 250, 0)));
		word[0].setBounds(855, 140, 300, 250);
		word[0].setName("바나나");
		frame.add(word[0]);

		// 문제 이미지 (보라색 포도)
		word[1] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/문제보라색포도.png").getImage().getScaledInstance(300, 250, 0)));
		word[1].setBounds(835, 140, 300, 250);
		word[1].setName("null");
		word[1].setVisible(false);
		frame.add(word[1]);

		// 문제 이미지 (빨간색 사과)
		word[2] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/문제빨간색사과.png").getImage().getScaledInstance(300, 250, 0)));
		word[2].setBounds(825, 140, 300, 250);
		word[2].setName("null");
		word[2].setVisible(false);
		frame.add(word[2]);

		// 문제 이미지 (주황색 오렌지)
		word[3] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/문제주황색오렌지.png").getImage().getScaledInstance(300, 250, 0)));
		word[3].setBounds(845, 140, 300, 250);
		word[3].setName("null");
		word[3].setVisible(false);
		frame.add(word[3]);

		// 문제 이미지 (초록색 수박)
		word[4] = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/문제초록색수박.png").getImage().getScaledInstance(300, 250, 0)));
		word[4].setBounds(825, 140, 300, 250);
		word[4].setName("null");
		word[4].setVisible(false);
		frame.add(word[4]);

		// 정답 체크용 이미지(과일 바구니)
		basket = new JLabel(
				new ImageIcon(new ImageIcon("fruitShopImage/바구니.png").getImage().getScaledInstance(381, 414, 0)));
		basket.setBounds(800, 440, 381, 414);
		frame.add(basket);

		// JButton으로 만든 뒤로가기 버튼
		ImageIcon backToMainNormal = new ImageIcon(new ImageIcon("fruitShopImage/뒤로가기.png").getImage().getScaledInstance(100, 40, 0));
		ImageIcon backToMainFocus = new ImageIcon(new ImageIcon("fruitShopImage/뒤로가기.gif").getImage().getScaledInstance(100, 40, 0));
		backToMain = new JButton(backToMainNormal);
		// ImageIcon backMouseOver = new ImageIcon("fruitShopImage/뒤로가기1.png");
		// backToMain.setRolloverIcon(backMouseOver); //버튼위에 마우스가 있을경우 마우스 오버
		backToMain.setBounds(30, 30, 90, 60);
		backToMain.setBorderPainted(false);
		backToMain.setContentAreaFilled(false);
		backToMain.setFocusPainted(false);
		
		// 이전화면버튼클릭시 이벤트 (게임선택 화면으로 이동)
		ActionListener backButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clip.stop(); // 배경음악 제거
//				게임선택 화면으로 이동하기
				PlayMain playMain = new PlayMain();
				playMain.setVisible(true);
				frame.dispose(); // 진행중인창 닫기
			}
		};
		// 뒤로가기 이벤트 버튼에 추가
		backToMain.addActionListener(backButtonListener);

		backToMain.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				backToMain.setIcon(backToMainFocus);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				backToMain.setIcon(backToMainNormal);
			}
	      });
		
		// 프레임에 버튼 추가
		frame.add(backToMain);

		// 마우스 모션 리스너(마우스 드레깅을위한 객체, 이미지 아이콘 드레깅에 필요)
		frame.addMouseMotionListener(new ItemMouseListener());

		// 마우스 리스너 (마우스 버튼 클릭시 발행하는 액션을 위한 객체, 일정 영역 클릭시 이미지가 보이도록 하는데 필요)
		frame.addMouseListener(new ItemMouseListener());

		// 이미지의 Z축 상하위 순서 설정
		frame.setComponentZOrder(item[4], 1);
		frame.setComponentZOrder(item[1], 2);
		frame.setComponentZOrder(item[5], 3);
		frame.setComponentZOrder(item[10], 4);
		frame.setComponentZOrder(item[6], 5);
		frame.setComponentZOrder(item[2], 6);
		frame.setComponentZOrder(item[3], 7);
		frame.setComponentZOrder(item[7], 8);
		frame.setComponentZOrder(item[8], 9);
		frame.setComponentZOrder(item[9], 10);
		frame.setComponentZOrder(item[0], 11);

		frame.setComponentZOrder(word[0], 12);
		frame.setComponentZOrder(word[1], 13);
		frame.setComponentZOrder(word[2], 14);
		frame.setComponentZOrder(word[3], 15);
		frame.setComponentZOrder(word[4], 16);
		frame.setComponentZOrder(backToMain, 17); 
		frame.setComponentZOrder(basket, 18);
		frame.setComponentZOrder(background, 19);

		// 게임 시작시 등장하는 다이어로그 (게임 방법 설명)
		JOptionPane.showMessageDialog(null, "엄마를 도와 심부름을 할거에요!\n메모에 적힌 물건을 바구니에 담아보세요!", "게임 설명",
				JOptionPane.INFORMATION_MESSAGE);

		// 화면 보여지도록 설정
		frame.setVisible(true);

		// 배경음악이 실행되도록 설정
		backgroundMusic();

	}

	// 마우스 액션에 대한 클래스 (드레깅, 클릭)
	class ItemMouseListener extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			// 일정 영역에 마우스를 누를때 이벤트 발생
			// 0. 딸기
			if ((e.getX() > 289 && e.getX() < 402) && (e.getY() > 603 && e.getY() < 634)) {
				item[0].setVisible(true);
			}
			// 1. 포도
			if (e.getX() > 407 && e.getX() < 517 && e.getY() > 606 && e.getY() < 629 && item[1].getName().equals("포도")) {
				item[1].setVisible(true);
			}
			// 2. 토마토
			if (e.getX() > 533 && e.getX() < 630 && e.getY() > 605 && e.getY() < 629) {
				item[2].setVisible(true);
			}
			// 3. 키위
			if (e.getX() > 414 && e.getX() < 509 && e.getY() > 557 && e.getY() < 589) {
				item[3].setVisible(true);
			}
			// 4. 바나나
			if (e.getX() > 188 && e.getX() < 286 && e.getY() > 555 && e.getY() < 598 && item[4].getName().equals("바나나")) {
				item[4].setVisible(true);
			}
			// 5. 사과
			if (e.getX() > 174 && e.getX() < 270 && e.getY() > 644 && e.getY() < 672 && item[5].getName().equals("사과")) {
				item[5].setVisible(true);
			}
			// 6. 수박
			if ((e.getX() > 301 && e.getX() < 393 && e.getY() > 545 && e.getY() < 592 && item[6].getName().equals("수박"))) {
				item[6].setVisible(true);
			}
			// 7. 호박
			if (e.getX() > 414 && e.getX() < 521 && e.getY() > 646 && e.getY() < 669) {
				item[7].setVisible(true);
			}
			// 8. 파인애플
			if (e.getX() > 528 && e.getX() < 629 && e.getY() > 547 && e.getY() < 589) {
				item[8].setVisible(true);
			}
			// 9. 코끼리
			if (e.getX() > 540 && e.getX() < 648 && e.getY() > 645 && e.getY() < 671) {
				item[9].setVisible(true);
			}
			// 10. 오렌지
			if (e.getX() > 287 && e.getX() < 396 && e.getY() > 639 && e.getY() < 673 && item[10].getName().equals("오렌지")) {
				item[10].setVisible(true);
			}

		}

		// 마우스가 드래깅 상태일 경우
		public void mouseDragged(MouseEvent e) {
			// 마우스 포인터의 위치가 이미지영역 내에 있을때 해당 이미지 드레깅 허용

			// "0 딸기" 초기좌표 290, 570
			if (item[0].getMousePosition() != null) {
				// 이미지가 드래깅 상태일때 Z축 순위 0순위 설정
				frame.setComponentZOrder(item[0], 0);
				// 마우스의 frame내 x 좌표값
				int x = e.getX();
				// 마우스의 frame내 y 좌표값
				int y = e.getY();
				// 드래깅 중일 경우 마우스가 이미지 내로  x : 50, y : 50 픽셀 이동
				item[0].setLocation(x - 50, y - 50);
				// 이미지가 바구니 영역에 진입시
				if ((860 < item[0].getX() && item[0].getX() < 1180) && (685 < item[0].getY() && item[0].getY() < 825)) {
					// 오답 사운드이펙트
					wrongSoundEffect();
					// 바구니 영역에 진입시 아이템을 화면에서 표시하지 않음
					item[0].setVisible(false);
					// 초기 좌표로 이동
					item[0].setLocation(290, 570);
				}
			}

			// "1 포도" 초기좌표 423, 578
			else if (item[1].getMousePosition() != null) {
				frame.setComponentZOrder(item[1], 0);
				int x = e.getX();
				int y = e.getY();
				item[1].setLocation(x - 50, y - 50);
				if ((860 < item[1].getX() && item[1].getX() < 1180) && (685 < item[1].getY() && item[1].getY() < 825)
						&& word[1].getName().equals("포도")) {
					// 정답 사운드 이펙트
					soundEffect();
					item[1].setVisible(false);
					item[1].setName("null");
					word[1].setVisible(false);
					word[2].setVisible(true);
					word[2].setName("사과");
				} else if ((860 < item[1].getX() && item[1].getX() < 1180)
						&& (685 < item[1].getY() && item[1].getY() < 825) && word[1].getName().equals("null")) {
					wrongSoundEffect();
					item[1].setLocation(423, 578);
					item[1].setVisible(false);
				}
			}
			// "2 토마토" 초기좌표 545, 570
			else if (item[2].getMousePosition() != null) {
				frame.setComponentZOrder(item[2], 0);
				int x = e.getX();
				int y = e.getY();
				item[2].setLocation(x - 50, y - 50);
				if ((860 < item[2].getX() && item[2].getX() < 1180) && (685 < item[2].getY() && item[2].getY() < 825)) {
					wrongSoundEffect();
					item[2].setLocation(545, 570);
				}
			}
			// "3 키위" 초기좌표 410, 510
			else if (item[3].getMousePosition() != null) {
				frame.setComponentZOrder(item[3], 0);
				int x = e.getX();
				int y = e.getY();
				item[3].setLocation(x - 50, y - 50);
				if ((860 < item[3].getX() && item[3].getX() < 1180) && (685 < item[3].getY() && item[3].getY() < 825)) {
					wrongSoundEffect();
					item[3].setLocation(410, 510);
				}
			}
			// "4 바나나" 초기좌표 (210, 531)
			else if (item[4].getMousePosition() != null) {
				frame.setComponentZOrder(item[4], 0);
				int x = e.getX();
				int y = e.getY();
				item[4].setLocation(x - 50, y - 50);
				if ((860 < item[4].getX() && item[4].getX() < 1180) && (685 < item[4].getY() && item[4].getY() < 825)
						&& word[0].getName().equals("바나나")) {
					soundEffect();// 정답일경우 딩동댕 소리 나도록
					item[4].setVisible(false);
					item[4].setName("null");
					word[0].setVisible(false);
					word[1].setVisible(true);
					word[1].setName("포도");
				} else if ((860 < item[4].getX() && item[4].getX() < 1180)
						&& (685 < item[4].getY() && item[4].getY() < 825) && word[0].getName().equals("null")) {
					item[4].setLocation(210, 531);
				}
			}
			// "5 사과" 초기좌표 (170,600)
			else if (item[5].getMousePosition() != null) {
				frame.setComponentZOrder(item[5], 0);
				int x = e.getX();
				int y = e.getY();
				item[5].setLocation(x - 50, y - 50);
				if ((860 < item[5].getX() && item[5].getX() < 1180) && (685 < item[5].getY() && item[5].getY() < 825)
						&& word[2].getName().equals("사과")) {
					soundEffect(); // 정답일경우 딩동댕 소리 나도록
					item[5].setVisible(false);
					item[5].setName("null");
					word[2].setVisible(false);
					word[3].setVisible(true);
					word[3].setName("오렌지");
				} else if ((860 < item[5].getX() && item[5].getX() < 1180)
						&& (685 < item[5].getY() && item[5].getY() < 825) && word[2].getName().equals("null")) {
					wrongSoundEffect();
					item[5].setLocation(170, 600);
				}
			}
			// "6 수박 " 초기좌표 290, 515
			else if (item[6].getMousePosition() != null) {
				frame.setComponentZOrder(item[6], 0);
				int x = e.getX();
				int y = e.getY();
				item[6].setLocation(x - 50, y - 50);
				if ((860 < item[6].getX() && item[6].getX() < 1180) && (685 < item[6].getY() && item[6].getY() < 825)
						&& word[4].getName().equals("수박")) {
					soundEffect(); // 정답일경우 딩동댕 소리 나도록
					item[6].setVisible(false);
					item[6].setName("null");
					word[4].setVisible(false);
					clip.stop(); // 배경음악 제거
					JOptionPane.showMessageDialog(null, " 심부름을 완료했어요!\n 엄마가 기뻐하실꺼에요!", "심부름 성공!!",
							JOptionPane.INFORMATION_MESSAGE);
					JOptionPane.showMessageDialog(null, "확인을 누르면 메인화면으로 이동합니다.", "메인 화면으로 이동!",
							JOptionPane.INFORMATION_MESSAGE); 
					// 메인화면으로 이동하기!
					new SamMain();
					frame.dispose(); 
				}

				else if ((860 < item[6].getX() && item[6].getX() < 1180)
						&& (685 < item[6].getY() && item[6].getY() < 825) && word[4].getName().equals("null")) {
					wrongSoundEffect();
					item[6].setLocation(290, 515);
				}
			}
			// 7. "호박" 초기좌표 : 420, 607
			else if (item[7].getMousePosition() != null) { // 호박 1030. 510
				frame.setComponentZOrder(item[7], 0);
				int x = e.getX();
				int y = e.getY();
				item[7].setLocation(x - 50, y - 50);
				if ((860 < item[7].getX() && item[7].getX() < 1180) && (685 < item[7].getY() && item[7].getY() < 825)) {
					wrongSoundEffect();
					item[7].setLocation(420, 607);
				}
			// 8. "파인애플" 초기좌표 : 529, 470	
			} else if (item[8].getMousePosition() != null) {// 파인애플 70. 710
				frame.setComponentZOrder(item[8], 0);
				int x = e.getX();
				int y = e.getY();
				item[8].setLocation(x - 50, y - 50);
				if ((860 < item[8].getX() && item[8].getX() < 1180) && (685 < item[8].getY() && item[8].getY() < 825)) {
					wrongSoundEffect();
					item[8].setLocation(529, 470);
				}
			// 9. "코끼리" 초기좌표 : 550, 570
			} else if (item[9].getMousePosition() != null) { // 페이크 보너스 790. 710
				frame.setComponentZOrder(item[9], 0);
				int x = e.getX();
				int y = e.getY();
				item[9].setLocation(x - 50, y - 50);
				if ((860 < item[9].getX() && item[9].getX() < 1180) && (685 < item[9].getY() && item[9].getY() < 825)) {
					wrongSoundEffect();
					item[9].setLocation(550, 570);
					item[9].setVisible(false);
					JOptionPane.showMessageDialog(null, "코끼리는 바구니에 들어갈수 없어요!!", "코끼리 오류!",
							JOptionPane.INFORMATION_MESSAGE);

				}
				// 10. "오렌지" 초기좌표 : 293, 598
			} else if (item[10].getMousePosition() != null) { // 오렌지 1030. 730
				frame.setComponentZOrder(item[10], 0);
				int x = e.getX();
				int y = e.getY();
				item[10].setLocation(x - 50, y - 50);
				if ((860 < item[10].getX() && item[10].getX() < 1180)
						&& (685 < item[10].getY() && item[10].getY() < 825) && word[3].getName().equals("오렌지")) {
					soundEffect();
					item[10].setVisible(false);
					item[10].setName("null");
					word[3].setVisible(false);
					word[4].setVisible(true);
					word[4].setName("수박");
				} else if ((860 < item[10].getX() && item[10].getX() < 1180)
						&& (685 < item[10].getY() && item[10].getY() < 825) && word[3].getName().equals("null")) {
					wrongSoundEffect();
					item[10].setLocation(293, 598);
				}
			} else {
				item[0].setLocation(290, 570); // 드레그할때 이미지위에 마우스 포인터가 없을경우 원래 위치로
				item[1].setLocation(423, 578); // 프레임 밖으로 이미지가 나갔을경우 방지
				item[2].setLocation(545, 570);
				item[3].setLocation(410, 510);
				item[4].setLocation(210, 531);
				item[5].setLocation(170, 600);
				item[6].setLocation(290, 515);
				item[7].setLocation(420, 607);
				item[8].setLocation(529, 470);
				item[9].setLocation(550, 570);
				item[10].setLocation(293, 598);
				
				
				item[0].setVisible(false);
				item[1].setVisible(false);
				item[2].setVisible(false);
				item[3].setVisible(false);
				item[4].setVisible(false);
				item[5].setVisible(false);
				item[6].setVisible(false);
				item[7].setVisible(false);
				item[8].setVisible(false);
				item[9].setVisible(false);
				item[10].setVisible(false);

			}

		}
	}

	public void soundEffect() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("fruitShopSound/correctsound.wav"));
			Clip clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void wrongSoundEffect() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("fruitShopSound/worongsound.wav"));
			Clip clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void backgroundMusic() {
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new File("fruitShopSound/backgroundMusicOfFruitShop.wav"));
			clip = AudioSystem.getClip();
			clip.stop();
			clip.open(ais);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void changeMousePointer() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("fruitShopImage/FingerMousePointer.png").getScaledInstance(100, 100, 0);
		Point hotspot = new Point(0, 0);
		Cursor cursor = toolkit.createCustomCursor(image, hotspot, "FingerMousePointer");
		frame.setCursor(cursor);
	}
}