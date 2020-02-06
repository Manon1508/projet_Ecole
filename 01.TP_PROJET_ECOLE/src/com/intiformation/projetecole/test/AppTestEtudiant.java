package com.intiformation.projetecole.test;

import com.intiformation.projetecole.dao.AdministrateurDao;
import com.intiformation.projetecole.dao.EtudiantDao;
import com.intiformation.projetecole.entity.Administrateur;
import com.intiformation.projetecole.entity.Adresse;
import com.intiformation.projetecole.entity.Etudiant;

public class AppTestEtudiant {

	public static void main(String[] args) {
	
		
		 
		// +++++++++++++Ajouter un étudiant +++++++++++++++++++++++++++//

			Adresse adresseEtu2 = new Adresse("12 rue du pape", "12000", "ville");
			Adresse adressEtu1 = new Adresse("16 rue du pape", "12000", "ville");
			// Définition de quelque etudiant
			Etudiant etu1 = new Etudiant("etu1", "durant", "marc", "m@.", "25/69/58", adressEtu1);
			Etudiant etu2 = new Etudiant("etu2", "dupont", "tom", "t.@", "15/08/58", adresseEtu2);
			Etudiant etu3 = new Etudiant("etu2", "dupont", "luc", "t.@", "15/08/58", adresseEtu2);
			EtudiantDao etuDao = new EtudiantDao();
			
			// // Ajout
			etuDao.ajouter(etu1);
			etuDao.ajouter(etu2);
			etuDao.ajouter(etu3);
			System.out.println("ajouter____");
			System.out.println(etu1);
			System.out.println(etu2);
			System.out.println(etu3);
			System.out.println("ajouter____");
			
			// +++++++++++++Ajouter un admin +++++++++++++++++++++++++++//

	}

}
