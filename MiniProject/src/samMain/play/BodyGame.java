package samMain.play;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import samMain.main.SamMainFrame;

public class BodyGame extends JFrame implements ActionListener {

   /**
	 * 
	 */
	private static final long serialVersionUID = -3729103256794593715L;
	private AudioInputStream as;
   private AudioInputStream as2;
   private Clip clip1;
   private Clip clip2;

   private JPanel bg;
   private JButton backBtn, mainBtn, helpBtn;
   private List<String> quizList = new ArrayList<String>();
   private int[] qListIdxArr;
   private JLabel quizTextLabel;
   private int quizListIdx = 0;
   private ImageIcon oGif = new ImageIcon("images/o.gif");
   private ImageIcon xGif = new ImageIcon("images/x.gif");
   private JButton gifBtn;

   public BodyGame() {
      initQuiz();
      // 처음 퀴즈리스트 순서도 랜덤하게 할거야
      reorderQuizList();
      
      setTitle("놀이학습 - 머리어깨무릎발무릎발");
      setLayout(null);
      setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
      setBounds(350, 100, 1210, 930);
      
      try {
            as = AudioSystem.getAudioInputStream(new File("sounds/game3BgSound.wav"));
            clip1 = AudioSystem.getClip();
            clip1.stop();
            clip1.open(as);
            clip1.start();
         } catch (Exception e) {
            e.printStackTrace();
         }
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      Image cursorImg = tk.getImage("images/myCursor.png");
      Point point = new Point(0, 0);
      Cursor cursor = tk.createCustomCursor(cursorImg, point, "roman");
      setCursor(cursor);
      
      bg = new JPanel();
      bg.setBounds(0, 0, 1200, 900);
      bg.setLayout(null);
      bg.setBackground(Color.WHITE);
       
      JLabel bgImgLabel = new JLabel(new ImageIcon("images/sketchbookBg.png"));
      bgImgLabel.setBounds(0, 0, 1200, 900);
      
      JPanel quizPanel = new JPanel();
      quizPanel.setLayout(null);
      quizPanel.setBounds(110, 90, 400, 550);
      quizPanel.setBackground(Color.WHITE);
      JLabel quizPanelBg = new JLabel();
      quizPanelBg.setBounds(0, 0, 400, 550);
      quizPanel.add(quizPanelBg);
      
      JPanel quizTextPanel = new JPanel();
      // quizMap에서 quizTextLabel의 Text 값 꺼내오기
      // 클릭 이벤트 발생하고 정답을 맞춘 경우 ++idx 번째 인덱스의 값으로 setText 해주기. 
      quizTextLabel = new JLabel(quizList.get(qListIdxArr[quizListIdx]), JLabel.CENTER);
//      Font f = new Font("상상토끼 신비는 일곱살", Font.BOLD, 300);
      Font f = new Font("타이포_쌍문동 B", Font.PLAIN, 200);
      
      quizTextLabel.setForeground(Color.BLACK);
      quizTextLabel.setFont(f);
      quizTextPanel.add(quizTextLabel);
      quizTextPanel.setBackground(Color.WHITE);
      quizTextPanel.setBounds(130, 300, 400, 300);
      bg.add(quizTextPanel);

      JPanel imgPanel = new JPanel();
      imgPanel.setBackground(Color.WHITE);
      imgPanel.setBounds(650, 130, 450, 700);
      imgPanel.setOpaque(false); 
   
      // gif 이미지 파일은 배경 투명하게 못하나?
      // -> 메인 프레임에 add.
      gifBtn = new JButton(oGif);
      gifBtn.setBounds(250, 100, 700, 700);
      gifBtn.setVisible(false);
      gifBtn.setContentAreaFilled(false);
      gifBtn.setBorderPainted(false);
      gifBtn.setFocusPainted(false);
      
      bg.add(quizPanel);
      bg.add(imgPanel);
      bg.add(bgImgLabel);

      backBtn = new JButton(new ImageIcon("images/backBtnImg.png"));
      mainBtn = new JButton(new ImageIcon("images/mainBtnImg.png"));
      helpBtn = new JButton(new ImageIcon("images/helpBtnImg.png"));
      backBtn.setContentAreaFilled(false);
      backBtn.setBorderPainted(false);
      backBtn.setFocusPainted(false); 
      mainBtn.setContentAreaFilled(false);
      mainBtn.setBorderPainted(false);
      mainBtn.setFocusPainted(false);
      helpBtn.setContentAreaFilled(false);
      helpBtn.setBorderPainted(false);
      helpBtn.setFocusPainted(false);

      backBtn.setBounds(15, 840, 100, 40);
      mainBtn.setBounds(550, 840, 100, 40);
      helpBtn.setBounds(1070, 840, 100, 40);

      add(backBtn);
      add(mainBtn);
      add(helpBtn);
      
      add(gifBtn);
      
      add(bg);
         
      backBtn.addActionListener(this);
      mainBtn.addActionListener(this);
      helpBtn.addActionListener(this);

      imgPanel.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseReleased(MouseEvent e) {
            // 준비된 문제를 다 풀었을 경우
            if(quizListIdx == quizList.size() && gifBtn.getIcon() == oGif) {
               int result = JOptionPane.showConfirmDialog(bg, "문제풀이가 완료되었습니다.\n 다시 학습하시겠습니까?", "축하합니다!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, new ImageIcon("imagse/helpIcon.png"));
               // 확인 버튼 누른 경우 문제 처음부터 다시 출력
               // 문제 순서 랜덤하게 출력되도록 할까?
               if(result == 0) {
                  reorderQuizList();
                  quizListIdx = 0;
                  quizTextLabel.setText(quizList.get(qListIdxArr[quizListIdx]));
               }
            }
         }

         @Override
         public void mousePressed(MouseEvent e) {
            // -650, -130
            if(quizTextLabel.getText().equals("손")) {
               if(e.getX() > 23 && e.getX() < 83 && e.getY() > 350 && e.getY() < 420 // 왼손
                     || e.getX() > 352 && e.getX() < 405 && e.getY() > 340 && e.getY() < 400) { // 오른손
                  gifBtn.setIcon(oGif); 
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("발")) {
               if(e.getX() > 150 && e.getX() < 200 && e.getY() > 600 && e.getY() < 630 
                     || e.getX() > 250 && e.getX() < 300 && e.getY() > 600 && e.getY() < 630) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("눈")) {
               if(e.getX() > 145 && e.getX() < 180 && e.getY() > 207 && e.getY() < 230 
                     || e.getX() > 235 && e.getX() < 270 && e.getY() > 215 && e.getY() < 235) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("코")) {
               if(e.getX() > 200 && e.getX() < 225 && e.getY() > 225 && e.getY() < 250) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("입")) {
               if(e.getX() > 165 && e.getX() < 245 && e.getY() > 258 && e.getY() < 285) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("귀")) {
               if(e.getX() > 58 && e.getX() < 100 && e.getY() > 195 && e.getY() < 240 
                     || e.getX() > 325 && e.getX() < 355 && e.getY() > 195 && e.getY() < 240) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("목")) {
               if(e.getX() > 189 && e.getX() < 230 && e.getY() > 300 && e.getY() < 330) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("팔")) {
               if(((e.getX() > 75 && e.getX() < 150) && (e.getY() > 320 && e.getY() < 380))
                  || ((e.getX() > 273 && e.getX() < 350) && (e.getY() > 320 && e.getY() < 375))){
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("다리")) {
               if(e.getX() > 150 && e.getX() < 215 && e.getY() > 490 && e.getY() < 590 
                     || e.getX() > 215 && e.getX() < 290 && e.getY() > 490 && e.getY() < 590) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            if(quizTextLabel.getText().equals("배")) {
               if(e.getX() > 147 && e.getX() < 282 && e.getY() > 385 && e.getY() < 490) { 
                  gifBtn.setIcon(oGif);
               } else {
                  gifBtn.setIcon(xGif);
               }
            }
            
            // 스레드 사용해서 o,x 이미지 잠시동안만 보여지도록 할까?
            new GifBtnThread().start();
            setSound();
            // 문제를 맞춘 경우에만 다음 문제 보여주기
            if(quizListIdx < quizList.size()&& gifBtn.getIcon() == oGif) {
               if(quizListIdx < quizList.size() - 1) {
                  quizTextLabel.setText(quizList.get(qListIdxArr[++quizListIdx]));
               } else {
                  quizListIdx++;
               }
            } 
         }
      });
      
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(true);
      setResizable(false);
   }
   
   public void initQuiz() {
      quizList.add("손");
      quizList.add("발");
      quizList.add("눈");
      quizList.add("코");
      quizList.add("입");
      quizList.add("귀");
      quizList.add("목");
      quizList.add("팔");
      quizList.add("다리");
      quizList.add("배");
      
      qListIdxArr = new int[quizList.size()];
      // 0 ~ 9로 기본 인덱스 초기화
      for(int i=0; i<quizList.size(); i++) {
         qListIdxArr[i] = i;
      }
   }
   
   public void reorderQuizList() {
      for (int i = 0; i < quizList.size(); i++) {
         qListIdxArr[i] = (int) (Math.random() * quizList.size());
         for (int j = 0; j < i; j++) {
            if (qListIdxArr[j] == qListIdxArr[i]) {
               i--;
               break;
            }
         }
      }
//      for(int n : qListIdxArr) {
//         System.out.println(n);
//      }
   }
   
   public void setSound() {
      try {
         if(gifBtn.getIcon() == oGif) {
               as2 = AudioSystem.getAudioInputStream(new File("sounds/correctsoundVoice.wav"));
         } else if(gifBtn.getIcon() == xGif) {
            as2 = AudioSystem.getAudioInputStream(new File("sounds/worongsound.wav"));
         }
         clip2 = AudioSystem.getClip();
         clip2.stop();
         clip2.open(as2);
         clip2.start();
      } catch (Exception exception) {
         exception.printStackTrace();
      }
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == helpBtn) {
         JOptionPane.showMessageDialog(this, "화면의 왼쪽에 제시된 낱말에 해당하는 부위를 오른쪽 이미지에서 찾아 클릭하세요."
               , "*게임 방법*", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("images/helpIcon.png"));
      }
      if(e.getSource() == backBtn) {
         clip1.stop();
         new PlayMain().setVisible(true);
         dispose();
      }
      if(e.getSource() == mainBtn) {
         clip1.stop();
        new SamMainFrame().setVisible(true);;
         dispose();
      }
   }

   class GifBtnThread extends Thread {
      // 일정시간 동안만 gif 이미지 보여줌
      @Override
      public void run() {
         try {
            gifBtn.setVisible(true);
            Thread.sleep(1000);
            gifBtn.setVisible(false);
            gifBtn.setIcon(xGif);
         } catch (InterruptedException ie) {
            ie.printStackTrace();
         }
      }
      
   }
   

}