package samMain.play.zoo.clip;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Line;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EffectClip { // 긍, 부정 효과음
	private String positiveAudio = "resource/sound/efs/positive.wav";
	private String negativeAudio = "resource/sound/efs/negative.wav";
	
	private static EffectClip clips = new EffectClip();

	private EffectClip() {
		
	}
	
	public void activateEFS(boolean bool) {
		String location = null;
		
		if(bool) {
			location = positiveAudio;
		} else {
			location = negativeAudio;
		}
		
		try {
			final Clip clip = (Clip)AudioSystem.getLine(new Line.Info(Clip.class));
			clip.open(AudioSystem.getAudioInputStream(new File(location)));
			clip.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                        // TODO Auto-generated method stub
                        if(event.getType() == LineEvent.Type.STOP){
                                //이 부분이 없으면 효과음이 메모리에 점점 쌓여서 언젠가 크래시된다
                        	clip.close();
                        }
                }
			});
			clip.start();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static EffectClip getClips() {
		return clips;
	}

	public static void setClips(EffectClip clips) {
		EffectClip.clips = clips;
	}
	
}
