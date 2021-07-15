package persist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public abstract class JPAEntity<T> {
	private Class<T> entityClass;

	private Logger log = Logger.getLogger(getClass().getName());
	public EntityManager em;

	public JPAEntity(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.getEntityManager();
	}
	
	public JPAEntity() {
		this.getEntityManager();
	}

	public EntityManager getEntityManager() {
		if (em == null) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServicesCobranza");
			em = factory.createEntityManager();
		}
		return em;
	}

	public void create(T entity) throws Exception {
		try {
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo create Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public void edit(T entity) throws Exception {
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo edit Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public void remove(T entity) throws Exception {
		try {
			em.remove(em.merge(entity));
		} catch (Exception e) {
			log.info("Metodo remove Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public T find(Object id) throws Exception {
		try {
			return em.find(entityClass, id);
		} catch (Exception e) {
			log.info("Metodo find Exception: " + e);
			throw new Exception(e.getMessage());
		}
	}

	public List<T> findAllSorting(String query) throws Exception {
		List<T> getList = new ArrayList<T>();
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<T> q = em.createQuery(query, entityClass);
			getList = (List<T>) q.getResultList();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		return getList;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAllNativve(String query) throws Exception {
		List<T> getList = new ArrayList<T>();
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			Query q = em.createNativeQuery(query, entityClass);
			getList = (List<T>) q.getResultList();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
		return getList;
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Vector<HashMap> findAllNative(String query) throws Exception {
		List<Object[]> getList = new ArrayList<Object[]> ();
		HashMap<String, Object> mapconvert;
		Vector <HashMap> resultList = new Vector<HashMap>();
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			Query q = em.createNativeQuery(query);
			getList = (List<Object[]>) q.getResultList();
			 for(Object[] q1 : getList){
				 mapconvert = new HashMap<String,Object>();
				 mapconvert.put("venc_permitido",q1[0]);
				 mapconvert.put("ld_permitida",q1[1]);
				 mapconvert.put("id_cliente",q1[2]);
				 mapconvert.put("id",q1[3]);
				 mapconvert.put("observaciones",q1[4]);
				 mapconvert.put("monto",q1[5]);
				 mapconvert.put("cuotas",q1[6]);
				 mapconvert.put("adeudado",q1[7]);
				 mapconvert.put("cedula",q1[8]);
				 mapconvert.put("id_cuota",q1[9]);
				 mapconvert.put("numero",q1[10]);
				 mapconvert.put("nombre",q1[11]);
				 mapconvert.put("apellidos",q1[12]);
				 mapconvert.put("monto_cuota",q1[13]);
				 mapconvert.put("fecha_vencimiento",q1[14]);
				 mapconvert.put("monto_total",q1[15]);
				 resultList.add(mapconvert);

			        
			        
			     }
			
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
		return resultList;
	}
	
	public T getLogin(String query) throws Exception {
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<T> q = em.createQuery(query, entityClass);
			return q.getSingleResult();	
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception("No existe el usuario");
		} 
		finally {
			em.close();
		}
		
	}
	
	public T getOne(String query) throws Exception {
		try {

			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			TypedQuery<T> q = em.createQuery(query, entityClass);
			return q.getSingleResult();	
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() throws Exception {
		List<T> getList = new ArrayList<T>();
		try {
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(entityClass));
			getList = em.createQuery(cq).getResultList();

		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		}
		finally {
			em.close();
		}
		return getList;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) throws Exception {
		javax.persistence.Query q;
		try {
		javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0] + 1);
		q.setFirstResult(range[0]);
		}catch(Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		}
		
		return q.getResultList();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() throws Exception {
		int cuenta = 0;
		try {
			
			javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
			cq.select(em.getCriteriaBuilder().count(rt));
			javax.persistence.Query q = em.createQuery(cq);
			cuenta = ((Long) q.getSingleResult()).intValue();
		} catch (Exception e) {
			log.info("Metodo count Exception: " + e);
			throw new Exception(e.getMessage());
		}
		return cuenta;
	}

//	public void guardar(Object object) throws Exception {
//		try {
//			EntityManager em = this.getEntityManager();
//			EntityTransaction tx;
//			tx = em.getTransaction();
//			tx.begin();
//			em.persist(object);
//			tx.commit();
//		} catch (Exception e) {
//			log.info("Metodo Guardar Exception: " + e);
//			throw new Exception(e);
//		}
//	}
//	
//	
//	public void editar(Object object) throws Exception {
//		try {
//			EntityManager em = this.getEntityManager();
//			EntityTransaction tx;
//			tx = em.getTransaction();
//			tx.begin();
//			em.merge(object);
//			tx.commit();
//		} catch (Exception e) {
//			log.info("Metodo Editar Exception: " + e);
//			throw new Exception(e.getMessage());
//		} 
//	}
//	
//	public List<?> findAll(String table) throws Exception {
//		EntityManager em = this.getEntityManager();
//		try {
//			return (List<?>) em.createNamedQuery(table + ".findAll").getResultList();
//		}catch(Exception e) {
//			log.info("Metodo Listar Exception: " + e);
//			throw new Exception(e.getMessage());
//		}
//	}
//	
//	public void getOne(String table, int id) throws Exception{
//		EntityManager em = this.getEntityManager();
//		try {
//			em.createNamedQuery(table + ".findOne" ,1);
//		}catch (Exception e){
//			log.info("Metodo getOne Exception: " + e);
//			throw new Exception(e.getMessage());
//		}
//	}

}
