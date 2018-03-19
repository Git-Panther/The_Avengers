package project.game;

import java.awt.Image;
import java.util.Map;
import java.util.TreeMap;

public abstract class Game {
	protected String genre; // 장르
	protected Map<String, Image> map = new TreeMap<String, Image>();
	
	public Map<String, Image> getList() {
		return map;
	}
	
	public abstract void start();
	public abstract void reset();
	public abstract boolean check();
}
