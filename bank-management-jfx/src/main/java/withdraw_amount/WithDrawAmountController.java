package withdraw_amount;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import dto.CustomerWithdrawAmountRequest;
import dto.CustomerWithdrawAmountResponse;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class WithDrawAmountController {

    @FXML
    private TextField amount;

    @FXML
    private TextField pin;

    @FXML
    private TextField accountNumber;

    @FXML
    private Button withdraw;

    @FXML
    private Button back;

    public void withdraw(ActionEvent event) {
        if (amount.getText().isEmpty() || pin.getText().isEmpty() || accountNumber.getText().isEmpty()) {
            showAlert("Error", "Please enter both amount, pin, and account number.");
            return;
        }
        CustomerWithdrawAmountResponse withdrawResponse = performWithdraw(amount.getText(), pin.getText(), accountNumber.getText());

        if ("Success".equals(withdrawResponse.getStatus())) {
            showAlert("Success", "Withdrawal successful!");
        } else if ("Fail".equals(withdrawResponse.getStatus())) {
            showAlert("Error", withdrawResponse.getResponse());

      
            if ("Your Account Has Lock. Please Contact The Admin".equals(withdrawResponse.getResponse())) {
                showAlert("Account Locked", "Your account has been locked. Please contact the admin.");
            }
        }
    }

    public void back(ActionEvent event) {
        new CustomerManagementScreen().show();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private CustomerWithdrawAmountResponse performWithdraw(String withdrawalAmount, String pin, String accountNumber) {
        CustomerWithdrawAmountResponse response = new CustomerWithdrawAmountResponse();
        String url = "http://localhost:8081/api/v1/customer/withdraw/balance";
        CustomerWithdrawAmountRequest withdrawRequest = new CustomerWithdrawAmountRequest();
        withdrawRequest.setBalance(withdrawalAmount);
        withdrawRequest.setAtmPin(pin);
        withdrawRequest.setCardNo(accountNumber); 

        try {
            return RestUtil.sendPostRequest(url, CustomerWithdrawAmountResponse.class, withdrawRequest);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus("Fail");
            response.setResponse("Something went wrong. Please try again.");
            return response;
        }
    }
    
    private void sendWithdrawalEmail(double amount, double availableBalance) {
    	
        String toEmail = "recipient@example.com";
        String fromEmail = "ganukulkarni8459@gmail.com";
        String password = "btgm kptt jlki rcrv";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Withdrawal Confirmation");
            message.setText("You have successfully withdrawn rupee " + amount +
                    ". Your available balance is now rupee" + availableBalance);

            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Error sending email: " + e.getMessage());
        }
    }
}
