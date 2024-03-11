package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		SellerDao sellerDao = DaoFactory.createSellerDao();

		// Seller seller = sellerDao.findById(6);
		Department depart = new Department(2, null);
		//List<Seller> listSeller = sellerDao.findByDepartment(depart);
		//List<Seller> listSeller = sellerDao.findAll();

		//for (Seller list : listSeller) {
			//System.out.println(list);
		//}
		Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, depart);
		
		sellerDao.insert(newSeller);
		System.out.println("Inserted! new id = " + newSeller.getId());
	}

}
