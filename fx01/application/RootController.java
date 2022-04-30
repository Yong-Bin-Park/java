package application;

import java.net.*;
import java.util.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.control.*;

public class RootController implements Initializable {
	
	@FXML private TextField num1, num2,result;
	@FXML private Button plus, minus, mul, div;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		EventHandler<ActionEvent> handle = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				String strNum1 = num1.getText();
				String strNum2 = num2.getText();
				double n1 = Double.parseDouble(strNum1); // String을 Double로 변환
				double n2 = Double.parseDouble(strNum2); // String을 Double로 변환
				
				Button btn = (Button) event.getSource(); // 클릭한 버튼 확인

				if (btn == plus) { // 객체의 주소값 비교
					result.setText(n1 + n2 + "");
				} else if (btn == minus) {
					result.setText(n1 - n2 + "");
				} else if (btn == mul) {
					result.setText(n1 * n2 + "");
				} else if (btn == div) {
					result.setText(n1 / n2 + "");
				}
			}
		};
		// 각각의 버튼마다 setOnAction 메소드를 호출한다.
		plus.setOnAction(handle);
		minus.setOnAction(handle);
		mul.setOnAction(handle);
		div.setOnAction(handle);
	}
}
