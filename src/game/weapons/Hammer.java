package game.weapons;

import game.destructible.Destructible;
import game.destructible.Obstacle;

public class Hammer extends Weapon {

    private static final String nomArme = "Hammer";
    public static final double damage = 200;
    private static final double ratioDamageMonster = 1;
    private static final double ratioDamageObstacle = 0.5;
    private static final double price = 300;
    public Hammer(int idArme) {
        super(idArme, nomArme, damage, ratioDamageMonster, ratioDamageObstacle, price);
    }

    public String ascii_art() {
        return "       T                                    \\`.    T\n" +
                "       |    T     .--------------.___________) \\   |    T\n" +
                "       !    |     |//////////////|___________[ ]   !  T |\n" +
                "            !     `--------------'           ) (      | !\n" +
                "                                         mn  '-'      !";
    }

    @Override
    public void attack(Destructible d) {
        if(d instanceof Obstacle) {
            d.hit_me(this.damage*this.ratioDamageObstacle);
        }
        else {
            d.hit_me(this.damage*this.ratioDamageMonster);
        }

    }

}
