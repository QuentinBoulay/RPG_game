package game.interfaces;

import game.weapons.Weapon;
import game.weapons.WeaponStore;

public interface ActionsPlayer {

    // Acheter une arme
    void buyWeapon(Weapon arme, WeaponStore store);
}
