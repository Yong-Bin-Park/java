
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

 
public class Game extends JFrame{			
	private JLabel bear = new JLabel("B");		
	public JLabel fish = new JLabel("@");		
	private JLabel win = new JLabel("Bear가 Fish를 먹었습니다~!!");	
	Thread th;

	public Game(){
		setTitle("Bear의 Fish 먹기 게임");			//타이틀제목 정하기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		//닫기를 누르면 종료
		Container c= getContentPane();			//컨탠트팬 알아내기
		c.setLayout(null);						
		c.addKeyListener(new MyKeyListener());	

		th = new Thread(new FishMove(fish));
		th.start();

		bear.setLocation(0,0);					
		bear.setSize(20,20);					
		fish.setLocation(200,200);				
		fish.setSize(20,20);					
		win.setLocation(80,40);				
		win.setSize(200,20);					
		win.setVisible(false);				

		c.add(bear);							
		c.add(win);							
		c.add(fish);							

		setSize(400,400);						
		setVisible(true);						
		c.setFocusable(true);					
		c.requestFocus();						
	}

//keylistener 만들기
	class MyKeyListener extends KeyAdapter{		
		public void keyPressed(KeyEvent e) {	
			int keyCode = e.getKeyCode();		
			int Eating=-1;						

			if(bear.getX()==fish.getX())		
				if(bear.getY()==fish.getY())	
					Eating = 0;					//bear와 fish 의 좌표가 모두 일치하면 
			if(Eating==0)						
			{
				win.setVisible(true);		//승리	
				th.interrupt();						

				return;
			}

			switch(keyCode) {					
			case KeyEvent.VK_UP:				
				if(bear.getY()!=0)bear.setLocation(bear.getX(), bear.getY() - 20); break;
			case KeyEvent.VK_DOWN:				
				if(bear.getY()!=340)bear.setLocation(bear.getX(), bear.getY() + 20); break;
			case KeyEvent.VK_LEFT:				
				if(bear.getX()!=0)bear.setLocation(bear.getX() - 20, bear.getY()); break;
			case KeyEvent.VK_RIGHT:				
				if(bear.getX()!=380)bear.setLocation(bear.getX() + 20, bear.getY()); break;
			}									
		}
	}

//키 리스너를 구현

class FishMove extends Thread implements Runnable{		
	JLabel fish;		

	public FishMove(JLabel f){
		this.fish = f;
	}

	public void run() {		
		Random a= new Random();		
		Random b= new Random();	

		int x=fish.getX(), y=fish.getY();	

		while(true) {
			try {
				Thread.sleep(250);			
			}
			catch(InterruptedException e) {		
				fish.setText("");					
				return;
			}

			int point = b.nextInt(2)+1;		
			if(point == 1) {x += a.nextInt()%2*20;}			
			else{y += a.nextInt()%2*20;}		
			if( x>0 && y>0 && x<360 && y<360)	
				fish.setLocation(x,y);				//fish의 좌표를 랜덤으로 생성
		}
	}
}

	public static void main(String [] args) {
		new Game();				
	}
}

/*
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame{
		  Game() {
		  setTitle("Bear의 Fish 먹기 게임"); //프레임에 타이틀 달기
	      Container cp = getContentPane();//컨텐트팬을 알아낸다.
	      
	      setSize(400, 400); // 사이즈를 400,400으로 한다.	    
	      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프로그램 윈도우을 닫으면 프로그램 종료
	      JLabel bear = new JLabel("B");
		  JLabel fish = new JLabel("@");
		  JLabel eat = new JLabel("Bear가  Fish를 먹었습니다~!!");
		  bear.setLocation(0,0);
		  fish.setLocation(200,200);
		  eat.setLocation(200,100);
		  cp.setLayout(null);
		  cp.add(bear);
		  cp.add(fish);
		  cp.add(eat);
		  
		  setVisible(true);
	  }
		  
	  abstract class GameObject { // 추상 클래스
			protected int distance; // 한 번 이동 거리
			protected int x, y; // 현재 위치(화면 맵 상의 위치)
			public GameObject(int startX, int startY, int distance) { // 초기 위치와 이동 거리 설정
				this.x = startX;
				this.y = startY;
				this.distance = distance;
			}
			public int getX() { return x; }
			public int getY() { return y; }
			public boolean collide(GameObject p) { // 이 객체가 객체 p와 충돌했으면 true 리턴
				if(this.x == p.getX() && this.y == p.getY())
					return true;
				else
					return false;
			}
			public abstract void move(); // 이동한 후의 새로운 위치로 x, y 변경
			public abstract char getShape(); // 객체의 모양을 나타내는 문자 리턴
		}
	  
	  class Bear extends GameObject {
			public Bear(int startX, int startY, int distance) {
				super(0, 0, 20);
			}
			@Override
			public void move() {
				Scanner sc = new Scanner(System.in);
				
				System.out.print("왼쪽(a), 아래(s), 위(d), 오른쪽(f) >> ");
				String direction = sc.next();
				
								
				switch(direction) {
				case "a":
					if(y==0)
						y=0;
					else
						y-=distance;
					break;
				case "s":
					if(x==9)
						x=9;
					else
						x+=distance;
					break;
				case "d":
					if(x==0)
						x=0;
					else
						x-=distance;
					break;
				case "f":
					if(y==19)
						y=19;
					else
						y+=distance;
					break;
				default:
						System.out.println("error");
				}				
			}
			
			@Override
			public char getShape() {
				return 'B';
			}
		}
	  class Fish extends GameObject {
			public Fish(int startX, int startY, int distance) {
				super(200,200,20);
			}
			
			@Override
			public void move() {
				int num = (int)(Math.random()*4);
			
				switch(num) {
				case 0:
					if(y==0)
						y=0;
					else
						y-=distance;
					break;
				case 1:
					if(x==9)
						x=9;
					else
						x+=distance;
					break;
				case 2:
					if(x==0)
						x=0;
					else
						x-=distance;
					break;
				case 3:
					if(y==19)
						y=19;
					else
						y+=distance;
					break;
				default:
						System.out.println("error");
				}			
			}
			
			@Override
			public char getShape() {
				return '@';
			}
		}
			  
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
		
		
	}

}*/
