package jp.ac.ecc.progclub.handson.Ranking;

public class User {

    private String name;//名前
    private int tappoint;//ポイント

   public User(String name, int tappoint) {
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
