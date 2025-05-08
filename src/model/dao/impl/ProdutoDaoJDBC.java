package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
		PreparedStatement st = null;
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("insert into produto "
								+ "(descricao_interna, situacao) "
								+ "values "
								+ "(?, ?)",
								Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getDescricao_interna());
			st.setString(2, obj.getSituacao());
			
			int rowsAffected = st.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId_produto(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro inesperado! Nenhuma linha afetada!");
			}
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void update(Produto obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("update produto set descricao_interna = ?, situacao = ? where id_produto = ?");
			
			st.setString(1, obj.getDescricao_interna());
			st.setString(2, obj.getSituacao());
			st.setInt(3, obj.getId_produto());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
				
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
