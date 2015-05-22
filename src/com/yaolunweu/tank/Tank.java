package com.yaolunweu.tank;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
/**
 * Tank�� 
 * ��̹�˵�������
 * @author С��
 *
 */
public class Tank {
	/*
	 * ������̹�˵Ĵ�С���ƶ��ٶȡ�
	 * OldX ��OldY �洢��̹���ƶ���һ����λ�á�
	 */
	private static final int XSPEED = 2;
	private static final int YSPEED = 2;
	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private int oldX, oldY;
	
	
	private boolean bU = false, bR = false, bD = false, bL = false;
	private TankWay  way = TankWay.STOP;
	private TankWay paoTongWay = TankWay.R; //̹�˸�����Ͳ�ķ����ӵ���
	private static Random r = new Random(); // �������
	private int setp = r.nextInt(20)+5; // �����˻�̹���ƶ��Ĳ�����
	private boolean live = true; 	
	private boolean good ;
	int x, y;
	TankWar tw;
	/**
	 * �����API�ĵ���
	 * ����ͼƬ���Զ���HashMap���Լ�ֵ��ͼƬ�� 
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static HashMap<TankWay, Image> imgs = new HashMap<TankWay,Image>();
	
	
	
	/**
	 * ����ʱ��̬����ִ�н�ͼƬ�����ڴ��С�
	 */
	static {
		tankImages = new Image[] {
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankL.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankLU.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankU.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankRU.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankR.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankRD.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankD.gif")),
		tk.getImage(Tank.class.getClassLoader().getResource("images/tankLD.gif")),
		};
		mapImgs(imgs);
	}
	
