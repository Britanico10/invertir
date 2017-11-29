package com.grupo4.inversiones.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.grupo4.inversiones.Usuario;

public class Usuarios extends Repositorio {
	
	public Usuarios(EntityManager em) {
		super(em);
	}
	
	public Usuario buscarPorId(long id) {
		return em.find(Usuario.class, id);
	}
	
	public Usuario buscarPorNombre(String nombre) {
		Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre").setParameter("nombre", nombre);
		List<Usuario> resultados = query.getResultList();
		if (resultados.size() == 0) return null;
		return (Usuario) resultados.get(0);
	}
	
	public void persistir(Usuario usuario){
		   em.getTransaction().begin();
		   em.persist(usuario);
		   em.getTransaction().commit();
	   }

}
