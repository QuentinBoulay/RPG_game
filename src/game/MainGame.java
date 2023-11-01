package game;

import game.destructible.Monster;
import game.weapons.WeaponStore;

public class MainGame {
    public static void main(String[] args) {

        Player j1 = new Player("Quentin");

        WeaponStore store = new WeaponStore();

        System.out.println("------------------------------------------------------\nLe catalogue d'armes : \n------------------------------------------------------\n\n"+store);
        System.out.println("------------------------------------------------------\nJoueurs : \n------------------------------------------------------\n");
        System.out.println(j1);

        j1.buyWeapon(store.getWeaponInStore(1), store);

        Monster m1 = new Monster(100);
        m1.hit(j1);


    }
}