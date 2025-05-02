package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;
import model.services.ListaProdutoService;

public class ListaProdutoController implements Initializable {
	
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
	public void onBtNovoAction() {
		System.out.println("Novo produto");
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

}
