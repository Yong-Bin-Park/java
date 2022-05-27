package ex02;
import java.util.Scanner;
public class ex02 {

 public static void main(String[] args) {
  // TODO Auto-generated method stub
  Scanner s = new Scanner(System.in);
  
  String str[] = { "가위", "바위", "보"};
  int random[] = new int[3];   //중복되지 않게 넣어줄 랜덤값 배열선언
  
  while(true)
  {
	  System.out.println("컴퓨터와 가위 바위 보 하나 빼기 게임을 시작합니다.");
	  System.out.println("가위 바위 보! >>");
	  String me1 = s.next();
	  if(me1.equals("그만"))
	  {
		  System.out.println("프로그램을 종료합니다.");
		  return;
	  }
	  String me2 = s.next();
  
  for(int i=0;i<3;i++)   //중복x하게 랜덤 값을 배열에 넣어줌
  {
	  random[i] = (int)(Math.random()*3);
	  for(int r=0;r<i;r++)
	  {
		  if(random[i]==random[r])
			  i--;     //출력값이 같으면 재시작
	  }
  }
  
  int com = (int)(Math.random()*2);  //컴퓨터가 하나빼기 하는 랜덤
  System.out.print("컴퓨터는 ");
  for(int c =0;c<2;c++)  //컴퓨터가 내는 것
  System.out.print(str[random[c]] + " ");
  System.out.println("를 냈습니다.");
  System.out.println("하나빼기! >>");
  String user = s.next();
  
  System.out.println("컴퓨터가 "+ str[random[com]] + "를 냈습니다.");
  if(str[random[com]].equals("가위"))   //컴퓨터가 가위일 때
  {
	  if(user.equals("가위"))
		  System.out.println("비겼습니다.");
	  else if(user.equals("바위"))
		  System.out.println("사용자가 이겼습니다.");
	  else if(user.equals("보"))
		  System.out.println("컴퓨터가 이겼습니다.");
  }
  else if(str[random[com]].equals("바위"))   //컴퓨터가 바위일 때
  {
	  if(user.equals("가위"))
		  System.out.println("컴퓨터가 이겼습니다.");
	  else if(user.equals("바위"))
		  System.out.println("비겼습니다.");
	  else if(user.equals("보"))
		  System.out.println("사용자가 이겼습니다.");
  }
  else if(str[random[com]].equals("보"))  //컴퓨터가 보일 떄
  {
	  if(user.equals("가위"))
		  System.out.println("사용자가 이겼습니다.");
	  else if(user.equals("바위"))
		  System.out.println("컴퓨터가 이겼습니다.");
	  else if(user.equals("보"))
		  System.out.println("비겼습니다.");
  }
  
  } 
 }
} 
