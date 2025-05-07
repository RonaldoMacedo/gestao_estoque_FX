package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.entities.Produto;

public class ListaProdutoService {
	
	private ProdutoDao dao = DaoFactory.createProdutoDao();
	
	public List<Produto> findAll(){
		return dao.findAll();
	}

}
