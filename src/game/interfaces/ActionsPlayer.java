package game.interfaces;

import game.Map;
import game.destructible.Destructible;
import game.destructible.Monster;
import game.destructible.Obstacle;
import game.weapons.Weapon;
import game.weapons.WeaponStore;

public interface ActionsPlayer {

    // Acheter une arme
    void buyWeapon(Weapon arme, WeaponStore store);
    // se déplacer
    void deplacement(int choix, Map map, WeaponStore store);

    boolean meetDestructible(Weapon arme, WeaponStore store, Destructible de);
}
