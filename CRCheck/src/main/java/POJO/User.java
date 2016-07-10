package POJO;

import enums.UserLogin;
import enums.UserState;

public class User {
    private String id;
    private String password;
    private String email;
    private String sex;
    private String phone;
    private String address;
    private String userLogin;
    private String userState;

    public User() {
    }

    public User(String id, String password, String email, String sex, String phone, String address, String userLogin, String userState) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.address = address;
        this.userLogin = userLogin;
        this.userState = userState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }
}
