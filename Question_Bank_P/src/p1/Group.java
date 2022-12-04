package p1;

import java.util.ArrayList;

public class Group {
    // public ObjectLinkedList list;
    public ArrayList<Question> arrayList;
    String groupName;
    int listCounter = 0;

    public Group(String groupName) {
        this.groupName = groupName;
        // list = new ObjectLinkedList();
        arrayList = new ArrayList<Question>();
    }

}
