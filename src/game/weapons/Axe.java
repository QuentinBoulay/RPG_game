package game.weapons;

import game.destructible.Destructible;
import game.destructible.Obstacle;

public class Axe extends Weapon {

    private static final String nomArme = "Axe";

    public static final double damage = 100;
    private static final double ratioDamageMonster = 0.8;
    private static final double ratioDamageObstacle = 0.2;
    private static final float price = 120;

    public Axe(int idArme) {
        super(idArme, nomArme, damage, ratioDamageMonster, ratioDamageObstacle, price);
    }

    // récupération de la méthode de la classe mère abstraite "weapon"
    public String ascii_art() {
        return "  _________________.---.______\n" +
                " (_(______________(_o o_(____()\n" +
                "              .___.'. .'.___.\n" +
                "              \\ o    Y    o /\n" +
                "               \\ \\__   __/ /\n" +
                "                '.__'-'__.'\n" +
                "                    '''";
    }

    public void attack(Destructible d) {
        if(d instanceof Obstacle) {
            d.hit_me(this.damage*this.ratioDamageObstacle);
        }
        else {
            d.hit_me(this.damage*this.ratioDamageMonster);
        }

    }

}
