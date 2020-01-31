package com.intiformation.projetecole.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.intiformation.projetecole.tool.DbConnection;
import com.intiformation.projetecole.entity.Aide;
import com.intiformation.projetecole.tool.JpaUtil;

public class AideDao implements IGestion<Aide> {

	@Override
	public boolean Ajouter(Aide pAide) {

		// 1. D�finition d'une aide � ajouter
		Aide aide = new Aide(page, contenu);

		// 2. R�cup�rer l'entit� manager (interaction avec la bdd)

		// 2.1 R�cup d'une fabrique d'entity managed (EntityManagedFactory)
		// nom � aller chercher dans le persistence.xml (name)
		EntityManagerFactory fabriqueEntityManaged = Persistence.createEntityManagerFactory("01_intro_jpa_eclipselink");

		// 2.2 Recup d'une entityManaged � partir de la fabrique
		EntityManager entityManager = fabriqueEntityManaged.createEntityManager();

		// 2.3 r�cup d'une transaction (entittyTransaction) � partir de l'entityManager
		EntityTransaction transaction = entityManager.getTransaction();

		// 2.4 ouverture de la transaction
		transaction.begin();

		// 2.5 interaction avec la bdd via EM => ajout
		/**
		 * l'ajout dans la bdd se fait avec la methode persist() de EM. si la methode
		 * retourne une erreur, l'excception PersistenceException
		 */
		// try catch (rollback)
		entityManager.persist(formateur);

		// 2.6 Validation de la transaction avec la m�thode commit
		transaction.commit();
		// transaction.rollback();
		// 2.7 fermeture de ressources (EM et EMF)
		entityManager.close();
		fabriqueEntityManaged.close();

		return false;
	}

	@Override
	public boolean Supprimer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Aide GetById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean Modifier(Aide t) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * M�thode � d�finir avec JPQL
	 */
	@Override
	public List<Aide> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
