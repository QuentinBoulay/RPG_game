package game;

import game.destructible.Monster;
import game.weapons.Weapon;
import game.weapons.WeaponStore;
import static game.constants.ConsoleColors.*;
import java.util.Objects;
import java.util.Scanner;

public class MainGame {
    public static void main(String[] args) {

        Game game = new Game();
        game.demarrerJeu();
    }
}