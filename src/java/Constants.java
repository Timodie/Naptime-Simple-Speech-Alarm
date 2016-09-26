package com.example.tim.myapplication;

/**
 * Created by Vincent on 2015/11/29.
 */
public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = "com.example.tim.action.settings";
        public static String STARTFOREGROUND_ACTION = "com.example.tim.action.startforeground";
        public static String STOPFOREGROUND_ACTION = "com.example.tim.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}