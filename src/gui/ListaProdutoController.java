package gui;

import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.entities.Produto;

public class ListaProdutoController implements Initializable {
	
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
	
	@FXML
	public void onBtNovoAction() {
		System.out.println("Novo produto");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}

	private void initializeNodes() {
		colunaIdProduto.setCellValueFactory(new PropertyValueFactory<>("id_produto"));
		colunaDescricaoInterna.setCellValueFactory(new PropertyValueFactory<>("descricao_interna"));
		colunaSituacao.setCellValueFactory(new PropertyValueFactory<>("situaco"));
		colunaSaldo.setCellValueFactory(new PropertyValueFactory<>("saldo"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tabelaProduto.prefHeightProperty().bind(stage.heightProperty());		
		
	}

}
