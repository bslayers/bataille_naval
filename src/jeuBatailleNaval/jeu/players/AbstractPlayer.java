package jeuBatailleNaval.jeu.players;
import jeuBatailleNaval.jeu.game.*;
import java.util.ArrayList;
public class AbstractPlayer extends AbstractModeleEcoutable implements Player {

	public String nom;
	public Plateau plateau;

	public AbstractPlayer() {
		this.nom = "NAN";
		this.plateau = new Plateau();
	}
	@Override
	public char[] choosePlace(Game jeu){
		return this.choosePlace(jeu,null);
	}
	@Override
	public char[] choosePlace(Game jeu,ArrayList<Character> listeTaille){
		return null;
	}
	@Override
	public char[] chooseCible(Game jeu,Plateau plt){
		return null;
	}


	@Override
	public boolean aPerdu(){
		for (Cellule[] ligne : this.plateau.getGrille() ) {
			for (Cellule cel : ligne ) {
				if(cel.getObus() == null && cel.getNavire() != null){
					return false;
				}
			}
		}
		return true;
	}
	@Override
	public Plateau getGrille(){
		return this.plateau;
	}
	@Override
	public void setGrille(Plateau plt){
		this.plateau = plt;
	}
	@Override
	public String getNom(){
		return this.nom;
}
}
