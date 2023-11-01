package game.weapons;

import game.destructible.Destructible;

public abstract class Weapon {
    private int idArme;
    private String nomArme;
    private double damage;
    private double ratioDamageMonster;
    private double ratioDamageObstacle;
    private double price;


    // Constructeur pour créer mes armes
    public Weapon(int idArme, String nomArme, double damage, double ratioDamageMonster, double ratioDamageObstacle, double price) {
        this.idArme = idArme;
        this.nomArme = nomArme;
        this.damage = damage;
        this.ratioDamageMonster = ratioDamageMonster;
        this.ratioDamageObstacle = ratioDamageObstacle;
        this.price = price;
    }


    // méthodes abstraites pour afficher l'ascii des armes, attaquer un monstre/obstacle
    public abstract String ascii_art();

    public abstract void attack(Destructible d);

    public String toString() {
        return "Id arme : "+this.idArme+"\n"+"Nom de l'arme : "+this.nomArme+"\n"+"Damage : "+this.damage+"\n"+"Price : "+this.price+"\n"+"Représentation : \n"+this.ascii_art()+"\n\n";
    }

    // Getters
    public int getIdArme() {
        return this.idArme;
    }

    public String getNomArme() {
        return this.nomArme;
    }

    public double getDamage() {
        return this.damage;
    }

    public double getPrice() {
        return this.price;
    }

}
