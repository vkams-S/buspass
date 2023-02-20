package com.amazon.busPassManagement.Services;

import com.amazon.busPassManagement.DB.UserDAO;
import com.amazon.busPassManagement.Model.User;

import java.util.List;

public class AuthenticationService {
    private static AuthenticationService service = new AuthenticationService();
    public static AuthenticationService getInstance()
    {
        return service;
    }
    UserDAO dao = new UserDAO();
   // LinkedHashMap<Integer,User> users = new LinkedHashMap<>();

    private AuthenticationService()
    {
       /* User user1 = new User();
        user1.id=1;
        user1.name ="John Watson";
        user1.phone="+91 99999 11111";
        user1.email ="john@example.com";
        user1.password ="password123";
        user1.address ="Redwood Shores";
        user1.department="Admin";
        user1.type =1;

        User user2 = new User();
        user2.id=2;
        user2.name ="Fiona Flynn";
        user2.phone ="+91 99999 22222";
        user2.email ="fiona@example.com";
        user2.password ="password123";
        user2.address ="Country Homes";
        user2.department ="hr";
        user2.type =2;
        //users.put(user1.id,user1);
        //users.put(user2.id,user2);

        //Add these 2 user in DataBase

        UserDAO dao = new UserDAO();
        dao.insert(user1);
        dao.insert(user2);
        */

    }
    public boolean loginUser(User user)
    {
        String sql="SELECT * FROM [User] WHERE email='"+user.email+"'AND password ='"+user.password+"'";
        List<User> users = dao.retrieve(sql);
        if(users.size()>0)
        {
            User u = users.get(0);
            user.id=u.id;
            user.name = u.name;
            user.phone = u.phone;
            user.email = u.email;
            user.address = u.address;
            user.department = u.department;
            user.type = u.type;
            user.createdOn = u.createdOn;
            return true;
        }
        return false;
       /* boolean result=false;
       // Iterator<Integer> itr = users.keySet().iterator();
        while (itr.hasNext())
        {
            Integer key = itr.next();
            User u = users.get(key);
            if(u.email.equals(user.email) && u.password.equals(user.password))
            {
                user.name=u.name;
                user.type=u.type;
                return true;
            }
        }

        return  result;*/

    }
    public boolean registerUser(User user)
    {
        return dao.insert(user)>0;
    }

    public boolean updateUser(User user)
    {
        return dao.update(user)>0;
    }
}
