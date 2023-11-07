package game.destructible;

import game.Player;
import game.destructible.Destructible;

public class Monster extends Destructible {

    public static final int damage = 100;

    public Monster(double life) {
        super(life);
    }

    public void attack(Player player) {
        player.hit_me(this.damage);
    }

    public String getDamage() {
        return "Le monstre vous a infligé "+this.damage+" points de dégâts !";
    }
}
