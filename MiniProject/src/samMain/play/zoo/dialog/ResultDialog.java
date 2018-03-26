package samMain.play.zoo.dialog;

import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import samMain.main.SamMain;
import samMain.play.PlayMain;
import samMain.play.zoo.Listener.ZooMouseListener;
import samMain.play.zoo.button.ZooButton;
import samMain.play.zoo.clip.ClipSet;

public class ResultDialog extends ZooDialog { // 결과창에 대한 다이얼로그
	private static final long serialVersionUID = 7488391241677890730L;
	
	private boolean isCorrect; // 정답 여부
	
	public ResultDialog(JFrame frame, String title) 
	{
		super(frame, title); // 모달(자기 끝낼 때까지 딴거 못함)
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() { // 창 닫힐 때 닫아야 함.
            @Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				ClipSet.getClips().activateEFS(isCorrect);
			}

			@Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); // 다이얼로그 제거
            }
        });
	}

	public void buildZooDialog() // 기본적인 커스텀 다이얼로그(다 비웠는데 오답, 정답일 때) 생성
	{
		ZooButton choiceButton = new ZooButton("다른 게임");
		getButtonPanel().add(choiceButton);
		choiceButton.addMouseListener(new ZooMouseListener(choiceButton) {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				choiceButton.setIcon(ZooButton.DEFAULT);
				choiceButton.getParent().repaint();
				if(choiceButton.isEntered() && choiceButton.isPressed()) {
					choiceButton.setEntered(false);
					new PlayMain().setVisible(true);
					dispose(); // 다이얼로그 종료
					getParent().dispose();
				}
				choiceButton.setPressed(false);
			}
		});
		
		ZooButton mainButton = new ZooButton("메인 화면");
		getButtonPanel().add(mainButton);
		mainButton.addMouseListener(new ZooMouseListener(mainButton){
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				mainButton.setIcon(ZooButton.DEFAULT);
				mainButton.getParent().repaint();
				if(mainButton.isEntered() && mainButton.isPressed()) {
					mainButton.setEntered(false);
					dispose(); // 다이얼로그 종료
					new SamMain();
					getParent().dispose();
				}
				repaint();
				mainButton.setPressed(false);
			}
		});
	}
	
	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
