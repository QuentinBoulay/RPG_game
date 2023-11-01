package game.destructible;

public abstract class Destructible {

    protected double life;

    public Destructible(double life) {
        this.life = life;
    }

    public void hit_me(double damage) {
        this.life -= damage;
    }

    // Getters
    public double getLife() {
        return this.life;
    }
}
