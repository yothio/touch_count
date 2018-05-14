package jp.ac.ecc.progclub.handson;

public class User {

    public String name;
    public int tappoint;

    User(String name, int tappoint) {
        this.name = name;
        this.tappoint = tappoint;
    }

    public String getName() {
        return name;
    }

    public int getTappoint() {
        return tappoint;
    }
}
