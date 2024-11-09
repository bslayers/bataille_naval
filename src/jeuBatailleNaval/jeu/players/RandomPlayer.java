package jeuBatailleNaval.jeu.players;
import jeuBatailleNaval.jeu.game.*;
import java.util.*;
import java.util.Random;
public class RandomPlayer extends AbstractPlayer implements Player  {
	public Plateau plateau;
	private Random rand;
	private int indiceRand;

	public RandomPlayer(Random rand) {
		this.plateau = new Plateau();
		this.rand = rand;
		this.indiceRand= 0;
	}
	@Override
	public char[] choosePlace(Game jeu,ArrayList<Character> listeTaille){
		int taille = (int) listeTaille.get(0) -'0';
		ArrayList<char[]> listeMouvement = jeu.choixPlacementsValides(this.plateau,taille);
		this.indiceRand= this.rand.nextInt(listeMouvement.size());
		return listeMouvement.get(indiceRand);
	}

	@Override
	public char[] chooseCible(Game jeu,Plateau plt){
		ArrayList<char[]> listetir = jeu.choixTirValides(plt);
		this.indiceRand= rand.nextInt(listetir.size()-1);
		System.out.println(listetir.get(indiceRand)[0]+","+listetir.get(indiceRand)[1]);
		return listetir.get(indiceRand);
	}

	@Override
	public String getNom(){
		return "random n° "+this.hashCode();
	}



}
