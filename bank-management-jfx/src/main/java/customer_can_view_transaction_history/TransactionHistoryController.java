package customer_can_view_transaction_history;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import common.RestUtil;
import customer_management.CustomerManagementScreen;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import total_customer.CustomerResgistration;

public class TransactionHistoryController {
	
	@FXML
    private Button revert;

    @FXML
    private TextField searchByCustomerId;

    @FXML
    private TextField transactionId;

    @FXML
    private TextField amount;

    @FXML
    private Button reset;

    @FXML
    private Button fetch;
    

    @FXML
    private TableView<Transactions> transactionTableView;

    @FXML
    private TableColumn<Transactions, String> transactionIdColumn;

    @FXML
    private TableColumn<Transactions, String> customerIdColumn;

    @FXML
    private TableColumn<Transactions, String> cardNumberColumn;

    @FXML
    private TableColumn<Transactions, String> transactionAmountColumn;

    @FXML
    private TableColumn<Transactions, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transactions, String> dateAndTimeColumn;
    
    private ObservableList<Transactions> transactionList;
    
    
    @FXML
    private void revert() {
        new CustomerManagementScreen().show();
    }

    @FXML
    private void reset() {
    	transactionId.clear();
    	amount.clear();
    }

    @FXML
    private void fetch() throws IOException, InterruptedException {
    	transactionList = FXCollections.observableArrayList();
    	
    	String apiUrl = "http://localhost:8081/api/v1/customer/get/transactions/"+searchByCustomerId.getText();
    	
    	fetchTransactionData(apiUrl);
    	
    	transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
    	customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
    	cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
    	transactionAmountColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
    	transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
    	dateAndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));
    	
    	FilteredList<Transactions> filteredData = new FilteredList<>(FXCollections.observableArrayList(transactionList),
                p -> true);
        
    	transactionId.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
        });
    	
//    	customerId.textProperty().addListener((observable, oldValue, newValue) -> {
//            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
//        });
//    	
    	
    	amount.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
        });
    	
    	transactionTableView.setItems(filteredData);
    }
    private boolean matchesFilter(Transactions transaction, String filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = filter.toLowerCase();

        return transaction.getCustomerId().toLowerCase().contains(lowerCaseFilter)
                || transaction.getCardNo().toLowerCase().contains(lowerCaseFilter)
                || transaction.getBalance().toLowerCase().contains(lowerCaseFilter)
                || transaction.getTransactionType().toLowerCase().contains(lowerCaseFilter)
                || transaction.getTransactionDate().toLowerCase().contains(lowerCaseFilter);
    }
    private void fetchTransactionData(String apiUrl) {
        try {
            List<Transactions> transaction = RestUtil.sendGetRequests(apiUrl, new TypeReference<List<Transactions>>() {});
            transactionList.addAll(transaction);
            transactionTableView.setItems(transactionList);
            showAlert("Data Fetched", "Transaction data has been successfully fetched.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exception (e.g., show an error message)
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



