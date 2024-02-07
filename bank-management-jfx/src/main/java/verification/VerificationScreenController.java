package verification;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import admin_login.AdminLogin;
import common.RestUtil;
import dto.AdminResgisterRequest;
import dto.AdminResgisterResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class VerificationScreenController {

    private static final String SENDER_EMAIL = "ganukulkarni8459@gmail.com"; 
    private static final String SENDER_PASSWORD = "btgm kptt jlki rcrv"; 

    @FXML
    private TextField email;

    @FXML
    private TextField otp;

    @FXML
    private TextField newPassword;

//    @FXML
//    private TextField oldPassword;

    @FXML
    private Button verifyOtp;

    @FXML
    private Button updatePassword;

    @FXML
    private Button back;

    private String generatedOtp; 

    @FXML
    private void initialize() {
        if (!email.getText().isEmpty()) {
            generatedOtp = generateOtp();
            sendOtpToEmail(email.getText(), generatedOtp);
        }
    }

    @FXML
    public void verifyOtp(ActionEvent event) {
        if (generatedOtp == null) {
            generatedOtp = generateOtp();
            sendOtpToEmail(email.getText(), generatedOtp);
        }
    }

    @FXML
    public void updatePassword(ActionEvent event) throws IOException, InterruptedException {
        if (otp.getText().equals(generatedOtp)) {
            updatePasswordOnBackend(email.getText(), newPassword.getText());
        } else {
            showAlert("Error", "Invalid OTP. Please enter the correct OTP.");
        }
    }

    private void updatePasswordOnBackend(String email, String newPassword) throws IOException, InterruptedException {
        AdminResgisterRequest request = new AdminResgisterRequest();
        request.setAdminEmail(email);
       // request.setPassword(oldPassword.getText());
        request.setUpdatedPassword(newPassword);
        AdminResgisterResponse response = RestUtil.sendPostRequest(
                "http://localhost:8081/api/admin/update/password", AdminResgisterResponse.class, request);

        if ("Success".equals(response.getStatus())) {
            showAlert("Success", response.getMessage());
        } else {
            showAlert("Error", response.getMessage());
        }
    }

    @FXML
    public void back(ActionEvent event) {
        new AdminLogin().show();
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    private void sendOtpToEmail(String recipientEmail, String otp) {
      
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

       
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
            }
        });

        try {
           
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setSubject("OTP for Password Reset");
            message.setText("Your OTP is: " + otp);

           
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
