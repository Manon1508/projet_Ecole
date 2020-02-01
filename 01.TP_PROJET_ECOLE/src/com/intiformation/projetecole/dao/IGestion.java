/**
 * 
 */
package com.intiformation.projetecole.dao;

import java.util.List;

/**
 * Interface avec les 5 m�thodes principales
 * 
 * @author IN-BR-026
 *
 */
public interface IGestion<T> {

	/**
	 * M�thode g�n�rique Ajouter 
	 * 
	 * @return
	 */
	public boolean Ajouter(T t);

	/**
	 * M�thode g�n�rique Supprimer
	 * @return
	 */
	public void Supprimer(int id);


	/**
	 * M�thode g�n�rique GetById
	 * @return
	 */
	public T GetById(int id);

	/**
	 * M�thode g�n�rique Modifier
	 * @return
	 */
	public void Modifier (T t);

	/**
	 * Cette m�thode est faite avec JPQL
	 * M�thode g�n�rique GetAll
	 * @return
	 */
	public List<T> GetAll();

}// end Interface
