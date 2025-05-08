package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.entities.Produto;
import model.services.ListaProdutoService;

public class ListaProdutoController implements Initializable, DataChangeListener {
	
	private ListaProdutoService service;
	
	@FXML
	private TableView<Produto> tabelaProduto;
	
	@FXML
	private TableColumn<Produto, Integer> colunaIdProduto;
	
	@FXML
	private TableColumn<Produto, String> colunaDescricaoInterna;
	
	@FXML
	private TableColumn<Produto, String> colunaSituacao;
	
	@FXML
	private TableColumn<Produto, Integer> colunaSaldo;
	
	@FXML
	private Button btNovo;
	
	private ObservableList<Produto> obsList;
	
	@FXML
	public void onBtNovoAction(ActionEvent event) {
		Stage parentStage = Utils.stageAtual(event);
		Produto obj = new Produto();
		createDialogForm(obj, "/gui/ProdutoForm.fxml", parentStage);
	}
	
	public void setListaProdutoService(ListaProdutoService service) {
		this.service = service;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		colunaIdProduto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
		colunaDescricaoInterna.setCellValueFactory(new PropertyValueFactory<>("descricao_interna"));
		colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		colunaSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tabelaProduto.prefHeightProperty().bind(stage.heightProperty());		
		
	}
	
	public void updateTableView() {
		if(service == null) {
			throw new IllegalStateException("Service was null");
		}
		List<Produto> list = service.findAll();
		obsList = FXCollections.observableArrayList(list);
		tabelaProduto.setItems(obsList);
	}
	
	private void createDialogForm(Produto obj, String absoluteName, Stage parentStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			Pane pane = loader.load();
			
			ProdutoFormController controller = loader.getController();
			controller.setProduto(obj);
			controller.setProdutoService(new ListaProdutoService());
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Insira os dados do produto");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();
			
		}catch(IOException e) {
			Alerts.showAlert("IOException", "Erro ao carregar a tela", e.getMessage(), AlertType.ERROR);
		}
	}

	@Override
	public void onDataChanged() {
		updateTableView();
		
	}

}
