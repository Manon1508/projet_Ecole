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

import com.intiformation.projetecole.entity.Personne;

public class PersonneDao implements IGestion<Personne> {

	@Override
	public boolean ajouter(Personne pPersonne) {

		// 1. Définition d'une personne à ajouter
		Personne personne = new Personne();

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
		entityManager.persist(pPersonne);

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
		 * > La suppression se fait avec la méthode remove() de l'entity manager
		 * > Une instance doit être chargée avec d'être détruite dans la Bdd
		 *   => find()
		 */
			// 4.1 chargement du formateur à modifier
			Personne personneSupp = em.find(Personne.class, 2);
			
			// 4.3 suppr du formateur dans la Bdd
			em.remove(personneSupp);
			
		// 5. validation de la tx
		tx.commit();
		
		// 6. libération des ressources
		em.close();
		emf.close();
		return false;

	}

	@Override
	public Personne getById(int id) {
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
		Personne personne = em.find(Personne.class, 1);
		
		// 4. affichage
		System.out.println("Get personne by id : ");
		System.out.println("\t " + personne);
		
		// 5. libération des ressources : fermeture des ressources : 
		em.close();
		emf.close();

		return personne;
	}

	@Override
	public void modifier(Personne personne) {
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
		 * > La modification se fait avec la méthode merge() de l'entity manager
		 * > Une instance doit être chargée avec d'être modifiée dans la Bdd
		 *   => find()
		 */
			// 4.1 chargement du Personne à modifier
			Personne personneModif = em.find(Personne.class, 1);
			
			// 4.2 modif du formateur
			personneModif.setAdresse(personne.getAdresse());
			personneModif.setEmail(personne.getEmail());
			personneModif.setIdPersonne(personne.getIdPersonne());
			personneModif.setMdp(personne.getMdp());
			personneModif.setNom(personne.getNom());
			personneModif.setPrenom(personne.getPrenom());
			
			
			// 4.3 modif du formateur dans la Bdd
			Personne personneMerged = em.merge(personneModif);
			
			// 4.4 affichage de la modif
			System.out.println(personneMerged);
			
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
	public List<Personne> getAll() {
		// 1. Récup de l'EntityManager
		EntityManager em = Persistence.createEntityManagerFactory("01.TP_PROJET_ECOLE")
									  .createEntityManager();
		
		// 2. Construction de la requête de type Criteria
			// 2.1 Récup de l'interface principale de l'API Criteria à partir de l'EM
			// => CriteriaBuilder permet de construire la requête
			CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
			
			// 2.2 récup d'une instance de type CriteriaQuery
			// => CriteriaQuery = enveloppe dans laquelle on va construire notre requête
			// 					  définit toutes les requêtes de sélection de Bdd
			// 					  modéliste tous les clauses de requête Select du JPQL
			CriteriaQuery<Personne> criteriaQuery = criteriaBuilder.createQuery(Personne.class);
			
			/**
			 * Reproduction de la requête JPQL : SELECT e FROM etudiant e
			 */
			
				// 2.2.1 Construction du FROM de la requête : FROM personne e
				Root<Personne> clauseFrom = criteriaQuery.from(Personne.class);
				
				// 2.2.2 Construction du SELECT de la requête : SELECT e
				CriteriaQuery<Personne> clauseSELECT = criteriaQuery.select(clauseFrom); // => JPSL : SELECT e FROM etudiant e
				
		// 3. Transmission de la requête Criteria à l'EntityManager
		TypedQuery<Personne> getAllQuery = em.createQuery(clauseSELECT);
		
		// 4. Exécution et récup du résultat de la requête
		List<Personne> listePersonnes = getAllQuery.getResultList();
		
		// 5. Affichage
		System.out.println("Liste des étudiants dans la Bdd : ");
		for (Personne help : listePersonnes) {
			System.out.println(help);
		}
		return listePersonnes;

	}
}// end class
