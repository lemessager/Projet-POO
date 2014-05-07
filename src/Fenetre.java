

import javax.swing.*; 

import java.awt.event.*;
import java.awt.*; 

// gerer les erreurs de quantité 
// print les reduction total et le ticket.
// puis le projet est fini
     
public class Fenetre extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton acheter,retirer;
	JButton fin;
	JTextField reduction;
    
    Interaction inter;
    String reduc="";
    JLabel l;
    JLabel prixDesAchats;
    private JList list = new JList();
    
    
    public Fenetre(){
    	inter=new Interaction();
    	l=new JLabel();
        setTitle("M&I Shop");
        setSize(800,600);
        this.setLayout(new GridLayout(4,1));
        
        // Bouton retirer
        JPanel panelDuBoutonRetirer = new JPanel();
        retirer = new JButton("retirer");
        retirer.addActionListener(this);
        panelDuBoutonRetirer.add(retirer);
        add(panelDuBoutonRetirer);
        
        
        
        // liste d'elements
        
       
        inter.panier();
        list = new JList();
        DefaultListModel model = new DefaultListModel();
        for (String element : inter.getP().venteArray())
            model.addElement(element);

        
        
        //model.addElement("lel");
        list.setModel(model);
        
        
        add(list);
        
        
        
        
        
        // prix de la liste d'elements
        prixDesAchats = new JLabel("Prix Total :");
        add(prixDesAchats);
        
        // boutons retirer et reduction
        JPanel panelBoutons = new JPanel();
        fin=new JButton("fin");
        fin.addActionListener(this);
        panelBoutons.add(fin);
        reduction=new JTextField("reduction");
        reduction.addActionListener(this);
        panelBoutons.add(reduction);
        add(panelBoutons);
        
        
         }
     @Override
     public void actionPerformed(ActionEvent a){
         DefaultListModel listModel =  (DefaultListModel) list.getModel();
         int selectedIndex = list.getSelectedIndex();
         if (a.getSource()==retirer){
            
             listModel.remove(list.getSelectedIndex());
             
         }
         
    	 /*if(a.getSource() == retirer){
    		 String ref;
             String taille="0";
             do{
            	 ref=JOptionPane.showInputDialog(null, "Entrez une bonne Reference" ,"Reference de la Marchandise", JOptionPane.QUESTION_MESSAGE);
             }while(!inter.getMag().estUneRef(ref) && !inter.getMag().estUneRefAvecTaille(ref));
  
           	 if(inter.getP().estUneRef(ref)){
           		 if(inter.getMag().estUneRefAvecTaille(ref)){
            		 String[] tabTaille=inter.toTableau(inter.listeTaille(ref));
            		 taille=(String) JOptionPane.showInputDialog(null,"Taille","Saisir :",JOptionPane.QUESTION_MESSAGE,null,tabTaille,tabTaille[0]);
            	 }
                
           		 Marchandise m=inter.getP().refVersBonneMarchandise(taille,ref);
                 String[] tabQuantite = new String[inter.getP().getVente().get(m)+1];
                 for(int i=0;i<=inter.getP().getVente().get(m);i++)
                	 tabQuantite[i]=""+i;
                 String quant = (String)JOptionPane.showInputDialog(null,"Quantite","Saisir :",JOptionPane.QUESTION_MESSAGE,null,tabQuantite,tabQuantite[0]); 
                 int q=Integer.parseInt(quant);
            	 inter.getP().removePanier(ref,taille,q);
            	 l.setText(""+inter.getP());
                 //System.out.println("Panier : "+inter.getP());
                 System.out.println(inter.getMag());
                 l.setText("Total :"+inter.getP().total(inter.getP().marVersPrix()));
            }
    	 }
            */ 
    	 else if(a.getSource()== reduction)
             reduc= JOptionPane.showInputDialog(null, "Code :" ,"Ancien code :"+reduc, JOptionPane.QUESTION_MESSAGE);
         else{	 
        	 String[] livraison = {"1) relais colis(2j)","2) deroute(4j)","3) a domicile (7j)"};
        	 String date = (String)JOptionPane.showInputDialog(null, "Format de livraison","Saisir :",JOptionPane.QUESTION_MESSAGE,null,livraison,livraison[2]);
        	 JOptionPane.showMessageDialog(null,inter.getP().getTicket(reduc,date.substring(0,1)), "Ticket", JOptionPane.INFORMATION_MESSAGE);
         }
            	 
     }
}     
