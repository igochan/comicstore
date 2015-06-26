package comicstore.comic.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.AuditedEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = {
		@UniqueConstraint(columnNames = { "number", "title", "edition" }),
		@UniqueConstraint(columnNames = "coverImageFileName") })
public class Issue extends AuditedEntity {

	private static final long serialVersionUID = 6160624685557191927L;

	@Embedded
	private Cover cover;

	@ManyToOne(optional = false)
	@JoinColumn(name = "comicId", nullable = false)
	@NotNull
	private Comic comic;

	@Column(precision = 2)
	private BigDecimal coverPrice;

	@Min(value = 1)
	private Integer edition = 1;
	
	@Min(value = 1)
	private Integer number = 1;

	@Temporal(TemporalType.DATE)
	private Date published;

	@Column(length = 128)
	private String title;

	@ManyToMany
	private List<Author> colorists;

	@ManyToMany
	private List<Author> inkers;

	@ManyToMany
	private List<Author> letterers;

	@ManyToMany
	private List<Author> pencillers;

	@ManyToMany
	private List<Author> writers;

	public void addAuthor(Author author, Author.Type type) {
		if (author == null) {
			throw new NullPointerException();
		}

		if (type.equals(Author.Type.INKER)) {
			addInker(author);
		} else if (type.equals(Author.Type.WRITER)) {
			addWriter(author);
		} else if (type.equals(Author.Type.COLORIST)) {
			addColorist(author);
		} else if (type.equals(Author.Type.LETTERER)) {
			addLetterer(author);
		} else if (type.equals(Author.Type.PENCILLER)) {
			addPenciller(author);
		} else {
			throw new IllegalArgumentException("Author not allowed: " + type);
		}
	}

	/**
	 * @NotThreadSafe
	 */
	public void addColorist(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (this.colorists == null) {
			colorists = new ArrayList<>();
		}
		colorists.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addInker(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (this.inkers == null) {
			inkers = new ArrayList<>();
		}
		inkers.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addLetterer(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (this.letterers == null) {
			letterers = new ArrayList<>();
		}
		letterers.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addPenciller(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (this.pencillers == null) {
			pencillers = new ArrayList<>();
		}
		pencillers.add(author);
	}

	/**
	 * @NotThreadSafe
	 */
	public void addWriter(Author author) {
		if (author == null) {
			throw new NullPointerException();
		}
		if (this.writers == null) {
			writers = new ArrayList<>();
		}
		writers.add(author);
	}

	public List<Author> getColorists() {
		return colorists;
	}

	public Comic getComic() {
		return comic;
	}

	public Cover getCover() {
		return cover;
	}

	public BigDecimal getCoverPrice() {
		return coverPrice;
	}

	public int getEdition() {
		return edition;
	}

	public List<Author> getInkers() {
		return inkers;
	}

	public int getNumber() {
		return number;
	}

	public List<Author> getPencillers() {
		return pencillers;
	}

	public Date getPublished() {
		return published;
	}

	public String getTitle() {
		return title;
	}

	public List<Author> getWriters() {
		return writers;
	}

	public void setColorists(List<Author> colorists) {
		this.colorists = colorists;
	}

	public void setComic(Comic comic) {
		this.comic = comic;
	}

	public void setCover(Cover cover) {
		this.cover = cover;
	}

	public void setCoverPrice(BigDecimal coverPrice) {
		this.coverPrice = coverPrice;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public void setInkers(List<Author> inkers) {
		this.inkers = inkers;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPencillers(List<Author> pencillers) {
		this.pencillers = pencillers;
	}

	public void setPublished(Date published) {
		this.published = published;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setWriters(List<Author> writers) {
		this.writers = writers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comic == null) ? 0 : comic.hashCode());
		result = prime * result + ((edition == null) ? 0 : edition.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Issue other = (Issue) obj;
		if (comic == null) {
			if (other.comic != null) {
				return false;
			}
		} else if (!comic.equals(other.comic)) {
			return false;
		}
		if (edition == null) {
			if (other.edition != null) {
				return false;
			}
		} else if (!edition.equals(other.edition)) {
			return false;
		}
		if (number == null) {
			if (other.number != null) {
				return false;
			}
		} else if (!number.equals(other.number)) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(comic.toString()).append(" ")
				.append(number).append("-").append(title);
		if (edition != null) {
			sb.append(", ").append(edition).append(" edition");
		}
		return sb.toString();
	}
}
