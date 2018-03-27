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

public class CleanRoomMain {
   JFrame mainFrame = new JFrame("뽀로로와 크롱의 정리정돈");
   JButton gameinfoBtn,gamePlayBtn,gameMethodBtn,backMainBtn;
   Clip clip1;

   public CleanRoomMain() {
      mainFrame.setBounds(350, 80, 1210, 940);
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setLayout(null);
      mainFrame.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
      mainFrame.setCursor(getMousePointer());
      
      JLabel background = new JLabel(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뽀로로정리정돈.png").getImage().getScaledInstance(1200, 900, 0)));
      background.setBounds(0, 0, 1200, 900);
      mainFrame.setContentPane(background);

      gameinfoBtn = new JButton(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임소개.png").getImage().getScaledInstance(313, 101, 0)));
      gameinfoBtn.setBounds(58,697,313, 101);
      gameinfoBtn.setBorderPainted(false);
      gameinfoBtn.setFocusPainted(false);
      mainFrame.add(gameinfoBtn);

      gameinfoBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(background, "뽀로로와 크롱의 정리정돈!!\n방이 너무 더러워요!!!\n 정리해주세요!!! ");
            
         }
      });
      
      gameinfoBtn.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseEntered(MouseEvent e) {         
            gameinfoBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임소개.gif").getImage().getScaledInstance(313, 101, 0)));      
            
         }
         @Override
         public void mouseExited(MouseEvent e) {
            gameinfoBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임소개.png").getImage().getScaledInstance(313, 101, 0)));   
            
         }
         
      });
      
      gameMethodBtn = new JButton(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임방법.png").getImage().getScaledInstance(313, 101, 0)));
      gameMethodBtn.setBounds(445,696,313, 101);
      gameMethodBtn.setBorderPainted(false);
      gameMethodBtn.setFocusPainted(false);
      mainFrame.add(gameMethodBtn);
      gameMethodBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            JOptionPane.showMessageDialog(background, "탈것장난감은 상자에!\n도구는 뽀로로와 크롱에게!\n과일은 주머니에넣어주세요! ");

         }
      });
      
      gameMethodBtn.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseEntered(MouseEvent e) {         
            gameMethodBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임방법.gif").getImage().getScaledInstance(313, 101, 0)));      
            
         }
         @Override
         public void mouseExited(MouseEvent e) {
            gameMethodBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임방법.png").getImage().getScaledInstance(313, 101, 0)));   
            
            
         }
         
      });

      gamePlayBtn = new JButton(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임시작.png").getImage().getScaledInstance(570, 171, 0)));
      gamePlayBtn.setBounds(307,51,570, 171);
      gamePlayBtn.setBorderPainted(false);
      gamePlayBtn.setFocusPainted(false);
      mainFrame.add(gamePlayBtn);
      gamePlayBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            mainFrame.setVisible(false);
            new CleanRoomPlay();

         }

      });
      
      gamePlayBtn.addMouseListener(new MouseAdapter() {

         @Override
         public void mouseEntered(MouseEvent e) {         
            gamePlayBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임시작.gif").getImage().getScaledInstance(570, 171, 0)));      
            
         }
         @Override
         public void mouseExited(MouseEvent e) {
            gamePlayBtn.setIcon(new ImageIcon(new ImageIcon("CleanRoom/뽀로로/게임시작.png").getImage().getScaledInstance(570, 171, 0)));   
            
         }
         
      });
      
      ImageIcon backNormal = new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뒤로가기.png").getImage().getScaledInstance(100, 40, 0));
      ImageIcon backFocus = new ImageIcon(new ImageIcon("CleanRoom/뽀로로/뒤로가기.gif").getImage().getScaledInstance(100, 40, 0));
      backMainBtn = new JButton(backNormal);
      backMainBtn.setBounds(90,90,100, 40);//좌표 창크기      
      backMainBtn.setBackground(new Color(0,0,0,1));
      backMainBtn.setBorderPainted(false);      
      backMainBtn.setFocusPainted(false);
      backMainBtn.setContentAreaFilled(false);
      mainFrame.add(backMainBtn);
      
      backMainBtn.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {

            int result = JOptionPane.showConfirmDialog(mainFrame, "게임선택메뉴로 돌아갈까요?", "돌아가기",
                  JOptionPane.OK_CANCEL_OPTION);
            if (result == 0) {

               
               PlayMain playMain = new PlayMain();
                playMain.setVisible(true);
               mainFrame.setVisible(false);
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

      mainFrame.setResizable(false);

      mainFrame.setVisible(true);

   }
   
   public void ckeckSound() {
      try {
         AudioInputStream as = AudioSystem.getAudioInputStream(new File("soundfile/ckeckSound.wav"));
         clip1 = AudioSystem.getClip();
         clip1.stop();
         clip1.open(as);
         clip1.start();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
   
   public Cursor getMousePointer() {
	   Toolkit toolkit = Toolkit.getDefaultToolkit();
	   Image image = toolkit.getImage("CleanRoom/뽀로로/커서.png").getScaledInstance(100, 100, 0);
	   Point hotspot = new Point(0, 0);
	   Cursor cursor = toolkit.createCustomCursor(image, hotspot, "커서");
	   return cursor;
	}
}