/**
 * Modified by Nicolas
 */
package fr.cursusSopra.json;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.Action;

import fr.cursusSopra.model.Type1;
import fr.cursusSopra.model.Type2;

public class JSONType2Action {
	private int idType1;
	
	private List<Type2> listType2 = new ArrayList<Type2>();

	public List<Type2> getListType2() {
		return listType2;
	}

	public void setIdType1(int idType1) {
		this.idType1 = idType1;	
		Type1 type1 = new Type1(idType1);
		System.out.println("youhouu JSON");
		listType2 = type1.getListeType2();
		
		System.out.println(listType2.get(0).getLibelle2());
//		try {
//			Connection conn = PostgresConnection.GetConnexion();
//			Statement state = conn.createStatement();
//			String query = String.format("SELECT * FROM types2 WHERE id_type1=%d ORDER BY libelle2", this.idType1);
//			ResultSet result = state.executeQuery(query);
//			while (result.next()) {
//				Type2 t = new Type2(result.getInt("id_type2"), result.getString("libelle2"));
//				
//				listType2.add(t);
//				System.out.println(listType2);
//			}
//		} catch (Exception e) {
//		}
	}

	public JSONType2Action() {
	}

	public String execute() {
		return Action.SUCCESS;
	}
}
