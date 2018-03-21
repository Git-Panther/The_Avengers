package project.study;

import java.awt.Image;
import java.util.Map;

public class Study {
	private Map<String, Image> map;
	
	public Study(Map<String, Image> map) { // 반드시 객체를 받아야 한다.
		this.setMap(map);	
	}

	public Map<String, Image> getMap() {
		return map;
	}

	public void setMap(Map<String, Image> map) {
		this.map = map;
	}
}
