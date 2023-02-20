package com.amazon.busPassManagement.DB;

import com.amazon.busPassManagement.Model.Feedback;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO implements DAO<Feedback> {
    DB db = DB.getInstance();
    @Override
    public int insert(Feedback object) {
        String sql = "INSERT INTO Feedback (status,userId, title, description, type, raisedBy) "
                + "VALUES ( '"+object.status+"',"+object.userId+",'"+object.title+"', '"+object.description+"', "+object.type+", '"+object.raisedBy+"')";
        return db.executeSQL(sql);
    }


    @Override
    public int update(Feedback object) {
        String sql = "UPDATE Feedback set status = '"+object.status+"' WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public int delete(Feedback object) {
        String sql = "DELETE from Feedback WHERE id = "+object.id;
        return db.executeSQL(sql);
    }

    @Override
    public List<Feedback> retrieve() {
        String sql = "SELECT * from Feedback";

        ResultSet set = db.executeQuery(sql);

        ArrayList<Feedback> objects = new ArrayList<Feedback>();

        try {
            while(set.next()) {

                Feedback object = new Feedback();

                // Read the row from ResultSet and put the data into Object
                object.id = set.getInt("id");
                object.status = Feedback.Status.valueOf(set.getString("status"));
                object.userId = set.getInt("userId");
                object.title = Feedback.Type.valueOf(set.getString("title"));
                object.description = set.getString("description");
                object.type = set.getInt("type");
                object.raisedBy = set.getString("raisedBy");
                object.createdOn = set.getString("createdOn");

                objects.add(object);
            }
        } catch (Exception e) {
            System.err.println("Something Went Wrong: "+e);
        }


        return objects;
    }

    @Override
    public List<Feedback> retrieve(String sql) {
        return null;
    }
}
