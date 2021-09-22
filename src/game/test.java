package game;

import java.util.ArrayList;

public class test {
    private ArrayList<String> hello = new ArrayList<>();

    public ArrayList<String> getTest() {
        return hello;
    }

    public void setHello(String hello) {
        this.hello.add(hello);
    }

    public static void main(String[] args) {
        game.test test1 = new test();
        test1.setHello("Hello");
        System.out.println(test1.getTest());
    }

}
