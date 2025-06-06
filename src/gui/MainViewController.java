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
import model.services.ListaProdutoService;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemProduto;
	
	@FXML
	private MenuItem menuItemSobre;
	
	@FXML
	public void onMenuItemProduto() {
		loadView("/gui/ListaProduto.fxml", (ListaProdutoController controller) -> {
			controller.setListaProdutoService(new ListaProdutoService());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemSobre() {
		loadView("/gui/Sobre.fxml", x -> {});
	}

	@Override
	public void initialize(URL uri, ResourceBundle rb) {
		
		
	}
	
	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();
			
			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());
			
			T controller = loader.getController();
			initializingAction.accept(controller);
			
		}
		catch(IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar a pagina", e.getMessage(), AlertType.ERROR);
		}
	}

}
