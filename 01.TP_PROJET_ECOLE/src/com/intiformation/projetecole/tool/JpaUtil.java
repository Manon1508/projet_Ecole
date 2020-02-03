package com.intiformation.projetecole.tool;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

/**
 * Utilitaire qui permet de récupérer une entity manager
 * @author IN-BR-026
 *
 */
public class JpaUtil {
	
	private static EntityManager entityManager;
	private static String pu_name = "01.TP_PROJET_ECOLE";
	
	/**
	 * Récup une entityManager
	 * @return
	 */
	public static EntityManager getInstance() {
		
		if (entityManager == null) {
			
			try {
				
				entityManager = Persistence.createEntityManagerFactory(pu_name).createEntityManager();
				
				
			} catch (PersistenceException e) {
				System.out.println("la création de l'entity manager à échouée");
				e.printStackTrace();
			}
		}// end if
		
		return entityManager;
		
	}// end EntityManager

}//end class
