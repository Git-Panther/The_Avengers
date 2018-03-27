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
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import samMain.main.SamMain;

public class BodyGame implements ActionListener, MouseListener {

   private AudioInputStream as;
   private AudioInputStream as2;
   private Clip clip1;
   private Clip clip2;

   private JFrame frame;
   private JPanel bg;
   private JButton backBtn, mainBtn, helpBtn;
   private List<String> quizList = new ArrayList<String>();
   private int[] qListIdxArr;
   private JLabel quizTextLabel;
   private int quizListIdx = 0;
   private int preQuizListIdx = -1;
   private ImageIcon oGif = new ImageIcon("images/o.gif");
   private ImageIcon xGif = new ImageIcon("images/x.gif");
   private JButton gifBtn;

   public BodyGame() {
      frame = new JFrame();
      initQuiz();
      
      frame.setTitle("머리어깨무릎발무릎발");
      frame.setLayout(null);
      frame.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
      frame.setBounds(350, 100, 1200, 930);
      
      try {
            as = AudioSystem.getAudioInputStream(new File("sounds/game3BgSound.wav"));
            clip1 = AudioSystem.getClip();
            clip1.stop();
            clip1.open(as);
            clip1.start();
            clip1.loop(Clip.LOOP_CONTINUOUSLY);
         } catch (Exception e) {
            e.printStackTrace();
         }
      
      Toolkit tk = Toolkit.getDefaultToolkit();
      Image cursorImg = tk.getImage("images/myCursor.png");
      Point point = new Point(0, 0);
      Cursor cursor = tk.createCustomCursor(cursorImg, point, "roman");
      frame.setCursor(cursor);
      
      bg = new JPanel();
      bg.setBounds(0, 0, 1200, 900);
      bg.setLayout(null);
      bg.setBackground(Color.WHITE);
       
      JLabel bgImgLabel = new JLabel(new ImageIcon("images/sketchbookBg.png"));
      bgImgLabel.setBounds(0, 0, 1200, 900);
      
      JPanel quizPanel = new JPanel();
      quizPanel.setLayout(null);
      quizPanel.setBounds(110, 300, 400, 300);
      quizPanel.setBackground(Color.WHITE);
      JLabel quizPanelBg = new JLabel();
      quizPanelBg.setBounds(0, 0, 400, 550);
      quizPanel.add(quizPanelBg);
      
      JPanel quizTextPanel = new JPanel();
      // quizList에서 quizTextLabel의 Text 값 꺼내오기
      // 클릭 이벤트 발생하고 정답을 맞춘 경우 ++idx 번째 인덱스의 값으로 setText 해주기. 
      quizTextLabel = new JLabel(quizList.get(qListIdxArr[quizListIdx]), JLabel.CENTER);
//      Font f = new Font("상상토끼 신비는 일곱살", Font.BOLD, 300);
      Font f = new Font("타이포_쌍문동 B", Font.PLAIN, 200);
      
      quizTextLabel.setForeground(Color.BLACK);
      quizTextLabel.setFont(f);
      quizTextPanel.add(quizTextLabel);
      quizTextPanel.setBackground(Color.WHITE);
      quizTextPanel.setBounds(130, 320, 400, 300);
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

      frame.add(backBtn);
      frame.add(mainBtn);
      frame.add(helpBtn);
      
      frame.add(gifBtn);
      
      frame.add(bg);
         
      backBtn.addActionListener(this);
      mainBtn.addActionListener(this);
      helpBtn.addActionListener(this);
      
      backBtn.addMouseListener(this);
      mainBtn.addMouseListener(this);
      helpBtn.addMouseListener(this);
      
      imgPanel.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseReleased(MouseEvent e) {
            // 준비된 문제를 다 풀었을 경우
            if(quizListIdx == quizList.size()) {
               new PopupThread().start();
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
                  new NextQuizThread().start();
//                  quizTextLabel.setText(quizList.get(qListIdxArr[++quizListIdx]));

               } else {
                  quizListIdx++;
               }
            } 
         }
      });
      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      frame.setResizable(false);
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
      // 처음 퀴즈리스트 순서도 랜덤하게 할거야
      reorderQuizList();
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
         new ExplainDialog(frame);
      }
      if(e.getSource() == backBtn) {
         clip1.stop();
         PlayMain playMain = new PlayMain();
         playMain.setVisible(true);
         frame.dispose();
      }
      if(e.getSource() == mainBtn) {
         clip1.stop();
         new SamMain();
         frame.dispose();
      }
   }

   class MyDialog extends JDialog {

	private static final long serialVersionUID = -7733104673897654178L;

	public MyDialog(JFrame frame) {
         // 다이얼로그 위치 게임화면 중앙.
         setBounds((frame.getWidth() - 500) / 2 + frame.getX(), (frame.getHeight() - 200) / 2 + frame.getY(), 500,
               200);
         setLayout(null);
         setUndecorated(true);
         setBackground(new Color(0, 0, 0, 1));
         setModal(true);

         ImageIcon bg = new ImageIcon(new ImageIcon("images/myDialogBgImg.png").getImage());
         JLabel bgLabel = new JLabel(bg);
         bgLabel.setBounds(0, 0, getWidth(), getHeight());
         setContentPane(bgLabel);
         
         JLabel textLabel = new JLabel("다시 시작하시겠습니까?");
         textLabel.setBounds(120, 0, 300, 180);
         textLabel.setFont(new Font("상상토끼 신비는 일곱살", Font.PLAIN, 50));
         getContentPane().add(textLabel);
         
         JButton yesBtn = new JButton(new ImageIcon("images/yes.png"));
         yesBtn.setContentAreaFilled(false);
         yesBtn.setBorderPainted(false);
         yesBtn.setFocusPainted(false);
         
         JButton noBtn = new JButton(new ImageIcon("images/no.png"));
         noBtn.setContentAreaFilled(false);
         noBtn.setBorderPainted(false);
         noBtn.setFocusPainted(false);

         yesBtn.setBounds(120, 130, 63, 50);
         noBtn.setBounds(320, 130, 63, 50);
         
         getContentPane().add(yesBtn);
         getContentPane().add(noBtn);
         
         yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // 확인 버튼 누른 경우 문제 처음부터 다시 출력
               // 문제 순서 랜덤하게 출력되도록 할까?
               reorderQuizList();
               quizListIdx = 0;
               quizTextLabel.setText(quizList.get(qListIdxArr[quizListIdx]));
               dispose();
            }
         });
         
         yesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               yesBtn.setIcon(new ImageIcon("images/pressedYes.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
               yesBtn.setIcon(new ImageIcon("images/yes.png"));
            }
         });
         
         noBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });
         
         noBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               noBtn.setIcon(new ImageIcon("images/pressedNo.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
               noBtn.setIcon(new ImageIcon("images/no.png"));
            }
         });
         
         setVisible(true);
      }
   }
   
   class ExplainDialog extends JDialog {
	private static final long serialVersionUID = 4186769196732736749L;

	public ExplainDialog(JFrame frame) {
         // 다이얼로그 위치 게임화면 중앙.
         setBounds((frame.getWidth() - 500) / 2 + frame.getX(), (frame.getHeight() - 200) / 2 + frame.getY(), 500,
               200);
         setLayout(null);
         setUndecorated(true);
         setBackground(new Color(0, 0, 0, 1));
         setModal(true);

         ImageIcon bg = new ImageIcon(new ImageIcon("images/myDialogBgImg.png").getImage());
         JLabel bgLabel = new JLabel(bg);
         bgLabel.setBounds(0, 0, getWidth(), getHeight());
         setContentPane(bgLabel);
         
         JLabel textLabel = new JLabel("화면의 왼쪽에 제시된 낱말에 해당하는 신체부위를");
         JLabel textLabel2 = new JLabel("오른쪽 화면에서 찾아 클릭하세요.");
         textLabel.setBounds(90, 0, 400, 150);
         textLabel.setFont(new Font("상상토끼 신비는 일곱살", Font.PLAIN, 32));
         textLabel2.setBounds(150, 30, 400, 150);
         textLabel2.setFont(new Font("상상토끼 신비는 일곱살", Font.PLAIN, 32));
         getContentPane().add(textLabel);
         getContentPane().add(textLabel2);
         
         JButton yesBtn = new JButton(new ImageIcon("images/ok.png"));
         yesBtn.setContentAreaFilled(false);
         yesBtn.setBorderPainted(false);
         yesBtn.setFocusPainted(false);

         yesBtn.setBounds(200, 130, 63, 50);
         
         getContentPane().add(yesBtn);
         
         yesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               dispose();
            }
         });
         
         yesBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               yesBtn.setIcon(new ImageIcon("images/pressedOk.png"));
            }
            @Override
            public void mouseExited(MouseEvent e) {
               yesBtn.setIcon(new ImageIcon("images/ok.png"));
            }
         });

         setVisible(true);
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
   
   class NextQuizThread extends Thread {
      // o, x 버튼 사라지고 다음 문제 출력되도록 
      @Override
      public void run() {
         try {
            if(preQuizListIdx != quizListIdx) {
               preQuizListIdx = quizListIdx;
               Thread.sleep(1000);
               quizTextLabel.setText(quizList.get(qListIdxArr[++quizListIdx]));
            }
         } catch (InterruptedException ie) {
            ie.printStackTrace();
         }
      }
   }
   
   class PopupThread extends Thread {
      // o, x 사라지고 난 뒤에 다시 학습하겠냐는 JOptionPane 출력되게
      @Override
      public void run() {
         try {
            if(preQuizListIdx != quizListIdx) {
               Thread.sleep(1000);
               quizTextLabel.setText("끝");
               new MyDialog(frame);
            }
            
         } catch (InterruptedException ie) {
            ie.printStackTrace();
         }
      }
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      if(e.getSource() == backBtn) {
         backBtn.setIcon((new ImageIcon("images/backBtn.gif")));
      }
      else if(e.getSource() == mainBtn) {
         mainBtn.setIcon((new ImageIcon("images/mainBtn.gif")));
      }
      else if(e.getSource() == helpBtn) {
         helpBtn.setIcon((new ImageIcon("images/helpBtn.gif")));
      }
   }

   @Override
   public void mouseExited(MouseEvent e) {
      backBtn.setIcon(new ImageIcon("images/backBtnImg.png"));      
      mainBtn.setIcon(new ImageIcon("images/mainBtnImg.png"));      
      helpBtn.setIcon(new ImageIcon("images/helpBtnImg.png"));      
   }

   @Override
   public void mouseClicked(MouseEvent e) {}
   @Override
   public void mousePressed(MouseEvent e) {}
   @Override
   public void mouseReleased(MouseEvent e) {}

}
