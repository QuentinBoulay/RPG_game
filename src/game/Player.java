package game;

import game.destructible.Destructible;
import game.destructible.Monster;
import game.destructible.Obstacle;
import game.interfaces.ActionsPlayer;
import game.weapons.Weapon;
import game.weapons.WeaponStore;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static game.constants.ConsoleColors.*;

public class Player implements ActionsPlayer {

    private String nom;
    private int race;
    private int life;
    private double money = 10000;
    private ArrayList<Weapon> armes;
    private boolean isDead = false;


    public Player(String nom, int race) {
        this.nom = nom;
        this.race = race;
        this.life = 300;
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
                store.getStore().remove(arme);
                this.money = this.money - arme.getPrice();
            }
        }

    }

    public void changeWeapon(int choixArme) {
        if(this.armes.isEmpty()) {
            System.out.println("Vous n'avez pas d'arme");
            return;
        }
        if(choixArme >= this.armes.size()) {
            System.out.println("Vous n'avez pas d'arme à cet emplacement");
            return;
        }
        Weapon arme = this.armes.get(choixArme);
        // placer arme au début de la liste sans supprimer l'arme qui était à la première position :
        this.armes.add(0, arme);
        this.armes.remove(choixArme+1);
        System.out.println("Vous avez changé d'arme. Vous avez maintenant "+arme.getName());
    }

    @Override
    public void deplacement(int choixAction, Map map, WeaponStore weaponStore) {
        int[] positionJoueur = map.getPositionJoueur();
        Object[][] mapGame = map.getMap();

        // Calculer la nouvelle position en fonction du choix de l'action
        int newX = positionJoueur[0];
        int newY = positionJoueur[1];
        switch (choixAction) {
            case 0: newX--; break; // up
            case 1: newX++; break; // down
            case 2: newY--; break; // left
            case 3: newY++; break; // right
        }

        // Vérifier si le déplacement est dans les limites de la carte
        if (newX < 0 || newX >= mapGame.length || newY < 0 || newY >= mapGame[0].length) {
            System.out.println("Déplacement impossible : hors de la carte.");
            return;
        }

        // Vérifier le contenu de la nouvelle position
        Object caseContent = mapGame[newX][newY];

        // Agir en fonction du contenu de la case
        if (caseContent instanceof Monster) {
            // Interaction avec un monstre
            if(meetDestructible(this.armes.get(0), weaponStore, (Monster) caseContent)) {
             mapGame[newX][newY] = WHITE+"[ ]" ;
            }
        } else if (caseContent instanceof Obstacle) {
            // Interaction avec un obstacle
            if(meetDestructible(this.armes.get(0), weaponStore, (Obstacle) caseContent)) {
                mapGame[newX][newY] = WHITE+"[ ]"+RESET ;
            }
        } else if (caseContent instanceof WeaponStore) {
            // Interaction avec un magasin
            weaponStore.displayStore(this); // Ici le joueur ne se déplace pas, il interagit juste avec le magasin
        } else {
            // Déplacement autorisé
            mapGame[positionJoueur[0]][positionJoueur[1]] = WHITE+"[ ]"+RESET; // Effacer la trace du joueur de l'ancienne position
            positionJoueur[0] = newX;
            positionJoueur[1] = newY;
            mapGame[newX][newY] = PURPLE+"[X]"+RESET; // Marquer la nouvelle position du joueur
        }
    }
    // Rencontre avec un obstacle
    @Override
    public boolean meetDestructible(Weapon arme, WeaponStore store, Destructible destructible) {
        System.out.println("=====================================");
        String destructibleType = destructible instanceof Monster ? "monstre" : "obstacle";
        System.out.println("Vous êtes tombé sur un " + destructibleType);

        Scanner sc = new Scanner(System.in);
        while(destructible.getLife() > 0 && (destructibleType.equals("obstacle") || this.life > 0)) {
            System.out.println("Vous avez " + this.life + " points de vie");
            System.out.println("Le " + destructibleType + " a " + destructible.getLife() + " points de vie");
            System.out.println("Voulez-vous l'attaquer ? "+GREEN+"[0] "+RESET+"Oui "+GREEN+"[1] "+RESET+"Non");

            int choixAttaque = -1;
            boolean entreeValide = false;

            while (!entreeValide) {
                try {
                    choixAttaque = sc.nextInt();
                    if (choixAttaque == 0 || choixAttaque == 1) {
                        entreeValide = true;
                    } else {
                        System.out.println("Choisissez une action valide : Oui (0) ou Non (1)");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erreur : Veuillez entrer un numéro valide : Oui (0) ou Non (1)");
                    sc.next(); // important pour éviter une boucle infinie
                }
            }

            if (choixAttaque == 0) {
                arme.attack(destructible);
                if (destructible instanceof Monster) {
                    System.out.println("Le monstre vous attaque !");
                    ((Monster) destructible).attack(this);
                    System.out.println(((Monster) destructible).getDamage());
                }
            } else {
                System.out.println("Vous avez fui");
                return false;
            }

            // Gérer la mort du joueur ou de la créature
            if (this.life <= 0) {
                System.out.println("=====================================");
                System.out.println("Vous êtes mort");
                System.out.println("Game Over. Vous avez perdu. Vous pouvez recommencer.");
                this.isDead = true;
                return false;
            } else if (destructible.getLife() <= 0 && destructible instanceof Monster) {
                int randomMoney = (int) (Math.random() * (100 - 50)) + 50;
                System.out.println("=====================================");
                System.out.println("Vous avez tué le monstre. Vous venez de gagner "+randomMoney+" pièces d'or");
                this.money = this.money + randomMoney;
            }
        }
        System.out.println("Vous avez détruit le " + destructibleType + ". Vous pouvez avancer");
        return destructible.getLife() <= 0;
    }

    public String toString() {
        return "Nom du joueur : "+this.nom+"\n"+"Race du joueur : "+this.getRace()+"\n"+"Points de vie : "+this.life+"\n"+"Money : "+this.money +"\n"+"Armes : "+this.armes.toString()+"\n";
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

    public ArrayList<Weapon> getArmes() {
        return this.armes;
    }

    public void hit_me(double damage) {
        this.life = this.life - (int)damage;
    }

    public boolean isDead() {
    	return this.isDead;
    }
}
