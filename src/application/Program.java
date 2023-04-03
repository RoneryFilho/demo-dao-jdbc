package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		System.out.println("=== TEST 1: seller findById===");
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);
		
		System.out.println("\n=== TEST 2: seller findByDepartment===");
		Department depSel = new Department(2, null);
		List<Seller> sellers = sellerDao.findByDepartment(depSel);
		for(Seller sel : sellers) {
			System.out.println(sel);
		}
		
		System.out.println("\n=== TEST 3: seller findAll===");
		sellers = sellerDao.findAll();
		for(Seller sel : sellers) {
			System.out.println(sel);
		}
		
		System.out.println("\n=== TEST 4: seller insert===");
		Seller newSeller = new Seller(null, "James", "james@gmail.com", new Date(), 3500.00, depSel);
		sellerDao.insert(newSeller);
		System.out.println("Inserted! New Id: " + newSeller.getId());
		
		System.out.println("\n=== TEST 5: seller update===");
		seller = sellerDao.findById(1);
		seller.setName("Martha Waine");
		sellerDao.update(seller);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 6: seller delete===");
		System.out.print("Enter Id to delete: ");
		int id = sc.nextInt();
		sellerDao.deleteById(id);
		System.out.println("Delete completed!");
		
		DepartmentDao departmentDao = DaoFactory.createDepartmentDao();
		
		System.out.println("\n=== TEST 1: department findById===");
		Department dep = departmentDao.findById(3);
		System.out.println(dep);
		
		System.out.println("\n=== TEST 2: department findAll===");
		List<Department> departments = departmentDao.findAll();
		for(Department depart : departments){
		System.out.println(depart);
		}
		
		System.out.println("\n=== TEST 3: department insert===");
		Department newDep = new Department(null, "Sales");
		departmentDao.insert(newDep);
		System.out.println("Inserted! New Id: " + newDep.getId());
		
		System.out.println("\n=== TEST 4: department update===");
		dep = departmentDao.findById(4);
		dep.setName("Vehicles");
		departmentDao.update(dep);
		System.out.println("Update completed!");
		
		System.out.println("\n=== TEST 5: department delete===");
		System.out.println("Enter the Id to delete: ");
		int idDep = sc.nextInt();
		departmentDao.deleteById(idDep);
		System.out.println("Delete completed!");
		
		sc.close();
	}
}
