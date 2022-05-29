package ex04;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ex04 extends JFrame{
	
	JLabel la = new JLabel("안녕하세요. 커피자판기를 이용해주셔서 감사합니다.");  // “안녕하세요”를 표시하는 JLabel 생성
	JTextField txt = new JTextField("0",10); // 10문자 입력 가능한 텍스트필드 생성 
	JTextField txt2 = new JTextField("0",10); // 10문자 입력 가능한 텍스트필드 생성 
	
	ex04() {
		setTitle("자판기 GUI 과제"); //프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프로그램 윈도우을 닫으면 프로그램 종료
		
		Container cp = getContentPane();//컨텐트팬을 알아낸다.
		cp.setBackground(Color.CYAN); //배경색을 CYAN로 한다.
		cp.setLayout(new FlowLayout(FlowLayout.RIGHT,80,30));
		
		JLabel a = new JLabel("잔액:");
		cp.add(a);
		cp.add(txt);
		
		JButton bt1 = new JButton("밀크커피(3백원)");
		JButton bt2 = new JButton("설탕커피(2백원)");
		JButton bt3 = new JButton("블랙커피(1백원)");
		bt1.addActionListener(new ActionButton());
		bt2.addActionListener(new ActionButton());
		bt3.addActionListener(new ActionButton());
		cp.add(bt1);
		cp.add(bt2);
		cp.add(bt3);
		
		JLabel b = new JLabel("현금 투입");
		cp.add(b);
		
		JButton bt4 = new JButton("투입");
		cp.add(txt2);
		JButton bt5 = new JButton("반환");
		bt4.addActionListener(new ButtonListener());
		bt5.addActionListener(new ActionButton());
		cp.add(bt4);
		cp.add(bt5);
		
		la.setLocation(250,200);
		la.setSize(200,200);
		cp.add(la);
		
		setSize(550,500);
		setVisible(true);
	}
	
	class ActionButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = Integer.parseInt(txt.getText()); // 현재 텍스트 내용을 가져와 정수로 변환
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("밀크커피(3백원)")){ //밀크커피를 클릭하면 잔액 -300원 , 만약 잔액이 부족한경우 부족하다고 출력
				if(k - 300 >= 0)
	            	{
	            		la.setText("밀크 + 커피가 나옵니다 맛있게 드시기  바랍니다.");
	            		k -= 300;
	            		txt.setText(Integer.toString(k));  // 정수 변수의 값을 문자열로 변환하여 표시
	            	}
				else
					la.setText("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
				}
			if(b.getText().equals("설탕커피(2백원)")){ //설탕커피를 클릭하면 잔액 -200원 , 만약 잔액이 부족한경우 부족하다고 출력
				if(k - 200 >= 0)
	            	{
	            		la.setText("설탕 + 커피가 나옵니다 맛있게 드시기  바랍니다.");
	            		k -= 200;
	            		txt.setText(Integer.toString(k));  // 정수 변수의 값을 문자열로 변환하여 표시
	            	}
				else
					la.setText("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
				}
			if(b.getText().equals("블랙커피(1백원)")){ //블랙커피를 클릭하면 잔액 -100원 , 만약 잔액이 부족한경우 부족하다고 출력
				if(k - 100 >= 0)
	            	{
	            		la.setText("블랙커피가 나옵니다 맛있게 드시기  바랍니다.");
	            		k -= 100;
	            		txt.setText(Integer.toString(k));  // 정수 변수의 값을 문자열로 변환하여 표시
	            	}
				else
					la.setText("잔액이 부족합니다. 현금을 투입해 주시기 바랍니다.");
				}
			if(b.getText().equals("반환")) { 
		         la.setText("잔액" + k + "원이 반환되었습니다."); 
		         k = 0;
		         txt.setText(Integer.toString(k));  // 정수 변수의 값을 문자열로 변환하여 표시
		         txt2.setText("0");
			} 
		}
	}
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b2 = (JButton)e.getSource();
			int k2 = Integer.parseInt(txt.getText());
			if(b2.getText().equals("투입")){
			int k = Integer.parseInt(txt2.getText());  // 현재 텍스트 내용을 가져와 정수로 변환
			txt.setText(Integer.toString(k));  // 정수 변수의 값을 문자열로 변환하여 표시 
			k2 += k;
			txt.setText(Integer.toString(k2)); // 정수 변수의 값을 문자열로 변환하여 표시 
		}
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ex04();
		
		Scanner s = new Scanner(System.in);
		CasherBox cash = new CasherBox();
		
		System.out.println("안녕하세요. 커피자판기를 이용해주셔서 감사합니다.");
		while(true) {
			
            cash.showBalance();
            int answer = s.nextInt();
            cash.addBalance(answer);
		}
	}
}

class CasherBox {
	String name;
	int balance;
	
	int addBalance(int k) {
		 if (k == 100 || k == 500 || k == 1000) {
	            balance += k;
	            return balance;
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


