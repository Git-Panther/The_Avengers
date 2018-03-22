package project.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import project.clip.ClipSet;
import project.frame.GameFrame;

public class ResultDialog extends JDialog { // 결과창에 대한 다이얼로그
	private static final long serialVersionUID = 7488391241677890730L;
	
	private JPanel messagePanel; // 다이얼로그 전체 패널
	private JPanel buttonPanel; // 버튼들 패널
	private JLabel messageLabel1; // 메시지 레이블 1
	private JLabel messageLabel2; // 메시지 레이블 2
	
	private boolean isCorrect; // 정답 여부
	
	public static final int DIALOG_WIDTH = 350;
	public static final int DIALOG_HEIGHT = 150;
	
	public ResultDialog(JFrame frame, String title) 
	{
		super(frame, title, true); // 모달(자기 끝낼 때까지 딴거 못함)
		setBounds( (GameFrame.MONITOR_WIDTH - DIALOG_WIDTH) / 2 , (GameFrame.MONITOR_HEIGHT - DIALOG_HEIGHT) / 2
				, DIALOG_WIDTH, DIALOG_HEIGHT);
		setResizable(false);
		setLayout(null);
//		setUndecorated(true); // x창 포함 타이틀도 다 가려버림(...)
		// 바로 위 코드는 이미지 입힐 거 아니면 쓰지 말자..
//		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		
		messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setBounds(0, 15, 350, 50);
        add(messagePanel, BorderLayout.CENTER);
       
        messageLabel1 = new JLabel("", JLabel.CENTER);		
        messageLabel2 = new JLabel("", JLabel.CENTER);	
        
        messagePanel.add(messageLabel1);
        messagePanel.add(messageLabel2);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds(0, 65, 350, 50);
        add(buttonPanel, BorderLayout.SOUTH);
        
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

	public JPanel getMessagePanel() {
		return messagePanel;
	}

	public JPanel getButtonPanel() {
		return buttonPanel;
	}

	public JLabel getMessageLabel1() {
		return messageLabel1;
	}

	public JLabel getMessageLabel2() {
		return messageLabel2;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}
}
