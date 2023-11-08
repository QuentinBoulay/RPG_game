package game.weapons;

import game.Player;
import game.weapons.Axe;
import game.weapons.Bow;
import game.weapons.Hammer;
import game.weapons.Weapon;

import java.util.ArrayList;
import java.util.Scanner;

import static game.constants.ConsoleColors.*;

public class WeaponStore {
    private ArrayList<Weapon> weapons = new ArrayList<>();
    private String representation = YELLOW+"[S]"+RESET;

    public WeaponStore() {
        weapons.add(new Axe(1));
        weapons.add(new Hammer(2));
        weapons.add(new Bow(3));

    }

    public void displayStore(Player player) {
        // Affichage du magasin :
        if(this.weapons.isEmpty()) {
            System.out.println("Le magasin est vide");
            return;
        }
        System.out.println("=====================================");
        System.out.println("Bienvenue dans le magasin d'armes");
        System.out.println("Voici les armes disponibles : ");
        for (Weapon arme : this.weapons) {
            System.out.println("Id arme : "+arme.getIdArme());
            System.out.println("Nom de l'arme : "+arme.getName());
            System.out.println("Damage : "+arme.getDamage());
            System.out.println("Price : "+arme.getPrice());
            System.out.println("Représentation : \n"+arme.ascii_art()+"\n\n");
        }

        // Choix de l'arme du personnage :
        System.out.println("Choisissez votre arme : ");
        Scanner sc = new Scanner(System.in);

        for (Weapon arme : this.weapons) {
            System.out.println(GREEN+"[" + arme.getIdArme() + "] "+RESET+arme.getName() + " (prix : " + arme.getPrice() + ")");
        }
        if(!player.getArmes().isEmpty()) {
            System.out.println(GREEN+"[0] "+RESET+"Quitter le magasin");
        }

        int choixArme = sc.nextInt();
        while (choixArme != 1 && choixArme != 2 && choixArme != 3 && choixArme != 0) {
            System.out.println("Choisissez une arme valide : ");
            choixArme = sc.nextInt();
        }
        if(choixArme == 0) {
            return;
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

    public String toString() {
        return this.representation;
    }

}
