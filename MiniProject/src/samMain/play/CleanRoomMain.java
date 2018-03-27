package samMain.play;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class CleanRoomMain {

	public CleanRoomMain(){

		JFrame startText = new JFrame("정리정돈");
		startText.setBounds(350, 80, 1210, 940);
		startText.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		startText.setLayout(null);
		startText.setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());

		JButton gameinfoBtn = new JButton("게임소개");
		gameinfoBtn.setLocation(95, 50);
		gameinfoBtn.setSize(100, 50);
		startText.add(gameinfoBtn);

		gameinfoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(startText, "뽀로로와 정리정돈!!\n방이 너무 더러워요!!\n 정리해주세요!!! ");

			}
		});

		JButton gameMethodBtn = new JButton("게임방법");
		gameMethodBtn.setLocation(95, 200);
		gameMethodBtn.setSize(100, 50);
		startText.add(gameMethodBtn);
		gameMethodBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(startText, "탈것장난감은 상자에!\n도구는 뽀로로와 크롱에게!\n과일은 주머니에! ");

			}
		});

		JButton gamePlayBtn = new JButton("게임시작!!");
		gamePlayBtn.setLocation(95, 350);
		gamePlayBtn.setSize(100, 50);
		startText.add(gamePlayBtn);
		gamePlayBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				startText.setVisible(false);

				new CleanRoomPlay();

			}

		});

		startText.setResizable(false);

		startText.setVisible(true);

	}

}
