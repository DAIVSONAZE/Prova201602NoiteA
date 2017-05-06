package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entidades.Brinquedos;
import util.ConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class BrinquedoDao {

	private Connection connection;

	public BrinquedoDao() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void inserir(Brinquedos brinquedos) {

		String sql = "INSERT INTO brinquedos (descricao, precoCusto,precoVenda,quantidadeEstoque,fornecedor,dataFabricao) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, brinquedos.getDescricao());
			stmt.setDouble(2, brinquedos.getPrecoCusto());
			stmt.setDouble(3, brinquedos.getPrecoVenda());
			stmt.setInt(4, brinquedos.getQtdEstoque());
			stmt.setString(5, brinquedos.getFornecedor());
			stmt.setDate(6, new java.sql.Date(brinquedos.getDataFabricacao().getTime()));

			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void remover(int id) {

		try {
			String sql = "DELETE FROM brinquedos WHERE id = ?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}



	public List<Brinquedos> listar() {

		try {
			List<Brinquedos> listaBrinquedo = new ArrayList<Brinquedos>();

			String sql = "SELECT * FROM brinquedos ORDER BY id";
			PreparedStatement stmt = this.connection.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Brinquedos brinquedos = new Brinquedos();
				brinquedos.setId(rs.getInt("id"));
				brinquedos.setDescricao(rs.getString("descricao"));
				brinquedos.setPrecoCusto(rs.getDouble("precoCusto"));
				brinquedos.setPrecoVenda(rs.getDouble("precoVenda"));
				brinquedos.setQtdEstoque(rs.getInt("quantidadeEstoque"));
				brinquedos.setFornecedor(rs.getString("fornecedor"));
				brinquedos.setDataFabricacao(rs.getDate("dataFabricao"));

				listaBrinquedo.add(brinquedos);
			}

			rs.close();
			stmt.close();
			connection.close();

			return listaBrinquedo;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

    private Brinquedos montarObjeto(ResultSet rs) throws SQLException {

    	Brinquedos brinquedos = new Brinquedos();
		brinquedos.setId(rs.getInt("id"));
		brinquedos.setDescricao(rs.getString("descricao"));
		brinquedos.setPrecoCusto(rs.getDouble("precoCusto"));
		brinquedos.setPrecoVenda(rs.getDouble("precoVenda"));
		brinquedos.setQtdEstoque(rs.getInt("qtdEstoque"));
		brinquedos.setFornecedor(rs.getString("fornecedor"));
		brinquedos.setDataFabricacao(rs.getDate("dataFabricao"));
	return brinquedos;
    }
}



