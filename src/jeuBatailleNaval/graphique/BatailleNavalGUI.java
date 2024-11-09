package jeuBatailleNaval.graphique;
import jeuBatailleNaval.jeu.game.*;
import jeuBatailleNaval.jeu.players.*;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
public class BatailleNavalGUI extends JFrame {

  private VueBataille vueBataille;
  private VueBataille vueBataille2;
  private Player player1;
  private Player player2;
  private Container contain; // pour le card Layout
  private CardLayout card;
  private Boolean start;
  private BarreChoixPos barreChPos;
  private JPanel jeu;
  private Game moteur;
  public Menu menu;
  public MenuParametre menuParametre;
  
  public String joueurCouleurChoisieBackground = "Noir";

  public BatailleNavalGUI(){
    this.player1 = new Human("Moi");
    this.player2 = new RandomPlayer(new Random());
    this.moteur = new Game(this.player1,this.player2);
    this.start = false;

    this.affichage();
  }

  public void setStart(){
      this.start = true;
  }

  public boolean getStart(){
    return this.start;
}

  public void affichage(){
    this.setTitle("Bataille Navale");
    this.setPreferredSize(new Dimension(1000, 600));
    this.contain = this.getContentPane();
    this.card=new CardLayout();
    this.contain.setLayout(this.card);

    // Affichage du menu
    this.menu = new Menu(this);
    this.contain.add(this.menu,"Menu");
    this.menu.setVisible(true);

    // Affichage du menu paramètre
    this.menuParametre = new MenuParametre(this);
    this.contain.add(this.menuParametre,"MenuParametre");
    this.menuParametre.setVisible(false);
    // Ajouter la barre du JPanel qui gère le placement des bateaux
    this.barreChPos = new BarreChoixPos(this,this.player1);
    this.contain.add(this.barreChPos, "BarreChoixPos");
    this.barreChPos.setVisible(false); // A false

    // Ajouter le plateau de jeu
    this.jeu = this.panelJeu();
    this.contain.add(this.jeu, "Jeu");
    this.jeu.setVisible(false);

    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
  }
public JPanel panelJeu(){
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(1, 2));
      this.moteur.construct(player2);
      this.vueBataille = new VueBataille(this.player1,this.player2,true,this.moteur);
      this.vueBataille2 = new VueBataille(this.player2,this.player1,false,this.moteur);
      panel.add(this.vueBataille);
      panel.add(this.vueBataille2);
      return panel;
  }

  public void setVueBataille(Plateau plateau){
      if (plateau.toutBateauPlacer()) {
          this.card.show(this.contain, "Jeu");
          this.jeu.setVisible(true);
          this.barreChPos.setVisible(false);

          //mit en commentare pour pas faire planter le jeu
          //this.partie();

          // Actualiser l'affichage
          this.vueBataille.repaint();
          this.vueBataille2.repaint();
      }
  }

  public void demarrerPlacementJeu(){
    // Masquer la barre de choix de position et afficher le plateau de jeu
    this.barreChPos.setVisible(true); // lancement du jeu visible

    this.menu.setVisible(false); // le menu invisible
    this.menuParametre.setVisible(false);
    // Actualiser l'affichage
    }

  public void demarrerMenuParametre() {
    this.menuParametre.setVisible(true);

    this.menu.setVisible(false);
    this.barreChPos.setVisible(false);
  }

  public void demarrerMenu() {
    this.menu.setVisible(true);
    if (this.joueurCouleurChoisieBackground.equals("Noir")) {
        this.menu.setBackground(Color.BLACK);
    } else if (this.joueurCouleurChoisieBackground.equals("Gris")) {
        this.menu.setBackground(Color.GRAY);
    }

    this.menuParametre.setVisible(false);
    this.barreChPos.setVisible(false);
  }

    public void partie() {
      Player currentPlayer = this.player1; // Le joueur 1 commence car l'humain
      while (!currentPlayer.aPerdu() && !this.player2.aPerdu()) {
          if (currentPlayer instanceof Human) {
              // tout du joueur humain
          } else {
              // mettre le tour de l'IA car on pars sur le fait qu'il ne peut pas y avoir 2 humain qui joue en meme temps
          }
          //faut penser a sortir de la boucle while quand un joueur gagne
          // Passer le tour au joueur suivant
          if (currentPlayer == this.player1) {
            currentPlayer = this.player2;
          } else {
            currentPlayer = this.player1;
          }
      }
      JOptionPane.showMessageDialog(this, "Le joueur " + currentPlayer.getNom() + " a gagné !");
    }

}
