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




public class FruitShop { //과일가게 게임 구현 클래스
	
	JFrame frame;
	JLabel[] item = new JLabel[11]; 
	JLabel[] word = new JLabel[5];
	JLabel background, memoScreen, storeName;
	JButton backToMain;
	Clip clip;
	
	public FruitShop() {
		frame = new JFrame(); 
		frame.setBounds(350, 100, 1200, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setLayout(null);
		changeMousePointer();
		
		background = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/과일가게.png").getImage().getScaledInstance(1200, 900, 0)));
		background.setBounds(0, 0, 100, 100);
		frame.add(background);
		
		item[0] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/1딸기.png").getImage().getScaledInstance(100, 100, 0)));
		item[0].setBounds(70, 280, 100, 100);
		frame.add(item[0]); 
		
		item[1] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/2포도.png").getImage().getScaledInstance(100, 100, 0)));
		item[1].setBounds(320, 280, 100, 100);
		item[1].setName("포도");
		frame.add(item[1]);

		item[2] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/3토마토.png").getImage().getScaledInstance(100, 100, 0)));
		item[2].setBounds(550, 280, 100, 100);
		frame.add(item[2]);
		
		item[3] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/4키위.png").getImage().getScaledInstance(100, 100, 0)));
		item[3].setBounds(790, 280, 100, 100);
		frame.add(item[3]);
		
		item[4] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/5바나나.png").getImage().getScaledInstance(100, 100, 0)));
		item[4].setBounds(1030, 280, 100, 100);
		item[4].setName("바나나");
		frame.add(item[4]);
		
		item[5] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/6사과.png").getImage().getScaledInstance(100, 100, 0)));
		item[5].setBounds(70, 510, 100, 100);
		item[5].setName("사과");
		frame.add(item[5]);
		
		item[6] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/7수박.png").getImage().getScaledInstance(130, 130, 0)));
		item[6].setBounds(770, 490, 130, 130);
		item[6].setName("수박");
		frame.add(item[6]);
		
		item[7] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/8호박.png").getImage().getScaledInstance(100, 100, 0)));
		item[7].setBounds(1030, 510, 100, 100);
		frame.add(item[7]);
		
		item[8] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/9파인애플.png").getImage().getScaledInstance(100, 150, 0)));
		item[8].setBounds(70, 710, 100, 150);
		frame.add(item[8]);
		
		item[9] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/10페이크.png").getImage().getScaledInstance(100, 150, 0)));
		item[9].setBounds(790, 710, 100, 150);
		frame.add(item[9]);
		
		item[10] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/11오렌지.png").getImage().getScaledInstance(100, 100, 0)));
		item[10].setBounds(1030, 730, 100, 100);
		item[10].setName("오렌지");
		frame.add(item[10]);

		word[0] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/문제노란색바나나.png").getImage().getScaledInstance(300, 250 , 0)));
		word[0].setBounds(860, 100, 300, 250);
		word[0].setName("바나나");
		frame.add(word[0]);
		
		word[1] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/문제보라색포도.png").getImage().getScaledInstance(300, 250 , 0)));
		word[1].setBounds(860, 100, 300, 250);
		word[1].setName("null");
		word[1].setVisible(false);
		frame.add(word[1]);
		
		word[2] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/문제빨간색사과.png").getImage().getScaledInstance(300, 250 , 0)));
		word[2].setBounds(860, 100, 300, 250);
		word[2].setName("null");
		word[2].setVisible(false);
		frame.add(word[2]);
		
		word[3] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/문제주황색오렌지.png").getImage().getScaledInstance(300, 250 , 0)));
		word[3].setBounds(860, 100, 300, 250);
		word[3].setName("null");
		word[3].setVisible(false);
		frame.add(word[3]);
		
		word[4] = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/문제초록색수박.png").getImage().getScaledInstance(300, 250 , 0)));
		word[4].setBounds(860, 100, 300, 250);
		word[4].setName("null");
		word[4].setVisible(false);
		frame.add(word[4]);
		
		memoScreen = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/메모.png").getImage().getScaledInstance(280, 250 , 0)));
		memoScreen.setBounds(860, 40, 280, 250);
		frame.add(memoScreen);
		
		storeName = new JLabel(new ImageIcon(new ImageIcon("cartGameImage/과일가게상호.png").getImage().getScaledInstance(600, 350 , 0)));
		storeName.setBounds(200, 50, 600, 350);
		frame.add(storeName);
		
		//JButton으로 만든 뒤로가기 버튼
		backToMain = new JButton(new ImageIcon("cartGameImage/뒤로가기.png"));
		ImageIcon backMouseOver = new ImageIcon("cartGameImage/뒤로가기1.png");
		backToMain.setRolloverIcon(backMouseOver); //버튼위에 마우스가 있을경우 마우스 오버
		backToMain.setBounds(30, 50, 110, 110);
		backToMain.setBorderPainted(false);
		backToMain.setContentAreaFilled(false);
		backToMain.setFocusPainted(false);
		
		
		ActionListener backButtonListener = new ActionListener() {//뒤로가기버튼클릭시 이벤트
			@Override		
			public void actionPerformed(ActionEvent e) {		  //메인화면으로 이동하도록 함.
				clip.stop(); //배경음악 제거
				new SamMain();
				frame.dispose(); //진행중인창 닫기
			}
		};
		backToMain.addActionListener(backButtonListener); //뒤로가기 이벤트 버튼에 추가
		frame.add(backToMain); //프레임에 버튼 추가

		frame.addMouseMotionListener(new ItemMouseListener()); //마우스 모션 리스너(마우스 드레깅을위한 객체)
		
		
		
		
		
		
		//이미지의 상하위 순서 설정
		frame.setComponentZOrder(item[4], 1);   //바나나
		frame.setComponentZOrder(item[1], 2);   //포도
		frame.setComponentZOrder(item[5], 3);   //사과
		frame.setComponentZOrder(item[10], 4); //오렌지
		frame.setComponentZOrder(item[6], 5);   //수박 
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
		frame.setComponentZOrder(memoScreen, 17);
		frame.setComponentZOrder(storeName, 18);
		frame.setComponentZOrder(backToMain, 19);  //jButton, 뒤로가기
		frame.setComponentZOrder(background, 20);
		
		
		JOptionPane.showMessageDialog(null, "엄마를 도와 심부름을 할거에요!\n메모에 적힌 물건을 바구니에 담아보세요!", "게임 설명", JOptionPane.INFORMATION_MESSAGE);
		frame.setVisible(true);
		
		backgroundMusic();
	}
	
	class ItemMouseListener extends MouseAdapter {
		
		//1.딸기 (70, 280)
	  public void mouseDragged(MouseEvent e){ 		// itme1을 드레그 했을때 이벤트 발생
		  if(item[0].getMousePosition() != null) {  // 마우스 포인터의 위치가 이미지영역 내에 있을때, 프레임밖으로 마우스포인터가 나가거나
			  frame.setComponentZOrder(item[0], 0);
			  int x = e.getX();   					//마우스의 frame내 x 좌표값
			  int y = e.getY();  					 //마우스의 frame내 y 좌표값
			  item[0].setLocation(x-50, y-50);		 // 그림을 중심으로 안쪽으로 x,y-30씩 (위치 조정)
			  if((440 <item[0].getX() && item[0].getX() < 650) && (600 < item[0].getY()&& item[0].getY() < 880)) { //카트그림이 있는 영역내에 접근시
				  wrongSoundEffect();
				  item[0].setLocation(70, 280);
			  }
		  }	
		  
		  else if(item[1].getMousePosition() != null) {//포도 (문제O) 320, 280
			  frame.setComponentZOrder(item[1], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[1].setLocation(x-50, y-50); 
			  if((440 <item[1].getX() && item[1].getX() < 650) && (600 < item[1].getY()&& item[1].getY() < 880) && word[1].getName().equals("포도")) {
				  soundEffect();//정답일경우 딩동댕 소리 나도록
				  item[1].setVisible(false);
				  word[1].setVisible(false);
				  word[2].setVisible(true);
				  word[2].setName("사과");
			  }
			  else if((440 <item[1].getX() && item[1].getX() < 650) && (600 < item[1].getY()&& item[1].getY() < 880) && word[1].getName().equals("null")) {
				  wrongSoundEffect();
				  item[1].setLocation(320, 280);
			  }
			  
		  }
		  else if(item[2].getMousePosition() != null) { //토마토 550, 280
			  frame.setComponentZOrder(item[2], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[2].setLocation(x-50, y-50); 
			  if((440 <item[2].getX() && item[2].getX() < 650) && (600 < item[2].getY()&& item[2].getY() < 880)) {
				  wrongSoundEffect();
				  item[2].setLocation(550, 280);
			  }
		  }
		  else if(item[3].getMousePosition() != null) { //키위  790, 280
			  frame.setComponentZOrder(item[3], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[3].setLocation(x-50, y-50); 
			  if((440 <item[3].getX() && item[3].getX() < 650) && (600 < item[3].getY()&& item[3].getY() < 880)) {
				  wrongSoundEffect();
				  item[3].setLocation(790, 280);
			  }
		  }
		  else if(item[4].getMousePosition() != null) { //바나나  1030,280
			  frame.setComponentZOrder(item[4], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[4].setLocation(x-50, y-50);
			  if((440 <item[4].getX() && item[4].getX() < 650) && (600 < item[4].getY()&& item[4].getY() < 880) && word[0].getName().equals("바나나")) {
				  soundEffect();//정답일경우 딩동댕 소리 나도록
				  item[4].setVisible(false);
				  word[0].setVisible(false);
				  word[1].setVisible(true);
				  word[1].setName("포도");
			  }
				  else if ((440 <item[4].getX() && item[4].getX() < 650) && (600 < item[4].getY()&& item[4].getY() < 880) && word[0].getName().equals("null")){
					 item[4].setLocation(1030, 280); 
			  }
		  }
		  else if(item[5].getMousePosition() != null) { //사과 70 510
			  frame.setComponentZOrder(item[5], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[5].setLocation(x-50, y-50); 
			  if((440 <item[5].getX() && item[5].getX() < 650) && (600 < item[5].getY()&& item[5].getY() < 880) && word[2].getName().equals("사과")) {
				 soundEffect(); //정답일경우 딩동댕 소리 나도록
				 item[5].setVisible(false);
				 word[2].setVisible(false);
				 word[3].setVisible(true);
				 word[3].setName("오렌지");
			  }
			  else if((440 <item[5].getX() && item[5].getX() < 650) && (600 < item[5].getY()&& item[5].getY() < 880) && word[2].getName().equals("null")) {
				  wrongSoundEffect();
				  item[5].setLocation(70, 510);
			  }
		  }
		  
		  else if(item[6].getMousePosition() != null) { //수박 770. 490
			  frame.setComponentZOrder(item[6], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[6].setLocation(x-50, y-50); 
			  if((440 <item[6].getX() && item[6].getX() < 650) && (600 < item[6].getY()&& item[6].getY() < 880) && word[4].getName().equals("수박")) {
				  soundEffect(); //정답일경우 딩동댕 소리 나도록
				  item[6].setVisible(false);
				  word[4].setVisible(false);
				  clip.stop(); //배경음악 제거
				  JOptionPane.showMessageDialog(null, " 심부름을 완료했어요!\n 엄마가 기뻐하실꺼에요!", "심부름 성공!!", JOptionPane.INFORMATION_MESSAGE);
				  JOptionPane.showMessageDialog(null, "확인을 누르면 게임선택화면으로 이동합니다.", "게임 선택 화면으로 이동!",JOptionPane.INFORMATION_MESSAGE);
				  
				  
				  
				  
				  
				  PlayMain playMain = new PlayMain();
				  playMain.setVisible(true);
				  frame.dispose(); //이전화면 없애기!
			  
			  
			  
			  
			  
			  
			  }
			  
			  else if((440 <item[6].getX() && item[6].getX() < 650) && (600 < item[6].getY()&& item[6].getY() < 880) && word[4].getName().equals("null")) {
				  wrongSoundEffect();
				  item[6].setLocation(770, 490);
			  } 
		  }
		  
		  else if(item[7].getMousePosition() != null) { //호박 1030. 510
			  frame.setComponentZOrder(item[7], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[7].setLocation(x-50, y-50); 
			  if((440 <item[7].getX() && item[7].getX() < 650) && (600 < item[7].getY()&& item[7].getY() < 880)) {
				  wrongSoundEffect();
				  item[7].setLocation(1030, 510);
			  }
		  }
		  else if(item[8].getMousePosition() != null) {// 파인애플 70. 710
			  frame.setComponentZOrder(item[8], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[8].setLocation(x-50, y-50); 
			  if((440 <item[8].getX() && item[8].getX() < 650) && (600 < item[8].getY()&& item[8].getY() < 880)) {
				  wrongSoundEffect();
				  item[8].setLocation(70, 710);
			  }
		  }
		  else if(item[9].getMousePosition() != null) { //페이크 보너스 790. 710
			  frame.setComponentZOrder(item[9], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[9].setLocation(x-50, y-50); 
			  if((440 <item[9].getX() && item[9].getX() < 650) && (600 < item[9].getY()&& item[9].getY() < 880)) {
				  wrongSoundEffect();
				  item[9].setLocation(790, 710);
		JOptionPane.showMessageDialog(null, "코끼리는 바구니에 들어갈수 없어요!!", "코끼리 오류!", JOptionPane.INFORMATION_MESSAGE);
	  
				  
			  }
		  }
		  else if(item[10].getMousePosition() != null) { // 오렌지 1030. 730
			  frame.setComponentZOrder(item[10], 0);
			  int x = e.getX();
			  int y = e.getY();
			  item[10].setLocation(x-50, y-50); 
			  if((440 <item[10].getX() && item[10].getX() < 650) && (600 < item[10].getY()&& item[10].getY() < 880) && word[3].getName().equals("오렌지")) {
				  soundEffect();
				  item[10].setVisible(false);
				  word[3].setVisible(false);
				  word[4].setVisible(true);
				  word[4].setName("수박");
			  }
			  else if((440 <item[10].getX() && item[10].getX() < 650) && (600 < item[10].getY()&& item[10].getY() < 880) && word[3].getName().equals("null")){
				  wrongSoundEffect();
				  item[10].setLocation(1030, 730); 
			  }
		  }
		  else {
			  item[0].setLocation(70, 280);        //드레그할때 이미지위에 마우스 포인터가 없을경우 원래 위치로
			  item[1].setLocation(320, 280);		//프레임 밖으로 이미지가 나갔을경우 방지
			  item[2].setLocation(550, 280);
			  item[3].setLocation(790, 280);
			  item[5].setLocation(70, 510);
			  item[6].setLocation(770, 490);
			  item[7].setLocation(1030, 510);
			  item[8].setLocation(70, 710);
			  item[9].setLocation(790, 710);
			  item[10].setLocation(1030, 730);

		  }
		  
	  }
	}
	
	public void soundEffect() {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("SoundEffect/correctsound.wav"));
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
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("SoundEffect/worongsound.wav"));
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
			AudioInputStream ais = AudioSystem.getAudioInputStream(new File("SoundEffect/backgroundMusicOfFruitShop.wav"));
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
	
	public void changeMousePointer(){
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image = toolkit.getImage("cartGameImage/FingerMousePointer.png").getScaledInstance(100, 100, 0);
		Point hotspot = new Point(0,0);
		Cursor cursor = toolkit.createCustomCursor(image, hotspot, "FingerMousePointer");
		frame.setCursor(cursor);
		
		
		
	}
}

	

