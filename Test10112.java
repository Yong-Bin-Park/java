import javax.swing.*;
import java.awt.*;
import java.awt.Container;
import java.awt.event.*;

public class Test10112 extends JFrame {
    VendingMachine v  = new VendingMachine();   // VendingMachine 객체 선언
    JTextField coin = new JTextField("0",1000); // 동전 잔액 칸 선언
    JLabel txt = new JLabel("안녕하세요 별다방 입니다");   // 문구 선언
    Test10112() {

        setTitle("VendingMachine");                        // 타이틀
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container contentPane = getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.setLayout(null);                       // 배치 방밥

        // 잔액 부분
        JLabel coin_la = new JLabel("잔액 : ");       // 잔액 문구
        coin_la.setSize(300,30);
        coin_la.setLocation(50,80);
        contentPane.add(coin_la);

        coin.setSize(300,50);               // 잔액 표시
        coin.setLocation(150,70);
        contentPane.add(coin);


        // 커피 버튼 부분
        JButton MilkCoffee = new JButton("<html>밀크커피<br/>(300원)</html>");   // 밀크 커피 버튼
        MilkCoffee.setSize(100,50);
        MilkCoffee.setLocation(50,150);
        MilkCoffee.addActionListener(new CoffeeButton());
        contentPane.add(MilkCoffee);

        JButton SugarCoffee = new JButton("<html>설탕커피<br/>(200원)</html>");   // 설탕 커피 버튼
        SugarCoffee.setSize(100,50);
        SugarCoffee.setLocation(200,150);
        SugarCoffee.addActionListener(new CoffeeButton());
        contentPane.add(SugarCoffee);

        JButton BlackCoffee = new JButton("<html>블랙커피<br/>(100원)</html>");   // 블랙 커피 버튼
        BlackCoffee.setSize(100, 50);
        BlackCoffee.setLocation(350,150);
        BlackCoffee.addActionListener(new CoffeeButton());
        contentPane.add(BlackCoffee);

        // 동전 투입 부분
        JLabel insert_la = new JLabel("현금 투입"); // 동전 투입 글자
        insert_la.setSize(300,30);
        insert_la.setLocation(220,220);
        contentPane.add(insert_la);

        // 동전 버튼 부분
        JButton One = new JButton("50원");   // 50원 버튼
        One.setSize(100,50);
        One.setLocation(50,260);
        One.addActionListener(new CoinButton());
        contentPane.add(One);

        JButton Five = new JButton("100원");   // 100원 버튼
        Five.setSize(100,50);
        Five.setLocation(200,260);
        Five.addActionListener(new CoinButton());
        contentPane.add(Five);

        JButton Ten = new JButton("500원");   // 500원 버튼
        Ten.setSize(100, 50);
        Ten.setLocation(350,260);
        Ten.addActionListener(new CoinButton());
        contentPane.add(Ten);

        // 반환
        JButton eject = new JButton("반환");   // 반환 버튼
        eject.setSize(100, 50);
        eject.setLocation(190,400);
        eject.addActionListener(new EjectButton());
        contentPane.add(eject);

        // 문구
        txt.setSize(300,30);
        txt.setLocation(175,350);
        contentPane.add(txt);

        setSize(500,500);
        setVisible(true);
    }
    class CoinButton implements ActionListener {       // 동전 버튼 리스너
        public void actionPerformed(ActionEvent e) {
            String C = String.valueOf(v.balance);
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("50원")) {
                v.insertCoin(Coin.C50);
                C = String.valueOf(v.balance);
                txt.setText("50원 투입 되었습니다");
            }
            else if (b.getText().equals("100원")) {
                v.insertCoin(Coin.C100);
                C = String.valueOf(v.balance);
                txt.setText("100원 투입 되었습니다");
            }
            else if(b.getText().equals("500원")) {
                v.insertCoin(Coin.C500);
                C = String.valueOf(v.balance);
                txt.setText("500원 투입 되었습니다");
            }
            coin.setText(C);
        }
    }
    class CoffeeButton implements ActionListener {      // 커피 버튼 리스너
        public void actionPerformed(ActionEvent e) {
            String C = String.valueOf(v.balance);
            JButton b = (JButton)e.getSource();
            if(b.getText().equals("<html>밀크커피<br/>(300원)</html>")) {
                if (v.isEnableMilkCoffee()) {
                    v.milkCoffeeButton.pushButton();
                    txt.setText("밀크커피 나왔습니다");
                } else
                txt.setText("            실패");
            }
            if (b.getText().equals("<html>설탕커피<br/>(200원)</html>")) {
                if (v.isEnableSugarCoffee()) {
                    v.sugarCoffeeButton.pushButton();
                    txt.setText("설탕커피 나왔습니다");
                } else
                txt.setText("            실패");
            }
            if(b.getText().equals("<html>블랙커피<br/>(100원)</html>")) {
                if (v.isEnableBlackCoffee()) {
                    v.blackCoffeeButton.pushButton();
                    txt.setText("블랙커피 나왔습니다");
                } else
                txt.setText("            실패");
            }
            C = String.valueOf(v.balance);
            coin.setText(C);
        }
    }
    class EjectButton implements ActionListener {       // 반환 버튼 리스너
        public void actionPerformed(ActionEvent e) {
            txt.setText(String.valueOf(v.balance) + " 원이 반환 되었습니다");
            v.ejectButton.pushButton();
            coin.setText(String.valueOf(v.balance));
        }
    }
}