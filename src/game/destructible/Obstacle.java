package game.destructible;

import static game.constants.ConsoleColors.BLUE;
import static game.constants.ConsoleColors.RESET;

public class Obstacle extends Destructible {

    private String representation = BLUE+"[O]"+RESET;


    public Obstacle(double life) {
        super(life);
    }
    public String toString() {
        return this.representation;
    }
}
