package fr.cursusSopra.dataLayer;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.cursusSopra.model.Producteur;


public class ProducteurDal_oldNicolas extends DataLayerExtended {
	
	private long idType1Dal;
	private String libelle1Dal;
	

	static private HashMap<Integer, String> listeTypes1;
	static private HashMap<Integer, String> listeTypes2;
	static private HashMap<Integer,String> listeProducteurs;
	static private List<Producteur> Producteurs;
	
	
	static private final String rqType1 = "SELECT * FROM types1";
	static private final String rqType2 = "SELECT * FROM types2";
	static private final String rqProducteurs = "SELECT * FROM producteurs";

	
	public void getListeProducteur(){
		
		long idProducteur;
		
		String raisonSociale;
		String siren;
		String ligneAdresse1;
		String ligneAdresse2;
		String codePostal;
		String ville;
		String latitude;
		String longitude;
		String description;
		int delaiLivraison;
		String photo;
		
				
		PreparedStatement psType1;
		PreparedStatement psType2;
		PreparedStatement psProducteurs;
		
		try {
			psType1 = connection.prepareStatement(rqType1);
			psType2 = connection.prepareStatement(rqType2);
			psProducteurs = connection.prepareStatement(rqProducteurs);
			ResultSet rsQueryT1 = psType1.executeQuery();
			ResultSet rsQueryT2 = psType2.executeQuery();
			ResultSet rsQueryP = psProducteurs.executeQuery();
			
			listeTypes1 = new HashMap<Integer, String>();
			listeTypes2 = new HashMap<Integer, String>();
			listeProducteurs = new HashMap<Integer, String>();
			Producteurs = new ArrayList<Producteur>();
			
			while (rsQueryT1.next()) {
				
				listeTypes1.put(rsQueryT1.getInt("id_type1"), rsQueryT1.getString("libelle1"));
			}
			while (rsQueryT2.next()) {
				
				listeTypes2.put(rsQueryT2.getInt("id_type2"), rsQueryT2.getString("libelle2"));
			}
			while (rsQueryP.next()) {
				
				idProducteur = rsQueryP.getInt("id_producteur");
				raisonSociale = rsQueryP.getString("raison_sociale");
				siren = rsQueryP.getString("siren");
				ligneAdresse1 = rsQueryP.getString("ligne_adresse1");
				ligneAdresse2 = rsQueryP.getString("ligne_adresse2");
				codePostal = rsQueryP.getString("code_postal");
				ville = rsQueryP.getString("ville");
				latitude = rsQueryP.getString("gpslat");
				longitude = rsQueryP.getString("gpslong");
				description = rsQueryP.getString("description");
				delaiLivraison = rsQueryP.getInt("delai_livraison");
				photo = rsQueryP.getString("photo");
				
				Producteur p = new Producteur(raisonSociale, siren, ligneAdresse1, codePostal, ville, latitude, longitude, description, delaiLivraison, photo);
				Producteurs.add(p);
				listeProducteurs.put((int) idProducteur,p.getRaisonSociale());
			}
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		
	}

	public long getIdType1Dal() {
		return idType1Dal;
	}


	public String getLibelle1Dal() {
		return libelle1Dal;
	}
	
	public HashMap<Integer, String> getListeTypes1() {
		return listeTypes1;
	}
	
	public HashMap<Integer, String> getListeTypes2() {
		return listeTypes2;
	}

	public HashMap<Integer, String> getListeProducteurs() {
		return listeProducteurs;
	}
	
	public void setListeProducteurs(HashMap<Integer, String> listeProducteurs) {
		ProducteurDal_oldNicolas.listeProducteurs = listeProducteurs;
	}
	
	public List<Producteur> getProducteurs() {
		return Producteurs;
	}

	public void setProducteurs(List<Producteur> producteurs) {
		Producteurs = producteurs;
	}


	
	
}
