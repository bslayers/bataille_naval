package jeuBatailleNaval.graphique;
import jeuBatailleNaval.jeu.game.Plateau;
import jeuBatailleNaval.jeu.game.Cellule;
import jeuBatailleNaval.jeu.game.Ecouteur;

import javax.swing.*;
import java.awt.*;

public class CreationChoixTableau extends JPanel implements Ecouteur{

    private Plateau plateau;
    private final int TAILLE_CELLULE = 40;
    private final int TAILLE_GRILLE = 10 * this.TAILLE_CELLULE;

    public CreationChoixTableau(Plateau plateau){
        this.plateau = plateau;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        // Dessin des lettres de lignes
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
                } else if (cellule.getNavire() != null) {
                    g.setColor(Color.GRAY);
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


}
