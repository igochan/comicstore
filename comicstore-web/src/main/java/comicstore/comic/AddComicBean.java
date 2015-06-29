package comicstore.comic;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import comicstore.comic.entity.Comic;
import comicstore.comic.entity.Cover;
import comicstore.comic.entity.Issue;
import comicstore.comic.entity.Publisher;
import core.util.ConfigurationProperty;

@Named
@ViewScoped
public class AddComicBean implements Serializable {

	private static final long serialVersionUID = 7019768335768980897L;

	@Inject
	private ComicService comicService;

	@Inject
	private Logger logger;

	// ////////////////////////////////////////////////////////////////////////
	// VARS
	// ////////////////////////////////////////////////////////////////////////

	@Inject
	@ConfigurationProperty(value = "cover_image.directory")
	private String imageDirectory;

	private boolean advancedView = false;

	private Comic comic;

	private String comicName;

	private Issue issue;

	private List<Issue> issues;

	private boolean newComic = false;

	private Part part;

	// ////////////////////////////////////////////////////////////////////////
	// INIT
	// ////////////////////////////////////////////////////////////////////////

	@PostConstruct
	public void init() {
		issue = new Issue();
		issue.setCover(new Cover());
	}

	// ////////////////////////////////////////////////////////////////////////
	// GET AND SET
	// ////////////////////////////////////////////////////////////////////////

	public Comic getComic() {
		return comic;
	}

	public String getComicName() {
		return comicName;
	}

	public Issue getIssue() {
		return issue;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public Part getPart() {
		return part;
	}

	public boolean isAdvancedView() {
		return advancedView;
	}

	public boolean isNewComic() {
		return newComic;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public void setComicName(String comicName) {
		this.comicName = comicName;
	}

	public void setComicService(ComicService comicService) {
		this.comicService = comicService;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public void setPart(Part part) {
		this.part = part;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ACTIONS
	// ////////////////////////////////////////////////////////////////////////

	public void advanced() {
		advancedView = !advancedView;
	}

	public void save() {

	}

	public void searchComic() {
		logger.log(Level.FINE, "Searching comic with name {0}", comicName);
		comic = comicService.selectComicByName(comicName);
		if (comic == null) {
			newComic = true;
			comic = buildComic(comicName);
		}
	}

	public void upload() {
		String fileName = getFileName(part);
		try (InputStream input = part.getInputStream()) {
			Files.copy(input, new File(imageDirectory, fileName).toPath());
		} catch (IOException ex) {
			// TODO: faces message
		}
		issue.getCover().setCoverImageFileName(fileName);
	}

	// Extract part name from content-disposition header of part part
	private String getFileName(Part part) {
		final String partHeader = part.getHeader("content-disposition");
		logger.log(Level.FINE, "partHeader: {0}", partHeader);
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "").toLowerCase();
			}
		}
		// TODO: throw Exception!
		return null;
	}

	private Comic buildComic(String comicName) {
		Comic comic = new Comic();
		comic.setName(comicName);
		Publisher publisher = new Publisher();
		comic.setPublisher(publisher);
		return comic;
	}

}
