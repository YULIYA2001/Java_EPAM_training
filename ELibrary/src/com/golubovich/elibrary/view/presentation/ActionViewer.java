package com.golubovich.elibrary.view.presentation;

public class ActionViewer {

    public static String authorizationAnswer(boolean result) {
        String response;
        if (result) {
            response = "0 Successful authorization";
        } else {
            response = "1 No client or Wrong password";
        }

        return response;
    }

    public static String changeDataAnswer(boolean result) {
        String response;
        if (result) {
            response = "0 Changes applied successfully";
        } else {
            response = "1 Error Person not found or ...";
        }

        return response;
    }

    public static String registrationClientAnswer(boolean result) {
        String response;
        if (result) {
            response = "0 Successful client registration";
        } else {
            response = "1 Error  Existing e-mail or ...";
        }

        return response;
    }

    public static String deleteAnswer(boolean result) {
        String response;
        if (result) {
            response = "0 Deleted successfully";
        } else {
            response = "1 Error  Does not exists or ...";
        }

        return response;
    }

    public static String addItemAnswer(boolean result) {
        String response;
        if (result) {
            response = "0 Item added successfully";
        } else {
            response = "1 Error";
        }

        return response;
    }

    public static String addItemReview(boolean result) {
        String response;
        if (result) {
            response = "0 Review added successfully";
        } else {
            response = "1 Error";
        }

        return response;
    }
}

