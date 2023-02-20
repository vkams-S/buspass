package com.amazon.busPassManagement.DB;

import com.amazon.busPassManagement.Model.Route;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouteDAO implements DAO<Route> {
    DB db = DB.getInstance();
    @Override
    public int insert(Route object) {
        String sql = "INSERT INTO [Route] (title, description, adminId) VALUES ('"
                +object.title+"', '"+object.description+"', "+object.adminId+")";
        return db.executeSQL(sql);
    }

    @Override
    public int update(Route object) {
        String sql = "UPDATE [Route] set title = '"+object.title+"', description='"+object.description+"'," +
                " adminId="+object.adminId+" WHERE id = "+object.id+"";
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Route object) {
        String sql = "DELETE FROM [Route] WHERE id = "+object.id+"";
        return db.executeSQL(sql);
    }

    @Override
    public List<Route> retrieve() {
        String sql="SELECT * FROM [Route]";
        ResultSet set = db.executeQuery(sql);
        ArrayList<Route> routes= new ArrayList<>();
        try{
            while(set.next())
            {
                Route object = new Route();
                object.id=set.getInt("id");
                object.title = set.getString("title");
                object.description = set.getString("description");
                object.adminId = set.getInt("adminId");
                object.createdOn = set.getString("createdOn");
                routes.add(object);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return routes;
    }

    @Override
    public List<Route> retrieve(String sql) {
        ResultSet set = db.executeQuery(sql);
        ArrayList<Route> routes= new ArrayList<>();
        try{
            while(set.next())
            {
                Route object = new Route();
                object.id=set.getInt("id");
                object.title = set.getString("title");
                object.description = set.getString("description");
                object.adminId = set.getInt("adminId");
                object.createdOn = set.getString("createdOn");
                routes.add(object);
            }

        }catch (Exception e)
        {
            System.err.println("Something went wrong..."+e);
        }
        return routes;
    }

}

