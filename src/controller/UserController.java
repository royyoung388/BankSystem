package controller;

import bean.user.User;
import model.UserModel;
import model.UserModelImpl;

public class UserController {
    UserModelImpl userModel;
    public UserController() {
        userModel=new UserModelImpl();
    }

    public User login(String userName, String pwd){
        return userModel.login(userName,pwd);
    }

    public boolean signUp(String userName,String pwd,int type){
        if(userModel.isUserExists(userName)){
            return false;
        }
        return userModel.signUp(type,userName,pwd);
    }


}
