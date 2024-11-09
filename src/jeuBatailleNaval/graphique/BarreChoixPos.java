package jeuBatailleNaval.graphique;
import jeuBatailleNaval.jeu.game.*;
import jeuBatailleNaval.jeu.players.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BarreChoixPos extends JPanel implements Ecouteur,ActionListener{

    private Plateau plateau;
    private JComboBox<Integer> choixTailleBateau;
    private Label label,label2,label3;
    private JButton btnchoixPos,btnfinPlacement;
    private JTextField textx,texty,textdirection;
    private CreationChoixTableau cct;
    private BatailleNavalGUI bng;
    private Player player;

    public BarreChoixPos(BatailleNavalGUI bng, Player player){
        this.player = player;
        this.plateau = this.player.getGrille();
        this.bng = bng;
        this.plateau.ajoutEcouteur(this);
        this.affichagePanel();
    }

    public void affichagePanel(){
        this.setLayout(new GridLayout(1, 1));
        this.add(this.defChoixTailleBateau());
        this.cct = new CreationChoixTableau(this.plateau);
        this.add(this.cct);
    }

    public JPanel defChoixTailleBateau(){
		Font f = new Font(Font.SANS_SERIF ,  Font.BOLD, 15);
        this.setButtons();

		this.label=new Label();
        this.label.setText("colonne(A-J): ");
        this.label.setFont(f);
        this.textx = new JTextField();
        this.textx.setFont(f);
        this.textx.setPreferredSize(new Dimension(40,20));
        JPanel panel1text = new JPanel();
        panel1text.setLayout(new GridLayout(1, 2));
        panel1text.add(this.label);
        panel1text.add(this.textx);

        this.label2=new Label();
        this.label2.setText("ligne(1-10):");
        this.label2.setFont(f);
        this.texty = new JTextField();
        this.texty.setFont(f);
        this.texty.setPreferredSize(new Dimension(40,20));
        JPanel panel2text = new JPanel();
        panel2text.setLayout(new GridLayout(1, 2));
        panel2text.add(this.label2);
        panel2text.add(this.texty);

        this.label3=new Label();
        this.label3.setText("Direction (o->hori/v->verti):");
        this.label3.setFont(f);
        this.textdirection = new JTextField();
        this.textdirection.setFont(f);
        this.textdirection.setPreferredSize(new Dimension(20,20));
        JPanel panel3text = new JPanel();
        panel3text.setLayout(new GridLayout(1, 2));
        panel3text.add(this.label3);
        panel3text.add(this.textdirection);

        Integer i[] = {5,4,3,3,2};
		JPanel panelBox = new JPanel();
        panelBox.setLayout(new GridLayout(10, 1));
		this.choixTailleBateau = new JComboBox<>(i);


		panelBox.add(panel1text);
        panelBox.add(panel2text);
		panelBox.add(this.choixTailleBateau);
        panelBox.add(panel3text);
        panelBox.add(this.btnchoixPos);
        panelBox.add(this.btnfinPlacement);
		return panelBox;
	}

    private void setButtons(){
		Font f = new Font(Font.SANS_SERIF ,  Font.BOLD, 15);
		this.btnchoixPos = new JButton("ok");
		this.btnfinPlacement = new JButton("finis de placer");
		this.btnchoixPos.setFont(f);
        this.btnfinPlacement.setFont(f);
        this.btnchoixPos.addActionListener(this);
        this.btnfinPlacement.addActionListener(this);
	}

    // Vérifie que la lettre entrée est valide
    private boolean validerLettre(String lettre, String caracteres) {
        return caracteres.contains(lettre);
    }

    //verifie que l'entrer validé est bien entre 1 et 10
    private int validerEntier(String entier) {
        try {
            int val = Integer.parseInt(entier) - 1;
            if (val < 0 || val > 9) {
                return -1;
            }
            return val;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Vérifie que la valeur entrée pour la direction est "o" ou "v"
    private boolean validerDirection(String direction) {
        return direction.equals("o") || direction.equals("v");
    }

    //sert a verifier que les cases sont bien libres
    private boolean verifierCasesLibres(Plateau plateau,int x, int y, String direction, int tailleNavire) {
        for (int i = 0; i < tailleNavire; i++) {
            int nx = x;
            int ny = y;
            if (direction.equals("v")) {
                ny += i;
            }
            else {
                nx += i;
            }
            if (!plateau.estLibre(ny, nx)) {
                return false;
            }
        }
        return true;
    }
    //verifie que c'est bien dans la grille
    private boolean estDansGrille(int x, int y, int taille, String horizontal) {
        if (horizontal.equals("o")) {
            return x + taille <= 10;
        } else {
            return y + taille <= 10;
        }
    }

    //verifie tout le necessaire pour placer un navire
    //cette methode gère donc le placement et utilise des return; pour directement quitter la methodes
    //quand l'une des nombreuse verification n'est pas respecté pour eviter des problèmes
    private void placerNavire() {
        // Vérifier s'il reste des bateaux à placer
        if (this.choixTailleBateau.getItemCount() == 0) {
            JOptionPane.showMessageDialog(this, "Tous les bateaux ont été placés.");
            this.btnchoixPos.setEnabled(false);
            return;
        }

        String gtextx = (String) this.textx.getText();
        String gtextyString = this.texty.getText();
        String gtextdirection = (String) this.textdirection.getText();
        String caracteres = "ABCDEFGHIJ";

        if (!this.validerLettre(gtextx, caracteres)) {
            JOptionPane.showMessageDialog(this, "La valeur entrée pour la colonne doit être une lettre entre A et J inclus.");
            return;
        }

        //regarde l'index de la lettre entré dans la chaine de string
        int x = caracteres.indexOf(gtextx);
        //verifie que le nombre entrer est valide
        int y = this.validerEntier(gtextyString);

        //si le nombre n'est pas valide
        if (y == -1) {
            JOptionPane.showMessageDialog(this, "La valeur entrée pour la ligne doit être un entier entre 1 et 10 inclus.");
            return;
        }

        //si la direction entrée n'est pas valide
        if (!this.validerDirection(gtextdirection)) {
            JOptionPane.showMessageDialog(this, "La valeur entrée pour la direction doit être \"o\" pour horizontal ou \"v\" pour vertical.");
            return;
        }

        //prend l'item selectionner du JComboBox puis l'utilise pour definir la taille du navire
        int tailleNavire = (int) this.choixTailleBateau.getSelectedItem();
        Navire nav = new Navire(tailleNavire);

        // Vérifier si le navire est dans la grille
        if (!this.estDansGrille(x-1, y-1, tailleNavire, gtextdirection)) {
            JOptionPane.showMessageDialog(this, "Le navire dépasse de la grille. Veuillez choisir une autre position.");
            return;
        }

        //si la cases selectionné n'est pas valide on envoie cette erreur
        if (!this.verifierCasesLibres(this.plateau,x, y, gtextdirection, tailleNavire)) {
            JOptionPane.showMessageDialog(this, "Impossible de placer le navire sur le plateau. Veuillez vérifier que les cases sont libres.");
            return;
        }

        // Placement du navire sur le plateau
        //si ça réussi a placer le bateau ça retire du JComboBox la taille Selectionné
        //sinon ça envoie le meme message que le test précédent
        //pour etre sur qu'il n'y ai pas d'erreur
        try {
            this.plateau.placer(nav, x, y, gtextdirection);
            JOptionPane.showMessageDialog(this, "Le Navire a été placé sur le plateau !");
            this.retirerTailleNavireComboBox(tailleNavire);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Impossible de placer le navire sur le plateau. Veuillez vérifier que les cases sont libres et que le navire n'est pas en dehors des limites du plateau.");
        }
    }

    private void verifierFinPlacement() {
        // Vérifier qu'il ne reste plus de bateau à placer
        if (this.choixTailleBateau.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(this, "Tous les bateaux ont été placés.");
            this.btnchoixPos.setEnabled(false);
            this.btnfinPlacement.setEnabled(false);
            this.bng.setStart();
            this.bng.setVueBataille(this.plateau);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Il reste des bateaux à placer.");
            return;
        }
    }

    //retirer de la liste du ComboBox les tailles deja posé
    private void retirerTailleNavireComboBox(int taille) {
        int index = this.choixTailleBateau.getSelectedIndex();
        if (index != -1) {
            int item = (int) this.choixTailleBateau.getItemAt(index);
            if (item == taille) {
                this.choixTailleBateau.removeItemAt(index);
            }
        }
    }

    @Override
    public void modeleMisAJour(Object source) {
        this.cct.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object action = event.getSource();
        if (action == this.btnchoixPos) {
            this.placerNavire();
        }
        if (action == this.btnfinPlacement) {
            this.verifierFinPlacement();
        }
    }


}
