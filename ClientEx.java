import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class ClientEx extends JFrame {
	
	JLabel la = new JLabel("안녕하세요.서버채팅창입니다.");  // “안녕하세요”를 표시하는 JLabel 생성
	JTextField txt = new JTextField("",30); // 30문자 입력 가능한 텍스트필드 생성 
	JTextField txt2 = new JTextField("",30); // 30문자 입력 가능한 텍스트필드 생성 
	
	ClientEx() {
		setTitle("서버 채팅 창"); //프레임의 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프로그램 윈도우을 닫으면 프로그램 종료
		Container cp = getContentPane();//컨텐트팬을 알아낸다.
		cp.setBackground(Color.GRAY); //배경색을 GRAY로 한다.
		cp.setLayout(new FlowLayout(FlowLayout.CENTER,80,30));
		
		la.setLocation(200,200);
		la.setSize(500,450);
		cp.add(la);
		
		txt.setText("");
		cp.add(txt);
		
		
		JButton bt1 = new JButton("전송");//전송버튼
		cp.add(bt1);
		
		JButton btx= new JButton("종료");//종료버튼
		cp.add(btx);
		
		setSize(450,450);
		setVisible(true);
	}
	class ActionButton implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String k;
			k = txt.getText(); // 현재 텍스트 내용을 가져와 정수로 변환
			JButton b = (JButton)e.getSource();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ClientEx();
		BufferedReader in = null;
		BufferedWriter out = null;
		Socket socket = null;
		Scanner scanner = new Scanner(System.in); // 키보드에서 읽을 scanner 객체 생성
		try {
			socket = new Socket("localhost", 9999); // 클라이언트 소켓 생성. 서버에 연결
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			while (true) {
				System.out.print("보내기>>"); // 프롬프트
				String outputMessage = scanner.nextLine();   // 키보드에서 한 행 읽기
				if (outputMessage.equalsIgnoreCase("bye")) {
					out.write(outputMessage+"\n");   // "bye" 문자열 전송
					out.flush();
					break;     // 사용자가 "bye"를 입력한 경우 서버로 전송 후 실행 종료
				}
				out.write(outputMessage + "\n"); // 키보드에서 읽은 문자열 전송
				out.flush(); // out의 스트림 버퍼에 있는 모든 문자열 전송
				String inputMessage = in.readLine(); // 서버로부터 한 행 수신
				System.out.println("서버: " + inputMessage);
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				scanner.close();
				if(socket != null) socket.close(); // 클라이언트 소켓 닫기
			} catch (IOException e) {
				System.out.println("서버와 채팅 중 오류가 발생했습니다.");
			}
		}
	}
}


