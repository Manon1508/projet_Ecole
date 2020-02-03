package com.intiformation.projetecole.dao;

import com.intiformation.projetecole.entity.Aide;

public class AppTest {
public static void main(String[] args) {
	// Définition de quelque aide
	  Aide aide1 = new Aide("pPage1", "pContenu1");
	  Aide aide2 = new Aide("pPage2", "pContenu2");

	  // La Dao
	  AideDao aideDao = new AideDao();
	  // Ajout
	  aideDao.ajouter(aide1);
	  aideDao.ajouter(aide2);

	  System.out.println("ajouter____");
	  System.out.println(aide1);
	  System.out.println(aide2);
	  System.out.println("ajouter____");

}
}
