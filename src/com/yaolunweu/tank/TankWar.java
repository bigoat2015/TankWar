package com.yaolunweu.tank;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * ������������̹�˵�������
 * ��Ҫ����javaDoc �����������������һ�汾��
 * @author xiaoyao
 *
 */

public class TankWar extends Frame {
	/**
	 * ��Ļ��ȡ�
	 */
	public static final int GAME_WIDTH = 800;
	/**
	 * ��Ļ�߶�
	 */
	public static final  int GAME_HEIGHT = 600;
	/**
	 * new һ���õ�̹�ˣ���̹�ˣ�������
	 */
	Tank myTank = new Tank(50, 500, true, TankWay.STOP, this);
	/**
	 * Ϊ��̹�˼�������ֵ������Ľ���Ӧ�ö�����Tank���С�
	 */
	Live l1 = new Live(100, myTank,this);
	/**
	 * ָ��λ�ô�С����һ��ǽ��
	 */
	Wall w1 = new Wall(400,100,20,400,this);
	/**
	 * ָ��λ�ô�С���ڶ���ǽ��
	 */
	Wall w2 = new Wall(200,300,400,20,this);
	/**
	 * ��Ѫ�顣
	 */
	Blood b = new Blood(this);
	/**
	 * װ���ӵ��ļ��ϡ�
	 */
	ArrayList<Missile> missiles = new ArrayList<Missile>();
	/**
	 * ��ը���ϡ�����ͬʱ���������ը��
	 */
	ArrayList<TankDrea> tankDreas = new ArrayList<TankDrea>();
	/**
	 * ��̹�˼��ϡ�
	 */
	ArrayList<Tank> raTanks = new ArrayList<Tank>();
	/**
	 * ��̬�����������ʱû���õ���
	 */
	static Random r = new Random();
	/**
	 * ����˫���壬ʹ�ú�ͼƬ���ᶶ����
	 * �������API�ĵ���
	 */
	
	Image offScreenImage = null;
	/**
	 * δ�õ�������
	 */
	private static final long serialVersionUID = 8257776081794640863L;
	/**
	 * ���ر���ͼƬ��
	 */
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image backgroundImage = tk.getImage(TankWar.class.getClassLoader().getResource("images/tankBackground1.gif"));
	/**
	 * �����������������塣
	 * @param args
	 */
	public static void main(String[] args) {
		new TankWar().DrawTankWar();
	}
	/**
	 * ���������л���һϵ����Ҫ�Ķ�����
	 * ʹ���̲߳���ϵ��ػ���
	 */
	public void paint(Graphics g){
		g.setColor(Color.red);
		/**
		 * �ڴ���������ʾ�����֡� 
		 */
		g.drawString("���ڷɵ��ӵ���"+ missiles.size(), 10, 50);
		g.drawString("��ըʣ��������"+ tankDreas.size(), 10,70);
		g.drawString("��̹��������"+ raTanks.size(), 10,90);
		g.drawString("�������"+ b.tmp, 10,110);
		
		g.drawImage(backgroundImage, 0 , 0 , null); //���뱳��ͼƬ������Ľ����ػ�ʱ�������ͼƬ��Ӱ���ٶȡ���
		
		/**
		 * ��ȡraTanks�����еĻ�̹��
		 * ����ǽ and raTanks�����е�ÿһ��̹�˽�����ײ��⡣
		 */
		for(int i=0;i<raTanks.size();i++){ 
				Tank t = raTanks.get(i);
				t.TankWithWall(w1);
				t.TankWithWall(w2);
				t.tankWithTank(raTanks);
				t.drawTank(g); 
		}
		/**
		 * ��ȡraTanks�����е�ÿһ���ӵ�
		 * ����ǽ and �ӵ� and ��̹��  raTanks�����е�ÿһ��̹�˽�����ײ��⡣
		 * �������missile �еĺ�����
		 */
		for(int i=0;i<missiles.size();i++){
			Missile m = missiles.get(i);
			m.hitTanks(raTanks);
			m.hitTank(myTank);
			m.hitWall(w1);
			m.hitWall(w2);
			m.mToM(missiles);
			m.drawMissile(g);
			//b.drawBlood(g);
		}
		/**
		 * ������ը��
		 */
		for(int j=0;j<tankDreas.size();j++){
			TankDrea td =  tankDreas.get(j);
			td.drawTankDrea(g);
		}
		/**
		 * ��̹�ˣ�Ѫ�� ����̹��������
		 */
		myTank.drawTank(g);
		myTank.tankWithTank(raTanks);
		myTank.eatBlood(b);
		w1.drawWall(g);
		w2.drawWall(g);
		l1.drawLive(g);
		if(raTanks.size() <= 0) tanks("reTankCount");
	} 
	/**
	 * ˫���弼�����������ס�
	 */
	public void update(Graphics g){
		if(offScreenImage == null){
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics goffScreen = offScreenImage.getGraphics();
		Color c = goffScreen.getColor();
		goffScreen.setColor(Color.black);
		goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		goffScreen.setColor(c);
		paint(goffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
		
	}
	
	/**
	 * ������ʾ��
	 * �������¼�����  ���°��� �ͷŰ��� �رմ��� �¼���
	 */
	
	public void DrawTankWar(){
		tanks("initTankCount");
		this.setTitle(" ̹�˴�ս ");
		this.setLocation(200, 100);
		this.setSize(GAME_WIDTH, GAME_HEIGHT);
		this.setBackground(Color.black);	
		this.setResizable(false);
		this.addWindowListener( new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
			
		});
		
		
		this.addKeyListener(new KeyAdapter(){
		
			public void keyReleased(KeyEvent e) {
				myTank.outKey(e.getKeyCode());
			}

			public void keyPressed(KeyEvent e){
				myTank.pressedKey(e.getKeyCode());
			}
		});
		
		setVisible(true);
	
		new Thread(new PaintTread()).start();  //������������߳̿�ʼ��
	
	}
	/**
	 * ����̹�ˡ�
	 * @param str	���������ļ�tank.properties ����̹��������
	 */
	public void tanks(String str){
		for(int i=0; i<PropertyMgr.pros(str); i++){
			raTanks.add(new Tank(r.nextInt(750),r.nextInt(550),false,TankWay.D,this));
		}
	}
	
	/**
	 * �����ڲ��߳��಻��ϵ��ػ� paint()������ 
	 * repaint();  ������ӵ��� paint() ������
	 * @author С��
	 *
	 */
	private class PaintTread implements Runnable{

		public void run() {
			while(true){
				repaint(); 
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
		
		
	}

}