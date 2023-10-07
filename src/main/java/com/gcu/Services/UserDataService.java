package com.gcu.Services;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.Interfaces.UserDataInterface;
import com.gcu.Modelss.LoginModel;
import com.gcu.Modelss.ProductModel;
import com.gcu.Modelss.UserModel;

public class UserDataService implements UserDataInterface {
	@Autowired
	private DataSource datasource;
	private JdbcTemplate jdbcTemplate;
	private List<UserModel> results;
	
	//All user related data access from the database is here. Methods are named accordingly and self explanatory. - Jonah

	@Override
	public List<UserModel> findAllUsers() {
		String sql = "SELECT * FROM USERS";
		try {
			SqlRowSet srs = jdbcTemplate.queryForRowSet(sql);
			while (srs.next()) {
				results.add(new UserModel(srs.getString("FirstName"), srs.getString("LastName"),
						srs.getString("Password"), srs.getString("Address"), srs.getString("Email")));

			}
		} catch (Exception E) {
			E.printStackTrace();
		}
		return results;
	}

	@Override
	public boolean CheckUserModel(LoginModel model) {

		List<UserModel> results2 = findAllUsers();
		// if the user exists inside the database, return true. this will be used as
		// login functionality
		for (int x = 0; x < results2.size(); x++) {
			if (model.getUsername().equals(results2.get(x).getEmail())
					&& model.getPassword().equals(results2.get(x).getPassword())) {
				return true;
			}
		}

		return false;
	}

	public UserDataService(DataSource dataSource) {
		this.datasource = dataSource;
		this.jdbcTemplate = new JdbcTemplate(datasource);
		this.results = new ArrayList<>();
	}

	@Override
	public void addUsertoList(UserModel model) {

		String sql = "INSERT INTO USERS (FirstName, LastName, Password, Address, Email) VALUES (?, ?, ?, ?, ?)";
		try {
			jdbcTemplate.update(sql, model.getFirstName(), model.getLastName(), model.getPassword(), model.getAddress(),
					model.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public UserModel getCurrentUserModel(LoginModel model) {

		List<UserModel> results2 = findAllUsers();
		// if the user exists inside the database, return true. this will be used as
		// login functionality
		for (int x = 0; x < results2.size(); x++) {
			if (model.getUsername().equals(results2.get(x).getEmail())
					&& model.getPassword().equals(results2.get(x).getPassword())) {
				return results2.get(x);
			}
		}

		return null;
	}

}
