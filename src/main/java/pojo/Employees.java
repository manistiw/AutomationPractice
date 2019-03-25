package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "employee_name",
        "employee_salary",
        "employee_age",
        "profile_image"
})
public class Employees {

    @JsonProperty("id")
    private String id;
    @JsonProperty("employee_name")
    private String employeeName;
    @JsonProperty("employee_salary")
    private String employeeSalary;
    @JsonProperty("employee_age")
    private String employeeAge;
    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("employee_name")
    public String getEmployeeName() {
        return employeeName;
    }

    @JsonProperty("employee_name")
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @JsonProperty("employee_salary")
    public String getEmployeeSalary() {
        return employeeSalary;
    }

    @JsonProperty("employee_salary")
    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    @JsonProperty("employee_age")
    public String getEmployeeAge() {
        return employeeAge;
    }

    @JsonProperty("employee_age")
    public void setEmployeeAge(String employeeAge) {
        this.employeeAge = employeeAge;
    }

    @JsonProperty("profile_image")
    public String getProfileImage() {
        return profileImage;
    }

    @JsonProperty("profile_image")
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("id", id).append("employeeName", employeeName).append("employeeSalary", employeeSalary).append("employeeAge", employeeAge).append("profileImage", profileImage).toString();
    }

}
