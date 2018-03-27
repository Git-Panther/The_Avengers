package samMain.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import samMain.Adapter.FrameAdapter;
import samMain.clip.BackgroundClip;

public class SamFrame extends JFrame {

	private static final long serialVersionUID = 2592766512700603366L;
	
	public static final int FRAME_X = 350;
	public static final int FRAME_Y = 80;
	public static final int FRAME_WIDTH = 1210;
	public static final int FRAME_HEIGHT = 930;
	
	private static BackgroundClip bgm = BackgroundClip.getClip("유민.wav");
	
	public SamFrame() {
		setBounds(FRAME_X, FRAME_Y, FRAME_WIDTH, FRAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);	
		setTitle("샘과 함께");
		setIconImage(new ImageIcon("images/game3FrameIcon.png").getImage());
		addWindowListener(new FrameAdapter(bgm));
		if(!bgm.getBGM().isRunning()) {
			bgm.setBGM("유민.wav");
			bgm.resumeBGM();
		}
	}
	
	public JFrame getFrame() {
		return this;
	}

	public static BackgroundClip getBgm() {
		return SamFrame.bgm;
	}

	public static void setBgm(BackgroundClip bgm) {
		SamFrame.bgm = bgm;
	}
}
