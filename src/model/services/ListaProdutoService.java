package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Produto;

public class ListaProdutoService {
	
	public List<Produto> findAll(){
		List<Produto> list = new ArrayList<>();
		list.add(new Produto(1, "Luva de latex pp", "Ativo", 45));
		list.add(new Produto(2, "Tubo seco adulto", "Ativo", 45000));
		list.add(new Produto(3, "Agulha 25x8 a vacuo", "Ativo", 12000));
		return list;
	}

}
