package application;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.dao.DaoFactory;
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
		Department dep = new Department(2, null);
		List<Seller> sellers = sellerDao.findByDepartment(dep);
		for(Seller sel : sellers) {
			System.out.println(sel);
		}
		
		System.out.println("\n=== TEST 3: seller findAll===");
		sellers = sellerDao.findAll();
		for(Seller sel : sellers) {
			System.out.println(sel);
		}
		
		System.out.println("\n=== TEST 4: seller insert===");
		Seller newSeller = new Seller(null, "James", "james@gmail.com", new Date(), 3500.00, dep);
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
	}
}
