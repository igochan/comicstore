package comicstore.comic;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comicstore.comic.entity.Issue;

@Named
@ViewScoped
public class AddIssueBean implements Serializable{

	private static final long serialVersionUID = 2023061005130572614L;

	@Inject
	private Logger logger;

	@Inject
	private ComicService comicService;

	private Issue issue;

	@PostConstruct
	public void init() {
		issue = new Issue();
	}
	
	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public String save() {
		logger.fine("Saving issue");
		comicService.insert(issue);
		return "index";
	}
}
