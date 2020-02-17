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

import com.intiformation.projetecole.entity.Enseignant;

public class EnseignantDao implements IGestion<Enseignant> {

	@Override
	public boolean ajouter(Enseignant pEnseignant) {

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
		entityManager.persist(pEnseignant);

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
		Enseignant enseignantSupp = em.find(Enseignant.class, id);

		// 4.3 suppr du formateur dans la Bdd
		em.remove(enseignantSupp);

		// 5. validation de la tx
		tx.commit();

		// 6. libération des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Enseignant getById(int id) {
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
		Enseignant enseignant = em.find(Enseignant.class, id);

		// 5. libération des ressources : fermeture des ressources :
		em.close();
		emf.close();

		return enseignant;
	}

	@Override
	public void modifier(Enseignant enseignant) {
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
		Enseignant enseignantModif = em.find(Enseignant.class, enseignant.getIdPersonne());

		// donc on modifie l'objet enseignantMotif avec les paramètres de enseignant
		enseignantModif.setIdPersonne(enseignant.getIdPersonne());
		enseignantModif.setNom(enseignant.getNom());
		enseignantModif.setPrenom(enseignant.getPrenom());
		enseignantModif.setMdp(enseignant.getMdp());
		enseignantModif.setEmail(enseignant.getEmail());
		enseignantModif.setAdresse(enseignant.getAdresse());


		// 4.3 modif du formateur dans la Bdd
		Enseignant enseignantMerged = em.merge(enseignantModif);

		// // 4.4 affichage de la modif
		// System.out.println(enseignantMerged);

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
	public List<Enseignant> getAll() {
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
		CriteriaQuery<Enseignant> criteriaQuery = criteriaBuilder.createQuery(Enseignant.class);

		/**
		 * Reproduction de la requête JPQL : SELECT e FROM etudiant e
		 */

		// 2.2.1 Construction du FROM de la requête : FROM enseignant e
		Root<Enseignant> clauseFrom = criteriaQuery.from(Enseignant.class);

		// 2.2.2 Construction du SELECT de la requête : SELECT e
		CriteriaQuery<Enseignant> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM enseignant e

		// 3. Transmission de la requête Criteria à l'EntityManager
		TypedQuery<Enseignant> getAllQuery = em.createQuery(clauseSELECT);

		// 4. Exécution et récup du résultat de la requête
		List<Enseignant> listeEnseignants = getAllQuery.getResultList();

		return listeEnseignants;


	}
}// end class
