package p1;

import java.util.Arrays;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private MenuButton menuButton;

    @FXML
    Button btnAddQuestion;

    @FXML
    private TextArea txtQuestion;

    @FXML
    private TextField txtTitle;

    @FXML
    private ListView<String> listView;

    @FXML
    private ListView<String> listViewR;

    public void setup() {
        for (Group g : groupArray) {
            int i = 0;
            this.menuButton.getItems().add(new MenuItem(groupArray[0].groupName));
            i++;
        }

    }

    public void btnAddQuestionClicked() {

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
        if (actionEvent.getSource() == btnCreateQuestion) {
            if (groupArraySize == 0) {
                this.groupArray[this.groupArraySize] = new Group(txtTitle.getText());
                this.groupArray[this.groupArraySize].list.add(0,
                        new Question(txtTitle.getText(), txtQuestion.getText(), 1));
                // txtQuestion.getText(), 0));
                this.listView.getItems().add(this.groupArray[this.groupArraySize].groupName);
                groupArraySize++;
            } else {
                this.groupArray[this.groupArraySize] = new Group(txtTitle.getText());
                this.groupArray[this.groupArraySize].list.add(0,
                        new Question(txtTitle.getText(), txtQuestion.getText(), 1));
                // txtQuestion.getText(), 0));
                this.listView.getItems().add(this.groupArray[this.groupArraySize].groupName);
                groupArraySize++;
            }
            listView.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    // Check wich ListView index is selected then set txtContent value for that
                    // index
                    displayGroupQuestions();
                }
            });
        }
        if (actionEvent.getSource() == btnAddQuestion) {
            setup();
            for (int i = 0; i < groupArray.length; i++) {

                if (menuButton.getItems().get(i).toString() == groupArray[i].groupName) {

                }
            }
        }
    }

    public void displayGroupQuestions() {
        listViewR.getItems().clear();
        boolean b = true;

        while (b) {
            int i = 0;
            Question q = this.groupArray[this.listView.getSelectionModel().getSelectedIndex()].list.get(i);
            i++;
            System.out.println(q.data);
            listViewR.getItems().add(q.data);
            b = false;
        }
    }
}
