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
				double n1 = Double.parseDouble(strNum1); // String�� Double�� ��ȯ
				double n2 = Double.parseDouble(strNum2); // String�� Double�� ��ȯ
				
				Button btn = (Button) event.getSource(); // Ŭ���� ��ư Ȯ��

				if (btn == plus) { // ��ü�� �ּҰ� ��
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
		// ������ ��ư���� setOnAction �޼ҵ带 ȣ���Ѵ�.
		plus.setOnAction(handle);
		minus.setOnAction(handle);
		mul.setOnAction(handle);
		div.setOnAction(handle);
	}
}
