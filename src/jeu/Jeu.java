package jeu;

/**
 * Created by valid13 on 06/02/2017.
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.LinkedList;
import javax.swing.*;
import javax.swing.text.NumberFormatter;
import javax.swing.text.StringContent;

/**
 * Created by valid13 on 06/02/2017.
 */

public class Jeu extends JFrame {
    JTextArea texte; // charger partie
    JTextArea texte2; // nouvelle partie
    LinkedList<String> nom_parties;
    LinkedList<String> nom_modeles;

    String[] taille_parties;
    String[] difficultees;
    String[] mode;
    private JComboBox liste_parties;
    private JComboBox listeModele;
    private JComboBox taille; // Nouvelle Partie
    private JComboBox modes;

    private JComboBox difficulte; //
    private JButton boutonJouer;// charger
    private JButton boutonRetour; //
    private JButton boutonJouer1;// nouvelle partie
    private JButton boutonRetour1; //

    private JPanel panel;
    private JPanel panelMenu;
    private JButton boutonNouvellePartie;
    private JButton boutonChargerPartie;
    private JButton boutonValider;
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
    private NumberFormatter formatter;
    private JFormattedTextField[][] grille;
    private Tableau tableau;
    private int grilleFinale [][];
    private Tableau tabFinal;

    public Jeu() {
        if (! new File("Sauvegarde/").exists()){
            new File("Sauvegarde/").mkdir();
        }
        if (!new File("Modeles/").exists()){
            new File("Modeles/").mkdir();
        }

        initialisation();
        initialisationBouton();
        initialisationPanelMenu();
        initialisationPanelCharger();
        initialisationPanelNouvellePartie();
        initialisationPanelCreeModele();
        //initialisationTableau();
        CardLayout cardLayout = (CardLayout) (panel.getLayout());

        /* TEST */
        cardLayout.show(panel, "pagePrincipale");
        //cardLayout.show(panel,"pageCreeModele");

    }

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
        panel.add(panelCreeModele, "pageCreeModele");

        //PANEL GRILLE2 DANS PANEL CREEMODELE
        panelGrille2 = new JPanel();
        panelGrille2.setLayout(new GridBagLayout());

