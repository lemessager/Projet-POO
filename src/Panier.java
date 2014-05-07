import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;





public class Panier {
	private HashMap<Marchandise,Integer> vente=new HashMap<Marchandise,Integer>();
	private HashMap<String,ArrayList<Marchandise>>  panier=new HashMap<String,ArrayList<Marchandise>> ();
	Magasin deroute;
	
	public String[] venteArray(){
	    String[] venteListe = new String[vente.size()];
	    int i=0;
	    for(Marchandise m : vente.keySet()){
	        venteListe[i]=""+m.toString()+" q="+ vente.get(m)+";\n";
	        i++;
	    }
	    return venteListe;
	}
	
	public Panier(Magasin mag){
		ArrayList<Marchandise> d=new ArrayList<Marchandise>();
		panier.put("Vetement",d);d=new ArrayList<Marchandise>();
		panier.put("Mobilier",d);d=new ArrayList<Marchandise>();
		panier.put("ElectroMenager",d);
		deroute=mag;
	}
	
	public boolean estUneRef(String reference){
		for(Marchandise m : vente.keySet()){
			if(m.getReference().equals(reference))
				return true;
		}
		return false;
	}	
	//retourne l'objet désigné par sa taille et sa ref si ils corespondent
	//sinon retourne null
	public Marchandise refVersBonneMarchandise(String taille,String reference){
		if(taille.equals("0")){
			Marchandise mar=new Marchandise(reference);
			for(Marchandise m : deroute.getEntrepot().keySet()){
				if(mar.equals(m))
					return m;
			}
			return null;
		}
		else{
		Vetement v=new Vetement(reference,taille);
		for(Marchandise m : deroute.getEntrepotAvecTaille().keySet()){
			if(v.equals((Vetement) m))
				return m;
		}
		return null;
		}
	}
	
	
	//Met à jour la quantité après l'achat
	public boolean achat(String ref,String taille,int quant){
		Marchandise m=refVersBonneMarchandise(taille, ref);
		if(!vente.containsKey(m))
			vente.put(m,0);
		deroute.setObjetEnQuestion(m);
		if(deroute.estUnCommandable()){
		if(deroute.assez(quant) ){
			deroute.enleveStock(quant);
			return true;
		}
		return false;
		}else{
			if(deroute.assez(quant,Integer.parseInt(taille)) ){
				deroute.enleveStock(quant,Integer.parseInt(taille));
				return true;
			}
			return false;
		}
	}
	
	
	
	
		//ajoute ou retirer quantite de fois m dans panier
	public void addPanier(String ref,String taille, int quantite){
		if(achat(ref,taille,quantite)){//|| (s.equals("retirer")) && achat(ref,taille,-quantite,s)){
			Marchandise m=refVersBonneMarchandise(taille, ref);
			for(int i=0;i<quantite;i++)
				decision(panier.get(m.getClass().getSimpleName()),"ajouter",m);
		}	
	}
	
