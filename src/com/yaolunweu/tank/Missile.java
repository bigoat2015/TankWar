package com.yaolunweu.tank;
import java.awt.*;
import java.util.*;
/**
 * �ӵ���
 * @author С��
 */
public class Missile {
	/**
	 * �ӵ����ٶȡ�
	 * �߶ȺͿ�ȣ�Ӧ����ΪͼƬ�ĸ߶ȺͿ�ȡ�
	 */
	private static final int XSPEED = 2;
	private static final int YSPEED = 2;
	static final int WIDTH = 12;
	static final int HEIGHT = 12;
	/**
	 * �����ӵ���λ�á�
	 */
	int x,y;
	/**
	 * ����TankWar������ã�
	 * ��TankWar���Ա�뷽���ķ��ʡ�
	 */
	TankWar tw;
	/**
	 * �ڹ��캯���г�ʼ����"��"���ǻ�"��"��
	 * �õ�true������false�����ӵ����ӵ���̹����ײ��ʱ���ж��ӵ��Ƿ���ʧ��
	 */
	boolean good ;
	/**
	 * �Է���TankWar�����ã�
	 * �˸�����
	 */
	TankWay way;
	
	/**
	 *�ӵ�����������
	 */
	boolean live = true;
	
	/**
	 * �������д����Ǵ�Ӳ���϶�ȡͼƬ��
	 * ��new missile�Ǿͳ�ʼ�����´��룬
	 * ��ͼƬ����Ӱ���ص��ڴ��У�ֻ���ʼ��һ�ξͿ����ˣ�
	 * ���Զ���Ϊstatic ��̬�ġ�
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit(); 
	/* 	���������� Abstract Window Toolkit ʵ��ʵ�ֵĳ����ࡣ
	 * Toolkit �����౻���ڽ���������󶨵��ض��������߰�ʵ�֡� 
	 *��� GUI ���������첽ִ�С�����ζ���������ĳһ�����״̬��
	 *������̲�ѯ��״̬���򷵻ص�ֵ���ܲ�û�з�ӳ������ĸ��ġ�
	 *������������������²����������API�ĵ���
	 */
	private static Image[] missileImages = null; //����ͼƬ���顣
	private static Map<String, Image> imgs = new HashMap<String,Image>();
	//����Map�����Լ�ֵ��ʽ��ȡ�����е�ͼƬ��
	
	/**
	 * new missile ��ʱ�������ڴ��г�ʼ������ִֻ��һ�Ρ�
	 */
	static {
		missileImages = new Image[] {
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileL.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileLU.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileU.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileRU.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileR.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileRD.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileD.gif")),
		tk.getImage(Missile.class.getClassLoader().getResource("images/missileLD.gif")),
		};
		imgs.put("L", missileImages[0]);
		imgs.put("LU", missileImages[1]);
		imgs.put("U", missileImages[2]);
		imgs.put("RU", missileImages[3]);
		imgs.put("R", missileImages[4]);
		imgs.put("RD", missileImages[5]);
		imgs.put("D", missileImages[6]);
		imgs.put("LD", missileImages[7]);
	}	
	/**
	 * �ӵ����캯����
	 * @param x		��ʼ��x��ֵΪ̹�˵�xֵ����̹�˵�λ�øı���ı䡣
	 * @param y		��ʼ��y��ֵΪ̹�˵�yֵ��
	 * @param way	��ʼ��way��ֵ���õ��˸�����
	 */
	public Missile(int x, int y, TankWay way) {
		this.x = x;
		this.y = y;
		this.way = way;
	}
	/**
	 * 	���캯����
	 * @param x	
	 * @param y
	 * @param good	��ʼ���ӵ��Ǻ�"��"���ǻ�"��"��trueΪ�õ���falseΪ������
	 * @param way
	 * @param tw	TankWar���ͣ�TankWar���á�
	 */
	public Missile(int x, int y, boolean good,TankWay way, TankWar tw){
		this(x,y,way);
		this.good = good;
		this.tw = tw;
	}
	
	

