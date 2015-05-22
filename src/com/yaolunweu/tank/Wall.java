package com.yaolunweu.tank;
import java.awt.*;
/**
 * ǽ�࣬�ӵ����ܴ�����
 * @author С��
 *
 */
public class Wall {
	/**
	 * ������ǽ��λ�ú͸߶ȺͿ�ȡ�
	 */
	int x, y, w, h;
	TankWar tw;
	/**
	 * ���캯��: �Ի�ǽ��һϵ�в������á�
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param tw
	 */
	public Wall(int x, int y, int w, int h, TankWar tw) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.tw = tw;
	}
	/**
	 * ��ǽ��
	 * @param g
	 */
	public void drawWall(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.gray);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}
	/**
	 * ǽ����λ�ã���ռ������������ײ��⡣
	 * @return
	 */
	public Rectangle getRec(){
		return new Rectangle(x, y, w, h);
		
	}


	
	
}
