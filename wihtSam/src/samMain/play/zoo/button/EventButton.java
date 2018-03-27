package samMain.play.zoo.button;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import samMain.play.zoo.clip.EffectClip;

public class EventButton extends JButton { // 이벤트 버튼. JLabel에 주의
	private static final long serialVersionUID = -6067398026930471826L;

	public static final int BUTTON_WIDTH = 180;
	public static final int BUTTON_HEIGHT = 114;
	// 버튼 표준 사이즈
	
	private String normalLocation; // 평상시의 모습
	private String focusLocation; // 마우스를 올려놨을 때 모습
	private JFrame parent; // 해당 버튼의 부모
	private JDialog dialog; // 띄울 다이얼로그
	
	private ImageIcon normal; // 노멀 이미지아이콘
	private ImageIcon focus; // 마우스 포인터가 올라왔을 때
	
	private boolean isEntered; // 마우스 포인터가 안에 들어왔는지에 대한 여부
	
	public EventButton() {
		super();
		// TODO Auto-generated constructor stub
		setFocusPainted(false);
		setBorderPainted(false);
		setBackground(new Color(0, 0, 0, 1));
	}
	public EventButton(JFrame parent) {
		this();
		// TODO Auto-generated constructor stub
		this.parent = parent;
	}
	public EventButton(JFrame parent, String normal, String focus, String press) {
		this();
		// TODO Auto-generated constructor stub
		this.parent = parent;
		this.normalLocation = normal;
		this.focusLocation = focus;
	}
	
	public void setOneSide(Rectangle rect) { // 원사이드하게 하려면.
		setBounds(rect);
		setButtonImages(rect.width, rect.height);
		addDefaultEventListener();
	}
	
	public void setOneSide(int x, int y, int width, int height) { // 원사이드하게 하려면. 나가기 버튼 전용
		setOneSide(new Rectangle(x, y, width, height));
	}
	
	public void setButtonImages() { // 주입한 이미지들을 반영. 사이즈 자동 용도
		setButtonImages(BUTTON_WIDTH, BUTTON_HEIGHT);
	}
	
	public void setButtonImages(int width, int height) { // 주입한 이미지들을 반영. 사이즈 직접 반영
		setNormal(new ImageIcon(new ImageIcon(normalLocation).getImage().getScaledInstance(width, height, 0)));
		setFocus(new ImageIcon(new ImageIcon(focusLocation).getImage().getScaledInstance(width, height, 0)));
		setIcon(normal);
		parent.repaint();
	}
	
	public void addDefaultEventListener() { // 리스너 자동으로 입히려고. 다른 페이지로 이동하고자 할 때(기본적인 용도)
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				setIcon(normal);
				parent.repaint();
				if(isEntered()) {
					EffectClip.getClips().activateEFS(false);
					dialog.setVisible(true);
					setEntered(false);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				setIcon(normal);
				parent.repaint();
				setEntered(false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				setIcon(focus);
				parent.repaint();
				setEntered(true);
			}
		});
	}
	
	public String getNormalLocation() {
		return normalLocation;
	}
	public void setNormalLocation(String normalLocation) {
		this.normalLocation = normalLocation;
	}
	public String getFocusLocation() {
		return focusLocation;
	}
	public void setFocusLocation(String focusLocation) {
		this.focusLocation = focusLocation;
	}
	public JFrame getParent() {
		return parent;
	}
	public void setParent(JFrame parent) {
		this.parent = parent;
	}
	public boolean isEntered() {
		return isEntered;
	}
	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}
	public ImageIcon getNormal() {
		return normal;
	}
	public void setNormal(ImageIcon normal) {
		this.normal = normal;
	}
	public ImageIcon getFocus() {
		return focus;
	}
	public void setFocus(ImageIcon focus) {
		this.focus = focus;
	}
	public JDialog getDialog() {
		return dialog;
	}
	public void setDialog(JDialog dialog) {
		this.dialog = dialog;
	}
	@Override
	public boolean contains(Point point) { // 범위 안에 있는지 검사
		// TODO Auto-generated method stub
		if(containsX(point.x) && containsY(point.y))
			return true;
		else
			return false;
	}
	public boolean containsX(int x) { // x 좌표가 범위 안인지 검사. 버튼 사이로도 못 오게 상한선 없앰.
		if(getLocation().x <= x)
			return true;
		else
			return false;
	}
	public boolean containsY(int y) { // 주의할 점은 마우스가 화면 밖으로 튀면 못 나오니 하한선만 정한다.
		if(getLocation().y <= y)
			return true;
		else
			return false;
	}
}
