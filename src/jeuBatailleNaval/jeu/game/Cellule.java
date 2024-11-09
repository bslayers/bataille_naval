package jeuBatailleNaval.jeu.game;

public class Cellule{

    public int x;
    public int y;

    public Navire nav;
    public Obus ob;
    public Plateau plat;

    public Cellule(int x, int y,Plateau plat){
        this.x = x;
        this.y = y;
        this.nav = null;
        this.ob = null;
        this.plat = plat;
    }

    public Navire getNavire(){
        return this.nav;
    }

    public Obus getObus(){
        return this.ob;
    }

    public boolean estVide(){
      return this.nav == null;
    }
    public boolean nonVide(){
      return !this.estVide();
    }

    public boolean estCoule() {
        if (this.nav != null && this.ob != null && this.nav.estDetruit()) {
            return true;
        }
        return false;
    }

    public void placer(Navire navi){
        this.nav = navi;
        this.nav.ajouterCellule(this);
    }

    public void placer(Obus o){
        this.ob = o;
    }

    @Override
    public String toString(){
        if (this.nav!=null && this.ob!=null){
            return "x";
        }
        if (this.nav!=null){
            return "-";
        }
        if (this.ob!=null){
            return "o";
        }
        return " ";
    }

}
