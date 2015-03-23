package fr.cursusSopra.jUnit;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

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
		myitem.save();
		
		// recupere un item deja dans la base
		ItemCommande myitem2 = new ItemCommande(myitem.getId());
		
		assertEquals(2, myitem2.getQuantite());
		
		// le modifie
		myitem2.setEtat(2);
		myitem2.setMoyenPaiement(5);
		
		// sauvegarde les modfis
		myitem2.save();
		
		// recupere un item deja dans la base
		ItemCommande myitem3 = new ItemCommande(myitem.getId());
		
		assertEquals(2, myitem3.getEtat());
		assertEquals(5, myitem3.getMoyenPaiement());
		
		// recupere la liste des itemcommande de l'user 1
		List<ItemCommande> mylist = new ArrayList<ItemCommande>();
		mylist = new ItemCommande().getListeCommandes(1, -1);
		
		for (ItemCommande item : mylist) {
			System.out.println(item.getId());
		}
		
		boolean truc = new ItemCommande(1).delete();
		System.out.println(truc);
	}

}