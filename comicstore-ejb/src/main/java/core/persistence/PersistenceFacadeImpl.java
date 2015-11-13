package core.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Persistence
public class PersistenceFacadeImpl implements PersistenceFacade {

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
		Object[] logIds = new Object[] { entity.getClass().getCanonicalName(), entity.getId() };
		logger.log(Level.FINE, "Delete entity::Clase={0}::Id={1}", logIds);
		if (em.contains(entity)) {
			em.remove(entity);
		} else {
			logger.log(Level.FINEST, "Detached entity");
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
		logger.log(Level.FINE, "Insert entity::Class={0}", entity.getClass().getCanonicalName());
		em.persist(entity);
		logger.log(Level.FINE, "The entity has the id={0}", entity.getId());
	}

	@Override
	public <T extends AbstractEntity> T selectBytId(Class<T> clazz, Long id) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		if (id == null || id.equals(0)) {
			throw new IllegalArgumentException("Id must not be 0");
		}
		logger.log(Level.FINE, "Select entity {0} by id={1}", new Object[] { clazz, id });
		T result = null;
		try {
			result = em.find(clazz, id);
		} catch (NoResultException ex) {
			result = null;
		}
		return result;
	}

	@Override
	public <T extends AbstractEntity> List<T> selectAll(Class<T> clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Select all {0}", clazz);
		return selectByProperties(clazz, null, 0, MAX_RESULTS);
	}

	@Override
	public <T extends AbstractEntity> T selectByProperties(Class<T> clazz, Map<String, Object> properties) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Select by properties {0}", clazz);
		TypedQuery<T> typedQuery = createQueryFromCriteria(clazz, properties);
		return buildTypedQuerySingleResult(properties, typedQuery);
	}

	@Override
	public <T extends AbstractEntity> List<T> selectByProperties(Class<T> clazz, Map<String, Object> properties,
			int firstResult, int maxResult) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Select by properties {0}", clazz);
		TypedQuery<T> typedQuery = createQueryFromCriteria(clazz, properties);
		return buildTypedQuery(properties, firstResult, maxResult, typedQuery);
	}

	@Override
	public <T extends AbstractEntity> T selectByTypedQuery(Class<T> clazz, String queryName,
			Map<String, Object> parameters) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		if (queryName == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Executing named query: {0}", queryName);
		TypedQuery<T> typedQuery = em.createNamedQuery(queryName, clazz);
		return buildTypedQuerySingleResult(parameters, typedQuery);
	}

	@Override
	public <T extends AbstractEntity> List<T> selectByTypedQuery(Class<T> clazz, String queryName,
			Map<String, Object> parameters, int firstResult, int maxResult) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		if (queryName == null) {
			throw new NullPointerException();
		}
		logger.log(Level.FINE, "Executing named query: {0}", queryName);
		TypedQuery<T> typedQuery = em.createNamedQuery(queryName, clazz);
		return buildTypedQuery(parameters, firstResult, maxResult, typedQuery);
	}

	@Override
	public <T extends AbstractEntity> T update(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		if (entity.getId() != null || entity.getId().equals(0)) {
			throw new IllegalStateException("Entity must have an assigned id");
		}
		Object[] logIds = new Object[] { entity.getClass().getCanonicalName(), entity.getId() };
		logger.log(Level.FINE, "Update entity::Clase={0}::Id={1}", logIds);
		T ret = null;
		if (!em.contains(entity)) {
			ret = em.merge(entity);
		} else {
			ret = entity;
		}
		return ret;
	}

	private <T extends AbstractEntity> List<T> buildTypedQuery(Map<String, Object> parameters, int firstResult,
			int maxResult, TypedQuery<T> typedQuery) {
		paginate(firstResult, maxResult, typedQuery);
		parametrize(parameters, typedQuery);
		List<T> result = null;
		try {
			result = typedQuery.getResultList();
		} catch (NoResultException ex) {
			logger.log(Level.WARNING, "No result found");
			result = new ArrayList<T>();
		}
		return result;
	}

	private <T extends AbstractEntity> T buildTypedQuerySingleResult(Map<String, Object> parameters,
			TypedQuery<T> query) {
		parametrize(parameters, query);
		T result = null;
		try {
			result = query.getSingleResult();
		} catch (NoResultException ex) {
			logger.log(Level.WARNING, "No result found");
			result = null;
		}
		return result;
	}

	private <T extends AbstractEntity> TypedQuery<T> createQueryFromCriteria(Class<T> clazz,
			Map<String, Object> properties) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> root = cq.from(clazz);
		CriteriaQuery<T> criteriaQuery = cq.select(root);
		if (properties != null && !properties.isEmpty()) {
			Set<String> keys = properties.keySet();
			Predicate condition = null;
			for (String key : keys) {
				if (condition == null) {
					condition = cb.equal(root.get(key), cb.parameter(String.class, "name"));
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
				logger.log(Level.FINEST, "key={0}::parameter={1}", new Object[] { key, parameter });
				query.setParameter(key, parameter);
			}
		}
	}

	private void paginate(int firstResult, int maxResult, Query query) {
		if (firstResult < 0) {
			throw new IllegalArgumentException("firstResult: " + firstResult);
		}

		if (maxResult < 0) {
			throw new IllegalArgumentException("maxResult: " + maxResult);
		}

		logger.log(Level.FINEST, "First result {0}", firstResult);
		query.setFirstResult(firstResult);
		if (maxResult > 0) {
			logger.log(Level.FINEST, "Max result {0}", maxResult);
			query.setMaxResults(maxResult);
		} else if (maxResult == 0) {
			logger.log(Level.WARNING, "Query result is limited to {0} results", MAX_RESULTS);
			query.setMaxResults(MAX_RESULTS);
		}
	}
}
