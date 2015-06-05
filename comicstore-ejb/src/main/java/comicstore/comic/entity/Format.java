package comicstore.comic.entity;

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
public class Format extends AbstractEntity {

	private static final long serialVersionUID = -7236593832224048904L;

	@Column(length=64,nullable=false)
	@NotNull
	private String name;

	@Column(length=64)
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
		Format formatToCompare = (Format) obj;
		if (name == null) {
			if (formatToCompare.name != null) {
				return false;
			}
		} else if (!name.equals(formatToCompare.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Format [name=" + name + "]";
	}
}
