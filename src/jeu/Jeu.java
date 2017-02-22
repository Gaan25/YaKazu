package jeu;

/**
 * Created by valid13 on 06/02/2017.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.StringContent;

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
    private JComboBox listeDeroulante;
    private JButton boutonSauvegarder;
    private JButton boutonModeleFini;
    private JPanel panelCharger;
    private JPanel panelNouvellePartie;
    private JPanel panelCreeModele;
    private JPanel panelGrille2;
    private JPanel panelBoutons2;
    private JPanel panelJeu;
    private JPanel panelGrille;
    private JPanel panelBoutons;
    private int TAILLE;
    private JTextField [][] grille;
    private Tableau tableau;

    public Jeu() {
        initialisation();
        initialisationBouton();
        initialisationPanelMenu();
        initialisationPanelCreeModele();
        //initialisationTableau();
        CardLayout cardLayout = (CardLayout)(panel.getLayout());

        /* TEST */
        //cardLayout.show(panel,"pageJeu");
        cardLayout.show(panel,"pageCreeModele");

    }
    /**
     Méthode qui permet de creer les panels de chaque fenêtre de l'utilisateur
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
        //grille = new JTextField[TAILLE][TAILLE];

        //PANEL GRILLE DANS PANEL JEU
        panelGrille = new JPanel();
        panelGrille.setLayout(new GridBagLayout());
        panelJeu.add(panelGrille);

        //PANEL BOUTONS DANS PANEL JEU
        panelBoutons = new JPanel();
        panelBoutons.setLayout(new BoxLayout(panelBoutons, BoxLayout.Y_AXIS));
        panelJeu.add(panelBoutons);

        //PANEL CREEMODELE
        panelCreeModele = new JPanel();
        panelCreeModele.setLayout(new GridBagLayout());
        panel.add(panelCreeModele,"pageCreeModele");

        //PANEL GRILLE2 DANS PANEL CREEMODELE
        panelGrille2 = new JPanel();
        panelGrille2.setLayout(new GridBagLayout());

        //PANEL BOUTONS2 DANS PANEL CREEGRILLE
        panelBoutons2 = new JPanel();
        panelBoutons2.setLayout(new GridBagLayout());

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
                c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                c.gridy = i;
                c.ipadx = 30;
                c.ipady = 15;

                grille[i][j] = new JTextField();
                if (tableau.getCase(i,j) == -1){
                    grille[i][j].setBackground(Color.BLACK);
                }else if (tableau.getCase(i,j) > 0){
                    grille[i][j].setText(String.valueOf(tableau.getCase(i,j)));
                }
                grille[i][j].setPreferredSize(new Dimension(35, 35));
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
     * Méthode qui gère la vue de la creation d'une grille
     */
    private void initialisationPanelCreeModele(){
        panelBoutons2.add(listeDeroulante);
        panelBoutons2.add(new Label("Creation d'un modele"));
        panelBoutons2.add(boutonModeleFini);
        panelCreeModele.add(panelGrille2);
        panelCreeModele.add(panelBoutons2);
    }
    /**
     Méthode qui initialise les boutons du jeu
     */
    private void initialisationBouton(){
        ActionListener actionListener;
        boutonNouvellePartie = new JButton();
        boutonChargerPartie = new JButton();

        boutonAbandonner = new JButton();
        boutonIndice = new JButton();
        boutonSauvegarder = new JButton();

        boutonModeleFini = new JButton();
        listeDeroulante = new JComboBox();
        listeDeroulante.addItem("3x3");
        listeDeroulante.addItem("6x6");
        listeDeroulante.addItem("9x9");

        boutonNouvellePartie.setText("Nouvelle Partie");
        boutonChargerPartie.setText("Charger Partie");
        boutonIndice.setText("Indice");
        boutonSauvegarder.setText("Sauvegarder");
        boutonAbandonner.setText("Abandonner");
        boutonModeleFini.setText("Création Terminé");

        boutonNouvellePartie.setActionCommand("Nouvelle Partie");
        boutonChargerPartie.setActionCommand("Charger Partie");
        boutonAbandonner.setActionCommand("Abandonner");
        boutonSauvegarder.setActionCommand("Sauvegarder");
        boutonIndice.setActionCommand("Indice");
        boutonModeleFini.setActionCommand("Modele Fini");

        //Action listener des boutons du panelMenu
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                CardLayout cardLayout = (CardLayout) (panel.getLayout());
                String command = e.getActionCommand();
                if (command.equals("Nouvelle Partie")) {
                    cardLayout.show(panel, "pageNouvellePartie");
                } else if (command.equals("Charger Partie")) {
                    cardLayout.show(panel, "pageChargerPartie");
                }
            }
        };
        boutonNouvellePartie.addActionListener(actionListener);
        boutonChargerPartie.addActionListener(actionListener);


        //Action listener des boutons du panelJeu
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };
        boutonAbandonner.addActionListener(actionListener);
        boutonIndice.addActionListener(actionListener);
        boutonSauvegarder.addActionListener(actionListener);

        //Action listener des boutons du panelCreeModele
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Modele Fini")) {
                    tableau = new Tableau(TAILLE);
                    for (int i = 0;i<grille.length;i++){
                        for (int j = 0;j<grille.length;j++){
                            System.out.println(grille[i][j]);
                            if (grille[i][j].getBackground().equals(Color.black)){
                                tableau.setCase(i,j,-1);
                            }else if (grille[i][j].getText().equals("")){
                                tableau.setCase(i, j,0);
                            }else{
                                tableau.setCase(i, j, Integer.parseInt(grille[i][j].getText()));
                            }
                        }
                    }
                    tableau.afficherGrille();
                    try {
                        tableau.sauvegarderGrilleSerial("Modeles/");
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                    initialisationPanelJeu();
                    CardLayout cardLayout = (CardLayout)(panel.getLayout());
                    cardLayout.show(panel,"pageJeu");

                    /*
                    if (tableau.grilleValide()){
                        new JOptionPane("Modele crée");
                        CardLayout cardLayout = (CardLayout)(panel.getLayout());
                        cardLayout.show(panel,"pageJeu");
                        panelJeu.updateUI();

                    }else {
                        new JOptionPane("Non valide");
                    }*/
                    //POPUP
                }
            }
        };
        boutonModeleFini.addActionListener(actionListener);
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MouseListener mouseListener = new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (SwingUtilities.isRightMouseButton(e)){
                            JTextField textField = (JTextField)e.getSource();
                            textField.setBackground(Color.BLACK);
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
                };
                String taille = (String)listeDeroulante.getSelectedItem();
                GridBagConstraints c = new GridBagConstraints();
                c.ipadx = 30;
                c.ipady = 15;
                panelGrille2.removeAll();
                switch (taille){
                    case "3x3" :
                        grille = new JTextField[3][3];
                        for (int i = 0;i<3;i++){
                            for (int j = 0;j<3;j++){
                                TAILLE = 3;
                                c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                c.gridy = i;
                                grille[i][j] = new JTextField();
                                grille[i][j].setPreferredSize(new Dimension(35,35));
                                grille[i][j].addMouseListener(mouseListener);
                                panelGrille2.add(grille[i][j],c);
                            }
                        }
                        break;
                    case "6x6" :
                        grille = new JTextField[6][6];
                        for (int i = 0;i<6;i++){
                            for (int j = 0;j<6;j++){
                                TAILLE = 6;
                                c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                c.gridy = i;
                                grille[i][j] = new JTextField();
                                grille[i][j].setPreferredSize(new Dimension(35,35));
                                grille[i][j].addMouseListener(mouseListener);
                                panelGrille2.add(grille[i][j],c);
                            }
                        }
                        break;
                    case "9x9":
                        grille = new JTextField[9][9];
                        for (int i = 0;i<9;i++){
                            for (int j = 0;j<9;j++){
                                TAILLE = 9;
                                c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                c.gridy = i;
                                grille[i][j] = new JTextField();
                                grille[i][j].setPreferredSize(new Dimension(35,35));
                                grille[i][j].addMouseListener(mouseListener);
                                panelGrille2.add(grille[i][j],c);
                            }
                        }
                        break;
                }
                panelGrille2.updateUI();
            }
        };
        listeDeroulante.addActionListener(actionListener);


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

                if(tableau.getCase(i,j) == -1){
                    grille[i][j].setBackground(Color.BLACK);
                    grille[i][j].setEnabled(false);
                }else if(tableau.getCase(i,j) == 0){
                    grille[i][j].setText("");
                }else {
                    grille[i][j].setText("" + tableau.getCase(i, j));
                    //grille[i][j].setEnabled(false);
                }
            }
        }

    }
}
