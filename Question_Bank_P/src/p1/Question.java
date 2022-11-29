package p1;

public class Question {
    private String data;
    int group;

    public Question(String data, int group) {
        this.data = data;
        this.group = group;
    }

    @Override
    public String toString() {
        return this.data;
    }
}
