package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Produto;

public class ProdutoFormController implements Initializable {
	
	private Produto entidade;
	
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
	
	public void setProduto(Produto entidade) {
		this.entidade = entidade;
	}
	
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
	
	public void updateFormData() {
		if(entidade == null) {
			throw new IllegalStateException("A entidade estava nula");
		}
		txtCodigo.setText(String.valueOf(entidade.getId_produto()));
		txtDescricao.setText(entidade.getDescricao_interna());
		txtSituacao.setText(entidade.getSituacao());
		txtSaldo.setText(String.valueOf(entidade.getSaldo()));
	}

}
