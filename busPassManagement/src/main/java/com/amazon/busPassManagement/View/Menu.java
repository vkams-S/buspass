package com.amazon.busPassManagement.View;

import com.amazon.busPassManagement.DB.DB;
import com.amazon.busPassManagement.Services.AuthenticationService;
import com.amazon.busPassManagement.Services.BusPassService;
import com.amazon.busPassManagement.Services.FeedbackService;
import com.amazon.busPassManagement.Services.RouteService;

import java.util.Scanner;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    RouteService routeService = RouteService.getInstance();
    BusPassService passService = BusPassService.getInstance();
    FeedbackService feedbackService = FeedbackService.getInstance();

    Scanner scanner = new Scanner(System.in);

    public void showMainMenu() {

        // Initial Menu for the Application
        while(true) {

            System.out.println("1: Admin");
            System.out.println("2: User");
            System.out.println("3: Quit");

            System.out.println("Select an Option");
            int choice = Integer.parseInt(scanner.nextLine());

            if (choice == 3) {
                System.out.println("Thank You For using Bus Pass Management App");

                // Close the DataBase Connection, when user has exited the application :)
                DB db = DB.getInstance();
                db.closeConnection();
                scanner.close();
                break;
            }

            if(choice==1 || choice==2)
            {
                MenuFactory.getMenu(choice).showMenu();
            }
            else{
                System.err.println("Invalid choice... please choose a valid option.");
            }

            /*try {
                MenuFactory.getMenu(choice).showMenu();
            } catch (Exception e) {
                System.err.println("[Menu] [Exception] Invalid Choice...");
            }*/

        }
    }


    public void showMenu() {
        System.out.println("Showing the Menu...");
    }
}
