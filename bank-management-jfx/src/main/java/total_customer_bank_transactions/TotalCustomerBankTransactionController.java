package total_customer_bank_transactions;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;

import admin_dashboard.AdminDashboardScreen;
import admin_management.AdminManagementScreen;
import common.RestUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TotalCustomerBankTransactionController {

    @FXML
    private TableView<Transactions> tableView;

    @FXML
    private TableColumn<Transactions, String> transactionIdColumn;

    @FXML
    private TableColumn<Transactions, String> customerIdColumn;

    @FXML
    private TableColumn<Transactions, String> cardNumberColumn;

    @FXML
    private TableColumn<Transactions, String> amountColumn;

    @FXML
    private TableColumn<Transactions, String> transactionTypeColumn;

    @FXML
    private TableColumn<Transactions, String> dateAndTimeColumn;

    @FXML
    private TextField transactionId;

    @FXML
    private TextField amount;

    @FXML
    private TextField cardNumber;

    @FXML
    private Button resetButton;

    @FXML
    private Button backButton;

    private ObservableList<Transactions> transactionList;

    @FXML
    private void initialize() {
        transactionList = FXCollections.observableArrayList();

        String apiUrl = "http://localhost:8081/api/v1/admin/get/total/transactions";

        fetchTransactionData(apiUrl);

        transactionIdColumn.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        cardNumberColumn.setCellValueFactory(new PropertyValueFactory<>("cardNo"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("balance"));
        transactionTypeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionType"));
        dateAndTimeColumn.setCellValueFactory(new PropertyValueFactory<>("transactionDate"));

        FilteredList<Transactions> filteredData = new FilteredList<>(FXCollections.observableArrayList(transactionList), p -> true);

        transactionId.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
        });

        amount.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
        });

        cardNumber.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(transaction -> matchesFilter(transaction, newValue));
        });

        // Bind the filtered data to the TableView
        tableView.setItems(filteredData);
    }

    @FXML
    private void back() {
        new AdminDashboardScreen().show();
    }

    @FXML
    private void reset() {
        transactionId.clear();
        amount.clear();
        cardNumber.clear();
    }

    private boolean matchesFilter(Transactions transaction, String filter) {
        if (filter == null || filter.isEmpty()) {
            return true;
        }

        String lowerCaseFilter = filter.toLowerCase();

        return transaction.getCustomerId().toLowerCase().contains(lowerCaseFilter)
                || transaction.getTransactionId().toLowerCase().contains(lowerCaseFilter)
                || transaction.getCardNo().toLowerCase().contains(lowerCaseFilter);
    }

    private void fetchTransactionData(String apiUrl) {
        try {
            List<Transactions> transactions = RestUtil.sendGetRequests(apiUrl, new TypeReference<List<Transactions>>() {});
            transactionList.addAll(transactions);
            tableView.setItems(transactionList);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            // Handle exception (e.g., show an error message)
        }
    }
}
