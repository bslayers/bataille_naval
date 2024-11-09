package jeuBatailleNaval.jeu.game;
import java.util.ArrayList;

public abstract class AbstractModeleEcoutable implements ModeleEcoutable{

    private ArrayList<Ecouteur> ecouteur;

    public AbstractModeleEcoutable(){
        this.ecouteur = new ArrayList<>();
    }

    public void ajoutEcouteur(Ecouteur e){
        this.ecouteur.add(e);
    }

    public void retraitEcouteur(Ecouteur e){
        this.ecouteur.remove(e);
    }

    public void batailleChangement(){
        for (Ecouteur e : this.ecouteur){
            e.modeleMisAJour(this);
            }
    }

}
