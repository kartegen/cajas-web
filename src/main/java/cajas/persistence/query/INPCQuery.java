package cajas.persistence.query;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cajas.persistence.entity.INPCEntity;

public class INPCQuery {

	@PersistenceContext(name = "sitDS")
	private EntityManager entityManager;

	/**********
	 * Obtener un registro inpc en base al a�o y mes que se le mandan
	 **************/
	public INPCEntity inpcEntity(Integer aFiscal, Integer mesFiscal) {
		INPCEntity inpcEntity = entityManager
				.createQuery("FROM INPCEntity a WHERE a.aFiscal=:aFiscal AND a.mesFiscal=:mesFiscal", INPCEntity.class)
				.setParameter("aFiscal", aFiscal).setParameter("mesFiscal", mesFiscal).getSingleResult();
		return inpcEntity;
	}

	/********
	 * Obtiene una lista de inpc en base al a�o de inicio y mes en conjunto con
	 * el a�o final y mes final
	 */
	public List<INPCEntity> listaINPC(Integer aFiscalInicio, Integer mesFiscalInicio, Integer aFiscalFinal,
			Integer mesFiscalFinal) {

		List<INPCEntity> listaINPC = new ArrayList<>();
		
			listaINPC = entityManager
					.createQuery("FROM INPCEntity a WHERE a.aFiscal IN(:aFiscalInicio,:aFiscalFinal) AND a.mesFiscal IN (:mesFiscalInicio,:mesFiscalFinal)",
							INPCEntity.class)
					.setParameter("aFiscalInicio", aFiscalInicio)
					.setParameter("aFiscalFinal", aFiscalFinal)
					.setParameter("mesFiscalInicio", mesFiscalInicio)
					.setParameter("mesFiscalFinal", mesFiscalFinal)
					.getResultList();

		return listaINPC;
	}

}