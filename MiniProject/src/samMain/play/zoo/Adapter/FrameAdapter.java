package samMain.play.zoo.Adapter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import samMain.play.zoo.clip.BackgroundClip;

public class FrameAdapter extends WindowAdapter {
	private BackgroundClip bgm;
	
	public FrameAdapter(BackgroundClip bgm) {
		this.bgm = bgm;
	}
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		bgm.resumeBGM();
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		bgm.pauseBGM();
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
//		if(bgm.isOn())
		bgm.resumeBGM();
	}
	
	@Override
	public void windowClosing(WindowEvent e) { // 닫히고 있을 때. closing -> 다른 창 opening -> closed이니 주의.
		// TODO Auto-generated method stub
		bgm.pauseBGM();
	}
	
//	@Override
//	public void windowClosed(WindowEvent e) {
//		// TODO Auto-generated method stub
//		bgm.pauseBGM();
//		clip.setContinuous(false);
//		bgm.getBGM().close();
//	}
	
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
//		if(bgm.isOn())
		bgm.resumeBGM();
	}

	public BackgroundClip getClip() {
		return bgm;
	}

	public void setClip(BackgroundClip clip) {
		this.bgm = clip;
	}
}
