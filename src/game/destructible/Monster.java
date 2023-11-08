package game.destructible;

import game.Player;
import game.destructible.Destructible;

import static game.constants.ConsoleColors.RED;
import static game.constants.ConsoleColors.RESET;

public class Monster extends Destructible {

    public static final int damage = 100;
    public String representation = RED+"[M]"+RESET;

    public Monster(double life) {
        super(life);
    }

    public void attack(Player player) {
        player.hit_me(this.damage);
    }

    public String getDamage() {
        return "Le monstre vous a infligé "+this.damage+" points de dégâts !";
    }
    public String toString() {
        return this.representation;
    }
}
