package p1;

import java.util.ArrayList;
import java.util.Arrays;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class Controller extends AbstractGroup {
    public Group[] groupArray = new Group[10];
    int groupArraySize = 0;
    int threshold = 10;

    @FXML
    private Button btnCreateQuestion;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnGroups;

    @FXML
    private Button btnOverview;

    @FXML
    private Button btnCreateGroup;

    @FXML
    Button btnAddQuestion;

    @FXML
    private TextArea txtQuestion;

    @FXML
    private Label lblErrorMessage;

    @FXML
    private TextField txtTitle;

    @FXML
    ComboBox<String> comboBox;

    @FXML
    private Button btnDeleteGroup;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<Question> listViewR;

    ArrayList<String> al = new ArrayList<String>();

    public void initActions() {
        // Detecting mouse clicked
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                // listViewR.getItems().clear();
                // Check wich list index is selected then set txtContent value for that index
                // System.out.println(groupArray[comboBox.getSelectionModel().getSelectedIndex()].list.toString());
                listViewR.getItems().clear();
                if (listView.getSelectionModel().getSelectedItem() == null) {
                    lblErrorMessage.setText("No group selected!");
                } else {
                    listViewR.getItems().addAll(groupArray[listView.getSelectionModel().getSelectedIndex()].arrayList);
                }

            }
        });
    }

    public static ArrayList<Question> removeQuestion(ArrayList<Question> list, int index) {
        list.remove(index);
        return list;
    }

    public static Group[] removeGroup(Group[] arr, int index) {

        // If the array is empty
        // or the index is not in array range
        // return the original array
        if (arr == null || index < 0
                || index >= arr.length) {

            return arr;
        }

        // Create another array of size one less
        Group[] anotherArray = new Group[arr.length - 1];

        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {

            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }

            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }

        // return the resultant array
        return anotherArray;
    }

    public void textChangedHandler() {
        txtQuestion.setWrapText(true);
        listView.setPrefWidth(10);
    }

    public void handleClicks(ActionEvent actionEvent) {
        if (this.groupArraySize == threshold) {
            Group[] newArr = Arrays.copyOf(this.groupArray, threshold * 2);
            threshold = threshold * 2;
            groupArray = newArr;

        }
        if (actionEvent.getSource() == btnExit) {
            Platform.exit();

        }
        if (actionEvent.getSource() == btnCreateGroup) {
            if (txtTitle.getText() == "") {
                lblErrorMessage.setText("Cant add empty group!");
                lblErrorMessage.setVisible(true);

            } else {
                lblErrorMessage.setVisible(false);
                this.groupArray[this.groupArraySize] = new Group(txtTitle.getText());
                listView.getItems().add(txtTitle.getText());
                comboBox.getItems().add(txtTitle.getText());
                groupArraySize++;
            }
        }

        if (actionEvent.getSource() == btnDeleteGroup) {
            int index = listView.getSelectionModel().getSelectedIndex();
            if (index < 0 || index >= groupArray.length) {
                lblErrorMessage.setText("Error Deleting Group..");
            } else {
                // remove group from group array
                removeGroup(groupArray, index);
                comboBox.getItems().remove(index);
                listView.getItems().remove(index);
                // listViewR.getItems().clear();
                groupArray[index].arrayList.clear();
            }
        }
        if (actionEvent.getSource() == btnAddQuestion) {

            if (groupArraySize == 0 || comboBox.getValue() == null) {
                lblErrorMessage.setText("Must Create or Select a Group first!");
                lblErrorMessage.setVisible(true);
            } else {
                lblErrorMessage.setVisible(false);
                String s = comboBox.getValue();
                for (int i = 0; i < groupArraySize; i++) {
                    if (s.equals(groupArray[i].groupName)) {
                        groupArray[i].arrayList.add(new Question(txtQuestion.getText(), 0));
                    }
                }
            }
        }
    }
}