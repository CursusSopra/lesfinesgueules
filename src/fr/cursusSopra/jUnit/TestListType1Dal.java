/**
 *  Modified By Julien J
 */
package fr.cursusSopra.jUnit;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.cursusSopra.model.Type1;
import fr.cursusSopra.tech.HostnameConnection;

public class TestListType1Dal {

	@Before
	public void init() {

		// instancie l'objet HostnameConnection
		HostnameConnection hostnameConnexion = HostnameConnection.getInstance();
		hostnameConnexion.setHostName("localhost");
	}
	
	@Test
	public void testType1() {

		// Creation object type1 de clé 1
		List<Type1> lt1;
		lt1 = Type1.getListeType1();
		System.out.println(lt1);
;
	}
}
