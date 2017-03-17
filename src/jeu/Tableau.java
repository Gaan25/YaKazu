package jeu;

import java.io.*;
import java.sql.Time;
import java.util.Random;

public class Tableau implements Serializable{
	
	/////OPTIMISATIONS///////
	private static final long serialVersionUID = 1L;

	public void swap(Possibilitee [] t, int a, int b)
	{
	    Possibilitee c;
	    c=new Possibilitee(t[a].get_nb_possibilitees(),t[a].get_position());
	    t[a]=t[b];
	    t[b]=c;
	}
	
	public void show_tab()
	{
	    int i;
	    for(i=0;i<taille_possibilitees-1;i++)
	    {
	        System.out.println("Indice num�ro :" + i + " Position : " + valeurs_possibles[i].get_position() +" Nb Possibilitees : " +valeurs_possibles[i].get_nb_possibilitees());
	    }
	}
	
	public void trirap(Possibilitee[]t,int g, int d)
	{
	    int v;
	    int a = g;
	    int b = d;
	    if(g<d)
	    {
	        v=t[(a+b)/2].get_nb_possibilitees();
	        while(a<=b)
	        {
	            while(t[a].get_nb_possibilitees()<v) a++;
	            while(t[b].get_nb_possibilitees()>v) b--;
	            if(a<=b)
	            {
	                swap(t,a,b);
	                a++;
	                b--;
	            }
	        }
	        trirap(t,g,b);
	        trirap(t,a,d);
	    }
	}
	
	private Possibilitee valeurs_possibles [] ;
	private int taille_possibilitees;
		
	public void init_possibilitees (){
		taille_possibilitees=0;
		valeurs_possibles= new Possibilitee [getSize()*getSize()];  // en imaginant qu'on ai un tableau vide au pire cas
		int position;
		for (position=0 ; position<getSize()*getSize() ; position++)
		{
			if (getCase(position) == 0 )
			{
			valeurs_possibles[taille_possibilitees] = (new Possibilitee(tailles_max[position], position));
			taille_possibilitees ++; // une fois arriv� a la fin, le tableau aura une case en trop
			}
		}
		tri_possibilitees();
			
	}
	
	public void tri_possibilitees(){
		trirap(valeurs_possibles,0,taille_possibilitees-1);
	}
		
	private int tailles_max[];
	
	public void init_tailles_max ()
	{
		tailles_max = new int [getSize()*getSize()];
		int position;
		int i , j ;
		int taille_ligne, taille_colonne;
		for (position=0 ; position<getSize()*getSize() ; position++){
			i = getLigne(position);
			j = getColonne(position);
			taille_ligne = taille_ligne_courante(i,position);
			taille_colonne = taille_colonne_courante (j,position);			
			if (taille_ligne>taille_colonne)
					tailles_max[position] = taille_colonne;
			else 
					tailles_max[position] = taille_ligne;
			if (taille_ligne==getSize()+1 && taille_ligne==getSize()+1)
					tailles_max[position] = taille_colonne;
		}
	}
		
	public void montrer_max_pos()
	{
			for (int i = 0 ; i<(getSize()*getSize()) ; i++)
			{
				if (i!=0)
				{
				if (i%(getSize())==0)
					System.out.println("");
				}
			System.out.print(tailles_max[i]+ " ");	
			}
	}
	
	////FIN OPTIMISATIONS////
	
