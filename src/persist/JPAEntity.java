package persist;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
	private EntityManagerFactory factory;
	public JPAEntity(Class<T> entityClass) {
		this.entityClass = entityClass;
		this.getEntityManager();
	}
	
	public JPAEntity() {
		this.getEntityManager();
	}

	public EntityManager getEntityManager() {
		if (em == null) {
		    factory = Persistence.createEntityManagerFactory("WebServicesCobranza");
			em = factory.createEntityManager();
			
		}
		return em;
	}

	public void create(T entity) throws Exception {
		try {
			if(!em.isOpen()) {
			em = factory.createEntityManager();
		}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.persist(entity);		
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo create Exception: " + e);
			throw new Exception(e.getMessage());
		}
		finally {
			em.close();
		}
	}

	public void edit(T entity) throws Exception {
		try {
			if(!em.isOpen()) {
				em = factory.createEntityManager();
			}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			em.merge(entity);
			tx.commit();
		} catch (Exception e) {
			log.info("Metodo edit Exception: " + e);
			throw new Exception(e.getMessage());
		}
		finally {
			em.close();
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
	
	@SuppressWarnings({ "unchecked" })
	public List<Object[]> findAllNative(String query) throws Exception {
		try {
			if(!em.isOpen()) {
				em = factory.createEntityManager();
			}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			System.out.println("1");
			Query q = em.createNativeQuery(query);
			System.out.println(q.getResultList().size());
			return (List<Object[]>) q.getResultList();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public BigInteger findAllNative2(String query) throws Exception {
		try {
			if(!em.isOpen()) {
				em = factory.createEntityManager();
			}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			System.out.println("1");
			Query q = em.createNativeQuery(query);
			System.out.println("vamos");
			return (BigInteger) q.getSingleResult();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public Object onlyOne(String query) throws Exception {
		try {
			if(!em.isOpen()) {
				em = factory.createEntityManager();
			}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			System.out.println("1");
			Query q = em.createNativeQuery(query);
			System.out.println(q);
			return q.getSingleResult();
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
	}
	
	@SuppressWarnings({ "unchecked" })
	public boolean updateNative(String query) throws Exception {
		try {
			if(!em.isOpen()) {
				em = factory.createEntityManager();
			}
			EntityTransaction tx;
			tx = em.getTransaction();
			tx.begin();
			Query q= em.createNativeQuery(query);
			q.executeUpdate();
			return true;
		} catch (Exception e) {
			log.info("Metodo List Exception: " + e);
			throw new Exception(e.getMessage());
		} 
		finally {
			em.close();
		}
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

}
