package jeuBatailleNaval.graphique;
import jeuBatailleNaval.jeu.game.*;
import jeuBatailleNaval.jeu.players.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class VueBataille extends JPanel implements Ecouteur, MouseListener{

    private Plateau plateau;
    private Plateau plateau2;
    private final int TAILLE_CELLULE = 40;
    private final int TAILLE_GRILLE = 10 * this.TAILLE_CELLULE;
    private Player player;
    private Player player2;
    private boolean bateauVisible;
    private Game jeu;

    public VueBataille(Player player,Player player2,boolean bateauVisible,Game jeu){
        this.player = player;
        this.player2 = player2;

        this.jeu = jeu;

        this.plateau = this.player.getGrille();
        this.plateau2 = this.player2.getGrille();
        this.bateauVisible = bateauVisible;
        this.plateau.ajoutEcouteur(this);
        this.addMouseListener(this);
        //this.setBackground(Color.GRAY);
    }


    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Dessin des lettres de lignes
        g.drawString(this.player.getNom(),this.TAILLE_GRILLE/2,this.TAILLE_CELLULE);
        for (int i = 0; i < 10; i++) {
            char lettre = (char) ('A' + i);
            g.drawString(Character.toString(lettre), (i + 1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE / 2 + 5, this.TAILLE_CELLULE+(this.TAILLE_CELLULE/2));
            //c'est drawString(String,coordonate x, coordonate y)
        }

        // Dessin des lettres de lignes
        for (int i = 0; i < 10; i++) {
            g.drawString(Integer.toString(i + 1),15, (i + 1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE+(this.TAILLE_CELLULE/2));
        }

        // Dessin de la grille
        g.setColor(Color.WHITE);
        for (int i = 0; i <= 10; i++) {
            //g.drawLine(x1,y1,x2,y2)
            g.drawLine(i * this.TAILLE_CELLULE + this.TAILLE_CELLULE, this.TAILLE_CELLULE*2, i * this.TAILLE_CELLULE + this.TAILLE_CELLULE, this.TAILLE_GRILLE + (this.TAILLE_CELLULE*2));
        }

        for (int i = 0; i <= 10; i++) {
            g.drawLine(this.TAILLE_CELLULE, (i+1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE, this.TAILLE_GRILLE + this.TAILLE_CELLULE, (i+1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE);
        }

        // Dessin des cases
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Cellule cellule = this.plateau.getCase(i, j);
                if (cellule.getNavire() != null && cellule.getObus() != null) {
                    g.setColor(Color.RED);
                    g.fillOval(i * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, (j+1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, this.TAILLE_CELLULE - 2, this.TAILLE_CELLULE - 2);
                } else if (cellule.getNavire() != null && this.bateauVisible ==true) {
                    g.setColor(Color.ORANGE);
                    g.fillOval(i * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, (j+1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, this.TAILLE_CELLULE - 2, this.TAILLE_CELLULE - 2);
                } else if (cellule.getObus() != null) {
                    g.setColor(Color.GREEN);
                    g.fillOval(i * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, (j+1) * this.TAILLE_CELLULE + this.TAILLE_CELLULE + 1, this.TAILLE_CELLULE - 2, this.TAILLE_CELLULE - 2);
                }
            }
        }
    }


    @Override
    public void modeleMisAJour(Object source) {
        this.repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        /*
        if (this.bateauVisible==true && this.plateau.tousBateauCoules()){
            JOptionPane.showMessageDialog(this, "Le joueur 2 a gagné !");
        }*/
        if (this.bateauVisible==false){
            int x = e.getX() / this.TAILLE_CELLULE;
            int y = e.getY() / this.TAILLE_CELLULE;
            // Vérification que les coordonnées sont bien celles d'une case
            if (x > 0 && x <= 10 && y > 1 && y <= 11 && this.jeu.tirValide(x-1,y-2,this.plateau)) {
                Obus obus = new Obus();
                //if (this.jeu.tirValide(x-1,y-2,this.plateau2)) {
                  this.plateau.tirer(obus, x-1, y-2);


                  //pour le random Player pour qu'il tire instantanément sur la grille du joueur après que le joueur est tiré
                  //this.plateau2.tirer(obus,tir[0],tir[1]);
                  char[] cible = this.player.chooseCible(jeu,plateau2);
                  this.plateau2.tirer(obus,cible[0]-'0',cible[1]-'0');

                  if (this.jeu.isOver()){
                    JOptionPane.showMessageDialog(this, this.jeu.getWinner());
                    return;
                  }



        }

    }
  }


    @Override
    public void mousePressed(MouseEvent e) {
    }


    @Override
    public void mouseReleased(MouseEvent e) {
    }


    @Override
    public void mouseEntered(MouseEvent e) {
    }


    @Override
    public void mouseExited(MouseEvent e) {
    }

}
