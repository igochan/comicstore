package comicstore.comic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;

import comicstore.comic.entity.Comic;
import comicstore.comic.entity.Issue;
import core.service.GenericServiceImpl;

@Stateless
public class ComicServiceImpl extends GenericServiceImpl implements
		ComicService {

	@Override
	@TransactionAttribute()
	public List<Issue> selectAllIssues(Comic comic) {
		List<Issue> issues = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("comic", comic);
			issues = genericDao.selectByTypedQuery(Issue.class,
					"Issue.selectAllByComic", parameters, 0, 0);
		} catch (NoResultException e) {
			logger.log(Level.FINE, "No result found");
		}
		return issues;
	}

	@Override
	public Comic selectComicByName(String name) {
		Comic comic = null;
		try {
			Map<String, Object> properties = new HashMap<String, Object>();
			properties.put("name", name);
			comic = genericDao.selectByProperties(Comic.class, properties);
		} catch (NoResultException e) {
			logger.log(Level.FINE, "No result found");
		}
		return comic;
	}

}
