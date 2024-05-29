package Parking_Ticket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Jframe {

    public static void handleJframe() {
        // making the label for the title and logo
        ImageIcon OriginalIcon = new ImageIcon("Logo.png");
        Image originalImage = OriginalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(600, 400, java.awt.Image.SCALE_SMOOTH);
        ImageIcon parkingTicketIcon = new ImageIcon(resizedImage);

        JLabel label = new JLabel();
        label.setText("Welcome to our Parking Ticket program");
        label.setIcon(parkingTicketIcon);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setForeground(Color.BLACK);
        label.setFont(new Font("Monospaced", Font.BOLD, 20));

        // Making the frame
        JFrame frame = new JFrame();
        frame.setTitle("Parking Ticket Program");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(900, 700); // Mengatur ukuran jendela
        frame.setLayout(new BorderLayout());

        // panel for logo and label
        JPanel logoPanel = new JPanel(new GridBagLayout());
        logoPanel.setBackground(Color.WHITE); // Set background color to white
        logoPanel.add(label, new GridBagConstraints());

        //adding label to frame
        frame.add(logoPanel, BorderLayout.NORTH);

        //making panel for the button
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));

        //making the buttons
        JButton guestButton = new JButton("Continue As Guest");
        JButton memberButton = new JButton("Member");

        //adding the buttons to the panel
        panel.add(guestButton);
        panel.add(memberButton);

        //adding the panel inside the frame
        frame.add(panel, BorderLayout.CENTER);

        //background frame
        ImageIcon image = new ImageIcon("kirby2.jpg"); // Membuat ikon gambar
        frame.setIconImage(image.getImage());
        frame.getContentPane().setBackground(Color.WHITE); // Warna latar belakang

        // making action listener for the button
        guestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guestPanel.handleGuestPanel(frame);
            }
        });

        memberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMember(frame);
            }
        });




        // displaying frame
        frame.setVisible(true);
    }

    private static void handleMember(JFrame parentFrame) {
        // dialogue to choose either sign up or login
        JDialog memberDialog = new JDialog(parentFrame, "Member Options", true);
        memberDialog.setSize(300, 150);
        memberDialog.setLayout(new GridLayout(3, 1, 10, 10));

        JLabel label = new JLabel("Choose an option:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        JButton signUpButton = new JButton("Sign Up");
        JButton logInButton = new JButton("Log In");

        memberDialog.add(label);
        memberDialog.add(signUpButton);
        memberDialog.add(logInButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp(parentFrame);
                memberDialog.dispose();
            }
        });

        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin(parentFrame);
                memberDialog.dispose();
            }
        });

        memberDialog.setLocationRelativeTo(parentFrame);
        memberDialog.setVisible(true);
    }

    private static void handleSignUp(JFrame parentFrame) {
        // sign up dialogue
        JDialog signUpDialog = new JDialog(parentFrame, "Sign Up", true);
        signUpDialog.setSize(300, 300);
        signUpDialog.setLayout(new GridLayout(5, 2, 10, 10));

        JLabel emailLabel = new JLabel("Enter your email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter your password:");
        JPasswordField passwordField = new JPasswordField();
        JLabel usernameLabel = new JLabel("Enter your username:");
        JTextField usernameField = new JTextField();
        JLabel userTypeLabel = new JLabel("Enter your user type (Regular/Premium):");
        JTextField userTypeField = new JTextField();
        JButton signUpButton = new JButton("Sign Up");

        signUpDialog.add(emailLabel);
        signUpDialog.add(emailField);
        signUpDialog.add(passwordLabel);
        signUpDialog.add(passwordField);
        signUpDialog.add(usernameLabel);
        signUpDialog.add(usernameField);
        signUpDialog.add(userTypeLabel);
        signUpDialog.add(userTypeField);
        signUpDialog.add(new JLabel()); // Spacer
        signUpDialog.add(signUpButton);

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                String username = usernameField.getText();
                String userType = userTypeField.getText();

                try {
                    UserActions newUser;
                    if (userType.equalsIgnoreCase("Regular")) {
                        newUser = new RegularUser(email, password, username, 50.0);
                    } else if (userType.equalsIgnoreCase("Premium")) {
                        newUser = new PremiumUser(email, password, username, 50.0);
                    } else {
                        throw new IllegalArgumentException("Invalid user type.");
                    }

                    SignIn.users.add((User) newUser);
                    SignIn.Email.add(email);
                    SignIn.Password.add(password);
                    SignIn.Username.add(username);

                    JOptionPane.showMessageDialog(signUpDialog, "You have successfully created an account!");
                    signUpDialog.dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(signUpDialog, "An error occurred: " + ex.getMessage());
                }
            }
        });

        signUpDialog.setLocationRelativeTo(parentFrame);
        signUpDialog.setVisible(true);
    }

    private static void handleLogin(JFrame parentFrame) {
        // login dialogue
        JDialog loginDialog = new JDialog(parentFrame, "Login", true);
        loginDialog.setSize(300, 200);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));

        JLabel emailLabel = new JLabel("Enter your email:");
        JTextField emailField = new JTextField();
        JLabel passwordLabel = new JLabel("Enter your password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginDialog.add(emailLabel);
        loginDialog.add(emailField);
        loginDialog.add(passwordLabel);
        loginDialog.add(passwordField);
        loginDialog.add(new JLabel()); // Spacer
        loginDialog.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredEMAIL = emailField.getText();
                String enteredPASSWORD = new String(passwordField.getPassword());

                boolean found = SignIn.verifyCredentials(enteredEMAIL, enteredPASSWORD);

                if (found) {
                    JOptionPane.showMessageDialog(loginDialog, "Login Successful!");
                    loginDialog.dispose();
                    parking.handleParking();
                } else {
                    JOptionPane.showMessageDialog(loginDialog, "Invalid email or password. Please try again.");
                }
            }
        });

        loginDialog.setLocationRelativeTo(parentFrame);
        loginDialog.setVisible(true);
    }

    public static void main(String[] args) {
        handleJframe();
    }
}