package d5.solitaire;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        var game = new Game();
        game.play(System.in, System.out, new Random(0));
    }
}
