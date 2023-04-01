package model.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.Db;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao{
	
	private Connection conn;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller seller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ? ");
			
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dep = instantiateDepartment(rs);
				Seller seller = instantiateSeller(rs,dep);
				return seller;
			}
			return null;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs); 
			//não fecho a conexão porque outros métodos locais podem utilizar ela.
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
		int sellerId = rs.getInt("Id");
		String sellerName = rs.getString("Name");
		String sellerEmail = rs.getString("Email");
		Date sellerBirth = rs.getDate("BirthDate");
		double sellerSalary = rs.getDouble("BaseSalary");
		Seller seller = new Seller(sellerId, sellerName, sellerEmail, sellerBirth, sellerSalary, dep);
		return seller;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		int depId = rs.getInt("DepartmentId");
		String depName = rs.getString("DepName");
		Department dep = new Department(depId, depName);
		return dep;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ");
			
			st.setInt(1, (int) dep.getId());
			
			rs = st.executeQuery();
			
			List<Seller> sellers = new ArrayList<>();
			
			while(rs.next()) {//pode ter 0 ou N vendedores, por isso while ao inves de if
				Seller seller = instantiateSeller(rs,dep);
				sellers.add(seller);
			}
			return sellers;
		}
		catch(SQLException e){
			throw new DbException(e.getMessage());
		}
		finally {
			Db.closeStatement(st);
			Db.closeResultSet(rs); 
			//não fecho a conexão porque outros métodos locais podem utilizar ela.
		}
	}
	
}
