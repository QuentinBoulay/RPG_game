package game.weapons;

import game.weapons.Axe;
import game.weapons.Bow;
import game.weapons.Hammer;
import game.weapons.Weapon;

import java.util.ArrayList;

public class WeaponStore {
    private ArrayList<Weapon> weapons = new ArrayList<>();

    public WeaponStore() {
        weapons.add(new Axe(1));
        weapons.add(new Hammer(2));
        weapons.add(new Bow(3));

    }

    public String toString() {
        return this.weapons.toString()+"\n\n";
    }

    // Getters
    public ArrayList getStore() {
        return this.weapons;
    }
    public Weapon getWeaponInStore(int idArme) {
        for (Weapon arme : this.weapons) {
            if(idArme == arme.getIdArme()) {
                return arme;
            }
        }
        return null;
    }
}
