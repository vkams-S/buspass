package com.amazon.busPassManagement.Services;

import com.amazon.busPassManagement.DB.FeedbackDAO;
import com.amazon.busPassManagement.Model.Feedback;
import com.amazon.busPassManagement.Session.BusPassSession;

import java.util.List;
import java.util.Scanner;

public class FeedbackService {
    FeedbackDAO feedbackDAO = new FeedbackDAO();

    // Create it as a Singleton
    private static FeedbackService feedbackService = new FeedbackService();
    Scanner scanner = new Scanner(System.in);

    public static FeedbackService getInstance() {
        return feedbackService;
    }

    private FeedbackService() {

    }

    // Handler for the Feedback :)
    public void createFeedback(boolean suspension) {
        Feedback feedback = new Feedback();
        if(suspension){
            feedback.getSuspension();
        }else{
            feedback.getDetails();
        }


        // Add the User ID Implicitly.
        feedback.userId = BusPassSession.user.id;
        feedback.raisedBy = BusPassSession.user.email;
        feedback.status = Feedback.Status.OPEN;
        int result = feedbackDAO.insert(feedback);
        String message="";
        if(suspension){
            message+= (result > 0) ? "Suspension Request Created Successfully" : "Creating suspension Failed. Try Again..";
        }else{
            message+= (result > 0) ? "Feedback Created Successfully" : "Creating Feedback Failed. Try Again..";
        }
        System.out.println(message);
    }

    public void deleteFeedback() {
        Feedback feedback = new Feedback();
        System.out.println("Enter Feedback ID to be deleted: ");
        feedback.id = Integer.parseInt(scanner.nextLine());
        int result = feedbackDAO.delete(feedback);
        String message = (result > 0) ? "Feedback Deleted Successfully" : "Deleting Feedback Failed. Try Again..";
        System.out.println(message);
    }

    public void updateFeedback() {
        Feedback feedback = new Feedback();
        System.out.println("Enter Feedback ID to be updated: ");
        feedback.id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the Status to be updated: \n1. OPEN\n2. ASSIGNED\n3. RESOLVED ");
        int statusChoice = Integer.parseInt(scanner.nextLine());
        if(statusChoice==1){
            feedback.status= Feedback.Status.OPEN;
        }else if(statusChoice==2){
            feedback.status= Feedback.Status.ASSIGNED;
        }else if(statusChoice==3){
            feedback.status= Feedback.Status.RESOLVED;
        }else{
            System.err.println("Invalid status choice ...");
        }
        int result = feedbackDAO.update(feedback);
        String message = (result > 0) ? "Feedback Updated Successfully" : "Update Feedback Failed. Try Again..";
        System.out.println(message);
    }

    public void viewFeedbacks() {
        List<Feedback> objects = feedbackDAO.retrieve();
        for(Feedback object : objects) {
            object.prettyPrint();
        }
    }

    public void viewFeedbacksByStatus(int statusType) {
        Feedback feedback = new Feedback();
        if(statusType==1)
        {
            feedback.status= Feedback.Status.OPEN;
        } else if (statusType==2) {
            feedback.status= Feedback.Status.ASSIGNED;
        } else if (statusType==3) {
            feedback.status= Feedback.Status.RESOLVED;
        }else{
            System.err.println("Invalid status choice..");
            return;
        }
        String sql = "SELECT * from Feedback where status = "+feedback.status;
        List<Feedback> objects = feedbackDAO.retrieve();
        for(Feedback object : objects) {
            object.prettyPrint();
        }
    }


    public void viewFeedbacksByUser(int uid) {

        String sql = "SELECT * from Feedback where userId = "+uid;

        List<Feedback> objects = feedbackDAO.retrieve(sql);

        for(Feedback object : objects) {
            object.prettyPrint();
        }
    }
}
