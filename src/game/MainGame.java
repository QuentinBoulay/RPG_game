package game;

import game.destructible.Monster;
import game.weapons.WeaponStore;

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
        while(choixRace != 0 && choixRace != 1 && choixRace != 2) {
            System.out.println("Choisissez un type de personnage valide : ");
            choixRace = sc.nextInt();
        }


        Player j1 = new Player(nom, choixRace);

        // Création du magasin d'armes :
        WeaponStore store = new WeaponStore();

        // Choix de l'arme du personnage :
        System.out.println("Choisissez votre arme : ");
        System.out.println("[0] Axe");
        System.out.println("[1] Bow");
        System.out.println("[2] Hammer");

        int choixArme = sc.nextInt();
        while(choixArme != 0 && choixArme != 1 && choixArme != 2) {
            System.out.println("Choisissez une arme valide : ");
            choixArme = sc.nextInt();
        }

        if(choixArme == 0) {
            j1.buyWeapon(store.getWeaponInStore(0), store);
        }
        else if(choixArme == 1) {
            j1.buyWeapon(store.getWeaponInStore(1), store);
        }
        else {
            j1.buyWeapon(store.getWeaponInStore(2), store);
        }

        // Création d'une carte en 2d avec des "*" sur laquelle le joueur va se déplacer,avec des points d'interaction (store, obstacles, monstres). La map doit être générée aléatoirement comme ceci :
        // Création de la matrice comme ceci :
         String[][] map = new String[5][5];
         for(int i = 0; i < map.length; i++) {
             for(int j = 0; j < map[i].length; j++) {
                 if(Math.random() < 0.1) {
                     map[i][j] = "[S]";
                 }
                 else if(Math.random() < 0.1) {
                     map[i][j] = "[M]";
                 }
                 else if(Math.random() < 0.1) {
                     map[i][j] = "[O]";
                 }
                 else {
                     map[i][j] = "[ ]";
                 }
                 // placer le joueur sur la map :
                 map[map.length-1][0] = "[X]";
                 // placer la sortie sur la map :
                 map[0][map.length-1] = "[?]";
            }

         }

         // Tant que le joueur n'a pas atteint la case [?], on continue de jouer :
        while(map[0][map.length-1] != "[X]") {
            // Affichage de la map :
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[i].length; j++) {
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            // Choix de l'action du joueur :
            System.out.println("Choisissez votre action : ");
            System.out.println("[0] up");
            System.out.println("[1] down");
            System.out.println("[2] left");
            System.out.println("[3] right");

            int choixAction = sc.nextInt();
            while (choixAction != 0 && choixAction != 1 && choixAction != 2 && choixAction != 3) {
                System.out.println("Choisissez une action valide : ");
                choixAction = sc.nextInt();
            }

            // Déplacement du joueur :
            if (choixAction == 0) {
                // Déplacement vers le haut :
                map[map.length - 1][0] = "[ ]";
                map[map.length - 2][0] = "[X]";
            } else if (choixAction == 1) {
                // Déplacement vers le bas :
                map[map.length - 1][0] = "[ ]";
                map[map.length - 1][1] = "[X]";
            } else if (choixAction == 2) {
                // Déplacement vers la gauche :
                map[map.length - 1][0] = "[ ]";
                map[map.length - 1][0] = "[X]";
            } else {
                // Déplacement vers la droite :
                map[map.length - 1][0] = "[ ]";
                map[map.length - 1][1] = "[X]";
            }
        }

    }
}