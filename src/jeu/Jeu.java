package jeu;

/**
 * Created by valid13 on 06/02/2017.
 */

import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by valid13 on 06/02/2017.
 */

public class Jeu extends JFrame{
    private JPanel panel;
    private JPanel panelMenu;
    private JButton boutonNouvellePartie;
    private JButton boutonChargerPartie;
    private JPanel panelCharger;
    private JPanel panelNouvellePartie;
    private JPanel panelGrille;

    public Jeu() {
        initialisation();
        initialisationBouton();
        initialisationPanelMenu();
    }

    /**
     Méthode qui permet d'initialiser les panels de chaque fenêtre de l'utilisateur
     et d'ajouter tout les panels dans un panel principal
     */
    private void initialisation() {
        //PANEL PRINCIPAL #LE BIG BOSS
        panel = new JPanel();
        panel.setLayout(new CardLayout(0, 0));
        panel.setBackground(new Color(-15603696));
        panel.setEnabled(true);
        setContentPane(panel);

        //PANEL MENU #VIN DIESEL
        panelMenu = new JPanel();
        panelMenu.setLayout(new GridBagLayout());
        panel.add(panelMenu, "pagePrincipal");

        //PANEL CHARGER
        panelCharger = new JPanel();
        panelCharger.setLayout(new GridBagLayout());
        panel.add(panelCharger, "pageChargerPartie");

        //PANEL NOUVELLE PARTIE
        panelNouvellePartie = new JPanel();
        panelNouvellePartie.setLayout(new GridBagLayout());
        panel.add(panelNouvellePartie, "pageNouvellePartie");

        //PANEL GRILLE
        panelGrille = new JPanel();
        panelGrille.setLayout(new GridBagLayout());
        panel.add(panelGrille, "pageJeu");

    }

    /**
     Méthode qui ajoute tout les éléments destiner au panel Menu
     */
    private void initialisationPanelMenu(){
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        gbc.ipady = 30;

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.ipadx = 200;
        gbc2.ipady = 30;
        gbc2.insets.set(0,20,0,20);

        panelMenu.add(boutonNouvellePartie, gbc);
        panelMenu.add(boutonChargerPartie, gbc2);
    }
    /**
     Méthode qui initialise les boutons du jeu
     */
    private void initialisationBouton(){
        boutonNouvellePartie = new JButton();
        boutonChargerPartie = new JButton();

        boutonNouvellePartie.setText("Nouvelle Partie");
        boutonChargerPartie.setText("Charger Partie");

        boutonNouvellePartie.setActionCommand("Nouvelle Partie");
        boutonChargerPartie.setActionCommand("Charger Partie");

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout)(panel.getLayout());
                String command = e.getActionCommand();
                if (command.equals("Nouvelle Partie")) {
                    cardLayout.show(panel,"pageNouvellePartie");
                } else if (command.equals("Charger Partie")) {
                    cardLayout.show(panel,"pageChargerPartie");
                }
            }
        };
        boutonNouvellePartie.addActionListener(actionListener);
        boutonChargerPartie.addActionListener(actionListener);
    }
    /**
     Méthode d'initialisation relative à la fenetre Frame
     */
    public void commencer(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(700,600));
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
