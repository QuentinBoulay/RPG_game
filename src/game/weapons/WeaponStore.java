package game.weapons;

import game.Player;
import game.weapons.Axe;
import game.weapons.Bow;
import game.weapons.Hammer;
import game.weapons.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

public class WeaponStore {
    private ArrayList<Weapon> weapons = new ArrayList<>();

    public WeaponStore() {
        weapons.add(new Axe(1));
        weapons.add(new Hammer(2));
        weapons.add(new Bow(3));

    }

    public String toString() {
        return this.weapons.toString()+"\n\n";
    }

    public void displayStore(Player player) {
        // Affichage du magasin :
        if(this.weapons.isEmpty()) {
            System.out.println("Le magasin est vide");
            return;
        }
        System.out.println("Bienvenue dans le magasin d'armes");
        System.out.println("Voici les armes disponibles : ");
        System.out.println(this.toString());

        // Choix de l'arme du personnage :
        System.out.println("Choisissez votre arme : ");
        Scanner sc = new Scanner(System.in);

        for (Weapon arme : this.weapons) {
            System.out.println("[" + arme.getIdArme() + "] " + arme.getName() + " (prix : " + arme.getPrice() + ")");
        }

        int choixArme = sc.nextInt();
        while (choixArme != 1 && choixArme != 2 && choixArme != 3) {
            System.out.println("Choisissez une arme valide : ");
            choixArme = sc.nextInt();
        }
        player.buyWeapon(this.getWeaponInStore(choixArme), this);
    }

    // Getters
    public ArrayList getStore() {
        return this.weapons;
    }
    public Weapon getWeaponInStore(int idArme) {
        for (Weapon arme : this.weapons) {
            if(idArme == arme.getIdArme()) {
                return arme;
            }
        }
        return null;
    }
}
