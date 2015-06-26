package comicstore.comic;

import java.util.List;

import comicstore.comic.entity.Comic;
import comicstore.comic.entity.Issue;
import core.service.GenericService;

public interface ComicService extends GenericService{
	
	public List<Issue> selectAllIssues(Comic comic);
	
	public Comic selectComicByName(String name);

}
