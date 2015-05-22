package com.yaolunweu.tank;
import java.awt.*;
/**
 * ���̹�˵�����ֵ�ࡣ
 * @author xiaoyao
 *
 */
public class Live {
	
	private static final int WIDTH = 100;
	private static final int HEIGTH = 5;
	/**
	 * live����ʱ̹�˵�����ֵ���ڹ��캯���г�ʼ����
	 */
	int live; 
	/**
	 * ��ս̹�˵����á�
	 * ��Tank���еĳ�Ա�����ͷ����ķ��ʡ�
	 */
	Tank t;
	/**
	 * ���������������ã���TankWar���е����Ժͷ����ķ��ʡ�
	 */
	TankWar tw;
	/**
	 * ���캯����
	 * ��ʼ������ֵ	
	 * @param live
	 * @param t 	��Tank������ò�����
	 * @param tw
	 */
	public Live(int live, Tank t, TankWar tw) {
		this.live = live;
		this.t = t;
		this.tw = tw;
	}
	/**
	 * ����Ѫ��������ֵ��
	 * @param g  �õ����ʡ�
	 */
	public void drawLive(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.red);
		if(t.isLive()){
			g.drawRect(t.x-20, t.y-20, WIDTH, HEIGTH);
			if(live < 40){
				g.setColor(Color.magenta);
				g.drawString(" �����ˣ���Q�����䳬���ӵ���",t.x-50, t.y-30);
			}
			else g.setColor(Color.red);
			g.fillRect(t.x-20, t.y-20, live, HEIGTH);
		} 
		else return;
		g.setColor(c);
		
	}
}
