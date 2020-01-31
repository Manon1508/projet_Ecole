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

		// 1. Définition d'une aide à ajouter
		Aide aide = new Aide(page, contenu);

		// 2. Récupérer l'entité manager (interaction avec la bdd)

		// 2.1 Récup d'une fabrique d'entity managed (EntityManagedFactory)
		// nom à aller chercher dans le persistence.xml (name)
		EntityManagerFactory fabriqueEntityManaged = Persistence.createEntityManagerFactory("01_intro_jpa_eclipselink");

		// 2.2 Recup d'une entityManaged à partir de la fabrique
		EntityManager entityManager = fabriqueEntityManaged.createEntityManager();

		// 2.3 récup d'une transaction (entittyTransaction) à partir de l'entityManager
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

		// 2.6 Validation de la transaction avec la méthode commit
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
	 * Méthode à définir avec JPQL
	 */
	@Override
	public List<Aide> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}// end class
