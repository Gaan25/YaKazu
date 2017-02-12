package jeu;

/**
 * Created by valid13 on 06/02/2017.
 */

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private JButton boutonIndice;
    private JButton boutonAbandonner;
    private JButton boutonSauvegarder;
    private JPanel panelCharger;
    private JPanel panelNouvellePartie;
    private JPanel panelJeu;
    private JPanel panelGrille;
    private JPanel panelBoutons;
    private final int TAILLE = 9;
    private JTextField [][] grille;
    private Tableau tableau;
    private Modeles modele;

    public Jeu() {
        initialisation();

        initialisationBouton();
        initialisationPanelMenu();
        initialisationPanelJeu();
        initialisationTableau();
        CardLayout cardLayout = (CardLayout)(panel.getLayout());
        cardLayout.show(panel,"pageJeu");
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
        panel.add(panelMenu, "pagePrincipale");

        //PANEL CHARGER
        panelCharger = new JPanel();
        panelCharger.setLayout(new GridBagLayout());
        panel.add(panelCharger, "pageChargerPartie");

        //PANEL NOUVELLE PARTIE
        panelNouvellePartie = new JPanel();
        panelNouvellePartie.setLayout(new GridBagLayout());
        panel.add(panelNouvellePartie, "pageNouvellePartie");

        //PANEL JEU
        panelJeu = new JPanel();
        panelJeu.setLayout(new GridBagLayout());
        panel.add(panelJeu, "pageJeu");
        grille = new JTextField[TAILLE][TAILLE];

        //PANEL GRILLE
        panelGrille = new JPanel();
        panelGrille.setLayout(new GridBagLayout());
        panelJeu.add(panelGrille);

        //PANEL BOUTONS
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelJeu.add(panelBoutons);

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
     * Méthode qui gère la vue de la grille principale
     */
    private void initialisationPanelJeu(){
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0;i<TAILLE;i++){
            for (int j = 0;j<TAILLE;j++){
                c.gridx = i;
                c.gridy = j;
                c.ipadx = 30;
                c.ipady = 15;
                grille[i][j] = new JTextField();
                //grille[i][j].setBorder(new LineBorder(Color.DARK_GRAY,1));
                panelGrille.add(grille[i][j],c);
            }
        }
        panelBoutons.add(boutonIndice);
        panelBoutons.add(boutonSauvegarder);
        panelBoutons.add(boutonAbandonner);
        panelJeu.add(panelGrille);
        panelJeu.add(panelBoutons);
    }
    /**
     Méthode qui initialise les boutons du jeu
     */
    private void initialisationBouton(){
        boutonNouvellePartie = new JButton();
        boutonChargerPartie = new JButton();
        boutonAbandonner = new JButton();
        boutonIndice = new JButton();
        boutonSauvegarder = new JButton();


        boutonNouvellePartie.setText("Nouvelle Partie");
        boutonChargerPartie.setText("Charger Partie");
        boutonIndice.setText("Indice");
        boutonSauvegarder.setText("Sauvegarder");
        boutonAbandonner.setText("Abandonner");

        boutonNouvellePartie.setActionCommand("Nouvelle Partie");
        boutonChargerPartie.setActionCommand("Charger Partie");
        boutonAbandonner.setActionCommand("Abandonner");
        boutonSauvegarder.setActionCommand("Sauvegarder");
        boutonIndice.setActionCommand("Indice");

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
        boutonAbandonner.addActionListener(actionListener);
        boutonIndice.addActionListener(actionListener);
        boutonSauvegarder.addActionListener(actionListener);
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
    //TEST DE GRILLE
    public void initialisationTableau(){
        //TABLEAU
        tableau = new Tableau(TAILLE);
        try {
            tableau.restaurerGrilleSerial("Modeles/modele1.ser");
        }catch (Exception e){
            e.getMessage();
        }
        tableau.afficherGrille();

        for (int i = 0;i<TAILLE;i++) {
            for (int j = 0; j < TAILLE; j++) {

                grille[i][j].setText(""+tableau.getCase(i,j));
            }
        }


    }
}
