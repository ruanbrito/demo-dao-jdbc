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
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {

	private Connection conn;

	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("INSERT INTO department" + "(Name) " + "Values(?)",
					Statement.RETURN_GENERATED_KEYS);

			pst.setString(1, obj.getName());

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = pst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}

	}

	@Override
	public void update(Department obj) {
		PreparedStatement pst = null;

		try {
			pst = conn.prepareStatement("UPDATE department " + "SET Name = ? " + "WHERE id = ?");
			pst.setString(1, obj.getName());
			pst.setInt(2, obj.getId());

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement("DELETE FROM department WHERE Id = ?");

			pst.setInt(1, id);

			int rowsAffected = pst.executeUpdate();

			if (rowsAffected == 0) {
				throw new DbException("No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(pst);
		}

	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT department.* FROM department " + "WHERE Id = ?");
			pst.setInt(1, id);

			rs = pst.executeQuery();

			if (rs.next()) {
				Department depart = instantiateDepartment(rs);
				return depart;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {

			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement("SELECT department.* FROM department " + "ORDER BY Id");

			rs = pst.executeQuery();

			List<Department> list = new ArrayList<>();

			while (rs.next()) {

				Department dep = instantiateDepartment(rs);

				list.add(dep);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pst);
		}
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department depart = new Department();
		depart.setId(rs.getInt("Id"));
		depart.setName(rs.getString("Name"));

		return depart;
	}

}
