package ui;

import Listners.TestListner;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import pojo.CustomerData;
import util.Mappers;

import static org.testng.Assert.*;

@Listeners(TestListner.class)
public class UserTests extends AbstractTest {
    @Autowired
    public PageHeader pageHeader;

    @Autowired
    public LoggedInHomePage loggedInHomePage;

    @Autowired
    public ProductCheckout productCheckout;

    @Autowired
    public CreateAccountPage createAccountPage;
    @Autowired
    public SignInPage signInPage;

    @DataProvider(name = "getCustomerData")
    public Object[][] getCustomerData() {
        CustomerData customer = Mappers.convertJsonToObject(Mappers.getValue("userData"), CustomerData.class);
        return new Object[][]{{customer}};
    }

    @Test(dataProvider = "getCustomerData", description = "create customer with all valid data and validate login is successful")
    public void validateSignIn(CustomerData customerData) {
        pageHeader.clickSignIn();
        signInPage.enterEmailId(customerData.getEmail());
        signInPage.clickCreateAccount();
        createAccountPage.fillAndSubmitForm(customerData);
        assertTrue(loggedInHomePage.VerifyMyHomePage());
        assertTrue(loggedInHomePage.getUserName().equalsIgnoreCase(customerData.getFirstName() + " " + customerData.getLastName()));
        assertTrue(pageHeader.clickLogout());
    }

    @DataProvider(name = "getRegisteredUser")
    public Object[][] getRegisteredUser() {
        CustomerData registeredUserData = Mappers.convertJsonToObject(Mappers.getValue("registeredUserData"), CustomerData.class);
        return new Object[][]{{registeredUserData}};
    }

    @Test(dataProvider = "getRegisteredUser", description = "Validate registered User is able to login in")
    public void validateLogIn(CustomerData customerData) {
        pageHeader.clickSignIn();
        signInPage.login(customerData.getEmail(), customerData.getPassword());
        assertTrue(loggedInHomePage.VerifyMyHomePage());
        assertTrue(loggedInHomePage.getUserName().equalsIgnoreCase(customerData.getFirstName() + " " + customerData.getLastName()));
        assertTrue(pageHeader.clickLogout());
    }

    @DataProvider(name = "getRegisteredUserAndOrderDetails")
    public Object[][] getRegisteredUserAndOrderDetails() {
        CustomerData registeredUserData = Mappers.convertJsonToObject(Mappers.getValue("registeredUserData"), CustomerData.class);
        return new Object[][]{{registeredUserData}};
    }

    @Test(dataProvider = "getRegisteredUserAndOrderDetails", description = "Validate registered user is able to place order and complete checkout.")
    public void validateRegisteredUserPlaceOrderSuccessfully(CustomerData customerData) {
        pageHeader.clickSignIn();
        signInPage.login(customerData.getEmail(), customerData.getPassword());
        pageHeader.selectMenu("Women");
        productCheckout.productCheckOut("Faded Short Sleeve T-shirts");
        assertTrue(productCheckout.verifyFinalStepGreenColor(), "final Step color supposed to be green");
        assertTrue(productCheckout.verifyOrderConfirmationText(), "confirmation text NOT shown");
        assertTrue(productCheckout.verifyFinalStepUrl(), "Url does not reflect order confirmation");
        assertTrue(pageHeader.clickLogout());
    }
}


