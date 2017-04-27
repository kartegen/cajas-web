package cajas.persistence.query;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import cajas.persistence.entity.ConceptosEntity;

public class ConceptoQuery {

	@PersistenceContext(name = "sitDS")
	private EntityManager entityManager;

	public ConceptosEntity obtenerConcepto(Integer idConcepto) {
		return entityManager.find(ConceptosEntity.class, idConcepto);
	}

	public List<ConceptosEntity> obtenerConceptos(String clave){
		
		List<ConceptosEntity> listaConceptos = entityManager.
				createNamedQuery("SELECT c FROM ConceptosEntity AS c WHERE c.clave=:clave", ConceptosEntity.class)
				.setParameter("clave", clave).getResultList();
		
		return listaConceptos;
	}

	public void altaConcepto(ConceptosEntity conceptoEntity) {
		entityManager.persist(conceptoEntity);
	}

	public void editarConcepto(ConceptosEntity conceptoEntity) {
		entityManager.merge(conceptoEntity);
	}

	public boolean existeConcepto(String clave, Integer aFiscal) {

		boolean bandera = false;

		try {

			// se modifica consulta, falta filtro por si esta activo.
			ConceptosEntity concepto = entityManager
					.createQuery(
							"SELECT c FROM ConceptosEntity AS c WHERE c.clave=:clave AND WHERE c.ejercicioFiscal=:ejercicioFiscal AND WHERE c.activo=1",
							ConceptosEntity.class)
					.setParameter("clave", clave).setParameter("ejercicioFiscal", aFiscal).getSingleResult();

			if (concepto != null)
				bandera = true;

		} catch (NoResultException ex) {
			bandera = false;
		}

		return bandera;
	}

}
