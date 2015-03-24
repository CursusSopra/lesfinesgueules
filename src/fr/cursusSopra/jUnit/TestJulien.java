/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.jUnit;

import static org.junit.Assert.assertEquals;

import java.util.List;

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
	public void testCommande() {

		// Creation object Commande de l'user 1, va retourner le panier (vide)
		Commande mycom = new Commande(1);

		assertEquals(-1, mycom.getEtat());
		assertEquals(-1, mycom.getIdCommande());

		// produit id = 10, quantite = 2
		mycom.addItemCommande(10, 2);
		mycom.addItemCommande(2, 5);

		// show la liste des itemscommandes
		listerItems(mycom.getListeItems());

		// sauvergarde en db
		mycom.save();

		// show la liste des itemscommandes
		listerItems(mycom.getListeItems());

		// delete un item
		mycom.deleteItemCommande(mycom.getListeItems().get(0).getIdItemCommande());

		// show la liste des itemscommandes
		listerItems(mycom.getListeItems());

		// changer l'etat de la commande
		mycom.setEtat(0);
		mycom.setMoyenPaiement(1);

		// sauver
		mycom.save();

		// on recupere le panier, devrait etre vide
		Commande mycom2 = new Commande(1);
		listerItems(mycom2.getListeItems());

		// liste des commandes passees
		for (Commande item : Commande.listerCommandesPassees(1)) {
			System.out.println("commande n°" + item.getIdCommande() + ", etat: " + item.getEtat() + ", date val=" + item.getTsValidation() + ", date arch=" + item.getTsArchivage());
			listerItems(item.getListeItems());
		}

		// delete ce qu'on a fait
		mycom2.delete();
		mycom.delete();
	}


	public void listerItems(List<ItemCommande> mylist) {
		// show la liste des itemscommandes
		System.out.println("____________");
		for (ItemCommande item : mylist) {
			System.out.println("item id = " + item.getIdItemCommande() + ", produit = " + item.getIdProduit()+ ", quantite = " + item.getQuantite());
		}
		if (mylist.isEmpty()) System.out.println("Vide!");
	}

}