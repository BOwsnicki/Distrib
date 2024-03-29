/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uwf.json1;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author bernd
 */
public class JSONBindings {

    public static Employee createEmployee() {

        Employee emp = new Employee();
        emp.setId(100);
        emp.setName("David");
        emp.setPermanent(false);
        emp.setPhoneNumbers(new long[]{1234567, 9876543});
        emp.setRole("Manager");

        Address add = new Address();
        add.setCity("Baltimore");
        add.setStreet("1234 ABC St");
        add.setZipcode(21223);
        emp.setAddress(add);

        return emp;
    }

    // Convert Employee instance to JSON String
    private static String emp2JSON(Employee emp) {
        Jsonb jsonb = JsonbBuilder.create();
        String jsonEmp = jsonb.toJson(emp);
        return jsonEmp;
    }

    // Convert JSON String to Employee instance
    private static Employee json2EMP(String json) {
        Jsonb jsonb = JsonbBuilder.create();
        return jsonb.fromJson(json, Employee.class);
    }

    // Demo!
    public static void main(String[] args) {
        Employee emp = createEmployee();
        String empJ = emp2JSON(emp);
        System.out.println(empJ);
        Employee emp2 = json2EMP(empJ);
        System.out.println(emp2);
    }
}
