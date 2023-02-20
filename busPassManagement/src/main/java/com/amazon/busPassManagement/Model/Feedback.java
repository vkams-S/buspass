package com.amazon.busPassManagement.Model;

import java.util.Scanner;

/*
SQL SERVER::
    create table Feedback(
	id INT IDENTITY(1,1),
	status VARCHAR(256),
	userId INT,
	title VARCHAR(256),
	description VARCHAR(2048),
	type INT,
	raisedBy VARCHAR(256),
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (userId) REFERENCES [User](id),
	PRIMARY KEY (id)
);
 */
public class Feedback {
    public enum Status{
        OPEN,ASSIGNED,RESOLVED;
    }
    public enum Type{
        SUGGESTION,COMPLAINT,PASS_SUSPENSION,GENERIC;
    }
    public int id;
    public Status status;
    public int userId;
    public Type title;
    public String description;
    public  int type;
    public String raisedBy;
    public String createdOn;

    public Feedback() {
    }

    //Method to convert string into Status
    public Enum<Status> getStatus(String status)
    {
            return Status.valueOf(status);
    }

    public Feedback(int id, Status status, int userId, Type title, String description, int type, String raisedBy, String createdOn) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.raisedBy = raisedBy;
        this.createdOn = createdOn;
    }


    public void getDetails() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Feedback Details....");

        System.out.println("1: Suggestion");
        System.out.println("2: Complaint");
        System.out.println("3: Pass Suspension");
        System.out.println("Select Type of Feedback:");
        type = Integer.parseInt(scanner.nextLine());

        if(type == 1) {
            title = Type.SUGGESTION;
        }else if(type == 2) {
            title = Type.COMPLAINT;
        }else if(type == 3) {
            title = Type.PASS_SUSPENSION;
        }else {
            title = Type.GENERIC;
        }

        System.out.println("Enter Description:");
        description = scanner.nextLine();

    }

    public void getSuspension() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Capturing Suspension Details....");
        type = 3;
        title = Type.PASS_SUSPENSION;
        System.out.println("Enter Description:");
        description = scanner.nextLine();
    }




    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~Feedback~~~~~~~~~~");
        System.out.println("Feedback ID:\t\t"+id);
        System.out.println("Feedback Status:\t\t"+status);
        System.out.println("Title:\t\t"+title);
        System.out.println("Description:\t\t"+description);
        System.out.println("Type:\t\t"+type);
        System.out.println("Raised By:\t\t"+raisedBy);
        System.out.println("Created On:\t\t"+createdOn);
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", status=" + status +
                ", userId=" + userId +
                ", title=" + title +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", raisedBy='" + raisedBy + '\'' +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
