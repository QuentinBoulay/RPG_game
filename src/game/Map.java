package game;

import game.destructible.Monster;
import game.destructible.Obstacle;
import game.weapons.WeaponStore;

import java.util.ArrayList;
import java.util.List;

import static game.constants.ConsoleColors.*;

public class Map {
    // nombre de lignes
    private int nbLignes;
    // nombre de colonnes
    private int nbColonnes;
    // matrice de la map
    private Object[][] map;
    // position du joueur sur la map
    private int[] positionJoueur;
    // position de la sortie sur la map
    private int[] positionSortie;

    // position de mes monstres sur la map, ça sera un tableau de tableaux. Vu qu'il faudra stocker la position de plusieurs monstres
    private List<int[]> positionMonstres;
    private List<int[]> positionObstacles;
    private List<int[]> positionStores;

    public Map(int nbLignes, int nbColonnes) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.map = new Object[nbLignes][nbColonnes];
        this.positionJoueur = new int[2];
        this.positionSortie = new int[2];
        this.positionMonstres = new ArrayList<>();
        this.positionObstacles = new ArrayList<>();
        this.positionStores = new ArrayList<>();
    }

    public void initMap() {
        for(int i = 0; i < this.map.length; i++) {
            for(int j = 0; j < this.map[i].length; j++) {
                if(Math.random() < 0.1) {
                    // remplacer par un objet Weapon Store avec comme propriété "représentation" et créer une méthode toString()
                    // qui sera appelée ici pour l'afficher sur la map
                    WeaponStore weaponStore = new WeaponStore();
                    this.map[i][j] = weaponStore;

                }
                else if(Math.random() < 0.1) {
                    // remplacer par un objet Mosntre avec comme propriété "représentation" et créer une méthode toString()
                    // qui sera appelée ici pour l'afficher sur la map
                    Monster monster = new Monster(200);
                    this.map[i][j] = monster;
                }
                else if(Math.random() < 0.1) {
                    // remplacer par un objet Obstacles avec comme propriété "représentation" et créer une méthode toString()
                    // qui sera appelée ici pour l'afficher sur la map
                    Obstacle obstacle = new Obstacle(200);
                    this.map[i][j] = obstacle;
                }
                else {
                    this.map[i][j] = WHITE+"[ ]"+RESET;
                }
            }
        }

        // placer le joueur sur la map :
        this.map[this.map.length-1][0] = PURPLE+"[X]"+RESET;
        this.positionJoueur[0] = this.map.length-1;
        this.positionJoueur[1] = 0;

        // placer la sortie sur la map :
        this.map[0][this.map.length-1] = WHITE+"[?]"+RESET;
        this.positionSortie[0] = 0;
        this.positionSortie[1] = this.map.length-1;
    }

    public void afficherMap() {
        for(int i = 0; i < this.map.length; i++) {
            for(int j = 0; j < this.map[i].length; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println();
        }
    }

    public boolean isSortie() {
        if(this.positionJoueur[0] == this.positionSortie[0] && this.positionJoueur[1] == this.positionSortie[1]) {
            return true;
        }
        else {
            return false;
        }
    }


    public Object[][] getMap() {
        return this.map;
    }

    public int[] getPositionJoueur() {
        return this.positionJoueur;
    }

    public List<int[]> getPositionMonstres() {
        return this.positionMonstres;
    }

    public List<int[]> getPositionObstacles() {
        return this.positionObstacles;
    }

    public List<int[]> getPositionStores() {
        return this.positionStores;
    }

    public int[] getPositionSortie() {
        return this.positionSortie;
    }
}
