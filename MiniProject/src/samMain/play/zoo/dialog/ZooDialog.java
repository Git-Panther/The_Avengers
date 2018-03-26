package samMain.play.zoo.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import samMain.play.zoo.cursor.ZooCursor;
import samMain.play.zoo.frame.GameFrame;

public class ZooDialog extends JDialog {

	private static final long serialVersionUID = 8796110747470698087L;
	
	public static final int DIALOG_WIDTH = 350;
	public static final int DIALOG_HEIGHT = 150;
	public static final int MESSAGE_PANEL_WIDTH = 330;
	public static final int MESSAGE_PANEL_HEIGHT = 100;
	public static final int BUTTON_PANEL_WIDTH = 300;
	public static final int BUTTON_PANEL_HEIGHT = 45;
	// 다이얼로그 크기 및 각 패널 세로 길이
	
	protected JPanel messagePanel; // 다이얼로그 메시지 패널
	protected JPanel buttonPanel; // 버튼들 패널
	protected JLabel messageLabel1; // 메시지 레이블 1
	protected JLabel messageLabel2; // 메시지 레이블 2
	
	protected JFrame parent; // 부모창

	public ZooDialog(JFrame parent, String title) {
		super(parent, title, true); // 모달(자기 끝낼 때까지 딴거 못함)
		this.parent = parent; // 부모창
		setBounds( (GameFrame.MONITOR_WIDTH - DIALOG_WIDTH) / 2 , (GameFrame.MONITOR_HEIGHT - DIALOG_HEIGHT) / 2
				, DIALOG_WIDTH, DIALOG_HEIGHT);
		setResizable(false);
		setLayout(null);
		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 1));
		
		setCursor(ZooCursor.getCursor());
		
		ImageIcon bg = new ImageIcon(new ImageIcon("resource/image/zoo/dialog/zoo_dialog.png").getImage().getScaledInstance(DIALOG_WIDTH, DIALOG_HEIGHT, 0));
	    JLabel bgLabel = new JLabel(bg);
	    bgLabel.setBounds(0, 0, DIALOG_WIDTH, DIALOG_HEIGHT);
	    setContentPane(bgLabel);
		
		messagePanel = new JPanel();
        messagePanel.setLayout(new FlowLayout());
        messagePanel.setBounds( (DIALOG_WIDTH - MESSAGE_PANEL_WIDTH) / 2 , 15, MESSAGE_PANEL_WIDTH, MESSAGE_PANEL_HEIGHT);
        messagePanel.setBackground(new Color(0, 0, 0, 1));
        getContentPane().add(messagePanel, BorderLayout.CENTER);
       
        messageLabel1 = new JLabel("", JLabel.CENTER);	
//        messageLabel1.setForeground(new Color(57, 198, 147));
        messageLabel1.setForeground(new Color(255, 246, 229));
        messageLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        
        messageLabel2 = new JLabel("", JLabel.CENTER);	
//        messageLabel2.setForeground(new Color(57, 198, 147));
        messageLabel2.setForeground(new Color(255, 246, 229));
        messageLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        
        messagePanel.add(messageLabel1);
        messagePanel.add(messageLabel2);
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBounds( (DIALOG_WIDTH - BUTTON_PANEL_WIDTH) / 2 , MESSAGE_PANEL_HEIGHT - 10, BUTTON_PANEL_WIDTH, BUTTON_PANEL_HEIGHT);
        buttonPanel.setBackground(new Color(0, 0, 0, 1));
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setMessagePanel(JPanel messagePanel) {
		this.messagePanel = messagePanel;
	}

	public void setButtonPanel(JPanel buttonPanel) {
		this.buttonPanel = buttonPanel;
	}

	public void setMessageLabel1(JLabel messageLabel1) {
		this.messageLabel1 = messageLabel1;
	}

	public void setMessageLabel2(JLabel messageLabel2) {
		this.messageLabel2 = messageLabel2;
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

	public JFrame getParent() {
		return parent;
	}

	public void setParent(JFrame parent) {
		this.parent = parent;
	}
}
