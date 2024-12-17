package com.edubridge;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Kettle {
	private int id; // unique
	private String brand;
	private String material;
	private float capacity;
	private double price;

	public Kettle(int id, String brand, String material, float capacity, double price) {
		super();
		this.id = id;
		this.brand = brand;
		this.material = material;
		this.capacity = capacity;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public float getCapacity() {
		return capacity;
	}

	public void setCapacity(float capacity) {
		this.capacity = capacity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Kettle [id=" + id + ", brand=" + brand + ", material=" + material + ", capacity=" + capacity
				+ ", price=" + price + "]";
	}

	private static List<Kettle> kettles = new ArrayList<Kettle>();
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int choice;

		System.out.println("***************Kettle Management System************");
		while (true) {
			System.out.println("*****Menu******");
			System.out.println("1. Create kettle");
			System.out.println("2. Retrive all kettles");
			System.out.println("3. Retrive kettle based on id");
			System.out.println("4. Update kettle");
			System.out.println("5. Delete kettle");
			System.out.println("6. Quit");
			System.out.println("Enter your choice :");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter kettle id :");
				int id = sc.nextInt();
				System.out.println("Enter kettle brand :");
				sc.nextLine();
				String brand = sc.nextLine();
				System.out.println("Enter kettle material :");
				String material = sc.nextLine();
				System.out.println("Enter kettle capacity :");
				float capacity = sc.nextFloat();
				System.out.println("Enter kettle price :");
				double price = sc.nextDouble();

				boolean status = createKettle(id, brand, material, capacity, price);
				if (status) {
					System.out.println("Kettle creation successful!");
				} else {
					System.out.println("Id already exists");
				}
				break;

			case 2:
				if (kettles.size() != 0) {
					kettles.stream().forEach(kettle -> System.out.println(kettle));
				} else {
					System.out.println("No kettles available");
				}
				break;

			case 3:
				System.out.println("Enter kettle id :");
				id = sc.nextInt();
				Kettle kettle = retrieveKettle(id);
				if (kettle != null) {
					System.out.println(kettle);
				} else {
					System.out.println("Invalid kettle id");
				}
				break;

			case 4:
				System.out.println("Enter kettle id :");
				id = sc.nextInt();
				System.out.println("Enter kettle capacity :");
				capacity = sc.nextFloat();
				System.out.println("Enter kettle price :");
				price = sc.nextDouble();

				status = updateKettle(id, capacity, price);
				if (status) {
					System.out.println("Updation successful!");
				} else {
					System.out.println("Invalid id");
				}
				break;

			case 5:
				System.out.println("Enter kettle id :");
				id = sc.nextInt();
				status = deleteKettle(id);
				if (status) {
					System.out.println("Deletion successful");
				} else {
					System.out.println("Invalid Id");
				}
				break;

			case 6:
				System.out.println("Exiting...");
				System.exit(0);
			default:
				System.out.println("Invalid choice");
			}
		}
	}

	public static boolean createKettle(int id, String brand, String material, float capacity, double price) {
		Optional<Kettle> findFirst = kettles.stream().filter(kettle -> kettle.getId() == id).findFirst();
		if (findFirst.isEmpty()) {
			Kettle kettle = new Kettle(id, brand, material, capacity, price);
			return kettles.add(kettle);
		}
		return false;
	}

	public static Kettle retrieveKettle(int id) {
		Optional<Kettle> findFirst = kettles.stream().filter(kettle -> kettle.getId() == id).findFirst();
		return findFirst.orElse(null);
	}

	public static boolean updateKettle(int id, float capacity, double price) {
		Optional<Kettle> findFirst = kettles.stream().filter(kettle -> kettle.getId() == id).map(kettle -> {
			kettle.setCapacity(capacity);
			kettle.setPrice(price);
			return kettle;
		}).findFirst();
		return findFirst.isPresent();
	}

	public static boolean deleteKettle(int id) {
		Optional<Kettle> findFirst = kettles.stream().filter(kettle -> kettle.getId() == id).findFirst();
		Kettle kettle = findFirst.orElse(null);
		return kettles.remove(kettle);
	}
}