	private int tabCase[][];
	int size;
	public int[][] getTabCase(){
		return tabCase;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getLigne(int position){
		return position/getSize();
	}
	
	public int getColonne(int position){
		return position%getSize();
	}
	
	public int getCase(int i , int j){
		return tabCase[i][j];
	}
	
	public void setCase(int i, int j, int value){
		tabCase[i][j]=value;
	}
	
	public int getCase(int position){
		if (position<0) return -1;

		int i = position/(getSize());
		int j = position%(getSize());
		return tabCase[i][j];
	}

	public void modifier_case (int i,int j, int chiffre){

		if (getSize()<chiffre) System.out.println("Impossible la taille tu tableau est de"+ getSize()+"*" + getSize());
		else
			tabCase[i][j]=chiffre;
	}
	
	public void init_tableau(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				tabCase[i][j]= 0;
			}
		}
	}

	public Tableau (int size){
		setSize(size);
		if (getSize()>=3 && getSize()<=9){
			tabCase = new int[size][size];
			init_tableau();
		}
		else System.out.println("Erreur : la grille a un format compris entre 3x3 et 9x9");

	}
	//Walid = j'ai rajouter ce constructeur pour alleger l'initialisation de la grille dans le main
	public Tableau (int tab[][], int size){
		this(size);
		//int numCase = 0;
		for (int i = 0;i<size;i++){
			for (int j = 0 ;j<size;j++){
				modifier_case(i,j,tab[i][j]);
				//modifier_case(numCase,tab[i][j]);
				//numCase++;
			}
		}
	}
	public String toString(){
		StringBuffer s1 = new StringBuffer(255);
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				if(tabCase[i][j]<0) 
					s1.append(" N");
				else if(tabCase[i][j]==0) 
					s1.append(" V");
				else 
					s1.append(" "+tabCase[i][j]);
				if (j==getSize()-1){
					s1.append("\n");
				}
				/*System.out.println(tabCase[i][j].toString());*/
			}
		}
		String s2 = s1.toString();
		return s2;
	}
///////////////////////////VERIFICATION GRILLE VALIDE///////////////////////
	
	public void init_verification(boolean tableau[], int size){
		for (int i = 0 ; i<size ; i++){
			tableau[i]=false;
		}
	}
	
	public boolean grilleValide(){
		init_possibilitees();
		init_tailles_max();
		boolean tableau [] = new boolean[getSize()+1];
		init_verification(tableau, getSize()+1);
		for (int position = 0 ; position<getSize()*getSize(); position++){
			int i = position/getSize(), j = position%getSize();
			if (getCase(position)!=-1)
			{
				if ((doublonLigneCourante(tableau,i,position)==true) || (doublonColonneCourante(tableau,j,position)==true)  || (taille_valide(getCase(position),i,j,position)==false))
				{
					return false;
				}
			}
		}
		return true;	
	}
	public boolean doublonLigneCourante (boolean tableau[], int i, int position)
	{
		//	if (k>taille) return false;
		int debut = trouver_premiere_case_ligne_courante(i,position);
		int fin = trouver_derniere_case_ligne_courante(i,position);
		for (int j=debut; j <= fin; j++)
		{
			if(tableau[getCase(j)]==false)
			tableau[getCase(j)]=true;
			else {
				System.out.println("FALSE : " + getCase(j) + " deja sur ligne : " +i);
				return true;
			}
		}
		init_verification(tableau,getSize()+1);
		return false;
	}

	
	public boolean doublonColonneCourante (boolean tableau[], int j, int position)
	{
		//	if (k>taille) return false;
		int debut = trouver_premiere_case_colonne_courante(j,position);
		int fin = trouver_derniere_case_colonne_courante(j,position);
		for (int i=debut; i <= fin; i=i+getSize())
		{
			if(tableau[getCase(i)]==false)
				tableau[getCase(i)]=true;
				else {
					System.out.println("FALSE : " + getCase(i) + " deja sur colonne : " +j);
					return true;
				}
		}
		init_verification(tableau,getSize()+1);
		return false;
	}
	
