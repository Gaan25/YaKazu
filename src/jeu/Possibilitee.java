package jeu;

public class Possibilitee {

	private int nb_valeurs;
	private int position;

	public Possibilitee(int nb_valeurs, int position){
		this.position=position;
		this.nb_valeurs = nb_valeurs; 
	}

	public int get_nb_possibilitees() {
		return nb_valeurs;
	}
	public void set_nb_possibilitees(int n) {
		this.nb_valeurs=n;
	}
	
	public int get_position() {
		return position;
	}


}
