package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
	
	//palco atual
	public static Stage currentStage(ActionEvent event) { //e recebe o obj ActionEvent q é o evento q o botão por ex recebeu
		return (Stage) ((Node) event.getSource()).getScene().getWindow();//implementação padrão para pegar o stage a partir do obj de evento(o event acima)
	}

}
