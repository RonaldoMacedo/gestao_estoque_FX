package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_produto;
	private String descricao_interna;
	private String situacao;
	private Integer saldo;
	
	public Produto() {
		
	}

	public Produto(Integer id_produto, String descricao_interna, String situacao, Integer saldo) {
		super();
		this.id_produto = id_produto;
		this.descricao_interna = descricao_interna;
		this.situacao = situacao;
		this.saldo = saldo;
	}

	public Integer getId_produto() {
		return id_produto;
	}

	public String getDescricao_interna() {
		return descricao_interna;
	}

	public String getSituacao() {
		return situacao;
	}

	public Integer getSaldo() {
		return saldo;
	}

	public void setDescricao_interna(String descricao_interna) {
		this.descricao_interna = descricao_interna;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_produto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		return Objects.equals(id_produto, other.id_produto);
	}

	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", descricao_interna=" + descricao_interna + ", situacao="
				+ situacao + ", saldo=" + saldo + "]";
	}
			
}
