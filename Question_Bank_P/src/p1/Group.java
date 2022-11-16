package p1;

import java.util.ArrayList;

public class Group extends AbstractGroup {
    // public ObjectLinkedList list;
    public ArrayList<String> arrayList;
    String groupName;
    int listCounter = 0;

    public Group(String groupName) {
        this.groupName = groupName;
        // list = new ObjectLinkedList();
        arrayList = new ArrayList<String>();
    }

}
