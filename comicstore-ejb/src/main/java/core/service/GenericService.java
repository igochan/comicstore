package core.service;

import java.util.List;

import core.persistence.AbstractEntity;

public interface GenericService {

	public <T extends AbstractEntity> void insert(T entity);

	public <T extends AbstractEntity> T update(T entity);

	public <T extends AbstractEntity> void delete(T entity);

	public <T extends AbstractEntity> T select(Class<T> clazz, Long id);

	public <T extends AbstractEntity> List<T> selectAll(Class<T> clazz);

}
