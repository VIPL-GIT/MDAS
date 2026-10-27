/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBeans;

/**
 *
 * @author dell
 */
public class UsersBean {
    
    private String userId;
    private String password;
    private int userType;
    private int isActive;
    private String firstName;
    private String lastName;
    private String emailId;
    private String mobileNo;
    private String roleType;
    private String officeId;
    private String officeTypeId;
    private String loc_type_access;
    
    

    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    /**
     * @return the officeId
     */
    public String getOfficeId() {
        return officeId;
    }

    /**
     * @param officeId the officeId to set
     */
    public void setOfficeId(String officeId) {
        this.officeId = officeId;
    }

    /**
     * @return the officeTypeId
     */
    public String getOfficeTypeId() {
        return officeTypeId;
    }

    /**
     * @param officeTypeId the officeTypeId to set
     */
    public void setOfficeTypeId(String officeTypeId) {
        this.officeTypeId = officeTypeId;
    }

    /**
     * @return the loc_type_access
     */
    public String getLoc_type_access() {
        return loc_type_access;
    }

    /**
     * @param loc_type_access the loc_type_access to set
     */
    public void setLoc_type_access(String loc_type_access) {
        this.loc_type_access = loc_type_access;
    }
    
    
}
