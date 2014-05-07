import java.util.HashMap;



public class Magasin implements CommandableAvecTaille,Commandable{
	//entrepot est l'entrepot de toutes les marchandises (clés : les Marchandises ;valeurs : les quantités)
	private HashMap<Marchandise,Integer> entrepotAvecTaille;
	private HashMap<Marchandise,Integer> entrepot;
	private Marchandise ObjetEnQuestion;

	//crée un objet de type Magasin
	public Magasin() {
		entrepot=new HashMap<Marchandise,Integer>();
		entrepotAvecTaille=new HashMap<Marchandise,Integer>();
	}
	
	//ajoute un item dans l'entrepot
	public void addItem(Marchandise theItem,Integer quantite){
		if(theItem.estUnVetement())
			entrepotAvecTaille.put(theItem,quantite);
		else
			entrepot.put(theItem,quantite);
	}
	
	public boolean estUneRefAvecTaille(String reference){
		for(Marchandise m : getEntrepotAvecTaille().keySet()){
			if(m.getReference().equals(reference))
				return true;
		}
		return false;
	}
	
	public boolean estUneRef(String reference){
		for(Marchandise m : getEntrepot().keySet()){
			if(m.getReference().equals(reference))
				return true;
		}
		return false;
	}	
	
	
	//Verifie que la quantité de m est dispo en stock
	public boolean quantitOk(int quantite,int quantiteDispo){
		if(quantiteDispo>=quantite && quantite>0)
			return true;
		return false;
	}
	
	public HashMap<Marchandise, Integer> getEntrepotAvecTaille() {
		return entrepotAvecTaille;
	}

	public HashMap<Marchandise, Integer> getEntrepot() {
		return entrepot;
	}

	@Override
	public int cle() {
		return Integer.parseInt(ObjetEnQuestion.getReference());
	}

	@Override
	public boolean assez(int n) {
		if(entrepot.get(ObjetEnQuestion)<n)
			return false;
		return true;
	}
	
	@Override
	public void enleveStock(int n) {
		if(assez(n))
			entrepot.put(ObjetEnQuestion,entrepot.get(ObjetEnQuestion)-n);
	}

	@Override
	public void enleveStock(int n, int t) {
		if(assez(n,t))
			entrepotAvecTaille.put(ObjetEnQuestion,entrepotAvecTaille.get(ObjetEnQuestion)-n);
	}

	@Override
	public boolean assez(int n, int t) {
		if(entrepotAvecTaille.get(ObjetEnQuestion)<n)
			return false;
		return true;
	}

	public boolean estUnCommandable(){
		if(ObjetEnQuestion.estUnVetement())
			return false;
		return true;
	}
	
	public void setObjetEnQuestion(Marchandise objetEnQuestion) {
		ObjetEnQuestion = objetEnQuestion;
	}
	
	public String toString() {
		String str="Magasin [";
		for(Marchandise m : entrepotAvecTaille.keySet())
			str+=m.toString()+" q="+ entrepotAvecTaille.get(m)+";";
		for(Marchandise m : entrepot.keySet())
			str+=m.toString()+" q="+ entrepot.get(m)+";";
		return str+"]";
	}
	
}
