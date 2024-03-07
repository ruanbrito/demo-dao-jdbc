package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
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
	public void insert(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Seller obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(
					"SELECT seller.*, department.Name as DepName "
					+ "FROM seller "
					+ "INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+"WHERE seller.Id = ?");
			pst.setInt(1, id);
			
			rs = pst.executeQuery();
			
			if(rs.next()) {
				Department depart = instatiateDepartment(rs);
				Seller seller = instantiateSeller(rs, depart);
				return seller;
			}
			return null;
		} 
		catch (SQLException e ) {
			throw new DbException(e.getMessage());
		}
		finally {

			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	private Seller instantiateSeller(ResultSet rs, Department depart) throws SQLException {
		Seller seller = new  Seller();
		seller.setId(rs.getInt("Id"));
		seller.setName(rs.getString("Name"));
		seller.setEmail(rs.getString("Email"));
		seller.setBirthdate(rs.getDate("BirthDate"));
		seller.setBaseSalary(rs.getDouble("BaseSalary"));
		seller.setDepartment(depart);
		
		return seller;
	}

	private Department instatiateDepartment(ResultSet rs) throws SQLException {
		Department  depart = new Department();
		depart.setId(rs.getInt("DepartmentId"));
		depart.setName(rs.getString("DepName"));
		
		return depart;
	}

	@Override
	public List<Seller> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
