package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import model.services.DepartmentService;
import model.services.SellerService;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		loadView("/gui/SellerList.fxml", (SellerListController controller) -> { 
		    controller.setSellerService(new SellerService()); 
		    controller.updateTableView(); 
		});
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml", (DepartmentListController controller) -> { //expressao lambda
		    controller.setDepartmentService(new DepartmentService()); //processo manual de injetar dependencia no meu controller
		    controller.updateTableView(); //e depois chamar para atualizar os dados na tela da tableView
		}); //funçao para inicializar o controlador
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml", x -> {});
	}
	
	@Override
	public void initialize(URL uri, ResourceBundle rb) {
	}
	
	// a parametrização do Consumer<T> é para ter apenas uma unica versao da função loadView, se nao teria que criar 3, uma para cada(About, Department e Seller)
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) { //quando clicar no menuItem About, abrir uma nova janela
		try {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
		    VBox newVBox = loader.load();
		
		    Scene mainScene = Main.getMainScene();
		    VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
		    
		    Node mainMenu = mainVBox.getChildren().get(0);
		    mainVBox.getChildren().clear();
		    mainVBox.getChildren().add(mainMenu);
		    mainVBox.getChildren().addAll(newVBox.getChildren());
		    
		    T controller = loader.getController(); //agora o getController vai retornar o controlador do tipo que eu chamar la em cima
		    initializingAction.accept(controller); //para executar a açao
	    }
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
}
