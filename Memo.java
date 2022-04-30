import java.awt.*;  
import javax.swing.*;  
import javax.swing.filechooser.*;  
import javax.swing.text.StyledEditorKit;  
import java.awt.event.*;  
import java.io.*;  
  
public class Memo extends JFrame  
{  
 // 변수 선언  
 JTextArea text = new JTextArea("",10,30);
 JTextField text2;
 Container pane;  
 JButton btnOpen = new JButton("열기");
 JButton btnSave = new JButton("저장");
 JButton btnNew = new JButton("새로만들기");
 JMenu file, help;  
 JMenuItem newI,openI,saveI,infoI;  
 JFileChooser open = new JFileChooser();//파일 및 디렉토리 선택 컴포넌트 선언  
   
 public Memo()  
 {  
  super("메모장 예제"); // 부모클래스 생성자 호출  
  pane=getContentPane(); //JFrame 디자인을 위한 컨텐츠 영역 선언  
  pane.setLayout(new BorderLayout()); //JFrame 정렬  
    
  text2 = new JTextField(20);
  JPanel pn = new JPanel();
  pn.add("South",text2);
  pn.add(btnOpen);
  pn.add(btnSave);
  pn.add(btnNew);
  
  add("South", pn);
  pn.setLayout(new FlowLayout(FlowLayout.TRAILING)); //버튼 오른쪽 정렬
  JScrollPane ScrollPane = new JScrollPane(text); //스크롤바 생성
  add("Center", text);   
              
  // 메뉴에서 새파일 클릭했을때 이벤트 처리  
  btnNew.addActionListener(new ActionListener()  
  {  
   public void actionPerformed(ActionEvent e)  
   {  
    text.setText(""); // text내용을 모두 지운다  
   }  
  });  
   
  // 메뉴에서 열기 클릭했을때 이벤트 처리  
  btnOpen.addActionListener(new ActionListener()  
  {  
   public void actionPerformed(ActionEvent e)  
   {  
    int re = open.showOpenDialog(Memo.this); //파일열기 다이얼로그창을 띄운다  
    if (re==JFileChooser.APPROVE_OPTION)     //리턴 상태 확인  
    {  
     String fN;  
     String dir;  
     String str;  
      
     File file_open = open.getSelectedFile(); // 선택한 파일명을 가져온다  
     
     FileInputStream fis;   //파일 시스템의 파일 입력 바이트 취급 스트림 선언  
     ByteArrayOutputStream bo;  //데이터 바이트 배열에 기입해지는 출력 스트림 선언  
      try  
      {  
       fis = new FileInputStream(file_open); // FileInputStream객체를 생성  
       bo = new ByteArrayOutputStream();     // ByteArrayOutputStream객체를 생성  
       int i = 0;  
       while ((i = fis.read()) != -1) // 파일이 끝날때까지 읽어드림  
       {  
        bo.write(i);                  //len 바이트를 바이트 배열 출력 Stream에 기입  
       }  
        text.setText(bo.toString()); // 화면에 뿌려준다  
       fis.close();                 // FileInputStream을 닫는다.                  
       bo.close();                  // ByteArrayOutputStreamm을 닫는다.  
      }  
      catch(FileNotFoundException fe)  
      {}  
      catch(IOException ie)  
      {}  
    }  
   }  
  });  
   
  //메뉴에서 저장 클릭했을때 이벤트 처리  
  btnSave.addActionListener(new ActionListener()  
  {  
   public void actionPerformed(ActionEvent e)  
   {  
    int re = open.showSaveDialog(Memo.this);  
    if (re==JFileChooser.APPROVE_OPTION) // 파일저장 다이얼로그를 띄운다  
    {  
     File file_open = open.getSelectedFile(); // 저장할 파일명을 가져온다  
     
     try  
     {  
     PrintWriter pw   = new PrintWriter(new BufferedWriter(new FileWriter(file_open))); // PrintWriter객체를 생성해서  
     pw.write(text.getText()); // 화면의 내용을 파일에 쓴다  
     pw.close();  
     }  
       
     catch(FileNotFoundException ie2)  
        {}  
     catch(IOException ie)  
        {}  
    }  
   }  
  });  
    
  text = new JTextArea();  
   
  // 화면에 보여질 text들의 상태에 따른 색상 지정  
  text.setCaretColor(Color.black);   
  text.setSelectedTextColor(Color.white);  
  text.setSelectionColor(Color.blue);  
  text.setBackground(Color.white);  
   
  pane.add(new JScrollPane(text));  
  
 }  
   
 public static void main(String[] args)  
 {  
  Memo memo = new Memo(); // 객체생성  
  memo.setSize(500,500); // 사이즈 지정  
  memo.setVisible(true); // 화면에 보이게 함  
 }  
}



