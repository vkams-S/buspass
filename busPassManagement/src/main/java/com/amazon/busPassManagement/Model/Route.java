package com.amazon.busPassManagement.Model;

import java.util.Scanner;

/*
create table Route(
	id INT IDENTITY(1,1),
	title VARCHAR(256),
	description VARCHAR(256),
	adminId INT,
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	FOREIGN KEY (adminId) REFERENCES [User](id),
	PRIMARY KEY(id)
);
 */
//POJO or Model or Bean(enterprise edition)
public class Route {
    public int id;
    public String title;
    public String description;
    public int adminId;
    public String createdOn;

    public Route(){

    }

    public Route(int id, String title, String description, int adminId, String createdOn) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.adminId = adminId;
        this.createdOn = createdOn;
    }

    public void getDetails(boolean updateMode) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Route Details....");

        System.out.println("Enter Route Title:");
        title = scanner.nextLine();

        System.out.println("Enter Route Description:");
        description = scanner.nextLine();
        if(updateMode) {
            System.out.println("Enter Route ID:");
            id = Integer.parseInt(scanner.nextLine());
        }
    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Id:\t\t"+id);
        System.out.println("Title:\t\t"+title);
        System.out.println("Description:\t\t"+description);
        System.out.println("adminId:\t"+adminId);
        System.out.println("CreatedOn:\t"+createdOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", adminId=" + adminId +
                ", createdOn='" + createdOn + '\'' +
                '}';
    }
}
