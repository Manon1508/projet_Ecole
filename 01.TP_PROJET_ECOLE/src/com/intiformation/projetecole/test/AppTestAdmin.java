package com.intiformation.projetecole.test;

import com.intiformation.projetecole.dao.AdministrateurDao;
import com.intiformation.projetecole.dao.EtudiantDao;
import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.entity.Etudiant;

public class AppTestAdmin {

	public static void main(String[] args) {
		 
			// +++++++++++++Ajouter un étudiant +++++++++++++++++++++++++++//

				// Définition de quelque etudiant
				Administrateur admin1 = new Administrateur("admin1", "admin1", "Edouard", "ed.ad@gmail.fr");
				
				
				AdministrateurDao adminDao = new AdministrateurDao();
				
				// // Ajout
				adminDao.ajouter(admin1);

		
				System.out.println("ajouter____");
				System.out.println(admin1);

				System.out.println("ajouter____");
	}
	
	
}
