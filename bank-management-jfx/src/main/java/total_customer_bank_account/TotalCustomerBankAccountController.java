package total_customer_bank_account;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;

import admin_dashboard.AdminDashboardScreen;
import common.RestUtil;

public class TotalCustomerBankAccountController {

    @FXML
    private TableView<TotalAccounts> tableView;

    @FXML
    private TableColumn<TotalAccounts, String> firstName;

    @FXML
    private TableColumn<TotalAccounts, String> lastName;

    @FXML
    private TableColumn<TotalAccounts, String> mobileNumber;

    @FXML
    private TableColumn<TotalAccounts, String> emailId;

    @FXML
    private TableColumn<TotalAccounts, String> atmPin;

    @FXML
    private TableColumn<TotalAccounts, String> customerId;

    @FXML
    private TableColumn<TotalAccounts, String> cardNo;

    @FXML
    private TableColumn<TotalAccounts, String> accountType;

    @FXML
    private TableColumn<TotalAccounts, String> balance;

    @FXML
    private TableColumn<TotalAccounts, String> accountStatus;

    @FXML
    private TextField customerIds;

    @FXML
    private TextField firstNames;

    @FXML
    private TextField accountNumber;
    
    @FXML
    private Button reset;
    
    @FXML
    private Button back;


    private ObservableList<TotalAccounts> totalAccountsList = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Your initialization logic here
        setupTableView();
    }

    private void setupTableView() {
    	
    	totalAccountsList = FXCollections.observableArrayList();
    	
    	String apiUrl = "http://localhost:8081/api/v1/admin/total/account";
    	String apiUrl1 = "http://localhost:8081/api/v1/admin/total/customers";
    	fetchCustomerAccountData(apiUrl);
    	fetchCustomerAccountData(apiUrl1);

    	firstName.setCellValueFactory(new PropertyValueFactory<>("firstName") );
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        emailId.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        atmPin.setCellValueFactory(new PropertyValueFactory<>("atmPin"));
        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cardNo.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        accountType.setCellValueFactory(new PropertyValueFactory<>("accountType"));
        balance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        accountStatus.setCellValueFactory(new PropertyValueFactory<>("accountStatus"));

        FilteredList<TotalAccounts> filteredData = new FilteredList<>(FXCollections.observableArrayList(totalAccountsList),
                p -> true);
        
        customerIds.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(TotalAccounts -> matchesFilter(TotalAccounts, newValue));
        });

        firstNames.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(TotalAccounts -> matchesFilter(TotalAccounts, newValue));
        });

        accountNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(TotalAccounts -> matchesFilter(TotalAccounts, newValue));
        });
        
        
        tableView.setItems(totalAccountsList);
    }
    private boolean matchesFilter(TotalAccounts totalAccount, String filterText) {
        if (filterText == null || filterText.isEmpty()) {
            return true;
        }

        String lowercaseFilter = filterText.toLowerCase();

        return totalAccount.getFirstName().toLowerCase().contains(lowercaseFilter)
                || totalAccount.getCustomerId().toLowerCase().contains(lowercaseFilter)
                || totalAccount.getCardNo().toLowerCase().contains(lowercaseFilter);        
    }

    @FXML
    private void reset() {
        customerIds.clear();
        firstNames.clear();
        accountNumber.clear();
    }
    private void fetchCustomerAccountData(String apiUrl) {
        try {
            List<TotalAccounts> customers = RestUtil.sendGetRequests(apiUrl, new TypeReference<List<TotalAccounts>>() {});
            totalAccountsList.addAll(customers);
            tableView.setItems(totalAccountsList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
   
    public void addTotalAccount(TotalAccounts totalAccount) {
        totalAccountsList.add(totalAccount);
    }
    @FXML
    private void back() {
       new AdminDashboardScreen().show();
    }

}


