package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ProdutoFormController implements Initializable {
	
	@FXML
	private TextField txtCodigo;
	
	@FXML
	private TextField txtDescricao;
	
	@FXML
	private TextField txtSituacao;
	
	@FXML
	private TextField txtSaldo;
	
	@FXML
	private Label labelErroDescricao;
	
	@FXML
	private Label labelErroSituacao;
	
	@FXML
	private Button btIncluir;
	
	@FXML
	private Button btCancelar;
	
	@FXML
	public void onBtIncluirAction() {
		System.out.println("Incluir");
	}
	
	@FXML
	public void onBtCancelarAction() {
		System.out.println("Cancelar");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
		
	}
	
	private void initializeNodes() {
		Constraints.setTextFieldInteger(txtCodigo);
		Constraints.setTextFieldMaxLength(txtDescricao, 200);
	}

}
