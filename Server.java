import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
 
//멀티 채팅을 위한 서버 구성

public class Server 
{
  ServerSocket server;
  Socket sk;
  ArrayList<ServerThread> list = new ArrayList<ServerThread>();  
   
  public Server(){ //생성자
      try {
          server = new ServerSocket(7000); //서버 소켓 생성
          while(true)
          {
              System.out.println("\n클라이언트 접속 대기중");
              sk = server.accept(); // 클라이언트 접속 대기중
              System.out.println(sk.getInetAddress()+"의 주소가 연결되었습니다. ");            
              //접속된 클라이언트를 스레드로 만들어 ArrayList에 추가
              ServerThread st = new ServerThread(this);
              addThread(st);
              st.start(); //쓰레드 시작하기          
          }
      } catch (IOException e) 
      {
          System.out.println(e + " -> ServerSocket fil");
      }
  }//생성자 끝
 
  // 접속된 클라이언트를 저장하기

  public void addThread(ServerThread st)
  {
          list.add(st);  
  }//추가스레드
 
  //접속이 끊긴 클라이언트를 ArrayList에 제거하기
   
  public void removeThread(ServerThread st)
  {
          list.remove(st); 
  }//제거스레드
 
  // 접속된 각각의 클라이언트에게 데이터 전송하기.
   
  public void broadCast(String message)
  {     
      for(ServerThread st : list)
      {
          st.pw.println(message);
      }              
  }
 
  public static void main(String[] args) {
      new Server();
  }
} 
 
//////////////////////////////////////////////////////////////////////

class ServerThread extends Thread
{
	Server server;
	PrintWriter pw;
	String name;
	public ServerThread(Server server)
	{
      this.server = server;
  }
  
  public void run() {
      try 
      { 
      BufferedReader br= new BufferedReader
              (new InputStreamReader(server.sk.getInputStream()));   //읽기
      pw = new PrintWriter(server.sk.getOutputStream(),true);     //쓰기
             
      name = br.readLine(); //대화명 읽기
     
      server.broadCast("["+name+"]님 입장하셨습니다.");
     
      String data= null;
      while((data = br.readLine()) != null)
      {
          server.broadCast("["+name+"] "+ data);          
      }
 } 
      catch (Exception e) 
      {
          //현재 쓰레드를 ArrayList에 제거한다.
          server.removeThread(this);
          server.broadCast("[ "+name+"] 님이 퇴장하셨습니다.");
          System.out.println
          (server.sk.getInetAddress()+"주소의 [ "+name+"] 님이 퇴장하셨습니다.");
          System.out.println(e);
      }          
  }
}
