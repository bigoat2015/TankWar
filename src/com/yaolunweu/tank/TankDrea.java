package com.yaolunweu.tank;
import java.awt.*;
import java.awt.Toolkit;
/**
 * ̹�˱�ը�ࡣ
 * @author С��
 *
 */
public class TankDrea{
	/**
	 * ��ըλ�á�
	 */
	int x , y ;
	/**
	 * ��ըͼƬ�Ƿ���ڡ�
	 */
	boolean live = true;
	/**
	 * ����ըͼƬ���衣
	 */
	int step = 0;
 	TankWar tw;
 	private static boolean init = false;
 	/**
 	 * ���뱬ըͼƬ��
 	 */
 	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] imgs= {
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/0.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/1.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/2.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/3.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/4.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/5.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/6.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/7.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/8.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/9.gif")),
		tk.getImage(TankDrea.class.getClassLoader().getResource("images/10.gif")),
	};
 	/**
 	 * ���캯����
 	 * @param x		x���ꡣ
 	 * @param y		y���ꡣ
 	 * @param tw	TankWar�����á�
 	 */
	TankDrea(int x, int y, TankWar tw){
		this.x = x;
		this.y = y;
		this.tw = tw;
	}
 	
 	/**
 	 * ����ըͼƬ��
 	 * @param g
 	 */
	
	public void drawTankDrea(Graphics g){
		if(!init){
			for (int i = 0; i < imgs.length; i++) {
				g.drawImage(imgs[i], x, y, null);
			}
			init = true;
		}
		if(!live) {
			tw.tankDreas.remove(this);
			return;
		}
		if(step == imgs.length){
			live = false;
			step = 0;
			return;
		}
		g.drawImage(imgs[step], x, y, null);
		
		step ++;
	}
}
