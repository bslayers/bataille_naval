package jeuBatailleNaval.jeu.game;
import java.util.ArrayList;
public class Navire{

    public Plateau plateau;

    public ArrayList<Cellule> cellules;

    public int taille;

    public Navire(int taille){
        this.plateau = null;
        this.taille = taille;
        this.cellules = new ArrayList<Cellule>();
    }

    public boolean estDetruit() {
        for (Cellule cellule : this.cellules) {
            if (cellule.getObus() != null) {
                return false;
            }
        }
    return true;
    }
    

    //verifier qu'il y a un bateau placer
    public boolean estPlace() {
        for (Cellule cellule : this.cellules) {
            if (cellule.getNavire() == null) {
                return false;
            }
        }
        return true;
    }

    public void ajouterCellule(Cellule cel){
        this.cellules.add(cel);
    }

    public String celluleToString(){
        String mot = "";
        for (int i =0;i<this.cellules.size();i++ ) {
            mot+=this.cellules.get(i);
        }
        return mot;
    }

    @Override
    public String toString(){
        return "bateau"+this.taille;
    }

}
