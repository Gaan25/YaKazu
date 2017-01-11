
public class Tableau {
	private Case TabCase[][];
	int size;
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void init_tableau(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				TabCase[i][j]= new Case();
			}
		}
	}
	
	public Tableau (int size){
		setSize(size);
		if (getSize()>=3 && getSize()<=9){
			TabCase = new Case[size][size];
			init_tableau();
		}
		else System.out.println("Erreur : la grille a un format compris entre 3x3 et 9x9");
		
	}
	
	public void tostring(){
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				System.out.println(TabCase[i][j].toString());
			}
		}
	}
	public static void main(String[] args) {
		Tableau T = new Tableau(3);
		T.tostring();
	}
}
