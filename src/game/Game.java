package game;

import game.weapons.Weapon;
import game.weapons.WeaponStore;

import java.util.Objects;
import java.util.Scanner;

import static game.constants.ConsoleColors.GREEN;
import static game.constants.ConsoleColors.RESET;

public class Game {
    private Map map;
    private Scanner sc;
    private Player j1;
    private WeaponStore store;

    public Game() {
        this.sc = new Scanner(System.in);
        this.store = new WeaponStore();
        this.map = new Map(10, 10);
    }

    public void demarrerJeu() {
        initialiserJoueur();
        afficherMagasin();
        jouer();
    }

    private void initialiserJoueur() {
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
        this.j1 = new Player(nom, choixRace);
        System.out.println("=====================================");
        System.out.println("Voici votre personnage :");
        System.out.println(this.j1);
        System.out.println(GREEN+"[X] "+RESET+"Commencer l'aventure");
        String choixBegin = "";
        while(!Objects.equals(choixBegin, "X")) {
            choixBegin = sc.nextLine();
        }
    }

    private void afficherMagasin() {
        // Affichage du magasin d'armes :
        this.store.displayStore(this.j1);
    }

    private void jouer() {
        this.map.initMap();

        while (!this.map.isSortie()) {

            if (this.j1.isDead()) {
                return;
            }
            // Affichage de la map :
            this.map.afficherMap();

            // Déplacement du joueur :
            System.out.println("=====================================");
            System.out.println("Choisissez votre action : ");
            System.out.println(GREEN + "[0] " + RESET + "up");
            System.out.println(GREEN + "[1] " + RESET + "down");
            System.out.println(GREEN + "[2] " + RESET + "left");
            System.out.println(GREEN + "[3] " + RESET + "right");
            System.out.println(GREEN + "[4] " + RESET + "Voir inventaire");
            int choixAction = sc.nextInt();
            while (choixAction != 0 && choixAction != 1 && choixAction != 2 && choixAction != 3 && choixAction != 4) {
                System.out.println("Choisissez une action valide : ");
                choixAction = sc.nextInt();
            }

            if (choixAction == 4) {
                System.out.println("=====================================");
                System.out.println("Voici votre inventaire :");
                System.out.println("Porte-feuille : " + this.j1.getMoney());
                System.out.println(this.j1.getArmes());
                System.out.println(GREEN + "[C] " + RESET + "Changez d'arme");
                System.out.println(GREEN + "[X] " + RESET + "Quitter inventaire");
                String choixQuit = "";
                while (!Objects.equals(choixQuit, "X") && !Objects.equals(choixQuit, "C")) {
                    choixQuit = sc.nextLine();
                }
                if (Objects.equals(choixQuit, "C")) {
                    System.out.println("Choisissez votre arme : ");
                    for (Weapon arme : this.j1.getArmes()) {
                        System.out.println(GREEN + "[" + arme.getIdArme() + "] " + RESET + arme.getName() + " (prix : " + arme.getPrice() + ")");
                    }
                    int choixArme = sc.nextInt();
                    while (choixArme != 1 && choixArme != 2 && choixArme != 3) {
                        System.out.println("Choisissez une arme valide : ");
                        choixArme = sc.nextInt();
                    }
                    this.j1.changeWeapon(choixArme);
                }
            }

            this.j1.deplacement(choixAction, this.map, this.store);
        }
        System.out.println("=====================================");
        System.out.println("Vous avez gagné ! Vous avez trouvé la sortie !");

    }
}
