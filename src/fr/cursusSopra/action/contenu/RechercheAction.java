package fr.cursusSopra.action.contenu;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import fr.cursusSopra.dataLayer.admin.Type1Dal;
import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Type2;

public class RechercheAction extends ActionSupport {
	
	static private List<Type1> listeTypes1;
	private List<Type2> listeTypes2;
	static private HashMap<Integer, String> listeProducteurs;
	
	private long idType1;
	private long idType2;
	private long idProducteur;
	
	
	public String execute() {

		listeTypes1 = Type1Dal.getListeType1();
		
		//ProducteurDal rchdal = new ProducteurDal();
		//rchdal.getListeProducteur();
				
		
		
		//listeProducteurs = rchdal.getListeProducteurs();
		
		return SUCCESS;
	}
	
	

	public List<Type1>  getListeTypes1() {
		return listeTypes1;
	}


	public List<Type2> getListeTypes2() {
		return listeTypes2;
	}

	public long getIdType1() {
		return idType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public long getIdType2() {
		return idType2;
	}

	public void setIdType2(long idType2) {
		this.idType2 = idType2;
	}

	public HashMap<Integer, String> getListeProducteurs() {
		return listeProducteurs;
	}

	public void setListeProducteurs(HashMap<Integer, String> listeProducteurs) {
		RechercheAction.listeProducteurs = listeProducteurs;
	}
	
}