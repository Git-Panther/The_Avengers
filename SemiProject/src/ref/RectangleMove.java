package ref;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class RectangleMove extends JComponent implements MouseListener, MouseMotionListener
{
	// 참고용 클래스
	/**
	 * 
	 */
	private static final long serialVersionUID = -4390852011789460552L;

	//사각형영역
	Rectangle box;

	//마우스 드래그 체크
	boolean isDragged;

	//마우스 오프셋좌표
	int offX, offY;

	public RectangleMove(){

		//사각형영역 정의(100x80 size)
		box = new Rectangle(0,0,100,80);

		//현재 드래그 상태 저장
		isDragged = false;

		//마우스 리스너 등록
		addMouseListener(this);
		addMouseMotionListener(this);

	}

	//컴포넌트 페인팅
	public void paintComponent(Graphics g){
		
		//사각형 그릴 색상 설정
		g.setColor(Color.red);

		//사각형 그림
		g.drawRect(box.x,box.y,box.width,box.height);

		//사각형을 이동하기 위하여 사각형의 x,y 좌표와 사각형 내 클릭한 마우스의 좌표가 필요하다

	}

 	public void mousePressed(MouseEvent me){
		//사각형 안에서만 이벤트 작업 설정
		if(box.contains(new Point(me.getX(),me.getY()))){
			//#1 마우스 버튼 누름
			//사각형내 마우스 클릭 상대 좌표를 구함
			//현재 마우스 스크린 좌표에서 사각형 위치 좌표의 차이를 구함
			offX = me.getX() - box.x;
			offY = me.getY() - box.y;
			
			//드래그 시작을 표시
			isDragged = true;

		}
	}
	public void mouseReleased(MouseEvent me){
		//마우스 버튼이 릴리즈되면 드래그 모드 종료
		isDragged = false;
	}
	public void mouseDragged(MouseEvent me){
		
		//드래그 모드인 경우에만 사각형 이동시킴
		if(isDragged){
			box.x = me.getX() - offX;
			box.y = me.getY() - offY;
		}
		repaint();
	
	}
	public void mouseMoved(MouseEvent me){}
	public void mouseClicked(MouseEvent me){}
	public void mouseEntered(MouseEvent me){}
	public void mouseExited(MouseEvent me){}

	//프로그램 실행
	public static void main(String[] args){

		JFrame f = new JFrame("http://sexy.pe.kr");
		f.setBounds(0,0,300,300);
		f.add(new RectangleMove());
		f.setVisible(true);
	}
}
