package PkProject.DAO;

import PkProject.Entity.UserEntity;

public class UserDAO {
    public static UserEntity login(UserEntity user) {
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        
        return new UserEntity();
    }
}