/////////////////////ALGORITHME DU BACKTRACKING///////////////////////////
	

	public boolean estValide (int position,long timeOut) {
		
		//On y a ajoute un timeOut pour optimiser le temps d'execution celon les cas ou la grille est trop longue a resoudre
		if (System.currentTimeMillis() - timeOut > 500){
			return false;
		}
		// Si on est a la n*n eme case (on sort du tableau), on s'arrete ici car le parcours aura ete fait en entier
	//	if (position == (getSize()*getSize())) ->Plus un parcours lineaire
		if (position == taille_possibilitees)
			return true;

		// On recupere les coordonnees de la case
		// int i = position/getSize(), j = position%getSize();
		
		int i = (valeurs_possibles[position].get_position())/getSize();
		int j = (valeurs_possibles[position].get_position())%getSize();


		// Si la case n'est pas vide, on passe a la suivante (appel recursif)
		if (tabCase[i][j] != 0)
			return estValide(position+1,timeOut);

		//PRINCIPE DU BACKTRACKING : TESTER UNE POSSIBILITEE VALIDE ET EFFECTUER UN PARCOURS RECURSIF AVEC CETTE POSSIBILITEE//
		for (int k=1; k <= getSize(); k++) 
		{
			if ((absentLigneCourante(k,i,(valeurs_possibles[position].get_position()))) && (absentColonneCourante(k,j,(valeurs_possibles[position].get_position())))  && (taille_valide(k,i,j,(valeurs_possibles[position].get_position()))) ) /*&& valideLigne(k,position,i) && valideColonne(k,position,j)*//* && valideColonne(k,position,j)*/
			{
				modifier_case(i,j,k);
				if (estValide (position+1,timeOut))
					return true;
			}
		}
		modifier_case(i,j,0);// = On renonce a cette possibilitee , on remet la case a 0 //
		return false; 
	}
	
	public boolean taille_valide (int k , int i ,int j ,int position){
		if (k > tailles_max[position])
			return false;
		else return true;

	}
	
	public int taille_ligne_courante(int i , int position){
		if (getCase(position)==-1) return 0;

		int pos_dernier_ligne= ((i+1)*getSize())-1; //Tester sur un dessin de grille pour comprendre
		int pos_premier_ligne= ((i)*getSize());
		int size_ligne = 1;
		int j = 1 ;

		while ((position-j>=pos_premier_ligne) && ( getCase(position-j) !=- 1 )){ //On fait un parcours a gauche de la ligne
			size_ligne++;
			j++;
		}
		j=1;
		while ((position+j<=pos_dernier_ligne) && (getCase(position+j) != -1)){ // On fait un parcours a droite de la ligne
			size_ligne++;
			j++;
		}
		if (size_ligne==1) size_ligne=getSize()+1; // Regle du Jeu : si on a une ligne de 1 case, on ne peut pas imposer comme chiffre seulement 1 ! Ici on renvoie getSize()+1 pour dire : pas de restriction.
		return size_ligne;
	}
	
	public int pos_case_precedente(int j,int position){
		return position-(getSize()*1);
	}
	
	public int taille_colonne_courante(int j , int position){
		if (getCase(position)==-1) return 0;

		int pos_dernier_colonne= ((getSize()*getSize())-1) - (getSize()-(j+1));
		int pos_premier_colonne= j;
		int size_colonne= 1;
		int i = 1 ;
		int pos_case_precedente= position-(getSize()*i);
		int pos_case_suivante= position+(getSize()*i);

		while ((pos_case_precedente >= pos_premier_colonne) && ( getCase(pos_case_precedente) != -1 )){ // On fait un parcours vers le haut de la colonne
			size_colonne++;
			i++;
			pos_case_precedente= position-(getSize()*i);
		}
		i=1;
		while ((pos_case_suivante<=pos_dernier_colonne) && (getCase(pos_case_suivante) != -1)){ // On fait un parcours vers le bas de la colonne
			size_colonne++;
			i++;
			pos_case_suivante= position+(getSize()*i);
		}
		if(size_colonne==1) size_colonne=getSize()+1; // Regle du Jeu : si on a une colonne de 1 case, on ne peut pas imposer comme chiffre seulement 1 !
		return size_colonne;
	}
	
	public int trouver_premiere_case_ligne_courante(int i , int position){
		int pos_premier_ligne= ((i)*getSize());
		int j =1;
		while ((position-j>=pos_premier_ligne) && ( getCase(position-j) !=- 1 )){
			j++;
		}
		return position-(j-1);
	}
	
	public int trouver_derniere_case_ligne_courante(int i , int position){
		int pos_dernier_ligne= ((i+1)*getSize())-1;
		int j =1;
		while ((position+j<=pos_dernier_ligne) && ( getCase(position+j) !=- 1 )){
			j++;
		}
		return position+(j-1);
	}
	
	public boolean absentLigneCourante (int k, int i, int position) //Si un chiffre est present sur la colonne, renvoie false ! 
	{
		int debut = trouver_premiere_case_ligne_courante(i,position);
		int fin = trouver_derniere_case_ligne_courante(i,position);
		for (int j=debut; j <= fin; j++)
		{
			if (getCase(j) == k)
				return false;
		}
		return true;
	}
	
	public int trouver_premiere_case_colonne_courante(int j , int position){
		int pos_premier_colonne= j;
		int i =1;
		int pos_case_precedente= position-(getSize()*i);

		while ((pos_case_precedente>=pos_premier_colonne) && ( getCase(pos_case_precedente) !=- 1 )){
			i++;
			pos_case_precedente= position-(getSize()*i);
		}
		return pos_case_precedente+(getSize());
	}
	
	public int trouver_derniere_case_colonne_courante(int j , int position){

		int pos_dernier_colonne= ((getSize()*getSize())-1) - (getSize()-(j+1));
		int i =1;
		int pos_case_suivante= position+(getSize()*i);

		while ((pos_case_suivante<=pos_dernier_colonne) && ( getCase(pos_case_suivante) !=- 1 )){
			i++;
			pos_case_suivante= position+(getSize()*i);
		}
		return pos_case_suivante-(getSize());
	}
	
	public boolean absentColonneCourante (int k, int j, int position) //Si un chiffre est present sur la colonne, renvoie false ! 
	{
		int debut = trouver_premiere_case_colonne_courante(j,position);
		int fin = trouver_derniere_case_colonne_courante(j,position);
		for (int i=debut; i <= fin; i=i+getSize())
		{
			if (getCase(i) == k)
				return false;
		}
		return true;
	}

