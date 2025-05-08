package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.entities.Produto;
import model.exceptions.ValidationException;
import model.services.ListaProdutoService;

public class ProdutoFormController implements Initializable {
	
	private Produto entidade;
	private ListaProdutoService service;
	
	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();
	
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
	
	public void setProdutoService(ListaProdutoService service) {
		this.service = service;
	}
	
	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);
	}
	
	@FXML
	public void onBtIncluirAction(ActionEvent event) {
		if(entidade == null) {
			throw new IllegalStateException("Entidade estava nula");
		}
		if(service == null) {
			throw new IllegalStateException("Serviço estava nulo");
		}
		try {
			entidade = getFormData();
			service.saveOrUpdate(entidade);
			notifyDataChangeListeners();
			Utils.stageAtual(event).close();
		}
		catch(ValidationException e) {
			setErrorMessages(e.getErrors());
		}
		catch(DbException e) {
			Alerts.showAlert("Erro ao incluir produto", null, e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void notifyDataChangeListeners() {
		for(DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
		
	}

	private Produto getFormData() {
		Produto obj = new Produto();
		
		ValidationException exception = new ValidationException("Erro de validação");
		
		obj.setId_produto(Utils.tryParseToInt(txtCodigo.getText()));
		
		if(txtDescricao.getText() == null || txtDescricao.getText().trim().equals("")) {
			exception.addError("descricao", "Nome do produto obrigatório");
		}
		obj.setDescricao_interna(txtDescricao.getText());
		obj.setSituacao(txtSituacao.getText());
		obj.setSaldo(Utils.tryParseToInt(txtSaldo.getText()));
		
		if(exception.getErrors().size() > 0) {
			throw exception;
		}
		
		return obj;
	}

	@FXML
	public void onBtCancelarAction(ActionEvent event) {
		Utils.stageAtual(event).close();
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
	
	private void setErrorMessages(Map<String, String> errors) {
		Set<String> fields = errors.keySet();
		
		if(fields.contains("descricao")) {
			labelErroDescricao.setText(errors.get("descricao"));
		}
	}

}
