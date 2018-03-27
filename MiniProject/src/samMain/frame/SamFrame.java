package samMain.frame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import samMain.play.zoo.Adapter.FrameAdapter;
import samMain.play.zoo.clip.BackgroundClip;

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
				
		if(bgm.getBGM() == null || !bgm.getBgmLocation().equals("유민.wav")) { // 없다면 새로 만들어줌
			bgm.setBGM("유민.wav");
			bgm.resumeBGM();
		}
		addWindowListener(new FrameAdapter(SamFrame.bgm));
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
