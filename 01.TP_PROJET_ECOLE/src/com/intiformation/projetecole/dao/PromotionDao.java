package com.intiformation.projetecole.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.intiformation.projetecole.entity.Promotion;
import com.intiformation.projetecole.entity.Aide;
import com.intiformation.projetecole.entity.Promotion;

public class PromotionDao implements IGestion<Promotion> {

	@Override
	public boolean ajouter(Promotion pPromotion) {

		// 2. Récupérer l'entité manager (interaction avec la bdd)

		// 2.1 Récup d'une fabrique d'entity managed (EntityManagedFactory)
		// nom à aller chercher dans le persistence.xml (name)
		EntityManagerFactory fabriqueEntityManaged = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

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
		entityManager.persist(pPromotion);

		// 2.6 Validation de la transaction avec la méthode commit
		transaction.commit();
		// transaction.rollback();
		// 2.7 fermeture de ressources (EM et EMF)
		entityManager.close();
		fabriqueEntityManaged.close();

		return false;
	}

	@Override
	public boolean supprimer(int id) {
		// 1. récup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. récup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La suppression se fait avec la méthode remove() de l'entity manager > Une
		 * instance doit être chargée avec d'être détruite dans la Bdd => find()
		 */
		// 4.1 chargement du formateur à supprimer
		Promotion promotionSupp = em.find(Promotion.class, id);

		// 4.3 suppr du formateur dans la Bdd
		em.remove(promotionSupp);

		// 5. validation de la tx
		tx.commit();

		// 6. libération des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Promotion getById(int id) {
		// 1. récup de la factory
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		/**
		 * NB : le get by id ne nécessite pas de transaction
		 */

		// 3. récup d'un formateur par son id (pk)
		/**
		 * > via la méthode find(classe entité, clé primaire) de l'EM
		 * 
		 * > find() retourne null si aucun enregistrement n'a été effectué
		 */
		Promotion promotion = em.find(Promotion.class, id);

		// 5. libération des ressources : fermeture des ressources :
		em.close();
		emf.close();

		return promotion;
	}

	@Override
	public void modifier(Promotion promotion) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE");

		// 2. récup de l'entite manager
		EntityManager em = emf.createEntityManager();

		// 3. récup d'une transaction
		EntityTransaction tx = em.getTransaction();

		// 3.1 ouverture de la tx
		tx.begin();

		// 4. modif
		/**
		 * > La modification se fait avec la méthode merge() de l'entity manager > Une
		 * instance doit être chargée avec d'être modifiée dans la Bdd => find()
		 */
		// 4.1 chargement du formateur à modifier
		Promotion promotionModif = em.find(Promotion.class, promotion.getIdPromotion());

		// donc on modifie l'objet promotionMotif avec les paramètres de promotion
		promotionModif.setIdPromotion(promotion.getIdPromotion());
		promotionModif.setLibelle(promotion.getLibelle());
		promotionModif.setListeCours(promotion.getListeCours());
		promotionModif.setListeEtudiants(promotion.getListeEtudiants());


		// 4.3 modif du formateur dans la Bdd
		Promotion promotionMerged = em.merge(promotionModif);

		// // 4.4 affichage de la modif
		// System.out.println(promotionMerged);

		// 5. validation de la tx
		tx.commit();

		// 6. libération des ressources
		em.close();
		emf.close();

	}

	/**
	 * Méthode à définir avec JPQL
	 */
	@Override
	public List<Promotion> getAll() {
		// 1. Récup de l'EntityManager
		EntityManager em = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE").createEntityManager();

		// 2. Construction de la requête de type Criteria
		// 2.1 Récup de l'interface principale de l'API Criteria à partir de l'EM
		// => CriteriaBuilder permet de construire la requête
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		// 2.2 récup d'une instance de type CriteriaQuery
		// => CriteriaQuery = enveloppe dans laquelle on va construire notre requête
		// définit toutes les requêtes de sélection de Bdd
		// modéliste tous les clauses de requête Select du JPQL
		CriteriaQuery<Promotion> criteriaQuery = criteriaBuilder.createQuery(Promotion.class);

		/**
		 * Reproduction de la requête JPQL : SELECT e FROM etudiant e
		 */

		// 2.2.1 Construction du FROM de la requête : FROM promotion e
		Root<Promotion> clauseFrom = criteriaQuery.from(Promotion.class);

		// 2.2.2 Construction du SELECT de la requête : SELECT e
		CriteriaQuery<Promotion> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM etudiant e

		// 3. Transmission de la requête Criteria à l'EntityManager
		TypedQuery<Promotion> getAllQuery = em.createQuery(clauseSELECT);

		// 4. Exécution et récup du résultat de la requête
		List<Promotion> listePromotions = getAllQuery.getResultList();

		return listePromotions;

	}

}// end class
