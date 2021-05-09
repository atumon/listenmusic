package org.sky.listenmusic.Bean;

public class User {

    private Long UserId;
    private String UserName;
    private String UserPassword;
    private String UserBrithday;
    private String UserSex;
    private String UserRegion;
    private String UserDescription;


    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserBrithday() {
        return UserBrithday;
    }

    public void setUserBrithday(String userBrithday) {
        UserBrithday = userBrithday;
    }

    public String getUserSex() {
        return UserSex;
    }

    public void setUserSex(String userSex) {
        UserSex = userSex;
    }

    public String getUserRegion() {
        return UserRegion;
    }

    public void setUserRegion(String userRegion) {
        UserRegion = userRegion;
    }

    public String getUserDescription() {
        return UserDescription;
    }

    public void setUserDescription(String userDescription) {
        UserDescription = userDescription;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", UserName='" + UserName + '\'' +
                ", UserPassword='" + UserPassword + '\'' +
                ", UserBrithday='" + UserBrithday + '\'' +
                ", UserSex='" + UserSex + '\'' +
                ", UserRegion='" + UserRegion + '\'' +
                ", UserDescription='" + UserDescription + '\'' +
                '}';
    }

    public User(Long userId, String userName, String userPassword, String userBrithday, String userSex, String userRegion, String userDescription) {
        UserId = userId;
        UserName = userName;
        UserPassword = userPassword;
    }
}
