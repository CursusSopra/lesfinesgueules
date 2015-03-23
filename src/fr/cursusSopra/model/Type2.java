package fr.cursusSopra.model;

public class Type2 {

	private long idType2;
	private long idType1;
	private String libelle2;
	
	public Type2 (String libelle2){
		this.setLibelle2(libelle2);
	}
	
	public Type2 (long idType1, long idType2, String libelle2){
		this.setLibelle2(libelle2);
		this.setIdType2(idType2);
		this.setIdType1(idType1);
	}
	public Type2 (long idType2, String libelle2){
		this.setLibelle2(libelle2);
		this.setIdType2(idType2);
	}

	public long getIdType2() {return idType2;}
	public void setIdType2(long idType2) {this.idType2 = idType2;}
	public long getIdType1() {return idType1;}
	public void setIdType1(long idType1) {this.idType1 = idType1;}
	public String getLibelle2() {return libelle2;}
	public void setLibelle2(String libelle2) {this.libelle2 = libelle2;}
}
