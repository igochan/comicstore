package comicstore.comic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.AuditedEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "volume" }))
public class Comic extends AuditedEntity {

	private static final long serialVersionUID = 7750637931724949574L;

	@ManyToOne
	@JoinColumn(name = "formatId")
	private Format format;

	@ManyToOne
	@JoinColumn(name = "genreId")
	private Genre genre;

	@OneToMany(mappedBy = "comic", orphanRemoval = true)
	@OrderColumn(name = "number")
	private List<Issue> issues;

	@Column(nullable = false, length = 256)
	@NotNull
	private String name;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "publisherId")
	private Publisher publisher;

	@ManyToOne
	@JoinColumn(name = "scheudleId")
	private Schedule schedule;

	@Min(value = 1)
	@Max(value = 9)
	@Column(precision = 1)
	private Integer volume = 1;

	public Format getFormat() {
		return format;
	}

	public Genre getGenre() {
		return genre;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public String getName() {
		return name;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setFormat(Format format) {
		this.format = format;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Comic)) {
			return false;
		}
		Comic comicToCompare = (Comic) obj;
		if (name == null) {
			if (comicToCompare.name != null) {
				return false;
			}
		} else if (!name.equals(comicToCompare.name)) {
			return false;
		}
		if (volume == null) {
			if (comicToCompare.volume != null) {
				return false;
			}
		}
		else if (volume != comicToCompare.volume) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		if (volume != null) {
			result = prime * result + volume;
		}
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name);
		if( volume != null ){
			sb.append(", vol ").append(volume);
		}
		
		return sb.toString();
	}
}
