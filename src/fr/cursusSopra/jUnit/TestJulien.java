package fr.cursusSopra.jUnit;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.cursusSopra.model.Commande;
import fr.cursusSopra.model.ItemCommande;
import fr.cursusSopra.tech.HostnameConnection;

public class TestJulien {

	@Before
	public void init() {
		
		// instancie l'objet HostnameConnection
		HostnameConnection hostnameConnexion = HostnameConnection.getInstance();		
		hostnameConnexion.setHostName("localhost");
	}
	
	@Test
	public void testItemCommande() {
		// TEST DE LA SELECT/UPDATE/INSERT de la classe ItemCommande
		
		// Creation object itemCommande
		ItemCommande myitem = new ItemCommande(1, 1, 2);
		
		// sauvegarde
		long id = myitem.save();
		
		// check la sauvegarde
		assertEquals(id, myitem.getId());
		
		// recupere un item deja dans la base
		ItemCommande myitem2 = new ItemCommande(myitem.getId());
		
		// check la recuperation
		assertEquals(2, myitem2.getQuantite());
		
		// le modifie
		myitem2.setEtat(2);
		myitem2.setMoyenPaiement(5);
		
		// sauvegarde les modfis
		id = myitem2.save();
		
		// check la sauvegarde
		assertEquals(id, myitem2.getId());
		
		// recupere un item deja dans la base
		ItemCommande myitem3 = new ItemCommande(myitem.getId());
		
		// verifier les modifs faites
		assertEquals(2, myitem3.getEtat());
		assertEquals(5, myitem3.getMoyenPaiement());
		
		// supprimer l'item qu'on vient de creer
		boolean isdeleted = new ItemCommande(myitem.getId()).delete();
		
		// verif de la suppression
		assertEquals(true, isdeleted);
	}
	
	@Test
	public void testCommande() {
		
		// Creation object itemCommande
		ItemCommande myitem = new ItemCommande(1, 1, 2);
		
		// recupere la liste des itemcommande de l'user
		for (ItemCommande item : Commande.listerCommandesPanier(myitem.getIdUtilisateur())) {
			System.out.println(item.getId());
		}
		
		myitem.delete();

	}

}