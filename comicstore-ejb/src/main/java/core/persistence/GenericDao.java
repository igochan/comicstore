package core.persistence;

import java.util.List;
import java.util.Map;

public interface GenericDao {

	public <T extends AbstractEntity> void insert(T entity);

	public <T extends AbstractEntity> T update(T entity);

	public <T extends AbstractEntity> void delete(T entity);

	public <T extends AbstractEntity> T select(Class<T> clazz, Long id);

	public <T extends AbstractEntity> List<T> selectAll(Class<T> clazz);

	public <T extends AbstractEntity> List<T> selectByTypedQuery(
			Class<T> clazz, String queryName, Map<String, Object> parameters,
			int pageNumber, int pageSize);

	public <T extends AbstractEntity> T selectByTypedQuery(Class<T> clazz,
			String queryName, Map<String, Object> parameters);

	public <T extends AbstractEntity> List<T> selectByProperties(
			Class<T> clazz, Map<String, Object> properties, int pageSize,
			int pageNumber);

	public <T extends AbstractEntity> T selectByProperties(Class<T> clazz,
			Map<String, Object> properties);
}
