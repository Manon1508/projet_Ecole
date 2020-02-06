package com.intiformation.projetecole.test;

import com.intiformation.projetecole.dao.EnseignantDao;
import com.intiformation.projetecole.dao.EtudiantDao;
import com.intiformation.projetecole.entity.Enseignant;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;

public class AppTestEnseignant {

	public static void main(String[] args) {
		 
			// +++++++++++++Ajouter un étudiant +++++++++++++++++++++++++++//

				Adresse ad = new Adresse("85 rue des pointes", "64000", "Pau");
				// Définition de quelque etudiant
				Enseignant ens1 = new Enseignant("ens1", "remy", "dutana", "rd@", ad);
				
				
				EnseignantDao ensDao = new EnseignantDao();
				
				// // Ajout
				ensDao.ajouter(ens1);

		
				System.out.println("ajouter____");
				System.out.println(ens1);

				System.out.println("ajouter____");
	}
	
	
}
