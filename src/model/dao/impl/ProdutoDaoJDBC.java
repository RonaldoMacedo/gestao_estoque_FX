package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("select * from produto where id_produto = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Produto prod = new Produto();
				prod.setId_produto(rs.getInt("id_produto"));
				prod.setDescricao_interna(rs.getString("descricao_interna"));
				prod.setSituacao(rs.getString("situacao"));
				prod.setSaldo(rs.getInt("saldo"));
				return prod;
			}
			return null;
		}catch(SQLException e) {
			throw new DbException(e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
			
		}
	}

	@Override
	public List<Produto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
