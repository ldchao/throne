package model;

import enums.Power;

/**
 * Created by zs on 2016/7/11.
 */
public class UserModel {
    private String id;
    private Power power;
    public UserModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Power getPower() {
        return power;
    }

    public void setPower(Power power) {
        this.power = power;
    }

}
