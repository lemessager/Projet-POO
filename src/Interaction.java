import java.util.ArrayList;
import java.util.Scanner;



public class Interaction {
	Magasin mag=new Magasin();
	Panier p;
	Marchandise rds36=new Vetement(59.99,"1234506","36","robedesoiree");Marchandise rds38=new Vetement(59.99,"1234506","38","robedesoiree");Marchandise rds40=new Vetement(59.99,"1234506","40","robedesoiree");
	
	Marchandise riec38=new Vetement(35.45,"1238845","38","robeimprimeencoton");Marchandise riec40=new Vetement(35.45,"1238845","40","robeimprimeencoton");Marchandise riec42=new Vetement(35.45,"1238845","42","robeimprimeencoton");Marchandise riec44=new Vetement(35.45,"1238845","44","robeimprimeencoton");
	
	Marchandise pj40=new Vetement(52.69,"1234507","40","pantalonjean");Marchandise pj42=new Vetement(52.69,"1234507","42","pantalonjean");Marchandise pj44=new Vetement(52.69,"1234507","44","pantalonjean");
	
	Marchandise pa38=new Vetement(32.45,"1204566","38","pullacrylique");Marchandise pa40=new Vetement(32.45,"1204566","40","pullacrylique");Marchandise pa42=new Vetement(32.45,"1204566","42","pullacrylique");Marchandise pa44=new Vetement(32.45,"1204566","44","pullacrylique");
	
	Marchandise batE=new ElectroMenager(26.10,"2554507","batteurelectrique");
	Marchandise rasE=new ElectroMenager(350.40,"2553563","rasoirelectrique");
	Marchandise br=new ElectroMenager(125.40,"2552333","brand");
	Marchandise we=new ElectroMenager(140.40,"2552332","weber");
	Marchandise fmo=new ElectroMenager(120.90,"2004507","fourmicroonde");
	
	Marchandise bcc=new Mobilier(420.50,"2002345","banquetteclicclac");
	Marchandise bbz=new Mobilier(399.99,"2002330","braquettebz");
	Marchandise eta=new Mobilier(36.20,"2002320","etagere");
	
	public Interaction(){
		mag.addItem(rds36,1);mag.addItem(rds38,4);mag.addItem(rds40,5);
		mag.addItem(riec38,1);mag.addItem(riec40,1);mag.addItem(riec42,2);mag.addItem(riec44,2);
		mag.addItem(pj40,1);mag.addItem(pj42,2);mag.addItem(pj44,2);
		mag.addItem(pa38,1);mag.addItem(pa40,1);mag.addItem(pa42,2);mag.addItem(pa44,2);
		mag.addItem(batE,100);
		mag.addItem(rasE,10);
		mag.addItem(br,3);
		mag.addItem(we,2);
		mag.addItem(fmo,10);
		mag.addItem(bcc,3);
		mag.addItem(bbz,3);
		mag.addItem(eta,100);
		p=new Panier(mag);
	}
	
	public String read(String demande){
		Scanner reader = new Scanner(System.in);
		System.out.println(demande);
    	return reader.nextLine();
	}
	
	//vérifie si str est un nombre entier
	public boolean isNumeric(String str){
		try{
			Integer.parseInt(str);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	
	// verifie que la taille est ok 
	public ArrayList<String> listeTaille(String ref){
		ArrayList<String> s =new ArrayList<String>();
		for(Marchandise m : mag.getEntrepotAvecTaille().keySet()){
			if(m.getReference().equals(ref)){	
				Vetement v=(Vetement) m;
				s.add(v.getTaille());
				
			}	
		}
		return s;
	}
	
	public String[] toTableau(ArrayList<String> as){
		String[] tab=new String[as.size()];
		for(int i=0;i<as.size();i++)
			tab[i]=as.get(i);
		return tab;
		
		
	}
	
	public boolean tailleOk(String taille,String ref){
		String s="Dispo : ";
		for(Marchandise m : mag.getEntrepotAvecTaille().keySet()){
			if(m.getReference().equals(ref)){
				Vetement v=(Vetement) m;
				s+=v.getTaille()+" ";
				if(v.getTaille().equals(taille))
					return true;
			}	
		}
		System.out.println("Taille invalide ("+s+ ")");
		return false;
	}
	 
	// redemande la taille si elle est erronée
	public String redemande(String ref){
		String taille = read("Entrer une taille");
		if(!tailleOk(taille, ref))
			return redemande(ref);
		return taille;
	}
	
	//Ajoute les marchandises achetés et leurs quantités dans une HashMap triée
public void  panier(){
	String ref,quant;
	String taille="0";
	do{
		//on affiche le contenu de l'entrepot
	    System.out.println(mag);
	   	System.out.println("Panier Client : "+p);
	   	//on demande les caractéristiques de la commande
	   	ref = read("entrez la reference (non pour stopper)");
	   	Integer quantite=0;
	   	if (!ref.equals("non") && !ref.equals("")){
	   		while(!mag.estUneRef(ref) && !mag.estUneRefAvecTaille(ref))
	   			ref=read("entrez la reference (non pour stopper)");
	   		if(mag.estUneRefAvecTaille(ref))
	   			taille = redemande(ref);
	   		quant=read("entrez la quantite");
	   		if(isNumeric(quant))
	   			quantite=Integer.parseInt(quant);
	    	//on stock le premier objet qui a la meme reference
	    	p.addPanier(ref,taille,quantite);
	   	}
	} while (!ref.equals("non") && !ref.equals(""));	
}

public Panier getP() {
	return p;
}

public Magasin getMag() {
	return mag;
}
		
		
		
		
		
}
