package com.amazon;


import com.amazon.busPassManagement.DB.*;
import com.amazon.busPassManagement.Model.*;
import com.amazon.busPassManagement.Services.AuthenticationService;
import com.amazon.busPassManagement.Services.BusPassService;
import org.junit.Assert;
import org.junit.Test;

public class AppTest
{
    AuthenticationService authService = AuthenticationService.getInstance();
    UserDAO userDAO = new UserDAO();
    RouteDAO routeDAO = new RouteDAO();
    StopDAO stopDAO = new StopDAO();
    VehicleDAO vehicleDAO = new VehicleDAO();
    BusPassDAO busPassDAO = new BusPassDAO();

    FeedbackDAO feedbackDAO = new FeedbackDAO();



    // UNIT TESTS

    @Test
    public void testUserLogin() {

        User user = new User();
        user.email = "test@example.com";
        user.password = "test";

        boolean result = authService.loginUser(user);
        Assert.assertEquals(true, result);

    }

    @Test
    public void testAdminLogin() {

        User user = new User();
        user.email = "john@example.com";
        user.password = "password123";

        boolean result = authService.loginUser(user);
        Assert.assertEquals(true, result);
        Assert.assertEquals(1, user.type); // 1 should be equal to 1

    }

    @Test
    public void registerUser()
    {
        User user = new User();
        user.name="DummyName";
        user.phone="+91 00000 00002";
        user.email="DummyUser@example.com";
        user.password = "Password123";
        user.address ="Dummy";
        user.department="AMZ";
        user.type=2;
       boolean result = authService.registerUser(user);
       Assert.assertTrue(result);
       userDAO.delete(user);
    }

    @Test
    public void AddRoute()
    {
        Route route = new Route();
        route.title="TestRouteADD";
        route.description="This is a test";
        route.adminId=1;
        int result=routeDAO.insert(route);
        Assert.assertTrue(result>0);
        routeDAO.delete(route);

    }

    @Test
    public void AddStop()
    {
        Stop stop = new Stop();
        stop.address="Test";
        stop.sequenceOrder=3;
        stop.routeId=2;
        stop.adminId=1;
        int result=stopDAO.insert(stop);
        Assert.assertTrue(result>0);
        stopDAO.delete(stop);

    }

    @Test
    public void UpdateVehicle()
    {
        Vehicle vehicle = new Vehicle();
        vehicle.id=2;
        vehicle.registrationNumber="KA 05 HN 7777";
        vehicle.totalSeats=20;
        vehicle.filledSeats=6;
        vehicle.routeId=1;
        vehicle.type=1;
        vehicle.vehicleStatus=1;
        vehicle.startDropOffTime="07:00";
        vehicle.startPickUpTime="06:00";
        vehicle.adminId=1;
        vehicle.driverID=4;
        int result=vehicleDAO.update(vehicle);
        Assert.assertTrue(result>0);

    }

    @Test
    public void ApproveBusPass()
    {
        BusPass busPass = new BusPass();
        busPass.id=2;
        busPass.uid=1006;
        busPass.routeId=1;
        busPass.status=2;
        busPass.requestedOn="2023-02-11 20:00:02.773";
        busPass.approvedRejectedOn="2023-02-16 08:30:48.020";
        busPass.createdOn="2023-02-11 20:00:02.773";
        int result=busPassDAO.update(busPass);
        Assert.assertTrue(result>0);

    }


    @Test
    public void Feedback()
    {
        Feedback feedback = new Feedback();
        feedback.status= Feedback.Status.valueOf("OPEN");
        feedback.userId=2;
        feedback.title= Feedback.Type.valueOf("SUGGESTION");
        feedback.description="Test";
        feedback.type=1;
        feedback.raisedBy="fiona@example.com";
        int result=feedbackDAO.insert(feedback);
        Assert.assertTrue(result>0);

    }


}
