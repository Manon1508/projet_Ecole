package com.intiformation.projetecole.test;

import com.intiformation.projetecole.dao.AideDao;
import com.intiformation.projetecole.entity.Aide;

public class AppTest {

	public static void main(String[] args) {

		// +++++++++++++Ajouter une aide +++++++++++++++++++++++++++//

		// Définition de quelque aide
		Aide aide1 = new Aide("pPage1", "pContenu1");
		Aide aide2 = new Aide("pPage2", "pContenu2");
		Aide aideArbre = new Aide(14, "pArbre", "cArbre");

		// La Dao
		AideDao aideDao = new AideDao();

		// // Ajout
//		aideDao.ajouter(aide1);
//		aideDao.ajouter(aide2);
//
//		System.out.println("ajouter____");
//		System.out.println(aide1);
//		System.out.println(aide2);
//		System.out.println("ajouter____");

		// +++++++++++++Get By Id+++++++++++++++++++++++++++//

		// // Afficher une aide via son id
//		Aide aideId1 = aideDao.getById(13);
//
//		System.out.println("Get by id---------------");
//		System.out.println("\t" + aideId1);
//		System.out.println("Get by id---------------");

		 // +++++++++++++Get All+++++++++++++++++++++++++++//
		
//		 System.out.println("Get all aide----------------");
//		 for (Aide aid : aideDao.getAll()) {
//		 System.out.println("\t>" + aid.getIdAide() + " " + aid.getPage() + " " +
//		 aid.getContenu());
//		 }
//		 System.out.println("Get all aide----------------");
		
	 // +++++++++++++ Update ++++++++++++++++++++++++++//
		 System.out.println("Modifier---------------");
		 aideDao.modifier(aideArbre);
		 System.out.println("\t Après update :");
		 Aide aidemodif = aideDao.getById(14);
		 System.out.println("\t " + aidemodif.getPage() + " " +
		 aidemodif.getContenu());
		 System.out.println("-----------------------");
		
		//// // +++++++++++++ Delete +++++++++++++++++++++++++++//

		 //System.out.println("Supprimer---------------");
		 //aideDao.supprimer(13);
	}// end main

}// end classe
