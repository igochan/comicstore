package comicstore.comic;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comicstore.comic.entity.Comic;
import comicstore.comic.entity.Issue;

@Named
@ViewScoped
public class EditComicBean implements Serializable {

	private static final long serialVersionUID = -902008747973025948L;

	@Inject
	private Logger logger;

	@Inject
	private ComicService comicService;

	private Comic comic;

	private Issue issue;

	@PostConstruct
	public void init() {
		logger.fine("Saving comic");
		comic = new Comic();
		issue = new Issue();
		issue.setComic(comic);
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Comic getComic() {
		return comic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public String save() {
		logger.fine("Saving comic");
		comicService.insert(comic);
		return "index";
	}

	public void newIssue() {
	}
}
