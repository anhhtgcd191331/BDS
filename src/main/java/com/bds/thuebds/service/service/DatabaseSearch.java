package com.bds.thuebds.service.service;

import com.bds.thuebds.dto.PostDTO;
import com.bds.thuebds.repository.ApartmentTypeRepository;
import com.bds.thuebds.service.iService.IDatabaseSearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;


@Service
public class DatabaseSearch implements IDatabaseSearch {

	@Autowired
	ApartmentTypeRepository apartmentTypeRepository;

	public List<PostDTO> search(LinkedHashMap<String, Object> map) throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/bds";
		String username = "root";
		String password = "root";
		String tableName = "post_apartment";

		if (map.containsKey("typeOfApartment")) {
			Long apartmentTypeId = apartmentTypeRepository.getApartmentTypeEntityByTypeOfApartment((String) map.get("typeOfApartment")).getId();
			map.put("apartment_type_id", apartmentTypeId);
			map.remove("typeOfApartment");
		}

		final Connection conn = DriverManager.getConnection(url, username, password);
		// Construct the SQL query
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM ").append(tableName).append(" WHERE ");
		int count = 0;
		for (String key : map.keySet()) {
			if (count > 0) {
				sql.append(" AND ");
			}
			if (map.get(key) instanceof String) {
				sql.append(key).append(" LIKE ?");
			} else if (map.get(key) instanceof Number) {
				sql.append(key).append(" = ?");
			}
			count++;
		}
		System.out.println(sql);
		// Prepare the statement and pass the search parameter values
		PreparedStatement stmt = conn.prepareStatement(sql.toString());
		int parameterIndex = 0;
		for (String key : map.keySet()) {
			if (map.get(key) instanceof String) {
				stmt.setString(parameterIndex + 1, "%" + map.get(key) + "%");
			} else if (map.get(key) instanceof Number) {
				stmt.setObject(parameterIndex + 1, map.get(key));
			}
			parameterIndex++;
		}
		System.out.println(stmt);
		List<PostDTO> postDTOS = new ArrayList<>();
//		execute query
		ResultSet rs = stmt.executeQuery();
//		mapping result set to object
		while (rs.next()) {

			PostDTO postDTO = new PostDTO(Long.valueOf(rs.getString(1)),
				rs.getString("post_title"),
				rs.getString("description"),
				Integer.valueOf(rs.getString("number_of_rooms")),
				Double.valueOf(rs.getString("square_area")),
				Double.valueOf(rs.getString("price")),
				rs.getString("details_address"),
				Boolean.parseBoolean(rs.getString("is_sold")),
				apartmentTypeRepository.getById(rs.getLong("apartment_type_id")).getTypeOfApartment(),
				Long.valueOf(rs.getString("author_id")),
				Integer.valueOf(rs.getString("total_like")));
			postDTOS.add(postDTO);
		}
		return postDTOS;
	}
}