/////////////////////////SAUVEGARDE///////////////////////////////////
	
	public void sauvegarderGrilleTexte() throws Exception{
		int numFichier;
		File di = new File("Modeles/");
		File fl[] = di.listFiles();
		numFichier = 1;
		if (fl != null){
			numFichier = fl.length+1;
		}
		FileWriter fileWriter = new FileWriter("Modeles/modele"+numFichier+".txt");
		for(int i = 0;i<this.tabCase.length;i++){
			for (int j = 0;j<this.tabCase[i].length;j++){
				fileWriter.write(String.valueOf(this.tabCase[i][j]));
			}
			fileWriter.write("\n");
		}
		fileWriter.close();
	}
	public void restaurerGrilleTexte(String nomFichier) throws Exception{
		LineNumberReader in = new LineNumberReader(new FileReader(nomFichier));
		String str ="";
		int j = 0;
		this.tabCase = null;
		while((str = in.readLine()) != null){
			if (this.tabCase == null){
				this.tabCase = new int[str.length()][str.length()];
			}
			for(int i = 0;i<str.length();i++) {
				this.tabCase[i][j]=Integer.parseInt(Character.toString(str.charAt(i)));
			}
			j++;
		}
		in.close();
	}
	public static void sauvegarderGrilleSerial(Tableau tableau,String path) throws Exception {
		int numFichier;
		File di = new File(path);
		File fl[] = di.listFiles();
		numFichier = 1;
		if (fl != null){
			numFichier = fl.length+1;
		}
		FileOutputStream fileOut = new FileOutputStream(path+"grille_"+numFichier+".ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(tableau); //NOT SERIALIZABLE EXCEPTION
		fileOut.close();
		out.close();
	}
	public static Tableau restaurerGrilleSerial(String nomFichier) throws Exception{
		FileInputStream fileIn = new FileInputStream(nomFichier);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		Tableau tableau = (Tableau) in.readObject();
		fileIn.close();
		in.close();
		return tableau;
	}
	public void afficherGrille(){
		System.out.println("Grille : ");

		System.out.println(this.toString());
	}
	public void genererGrillee(int difficulte,int taille) throws Exception {
		int i;
		int j;
		int nbCaseNoire = 0;
		int nbCaseVide = 0; //nombre de case vide (nbCase - nbCaseRemplie)
		int nbCaseChiffre; //nombre de case avec chiffre (nbCaseRemplie - nbCaseNoire)
		Random random = new Random();
		int chiffreAleatoire; // variable pour cree un nombre aleatoire et proportionnel entre les cases noires et les cases pr�remplie(noire et chiffre proportionnel)
		int nbCaseRemplie; //Case noire + case chiffre
		int nbCase = (taille * taille);
		long tempsDebut;
		long tempsFin;
		long timeOut=1;
		/*
			La difficulte est determinee par le nombre de case preremplie (noires ou chiffre)
		 */
		tempsDebut = System.currentTimeMillis();
		while(true){
			init_tableau();
			//On gere nbCaseNoire et nbCaseVide selon la difficulté
			switch (difficulte) {
				case 1 : //facile 50% des cases d�ja remplie
					nbCaseRemplie  = nbCase/ 2;
					chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 � nbCaseRemplie
					nbCaseNoire = chiffreAleatoire;
					nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
					nbCaseVide = nbCase - nbCaseRemplie;
					break;
				case 2 : //normale 40 % des cases remplie
					nbCaseRemplie  = (int) (nbCase * 0.4);
					chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 � nbCaseRemplie
					nbCaseNoire = chiffreAleatoire;
					nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
					nbCaseVide = nbCase - nbCaseRemplie;
					break;
				case 3 : //difficile 30 % des cases remplie
					nbCaseRemplie  = (int) (nbCase * 0.3);
					chiffreAleatoire = random.nextInt(nbCaseRemplie - 1)+1; //de 1 � nbCaseRemplie
					nbCaseNoire = chiffreAleatoire;
					nbCaseChiffre = nbCaseRemplie - nbCaseNoire;
					nbCaseVide = nbCase - nbCaseRemplie;
					break;
				default:
					throw new Exception("Pas de difficulte valide, choisir entre 1,2 et 3");
			}
			//On place les nbCasesNoires
			while (nbCaseNoire != 0){
				i = random.nextInt(taille);
				j = random.nextInt(taille);
				//CORRECTION
				if (this.tabCase[i][j]!= -1){
					this.tabCase[i][j] = -1;
					nbCaseNoire --;
				}
				//FIN CORRECTION
			}
			// on remplie la grille à l'aide du backtracking
			init_tailles_max(); // OPTI 1
            init_possibilitees(); // OPTI 2
			if (!estValide(0,System.currentTimeMillis())){
				System.out.println("Non valide ");
			}else {
				tempsFin = System.currentTimeMillis();
				System.out.println("Temps de génération via backtracking : " + (tempsFin-tempsDebut) +" millisecondes");
				break;
			}
		}

		//On vide de nbCaseVide
		while (nbCaseVide!= 0){
			i = random.nextInt(taille);
			j = random.nextInt(taille);
			if (tabCase[i][j] == -1){
				continue;
			}
			this.tabCase[i][j] = 0;
			nbCaseVide --;
		}

		//Affichage
		System.out.println("Grille générée par genererGrille() avant résolution");
		afficherGrille();
	}
	
	

}