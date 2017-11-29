package com.grupo4.inversiones.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.grupo4.inversiones.entidades.Metodologia;

public class Metodologias extends Repositorio{
	
	public Metodologias(EntityManager em) {
		super(em);
	}
	
	   public Metodologia buscarPorId(Long id){   
		   return em.find(Metodologia.class, id);
	   }
	   
	   public void borrarPorId(Long id){   
		   em.getTransaction().begin();
		   em.remove(buscarPorId(id));
		   em.getTransaction().commit();
	   }
	   
	   public long buscarDuenio(long id) {
		   return em.find(Metodologia.class, id).getDuenio();
	   }
	   
	   public Metodologia buscarPorNombre(String nombre){   
		   Query query = em.createQuery("SELECT m FROM Metodologia m WHERE m.nombre = :nombre").setParameter("nombre", nombre);
		   List<Metodologia> resultados = query.getResultList();
		   if (resultados.size() == 0) return null;
		   return (Metodologia) resultados.get(0);
	   }
	   
	   public List<Metodologia> buscarTodas(long idUsuario){
		   Query query = em.createQuery("SELECT m FROM Metodologia m WHERE m.duenio = :idUsuario").setParameter("idUsuario", idUsuario);
		   
		   List<Metodologia> metodologias = query.getResultList();
		   return metodologias;
	   }
	   
	   public void persistir(Metodologia metodologia){
		   em.getTransaction().begin();
		   em.persist(metodologia);
		   em.getTransaction().commit();
	   }

	public void editarNombreMetodologia(long id, String nombre) {
		em.getTransaction().begin();
		Metodologia metodologia = buscarPorId(id);
		metodologia.setNombre(nombre);
	    em.getTransaction().commit();
	}

	public void editarCondicionesFiltroMetodologia(long id, List<String> condicionesFiltro) {
		em.getTransaction().begin();
		Metodologia metodologia = buscarPorId(id);
		metodologia.setCondicionesFiltro(condicionesFiltro);
	    em.getTransaction().commit();
	}
	
	public void editarCondicionesOrdenMetodologia(long id, List<String> condicionesOrden) {
		em.getTransaction().begin();
		Metodologia metodologia = buscarPorId(id);
		metodologia.setCondicionesOrden(condicionesOrden);
	    em.getTransaction().commit();
	}
	
}
