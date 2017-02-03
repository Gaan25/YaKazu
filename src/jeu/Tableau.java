package jeu;

import java.io.*;
import java.sql.Time;
import java.util.Random;

public class Tableau {

	private Case tabCase[][];
	int size;
	
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
		return tabCase[i][j].getChiffre();
	}
	public int getCase(int position){
		if (position<0) return -1;
		
		int i = position/(getSize());
		int j = position%(getSize());
		return tabCase[i][j].getChiffre();
	}

	public void modifier_case (int i,int j, int chiffre){

		if (getSize()<chiffre) System.out.println("Impossible la taille tu tableau est de"+ getSize()+"*" + getSize());
		else 
			
		tabCase[i][j].setChiffre(chiffre);
	}
	public void init_tableau(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				tabCase[i][j]= new Case(0);
			}
		}
	}

	public Tableau (int size){
		setSize(size);
		if (getSize()>=3 && getSize()<=9){
			tabCase = new Case[size][size];
			init_tableau();
		}
		else System.out.println("Erreur : la grille a un format compris entre 3x3 et 9x9");
		
	}
	//Walid = j'ai rajouter ce constructeur pour all�ger l'initialisation de la grille dans le main
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
				s1.append(tabCase[i][j].toString());
					if (j==getSize()-1){
						s1.append("\n");
					}
				/*System.out.println(tabCase[i][j].toString());*/
			}
		}
		String s2 = s1.toString();
		return s2;
	}
	
	//backtracking
	
	
	
	//position et pas i car ligne i pas assez pr�cis (cases noires)

	public boolean estValide (int position)
	{
	    // Si on est � la n*n eme case (on sort du tableau)
	    if (position == (getSize()*getSize()))
	        return true;

	    // On r�cup�re les coordonn�es de la case
	    int i = position/getSize(), j = position%getSize();
	    
	    // Si la case n'est pas vide, on passe � la suivante (appel r�cursif)
	    if (tabCase[i][j].getChiffre() != 0)
	        return estValide(position+1);

	    // A impl�menter : backtracking
	   
	    //absentLigne(k,i) n'est pas assez pr�cis, ici il nous faut la position en raison des cases noires pr�sentes sur les lignes
		    for (int k=1; k <= getSize(); k++) /*tout le tableau? risque pas de ne pas s'arreter en cas de case noire?*/
		    {
		        if ((absentLigneCourante(k,i,position)) && (absentColonneCourante(k,j,position))  && (taille_valide(k,i,j,position)) ) /*&& valideLigne(k,position,i) && valideColonne(k,position,j)*//* && valideColonne(k,position,j)*/
		        {
		        	modifier_case(i,j,k);
		        	
		            if ( estValide (position+1) )
		                return true;
		        }
		    }
		    modifier_case(i,j,0);
		    return false;
		    /*on renonce a cette "id�e"*/
	}
	public boolean taille_valide (int k , int i ,int j ,int position){
		if (k > taille_ligne_courante(i,position) || (k > taille_colonne_courante(j,position)))
			return false;
			else return true;
		
	}
	public int taille_ligne_courante(int i , int position){
		
	int pos_dernier_ligne= ((i+1)*getSize())-1;
	int pos_premier_ligne= ((i)*getSize());
	int size_ligne = 1;
	int j = 1 ;
	
	while ((position-j>=pos_premier_ligne) && ( getCase(position-j) !=- 1 )){
		size_ligne++;
		j++;
	}
	j=1;
	while ((position+j<=pos_dernier_ligne) && (getCase(position+j) != -1)){
		size_ligne++;
		j++;
	}
	if (size_ligne==1) size_ligne=getSize();
	return size_ligne;
	}
	public int pos_case_precedente(int j,int position){
		return position-(getSize()*1);
	}
	public int taille_colonne_courante(int j , int position){
		
		int pos_dernier_colonne= ((getSize()*getSize())-1) - (getSize()-(j+1));
		int pos_premier_colonne= j;
		int size_colonne= 1;
		int i = 1 ;
		int pos_case_precedente= position-(getSize()*i);
		int pos_case_suivante= position+(getSize()*i);
		
		while ((pos_case_precedente >= pos_premier_colonne) && ( getCase(pos_case_precedente) != -1 )){
			size_colonne++;
			i++;
			pos_case_precedente= position-(getSize()*i);
		}
		i=1;
		while ((pos_case_suivante<=pos_dernier_colonne) && (getCase(pos_case_suivante) != -1)){
			size_colonne++;
			i++;
			pos_case_suivante= position+(getSize()*i);
		}
		if(size_colonne==1) size_colonne=getSize();
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
	public boolean absentLigneCourante (int k, int i, int position)
	{
	//	if (k>taille) return false;
		int debut = trouver_premiere_case_ligne_courante(i,position);
		int fin = trouver_derniere_case_ligne_courante(i,position);
		for (int j=debut; j < fin; j++)
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
	public boolean absentColonneCourante (int k, int j, int position)
	{
	//	if (k>taille) return false;
		int debut = trouver_premiere_case_colonne_courante(j,position);
		int fin = trouver_derniere_case_colonne_courante(j,position);
		for (int i=debut; i < fin; i=i+getSize())
	    {
	    	if (getCase(i) == k)
	            return false;
	    }     
	    return true;
	}

	//SAUVEGARDE
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
				this.tabCase = new Case[str.length()][str.length()];
			}
			for(int i = 0;i<str.length();i++) {
				this.tabCase[i][j].setChiffre(Integer.parseInt(Character.toString(str.charAt(i))));
			}
			j++;
		}
		in.close();
	}
	public void sauvegarderGrilleSerial() throws Exception {
		int numFichier;
		File di = new File("Modeles/");
		File fl[] = di.listFiles();
		numFichier = 1;
		if (fl != null){
			numFichier = fl.length+1;
		}
		FileOutputStream fileOut = new FileOutputStream("Modeles/modele"+numFichier+".ser");
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		out.writeObject(this.tabCase);

		out.close();
	}
	public void restaurerGrilleSerial(String nomFichier) throws Exception{
		FileInputStream fileIn = new FileInputStream(nomFichier);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		this.tabCase = (Case[][]) in.readObject();
		in.close();
	}
	public void afficherGrille(){
		System.out.println("Grille : ");

		System.out.println(this.toString());
	}
	public void genererGrillee(int difficulte,int taille) throws Exception {
		init_tableau();

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
		/*
			La difficulte est determinee par le nombre de case preremplie (noires ou chiffre)
		 */
        tempsDebut = System.currentTimeMillis();

		while(true){
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
				this.tabCase[i][j].setChiffre(-1);
				nbCaseNoire --;
			}

            // on remplie la grille à l'aide du backtracking
			if (!estValide(0)){
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
			if (tabCase[i][j].getChiffre() == -1){
				continue;
			}
			this.tabCase[i][j].setChiffre(0);
			nbCaseVide --;
		}

        //Affichage
		System.out.println("Grille générée par genererGrille() avant résolution");
		afficherGrille();
	}
	
	/*public boolean absentLigne (int k, int i)
	{
	    for (int j=0; j < getSize(); j++)
	    {
	    	if (getCase(i,j) == k)
	            return false;
	        else if (getCase(i,j) == -1) 	
	        
	        	return true;
	    }
	            
	    return true;
	}

	public boolean absentColonne (int k,int j)
	{
	    for (int i=0; i < getSize(); i++)
	    {
	    	if (getCase(i,j) == k)
	            return false;
	        else if (getCase(i,j) == -1)
	        	//il y a donc i chiffres dans la ligne avant la case noire allant de 1 � i
	    		
	        	return true;
	    }
	    return true;
	   
	}
	*/
	
}
