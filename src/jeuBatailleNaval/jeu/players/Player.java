package jeuBatailleNaval.jeu.players;
import jeuBatailleNaval.jeu.game.*;
import java.util.ArrayList;
public interface Player{

	public char[] choosePlace(Game jeu);//0:x 1:y 2:orientation 3:taille
	public char[] choosePlace(Game jeu,ArrayList<Character> listeTaille);

	 public char[] chooseCible(Game jeu,Plateau plt);//0:x 1:y

	 public boolean aPerdu();

	 public String getNom();

	 public Plateau getGrille();

	 public void setGrille(Plateau plt);

}






/*
public class Player {

	public String nom;
	public ArrayList<Navire> lesNavires;
	public ArrayList<Obus> lesObus;

	public Player(String nom) {
		this.nom = nom;
		this.lesNavires = new ArrayList<Navire>();
		this.lesObus    = new ArrayList<Obus>();
	}

	public void ajout(Plateau plat,Navire nav, int x, int y,String orientation) {
		lesNavires.add(nav);
		plat.placer(nav, x ,y,orientation);
	}

	public void ajout(Plateau plat, Obus obu, int x, int y) {
		lesObus.add(obu);
		plat.tirer(obu, x, y);
	}

}
*/