	/**
	 * ���ӵ���
	 * �ж��ӵ��Ƿ����� �����ͱ��ˣ�����TankWar����missiles �������Ƴ���
	 * ���� ��������
	 * @param g ���ʡ�
	 */
	public void drawMissile(Graphics g){
		if(!live) {		
			tw.missiles.remove(this);
			return;
		}
		
		
		switch(way){
		case L : g.drawImage(imgs.get("L"), x, y, null); break;
		case LU : g.drawImage(imgs.get("LU"), x, y, null);break;
		case U : g.drawImage(imgs.get("U"), x, y, null);break;
		case RU : g.drawImage(imgs.get("RU"), x, y, null);break;
		case R : g.drawImage(imgs.get("R"), x, y, null);break;
		case RD : g.drawImage(imgs.get("RD"), x, y, null);break;
		case D : g.drawImage(imgs.get("D"), x, y, null);break;
		case LD : g.drawImage(imgs.get("LD"), x, y, null);break;
		}
		move();
	}
	/**
	 * �����ӵ����ӵ��Ĳ��� Tank����ӵ��ķ����ƶ���
	 */
	private void move(){
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
		
		}
		if( x<0 || y<0 || x>TankWar.GAME_WIDTH || y>TankWar.GAME_HEIGHT){	//ֻҪ�ӵ����������ڵĴ�С�ӵ���æ��
			live = false;
		}
	}
	/**
	 * �õ��ӵ�������״̬��
	 * @return	true �� \n, false ����
	 */
	public boolean isLive(){
		return live;
	}
	/**
	 * Rectangle ָ������ռ��е�һ������
	 * ͨ������ռ��� Rectangle �������Ϸ��ĵ� (x,y)��
	 * ��Ⱥ͸߶ȿ��Զ����������
	 * ���������API�ĵ���
	 * @return	�����ӵ�����������
	 */
	public Rectangle getRec(){
		return new Rectangle(x,y,WIDTH,HEIGHT);
		
	}
	/**
	 * �ӵ���̹����ײʱ��
	 * if( this.live && this.getRec().intersects(t.getRec()) && t.isLive() && this.good != t.isGood())
	 * �ж��ӵ��Ƿ����  and �ӵ���̹���Ƿ����� and ����ͬһ���ӵ����õ����߻����� �Ż��̹��Ȼ��
	 * new ��ը��(TankDrea) ���뵽TankWar tankDreasr�����в���������
	 * @param t
	 * @return
	 */
	public boolean hitTank(Tank t){
		if( this.live && this.getRec().intersects(t.getRec()) && t.isLive() && this.good != t.isGood()){
			TankDrea td = new TankDrea(x,y,tw);
			if(t.isGood()){
				tw.l1.live -= 10; 	// ��̹�˱���һ�μ�10��Ѫ��
				tw.missiles.remove(this);  	//�ӵ���ʧ��
				if(tw.l1.live <= 0) { 	//�ж�Ѫ���������ʱ��������ը��������̹������Ϊfalse��
					tw.tankDreas.add(td);
					t.setLive(false);
				}
			}	
			else {					//����ս̹�� ���ӵ���һ�ξ�������
				t.setLive(false);
				tw.tankDreas.add(td);
				tw.missiles.remove(this);
			}
			
			return true;
			
		}
		return false;	
	}
	/**
	 * �ӵ���raTanks�����е�ÿһ��̹����ײ��⡣ 
	 * @param raTanks
	 * @return
	 */
	public boolean hitTanks(ArrayList<Tank> raTanks) {
		for(int i=0;i<raTanks.size();i++){
			if(hitTank(raTanks.get(i))) 
				return true;
		} 
		return false;
	}
	/**
	 * �ӵ���ǽ��ײ��
	 * @param �ӵ����Ǹ�ǽ��ײ��
 	 */
	public void hitWall(Wall w){
			if(this.isLive() && this.getRec().intersects(w.getRec())) {
				tw.missiles.remove(this);
		}
		
		
	}
	/**
	 * �ӵ����ӵ���ײ��⡣
	 * @param ms	���ӵ������е��ӵ���⡣
	 */
	public void mToM(ArrayList<Missile> ms){
		for(int i=0; i<ms.size(); i++ ){
			Missile m = ms.get(i);
			if(this.isLive() && this.good != m.good && m.isLive() && this.getRec().intersects(m.getRec())) {
				ms.remove(m);
				ms.remove(this);
			}
		}
	}

}
