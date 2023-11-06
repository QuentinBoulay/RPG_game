package game.interfaces;

import game.Map;
import game.weapons.Weapon;
import game.weapons.WeaponStore;

public interface ActionsPlayer {

    // Acheter une arme
    void buyWeapon(Weapon arme, WeaponStore store);
    // se déplacer
    void deplacement(int choix, Map map, WeaponStore store);

    boolean meetObstacle(Weapon arme, WeaponStore store);

    boolean meetMonster(Weapon arme, WeaponStore store);
}
