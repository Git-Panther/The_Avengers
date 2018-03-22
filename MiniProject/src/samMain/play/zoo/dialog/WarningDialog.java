package samMain.play.zoo.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import samMain.play.zoo.clip.ClipSet;
import samMain.play.zoo.frame.GameFrame;

public class WarningDialog extends JDialog {
	private static final long serialVersionUID = 541935369638909634L;

	private JPanel messagePanel; // 다이얼로그 메시지 패널
	private JPanel buttonPanel; // 버튼들 패널
	private JLabel messageLabel1; // 메시지 레이블 1
	private JLabel messageLabel2; // 메시지 레이블 2
	
	private JButton positiveButton; // 예 버튼
	private JButton negativeButton; // 아니오 버튼
	
	public static final int DIALOG_WIDTH = 350; // 패널 가로 길이이기도 함
	public static final int DIALOG_HEIGHT = 150;
	public static final int PANEL_HEIGHT = 50;
	// 다이얼로그 크기 및 각 패널 세로 길이
	
	public WarningDialog(JFrame frame, String title, String message) {
		super(frame, title, true); // 모달(자기 끝낼 때까지 딴거 못함)
		setBounds( (GameFrame.MONITOR_WIDTH - DIALOG_WIDTH) / 2 , (GameFrame.MONITOR_HEIGHT - DIALOG_HEIGHT) / 2
				, DIALOG_WIDTH, DIALOG_HEIGHT);
		setResizable(false);
		setLayout(null);
		setUndecorated(true); // x창 포함 타이틀도 다 가려버림(...)
//		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setBounds(0, 15, DIALOG_WIDTH, PANEL_HEIGHT);
        add(messagePanel, BorderLayout.CENTER);
       
        messageLabel1 = new JLabel("지금까지 진행한 정보는 모두 사라집니다.", JLabel.CENTER);		
        messageLabel2 = new JLabel(message, JLabel.CENTER);	
        
        messagePanel.add(messageLabel1);
        messagePanel.add(messageLabel2);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(0, 15 + PANEL_HEIGHT, DIALOG_WIDTH, PANEL_HEIGHT);
        
        negativeButton = new JButton("아니오");
        buttonPanel.add(negativeButton);
    
        positiveButton = new JButton("예");
        buttonPanel.add(positiveButton);
        
        add(buttonPanel, BorderLayout.SOUTH);
        
        addWindowListener(new WindowAdapter() { // 창 닫힐 때 닫아야 함.
            @Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowOpened(e);
				ClipSet.getClips().activateEFS(false);
				// 닫기 때문에 경고음을 발생
			}

			@Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                dispose(); // 다이얼로그 제거
            }
        });
	}

	public JPanel getMessagePanel() {
		return messagePanel;
	}

	public void setMessagePanel(JPanel messagePanel) {
		this.messagePanel = messagePanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public JLabel getMessageLabel1() {
		return messageLabel1;
	}

	public void setMessageLabel1(JLabel messageLabel) {
		this.messageLabel1 = messageLabel;
	}

	public JButton getPositiveButton() {
		return positiveButton;
	}

	public void setPositiveButton(JButton positiveButton) {
		this.positiveButton = positiveButton;
	}

	public JButton getNegativeButton() {
		return negativeButton;
	}

	public void setNegativeButton(JButton negativeButton) {
		this.negativeButton = negativeButton;
	}

	public JLabel getMessageLabel2() {
		return messageLabel2;
	}

	public void setMessageLabel2(JLabel messageLabel2) {
		this.messageLabel2 = messageLabel2;
	}
}
