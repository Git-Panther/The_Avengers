package samMain.play.zoo.dialog;

import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import samMain.play.zoo.Adapter.ZooMouseAdapter;
import samMain.play.zoo.button.ZooButton;

public class WarningDialog extends ZooDialog { // 예, 아니오 다이얼로그
	private static final long serialVersionUID = 541935369638909634L;
	
	private ZooButton positiveButton; // 예 버튼
	private ZooButton negativeButton; // 아니오 버튼
	
	public WarningDialog(JFrame frame, JFrame nextFrame) {
		this(frame, nextFrame, "", "");
	}
	
	public WarningDialog(JFrame frame, JFrame nextFrame, String title, String message) {
		super(frame, title); // 모달(자기 끝낼 때까지 딴거 못함)
		setTitle(title);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		messageLabel1.setText("지금까지 진행한 정보는 사라집니다.");
		messageLabel2.setText(message);
		
        negativeButton = new ZooButton("아니오");
        buttonPanel.add(negativeButton);
    
        positiveButton = new ZooButton("예");
        buttonPanel.add(positiveButton);
        
        negativeButton.addMouseListener(new ZooMouseAdapter(negativeButton) {
        	@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub	
        		negativeButton.setIcon(ZooButton.DEFAULT);
        		negativeButton.getParent().repaint();
				if(negativeButton.isEntered() && negativeButton.isPressed()) {
					negativeButton.setEntered(false);
					nextFrame.dispose();
					dispose();
				}
				negativeButton.setPressed(false);
			}
        });
        
        positiveButton.addMouseListener(new ZooMouseAdapter(positiveButton) {
        	@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
        		positiveButton.setIcon(ZooButton.DEFAULT);
        		positiveButton.getParent().repaint();
				if(positiveButton.isEntered() && positiveButton.isPressed()) {
					positiveButton.setEntered(false);
					dispose();
					nextFrame.setVisible(true);				
					frame.dispose();
				}
				positiveButton.setPressed(false);
        	}
        });
        
        addWindowListener(new WindowAdapter() { // 창 닫힐 때 닫아야 함.
            @Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				// 상위 버튼에서 효과음 이미 있음
			}

			@Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); // 다이얼로그 제거
            }
        });
	}

	public ZooButton getPositiveButton() {
		return positiveButton;
	}

	public void setPositiveButton(ZooButton positiveButton) {
		this.positiveButton = positiveButton;
	}

	public ZooButton getNegativeButton() {
		return negativeButton;
	}

	public void setNegativeButton(ZooButton negativeButton) {
		this.negativeButton = negativeButton;
	}
}