	public static void mapImgs(HashMap<TankWay,Image> ms){
		ms.put(TankWay.L, tankImages[0]);
		ms.put(TankWay.LU, tankImages[1]);
		ms.put(TankWay.U, tankImages[2]);
		ms.put(TankWay.RU, tankImages[3]);
		ms.put(TankWay.R, tankImages[4]);
		ms.put(TankWay.RD, tankImages[5]);
		ms.put(TankWay.D, tankImages[6]);
		ms.put(TankWay.LD, tankImages[7]);
	} 
	/**
	 * ���캯��
	 * @param x 	̹��λ��x��
	 * @param y		̹��λ��y��
	 * @param good	̹���Ǻû��ǻ���
	 */
	public Tank(int x, int y,boolean good){
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.good = good;
	}
	/**
	 * ���캯��
	 * @param x
	 * @param y
	 * @param good
	 * @param way	����TankWay���ͣ��õ�����
	 * @param tw	��TankWar �����á�
	 */
	public Tank(int x,int y,boolean good,TankWay way,TankWar tw){
		this(x, y, good);
		this.way = way;
		this.tw = tw;
	}
	/**
	 * ����̹�˵ķ���̹�ˡ�
	 * ���̹�����˾ͱ𻭡�
	 * @param g
	 */
	public void drawTank(Graphics g){
		if(!live){
			if(!good){
				tw.raTanks.remove(this);
			}
			return;
		}
		
		switch(paoTongWay){
		case L:
			g.drawImage(imgs.get(TankWay.L), x , y , null);
			break ;
		case LU:
			g.drawImage(imgs.get(TankWay.LU), x , y , null);
			break ;
		case U:
			g.drawImage(imgs.get(TankWay.U), x , y , null);
			break ;
		case RU:
			g.drawImage(imgs.get(TankWay.RU), x , y , null);
			break ;
		case R:
			g.drawImage(imgs.get(TankWay.R), x , y , null);
			break ;		
		case RD:
			g.drawImage(imgs.get(TankWay.RD), x , y , null);
			break ;
		case D:
			g.drawImage(imgs.get(TankWay.D), x , y , null);
			break ;
		case LD:
			g.drawImage(imgs.get(TankWay.LD), x , y , null);
			break ;
			
		}
			move();
	}
	/**
	 * ̹�˵��ƶ�������
	 * �����̹��λ���Ƿ񳬳������� ��
	 * �ж� �ǻ�̹�� ������ı�way�����ƶ� ������ӵ��� 
	 */
	public void move(){
		this.oldX = x;
		this.oldY = y;
		switch(way){
		case  L:
			x -= XSPEED;
			break;
		case  LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case  U:
			y -= YSPEED;
			break;
		case  RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case  R:
			x += XSPEED;
			break;
		case  RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case  D:
			y += YSPEED;
			break;
		case  LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		case  STOP:
			break;
		}
	
		if(this.way != TankWay.STOP) this.paoTongWay = this.way;
		if(x < 0) x = 0;
		if(y < 30) y = 30;
		if(x + Tank.WIDTH > TankWar.GAME_WIDTH) x = TankWar.GAME_WIDTH - Tank.WIDTH;
		if(y + Tank.HEIGHT > TankWar.GAME_HEIGHT) y = TankWar.GAME_HEIGHT - Tank.HEIGHT; 
		if(!good){
			TankWay[] tws = TankWay.values();
			if(setp == 0){
				setp = r.nextInt(20)+5;
				int ra = r.nextInt(tws.length);
				way = tws[ra];
			}
			setp --;
			
			if(r.nextInt(40) > 38) this.fire();
		
		}
	}
	/**
	 * ���°���ʱ���жϴ���
	 * @param key 	���ܰ���ֵ��
	 */
	public void pressedKey(int key){
		
		switch(key) {
		case KeyEvent.VK_LEFT :
			bL = true;
			break;
		case KeyEvent.VK_A :
			fire();
			break;
		case KeyEvent.VK_UP :
			bU = true;
			break;
		case KeyEvent.VK_RIGHT :
			bR = true;
			break;
		case KeyEvent.VK_DOWN :
			bD = true;
			break;
		}
		keyConnect();
		
	}
	/**
	 * ���ݰ��µİ����ж�̹�˵ķ���
	 */
	public void keyConnect(){
		if(bL && !bU && !bR && !bD) way = TankWay.L; 
		else if(bL && bU && !bR && !bD) way = TankWay.LU;
		else if(!bL && bU && !bR && !bD) way = TankWay.U;
		else if(!bL && bU && bR && !bD) way = TankWay.RU;
		else if(!bL && !bU && bR && !bD) way =TankWay.R;
		else if(!bL && !bU && bR && bD) way = TankWay.RD;
		else if(!bL && !bU && !bR && bD) way = TankWay.D;
		else if(bL && !bU && !bR && bD) way = TankWay.LD;
		else if(!bL && !bU && !bR && !bD) way = TankWay.STOP;
		
		
		
	}
	/**
	 * �ͷŰ���ʱ�����Ĵ���
	 * �� f2 ��̹�˸��
	 * ��Q����super�ӵ���
	 * @param key
	 */
	public void outKey(int key){
		switch(key) {
		case KeyEvent.VK_F2 :
			if(!tw.myTank.live)
				tw.myTank = new Tank(50, 500, true, TankWay.STOP, tw);
			 	tw.l1 = new Live(100, tw.myTank,tw);
			break;
		case KeyEvent.VK_Q :
			superFire();
			break;
		case KeyEvent.VK_LEFT :
			bL = false;
			break;
		case KeyEvent.VK_UP :
			bU = false;
			break;
		case KeyEvent.VK_RIGHT :
			bR = false;
			break;
		case KeyEvent.VK_DOWN :
			bD = false;
			break;
		}
		keyConnect();
		
	}
	/**
	 * �����ӵ���
	 * �����ж��ӵ��Ƿ������ڰ��ӵ����뵽 missiles�����в���������
	 * @return
	 */
	public Missile fire(){
		if(!live) return null;
		int x = this.x +Tank.WIDTH/2-Missile.WIDTH/2;
		int y = this.y +Tank.HEIGHT/2+5-Missile.HEIGHT/2;
		Missile m =  new Missile(x,y,good,paoTongWay,this.tw);
		tw.missiles.add(m);
		return m;
	}
	/**
	 *  ��superFire() �������á�
	 * @param tws	����	Tank�ࡣ
	 * @return
	 */
	public Missile fire(TankWay tws){
		if(!live) return null;
		int x = this.x +Tank.WIDTH/2-Missile.WIDTH/2;
		int y = this.y +Tank.HEIGHT/2-Missile.HEIGHT/2;
		Missile m =  new Missile(x,y,good,tws,this.tw);
		tw.missiles.add(m);
		return m;
		
	}
	/**
	 * ����super�ڵ� ����˸��������ӵ�����
	 * ����fire��TankWay��tws��������ϡ� 
	 */
	public void superFire(){
		TankWay[] tws = TankWay.values();
		for(int i=0; i<8; i++){
			fire(tws[i]);
		}
	}
	/**
	 * ������һ��λ�á�
	 * ̹������ǽ������̹��ʱ���á�
	 */
	public void stay(){
		this.x = oldX;
		this.y = oldY;
	}
	/**
	 * �õ�̹������λ����ռ������������ײ��⡣
	 * @return
	 */
	public Rectangle getRec(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
		
	}
	/**
	 * ̹����ǽ��ײ��⡣
	 * @param w  ����Wall��
	 */
	public void TankWithWall(Wall w){
			if( this.live && this.getRec().intersects(w.getRec())){
				stay();
			}
	}
	/**
	 * ̹����̹����ײ��⡣
	 * ��������stay����������̹�˻ص���һ��λ�á�
	 * @param tanks
	 */
	public void tankWithTank(ArrayList<Tank> tanks){
		for(int i=0; i<tanks.size(); i++){
			Tank t = tanks.get(i);
			if(this != t){
				if( this.live && t.isLive() && this.getRec().intersects(t.getRec())){
					this.stay();
					t.stay();
				}
			}
		}
	}
	
	/**
	 * ̹����Ѫ����ײ��⡣
	 * @param b
	 * @return
	 */
	
	public boolean eatBlood(Blood b){
		if( b.getLive() && this.isLive() && this.getRec().intersects(b.getRec())){
			tw.l1.live = 100;
			b.setLive(false);
			return true;
			}
		else return false;
	}
	/**
	 * �õ�̹������״̬��	
	 * @return
	 */
	public boolean isLive(){
		return live;
	}
	/**
	 * ����̹��������
	 * @param live
	 */
	public void setLive(boolean live){
		this.live = live;
	}
	/**
	 * ����̹�˵����ͣ��ã�������
	 * @return
	 */
	public boolean isGood(){
		return good;
	}
	

		
}
