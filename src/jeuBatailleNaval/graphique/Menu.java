package jeuBatailleNaval.graphique;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Menu extends JPanel implements MouseListener {
	
	private JButton btnJouer;
	private JButton btnParametre;
	private JButton btnQuitter;
	private BatailleNavalGUI navalGUI;

	public Menu(BatailleNavalGUI navalGUI) {
		this.navalGUI = navalGUI;
		this.menuElement();
	}
	
	public void menuElement() {
		this.setLayout(new GridLayout(10,1,1,5));
		this.setBackground(Color.BLACK);
		
			// Création des éléments
		JLabel titre = new JLabel("Menu");
		JLabel titre2 = new JLabel("Bienvenu dans le jeu Bataille Naval");
	
		this.btnJouer = new JButton("  Jouer  ");
		this.btnParametre = new JButton("  Paramètres  ");
		this.btnQuitter = new JButton("  Quitter  ");
		
			// Changement de font, couleur, taille
		titre.setFont(new Font("Verdana", Font.BOLD, 70));
		titre.setBackground(Color.BLACK);
		titre.setForeground(Color.WHITE);
		titre2.setFont(new Font("Verdana", Font.ITALIC, 20));
		titre2.setBackground(Color.BLACK);
		titre2.setForeground(Color.WHITE);
		this.btnJouer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.btnJouer.setFont(new Font("MS Song" , Font.BOLD, 30));
		this.btnJouer.setBackground(Color.BLACK);
		this.btnJouer.setForeground(Color.WHITE);
		this.btnParametre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.btnParametre.setFont(new Font("MS Song" , Font.BOLD, 30));
		this.btnParametre.setBackground(Color.BLACK);
		this.btnParametre.setForeground(Color.WHITE);
		this.btnQuitter.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
		this.btnQuitter.setFont(new Font("MS Song" , Font.BOLD, 30));
		this.btnQuitter.setBackground(Color.BLACK);
		this.btnQuitter.setForeground(Color.WHITE);
		
		
			// bouton intéractif
		this.btnJouer.addMouseListener(this);
		this.btnParametre.addMouseListener(this);
		this.btnQuitter.addMouseListener(this);
		
			// Ajout des éléments au panel
		ajoutEspaceVerticalAuPanel(this, 2);
		this.add(titre);
		ajoutEspaceVerticalAuPanel(this, 2);
		this.add(titre2);
		ajoutEspaceVerticalAuPanel(this, 5);
		this.add(btnJouer);
		ajoutEspaceVerticalAuPanel(this, 2);
		this.add(btnParametre);
		ajoutEspaceVerticalAuPanel(this, 2);
		this.add(btnQuitter);
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS); // Pour pouvoir centrer
		this.setLayout(layout);
		
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		titre2.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnJouer.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnParametre.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnQuitter.setAlignmentX(Component.CENTER_ALIGNMENT);
	}
	
	public void ajoutEspaceVerticalAuPanel(JPanel panel, int n) {
		for (int i=0; i<n; i++) {
			panel.add(new JLabel(" "));
		}
	}
    	
	@Override
	public void mouseClicked(MouseEvent e) { // Souris clique sur la zone
		if (e.getSource() instanceof JButton ) {
			JButton button = (JButton) e.getSource();
			if ( button.getText().equals(this.btnJouer.getText()) ) {
				this.navalGUI.demarrerPlacementJeu();
			} else if ( button.getText().equals(this.btnParametre.getText()) ) {
				this.navalGUI.demarrerMenuParametre();
			} else if ( button.getText().equals(this.btnQuitter.getText()) ) {
				this.navalGUI.dispose();
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
	public void mouseEntered(MouseEvent e) { // Souris entre dans la zone
		if (e.getSource() instanceof JButton ) {
			JButton button = (JButton) e.getSource();
			if ( button.getText().equals(this.btnJouer.getText()) ) {
				this.btnJouer.setBackground(Color.RED);
				this.btnJouer.setForeground(Color.BLACK);
				this.btnJouer.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			} else if ( button.getText().equals(this.btnParametre.getText()) ) {
				this.btnParametre.setBackground(Color.RED);
				this.btnParametre.setForeground(Color.BLACK);
				this.btnParametre.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			} else if ( button.getText().equals(this.btnQuitter.getText()) ) {
				this.btnQuitter.setBackground(Color.RED);
				this.btnQuitter.setForeground(Color.BLACK);
				this.btnQuitter.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			}

		}
		
	
	}


	@Override
	public void mouseExited(MouseEvent e) { // Souris sort de la zone
		if (e.getSource() instanceof JButton ) {
			JButton button = (JButton) e.getSource();
			if ( button.getText().equals(this.btnJouer.getText()) ) {
				this.btnJouer.setBackground(Color.BLACK);
				this.btnJouer.setForeground(Color.WHITE);
				this.btnJouer.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			} else if ( button.getText().equals(this.btnParametre.getText()) ) {
				this.btnParametre.setBackground(Color.BLACK);
				this.btnParametre.setForeground(Color.WHITE);
				this.btnParametre.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			} else if ( button.getText().equals(this.btnQuitter.getText()) ) {
				this.btnQuitter.setBackground(Color.BLACK);
				this.btnQuitter.setForeground(Color.WHITE);
				this.btnQuitter.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
			}

		}
	

	
	}
	


}
