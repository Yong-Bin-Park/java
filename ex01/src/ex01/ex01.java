package ex01;
import java.util.Scanner;
public class ex01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
		
		String s1,s2,s3;
		boolean b1,b2;
		
		System.out.println("식을 입력하세요.");
		s1 =s.next();
		s2 =s.next();
		s3 =s.next();
		
		if(s2.equals("+"))
		{
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s3);
			System.out.print(d1+d2);
		}
		if(s2.equals("-"))
		{
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s3);
			System.out.print(d1-d2);
		}
		if(s2.equals("*"))
		{
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s3);
			System.out.print(d1*d2);
		}
		if(s2.equals("/"))
		{
			double d1 = Double.parseDouble(s1);
			double d2 = Double.parseDouble(s3);
			System.out.print(d1/d2);
		}
		if(s2.equals("%"))
		{
			int a1 = Integer.parseInt(s1);
			int a2 = Integer.parseInt(s3);
			System.out.print(a1%a2);
		}
		switch(s2)
		{
		case "&&":
			if(s1.equals("true"))
			{
				b1 = true;
				if(s3.equals("true"))
					b2= true;
				System.out.println("true");
				break;
			}
			else
				System.out.println("false");
			break;
		case "||":
			if(s1.equals("true"))
			{
				b1 =true;
				System.out.println("true");
				break;
			}
			if(s3.equals("true"))
			{
				b2 =true;
				System.out.println("true");
				break;
			}
			
		case "^":
			if(s1.equals("true"))
			{
				b1 =false;
			System.out.println("false");
			break;
			}
			else
			{
				b1 =true;
				System.out.println("true");
				break;
			}
		}
		s.close();
	}
}
