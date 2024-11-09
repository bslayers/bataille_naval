package jeuBatailleNaval.jeu.players;
import jeuBatailleNaval.jeu.game.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Human extends AbstractPlayer implements Player{
	private String nom;
	private Plateau plateau;
	private String alphabet;
	private Scanner scan;

	public Human(){this("joueur Humain");}
	public Human(String nom) {
		this.nom = nom;
		this.plateau = new Plateau();
		this.scan = new Scanner(System.in);
	}

	@Override
	public char[] choosePlace(Game jeu,ArrayList<Character> listeTaille){
		char[] action = new char[4];
		do {
			this.batailleChangement();

			System.out.println("Coordonées x cible(exemple:'A,B,...J'):");
			System.out.println("Seul la première lettre compte");
			action[0] = this.findColone(this.scan.next());
			System.out.println("Coordonées y cible(exemple:'0,1,...9'):");
			action[1] = this.scan.next().charAt(0);
			System.out.println("l'orientation du navire('o' pour horizontal ou 'v' vertical):");
			action[2] =this.scan.next().charAt(0);
			System.out.println("Liste des tailles de Navire:");
			System.out.println(listeTaille);
			System.out.println("La taille du Navire:");
			action[3] =this.scan.next().charAt(0);
			System.out.println(this.plateau.toString());

			if (!jeu.placementValide(action,this.plateau)){
				System.out.println("Les coordonnées sont incorectes");
			}
		} while (!jeu.placementValide(action,this.plateau));

		return action;

	}

	@Override
	public char[] chooseCible(Game jeu,Plateau plt){
		char[] action =new char[2];
		do {
			this.batailleChangement();
			System.out.println("Coordonées x cible(exemple:'A,B,...J'):");
			action[0] = this.findColone(this.scan.next());
			System.out.println("Coordonées y cible(exemple:'0,1,...9'):");
			action[1] = this.scan.next().charAt(0);

			if (!(jeu.tirValide(action,plt))){
				System.out.println("Les coordonnées sont incorectes");
			}
		} while (!jeu.tirValide(action,plt));
		return action;
	}

	public char findColone(String lettre){
		int index = "ABCDEFGHIJ".indexOf(lettre.toUpperCase());
		return Character.forDigit(index, 10);//10 coorespond à une base 10 donc du décimale
	}
	@Override
	public String getNom(){
		return this.nom;
	}
	@Override
	public String toString(){
		return this.nom;
	}



}
