import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import pojo.CustomerData;
import ui.AbstractTest;
import util.Mappers;

import static org.testng.Assert.assertTrue;

public class UserTestsTest extends AbstractTest {
    @Autowired
    public PageHeader pageHeader;

    @DataProvider(name = "getCustomerData")
    public Object[][] getCustomerData(){
        CustomerData customer=Mappers.convertJsonToObject(Mappers.getValue("userData"),CustomerData.class);
        return new Object[][]{{customer}};
    }

    @Test(dataProvider = "getCustomerData",description = "create customer with all valid data and validate login is successful")
    public void validateSignIn(CustomerData customerData){
        System.out.println("First Test");
        pageHeader.clickSignIn();
    }

    @DataProvider(name="getRegisteredUser")
    public Object[][] getRegisteredUser(){
        CustomerData registeredUserData=Mappers.convertJsonToObject(Mappers.getValue("registeredUserData"),CustomerData.class);
        return new Object[][]{{registeredUserData}};
    }

    @Test(dataProvider = "getRegisteredUser",description = "Validate registered User is able to login in")
    public void validateLogIn(CustomerData customerData){
        System.out.println("Second Test");
        pageHeader.clickSignIn();

    }

    @DataProvider(name="getRegisteredUserAndOrderDetails")
    public Object[][] getRegisteredUserAndOrderDetails(){
        CustomerData registeredUserData=Mappers.convertJsonToObject(Mappers.getValue("registeredUserData"),CustomerData.class);
        return new Object[][]{{registeredUserData}};
    }

    @Test(dataProvider = "getRegisteredUserAndOrderDetails",description = "Validate registered user is able to place order and complete checkout.")
    public void validateRegisteredUserPlaceOrderSuccessfully(CustomerData customerData){
        System.out.println("Third Test");
        pageHeader.clickSignIn();
    }

    @AfterMethod()
    public void logoutIfSuccessfulLogin(){
        System.out.println("AfterMethod");
    }

}
