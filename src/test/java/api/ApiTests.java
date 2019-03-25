package api;

import apicomponent.RestAssuredClient;
import io.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pojo.EmployeeCreateData;
import pojo.Employees;
import ui.AbstractTest;
import util.Mappers;

import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class ApiTests extends AbstractTest {
    @Autowired
    public RestAssuredClient rest;

    private static final String GET_ALL_EMPLOYEE = "employees";
    private static final String POST_EMPLOYEE = "create";
    private static final String GET_EMPLOYEE = "employee/%s";
    private static final String PUT_EMPLOYEE = "update/%s";
    private static final String DELETE_EMPLOYEE = "update/%s";

    @Test(description = "Test validating schema for GET")
    public void ValidateSchemaOnGetEmployee(){
        ValidatableResponse data = rest.doGet(GET_ALL_EMPLOYEE);
        List<Employees> employees=data.extract().body().htmlPath().getList("body", Employees.class);
    }

    @DataProvider(name="getEmployeeToCreate")
    public Object[][] getEmployeeToCreate(){
        EmployeeCreateData employee= Mappers.convertJsonToObject(Mappers.getValue("employeeCreateData"),EmployeeCreateData.class);
        JSONObject request=Mappers.getJSONObject(employee);
        request.put("salary", RandomStringUtils.random(3, false, true));
        request.put("age", RandomStringUtils.random(2, false, true));
        return new Object[][]{{request}};
    }

    @Test(dataProvider = "getEmployeeToCreate",description = "Validate create employee api working as expected")
    public void validateCreateEmployee(JSONObject employee){
        ValidatableResponse response = rest.doPost(POST_EMPLOYEE,employee);
        response.statusCode(200);
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        EmployeeCreateData employeeCreated=Mappers.convertJsonToObject(jsonString, EmployeeCreateData.class);
        Assert.assertTrue(employeeCreated.getSalary().equals(employee.get("salary")));
        Assert.assertTrue(employeeCreated.getAge().equals(employee.get("age")));
        Assert.assertTrue(employeeCreated.getName().equals(employee.get("name")));
    }

    @DataProvider(name="getNewlyCreatedEmployee")
    public Object[][] getNewlyCreatedEmployee(){
        EmployeeCreateData employeeObj= Mappers.convertJsonToObject(Mappers.getValue("employeeCreateData"),EmployeeCreateData.class);
        JSONObject employeeJSONObj=Mappers.getJSONObject(employeeObj);
        employeeJSONObj.put("salary", RandomStringUtils.random(3, false, true));
        employeeJSONObj.put("age", RandomStringUtils.random(2, false, true));
        ValidatableResponse response = rest.doPost(POST_EMPLOYEE,employeeJSONObj);
        response.statusCode(200);
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        EmployeeCreateData employeeCreatedObj=Mappers.convertJsonToObject(jsonString, EmployeeCreateData.class);
        return new Object[][]{{employeeCreatedObj}};
    }

    @Test(dataProvider = "getNewlyCreatedEmployee",description = "Validate GET employee api return True for Valid Existing Employees Id")
    public void validateGetEmployee(EmployeeCreateData employeeCreated){
        ValidatableResponse response = rest.doGet(String.format(GET_EMPLOYEE,employeeCreated.getId()));
        response.statusCode(200);
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        Employees getEmployeeResponse=Mappers.convertJsonToObject(jsonString, Employees.class);
        Assert.assertTrue(employeeCreated.getSalary().equals(getEmployeeResponse.getEmployeeSalary()));
        Assert.assertTrue(employeeCreated.getAge().equals(getEmployeeResponse.getEmployeeAge()));
        Assert.assertTrue(employeeCreated.getName().equals(getEmployeeResponse.getEmployeeName()));
        Assert.assertTrue(employeeCreated.getId().equals(getEmployeeResponse.getId()));
    }

    @Test(dataProvider = "getNewlyCreatedEmployee",description = "Validate PUT employee api return Valid response")
    public void validatePutEmployee(EmployeeCreateData employeeObj){
        String name=employeeObj.getName()+"Updated";
        employeeObj.setName(name);
        ValidatableResponse response = rest.doPut(Mappers.getJSONObject(employeeObj).toString(),String.format(PUT_EMPLOYEE,employeeObj.getId()));
        response.statusCode(200);
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        EmployeeCreateData getEmployeeResponse=Mappers.convertJsonToObject(jsonString, EmployeeCreateData.class);
        Assert.assertTrue(employeeObj.getSalary().equals(getEmployeeResponse.getSalary()));
        Assert.assertTrue(employeeObj.getAge().equals(getEmployeeResponse.getAge()));
        Assert.assertTrue(employeeObj.getName().equals(getEmployeeResponse.getName()));
    }

    @Test(dataProvider = "getNewlyCreatedEmployee",description = "Validate DELETE employee api return True for Valid response")
    public void validateDeleteEmployee(EmployeeCreateData employeeObj){
        ValidatableResponse response = rest.doDelete(String.format(PUT_EMPLOYEE,employeeObj.getId()));
        response.statusCode(200);
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        Employees getEmployeeResponse=Mappers.convertJsonToObject(jsonString, Employees.class);
        Assert.assertTrue(employeeObj.getSalary().equals(getEmployeeResponse.getEmployeeSalary()));
        Assert.assertTrue(employeeObj.getAge().equals(getEmployeeResponse.getEmployeeAge()));
        Assert.assertTrue(employeeObj.getName().equals(getEmployeeResponse.getEmployeeName()));
        Assert.assertTrue(employeeObj.getId().equals(getEmployeeResponse.getId()));
    }

    public JSONObject getJsonResponse(ValidatableResponse response){
        String jsonString=response.assertThat().extract().htmlPath().get("body").toString();
        return Mappers.convertJsonToObject(jsonString,JSONObject.class);
    }
}
