package samMain.play.zoo.Listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import samMain.play.zoo.button.ZooButton;

public class ZooMouseListener implements MouseListener {

	private ZooButton parentButton;
	
	public ZooMouseListener(ZooButton parentButton) {
		// TODO Auto-generated constructor stub
		this.setParentButton(parentButton);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		parentButton.setIcon(ZooButton.PRESS);
		parentButton.getParent().repaint();
		parentButton.setPressed(true);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		parentButton.setIcon(ZooButton.DEFAULT);
		parentButton.getParent().repaint();
		parentButton.setEntered(false);
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(parentButton.isPressed()) {
			parentButton.setIcon(ZooButton.PRESS);
		} else {
			parentButton.setIcon(ZooButton.FOCUS);
		}
		parentButton.getParent().repaint();
		parentButton.setEntered(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public ZooButton getParentButton() {
		return parentButton;
	}

	public void setParentButton(ZooButton parentButton) {
		this.parentButton = parentButton;
	}

}
