package com.intiformation.projetecole.test;

import com.intiformation.projetecole.dao.AdministrateurDao;
import com.intiformation.projetecole.dao.EtudiantDao;
import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;

public class AppTestAdmin {

	public static void main(String[] args) {
		 
			// +++++++++++++Ajouter un �tudiant +++++++++++++++++++++++++++//

				Adresse ad = new Adresse("85 rue des moines", "65000", "tarbes");
				// D�finition de quelque etudiant
				Administrateur admin1 = new Administrateur("admin1", "admin1", "edouard", "ea.@", ad);
				
				
				AdministrateurDao adminDao = new AdministrateurDao();
				
				// // Ajout
				adminDao.ajouter(admin1);

		
				System.out.println("ajouter____");
				System.out.println(admin1);

				System.out.println("ajouter____");
	}
	
	
}
