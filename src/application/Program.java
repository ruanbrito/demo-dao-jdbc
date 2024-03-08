package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();

		// Seller seller = sellerDao.findById(6);
		Department depart = new Department(2, null);
		List<Seller> listSeller = sellerDao.findByDepartment(depart);

		for (Seller list : listSeller) {
			System.out.println(list);
		}
	}

}
