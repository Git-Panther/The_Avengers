package samMain.play.zoo.button;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import samMain.play.zoo.dialog.ZooDialog;

public class ZooButton extends JLabel { // ZooDialog의 버튼

	private static final long serialVersionUID = 2789439432228477917L;
	
	public static final int BUTTON_WIDTH = 90;
	public static final int BUTTON_HEIGHT = ZooDialog.BUTTON_PANEL_HEIGHT;
	
	public static final ImageIcon DEFAULT = new ImageIcon(new ImageIcon("resource/image/zoo/button/zoo_button.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0));
	public static final ImageIcon FOCUS = new ImageIcon(new ImageIcon("resource/image/zoo/button/zoo_button_focus.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0));
	public static final ImageIcon PRESS = new ImageIcon(new ImageIcon("resource/image/zoo/button/zoo_button_press.png").getImage().getScaledInstance(BUTTON_WIDTH, BUTTON_HEIGHT, 0));
	// 각 이미지별로 상태 표시
	
	private boolean isEntered; // 안에 들어왔는지에 대한 여부
	private boolean isPressed; // 눌렀는지에 대한 여부
	
	public ZooButton(String message) {
		super();
		setBackground(new Color(0, 0, 0, 1));
		setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
		setForeground(new Color(30, 50, 105));
		setFont(new Font("맑은 고딕", Font.BOLD, 18));
		setText(message);
		setHorizontalTextPosition(SwingConstants.CENTER);
		setVerticalTextPosition(SwingConstants.CENTER);
		setIcon(DEFAULT);
		setEntered(false);
	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}

	public boolean isPressed() {
		return isPressed;
	}

	public void setPressed(boolean isPressed) {
		this.isPressed = isPressed;
	}
}
