package main.dashboard.model;

import main.dashboard.MainApp;

public class Launcher {
    private static Launcher instance;
    static MainApp app;


    Launcher(){
        this.app = new MainApp();

    }


    public static Launcher getInstance() {
        if(instance == null) {
            instance = new Launcher();
        }
        return instance;
    }

     public static void main(String[] args) {
        Launcher runApp = new Launcher();

        if(runApp.getInstance() != null) {
            app.main(args);
        }
        else{
            System.out.println("Instance is running...");
     	}

    }
}

