package game;

import game.destructible.Monster;
import game.weapons.Weapon;
import game.weapons.WeaponStore;
import static game.constants.ConsoleColors.*;
import java.util.Objects;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {

        // Création du personnage avec demande à l'utilisateur le nom de son personnage :
        Scanner sc = new Scanner(System.in);
        System.out.println("=====================================");
        System.out.println("Bienvenue dans le jeu de rôle !");
        System.out.println("=====================================");
        System.out.println("Nom de votre personnage : ");
        String nom = sc.nextLine();

        // Création du personnage un peu plus précise. Quel race de personnage êtes-vous ?
        System.out.println("=====================================");
        System.out.println("Quel type de personnage êtes-vous ?");
        System.out.println(GREEN+"[0] "+RESET+"Humain");
        System.out.println(GREEN+"[1] "+RESET+"Elfe");
        System.out.println(GREEN+"[2] "+RESET+"Nain");

        int choixRace = sc.nextInt();

        while (choixRace != 0 && choixRace != 1 && choixRace != 2) {
            System.out.println("Choisissez un type de personnage valide : ");
            choixRace = sc.nextInt();
        }
        Player j1 = new Player(nom, choixRace);
        System.out.println("=====================================");
        System.out.println("Voici votre personnage :");
        System.out.println(j1);
        System.out.println(GREEN+"[X] "+RESET+"Commencer l'aventure");
        String choixBegin = "";
        while(!Objects.equals(choixBegin, "X")) {
            choixBegin = sc.nextLine();
        }

        // Création du magasin d'armes :
        WeaponStore store = new WeaponStore();
        store.displayStore(j1);

        // Création de la map :
        Map map = new Map(10, 10);
        map.initMap();

        while (!map.isSortie()) {

            if(j1.isDead()) {
                return;
            }
            // Affichage de la map :
            map.afficherMap();

            // Déplacement du joueur :
            System.out.println("=====================================");
            System.out.println("Choisissez votre action : ");
            System.out.println(GREEN+"[0] "+RESET+"up");
            System.out.println(GREEN+"[1] "+RESET+"down");
            System.out.println(GREEN+"[2] "+RESET+"left");
            System.out.println(GREEN+"[3] "+RESET+"right");
            System.out.println(GREEN+"[4] "+RESET+"Voir inventaire");
            int choixAction = sc.nextInt();
            while (choixAction != 0 && choixAction != 1 && choixAction != 2 && choixAction != 3 && choixAction != 4) {
                System.out.println("Choisissez une action valide : ");
                choixAction = sc.nextInt();
            }

            if(choixAction == 4) {
                System.out.println("=====================================");
                System.out.println("Voici votre inventaire :");
                System.out.println("Porte-feuille : " + j1.getMoney());
                System.out.println(j1.getArmes());
                System.out.println(GREEN+"[C] "+RESET+"Changez d'arme");
                System.out.println(GREEN+"[X] "+RESET+"Quitter inventaire");
                String choixQuit = "";
                while(!Objects.equals(choixQuit, "X") && !Objects.equals(choixQuit, "C")) {
                    choixQuit = sc.nextLine();
                }
                if(Objects.equals(choixQuit, "C")) {
                    System.out.println("Choisissez votre arme : ");
                    for (Weapon arme : j1.getArmes()) {
                        System.out.println(GREEN+"[" + arme.getIdArme() + "] "+RESET+arme.getName() + " (prix : " + arme.getPrice() + ")");
                    }
                    int choixArme = sc.nextInt();
                    while (choixArme != 1 && choixArme != 2 && choixArme != 3) {
                        System.out.println("Choisissez une arme valide : ");
                        choixArme = sc.nextInt();
                    }
                    j1.changeWeapon(choixArme);
                }
            }

            j1.deplacement(choixAction, map, store);


        }
        System.out.println("=====================================");
        System.out.println("Vous avez gagné ! Vous avez trouvé la sortie !");

    }
}