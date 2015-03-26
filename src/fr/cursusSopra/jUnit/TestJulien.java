/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.jUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.cursusSopra.model.Commande;
import fr.cursusSopra.model.Commentaire;
import fr.cursusSopra.model.ItemCommande;
import fr.cursusSopra.tech.EtatCommande;
import fr.cursusSopra.tech.HostnameConnection;
import fr.cursusSopra.tech.TypeCommentaire;

public class TestJulien {

	@Before
	public void init() {

		// instancie l'objet HostnameConnection
		HostnameConnection hostnameConnexion = HostnameConnection.getInstance();
		hostnameConnexion.setHostName("localhost");
	}

	@Test
	public void testCommentaire() {

		// on cree un nouveau commentaire
		Commentaire mycom = new Commentaire(2, 1, "blabla mon avis (user1) sur le produit 2 et ma note de 5", 5, TypeCommentaire.PRODUIT);

		// sauvegarde du commentaire
		mycom.save();

		// on recupere les commentaires sur le produit 1
		List<Commentaire> mylist = Commentaire.getListeCommentaires(2, TypeCommentaire.PRODUIT);

		System.out.println("____________");
		for (Commentaire item : mylist) {
			System.out.println("commentaire id = " + item.getIdCommentaire() + ", avis = " + item.getAvis() + ", ma note = " + item.getNote() + ", mon nom = " + item.getNom() + ", mon prenom = " + item.getPrenom());
		}

		// delete le comm
		mycom.delete();
	}


	@Test
	public void testCommande() {

		// Creation object Commande de l'user 2, va retourner le panier (vide)
		Commande mycom = new Commande(2);

		assertEquals(EtatCommande.PANIER, mycom.getEtat());
		assertEquals(-1, mycom.getIdCommande());

		// produit id = 1, quantite = 2
		mycom.addItemCommande(1, 2);
		mycom.addItemCommande(2, 2);
		mycom.addItemCommande(2, 3);

		assertEquals(1, mycom.getListeItems().get(0).getIdProduit());
		assertEquals(5, mycom.getListeItems().get(1).getQuantite());

		// show la liste des itemscommandes
		listerItems(mycom.getListeItems());

		assertNotEquals(-1, mycom.getIdCommande());
		assertNotEquals(-1, mycom.getListeItems().get(0).getIdItemCommande());
		assertEquals(2, mycom.getListeItems().size());

		// cout total commande
		System.out.println("mon cout total = " + mycom.calculTotalPrixCommande());

		// delete un item
		mycom.removeItemCommande(mycom.getListeItems().get(0).getIdProduit(), 999);

		assertEquals(1, mycom.getListeItems().size());

		// show la liste des itemscommandes
		listerItems(mycom.getListeItems());

		// changer l'etat de la commande
		mycom.setEtat(EtatCommande.VALIDEE);
		mycom.setMoyenPaiement(1);

		// sauver
		mycom.save();

		// on recupere le panier, devrait etre vide
		Commande mycom2 = new Commande(2);
		listerItems(mycom2.getListeItems());

		assertEquals(true, mycom2.getListeItems().isEmpty());

		// liste des commandes passees
		System.out.println("____________");
		for (Commande item : Commande.listerCommandesPassees(1)) {
			System.out.println(" > commande n°" + item.getIdCommande() + ", etat: " + item.getEtat() + ", date val=" + item.getTsValidation() + ", date arch=" + item.getTsArchivage());
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