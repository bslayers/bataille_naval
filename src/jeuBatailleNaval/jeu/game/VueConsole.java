package jeuBatailleNaval.jeu.game;

//idée ajout:donner la possibiliter de recommencer une partie sans relancer le code
public class VueConsole implements Ecouteur{

    private Plateau plateau;
    private boolean bateauVisible;

    public VueConsole(Plateau plateau){this(plateau,true);}
    public VueConsole(Plateau plateau,boolean bateauVisible){
        this.plateau = plateau;
        this.bateauVisible = bateauVisible;
    }

    public void setBateauVisible(boolean visible){
      this.bateauVisible =visible;
    }

    @Override
    public void modeleMisAJour(Object source) {
      String mot = "  A B C D E F G H I J\n";
      for (int i=0;i<10 ;i++ ) {
        mot+= i+" ";
          for (int j=0;j<10 ;j++ ) {
            if (!bateauVisible && this.plateau.getCase(j,i).toString() == "-") {
              mot+= "  ";
            }
            else{
              mot+= this.plateau.getCase(j,i).toString()+ " ";
            }
          }
          mot+="\n";
      }
      System.out.println( mot+"\n");

  }

}
