package project.manager;

import java.awt.Image;
import java.util.Map;

import project.study.Study;

public class StudyManager {
	private Study study;
	
	public StudyManager(Map<String, Image> map) {
		study = new Study(map);
		// 고른 종목에 맞게 공부 생성
	}
}
