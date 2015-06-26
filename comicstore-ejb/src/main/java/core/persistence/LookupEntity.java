package core.persistence;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
public abstract class LookupEntity extends AbstractEntity {

	private static final long serialVersionUID = 8276454593951062121L;

	@Column(length = 64, nullable = false)
	@NotNull
	protected String name;

	@Column(length = 64)
	protected String description;

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
		LookupEntity entityToCompare = (LookupEntity) obj;
		if (name == null) {
			if (entityToCompare.name != null) {
				return false;
			}
		} else if (!name.equals(entityToCompare.name)) {
			return false;
		}
		return true;
	}

}
