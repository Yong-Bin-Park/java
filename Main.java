import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.io.IOException;
import java.net.*;
import javax.swing.*;
import javax.swing.border.Border;
 
 
//배경색 0xffcc66 연노랑
class LogInFrame extends JFrame{
    JPanel smallpn, bigpn;
    JTextArea ta1,ta2;
    JButton bt;
    Color bgcolor;
    public LogInFrame() {
        setLayout(new FlowLayout());
        setSize(300,200);
        setVisible(true);
        setResizable(false);
        bgcolor=new Color(0xffcc66);
        ta1=new JTextArea(1,10);
        ta2=new JTextArea(1,10);
        bt=new JButton("LOGIN");
        smallpn=new JPanel();
        bigpn=new JPanel();
        GridLayout layout=new GridLayout(2,1);
        layout.setVgap(5);
        smallpn.setLayout(layout);
        smallpn.setBackground(bgcolor);
        smallpn.add(ta1, BorderLayout.NORTH);
        smallpn.add(ta2, BorderLayout.CENTER);
        bigpn.setLayout(new GridLayout(1,2));
        bigpn.setBackground(bgcolor);
        bigpn.add(smallpn, BorderLayout.WEST);
        bigpn.add(bt, BorderLayout.EAST);
        add(bigpn);
        this.getContentPane().setBackground(bgcolor);
        bigpn.setPreferredSize(new Dimension(200,80));
        
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
 
 
public class Main extends JFrame{
    JLabel titleLb, msgLb;
    JButton bt;
    JTextArea ta;
    JPanel pn;
    JScrollPane sc;
    String msgContents;
    Color bgColor;
    DatagramSocket dsock = null;
    InetAddress clientAddress;
    int clientPort;
    //MyKeyAdapter keyAdapter;
    public Main() {
        setSize(450,600);
        setResizable(false);
        bgColor=new Color(0xffcc66);
        this.getContentPane().setBackground(bgColor);
        setVisible(true);
        getContentPane().setVisible(true);
        setLayout(new BorderLayout());
        bt=new JButton("Send");
        ta=new JTextArea(4,16);
        ta.addKeyListener(new MyKeyAdapter());
        sc=new JScrollPane();
        pn=new JPanel();
        pn.setVisible(true);
        pn.setLayout(new GridLayout(1,2));
        pn.add(ta);        pn.add(bt);
        titleLb=new JLabel("JTalk__Server__");
        titleLb.setFont(new Font(null, Font.BOLD, 18));
        msgLb=new JLabel("Waiting For a Client...");
        msgLb.setFont(new Font(null, Font.BOLD, 14));
      //  sc.add(msgLb);
        sc.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sc.setViewportView(msgLb);
        sc.getViewport().setBackground(bgColor);
        
        //sc.add(ta);    sc.add(bt);
        add(titleLb, BorderLayout.NORTH);
        add(sc, BorderLayout.CENTER);
        add(pn, BorderLayout.SOUTH);
        msgContents=null;
        bt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send();
            }
        });
        
    }
    
    class MyKeyAdapter extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            int key=e.getKeyCode();
            //******************e.consume으로 엔터키 줄바꿈은 고쳤는데 , KeyBinding 내용 공부할것
            if(key==KeyEvent.VK_ENTER) {
                if(!e.isShiftDown()) {//쉬프트 엔터가 아닐 때는 전송
                    e.consume();
                    send();
                }
                //쉬프트 엔터면 줄바꿈
                else ta.append(System.lineSeparator());    //\r\n으로 해도되지만 안전하게.
            }
        }
    }
    
    public void send() {
        if(clientAddress!=null) {
            String sendMsg=null;
            sendMsg = new String(ta.getText());
 
            //sendMsg.replaceAll(System.lineSeparator(), "<br>");
            //*****************여기 수정해야함
            sendMsg.replaceAll(System.getProperty("line.separator"), "<br>");
            DatagramPacket sendPacket =new DatagramPacket(sendMsg.getBytes(),
                                        sendMsg.getBytes().length,clientAddress, 
                                        clientPort);
            try {
                dsock.send(sendPacket);
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            
            msgContents+="<br>Server&nbsp;&nbsp;:&nbsp;&nbsp;"+sendMsg;
            msgLb.setText("<html>"+msgContents+"</html>");
            ta.setText(null);
            if(sendMsg.equals("quit")) {
                dsock.close();
                System.out.println("서버소켓 닫힘");
            }
            
            //**왜 한 줄이 자꾸 남는 걸까...
            sc.revalidate();
            sc.getVerticalScrollBar().setValue(sc.getVerticalScrollBar().getMaximum());
            sc.revalidate();
        }
        else    System.out.println("No Client has been connected.");
    }
    
    public static void main(String[] args){
           
           //args 에러
             if(args.length != 1){
                    System.out.println("사용법 : java UDPEchoServer port");
                    System.exit(1);
             }
             
             int port = 0;
             try{
                 port = Integer.parseInt(args[0]);
             }
             
             catch(Exception e){
                    System.out.println("Port 번호는 양의 정수로 입력해서 주세요.");
                    System.exit(1);
             }
 
            /*
             try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
             }
             catch(Exception e) {e.printStackTrace();}
             */
             //이제 진짜 시작@
       // 클라이언트에게 DatagramPacket을 전송하거나 수신하기 위해 DatagramSocket 객체 생성
             Main main=new Main();
             main.revalidate();
             LogInFrame lf=new LogInFrame();
             lf.revalidate();
             //오ㅐ revalidate를 해야되냐
             //메시지를 받는 패킷 생성
             
             try {
                main.dsock=new DatagramSocket(port);
            } catch (SocketException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.err.println("바인드익셉션으로 내가 종료시킨거다...");
                System.exit(1);
            }
            try {
                main.titleLb.setText("<html>JTalk__Server__<br>IP&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;"+InetAddress.getLocalHost().getHostAddress()+"<br>Port | "+port+"</html>");
            } catch (UnknownHostException e1) {
                e1.printStackTrace();
            }
             byte[] buffer = new byte[1024];
             DatagramPacket receivePacket= new DatagramPacket(buffer, buffer.length);
             try{
                    System.out.println("접속 대기상태입니다.");
 
                    // 클라이언트로부터 DatagramPacket과 그 정보를 받는다
                    main.dsock.receive(receivePacket);
                    main.msgContents=receivePacket.getAddress().getHostAddress()+" has connected<br><br>";
                    //String msg =new String(receivePacket.getData(), 0,receivePacket.getData().length); 이래하면 엄청 길어지는듯
                    String msg =new String(receivePacket.getData(), 0,receivePacket.getLength());
                    main.msgContents+="Client&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;"+msg;
                    main.msgLb.setText("<html>"+main.msgContents+"</html>");
                    
                    main.clientAddress=receivePacket.getAddress();
                    main.clientPort=receivePacket.getPort();
                    RecieveThread recieveThread=new RecieveThread(main);
                    recieveThread.start();
 
             }
 
             
             catch(Exception e){ 
                 System.err.println(e);; 
             e.printStackTrace();
             }
       }
       
       
 
}
 
 
class RecieveThread extends Thread{
    DatagramSocket dsock;
    Main main;
    public RecieveThread(Main main) {
        this.main=main;
        this.dsock=main.dsock;
    }
    
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket= new DatagramPacket(buffer, buffer.length);
        while(true){
             // 클라이언트로부터 DatagramPacket을
            
            try {
                dsock.receive(receivePacket);
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
 
                      
 
           // 전송받은 데이터를 String 객체에 지정하고 출력
 
            String recievedMsg =new String(receivePacket.getData(), 0,receivePacket.getLength());
            main.msgContents+="<br>Client&nbsp;&nbsp;&nbsp;:&nbsp;&nbsp;&nbsp;"+recievedMsg;
            main.msgLb.setText("<html>"+main.msgContents+"</html>");
            if(recievedMsg.equals("quit")) break;
            main.sc.revalidate();
            main.sc.getVerticalScrollBar().setValue(main.sc.getVerticalScrollBar().getMaximum());
            main.sc.revalidate();
           
            try{ Thread.sleep(50);}
            catch(Exception e) {}
        }    
    }
}



