package core.persistence;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Stateless
public class GenericDaoImpl implements GenericDao {

	private final static int MAX_RESULTS = 150;

	@Inject
	private Logger logger;

	@Inject
	private EntityManager em;

	@Override
	public <T extends AbstractEntity> void delete(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		if (entity.getId() != null || entity.getId().equals(0)) {
			throw new IllegalStateException("Entity must have an assigned id");
		}
		Object[] logIds = new Object[] { entity.getClass().getCanonicalName(),
				entity.getId() };
		logger.log(Level.FINE, "Delete entity::Clase={0}::Id={1}", logIds);
		if (em.contains(entity)) {
			em.remove(entity);
		} else {
			em.remove(em.merge(entity));
		}
	}

	@Override
	public <T extends AbstractEntity> void insert(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		if (entity.getId() == null || !entity.getId().equals(0)) {
			throw new IllegalStateException("Entity can't have an assigned id");
		}
		logger.log(Level.FINE, "Insert entity::Class={0}", entity.getClass()
				.getCanonicalName());
		em.persist(entity);
		logger.log(Level.FINE, "The entity has the id={0}", entity.getId());
	}

	@Override
	public <T extends AbstractEntity> T select(Class<T> clazz, Long id) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		if (id == null || id.equals(0)) {
			throw new IllegalArgumentException("Id must not be 0");
		}
		logger.log(Level.FINE, "Select entity {0} with Id={1}", new Object[] {
				clazz, id });
		return em.find(clazz, id);
	}

	@Override
	public <T extends AbstractEntity> List<T> selectAll(Class<T> clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Select all entities from class {0}", clazz);
		TypedQuery<T> typedQuery = createQueryFromCriteria(clazz, null);
		paginate(0, MAX_RESULTS, typedQuery);
		return typedQuery.getResultList();
	}

	@Override
	public <T extends AbstractEntity> T selectByProperties(Class<T> clazz,
			Map<String, Object> properties) {
		TypedQuery<T> typedQuery = createQueryFromCriteria(clazz, properties);
		parametrize(properties, typedQuery);
		return typedQuery.getSingleResult();
	}

	@Override
	public <T extends AbstractEntity> List<T> selectByProperties(
			Class<T> clazz, Map<String, Object> properties, int pageSize,
			int pageNumber) {
		TypedQuery<T> typedQuery = createQueryFromCriteria(clazz, properties);
		paginate(pageSize, pageNumber, typedQuery);
		parametrize(properties, typedQuery);
		return typedQuery.getResultList();
	}

	@Override
	public <T extends AbstractEntity> T selectByTypedQuery(Class<T> clazz,
			String queryName, Map<String, Object> parameters) {
		logger.log(Level.FINE, "Executing named query: {0}", queryName);
		TypedQuery<T> query = em.createNamedQuery(queryName, clazz);
		parametrize(parameters, query);
		return query.getSingleResult();
	}

	@Override
	public <T extends AbstractEntity> List<T> selectByTypedQuery(
			Class<T> clazz, String queryName, Map<String, Object> parameters,
			int pageNumber, int pageSize) {
		logger.log(Level.FINE, "Executing named query: {0}", queryName);
		TypedQuery<T> query = em.createNamedQuery(queryName, clazz);
		paginate(pageSize, pageNumber, query);
		parametrize(parameters, query);
		return query.getResultList();
	}

	@Override
	public <T extends AbstractEntity> T update(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		if (entity.getId() != null || entity.getId().equals(0)) {
			throw new IllegalStateException("Entity must have an assigned id");
		}
		Object[] logIds = new Object[] { entity.getClass().getCanonicalName(),
				entity.getId() };
		logger.log(Level.FINE, "Update entity::Clase={0}::Id={1}", logIds);
		T ret = null;
		if (!em.contains(entity)) {
			ret = em.merge(entity);
		} else {
			ret = entity;
		}
		return ret;
	}
	
	private <T extends AbstractEntity> TypedQuery<T> createQueryFromCriteria(
			Class<T> clazz, Map<String, Object> properties) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		CriteriaQuery<T> criteriaQuery = cq.select(root);

		if (properties != null && !properties.isEmpty()) {
			Set<String> keys = properties.keySet();
			Predicate condition = null;
			for (String key : keys) {
				if (condition == null) {
					condition = cb.equal(root.get(key), properties.get(key));
				} else {
					condition = cb.and(condition);
				}
			}
			criteriaQuery.where(condition);
		}

		TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
		return typedQuery;
	}

	private void parametrize(Map<String, Object> parameters, Query query) {
		if (parameters != null) {
			for (String key : parameters.keySet()) {
				Object parameter = parameters.get(key);
				logger.log(Level.FINEST, "key={0}::parameter={1}",
						new Object[] { key, parameter });
				query.setParameter(key, parameter);
			}
		}
	}

	private void paginate(int pageSize, int pageNumber, Query query) {
		if (pageNumber < 0) {
			throw new IllegalArgumentException("pageNum: " + pageNumber);
		}

		if (pageSize < 0) {
			throw new IllegalArgumentException("pageSize: " + pageSize);
		}

		logger.log(Level.FINEST, "Page {0}", pageNumber);
		query.setFirstResult(pageNumber * pageSize);
		if (pageSize > 0) {
			logger.log(Level.FINEST, "Page size {0}", pageSize);
			query.setMaxResults(pageSize);
		} else if (pageSize == 0) {
			logger.log(Level.WARNING, "Query result is limited to {0} results",
					MAX_RESULTS);
			query.setMaxResults(MAX_RESULTS);
		}
	}
}
