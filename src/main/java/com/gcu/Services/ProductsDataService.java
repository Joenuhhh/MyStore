package com.gcu.Services;

import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.Interfaces.DataAccessInterface;
import com.gcu.Modelss.ProductModel;
import com.gcu.Modelss.UserModel;

public class ProductsDataService implements DataAccessInterface {
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	
	//All database access logic for products and order_logs is held here. Methods are named accordingly and self explanatory. - Jonah

	@Override
	public List<ProductModel> findAllProducts() {
		String sql = "SELECT * FROM PRODUCT";
		List<ProductModel> results = new ArrayList<ProductModel>();
		try {
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while (srs.next()) {
				results.add(new ProductModel(srs.getInt("ID"), srs.getString("Name"), srs.getString("Category"),
						srs.getString("Description"), srs.getInt("Quantity"), srs.getString("Manufacturer"),
						srs.getDouble("Price")));

			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return results;
	}

	public ProductsDataService(DataSource dataSource) {
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}

	@SuppressWarnings("deprecation")
	@Override
	public ProductModel findByID(int id) {
		String sql = "SELECT * FROM PRODUCT WHERE ID = ?";
		try {
			return jdbcTemplate.queryForObject(sql, new Object[] { id },
					(rs, rowNum) -> new ProductModel(rs.getInt("ID"), rs.getString("Name"), rs.getString("Category"),
							rs.getString("Description"), rs.getInt("Quantity"), rs.getString("Manufacturer"),
							rs.getDouble("Price")));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean create(ProductModel product) {
		String sql = "INSERT INTO PRODUCT (Name, Category, Description, Quantity, Manufacturer, Price) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			int rows = jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getDescription(),
					product.getQuantity(), product.getManufacturer(), product.getPrice());
			return rows == 1;
		} catch (Exception E) {
			E.printStackTrace();
		}
		return false;
	}

	@Override
	public void update(ProductModel product) {
		// Prepare the SQL update statement
		String sql = "UPDATE product SET Name=?, Category=?, Description=?, Quantity=?, Manufacturer=?, Price=? WHERE ID=?";

		try {
			// Execute the update query using JdbcTemplate's update method
			jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getDescription(),
					product.getQuantity(), product.getManufacturer(), product.getPrice(), product.getID());
		} catch (Exception E) {
			E.printStackTrace();
		}
	}

	public int findProductIdByAttributes(ProductModel product) {
		String sql = "SELECT ID FROM product WHERE Name = ? AND Category = ? AND Description = ? AND Quantity = ? AND Manufacturer = ? AND Price = ?";
		try {
			return jdbcTemplate.queryForObject(sql, Integer.class, product.getName(), product.getCategory(),
					product.getDescription(), product.getQuantity(), product.getManufacturer(), product.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0; // Return 0 or a default value if the product is not found
	}

	@Override
	public void delete(int productId) {
		String sql = "DELETE FROM product WHERE ID = ?";

		try {
			int rowsAffected = jdbcTemplate.update(sql, productId);
			if (rowsAffected > 0) {
				System.out.println("Product with ID " + productId + " deleted successfully.");
			} else {
				System.out.println("No product found with ID " + productId + ". Deletion failed.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductModel> findByName(String Name) {
		String sql = "SELECT * FROM PRODUCT WHERE NAME LIKE '%" + Name + "%' || CATEGORY LIKE '%" + Name + "%';";
		List<ProductModel> results = new ArrayList<ProductModel>();
		try {
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while (srs.next()) {
				results.add(new ProductModel(srs.getInt("ID"), srs.getString("Name"), srs.getString("Category"),
						srs.getString("Description"), srs.getInt("Quantity"), srs.getString("Manufacturer"),
						srs.getDouble("Price")));

			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return results;
	}

	public void addProductToDB(ProductModel product) {
		String sql = "INSERT INTO PRODUCT (Name, Category, Description, Quantity, Manufacturer, Price) VALUES (?, ?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, product.getName(), product.getCategory(), product.getDescription(),
					product.getQuantity(), product.getManufacturer(), product.getPrice());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertLog(UserModel user, List<ProductModel> products) {
		String itemsBought = "";
		double total = 0;
		for (ProductModel product : products) {
			itemsBought = itemsBought
					.concat(product.getName() + " " + product.getCategory() + " " + product.getDescription());
			total += product.getPrice();
		}

		String sql = "INSERT INTO order_logs (Email, FirstName, LastName, Address, ItemsBought, Total, timestamp) VALUES (?, ?, ?, ?, ?, ?, NOW())";
		try {
			int rows = jdbcTemplate.update(sql, user.getEmail(), user.getFirstName(), user.getLastName(),
					user.getAddress(), itemsBought, total);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int getNextOrderId() {
		String sql = "SELECT MAX(ID) FROM order_logs";
		try {
			Integer maxId = jdbcTemplate.queryForObject(sql, Integer.class);
			if (maxId != null) {
				return maxId + 1;
			} else {
				return 1; // In case the table is empty
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1; // Error code or any suitable handling
		}
	}

}
