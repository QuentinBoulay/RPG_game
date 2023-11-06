package game;

import java.util.ArrayList;
import java.util.List;

public class Map {
    // nombre de lignes
    private int nbLignes;
    // nombre de colonnes
    private int nbColonnes;
    // matrice de la map
    private String[][] map;
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
        this.map = new String[nbLignes][nbColonnes];
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
                    this.map[i][j] = "[S]";
                    this.positionStores.add(new int[]{i, j});

                }
                else if(Math.random() < 0.1) {
                    this.map[i][j] = "[M]";
                    this.positionMonstres.add(new int[]{i, j});
                }
                else if(Math.random() < 0.1) {
                    this.map[i][j] = "[O]";
                    this.positionObstacles.add(new int[]{i, j});
                }
                else {
                    this.map[i][j] = "[ ]";
                }
            }
        }

        // placer le joueur sur la map :
        this.map[this.map.length-1][0] = "[X]";
        this.positionJoueur[0] = this.map.length-1;
        this.positionJoueur[1] = 0;

        // placer la sortie sur la map :
        this.map[0][this.map.length-1] = "[?]";
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


    public String[][] getMap() {
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
