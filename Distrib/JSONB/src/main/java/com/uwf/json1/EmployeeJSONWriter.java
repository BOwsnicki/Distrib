package com.uwf.json1;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue; 
import javax.json.JsonWriter;
import javax.json.JsonObjectBuilder;
import javax.json.JsonArrayBuilder;


public class EmployeeJSONWriter {

	public static void main(String[] args) throws FileNotFoundException {

		Employee emp = createEmployee();

		JsonObjectBuilder empBuilder = Json.createObjectBuilder();
		JsonObjectBuilder addressBuilder = Json.createObjectBuilder();
		JsonArrayBuilder phoneNumBuilder = Json.createArrayBuilder();

		for (long phone : emp.getPhoneNumbers()) {
			phoneNumBuilder.add(phone);
		}
		
		addressBuilder.add("street", emp.getAddress().getStreet())
						.add("city", emp.getAddress().getCity())
							.add("zipcode", emp.getAddress().getZipcode());
		
		empBuilder.add("id", emp.getId())
					.add("name", emp.getName())
						.add("permanent", emp.isPermanent())
							.add("role", emp.getRole());
		
		empBuilder.add("phoneNumbers", phoneNumBuilder);
		empBuilder.add("address", addressBuilder);
		
		JsonObject empJsonObject = empBuilder.build();
		
		System.out.println("Employee JSON String\n"+empJsonObject);
		
		//write to file
		OutputStream os = new FileOutputStream("emp.txt");
		JsonWriter jsonWriter = Json.createWriter(os);
		/**
		 * We can get JsonWriter from JsonWriterFactory also
		JsonWriterFactory factory = Json.createWriterFactory(null);
		jsonWriter = factory.createWriter(os);
		*/
		jsonWriter.writeObject(empJsonObject);
		jsonWriter.close();
	}
	

	public static Employee createEmployee() {

		Employee emp = new Employee();
		emp.setId(100);
		emp.setName("David");
		emp.setPermanent(false);
		emp.setPhoneNumbers(new long[] { 1234567, 9876543 });
		emp.setRole("Manager");

		Address add = new Address();
		add.setCity("Baltimore");
		add.setStreet("1234 ABC St");
		add.setZipcode(21223);
		emp.setAddress(add);

		return emp;
	}

}
