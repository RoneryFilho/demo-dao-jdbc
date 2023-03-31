package application;

import java.util.Date;

import models.entities.Department;
import models.entities.Seller;

public class Program {
	
	public static void main(String[] args) {
		
		Department obj = new Department(1, "Finances");
		System.out.println(obj);
		
		Seller seller = new Seller(15, "James", "james@gmail.com", new Date(), 3000.00, obj);
		System.out.println(seller);
	}
}