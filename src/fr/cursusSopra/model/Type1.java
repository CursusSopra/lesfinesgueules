package fr.cursusSopra.model;

public class Type1 {

	private long idType1;
	
	private String libelle1;
	
	public Type1 (String libelle1){
		this.setLibelle1(libelle1);
	}
	
	public Type1 (long idType1, String libelle1){
		this.setLibelle1(libelle1);
		this.setIdType1(idType1);
	}

	public long getIdType1() {
		return idType1;
	}

	public void setIdType1(long idType1) {
		this.idType1 = idType1;
	}

	public String getLibelle1() {
		return libelle1;
	}

	public void setLibelle1(String libelle1) {
		this.libelle1 = libelle1;
	}
}
