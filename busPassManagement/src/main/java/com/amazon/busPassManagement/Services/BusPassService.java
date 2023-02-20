package com.amazon.busPassManagement.Services;

import com.amazon.busPassManagement.DB.BusPassDAO;
import com.amazon.busPassManagement.Model.BusPass;
import com.amazon.busPassManagement.Session.BusPassSession;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BusPassService {
    BusPassDAO passDAO = new BusPassDAO();
    // Create it as a Singleton
    private static BusPassService passService = new BusPassService();
    Scanner scanner = new Scanner(System.in);

    public static BusPassService getInstance() {
        return passService;
    }

    private BusPassService() {

    }

    // Handler for the Bus Pass :)
    public void requestPass() {
        BusPass pass = new BusPass();
        pass.getDetails(false);

        // Add the User ID Implicitly.
        pass.uid = BusPassSession.user.id;

        // As initially record will be inserted by User where it is a request
        pass.status = 1; // initial status as requested :)

        int result = passDAO.insert(pass);
        String message = (result > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again..";
        System.out.println(message);
    }

    public void deletePass() {
        BusPass pass = new BusPass();
        System.out.println("Enter Pass ID to be deleted: ");
        pass.id = scanner.nextInt();
        int result = passDAO.delete(pass);
        String message = (result > 0) ? "Pass Deleted Successfully" : "Deleting Pass Failed. Try Again..";
        System.out.println(message);
    }

    public void approveRejectPassRequest() {

        BusPass pass = new BusPass();

        System.out.println("Enter Pass ID: ");
        pass.id = scanner.nextInt();

        System.out.println("2: Approve");
        System.out.println("3: Cancel");
        System.out.println("Enter Approval Choice: ");
        pass.status = scanner.nextInt();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        pass.approvedRejectedOn = dateFormat.format(date1);

        if(pass.status == 2) {
            calendar.add(Calendar.YEAR, 1);
            Date date2 = calendar.getTime();
            pass.validTill = dateFormat.format(date2);
        }else {
            pass.validTill = pass.approvedRejectedOn;
        }

        int result = passDAO.update(pass);
        String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again..";
        System.out.println(message);
    }

    public void viewPassRequests() {

        System.out.println("Enter Route ID to get All the Pass Reqeuests for a Route");
        System.out.println("Or 0 for All Bus Pass Requests");
        System.out.println("Enter Route ID: ");

        int routeId = scanner.nextInt();

        List<BusPass> objects = null;

        if(routeId == 0) {
            objects = passDAO.retrieve();
        }else {
            String sql = "SELECT * from BusPass where routeId = "+routeId;
            objects = passDAO.retrieve(sql);
        }

        for(BusPass object : objects) {
            object.prettyPrint();
        }
    }

    public void viewPassRequestsByUser(int uid) {

        String sql = "SELECT * from BusPass where uid = "+uid;
        List<BusPass> objects = passDAO.retrieve(sql);

        for(BusPass object : objects) {
            object.prettyPrint();
        }
    }


}