        //PANEL BOUTONS2 DANS PANEL CREEGRILLE
        panelBoutons2 = new JPanel();
        panelBoutons2.setLayout(new GridBagLayout());
    }

    /**
     * Méthode qui ajoute tout les éléments destiner au panel Menu
     */
    private void initialisationPanelMenu() {
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
        gbc2.insets.set(0, 20, 0, 20);

        panelMenu.add(boutonNouvellePartie, gbc);
        panelMenu.add(boutonChargerPartie, gbc2);
    }

    /**
     * Méthode qui gère la vue de la grille principale
     */
    private void initialisationPanelJeu() {
        GridBagConstraints c = new GridBagConstraints();
        for (int i = 0; i < TAILLE; i++) {
            for (int j = 0; j < TAILLE; j++) {
                c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                c.gridy = i;
                c.ipadx = 30;
                c.ipady = 15;

                grille[i][j] = new JFormattedTextField();
                grille[i][j].addKeyListener(new KeyAdapter() {
                    public void keyTyped(KeyEvent e) {
                        char c = e.getKeyChar();
                        if (!((c >= '0') && (c <= '9') ||
                                (c == KeyEvent.VK_BACK_SPACE) ||
                                (c == KeyEvent.VK_DELETE))) {
                            getToolkit().beep();
                            e.consume();
                        }
                    }
                });
                grille[i][j].setEditable(true);

                if (tableau.getCase(i, j) == -1) {
                    grille[i][j].setBackground(Color.BLACK);
                } else if (tableau.getCase(i, j) > 0) {
                    grille[i][j].setText(String.valueOf(tableau.getCase(i, j)));
                }
                grille[i][j].setPreferredSize(new Dimension(35, 35));
                //grille[i][j].setBorder(new LineBorder(Color.DARK_GRAY,1));
                panelGrille.add(grille[i][j], c);
            }

            }

        panelBoutons.add(boutonValider);
        panelBoutons.add(boutonSauvegarder);
        panelBoutons.add(boutonAbandonner);
        panelJeu.add(panelGrille);
        panelJeu.add(panelBoutons);
    }

    /**
     * Méthode qui gère la vue de la creation d'une grille
     */
    private void initialisationPanelCreeModele() {

        //panelBoutons2.add(listeDeroulante);

        panelBoutons2.add(new Label("Creation d'un modele"));
        panelBoutons2.add(boutonModeleFini);
        panelCreeModele.add(panelGrille2);
        panelCreeModele.add(panelBoutons2);
    }

    private void initialisationPanelCharger() {
        //	 panelCharger.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 200;
        gbc.ipady = 20;

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 3;
        gbc2.gridy = 0;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.ipadx = 200;
        gbc2.ipady = 20;

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2;
        gbc3.gridy = 1;
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.ipadx = 150;
        gbc3.ipady = 20;
        gbc3.insets.set(0, 20, 0, 20);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 3;
        gbc4.gridy = 1;
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.ipadx = 150;
        gbc4.ipady = 20;

        panelCharger.add(boutonRetour1, gbc);
        panelCharger.add(boutonJouer1, gbc2);
        panelCharger.add(liste_parties, gbc3);
        panelCharger.add(texte, gbc4);
    }

    private void initialisationPanelNouvellePartie() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 50;
        gbc.ipady = 20;

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 2;
        gbc2.gridy = 1;
        gbc2.fill = GridBagConstraints.HORIZONTAL;
        gbc2.ipadx = 50;
        gbc2.ipady = 20;

        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.gridx = 2;
        gbc3.gridy = 0;
        gbc3.fill = GridBagConstraints.HORIZONTAL;
        gbc3.ipadx = 50;
        gbc3.ipady = 20;
        gbc3.insets.set(0, 10, 0, 10);

        GridBagConstraints gbc4 = new GridBagConstraints();
        gbc4.gridx = 3;
        gbc4.gridy = 0;
        gbc4.fill = GridBagConstraints.HORIZONTAL;
        gbc4.ipadx = 50;
        gbc4.ipady = 20;

        GridBagConstraints gbc5 = new GridBagConstraints();
        gbc5.gridx = 3;
        gbc5.gridy = 1;
        gbc5.fill = GridBagConstraints.HORIZONTAL;
        gbc5.ipadx = 50;
        gbc5.ipady = 20;

        GridBagConstraints gbc6 = new GridBagConstraints();
        gbc6.gridx = 5;
        gbc6.gridy = 0;
        gbc6.fill = GridBagConstraints.HORIZONTAL;
        gbc6.ipadx = 50;
        gbc6.ipady = 20;
        gbc6.insets.set(0, 20, 0, 20);

        panelNouvellePartie.add(boutonRetour, gbc);
        panelNouvellePartie.add(boutonJouer, gbc2);
        panelNouvellePartie.add(difficulte, gbc3);
        panelNouvellePartie.add(taille, gbc4);
        panelNouvellePartie.add(texte2, gbc5);
        panelNouvellePartie.add(modes, gbc6);
        panelNouvellePartie.add(listeModele);
    }

    /**
     * Méthode qui initialise les boutons du jeu
     */
    private void initialisationBouton() {
        ActionListener actionListener;

        //SAUVEGARDE
        nom_parties = new LinkedList<String>();
        File di = new File("Sauvegarde/");
        nom_parties.add("Selectionnez...");
        String[] listeFichierSav= di.list();
        for (String s : listeFichierSav){
            nom_parties.add(s);
        }
        //MODELES
        nom_modeles = new LinkedList<String>();
        File diSav = new File("Modeles/");
        nom_modeles.add("Selectionnez...");
        String[] listeFichierModeles= diSav.list();
        for (String s : listeFichierModeles){
            nom_modeles.add(s);
        }

        texte = new JTextArea("Rien de selectionnez"); //charge partie
        texte2 = new JTextArea("Selectionnez un format ..."); // nouvelle partie
        taille_parties = new String[]{"3x3","4x4","5x5","6x6", "7x7", "8x8", "9x9"};
        difficultees = new String[]{"Facile", "Moyen", "Difficile"};
        mode = new String[]{"Generer grille", "Dessiner un Modele","Jouer avec un Modele"};

        listeModele = new JComboBox(nom_modeles.toArray());
        liste_parties = new JComboBox(nom_parties.toArray());
        taille = new JComboBox(taille_parties);
        difficulte = new JComboBox(difficultees);
        modes = new JComboBox(mode);

        boutonNouvellePartie = new JButton();
        boutonChargerPartie = new JButton();
        boutonRetour = new JButton(); //nouvelle partie
        boutonJouer = new JButton(); //
        boutonRetour1 = new JButton(); //charger partie
        boutonJouer1 = new JButton(); //

        boutonAbandonner = new JButton();
        boutonValider = new JButton();
        boutonSauvegarder = new JButton();

        boutonModeleFini = new JButton();

        boutonNouvellePartie.setText("Nouvelle Partie");
        boutonChargerPartie.setText("Charger Partie");
        boutonValider.setText("Valider");
        boutonSauvegarder.setText("Sauvegarder");
        boutonAbandonner.setText("Abandonner");
        boutonModeleFini.setText("Creation Terminee");
        boutonRetour.setText("Retour");
        boutonJouer.setText("Jouer");
        boutonRetour1.setText("Retour");
        boutonJouer1.setText("Jouer");

        boutonNouvellePartie.setActionCommand("Nouvelle Partie");
        boutonChargerPartie.setActionCommand("Charger Partie");
        boutonAbandonner.setActionCommand("Abandonner");
        boutonSauvegarder.setActionCommand("Sauvegarder");
        boutonValider.setActionCommand("Valider");
        boutonModeleFini.setActionCommand("Modele Fini");

        boutonRetour.setActionCommand("Retour");
        boutonJouer.setActionCommand("Jouer");
        boutonRetour1.setActionCommand("Retour1"); //charger partie
        boutonJouer1.setActionCommand("Jouer1"); //
        liste_parties.setActionCommand("Partie_selectionnee");
        difficulte.setActionCommand("difficulte_selectionnee");
        taille.setActionCommand("taille_selectionnee");
        modes.setActionCommand("mode_selectionne");

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
                int flag = 0;
                CardLayout cardLayout = (CardLayout) (panel.getLayout());
                String command = e.getActionCommand();
                if(command.equals("Abandonner")){
                    JOptionPane.showMessageDialog(null,"Vous avez abandonné");
                    cardLayout.show(panel, "pagePrincipale");
                }

                if(command.equals("Sauvegarder")){
                    for (int i = 0; i < TAILLE; i++) {
                        for (int j = 0; j < TAILLE; j++) {
                            if(grille[i][j].getBackground() == Color.BLACK){
                                grilleFinale[i][j] = -1;
                            }else if(grille[i][j].getText().equals("")){
                                grilleFinale[i][j] = 0;
                            }else {
                                grilleFinale[i][j] = Integer.parseInt(grille[i][j].getText());
                            }
                        }
                    }
                    tabFinal = new Tableau(grilleFinale,TAILLE);
                    try {
                        Tableau.sauvegarderGrilleSerial(tableau,"Sauvegarde/");
                        JOptionPane.showMessageDialog(null,"Grille sauvegardée !");
                    }catch(Exception exc){
                        JOptionPane.showMessageDialog(null,"Problème lors de la sauvegarde");
                    }
                }


                if(command.equals("Valider")){
                    grilleFinale = new int[TAILLE][TAILLE];
                        for (int i = 0; i < TAILLE; i++) {
                            for (int j = 0; j < TAILLE; j++) {
                                if(grille[i][j].getBackground() == Color.BLACK){
                                    grilleFinale[i][j] = -1;
                                }else if(grille[i][j].getText().equals("")){
                                    grilleFinale[i][j] = 0;
                                }else {
                                    grilleFinale[i][j] = Integer.parseInt(grille[i][j].getText());
                                }

                            }
                        }
                        for (int i = 0; i < TAILLE; i++) {
                            for (int j = 0; j < TAILLE; j++) {
                                if(grilleFinale[i][j]==0){
                                    flag = 1;
                                }
                            }
                        }
                        tabFinal = new Tableau(grilleFinale,TAILLE);
                    tabFinal.afficherGrille();
                        if(flag ==0){
                            if(tabFinal.grilleValide()){
                                JOptionPane.showMessageDialog(null,"Félicitations, vous avez réussi !");
                            }else{
                                JOptionPane.showMessageDialog(null,"Votre solution n'est pas valide. Il doit y avoir des doublons.");
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Vous n'avez pas fini de compléter la grille");
                        }
                }
            }
        };
        boutonAbandonner.addActionListener(actionListener);
        boutonValider.addActionListener(actionListener);
        boutonSauvegarder.addActionListener(actionListener);

        //Action listener des boutons du panelCreeModele
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = e.getActionCommand();
                if (command.equals("Modele Fini")) {
                    tableau = new Tableau(TAILLE);
                    for (int i = 0; i < grille.length; i++) {
                        for (int j = 0; j < grille.length; j++) {
                            System.out.println(grille[i][j]);
                            if (grille[i][j].getBackground().equals(Color.black)) {
                                tableau.setCase(i, j, -1);
                            } else if (grille[i][j].getText().equals("")) {
                                tableau.setCase(i, j, 0);
                            } else {
                                tableau.setCase(i, j, Integer.parseInt(grille[i][j].getText()));
                            }
                        }
                    }
                    if (!tableau.estValide(0, System.currentTimeMillis())){
                        return;
                    }
                    //tableau.afficherGrille();
                    try {
                        Tableau.sauvegarderGrilleSerial(tableau,"Modeles/");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    initialisationPanelJeu();
                    CardLayout cardLayout = (CardLayout) (panel.getLayout());
                    cardLayout.show(panel, "pageJeu");

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

        //Action Listener Charge PARTIE
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) (panel.getLayout());
                String command = e.getActionCommand();
                if (command.equals("Retour1")) {
                    cardLayout.show(panel, "pagePrincipale");
                } else if (command.equals("Jouer1")) {
                    texte.setText("Jouer sur la " + liste_parties.getSelectedItem()); // reference a l'objet selectionnee
                    try{
                        tableau = Tableau.restaurerGrilleSerial("Sauvegarde/" + liste_parties.getSelectedItem());
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                    tableau.afficherGrille();
                    TAILLE = tableau.getSize();
                    grille = new JFormattedTextField[tableau.getSize()][tableau.getSize()];
                    initialisationPanelJeu();

                    cardLayout.show(panel, "pageJeu");

                } else if (command.equals("Partie_selectionnee")) {
                    if (liste_parties.getSelectedIndex() != 0)
                        texte.setText("" + liste_parties.getSelectedItem()); // pr�visualiser la grille ???
                }
            }
        };

        boutonRetour1.addActionListener(actionListener);
        boutonJouer1.addActionListener(actionListener);
        liste_parties.addActionListener(actionListener);

        //Action Listener Nouvelle PARTIE
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) (panel.getLayout());
                String command = e.getActionCommand();
                if (command.equals("Retour")) {
                    cardLayout.show(panel, "pagePrincipale");
                } else if (command.equals("Jouer")) {
                    if (texte2.getText().equals("Selectionnez un format ...") || texte2.getText().equals("Veuillez d'abord \nselectionner un format !")) {
                        texte2.setText("Veuillez d'abord \nselectionner un format !");
                    } else {
                        if (modes.getSelectedItem().equals("Dessiner un Modele")){
                            MouseListener mouseListener = new MouseListener() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    if (SwingUtilities.isRightMouseButton(e)) {
                                        JFormattedTextField textField = (JFormattedTextField) e.getSource();
                                        if (textField.getBackground().equals(Color.BLACK)){
                                            textField.setBackground(Color.WHITE);
                                        }else{
                                            textField.setBackground(Color.BLACK);
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
                            };
                            String strTaille = (String) taille.getSelectedItem();
                            GridBagConstraints c = new GridBagConstraints();
                            c.ipadx = 30;
                            c.ipady = 15;
                            panelGrille2.removeAll();
                            switch (strTaille) {
                                case "3x3":
                                    grille = new JFormattedTextField[3][3];
                                    for (int i = 0; i < 3; i++) {
                                        for (int j = 0; j < 3; j++) {
                                            TAILLE = 3;
                                            c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                            c.gridy = i;
                                            grille[i][j] = new JFormattedTextField();
                                            grille[i][j].setPreferredSize(new Dimension(35, 35));
                                            grille[i][j].addMouseListener(mouseListener);
                                            panelGrille2.add(grille[i][j], c);
                                        }
                                    }
                                    break;
                                case "6x6":
                                    grille = new JFormattedTextField[6][6];
                                    for (int i = 0; i < 6; i++) {
                                        for (int j = 0; j < 6; j++) {
                                            TAILLE = 6;
                                            c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                            c.gridy = i;
                                            grille[i][j] = new JFormattedTextField();
                                            grille[i][j].setPreferredSize(new Dimension(35, 35));
                                            grille[i][j].addMouseListener(mouseListener);
                                            panelGrille2.add(grille[i][j], c);
                                        }
                                    }
                                    break;
                                case "9x9":
                                    grille = new JFormattedTextField[9][9];
                                    for (int i = 0; i < 9; i++) {
                                        for (int j = 0; j < 9; j++) {
                                            TAILLE = 9;
                                            c.gridx = j;// A cause du GridLayout nous sommes obligés d'inverser
                                            c.gridy = i;
                                            grille[i][j] = new JFormattedTextField();
                                            grille[i][j].setPreferredSize(new Dimension(35, 35));
                                            grille[i][j].addMouseListener(mouseListener);
                                            panelGrille2.add(grille[i][j], c);
                                        }
                                    }
                                    break;
                            }
                            cardLayout.show(panel, "pageCreeModele");
                        }
                        else if (modes.getSelectedItem().equals("Generer grille")) {
                            String taille_tab = (String) taille.getSelectedItem();
                            switch (taille_tab) {
                                case "3x3":
                                    TAILLE = 3;
                                    grille = new JFormattedTextField[3][3];
                                    break;
                                case "4x4":
                                    TAILLE = 4;
                                    grille = new JFormattedTextField[4][4];
                                    break;
                                case "5x5":
                                    TAILLE = 5;
                                    grille = new JFormattedTextField[5][5];
                                    break;
                                case "6x6":
                                    TAILLE = 6;
                                    grille = new JFormattedTextField[6][6];
                                    break;
                                case "7x7":
                                    TAILLE = 7;
                                    grille = new JFormattedTextField[7][7];
                                    break;
                                case "8x8":
                                    TAILLE = 8;
                                    grille = new JFormattedTextField[8][8];
                                    break;
                                case "9x9":
                                    TAILLE = 9;
                                    grille = new JFormattedTextField[9][9];
                                    break;
                            }
                            String difficulte1 = (String) difficulte.getSelectedItem();
                            int d = 1;
                            switch (difficulte1) {
                                case "Facile":
                                    d = 1;
                                    break;
                                case "Moyen":
                                    d = 2;
                                    break;
                                case "Difficile":
                                    d = 3;
                                    break;
                            }
                            tableau = new Tableau(TAILLE);
                            grilleFinale = new int[TAILLE][TAILLE];
                            try {
                                tableau.genererGrillee(d, TAILLE);
                                initialisationPanelJeu();
                                cardLayout.show(panel, "pageJeu");
                                tableau.afficherGrille();
                                tableau.init_tailles_max(); // OPTI 1
                                tableau.init_possibilitees(); // OPTI 2
                                tableau.estValide(0, System.currentTimeMillis());
                                System.out.print("La grille est : ");
                                if (tableau.grilleValide() == true)
                                    System.out.println("VALIDE");
                                else
                                    System.out.println("Non VALIDE");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            tableau.afficherGrille();
                            try {
                                Tableau.sauvegarderGrilleSerial(tableau,"Modeles/");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                        else if (modes.getSelectedItem().equals("Jouer avec un Modele")) {
                            try {
                                tableau = Tableau.restaurerGrilleSerial("Modeles/"+(String) listeModele.getSelectedItem());
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            grille = new JFormattedTextField[tableau.getSize()][tableau.getSize()];
                            TAILLE = tableau.getSize();
                            panelJeu.removeAll();
                            initialisationPanelJeu();
                            cardLayout.show(panel, "pageJeu");
                        }
                    }
                } else if (command.equals("difficulte_selectionnee")) {
                    texte2.setText("" + difficulte.getSelectedItem() + " " + taille.getSelectedItem());
                } else if (command.equals("taille_selectionnee")) {
                    texte2.setText("" + difficulte.getSelectedItem() + " " + taille.getSelectedItem());
                } else if (command.equals("mode_selectionne")) {
                    if (modes.getSelectedItem().equals("Dessiner grille"))
                        texte2.setText("Vous allez dessiner \nvotre propre modele");
                    else if (modes.getSelectedItem().equals("Generer grille"))
                        texte2.setText("" + difficulte.getSelectedItem() + " " + taille.getSelectedItem());
                }
            }
        };

        boutonRetour.addActionListener(actionListener);
        boutonJouer.addActionListener(actionListener);
        difficulte.addActionListener(actionListener);
        taille.addActionListener(actionListener);
        modes.addActionListener(actionListener);

    }

    /**
     * Méthode d'initialisation relative à la fenetre Frame
     */
    public void commencer() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(new Dimension(700, 600));
        //pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}