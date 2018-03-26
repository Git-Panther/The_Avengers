package samMain.play.zoo.clip;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BackgroundClip { // 배경음악 클립 기능
	private Clip bgm; // 배경음악
	private boolean isOn = false; // 이 녀석은 음소거 여부
//	private boolean isContinuous = false; // 배경음 지속 여부
	
	private String bgmLocation; // 브금 로케이션
	
	private static BackgroundClip clip = new BackgroundClip();
	
	private BackgroundClip() {
		if(!isOn())
			on();
		
//		if(!isContinuous())
//			setContinuous(true);
	}
	
	private BackgroundClip(String location) {
		this();
		this.bgmLocation = location;
		setBGM();
	}

	public Clip getBGM() {
		return bgm;
	}
	
	public void setBGM(String location) { // 배경음
		this.bgmLocation = location;
		setBGM();
	}
	
	public void setBGM() { // 배경음 자동 설정
		try {
			AudioInputStream ais = AudioSystem
					.getAudioInputStream(new BufferedInputStream(
							new FileInputStream(bgmLocation)));
		
			bgm = AudioSystem.getClip();
			bgm.open(ais);
			bgm.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                        // TODO Auto-generated method stub
                        if(event.getType() == LineEvent.Type.STOP){
                        	// 이 부분이 없으면 효과음이 메모리에 점점 쌓여서 언젠가 크래시된다
//                        	clip.close();
                        	
                        }
                }
			});
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static BackgroundClip getClip() {
		return clip;
	}

	public static BackgroundClip getClip(String location) {
		clip = new BackgroundClip(location);
		return clip;
	}
	
	public static void setClip(BackgroundClip clip) {
		BackgroundClip.clip = clip;
	}
	
	public void pauseBGM() { // 배경음 일시정지
		if(bgm.isRunning()) {
			bgm.stop();
		}		
	}
	
	public void resumeBGM() { // 배경음 재시작		
		if(!bgm.isRunning()) // 재생중이 아니면
		{
			bgm.start();
			bgm.loop(Clip.LOOP_CONTINUOUSLY);
			// 멈추고 시작할 때마다 루프가 풀리기에 위의 루프를 재생할 때마다 까줘야 한다.
		}
	}

	public boolean isOn() {
		return isOn;
	}

	public void on() {
		this.isOn = true;
	}
	
	public void off() {
		this.isOn = false;
	}

	public String getBgmLocation() {
		return bgmLocation;
	}

	public void setBgmLocation(String bgmLocation) {
		this.bgmLocation = bgmLocation;
	}

//	public boolean isContinuous() {
//		return isContinuous;
//	}
//
//	public void setContinuous(boolean isContinuous) {
//		this.isContinuous = isContinuous;
//	}
}
