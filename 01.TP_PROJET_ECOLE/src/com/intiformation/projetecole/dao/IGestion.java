/**
 * 
 */
package com.intiformation.projetecole.dao;

import java.util.List;

/**
 * Interface avec les 5 méthodes principales
 * 
 * @author IN-BR-026
 *
 */
public interface IGestion<T> {

	/**
	 * Méthode générique Ajouter 
	 * 
	 * @return
	 */
	public boolean Ajouter(T t);

	/**
	 * Méthode générique Supprimer
	 * @return
	 */
	public boolean Supprimer(int id);


	/**
	 * Méthode générique GetById
	 * @return
	 */
	public T GetById(int id);

	/**
	 * Méthode générique Modifier
	 * @return
	 */
	public boolean Modifier (T t);

	/**
	 * Cette méthode est faite avec JPQL
	 * Méthode générique GetAll
	 * @return
	 */
	public List<T> GetAll();

}// end Interface
