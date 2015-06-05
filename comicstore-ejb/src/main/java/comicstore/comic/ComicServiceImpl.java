package comicstore.comic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;

import comicstore.comic.entity.Comic;
import comicstore.comic.entity.Issue;

import core.service.GenericServiceImpl;

@Stateless
public class ComicServiceImpl extends GenericServiceImpl implements
		ComicService {

	@Override
	public List<Issue> selectAllIssues(Comic comic) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("comic", comic);
		return genericDao.selectByTypedQuery(Issue.class,
				"Issue.selectAllByComic", parameters, 0, 0);
	}
}
