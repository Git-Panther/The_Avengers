package project.frame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import project.game.Game;
import project.vo.Area;
import project.vo.SelectableImage;

public class GameFrame extends JFrame implements MouseListener, MouseMotionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2589215222057371650L;

	// 이미지는 레이블 안에 넣고 레이블의 부모는 그때그때 패널로 바꿔준다.
	
	private Game game; // 게임 객체
	
	public GameFrame(Game game) {
		// 화면 생성
		super("샘과 함께");
		setBounds(360, 90, 1200, 900);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		setGame(game);

		Image bgImage = new ImageIcon(game.getBackgroundLocation()).getImage();
		JPanel bgPanel = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 6410213718089658288L;

			@Override
			public void paintComponent(Graphics bg) {
				// TODO Auto-generated method stub
				bg.drawImage(bgImage, 0, 0, 1200, 900, null);
				setOpaque(false);
				super.paintComponent(bg);
				System.out.println("배경 완료");
			}
		};
		
		JButton changeButton = new JButton("게임 바꾸기");
		changeButton.setBounds(725, 700, 200, 100);
		changeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new GameChoiceFrame();
				dispose();
			}
		});
		
		JButton mainButton = new JButton("메인 화면");
		mainButton.setBounds(950, 700, 200, 100);
		mainButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new MainFrame();
				dispose();
			}
		});
		
		JButton checkButton = new JButton("완료");
		checkButton.setBounds(500, 700, 200, 100);
		checkButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane bgPane = new JScrollPane(bgPanel);
		setContentPane(bgPane);
		bgPane.add(mainButton);
		bgPane.add(changeButton);
		bgPane.add(checkButton);
		System.out.println("버튼 추가 완료");

		JPanel gamePanel = game.getGamePanel();
		
		LinkedList<SelectableImage> imageObjects = game.getImages();
		LinkedList<JLabel> imageLabels = new LinkedList<JLabel>();
		Iterator<SelectableImage> itrObjects = imageObjects.iterator();
		while(itrObjects.hasNext()) {
			imageLabels.add(new JLabel() );
			imageLabels.getLast().setIcon(itrObjects.next());
			gamePanel.add(imageLabels.getLast());
		}	
		System.out.println("문제 항목 생성 완료");
		
		LinkedList<Area> questionArea = game.getArea();
		Iterator<Area> itrArea = questionArea.iterator();
		while(itrArea.hasNext()) {
			bgPane.add(itrArea.next());
		}
		System.out.println("공간 확보 완료");
		
		bgPane.add(gamePanel);
		System.out.println("게임 패널 넣기 완료");
		
		setVisible(true);
		System.out.println("창 완료");
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void mouseDragged(MouseEvent e) { // 드래그되었을 때 다시 그린다.
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
