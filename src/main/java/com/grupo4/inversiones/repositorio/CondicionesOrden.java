package com.grupo4.inversiones.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.grupo4.inversiones.App;
import com.grupo4.inversiones.entidades.condiciones.CondicionOrden;

public class CondicionesOrden extends Repositorio {

	public CondicionesOrden(EntityManager em) {
		super(em);
	}
	
	   public CondicionOrden buscarPorId(Long id){   
		   return em.find(CondicionOrden.class, id);
	   }
	   
	   public void borrarPorId(Long id){   
		   em.getTransaction().begin();
		   em.remove(buscarPorId(id));
		   em.getTransaction().commit();
	   }
	   
	   public long buscarDuenio(long id) {
		   return em.find(CondicionOrden.class, id).getDuenio();
	   }
	   
	   public CondicionOrden buscarPorNombre(String nombre){
		   Query query = em.createQuery("SELECT c FROM CondicionOrden c WHERE c.nombreCondicion = :nombre").setParameter("nombre", nombre);
		   List<CondicionOrden> resultados = query.getResultList();
		   if (resultados.size() == 0) return null;
		   return (CondicionOrden) resultados.get(0);
	   }
	   
	   public List<CondicionOrden> buscarTodas(long idUsuario){
		   EntityManager em2 = App.EM_FACTORY.createEntityManager();
		   Query query = em2.createQuery("SELECT c FROM CondicionOrden c WHERE c.duenio = :idUsuario").setParameter("idUsuario", idUsuario);
		   List<CondicionOrden> condiciones = query.getResultList();
		   return condiciones;
	   }
	   
	   public void persistir(CondicionOrden condicion){
		   em.getTransaction().begin();
		   em.persist(condicion);
		   em.getTransaction().commit();
	   }
	
}
