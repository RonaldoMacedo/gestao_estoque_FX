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
	
	public void saveOrUpdate(Produto obj) {
		if(obj.getId_produto() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}

}
