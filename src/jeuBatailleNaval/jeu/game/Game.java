package jeuBatailleNaval.jeu.game;
import jeuBatailleNaval.jeu.players.*;
import java.util.*;

public class Game{

    private Player joueur1;

    private Player joueur2;

    private Player joueurActuel;

    private Obus obus;
    public Game(Player joueur1,Player joueur2){
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
        this.joueurActuel = joueur1;
        this.obus = new Obus();
    }
/*
    public void debut(){
        String reponse = scanner.nextLine();
        System.out.println("Voulez-vous commencez la party? entre oui/non"+reponse);
        scanner.close();
        if (reponse=="oui"){
            this.start();
        }
        else{
            System.out.println("le jeu ne vas donc pas etre lancé");
        }
    }
*/
    //recupere le nom du joueur actuel
    public String getNomJoueurActuel(){
        if (this.joueurActuel==joueur1){
            return this.joueur1.getNom();
        }
        else{
            return this.joueur2.getNom();
        }
    }

    public Player getJoueurActuel(){
        return this.joueurActuel;
    }

    public Player getJoueurNonActuel(){
        if (this.joueurActuel==joueur1){
            return joueur2;
        }
        else{
            return joueur1;
        }
    }

    public void setJoueurActuel(){
        if (this.joueurActuel==joueur1){
            this.joueurActuel=joueur2;
        }
        else{
            this.joueurActuel=joueur1;
        }
    }

    public void construct(Player joueur){
      ArrayList<Character> listeTailleNavire = new ArrayList<Character>(Arrays.asList('5','4','3','3','2'));  //5,4,3,3,2
      char[] place;
      while(listeTailleNavire.size()>0 ){
        place = joueur.choosePlace(this,listeTailleNavire);
        if (listeTailleNavire.contains(place[3]) && listeTailleNavire.size()>0 && this.placementValide(place,joueur.getGrille())){
          joueur.getGrille().placer(new Navire((int) place[3]-'0'),(int) place[0]-'0',(int) place[1]-'0',String.valueOf(place[2]));
          listeTailleNavire.remove(listeTailleNavire.indexOf(place[3]));
        }
      }
      System.out.println("tout les bateaux on été placés");
    }

    public void start(){
        do {
          this.tourJoueur();
        } while (!this.isOver());
        System.out.println(this.getWinner());
    }

    public void tourJoueur(){
        System.out.println("C'est au tour du joueur " + this.getJoueurActuel().getNom() + " de jouer ");
        Plateau plateauDuJoueurEnemie= this.getJoueurNonActuel().getGrille();
        char[] cible = this.getJoueurActuel().chooseCible(this,plateauDuJoueurEnemie);
        if (this.tirValide(cible,plateauDuJoueurEnemie)) {
          plateauDuJoueurEnemie.tirer(this.obus,cible[0]-'0',cible[1]-'0');
          this.setJoueurActuel();
        }

    }

    public boolean tirValide(char[] cible, Plateau plateau){
      int x = Character.getNumericValue(cible[0]);
      int y = Character.getNumericValue(cible[1]);
      return this.tirValide(x,y,plateau);
    }

    public boolean tirValide(int x,int y, Plateau plateau){
      if (x>=0 && y>=0 &&x<=9 && y<=9){
        if (plateau.getCase(x,y).getObus()==null ){
          return true;
        }
      }
        return false;
    }
    public boolean placementValide(int x,int y,char orientation, int taille , Plateau plateau){
      if (this.estDansGrille(x,y,taille,orientation)) {
        return this.verifierCasesLibres(x,y,orientation,taille,plateau);
      }
      return false;
    }
    public boolean placementValide(char[] place, Plateau plateau){
      int x = Character.getNumericValue(place[0]);
      int y = Character.getNumericValue(place[1]);
      int taille = Character.getNumericValue(place[3]);
      return this.placementValide(x,y,place[2],taille,plateau);

    }

    private boolean verifierCasesLibres(int x, int y, Character direction, int tailleNavire,Plateau plateau) {
      for (int i = 0; i < tailleNavire; i++) {
        int nx = x;
        int ny = y;
        if (direction.equals('v')) {
          ny += i;
        } else {
          nx += i;
        }
        if (!plateau.estLibre(ny, nx)) {
          //System.out.println("erreur à:"+nx+","+ny);
          return false;
        }
      }
      return true;
    }

    private boolean estDansGrille(int x, int y, int taille, Character direction) {
      if (direction.equals('o')) {
        return x + taille <= 9;
      } else {
        return y + taille <= 9;
      }
    }

  public ArrayList<char[]> choixTirValides(Plateau plateau){
    ArrayList<char[]> choix = new ArrayList<char[]>();
    char[] possible = new char[2];
    for (int i=0;i<=9 ;i++ ) {
      possible[0]= (char)( i+'0');
      for (int j=0;j<=9 ;j++ ) {
        possible[1]= (char)( j+'0');
        if (tirValide(i,j, plateau)) {
          choix.add(new char[]{possible[0],possible[1]});
        }
      }
    }
    for (char[] elt : choix ) {System.out.println("["+elt[0]+","+elt[1]+"]");}
    System.out.println();
    return choix;
  }
  public ArrayList<char[]> choixPlacementsValides(Plateau plateau,int taille){
    ArrayList<char[]> choix = new ArrayList<char[]>();
    char[] possible = new char[4];
    possible[3]= (char) ( taille+'0');

    for (int i=0;i<=9 ;i++ ) {
      possible[0]= (char)( i+'0');
      for (int j=0;j<=9 ;j++ ) {
        possible[1]= (char)( j+'0');
        if (placementValide(i,j,'o',taille, plateau)) {
          possible[2]= 'o';
          choix.add(possible);
        }
        if (placementValide(i,j,'v',taille, plateau)) {
          possible[2]= 'v';
          choix.add(new char[]{possible[0],possible[1],possible[2],possible[3]});
        }
        //for (char[] elt : choix ) {System.out.println("["+elt[0]+","+elt[1]+","+elt[2]+","+elt[3]+"]");}
      }

    }


    return choix;
  }


    public boolean isOver(){
        if (this.joueur1.aPerdu() || this.joueur2.aPerdu()){
            return true;
        }
        else{
            return false;
        }
    }

    public String getWinner(){
        if (this.joueur1.aPerdu()){
            return "Le joueur " + this.joueur1.getNom() + " a perdu, vous ferez peut-etre mieux une prochaine fois";
        }
        else{
            return "Le joueur " + this.joueur2.getNom() + " a perdu, vous ferez peut-etre mieux une prochaine fois";
        }
    }

}


/*
for (int i; i<10; i++) {
    for (int j; j<10; j++) {
        if (this.plateau.getCase(x,y).getNavire()!=null && this.plateau.getCase(x,y).getObus()==null){
            return false; //donc la partie n'est pas terminé
        }
    }
}
return true;
*/
