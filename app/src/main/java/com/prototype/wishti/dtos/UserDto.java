package com.prototype.wishti.dtos;


public class UserDto {

    String userName;

    boolean isSpecialist;

    byte[] imageBytes;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSpecialist() {
        return isSpecialist;
    }

    public void setIsSpecialist(boolean isSpecialist) {
        this.isSpecialist = isSpecialist;
    }

    public byte[] getImageBytes() {
        return imageBytes;
    }

    public void setImageBytes(byte[] imageBytes) {
        this.imageBytes = imageBytes;
    }
}
