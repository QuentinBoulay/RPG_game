package game;

import game.interfaces.ActionsPlayer;
import game.weapons.Weapon;
import game.weapons.WeaponStore;

import java.util.ArrayList;

public class Player implements ActionsPlayer {

    private String nom;

    private int race;
    private int life;

    private double money;
    private ArrayList<Weapon> armes;

    public Player(String nom, int race) {
        this.nom = nom;
        this.race = race;
        this.life = 300;
        this.money = 50;
        this.armes = new ArrayList<Weapon>();
    }

    public void buyWeapon(Weapon arme, WeaponStore store) {
        if(!store.getStore().contains(arme)) {
            System.out.println("L'arme n'est pas présente dans le catalogue");
        }
        else {
            if (this.money < arme.getPrice()) {
                System.out.println(this.nom + ", tu n'as pas assez d'argent pour cette arme (prix arme : " + arme.getPrice() + ", argent possédé : " + this.money + ")");
            } else {
                this.armes.add(arme);
                store.getStore().remove(1);
                this.money = this.money - arme.getPrice();
            }
        }

    }

    public String toString() {
        return "Nom du joueur : "+this.nom+"\n"+"Points de vie : "+this.life+"\n"+"Money : "+this.money +"\n"+"Armes : "+this.armes.toString()+"\n";
    }

    // Getters

    public String getNom() {
        return this.nom;
    }

    public String getRace() {
        String raceLabel = "";

        if(this.race == 0) {
            raceLabel = "Humain";
        }
        else if(this.race == 1) {
            raceLabel = "Elfe";
        }
        else {
            raceLabel = "Nain";
        }

        return raceLabel;
    }

    public int getLife() {
        return this.life;
    }

    public double getMoney() {
        return this.money;
    }

    public ArrayList getArmes() {
        return this.armes;
    }
}
