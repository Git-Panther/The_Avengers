package samMain.play;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import samMain.main.SamMain;

public class CleanRoomPlay {
   JFrame frame;
   JLabel item1;
   JLabel item11;
   JLabel item2;
   JLabel item22;
   JLabel item3;
   JButton backMainBtn;

   Clip clip1;// 체크 사운드 클립
   Clip clip2;// BGM 클립

   public CleanRoomPlay() {
      frame = new JFrame("뽀로로와 크롱의 정리정돈");
      frame.setBounds(350, 80, 1210, 940);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setResizable(false);
      frame.setLayout(null);
      frame.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());

      Toolkit toolkit = Toolkit.getDefaultToolkit();
      Image image = toolkit.getImage("CleanRoom/뽀로로/커서.png").getScaledInstance(100, 100, 0);
      Point hotspot = new Point(0, 0);
      Cursor cursor = toolkit.createCustomCursor(image, hotspot, "커서");
      frame.setCursor(cursor);
      
      JLabel background = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뽀로로배경화면.png").getImage()));
      background.setBounds(0, 0, 1210, 940);
      frame.setContentPane(background);
      
      ImageIcon backNormal = new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뒤로가기.png").getImage().getScaledInstance(100, 40, 0));
      ImageIcon backFocus = new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뒤로가기.gif").getImage().getScaledInstance(100, 40, 0));
      backMainBtn = new JButton(backNormal);
      backMainBtn.setBounds(30,120,100, 40);//좌표 창크기
      backMainBtn.setBackground(new Color(0,0,0,1));
      backMainBtn.setBorderPainted(false);
      backMainBtn.setFocusPainted(false);
      backMainBtn.setContentAreaFilled(false);

