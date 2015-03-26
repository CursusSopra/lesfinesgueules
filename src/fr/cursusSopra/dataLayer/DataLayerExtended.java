/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.dataLayer;

import java.sql.Connection;
import java.sql.SQLException;

import fr.cursusSopra.tech.PostgresConnection;

/**
 * @author Julien C
 */
public abstract class DataLayerExtended {

	protected Connection connection;

	public DataLayerExtended() {
		connection = PostgresConnection.GetConnexion();
	}
	
	protected void finalize() throws Throwable {  
	    try { 
	   	 connection.close(); 
   	 } catch (SQLException e) { 
	        e.printStackTrace();
	    }
	    super.finalize();  
	}  
}
