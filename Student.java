
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Student extends JFrame {

	private JPanel contentPane;
	private JLabel lblJoin;
	private JButton joinCompleteBtn;
	private JTextField tfnumber;
    private JTextField tfPassword;
	private JTextField tfName;
	private JTextField tfEmail;
	private JTextField tfPhone;
	private JTextField tfadrass;


	/**

	 * 응용프로그램 실행

	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student frame = new Student();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//틀생성

	public Student() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(430, 490);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);	

		JLabel lblnumber = new JLabel("학번");//학번 
		contentPane.add("NORTH",lblnumber);

		JLabel lblName = new JLabel("이름");//이름
		contentPane.add("NORTH",lblName);
		
		JLabel label = new JLabel("학과");//학과 이름
		contentPane.add("NORTH",label);

		JLabel lblPhone = new JLabel("전화");//전화번호 이름

		contentPane.add("NORTH",lblPhone);
		
		JLabel lblEmail = new JLabel("이메일");//이메일 이름
		contentPane.add("NORTH",lblEmail);

		JLabel lbladrass = new JLabel("주소");//주소 이름
		contentPane.add("NORTH",lbladrass);

		tfnumber = new JTextField();
		tfnumber.setColumns(10);
		contentPane.add("NORTH",tfnumber);

		tfPassword = new JTextField();
		tfPassword.setColumns(10);
		contentPane.add("NORTH",tfPassword);	

		tfName = new JTextField();
		tfName.setColumns(10);
		contentPane.add("NORTH",tfName);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		contentPane.add("NORTH",tfEmail);

		tfPhone = new JTextField();
		tfPhone.setColumns(10);
		contentPane.add("NORTH",tfPhone);
		
		tfadrass = new JTextField();
		tfadrass.setColumns(10);
		contentPane.add("NORTH",tfadrass);
		
		joinCompleteBtn = new JButton("조회");
		contentPane.add("SOUTH",joinCompleteBtn);
		joinCompleteBtn.setLayout(new FlowLayout(FlowLayout.TRAILING)); //버튼 오른쪽 정렬
		joinCompleteBtn = new JButton("저장");
		contentPane.add("SOUTH",joinCompleteBtn);
		joinCompleteBtn.setLayout(new FlowLayout(FlowLayout.TRAILING)); //버튼 오른쪽 정렬
		setVisible(true);

		//회원가입완료 액션
		joinCompleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "저장되었습니다.");
				dispose();				
			}
		});
	}
}



/*
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
public class Student {
	public static void main(String[] args){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle Driver 클래스를 성공적으로 메모리에 로드하였습니다.");
			System.out.println(" =>Oracle Driver 인스턴스 생성");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle Driver 클래스를 찾을 수 없습니다.");
			System.exit(0);
		}
		System.out.println("================================");
		
		Enumeration<Driver> driverList = DriverManager.getDrivers();
		
		while(driverList.hasMoreElements()) {
			Driver driver = (Driver) driverList.nextElement();
			System.out.println(driver);
		}
		System.out.println("================================");
		}
	}
*/
