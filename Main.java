package Parking_Ticket;

import java.util.Scanner;
import javax.swing.*;

public class Main {
    private static final Scanner mandy = new Scanner(System.in); // Create a single Scanner instance

    public static void main(String[] args) {
        Jframe.handleJframe();

        while (true) {
            System.out.println(" ");
            System.out.println("Welcome to our Parking Ticket program ");
            System.out.println(" ");
            System.out.println("Enter a choice :  ");
            System.out.println("1. Continue As Guest  \n2. Don't have an account yet? Sign Up \n3. Log In Account \n4. Exit Program");

            String userInputStr = mandy.nextLine();

            try {
                int user_input = Integer.parseInt(userInputStr);

                switch (user_input) {
                    case 1: // continue as guest
                        guest.handleGuest();
                        break;
                    case 2: // sign up
                        SignIn.handleSignIn(mandy);
                        break;
                    case 3: // login
                        System.out.println(" ");
                        System.out.println("Login");

                        System.out.println("Enter your email : ");
                        String enteredEMAIL = mandy.nextLine();

                        System.out.println(" ");
                        System.out.println("Enter your password : ");
                        String enteredPASSWORD = mandy.nextLine();

                        boolean found = SignIn.verifyCredentials(enteredEMAIL, enteredPASSWORD);

                        if (found) {
                            System.out.println(" ");
                            System.out.println("Login Successful!");
                            parking.handleParking();
                        } else {
                            System.out.println("Invalid email or password. Please try again.");
                        }
                        break;
                    case 4:
                        // stop program
                        System.out.println("Exiting program");
                        mandy.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice, Please try again");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice, Please enter a number.");
            }
        }
    }
}