package jeuBatailleNaval.jeu.game;

public class Plateau extends AbstractModeleEcoutable {

  private Cellule[][] grille;

  public Plateau(){
    this.grille = new Cellule[10][10];

    //initialisation de la grille
    for (int i = 0; i<10;i++ ) {
      for (int j =0; j<10;j++) {
          this.grille[i][j] = new Cellule(i,j,this);
      }
    }
  }

  public Cellule[][] getGrille(){
    return this.grille;
  }

  public Cellule getCase(int x,int y){
      return this.grille[x][y];
  }

  public void setCase(int x,int y,Cellule valeur){
    this.grille[x][y] = valeur;
  }

  public void placer(Navire nav,int x, int y){
      this.grille[x][y].placer(nav);
  }

  public void placer(Navire nav,int x, int y, String orientation){
      if ("o".equals(orientation)){
        for (int i =0;i<nav.taille;i++ ) {
          this.placer(nav,x + i,y);
        }
      }
      else{
        for (int i =0;i<nav.taille;i++ ) {
          this.placer(nav,x,y + i);
        }
      }
      this.batailleChangement();
  }

  public boolean estLibre(int x, int y) {
    Cellule cellule = this.getCase(y, x);

    return cellule.getNavire() == null;
  }

  //verifier que tout les bateaux on bien etait placer
  public boolean toutBateauPlacer() {
    for (int i = 0; i < this.grille.length; i++) {
        for (int j = 0; j < this.grille[i].length; j++) {
            if (this.grille[i][j].getNavire() != null && !this.grille[i][j].getNavire().estPlace()) {
                return false;
            }
        }
    }

    return true;
  }

  public boolean tousBateauCoules() {
    for (Cellule[] ligne : this.grille) {
      for (Cellule cel : ligne) {
          if (cel.getNavire() != null && !cel.getNavire().estDetruit()) {
              return false;
          }
      }
   }
  return true;
  }

  public int traductionString(String x){
    String[] caracteres = {"a", "b", "c", "d", "e"};
    for (int i =0;i<caracteres.length;i++){
      if (caracteres[i]==x){
        return i;
      }
    }
    return 0;
  }

  public void tirer(Obus o, int x,int y){
      this.grille[x][y].placer(o);
      this.batailleChangement();
  }
  public void tirer(Obus o, String x, int y){
    this.grille[this.traductionString(x)][y].placer(o);
}

  @Override
  public String toString(){
      String mot = "  A B C D E F G H I J\n";
      for (int i=0;i<10 ;i++ ) {
        mot+= i+" ";
          for (int j=0;j<10 ;j++ ) {
            mot+= this.grille[j][i].toString()+ " ";
          }
          mot+="\n";
      }
      return mot+"\n";

  }
  /*
  public void afficherPlateau(char[][] plateau) {
    System.out.println(); // Affiche la légende des colonnes

    for(int i=0; i<10; i++) {
        System.out.print((i+1) + "  "); // Affiche la légende des lignes
        for(int j=0; j<10; j++) {
            System.out.print(plateau[i][j] + " "); // Affiche le contenu de chaque case
        }
        System.out.println(); // Passe à la ligne suivante
    }*/
}
