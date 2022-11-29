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
    private Group[] groupArray = new Group[10];
    private int groupArraySize = 0;
    private int threshold = 10;

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
    // public ListCell<Question> intialize() {
    // listViewR.relocate(10, 210);
    // listViewR.setPrefSize(this.getPrefWidth() - 20, this.getPrefHeight() - 250);
    // listViewR.setCellFactory(new Callback<ListView<Question>,
    // ListCell<Question>>() {
    // @Override
    // public ListCell<String> call(ListView<String> list) {
    // final ListCell cell = new ListCell() {
    // private Text text;

    // @Override
    // public void updateItem(Object item, boolean empty) {
    // super.updateItem(item, empty);
    // if (!isEmpty()) {
    // text = new Text(item.toString());
    // text.setWrappingWidth(listViewR.getPrefWidth());
    // setGraphic(text);
    // }
    // }
    // };

    // return cell;
    // }
    // });
    // }

    ArrayList<String> al = new ArrayList<String>();

    public void initActions() {
        // Detecting mouse clicked
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                listViewR.getItems().clear();
                if (listView.getSelectionModel().getSelectedItem() == null) {
                    lblErrorMessage.setText("No group selected!");
                } else {
                    // listViewR.getItems().addAll(groupArray[listView.getSelectionModel().getSelectedIndex()].arrayList);
                    for (int i = 0; i < groupArray.length; i++) {
                        if (groupArray[i] == null) {
                            throw new ListException("null group");
                        }
                        for (int j = 0; j < groupArray[i].list.size(); j++) {
                            listViewR.getItems().addAll(groupArray[i].list.printList());
                        }
                    }
                }

            }
        });
    }

    public void textChangedHandler() {
        txtQuestion.setWrapText(true);
        listView.setPrefWidth(10);
    }

    public void handleClicks(ActionEvent actionEvent) {
        // allocate new memory for groupArray
        if (this.groupArraySize == threshold) {
            Group[] newArr = Arrays.copyOf(this.groupArray, threshold * 2);
            threshold = threshold * 2;
            groupArray = newArr;
            System.out.println(this.groupArray.length);
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
                        groupArray[i].arrayList.add(new Question(txtQuestion.getText(), 0));
                        groupArray[i].list.add(0,
                                new Question(txtQuestion.getText(), 0));
                    }
                }
            }
        }
    }
}
