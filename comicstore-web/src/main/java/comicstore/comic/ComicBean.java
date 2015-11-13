package comicstore.comic;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comicstore.comic.entity.Issue;

@Named
@ViewScoped
public class ComicBean implements Serializable {

	private static final long serialVersionUID = -5201549822438554470L;

	@Inject
	private Logger logger;

	@Inject
	private ComicService comicService;

	private List<Issue> lastIssues;

	@PostConstruct
	public void init() {
		logger.fine("IndexBean init");
		lastIssues = comicService.selectLastIssues();
	}

	public List<Issue> getLastIssues() {
		return lastIssues;
	}

	public void setLastIssues(List<Issue> lastIssues) {
		this.lastIssues = lastIssues;
	}
	
	public String addIssue() {
		return "editComic?faces-redirect=true";
	}
}
