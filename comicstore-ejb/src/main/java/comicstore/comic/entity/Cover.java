package comicstore.comic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Embeddable
public class Cover {

	@Column(length = 64, nullable = false)
	@NotNull
	private String coverImageFileName;

	@ManyToMany
	private List<Author> coverInkers;

	@ManyToMany
	private List<Author> coverPencillers;

	@ManyToMany
	private List<Author> coverColorists;

	public void addAuthor(Author author, Author.Type type) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (type.equals(Author.Type.COVER_INKER)) {
			addCoverInker(author);
		} else if (type.equals(Author.Type.COVER_PENCILLER)) {
			addCoverPenciller(author);
		} else if (type.equals(Author.Type.COVER_COLORIST)) {
			addCoverColorist(author);
		} else {
			throw new IllegalArgumentException("Author not allowed: " + type);
		}
	}

	/**
	 * @NotThreadSafe
	 */
	public void addCoverColorist(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (coverColorists == null) {
			coverColorists = new ArrayList<>();
		}
		coverColorists.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addCoverInker(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (coverInkers == null) {
			coverInkers = new ArrayList<>();
		}
		coverInkers.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addCoverPenciller(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (coverPencillers == null) {
			coverPencillers = new ArrayList<>();
		}
		coverPencillers.add(author);
	}

	public List<Author> getCoverColorists() {
		return coverColorists;
	}

	public String getCoverImageFileName() {
		return coverImageFileName;
	}

	public List<Author> getCoverInkers() {
		return coverInkers;
	}

	public List<Author> getCoverPencillers() {
		return coverPencillers;
	}

	public void setCoverColorists(List<Author> coverColorists) {
		this.coverColorists = coverColorists;
	}

	public void setCoverImageFileName(String coverImageFileName) {
		this.coverImageFileName = coverImageFileName;
	}

	public void setCoverInkers(List<Author> coverInkers) {
		this.coverInkers = coverInkers;
	}

	public void setCoverPencillers(List<Author> coverPencillers) {
		this.coverPencillers = coverPencillers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((coverImageFileName == null) ? 0 : coverImageFileName
						.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Cover coverToCompare = (Cover) obj;
		if (coverImageFileName == null) {
			if (coverToCompare.coverImageFileName != null) {
				return false;
			}
		} else if (!coverImageFileName.equals(coverToCompare.coverImageFileName)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Cover [coverImageFileName=" + coverImageFileName + "]";
	}
}
