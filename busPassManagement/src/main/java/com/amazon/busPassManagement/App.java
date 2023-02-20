package com.amazon.busPassManagement;

import com.amazon.busPassManagement.View.Menu;
import com.amazon.busPassManagement.DB.DB;
public class App
{

    public static void main( String[] args )
    {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Bus Pass Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Menu menu = new Menu();
        if(args.length > 0) {
            DB.FILEPATH = args[0];
        }
        menu.showMainMenu();

    }
}
