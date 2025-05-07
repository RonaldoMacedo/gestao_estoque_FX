package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Produto;

public class ProdutoDaoJDBC implements ProdutoDao {
	
	private Connection conn;
	
	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Produto obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Produto obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Produto findById(Integer id) {
		return null;

	}

	@Override
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from produto");
			rs = st.executeQuery();
			
			List<Produto> list = new ArrayList<>();
			
			while(rs.next()) {
				Produto prod = new Produto();
				prod.setId_produto(rs.getInt("id_produto"));
				prod.setDescricao_interna(rs.getString("descricao_interna"));
				prod.setSituacao(rs.getString("situacao"));
				prod.setSaldo(rs.getInt("saldo"));
				list.add(prod);
			}
			
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

}
