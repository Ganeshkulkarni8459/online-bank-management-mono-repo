package total_customer;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import admin_dashboard.AdminDashboardScreen;
import admin_management.AdminManagementScreen;
import common.RestUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TotalCustomersController {
	
	@FXML
	private TextField searchByCustomerId;
	
	@FXML
	private TextField searchByFirstName;
	
	@FXML
	private TextField searchByGender;
	
    @FXML
    private TableView<CustomerResgistration> tableView;

    @FXML
    private TableColumn<CustomerResgistration, String> customerId;

    @FXML
    private TableColumn<CustomerResgistration, String> firstName;

    @FXML
    private TableColumn<CustomerResgistration, String> lastName;

    @FXML
    private TableColumn<CustomerResgistration, String> dateOfBirth;

    @FXML
    private TableColumn<CustomerResgistration, String> mobileNumber;

    @FXML
    private TableColumn<CustomerResgistration, String> emailId;

    @FXML
    private TableColumn<CustomerResgistration, String> gender;

    @FXML
    private TableColumn<CustomerResgistration, String> branch;

    @FXML
    private TableColumn<CustomerResgistration, String> password;

    @FXML
    private TableColumn<CustomerResgistration, String> permanentAddress;

    @FXML
    private TableColumn<CustomerResgistration, String> presentAddress;
    
    @FXML
    private TableColumn<CustomerResgistration, String> registerDate;
    
    @FXML
    private Button back;
    
    @FXML
    private Button reset;

    private ObservableList<CustomerResgistration> customerList;

    @FXML
    private void initialize() {
        customerList = FXCollections.observableArrayList();

        String apiUrl = "http://localhost:8081/api/v1/admin/total/customers";

        fetchCustomerData(apiUrl);

        customerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        dateOfBirth.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        mobileNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNo"));
//        emailId.setCellValueFactory(new PropertyValueFactory<>("emailId"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        branch.setCellValueFactory(new PropertyValueFactory<>("branch"));
      //  password.setCellValueFactory(new PropertyValueFactory<>("password"));
        permanentAddress.setCellValueFactory(new PropertyValueFactory<>("permanentAddress"));
        presentAddress.setCellValueFactory(new PropertyValueFactory<>("presentAddress")); 
        registerDate.setCellValueFactory(new PropertyValueFactory<>("registerDate"));
        

        FilteredList<CustomerResgistration> filteredData = new FilteredList<>(FXCollections.observableArrayList(customerList),
                p -> true);

        searchByCustomerId.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> matchesFilter(customer, newValue));
        });

        searchByGender.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> matchesFilter(customer, newValue));
        });

        searchByFirstName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(customer -> matchesFilter(customer, newValue));
        });

        // Bind the filtered data to the TableView
        tableView.setItems(filteredData);
    }
    private boolean matchesFilter(CustomerResgistration customer, String filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = filter.toLowerCase();

        return customer.getCustomerId().toLowerCase().contains(lowerCaseFilter)
                || customer.getGender().toLowerCase().contains(lowerCaseFilter)
                || customer.getFirstName().toLowerCase().contains(lowerCaseFilter);
    }

    private void fetchCustomerData(String apiUrl) {
        try {
            List<CustomerResgistration> customers = RestUtil.sendGetRequests(apiUrl, new TypeReference<List<CustomerResgistration>>() {});
            customerList.addAll(customers);
            tableView.setItems(customerList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exception (e.g., show an error message)
        }
    }
    
    @FXML
    private void reset(ActionEvent event) {
    	searchByCustomerId.clear();
    	searchByFirstName.clear();
    	searchByGender.clear();  
    }

    @FXML
    private void back() {
        new AdminDashboardScreen().show();
    }
}

    
