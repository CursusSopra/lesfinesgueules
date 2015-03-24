/**
 * File modified by : Benoît
 */
package fr.cursusSopra.action.contenu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.cursusSopra.action.ActionSupportExtended;
import fr.cursusSopra.model.Producteur;
import fr.cursusSopra.model.Produit;

public class DetailsProducteurAction extends ActionSupportExtended {

		/* SERIAL ID */
		
		private static final long serialVersionUID = -1802942881247205702L;
		
		/* PROPERTIES */
		
		private long idProducteur;
		
		private String raisonSociale;
		private String siren;
		private String ligneAdresse1;
		private String ligneAdresse2;
		private String codePostal;
		private String ville;
		private String latitude;
		private String longitude;
		private String description;
		private int delaiLivraison;
		private String photo;
		
		List<Produit> listeProduits = new ArrayList<Produit>();
		
		/* EXECUTE METHOD */
		
		@Override
		public String execute() throws SQLException {
			Producteur producteur = new Producteur(idProducteur);
			
			raisonSociale = producteur.getRaisonSociale();
			siren = producteur.getSiren();
			ligneAdresse1 = producteur.getLigneAdresse1();
			ligneAdresse2 = producteur.getLigneAdresse2();
			codePostal = producteur.getCodePostal();
			ville = producteur.getVille();
			latitude = producteur.getLatitude();
			longitude = producteur.getLongitude();
			description = producteur.getDescription();
			delaiLivraison = producteur.getDelaiLivraison();
			photo = producteur.getPhoto();
			
			return SUCCESS;
		}
		
		/* ACCESSORS */

		public void setIdProducteur(String idProducteur) {
			try {
				this.idProducteur = Long.parseLong(idProducteur);
			} catch (Exception e) {
				System.out.println("Conversion impossible");
			}
		}

		public long getIdProducteur() {
			return idProducteur;
		}

		public String getRaisonSociale() {
			return raisonSociale;
		}

		public String getSiren() {
			return siren;
		}

		public String getLigneAdresse1() {
			return ligneAdresse1;
		}

		public String getLigneAdresse2() {
			return ligneAdresse2;
		}

		public String getCodePostal() {
			return codePostal;
		}

		public String getVille() {
			return ville;
		}

		public String getLatitude() {
			return latitude;
		}

		public String getLongitude() {
			return longitude;
		}

		public String getDescription() {
			return description;
		}

		public int getDelaiLivraison() {
			return delaiLivraison;
		}

		public String getPhoto() {
			return photo;
		}

		public List<Produit> getListeProduits() {
			return listeProduits;
		}

}