	public void removePanier(String ref,String taille, int quantite){
		if(achat(ref,taille,-quantite)){//|| (s.equals("retirer")) && achat(ref,taille,-quantite,s)){
			Marchandise m=refVersBonneMarchandise(taille, ref);
			for(int i=0;i<quantite;i++)
				decision(panier.get(m.getClass().getSimpleName()),"retirer",m);
		}	
	}
	
	
	// ajoute ou retire la marchandise m du panier en fonction de s
	public void decision(ArrayList<Marchandise> am,String s,Marchandise m){
		if(s.equals("ajouter")){
			am.add(m);
			vente.put(m,vente.get(m)+1);
		}
		else if(s.equals("retirer")){
			if(am.remove(m))
				vente.put(m,vente.get(m)-1);
		}
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
	
	//applique la réduction
	public Double reduit(double d,int taux){
		return d-(d*taux/100);
	}
	
	// Convertis les marchandises d'un panier en leur prix
	public HashMap<String,ArrayList<Double>> marVersPrix(){
		HashMap<String,ArrayList<Double>> money=new HashMap<String,ArrayList<Double>>();
		Set<String> type=panier.keySet();
		for(String s : type){
			ArrayList<Double> l=new ArrayList<Double>();
			for(int i=0;i<panier.get(s).size();i++)
				l.add(i,panier.get(s).get(i).getPrix());
			money.put(s,l);
		}
		return money;
	}
	
	//Evite la duplication de ce for que l'on utilise à plusieurs reprises
	public void petitfor(ArrayList<Double> reven, int catarina, int taux){
		for (int i=0;i<catarina;i++)
			reven.set(i,reduit(reven.get(i),taux));
	}
	
	// applique la reduction au panier en fonction du code remis par le client
	public HashMap<String,ArrayList<Double>> reduction(String reduc){
		HashMap<String,ArrayList<Double>> red=marVersPrix();
		if(reduc.length()==4 && isNumeric(reduc)){
			ArrayList<Double> v=red.get("Vetement");
			ArrayList<Double> em=red.get("ElectroMenager");
			ArrayList<Double> m=red.get("Mobilier");
			if(reduc.substring(0,2).equals("40")){
				petitfor(v,Math.min(3,v.size()),40);
				petitfor(em,em.size(),20);
				petitfor(m,m.size(),10);
			}
			else if(reduc.substring(0,2).equals("30")){
				petitfor(v,v.size(),30);
				petitfor(em,em.size(),20);
				petitfor(m,m.size(),10);
			}
			else if(reduc.substring(0, 2).equals("20"))
				petitfor(v,v.size(),20);
			else
				System.out.println("Code de reduction non valide");
		}
		return red;
	}

	//Renvoie le prix total d'un panier triee
	public Double total(HashMap<String,ArrayList<Double>> PrixPanier){
		double tot=0;
		for(String s: PrixPanier.keySet()){
			for(int i=0;i<PrixPanier.get(s).size();i++)
				tot+=PrixPanier.get(s).get(i);
		}
		return tot;
	}

	public HashMap<Marchandise, Integer> getVente() {
		return vente;
	}
	
	public String toString(){
		String str="Panier [";
		for(Marchandise m : vente.keySet())
			str+=m.toString()+" q="+ vente.get(m)+";\n";
		return str+"]";
	}
	
	// Renvoie la date de livraison en fonction du type
	public String getDate(String livrai){
		Calendar calendar=Calendar.getInstance();
		//définir le format de la date
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		//String livrai=read("Type de livraison a choisir( Taper : 1) relais colis(2j) | 2) deroute(4j) | 3) a domicile (7j)");
		if(livrai.equals("1"))
			calendar.add(Calendar.DATE,2);
		else if (livrai.equals("2"))
			calendar.add(Calendar.DATE,4);
		else
			calendar.add(Calendar.DATE,7);
		return sdf.format(calendar.getTime());
			
	}
	
	public String getTicket(String reduc,String date){
		String ticket="           M&I Shop \n           Sophia Bienvenue \n           TEL: 0637043945 \n           du Lundi au Vendredi de 8h45 a 22h00 \n           le Samedi de 08h45 a 21h00";
		ticket+="\n\nDesignation                   Qtite*P.U       Montant";
		int nbreArticle=0;
		HashMap<String,ArrayList<Double>> prixDansPanier=marVersPrix(),pDPReduit=reduction(reduc);
		double montant=total(prixDansPanier),montantreduit=total(pDPReduit),reduction=montant-montantreduit;
		//System.out.println(prixDansPanier);System.out.println(pDPReduit);
		for(String s :panier.keySet()){
			ArrayList<Marchandise> m=panier.get(s);
			if(m.size() != 0){
				ticket+="\n\n"+s+"      :";
				while(m.size()!=0){
					Marchandise mar=m.get(0);
					int count=0;
					while(m.remove(mar))
						count++;
					ticket+="\n"+mar.toString()+"       "+count+"*"+mar.getPrix()+"          " +count*mar.getPrix();
					nbreArticle+=count;
		}	}	}
		String tiret="\n======================================================";
		ticket+=tiret;ticket+="\n   "+nbreArticle+" Articles    "+"TOTAL :"+montant+"€";
		ticket+=tiret;ticket+="\n                 Reduction :"+reduction+"€";
		ticket+=tiret;ticket+="\n                TOTAL A Payer:"+montantreduit+"€";
		String dateLivraison=getDate(date);
		while(dateLivraison.equals("erreur"))
			dateLivraison=getDate(date);
		ticket+="\n\n         Vous serez livré le "+dateLivraison;
		return ticket;
	}
	
	
}
