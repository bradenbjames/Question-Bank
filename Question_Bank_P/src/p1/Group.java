package p1;

public class Group extends AbstractGroup {
    public ObjectLinkedList list;
    String groupName;

    public Group(String groupName) {
        this.groupName = groupName;
        list = new ObjectLinkedList();
    }

}
