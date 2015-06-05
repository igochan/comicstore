package core.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import core.persistence.AbstractEntity;
import core.persistence.GenericDao;

@Stateless
public class GenericServiceImpl implements GenericService {

	@Inject
	protected GenericDao genericDao;

	@Override
	public <T extends AbstractEntity> void insert(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		genericDao.insert(entity);
	}

	@Override
	public <T extends AbstractEntity> T update(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		return genericDao.update(entity);
	}

	@Override
	public <T extends AbstractEntity> void delete(T entity) {
		if (entity == null) {
			throw new NullPointerException();
		}
		genericDao.delete(entity);
	}

	@Override
	public <T extends AbstractEntity> T select(Class<T> clazz, Long id) {
		if (clazz == null || id == null) {
			throw new NullPointerException();
		}
		return genericDao.select(clazz, id);
	}

	@Override
	public <T extends AbstractEntity> List<T> selectAll(Class<T> clazz) {
		if (clazz == null) {
			throw new NullPointerException();
		}
		return genericDao.selectAll(clazz);
	}
}
