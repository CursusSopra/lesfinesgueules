/**
 * File modified by : Julien Caillon
 */
package fr.cursusSopra.dataLayer;

import java.sql.Connection;

import fr.cursusSopra.tech.PostgresConnection;

/**
 * @author Julien C
 */
public abstract class DataLayerExtended {

	protected Connection connection;

	public DataLayerExtended() {

		connection = PostgresConnection.GetConnexion();

	}
}
