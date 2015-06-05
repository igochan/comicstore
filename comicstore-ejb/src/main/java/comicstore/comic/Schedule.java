package comicstore.comic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.AbstractEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Schedule extends AbstractEntity {

	private static final long serialVersionUID = 5457903503616144111L;

	@Column(length = 64, nullable = false)
	@NotNull
	private String name;

	@Column(length = 64)
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Schedule scheduleToCompare = (Schedule) obj;
		if (name == null) {
			if (scheduleToCompare.name != null) {
				return false;
			}
		} else if (!name.equals(scheduleToCompare.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Schedule [name=" + name + "]";
	}
}
