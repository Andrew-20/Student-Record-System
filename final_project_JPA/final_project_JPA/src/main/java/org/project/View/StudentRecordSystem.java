package org.project.View;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.project.Model.Grade;
import org.project.Model.Student;
import org.project.Controller.SystemController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.project.Model.StudentRepository;
import java.time.LocalDate;

/**
 * The View class of the Student Record System
 * <p>
 *     Contains all javafx code
 * </p>
 * @author Andrew Finch
 */

public class StudentRecordSystem extends Application {
    private VBox tab1_root;
    private HBox fNameBox, mNameBox, lNameBox, dobBox, topButtonsBox;
    private Button addButton, removeButton, updateButton;
    private Label firstNameText, middleNameText, lastNameText, dobText, studentLabel, moduleLabelHeading;
    private TextArea idTextArea, dobTextArea, studentList, firstNameTextArea, middleNameTextArea, lastNameTextArea;

    private static SystemController controller = new SystemController();

    /**
     * Creates all javafx objects and nodes for the GUI
     * @param stage object
     */
    public void start (Stage stage) {

        // Boxes /////////////////////////////
        tab1_root = new VBox();
        fNameBox =  new HBox();
        mNameBox = new HBox();
        lNameBox = new HBox();
        dobBox = new HBox();
        topButtonsBox = new HBox(); // Add, Remove, Update

        TableView<Student> studentTable = new TableView<Student>();
        TableView<Grade> gradeTableView = new TableView<Grade>();

        // Heading label for student
        studentLabel = new Label("Student");
        studentLabel.setStyle("-fx-font: 24 arial;");
        // Heading label for module
        moduleLabelHeading = new Label("Module");
        moduleLabelHeading.setStyle("-fx-font: 24 arial;");

        // Name HBox ////////////////////////
        firstNameText = new Label("First name");
        middleNameText = new Label("Middle name");
        lastNameText = new Label("Surname");

        firstNameText.setMinWidth(100);
        middleNameText.setMinWidth(100);
        lastNameText.setMinWidth(100);

        firstNameTextArea = new TextArea();
        middleNameTextArea = new TextArea();
        lastNameTextArea = new TextArea();

        firstNameTextArea.setMaxWidth(200);
        middleNameTextArea.setMaxWidth(200);
        lastNameTextArea.setMaxWidth(200);

        fNameBox.getChildren().add(firstNameText);
        fNameBox.getChildren().add(firstNameTextArea);
        fNameBox.setPadding(new Insets(35, 5, 10, 5));
        fNameBox.setSpacing(30);
        fNameBox.setPrefHeight(10);

        mNameBox.getChildren().addAll(middleNameText, middleNameTextArea);
        mNameBox.setPadding(new Insets(10, 5, 10, 5));
        mNameBox.setSpacing(30);
        mNameBox.setPrefHeight(10);

        lNameBox.getChildren().addAll(lastNameText, lastNameTextArea);
        lNameBox.setPadding(new Insets(10, 5, 10, 5));
        lNameBox.setSpacing(30);
        lNameBox.setPrefHeight(10);

        // DOB HBox ////////////////////////////
        dobText = new Label();
        dobText.setPrefWidth(100);
        dobText.setText("Date of Birth");
        DatePicker dobTextArea = new DatePicker();
        dobBox.getChildren().add(dobText);
        dobBox.getChildren().add(dobTextArea);
        dobBox.setPadding(new Insets(10, 5, 20, 5));
        dobBox.setSpacing(30);
        dobBox.setPrefHeight(10);
        dobTextArea.setMaxWidth(200);

        // top button box ////////////////////
        addButton = new Button("Add");
        addButton.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        removeButton = new Button("Remove");
        removeButton.setStyle("-fx-background-color: #B22222; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        updateButton = new Button("Update");
        updateButton.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        topButtonsBox.getChildren().addAll(addButton, updateButton, removeButton);
        topButtonsBox.setSpacing(10);
        topButtonsBox.setPadding(new Insets(30, 5, 20, 5));
        topButtonsBox.setAlignment(Pos.BASELINE_CENTER);

        addButton.setOnAction(actionEvent -> {
            controller.addStudent(firstNameTextArea.getText(), middleNameTextArea.getText(), lastNameTextArea.getText(), dobTextArea.getValue());
            studentTable.setItems(controller.getStudentsForTable());
        });
         removeButton.setOnAction(actionEvent -> {
             controller.removeStudent(studentTable.getSelectionModel().getSelectedItem());
             studentTable.getItems().removeAll(studentTable.getSelectionModel().getSelectedItem());
         });
         updateButton.setOnAction(actionEvent -> {
             Student s = studentTable.getSelectionModel().getSelectedItem();
             controller.updateStudent(s, firstNameTextArea.getText(), middleNameTextArea.getText(), lastNameTextArea.getText(), dobTextArea.getValue());
             studentTable.refresh();
         });
         studentTable.setOnMouseClicked(actionEvent -> {
             firstNameTextArea.setText(studentTable.getSelectionModel().getSelectedItem().name.getFName());
             middleNameTextArea.setText(studentTable.getSelectionModel().getSelectedItem().name.getMName());
             lastNameTextArea.setText(studentTable.getSelectionModel().getSelectedItem().name.getLName());
             dobTextArea.setValue(LocalDate.parse(studentTable.getSelectionModel().getSelectedItem().getDob()));
             gradeTableView.setItems(controller.getGradesforTable(studentTable.getSelectionModel().getSelectedItem()));
         });
         HBox exitButtonBox = new HBox();
         Button exitButton = new Button("Quit application");
         exitButton.setStyle("-fx-background-color: #B22222; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
         exitButtonBox.getChildren().add(exitButton);
         exitButtonBox.setAlignment(Pos.CENTER);
         exitButtonBox.setPadding(new Insets(30, 20, 20, 20));
        exitButton.setOnAction(e -> {
            boolean result = controller.exit();
            if(result){
                stage.close();
            }
        });

        HBox dummyStudentBox = new HBox();
        Button dummyStudentButton = new Button("Create Dummy Students");
        dummyStudentButton.setStyle("-fx-background-color: #e6b800; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        dummyStudentBox.getChildren().add(dummyStudentButton);
        dummyStudentBox.setAlignment(Pos.CENTER);
        dummyStudentBox.setPadding(new Insets(30, 20, 20, 20));
        dummyStudentButton.setOnAction(ActionEvent -> {
            controller.fillHeapSpace();
        });

        // add vbox for inputs to make student
        VBox input_box = new VBox();
        input_box.setPadding(new Insets(20, 20, 20, 20));
        input_box.getChildren().addAll(studentLabel, fNameBox, mNameBox, lNameBox, dobBox, topButtonsBox, dummyStudentBox, exitButtonBox);

        TableColumn<Student, String> nameCol = new TableColumn<Student, String>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));

        TableColumn<Student, String> idCol = new TableColumn<Student, String >("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<Student, String>("id"));

        TableColumn<Student, String> dobCol = new TableColumn<Student, String>("DOB");
        dobCol.setCellValueFactory(new PropertyValueFactory<Student, String>("dob"));

        studentTable.setItems(controller.getStudentsForTable());
        studentTable.getColumns().add(nameCol);
        studentTable.getColumns().add(idCol);
        studentTable.getColumns().add(dobCol);
        studentTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        studentTable.setMinWidth(400);

        TableColumn<Grade, String> moduleNameCol = new TableColumn<Grade, String>("Module");
        moduleNameCol.setCellValueFactory(new PropertyValueFactory<Grade, String>("studentModule"));

        TableColumn<Grade, Float> gradeCol = new TableColumn<Grade, Float>("Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<Grade, Float>("grade"));

        gradeTableView.getColumns().add(moduleNameCol);
        gradeTableView.getColumns().add(gradeCol);
        gradeTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        gradeTableView.setMinWidth(134);

        HBox sTable = new HBox();
        sTable.setAlignment(Pos.CENTER);
        sTable.getChildren().add(studentTable);

        HBox mTable = new HBox();
        mTable.getChildren().add(gradeTableView);

        HBox moduleBox = new HBox();
        Label moduleLabel = new Label("Name");
        moduleLabel.setPrefWidth(60);
        TextArea moduleField = new TextArea();
        moduleBox.getChildren().addAll(moduleLabel, moduleField);
        moduleBox.setPadding(new Insets(35, 5, 10, 5));
        moduleBox.setSpacing(30);
        moduleBox.setPrefHeight(10);
        moduleField.setMinWidth(100);
        moduleField.setMaxWidth(200);

        HBox gradeBox = new HBox();
        Label gradeLabel = new Label("Grade");
        gradeLabel.setPrefWidth(60);
        TextArea gradeField = new TextArea();
        gradeBox.getChildren().addAll(gradeLabel, gradeField);
        gradeBox.setPadding(new Insets(10, 5, 10, 5));
        gradeBox.setSpacing(30);
        gradeBox.setPrefHeight(10);
        gradeField.setMinWidth(100);
        gradeField.setMaxWidth(200);

        HBox addModuleBox = new HBox();
        Button addModuleButton = new Button("Add");
        addModuleButton.setStyle("-fx-background-color: #20B2AA; -fx-background-radius: 10px; -fx-text-fill: #ffffff");
        addModuleButton.setOnAction(ActionEvent -> {
            controller.addModule_Grade(studentTable.getSelectionModel().getSelectedItem(), moduleField.getText(), Float.parseFloat(gradeField.getText()));
            gradeTableView.setItems(controller.getGradesforTable(studentTable.getSelectionModel().getSelectedItem()));
        });
        addModuleBox.getChildren().add(addModuleButton);
        addModuleBox.setAlignment(Pos.CENTER);
        addModuleBox.setPadding(new Insets(30, 5, 10 , 5));

        Hyperlink clickMe = new Hyperlink("Click here");
        TextFlow flow = new TextFlow(new Text("List first class honour modules"), clickMe);
        clickMe.setOnAction(ActionEvent -> {
            // get a list of grades that are a first class honour
            gradeTableView.setItems(controller.getFirstClassGrade(studentTable.getSelectionModel().getSelectedItem()));
            // assign that list to the grade table
        });
        HBox flowBox = new HBox();
        flowBox.getChildren().add(flow);
        flowBox.setPadding(new Insets(30, 20, 20, 20));
        flowBox.setAlignment(Pos.CENTER);

        VBox Module_grade_Root_Box = new VBox();
        Module_grade_Root_Box.getChildren().addAll(moduleLabelHeading, moduleBox, gradeBox, addModuleBox, flowBox);
        Module_grade_Root_Box.setPadding(new Insets(20, 20, 20, 10));

        HBox root = new HBox();
        root.getChildren().addAll(input_box, sTable, mTable, Module_grade_Root_Box);

        Scene scene = new Scene(root, 1400, 600);
        stage.setScene(scene);
        stage.setTitle("MTU Student Record System");
        StudentRepository studentRepository = new StudentRepository();
        studentRepository.closeConnection();
        stage.show();
    }
}
