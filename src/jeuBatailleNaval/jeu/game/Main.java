package jeuBatailleNaval.jeu.game;
import jeuBatailleNaval.jeu.players.*;
import java.util.*;
import java.util.Random;
public class Main{



	public static void main(String[] args){

		//Random rand1 = new Random(123);
		//RandomPlayer moi = new RandomPlayer(rand1);

		Human moi = new Human("moi");

		Random rand = new Random(1414);
		RandomPlayer david = new RandomPlayer(rand);

		Game jeu = new Game(moi,david);
		VueConsole vue1 = new VueConsole(moi.getGrille());
		VueConsole vue2 = new VueConsole(david.getGrille());
		moi.ajoutEcouteur(vue1);

		jeu.construct(jeu.getJoueurActuel());
		jeu.construct(jeu.getJoueurNonActuel());

		moi.retraitEcouteur(vue1);

//		vue1.setBateauVisible(false);
		vue2.setBateauVisible(false);

		moi.ajoutEcouteur(vue2);
		moi.ajoutEcouteur(vue1);

		jeu.start();

/*

   Plateau plt = new Plateau();
   Navire bat = new Navire(3);
   Cellule c = new Cellule(4, 2,plt);
   VueConsole vc = new VueConsole(plt);
   plt.ajoutEcouteur(vc);
   c.placer(bat);
   //plt.placer(bat, 3, 4);
   System.out.println(c.toString());
   System.out.println(bat.toString());
   plt.placer(bat,4,2,"o");
   Obus obus = new Obus();
   plt.tirer(obus,4,2);
   plt.tirer(obus,5,3);
   plt.tirer(obus,9,6);
   plt.tirer(obus,7,2);
   plt.tirer(obus,"a",0);
   plt.tirer(obus,"e",9);
   //System.out.println(plt.toString());
  // System.out.println(bat.celluleToString());

   //joueur(plt)
     //VueConsole vc = new VueConsole(plt);

*/
    }
}
