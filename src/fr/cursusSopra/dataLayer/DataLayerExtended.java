package fr.cursusSopra.dataLayer;

import java.sql.Connection;

import fr.cursusSopra.tech.PostgresConnection;

public abstract class DataLayerExtended {
	
	protected Connection connection;
	
	public DataLayerExtended() {
		
		connection = PostgresConnection.GetConnexion();
		
	}
}