      frame.add(backMainBtn);
      backMainBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            int result = JOptionPane.showConfirmDialog(frame, "게임선택메뉴로 돌아갈까요?", "돌아가기",
                  JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {
               
               clip2.stop();
               PlayMain playMain = new PlayMain();
                playMain.setVisible(true);
               frame.setVisible(false);
            }
         }
      });
      backMainBtn.addMouseListener(new MouseAdapter() {

  		@Override
  		public void mouseEntered(MouseEvent e) {
  			// TODO Auto-generated method stub
  			backMainBtn.setIcon(backFocus);
  		}

  		@Override
  		public void mouseExited(MouseEvent e) {
  			// TODO Auto-generated method stub
  			backMainBtn.setIcon(backNormal);
  		}
      });
  
      item1 = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/fruit/" + (int) ((Math.random() * 4) + 1) + ".png")
            .getImage().getScaledInstance(90, 90, 0)));
      item1.setBounds(140 + ((int) Math.random() * 30), 150, 200, 200);
      frame.add(item1);

      item11 = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/kit/" + (int) ((Math.random() * 4) + 1) + ".png")
            .getImage().getScaledInstance(90, 90, 0)));
      item11.setBounds(300, 240 + ((int) Math.random() * 30), 200, 200);
      frame.add(item11);

      item2 = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/kit/kit2/" + (int) ((Math.random() * 4) + 6) + ".png")
            .getImage().getScaledInstance(90, 90, 0)));
      item2.setBounds(440 + ((int) Math.random() * 30), 150, 200, 200);
      frame.add(item2);

      item22 = new JLabel(
            new ImageIcon(new ImageIcon("CleanRoom/fruit/fruit2/" + (int) ((Math.random() * 4) + 6) + ".png")
                  .getImage().getScaledInstance(90, 90, 0)));
      item22.setBounds(600, 240 + ((int) Math.random() * 30), 200, 200);
      frame.add(item22);

      item3 = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/vehicle/" + (int) (Math.random() * 9 + 1) + ".png")
            .getImage().getScaledInstance(90, 90, 0)));
      item3.setBounds(740 + ((int) Math.random() * 30), 150, 200, 200);
      frame.add(item3);

      frame.setComponentZOrder(item1, 1);
      frame.setComponentZOrder(item11, 1);
      frame.setComponentZOrder(item2, 1);
      frame.setComponentZOrder(item22, 1);
      frame.setComponentZOrder(item3, 1);
      frame.setComponentZOrder(backMainBtn, 1);

      frame.setComponentZOrder(background, 7);

      frame.addMouseListener(new ItmeMouseListener());
      frame.addMouseMotionListener(new ItmeMouseListener());

      frame.setVisible(true);
      backgroundSoundStrat();

   }

   class ItmeMouseListener extends MouseAdapter {

      /*
       * public void mouseClicked(MouseEvent e) { System.out.println(e.getX() + "/" +
       * e.getY()); }
       */

      @Override
      public void mouseDragged(MouseEvent e) {

         if (item1.getMousePosition() != null) {
            int x = e.getX();
            int y = e.getY();
            item1.setLocation(x - 90, y - 90);
            frame.setComponentZOrder(item1, 0);

         } else if (item11.getMousePosition() != null) {
            int x = e.getX();
            int y = e.getY();
            item11.setLocation(x - 90, y - 90);

            frame.setComponentZOrder(item11, 0);

         } else if (item2.getMousePosition() != null) {
            int x = e.getX();
            int y = e.getY();
            item2.setLocation(x - 90, y - 90);

            frame.setComponentZOrder(item2, 0);

         } else if (item22.getMousePosition() != null) {
            int x = e.getX();
            int y = e.getY();
            item22.setLocation(x - 90, y - 90);

            frame.setComponentZOrder(item22, 0);

         } else if (item3.getMousePosition() != null) {
            int x = e.getX();
            int y = e.getY();
            item3.setLocation(x - 90, y - 90);
            frame.setComponentZOrder(item3, 0);
         }

      }

      @Override
      public void mouseReleased(MouseEvent e) {

         if ((90 < item3.getX() && item3.getX() < 350) && (550 < item3.getY() && item3.getY() < 820)
               && (item3.isVisible())) {

            item3.setVisible(false);
            ckeckSound();

         }
         if ((500 < item11.getX() && item11.getX() < 800) && (550 < item11.getY() && item11.getY() < 820)
               && (item11.isVisible())) {
            item11.setVisible(false);

            ckeckSound();

         }
         if ((500 < item2.getX() && item2.getX() < 800) && (550 < item2.getY() && item2.getY() < 820)
               && (item2.isVisible())) {
            item2.setVisible(false);
            ckeckSound();

         }
         if ((950 < item1.getX() && item1.getX() < 1050) && (550 < item1.getY() && item1.getY() < 860)
               && (item1.isVisible())) {
            item1.setVisible(false);

            ckeckSound();

         }
         if ((950 < item22.getX() && item22.getX() < 1050) && (550 < item22.getY() && item22.getY() < 860)
               && (item22.isVisible())) {
            item22.setVisible(false);

            ckeckSound();

         }

         ckeck();

      }

   }

   public void ckeck() {
      if (item1.isVisible() == false && item11.isVisible() == false && item2.isVisible() == false
            && item22.isVisible() == false && item3.isVisible() == false) {

         int result = JOptionPane.showConfirmDialog(frame, "게임을 다시 시작하시겠습니까?", "잘했어요!!",
               JOptionPane.OK_CANCEL_OPTION);
         if (result == 0) {
            backgroundSoundStop();
            frame.dispose();
            new CleanRoomPlay();
         } else {
            int result1 = JOptionPane.showConfirmDialog(frame, "게임선택메뉴로 돌아갈까요?", "확인용",
                  JOptionPane.OK_CANCEL_OPTION);
            if (result1 == 0) {
               backgroundSoundStop();
               PlayMain playMain = new PlayMain();
               playMain.setVisible(true);
               frame.setVisible(false);
            } else {
               int result2 = JOptionPane.showConfirmDialog(frame, "메인메뉴로 돌아갈까요?", "확인용",
                     JOptionPane.OK_CANCEL_OPTION);
               if (result2 == 0) {
                  backgroundSoundStop();
                  new SamMain();
                  frame.setVisible(false);
               } else {

                  JOptionPane.showMessageDialog(frame, "게임으로 돌아갈께요!\n메뉴를 보려면 한번더 클릭하세요!", "확인용",
                        JOptionPane.INFORMATION_MESSAGE);
               }
            }
         }

      }
   }

   public void ckeckSound() {

      /*
       * if ((90 < item3.getX() && item3.getX() < 350) && (550 < item3.getY() &&
       * item3.getY() < 820)&&(item1.isVisible() == false)) { try { AudioInputStream
       * as = AudioSystem.getAudioInputStream(new File("ckeckSound.wav")); clip1 =
       * AudioSystem.getClip(); clip1.stop(); clip1.open(as); clip1.start(); } catch
       * (Exception e) { e.printStackTrace(); } } else if ((500 < item11.getX() &&
       * item11.getX() < 800) && (550 < item11.getY() && item11.getY() <
       * 820)&&(item11.isVisible() == false)) { try { AudioInputStream as =
       * AudioSystem.getAudioInputStream(new File("ckeckSound.wav")); clip1 =
       * AudioSystem.getClip(); clip1.stop(); clip1.open(as); clip1.start(); } catch
       * (Exception e) { e.printStackTrace(); } } else if ((500 < item11.getX() &&
       * item11.getX() < 800) && (550 < item11.getY() && item11.getY() <
       * 820)&&(item2.isVisible() == false)) { try { AudioInputStream as =
       * AudioSystem.getAudioInputStream(new File("ckeckSound.wav")); clip1 =
       * AudioSystem.getClip(); clip1.stop(); clip1.open(as); clip1.start(); } catch
       * (Exception e) { e.printStackTrace(); } } else if ((950 < item1.getX() &&
       * item1.getX() < 1050) && (550 < item1.getY() && item1.getY() <
       * 860)&&(item22.isVisible() == false)) { try { AudioInputStream as =
       * AudioSystem.getAudioInputStream(new File("ckeckSound.wav")); clip1 =
       * AudioSystem.getClip(); clip1.stop(); clip1.open(as); clip1.start(); } catch
       * (Exception e) { e.printStackTrace(); } } else if ((950 < item1.getX() &&
       * item1.getX() < 1050) && (550 < item1.getY() && item1.getY() <
       * 860)&&(item3.isVisible() == false)) { try { AudioInputStream as =
       * AudioSystem.getAudioInputStream(new File("ckeckSound.wav")); clip1 =
       * AudioSystem.getClip(); clip1.stop(); clip1.open(as); clip1.start(); } catch
       * (Exception e) { e.printStackTrace(); } }
       */
      try {
         /*
          * if((clip1 !=null)&&(clip1.isOpen())) { clip1.stop(); clip1.close(); }
          */
         AudioInputStream as = AudioSystem.getAudioInputStream(new File("soundfile/ckeckSound.wav"));
         clip1 = AudioSystem.getClip();

         clip1.open(as);
         clip1.start();
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   public void backgroundSoundStrat() {
      try {
         AudioInputStream as = AudioSystem.getAudioInputStream(new File("soundfile/backgroundMusic.wav"));
         clip2 = AudioSystem.getClip();
         clip2.stop();
         clip2.open(as);
         clip2.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public void backgroundSoundStop() {
      if (item1.isVisible() == false && item11.isVisible() == false && item2.isVisible() == false
            && item22.isVisible() == false && item3.isVisible() == false) {
         clip2.stop();
         clip2.close();
      }
   }
}