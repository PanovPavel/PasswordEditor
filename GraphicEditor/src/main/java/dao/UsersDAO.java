package dao;

import enity.Users;

import java.sql.SQLException;
import java.util.List;

public interface UsersDAO {
    //creat

    void add(Users users) throws SQLException;
    //read
    List<Users> getAllUsers() throws SQLException;
    //Users getById(int id);

    //update
    //void update(Users users);

    //delete
    //void remove(Users users);

}
