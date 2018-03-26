package samMain.play.zoo.Adapter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import samMain.play.zoo.button.ZooButton;

public class ZooMouseAdapter extends MouseAdapter {

	private ZooButton parentButton;
	
	public ZooMouseAdapter(ZooButton parentButton) {
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

	public ZooButton getParentButton() {
		return parentButton;
	}

	public void setParentButton(ZooButton parentButton) {
		this.parentButton = parentButton;
	}

}
