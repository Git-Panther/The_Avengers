package samMain.play.zoo.cursor;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

public class ZooCursor{	
	private static Cursor cursor = null;
	
	private ZooCursor() {
		
	}
	
	public static Cursor getCursor() {
		if(cursor == null) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			Image image = toolkit.getImage("resource/image/object/zoo_cursor.png");//.getScaledInstance(100, 150, 0);
			Point hotspot = new Point(0, 0);
			cursor = toolkit.createCustomCursor(image, hotspot, "zoo_cursor");
		}	
		return cursor;
	}	
}
