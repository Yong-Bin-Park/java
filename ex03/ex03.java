package ex03;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ex03 extends JFrame{
	ex03() {
		setTitle("자판기 GUI 과제"); //프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프로그램 윈도우을 닫으면 프로그램 종료
		
		Container cp = getContentPane();//컨텐트팬을 알아낸다.
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ex03();
		
		Scanner s = new Scanner(System.in);
		CasherBox cash = new CasherBox();
		Button1 b1 = new Button1();
		Button2 b2 = new Button2();
		Button3 b3 = new Button3();
		CoffeeBox cb = new CoffeeBox();
		SugarBox sb = new SugarBox();
		MilkBottle mb = new MilkBottle();
		WaterBucket wb = new WaterBucket();
		CopContainer cc = new CopContainer();
		
		System.out.println("안녕하세요. 커피자판기를 이용해주셔서 감사합니다.");
		while(true) {
			
            cash.showBalance();
            int answer = s.nextInt();
            sb.SugarAmount();
            mb.MilkAmount();
    		cb.CoffeeAmount();
    		wb.WaterBucketAmount();
    		cc.CopContainerAmount();
            cash.addBalance(answer);
		}
	}
}

class Container {
	String name;
	int amount;
	
	void eject() {
		amount--;
	}
	boolean enoughAmount() {
		if(amount > 0)
			return true;
		else
			return false;
	}
}
class CoffeeBox extends Container {
	int amount = 1;
	
	void CoffeeAmount() {
		eject();
		enoughAmount();
		if(amount == 0) 
			System.out.println("재료(커피)가 부족합니다. 죄송하지만 다음에 이용 부탁드립니다.");
	}
}

class SugarBox extends Container {
	int amount = 1;
	
	void SugarAmount() {
		eject();
		enoughAmount();
		if(amount == 0) 
			System.out.println("재료(설탕)가 부족합니다. 죄송하지만 다음에 이용 부탁드립니다.");
	}
}

class MilkBottle extends Container {
	int amount = 1;
	
	void MilkAmount() {
		eject();
		enoughAmount();
		if(amount == 0) 
			System.out.println("재료(우유)가 부족합니다. 죄송하지만 다음에 이용 부탁드립니다.");
	}
}

class WaterBucket extends Container {
	int amount = 1;
	
	void WaterBucketAmount() {
		eject();
		enoughAmount();
		if(amount == 0) 
			System.out.println("재료(물)가 부족합니다. 죄송하지만 다음에 이용 부탁드립니다.");
	}
}

class CopContainer extends Container {
	int amount = 1;
	
	void CopContainerAmount() {
		eject();
		enoughAmount();
		if(amount == 0) 
			System.out.println("재료(컵)가 부족합니다. 죄송하지만 다음에 이용 부탁드립니다.");
	}
}

class CasherBox {
	String name;
	int balance;
	
	int addBalance(int k) {
		 if (k == 100 || k == 500 || k == 1000) {
	            balance += k;
	        }
		 switch(k) {
		 case 1:
	            if(balance - 300 >= 0)
	            {
	            	System.out.println("밀크 + 커피가 나옵니다 맛있게 드시기  바랍니다.");
                	balance -= 300;
                	break;
	            }
	            else 
	            	System.out.println("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
	            break;
		 case 2:
	            if(balance - 200 >= 0)
	            {
	                System.out.println("설탕 + 커피가 나옵니다 맛있게 드시기  바랍니다.");
	                balance -= 200;
	                break;
	            }
	            else 
	            	System.out.println("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
	            break;
		 case 3:
	            if(balance -100 >= 0)
	            {
	                System.out.println("블랙 커피가 나옵니다 맛있게 드시기  바랍니다.");
	                balance -= 100;
	                break;
	            }
	            else 
	            	System.out.println("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
	            break;
		 case 0:
	            ejectBalance();
	            System.out.println("안녕하세요. 커피자판기를 이용해주셔서 감사합니다.");
	            break;
		 }
	        return balance;
	}
	
	
	void showBalance() {
		
		System.out.println("(1) 밀크커피(3백원), (2) 설탕커피(2백원), (3) 블랙커피(1백원), (0) 잔액반환");
		System.out.println("금액 투입 또는 원하시는 음료를 선택해 주세요. 잔액"+ balance +" 원 >> ");
	}
	
	void ejectBalance() {
		System.out.println("잔액 "+balance+"원이 반환되었습니다.");
		balance = 0;
	}
}

class Button1 extends Container {
	String name;
	boolean OnOff;
	
	CoffeeBox cb = new CoffeeBox();
	SugarBox sb = new SugarBox();
	MilkBottle mb = new MilkBottle();
	WaterBucket wb = new WaterBucket();
	CopContainer cc = new CopContainer();
	
	void pushButton() {
		mb.MilkAmount();
		cb.CoffeeAmount();
		wb.WaterBucketAmount();
		cc.CopContainerAmount();
	}
}

class Button2 extends Container {
	String name;
	boolean OnOff;
	
	CoffeeBox cb = new CoffeeBox();
	SugarBox sb = new SugarBox();
	MilkBottle mb = new MilkBottle();
	WaterBucket wb = new WaterBucket();
	CopContainer cc = new CopContainer();
	
	void pushButton() {
		sb.SugarAmount();
		cb.CoffeeAmount();
		wb.WaterBucketAmount();
		cc.CopContainerAmount();
	}
}

class Button3 extends Container {
	String name;
	boolean OnOff;
	
	CoffeeBox cb = new CoffeeBox();
	SugarBox sb = new SugarBox();
	MilkBottle mb = new MilkBottle();
	WaterBucket wb = new WaterBucket();
	CopContainer cc = new CopContainer();
	
	void pushButton() {
		cb.CoffeeAmount();
		wb.WaterBucketAmount();
		cc.CopContainerAmount();
	}
}

