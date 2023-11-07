package game;

import game.destructible.Monster;
import game.weapons.WeaponStore;

import java.util.Objects;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {

        // Création du personnage avec demande à l'utilisateur le nom de son personnage :
        Scanner sc = new Scanner(System.in);
        System.out.println("Nom de votre personnage : ");
        String nom = sc.nextLine();

        // Création du personnage un peu plus précise. Quel race de personnage êtes-vous ?
        System.out.println("Quel type de personnage êtes-vous ?");
        System.out.println("[0] Humain");
        System.out.println("[1] Elfe");
        System.out.println("[2] Nain");

        int choixRace = sc.nextInt();
        while (choixRace != 0 && choixRace != 1 && choixRace != 2) {
            System.out.println("Choisissez un type de personnage valide : ");
            choixRace = sc.nextInt();
        }

        Player j1 = new Player(nom, choixRace);
        System.out.println(j1);

        // Création du magasin d'armes :
        WeaponStore store = new WeaponStore();
        store.displayStore(j1);

        // Création de la map :
        Map map = new Map(10, 10);
        map.initMap();

        while (!map.isSortie()) {
            // Affichage de la map :
            map.afficherMap();

            // Déplacement du joueur :
            System.out.println("Choisissez votre action : ");
            System.out.println("[0] up");
            System.out.println("[1] down");
            System.out.println("[2] left");
            System.out.println("[3] right");
            System.out.println("[4] Voir inventaire");

            int choixAction = sc.nextInt();
            while (choixAction != 0 && choixAction != 1 && choixAction != 2 && choixAction != 3 && choixAction != 4) {
                System.out.println("Choisissez une action valide : ");
                choixAction = sc.nextInt();
            }

            if(choixAction == 4) {
                System.out.println("Voici votre inventaire :");
                System.out.println("Porte-feuille : " + j1.getMoney());
                System.out.println(j1.getArmes());
                System.out.println("[X] Quitter inventaire");
                String choixQuit = "";
                while(!Objects.equals(choixQuit, "X")) {
                    choixQuit = sc.nextLine();
                }
            }

            j1.deplacement(choixAction, map, store);


        }
    }
}