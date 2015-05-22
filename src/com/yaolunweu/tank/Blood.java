package com.yaolunweu.tank;
import java.awt.*;
import java.util.Random;

/**
 * ����Ѫ���࣬����Ľ���
 * Ѫ����ʾʱ�䲻�ܺܺõĿ��ơ�
 * @author xiaoyao
 *
 */
public class Blood {
	/**
	 * ���������
	 */
	private static Random r = new Random();
	int tmp;
	private int x ;		// Ѫ���ڴ�������ʾ��λ��
	private int y ;	
	private final int w = 20, h = 20; 	//Ѫ��ĸߺͿ�
	TankWar tw; 	//�����ڵ����á�
	private boolean live = false; //Ѫ�������ֵ��
	/**
	 *BloodѪ�鹹�캯���� 
	 * @param tw
	 */
	Blood(TankWar tw) {
		this.tw = tw;
	}
	/**
	 * ��Ѫ�鷽����
	 * @param g
	 */
	public void drawBlood(Graphics g){
		tmp = r.nextInt(100); 
		if(this.getLive()){
			if(tmp > 6)
				this.setLive(false);
			return ;
		}
		if(!this.getLive()){
			if(tmp > 98){
				this.setLive(true);
				xy();
				Color c = g.getColor();
				g.setColor(Color.RED);
				g.fillRect(x, y, w, h);
				g.setColor(c);
			}
			return ;
		}
	}

	public boolean getLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	/**
	 * BloodѪ�飺
	 * Rectangle ָ������ռ��е�һ������ͨ������ռ��� Rectangle �������Ϸ��ĵ� (x,y)����Ⱥ͸߶ȿ��Զ����������
	 * @return
	 */
	public Rectangle getRec(){
		return new Rectangle(x,y,w,h);
		
	}
	/**
	 * x��ֵ�����ȡ�����㣬С�������ڵĿ�ȣ�
	 * y��ֵ�����ȡ�����㣬С�������ڵĸ߶ȡ�
	 */
	public void xy(){
		this.x = r.nextInt(TankWar.GAME_WIDTH);
		this.y = r.nextInt(TankWar.GAME_HEIGHT);
	}
	


}
