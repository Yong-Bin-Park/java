package guiClient;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
 
public class guiClient extends JFrame implements ActionListener, Runnable {
 
  JTextArea textArea = new JTextArea();
  JScrollPane jp = new JScrollPane(textArea);
  JTextField it = new JTextField();
  Socket sk;
  BufferedReader br;
  PrintWriter pw; // 다른 메서드에서 사용하기 위해 전역변수로
 
  public guiClient() 
  {
      setTitle("자바 채탕 프로그램"); //프레임에 타이틀 달기
      Container cp = getContentPane();//컨텐트팬을 알아낸다.
      textArea.setBackground(Color.GRAY); //배경색을 GRAY으로 한다.
      textArea.setEditable(false);// TextArea 입력하기 비활성화
      add(jp, "Center");
      add(it, "South");
      setSize(400, 600); // 사이즈를 400,600으로 한다.
      setVisible(true);
      it.requestFocus(); // 실행됐을때 커서
      JButton btx= new JButton("종료");//종료버튼
      btx.setLocation(100,100);
	  cp.add(btx);
	  
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프로그램 윈도우을 닫으면 프로그램 종료
      setVisible(true);
      it.addActionListener(this); 
  }
 
  //서버접속하는 메소드
  
  public void server_Connection() 
  {
      try {
          sk = new Socket("127.0.0.1", 7000);
          String name = JOptionPane.showInputDialog(this, "닉네임을 입력해주세요.",JOptionPane.INFORMATION_MESSAGE);    
          br = new BufferedReader(new InputStreamReader(sk.getInputStream()));   // 읽기       
          pw = new PrintWriter(sk.getOutputStream(), true);  // 쓰기
          pw.println(name); // 서버에 전송하기
 
          new Thread(this).start();
      }
       catch (Exception e) 
      {
          System.out.println(e + "소켓 접속 오류");
      }
  }
 
  public static void main(String[] args) {
      new guiClient().server_Connection(); 
  }
  
  public void run() {
      String data = null;
      try {
          while ((data = br.readLine()) != null) 
          {
              textArea.append(data + "\n");            
              //textArea박스의 스크롤바의 위치를 입력된 Text길이 만큼 내리기
              textArea.setCaretPosition(textArea.getText().length());
          }
      } catch (Exception e) 
      {
          System.out.println(e + " Client run fail");
      }
  }
  // 엔터쳤을때 이벤트 발생
 
  public void actionPerformed(ActionEvent e) 
  {
      String data = it.getText();
      pw.println(data); // 서버에 전송
      it.setText("");
  }
}




