package samMain.play.zoo.button;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import samMain.play.zoo.clip.BackgroundClip;

public class BGMButton extends JLabel{ // 배경음악 음소거 버튼

	/**
	 * 
	 */
	private static final long serialVersionUID = 5362464637650337006L;
	
	private static final String BGM_ON = "resource/image/object/on.png";
	private static final String BGM_ON_FOCUS = "resource/image/object/on_focus.png";
	private static final String BGM_OFF = "resource/image/object/off.png";
	private static final String BGM_OFF_FOCUS = "resource/image/object/off_focus.png";
	
	private ImageIcon onIcon; // 켜졌을 때
	private ImageIcon onFocusIcon; // 켜진 상태에서의 포커스
	private ImageIcon offIcon; // 꺼졌을 때
	private ImageIcon offFocusIcon;
	
	private boolean isEntered; // 들어와 있고 놓았을 때만 가능하도록

	private BackgroundClip clip = BackgroundClip.getClip();

	public BGMButton() {
		super();
	}
	
	public BGMButton(Rectangle rect) {
		this(rect.x, rect.y, rect.width, rect.height);
	}
	
	public BGMButton(int x, int y, int width, int height) {
		this();
		setBounds(x, y, width, height);
		setOnIcon(new ImageIcon(new ImageIcon(BGM_ON).getImage().getScaledInstance(width, height, 0)));
		setOnFocusIcon(new ImageIcon(new ImageIcon(BGM_ON_FOCUS).getImage().getScaledInstance(width, height, 0)));
		setOffIcon(new ImageIcon(new ImageIcon(BGM_OFF).getImage().getScaledInstance(width, height, 0)));
		setOffFocusIcon(new ImageIcon(new ImageIcon(BGM_OFF_FOCUS).getImage().getScaledInstance(width, height, 0)));
		setIcon(onIcon);
	}
	
	public void addDefaultEventListener() {
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if(isEntered) {
					if(clip.isOn()) {
						clip.off();
						setIcon(offFocusIcon);
						getParent().repaint();
						clip.pauseBGM();
					} else{
						clip.on();
						setIcon(onFocusIcon);
						getParent().repaint();
						clip.resumeBGM();
					}
				}
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				isEntered = false;
				if(clip.isOn()) {
					setIcon(onIcon);
					repaint();
					getParent().repaint();
				} else{
					setIcon(offIcon);
					repaint();
					getParent().repaint();
				}
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				isEntered = true;
				if(clip.isOn()) {
					setIcon(onFocusIcon);
					repaint();
					getParent().repaint();
				} else{
					setIcon(offFocusIcon);
					repaint();
					getParent().repaint();
				}
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	public BackgroundClip getClip() {
		return clip;
	}

	public void setClip(BackgroundClip clip) {
		this.clip = clip;
	}

	public ImageIcon getOnIcon() {
		return onIcon;
	}

	public void setOnIcon(ImageIcon onIcon) {
		this.onIcon = onIcon;
	}

	public ImageIcon getOffIcon() {
		return offIcon;
	}

	public void setOffIcon(ImageIcon offIcon) {
		this.offIcon = offIcon;
	}

	public boolean isEntered() {
		return isEntered;
	}

	public void setEntered(boolean isEntered) {
		this.isEntered = isEntered;
	}

	public ImageIcon getOnFocusIcon() {
		return onFocusIcon;
	}

	public void setOnFocusIcon(ImageIcon onFocusIcon) {
		this.onFocusIcon = onFocusIcon;
	}

	public ImageIcon getOffFocusIcon() {
		return offFocusIcon;
	}

	public void setOffFocusIcon(ImageIcon offFocusIcon) {
		this.offFocusIcon = offFocusIcon;
	}
}
