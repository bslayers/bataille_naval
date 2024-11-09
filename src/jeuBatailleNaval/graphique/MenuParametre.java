package jeuBatailleNaval.graphique;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MenuParametre extends JPanel implements MouseListener, ActionListener {


	private JPanel panelCouleurBackground = new JPanel();
	private JPanel panelCouleurNavireJ1 = new JPanel();
	private JPanel panelCouleurNavireJ2 = new JPanel();
	private JButton btnRetour = new JButton("Retour");
	private JComboBox<String> couleurBackground;
	private BatailleNavalGUI navalGUI;

	public MenuParametre(BatailleNavalGUI navalGUI) {	
		this.navalGUI = navalGUI;
		this.menuParametreElement();
		
	}
	
	public void menuParametreElement() {
		this.setLayout(new GridLayout(10,1,1,5));
		this.setBackground(Color.BLACK);
		
		String desCouleursBackground[] = {"Noir", "Gris"};
		String desCouleursNavires[] = {"Jaune", "Bleu", "Violet", "Rose", "Marron"};
		
			// Création des éléments
		JLabel titre = new JLabel("Paramètres");
		
		//JPanel panelCouleurBackground = new JPanel();
		JLabel qCouleurBackground = new JLabel("Choisissez la couleur d'arrière plan ");
		this.couleurBackground = new JComboBox<>(desCouleursBackground);
				
		//JPanel panelCouleurNavireJ1 = new JPanel();
		JLabel qCouleurNavireJ1 = new JLabel("Choisissez la couleur des navires pour le joueur 1 ");
		JComboBox<String> couleurNavireJ1 = new JComboBox<>(desCouleursNavires);
		
		//JPanel panelCouleurNavireJ2 = new JPanel();
		JLabel qCouleurNavireJ2 = new JLabel("Choisissez la couleur des navires pour le joueur 2 ");
		JComboBox<String> couleurNavireJ2 = new JComboBox<>(desCouleursNavires);
		
		JLabel sujetCredits = new JLabel("Ce jeu a été créé avec la participation de");
		JLabel developpeur1 = new JLabel("FAVIER Hugo");
		JLabel developpeur2 = new JLabel("JOUIN Thomas");
		JLabel developpeur3 = new JLabel("SARI Mikail");
		
		
			// Changement de font, couleur, taille
		this.btnRetour.setFont(new Font("Verdana", Font.BOLD, 16));
		this.btnRetour.setBackground(Color.BLACK);
		this.btnRetour.setForeground(Color.WHITE);
		titre.setFont(new Font("Verdana", Font.BOLD, 70));
		titre.setBackground(Color.BLACK);
		titre.setForeground(Color.WHITE);
		// panelCouleurBackground
		this.panelCouleurBackground.setBackground(Color.BLACK);
		qCouleurBackground.setForeground(Color.WHITE);
		qCouleurBackground.setFont(new Font("Verdana", Font.BOLD, 16));
		// panelCouleurNavireJ1
		this.panelCouleurNavireJ1.setBackground(Color.BLACK);
		qCouleurNavireJ1.setForeground(Color.WHITE);
		qCouleurNavireJ1.setFont(new Font("Verdana", Font.BOLD, 16));
		// panelCouleurNavireJ2
		this.panelCouleurNavireJ2.setBackground(Color.BLACK);
		qCouleurNavireJ2.setForeground(Color.WHITE);
		qCouleurNavireJ2.setFont(new Font("Verdana", Font.BOLD, 16));
		// zone crédits
		sujetCredits.setForeground(Color.WHITE);
		developpeur1.setForeground(Color.WHITE);
		developpeur2.setForeground(Color.WHITE);
		developpeur3.setForeground(Color.WHITE);
		sujetCredits.setFont(new Font("Verdana", Font.BOLD, 15));
		developpeur1.setFont(new Font("Verdana", Font.ITALIC, 15));
		developpeur2.setFont(new Font("Verdana", Font.ITALIC, 15));
		developpeur3.setFont(new Font("Verdana", Font.ITALIC, 15));
		
		
			// Ajout d'éléments au panelCouleurBackground
		this.panelCouleurBackground.add(qCouleurBackground);
		this.panelCouleurBackground.add(this.couleurBackground);
			// Ajout d'éléments au panelCouleurNavireJ1
		this.panelCouleurNavireJ1.add(qCouleurNavireJ1);
		this.panelCouleurNavireJ1.add(couleurNavireJ1);
			// Ajout d'éléments au panelCouleurNavireJ2
		this.panelCouleurNavireJ2.add(qCouleurNavireJ2);
		this.panelCouleurNavireJ2.add(couleurNavireJ2);
			
			// Ajout d'éléments au panel principal
		ajoutEspaceVerticalAuPanel(this, 2);
		this.add(this.btnRetour);
		this.add(titre);
		ajoutEspaceVerticalAuPanel(this, 5);
		this.add(this.panelCouleurBackground);
		ajoutEspaceVerticalAuPanel(this, 1);
		this.add(this.panelCouleurNavireJ1);
		ajoutEspaceVerticalAuPanel(this, 1);
		this.add(this.panelCouleurNavireJ2);
		ajoutEspaceVerticalAuPanel(this, 3);
		this.add(sujetCredits);
		this.add(developpeur1);
		this.add(developpeur2);
		this.add(developpeur3);
		ajoutEspaceVerticalAuPanel(this, 2);
		
		
		BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS); // Pour pouvoir centrer
		this.setLayout(layout);
		
		this.btnRetour.setAlignmentX(Component.CENTER_ALIGNMENT);
		titre.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelCouleurBackground.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.panelCouleurNavireJ1.setAlignmentX(Component.CENTER_ALIGNMENT);
		sujetCredits.setAlignmentX(Component.CENTER_ALIGNMENT);
		developpeur1.setAlignmentX(Component.CENTER_ALIGNMENT);
		developpeur2.setAlignmentX(Component.CENTER_ALIGNMENT);
		developpeur3.setAlignmentX(Component.CENTER_ALIGNMENT);
		
			// panel intéractif
		this.panelCouleurBackground.addMouseListener(this);
		this.panelCouleurNavireJ1.addMouseListener(this);
		this.panelCouleurNavireJ2.addMouseListener(this);
			// label intéractif
		this.btnRetour.addMouseListener(this);
			// JComboBox intéractif
		this.couleurBackground.addActionListener(this);
		
		
		
		
	}
	
	public void ajoutEspaceVerticalAuPanel(JPanel panel, int n) {
		for (int i=0; i<n; i++) {
			panel.add(new JLabel(" "));
		}
	}
	
	@Override
    	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JComboBox)  {
			JComboBox elementChoisie = (JComboBox) e.getSource();
			String couleur = elementChoisie.getSelectedItem().toString();
			if (couleur.equals("Noir")) {
				this.navalGUI.joueurCouleurChoisieBackground = "Noir";
				this.setBackground(Color.BLACK);
				this.panelCouleurBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				this.panelCouleurBackground.setBackground(Color.BLACK);
				this.panelCouleurNavireJ1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				this.panelCouleurNavireJ1.setBackground(Color.BLACK);
				this.panelCouleurNavireJ2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				this.panelCouleurNavireJ2.setBackground(Color.BLACK);
				
			} else if (couleur.equals("Gris")) {
				this.navalGUI.joueurCouleurChoisieBackground = "Gris";
				this.setBackground(Color.GRAY);
				this.panelCouleurBackground.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				this.panelCouleurBackground.setBackground(Color.GRAY);
				this.panelCouleurNavireJ1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				this.panelCouleurNavireJ1.setBackground(Color.GRAY);
				this.panelCouleurNavireJ2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				this.panelCouleurNavireJ2.setBackground(Color.GRAY);
			}
			
		}
	
	}
	
	@Override
	public void mouseClicked(MouseEvent e) { // Souris clique sur la zone
		if (e.getSource() instanceof JButton ) {
				JButton button = (JButton) e.getSource();
				
				if ( button.getText().equals(this.btnRetour.getText()) ) {
					this.navalGUI.demarrerMenu();
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
		if (e.getSource() instanceof JPanel) {
			JPanel panel = (JPanel) e.getSource();
			
			panel.setBackground(Color.RED);
			panel.setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			
			Component[] lesComposants = panel.getComponents();
			for (int i=0; i<lesComposants.length; i++) { // On va aller chercher tout les JLabels
				if (lesComposants[i] instanceof JLabel ) {
					lesComposants[i].setForeground(Color.BLACK);
				}
			}

		} else if (e.getSource() instanceof JButton ) {
			JButton button = (JButton) e.getSource();
			
			if ( button.getText().equals(this.btnRetour.getText()) ) {
				this.btnRetour.setBackground(Color.RED);
				this.btnRetour.setForeground(Color.BLACK);
			}
		
		} 
	
	}


	@Override
	public void mouseExited(MouseEvent e) { // Souris sort de la zone
		if (e.getSource() instanceof JPanel ) {
			JPanel panel = (JPanel) e.getSource();
			
			if (this.navalGUI.joueurCouleurChoisieBackground == "Noir") {
				panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
				panel.setBackground(Color.BLACK);
			} else if (this.navalGUI.joueurCouleurChoisieBackground == "Gris") {
				panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				panel.setBackground(Color.GRAY);
			}
			
			
			Component[] lesComposants = panel.getComponents();
			for (int i=0; i<lesComposants.length; i++) { // On va aller chercher tout les JLabels
				if (lesComposants[i] instanceof JLabel ) {
					lesComposants[i].setForeground(Color.WHITE);
				}
			}

		} else if (e.getSource() instanceof JButton ) {
			JButton button = (JButton) e.getSource();
			
			if ( button.getText().equals(this.btnRetour.getText()) ) {
				this.btnRetour.setBackground(Color.BLACK);
				this.btnRetour.setForeground(Color.WHITE);
			}
		
		}

	
	}


}
