package application;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class Program {

	public static void main(String[] args) {
		//SellerDao sellerDao = DaoFactory.createSellerDao();
		DepartmentDao departDao = DaoFactory.createDepartmentDao();
		
		System.out.println("==== teste 001 === Busca de deprtamento por ID");
		Department dep = departDao.findById(1);
		System.out.println(dep);
		
		System.out.println("==== teste 002 === Buscar todos os deprtamentos");
		List<Department> list = departDao.findAll();
		
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		departDao.deleteById(5);
		// Seller seller = sellerDao.findById(6);
		//Department depart = new Department(2, null);
		//List<Seller> listSeller = sellerDao.findByDepartment(depart);
		//List<Seller> listSeller = sellerDao.findAll();

		//for (Seller list : listSeller) {
			//System.out.println(list);
		//}
		//Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, depart);
		
		//sellerDao.insert(newSeller);
		//System.out.println("Inserted! new id = " + newSeller.getId());
	}

}
