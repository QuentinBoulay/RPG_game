package game.weapons;

import game.destructible.Destructible;
import game.destructible.Obstacle;

public class Bow extends Weapon {

    private static final String nomArme = "Bow";
    public static final double damage = 50;
    private static final double ratioDamageMonster = 2.5;
    private static final double ratioDamageObstacle = 0.5;
    private static final double price = 100;

    public Bow(int idArme) {
        super(idArme, nomArme, damage, ratioDamageMonster, ratioDamageObstacle, price);
    }

    public String ascii_art() {
        return "                (\n" +
                "                 \\\n" +
                "                  )\n" +
                "             ##-------->        b'ger\n" +
                "                  )\n" +
                "                 /\n" +
                "                (";
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

  //  Nom du perso
  //  Choisi ton arme
  //  [0] Axe
 //   [1] Bow

 //   1) construis ton personnage
 //   2) création d'une matrice (String[][]) et l'afficher
    //   3) nouveau choix d'action pour se déplacer :
  //        [0] up
  //        [1] down
  //        [2] left
  //        [3] right
  //    4) il y a des monstres et obstacles placés aléatoirement sur la matrice
    //  5) lorsque je rencontre un monstre, je peux me battre avec lui ou fuir

}
