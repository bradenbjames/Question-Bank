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

    private ListView<String> listView;
    @FXML
    private ListView<String> listViewR;

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
        if (actionEvent.getSource() == btnAddQuestion) {
            // groupArray[comboBox.getSelectionModel().getSelectedIndex()].list.add(
            // groupArray[comboBox.getSelectionModel().getSelectedIndex()].listCounter,
            // new Question(txtQuestion.getText(), 0));
            // groupArray[comboBox.getSelectionModel().getSelectedIndex()].listCounter++;
            if (groupArraySize == 0) {
                lblErrorMessage.setText("Must Create a group first!");
                lblErrorMessage.setVisible(true);
            }
            if (comboBox.getValue() == null) {
                lblErrorMessage.setText("Select a group first!");
                lblErrorMessage.setVisible(true);
            } else {
                lblErrorMessage.setVisible(false);
                String s = comboBox.getValue();
                for (int i = 0; i < groupArraySize; i++) {
                    if (s.equals(groupArray[i].groupName)) {
                        groupArray[i].arrayList.add(txtQuestion.getText());
                    }
                }
            }
        }
    }
}
