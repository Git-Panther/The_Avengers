package samMain.play.zoo.clip;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ZooBackGroundClip { // 배경음악 클립 기능
	private Clip bgm; // 배경음악
	private boolean isOn = false; // 이 녀석은 음소거 여부	
	private String bgmLocation; // 브금 로케이션
	
	private static ZooBackGroundClip clip = new ZooBackGroundClip(); // 반환 대상
	
	private ZooBackGroundClip() {
		if(!isOn())
			on();
	}
	
	private ZooBackGroundClip(String location) {
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
			bgm = AudioSystem.getClip();
			bgm.open(AudioSystem
					.getAudioInputStream(new BufferedInputStream(
							new FileInputStream(bgmLocation))));
			bgm.addLineListener(new LineListener() {
                @Override
                public void update(LineEvent event) {
                        // TODO Auto-generated method stub
                        if(event.getType() == LineEvent.Type.CLOSE){
                        	// 닫혔을 때 경로를 초기화해줌으로써 다음에 새롭게 브금을 실행할 수 있게 한다.
                        	bgm.close();
                        	on(); // 브금 꺼진 상태도 초기화(다시 켜야 하므로)
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

	public static ZooBackGroundClip getClip() {
		return clip;
	}

	public static ZooBackGroundClip getClip(String location) {
		clip = new ZooBackGroundClip(location);
		return clip;
	}
	
	public static void setClip(ZooBackGroundClip clip) {
		ZooBackGroundClip.clip = clip;
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
}