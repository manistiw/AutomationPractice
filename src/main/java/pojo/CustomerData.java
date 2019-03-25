package pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Title",
        "firstName",
        "lastName",
        "email",
        "password",
        "dateOfBirth",
        "monthOfBirth",
        "yearOfBirth",
        "company",
        "addressLine1",
        "addressLine2",
        "city",
        "state",
        "zipCode",
        "country",
        "phone",
        "addressAlias"
})
public class CustomerData {

    @JsonProperty("Title")
    private String title;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("email")
    private String email;
    @JsonProperty("password")
    private String password;
    @JsonProperty("dateOfBirth")
    private String dateOfBirth;
    @JsonProperty("monthOfBirth")
    private String monthOfBirth;
    @JsonProperty("yearOfBirth")
    private String yearOfBirth;
    @JsonProperty("company")
    private String company;
    @JsonProperty("addressLine1")
    private String addressLine1;
    @JsonProperty("addressLine2")
    private String addressLine2;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("zipCode")
    private String zipCode;
    @JsonProperty("country")
    private String country;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("addressAlias")
    private String addressAlias;

    @JsonProperty("Title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("Title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    @JsonProperty("password")
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty("dateOfBirth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("dateOfBirth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("monthOfBirth")
    public String getMonthOfBirth() {
        return monthOfBirth;
    }

    @JsonProperty("monthOfBirth")
    public void setMonthOfBirth(String monthOfBirth) {
        this.monthOfBirth = monthOfBirth;
    }

    @JsonProperty("yearOfBirth")
    public String getYearOfBirth() {
        return yearOfBirth;
    }

    @JsonProperty("yearOfBirth")
    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("addressLine1")
    public String getAddressLine1() {
        return addressLine1;
    }

    @JsonProperty("addressLine1")
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    @JsonProperty("addressLine2")
    public String getAddressLine2() {
        return addressLine2;
    }

    @JsonProperty("addressLine2")
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty("zipCode")
    public String getZipCode() {
        return zipCode;
    }

    @JsonProperty("zipCode")
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @JsonProperty("addressAlias")
    public String getAddressAlias() {
        return addressAlias;
    }

    @JsonProperty("addressAlias")
    public void setAddressAlias(String addressAlias) {
        this.addressAlias = addressAlias;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("title", title).append("firstName", firstName).append("lastName", lastName).append("email", email).append("password", password).append("dateOfBirth", dateOfBirth).append("monthOfBirth", monthOfBirth).append("yearOfBirth", yearOfBirth).append("company", company).append("addressLine1", addressLine1).append("addressLine2", addressLine2).append("city", city).append("state", state).append("zipCode", zipCode).append("country", country).append("phone", phone).append("addressAlias", addressAlias).toString();
    }

}