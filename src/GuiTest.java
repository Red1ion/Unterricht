/*
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class DynamicGuiApp extends Application {

    public static class Person {
        private final StringProperty name = new SimpleStringProperty();
        private final IntegerProperty age = new SimpleIntegerProperty();

        public Person(String name, int age) {
            this.name.set(name);
            this.age.set(age);
        }
        public String getName() { return name.get(); }
        public void setName(String n) { name.set(n); }
        public StringProperty nameProperty() { return name; }

        public int getAge() { return age.get(); }
        public void setAge(int a) { age.set(a); }
        public IntegerProperty ageProperty() { return age; }
    }

    private final ObservableList<Person> people = FXCollections.observableArrayList(
            new Person("Alex", 18),
            new Person("Mara", 20)
    );

    @Override
    public void start(Stage stage) {
        // Table
        TableView<Person> table = new TableView<>(people);
        TableColumn<Person, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Person, Integer> ageCol = new TableColumn<>("Alter");
        ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
        table.getColumns().addAll(nameCol, ageCol);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        // Form
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        Spinner<Integer> ageSpinner = new Spinner<>(0, 120, 18);
        ageSpinner.setEditable(true);
        Button addBtn = new Button("Hinzufügen");
        addBtn.setOnAction(e -> {
            String n = nameField.getText();
            Integer a = ageSpinner.getValue();
            if (n != null && !n.isBlank()) {
                people.add(new Person(n, a));
                nameField.clear();
            }
        });

        HBox form = new HBox(10, new Label("Name:"), nameField,
                new Label("Alter:"), ageSpinner, addBtn);
        form.setPadding(new Insets(10));

        // Statusbar
        Label status = new Label("Bereit");
        HBox statusBar = new HBox(status);
        statusBar.setPadding(new Insets(8));
        statusBar.setStyle("-fx-background-color: -fx-control-inner-background;");

        // Layout: BorderPane -> responsive
        BorderPane root = new BorderPane();
        root.setTop(form);
        root.setCenter(table);
        root.setBottom(statusBar);
        BorderPane.setMargin(table, new Insets(10));

        // Scene
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Dynamische GUI – JavaFX");
        stage.setScene(scene);
        stage.show();

        // Hintergrund-Task simulieren (z. B. Daten laden)
        Task<Void> loadTask = new Task<>() {
            @Override protected Void call() throws Exception {
                updateMessage("Lade Daten…");
                Thread.sleep(1500);
                Platform.runLater(() -> people.addAll(
                        new Person("Sami", 22),
                        new Person("Lina", 19)
                ));
                updateMessage("Daten aktualisiert");
                return null;
            }
        };
        status.textProperty().bind(loadTask.messageProperty());
        new Thread(loadTask, "loader").start();

        // Responsive Verhalten: Spaltenbreiten, wachsen mit Fenster
        VBox.setVgrow(table, Priority.ALWAYS);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
*/