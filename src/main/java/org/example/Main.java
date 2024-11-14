package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    private final AuthorDAO authorDAO = new AuthorDAO();
    private TableView<Author> tableView = new TableView<>();
    private TextField txtFirstName = new TextField();
    private TextField txtLastName = new TextField();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Authors Database");
        // Setup columns
        TableColumn<Author, Integer> colAuthorID = new TableColumn<>("AuthorID");
        colAuthorID.setCellValueFactory(new PropertyValueFactory<>("authorID"));
        TableColumn<Author, String> colFirstName = new TableColumn<>("FirstName");
        colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        TableColumn<Author, String> colLastName = new TableColumn<>("LastName");
        colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableView.getColumns().addAll(colAuthorID, colFirstName, colLastName);
// Load data
        loadAuthors();
// Setup input fields and buttons
        txtFirstName.setPromptText("First Name");
        txtLastName.setPromptText("Last Name");
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> addAuthor());
        Button btnUpdate = new Button("Update");
        btnUpdate.setOnAction(e -> updateAuthor());
        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> deleteAuthor());

// Layout
        HBox inputBox = new HBox(10, txtFirstName, txtLastName, btnAdd,
                btnUpdate, btnDelete);
        VBox vbox = new VBox(10, tableView, inputBox);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void loadAuthors() {
        try {
            tableView.getItems().setAll(authorDAO.getAllAuthors());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addAuthor() {
        try {
            authorDAO.insertAuthor(txtFirstName.getText(), txtLastName.
                    getText());
            loadAuthors();
            txtFirstName.clear();
            txtLastName.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void updateAuthor() {
        Author selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                authorDAO.updateAuthor(selected.getAuthorID(), txtFirstName.
                        getText(), txtLastName.getText());
                loadAuthors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private void deleteAuthor() {
        Author selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                authorDAO.deleteAuthor(selected.getAuthorID());
                loadAuthors();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}