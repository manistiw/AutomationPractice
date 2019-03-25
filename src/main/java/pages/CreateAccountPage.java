package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pojo.CustomerData;
import ru.yandex.qatools.allure.annotations.Step;

@Component
public class CreateAccountPage {
    @Autowired
    BasePage basePage;

    private final Logger log = LoggerFactory.getLogger(BasePage.class);

    private static final String genderMale="uniform-id_gender1";
    private static final String genderFemale="uniform-id_gender2";

    public static final By  firstName=By.id("customer_firstname");
    public static final By  lastName=By.id("customer_lastname");
    public static final By  password=By.id("passwd");
    public static final By  dateOfBirth=By.id("days");
    public static final By  monthOfBirth=By.id("months");
    public static final By  yearOfBirth=By.id("years");
    public static final By  company=By.id("company");
    public static final By  addressLine1=By.id("address1");
    public static final By  addressLine2=By.id("address2");
    public static final By  city=By.id("city");
    public static final By  state=By.id("id_state");
    public static final By  postcode=By.id("postcode");
    public static final By  country=By.id("id_country");
    public static final By  otherInfo=By.id("other");
    public static final By  phone=By.id("phone_mobile");
    public static final By  alias=By.id("alias");
    public static final By  submitAccount=By.id("submitAccount");

    @Step("Select Gender in Create Account Page with gender: {0}")
    public void selectGender(String gender){
        switch (gender) {
            case "Mr.":
                basePage.clickElement(By.id(genderMale));
                break;
            case "Mrs.":
                basePage.clickElement(By.id(genderFemale));
                break;
            default:
                log.error("Please provide either Mr. or Mrs.  as values");
        }
    }

    @Step("fill Create Account form with customer Details")
    public void fillAndSubmitForm(CustomerData customer){
        selectGender(customer.getTitle());
        basePage.setText(firstName,customer.getFirstName());
        basePage.setText(lastName,customer.getLastName());
        basePage.setText(password,customer.getPassword());
        basePage.selectDropDownValue(dateOfBirth,customer.getDateOfBirth());
        basePage.selectDropDownValue(monthOfBirth,customer.getMonthOfBirth());
        basePage.selectDropDownValue(yearOfBirth,customer.getYearOfBirth());
        basePage.setText(company,customer.getCompany());
        basePage.setText(addressLine1,customer.getAddressLine1());
        basePage.setText(addressLine2,customer.getAddressLine2());
        basePage.setText(city,customer.getCity());
        basePage.selectDropDownVisibleText(state,customer.getState());
        basePage.setText(postcode,customer.getZipCode());
        basePage.selectDropDownVisibleText(country,customer.getCountry());
        basePage.setText(otherInfo,"More info");
        basePage.setText(phone,customer.getPhone());
        basePage.setText(alias,customer.getAddressAlias());
        basePage.clickElement(submitAccount);
    }
}
