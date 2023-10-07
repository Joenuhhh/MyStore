package com.gcu.Modelss;

//Bread and butter of the app, this is the item displayed on the cards in the store. Will likely break everything if anything is changed. - Jonah

public class ProductModel {
	private int ID;
	private String Name;
	private String Category;
	private String Description;
	private int quantity;
	private String Manufacturer;
	private double Price;
	
	public ProductModel() {
		// Default constructor with no arguments
	}
	public ProductModel(int ID, String Name, String Category, String Description, int quantity, String Manufacturer, double Price) {
		this.ID = ID;
		this.Name = Name;
		this.Category = Category;
		this.Description = Description;
		this.quantity = quantity;
		this.Manufacturer = Manufacturer;
		this.Price = Price;
	}
	



	
	public int getID() {
		return ID;
	}
	
	public void setID(int id) {
		this.ID = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getManufacturer() {
		return Manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		Manufacturer = manufacturer;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}
    @Override
    public String toString() {
        return "ProductModel{" +
                "ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Category='" + Category + '\'' +
                ", Description='" + Description + '\'' +
                ", quantity=" + quantity +
                ", Manufacturer='" + Manufacturer + '\'' +
                ", Price=" + Price +
                '}';
    }
}
