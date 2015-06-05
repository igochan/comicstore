package comicstore.comic;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import core.persistence.AuditedEntity;

@Entity
@XmlRootElement
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name",
		"surename", "ordinal" }))
public class Author extends AuditedEntity {

	private static final long serialVersionUID = -7680745209538238324L;

	public static enum Type {
		COVER_INKER, COVER_PENCILLER, COVER_COLORIST, INKER, PENCILLER, COLORIST, WRITER, LETTERER
	};

	@NotNull
	@Column(nullable = false, length = 32)
	private String name;

	@NotNull
	@Column(nullable = false, length = 64)
	private String surename;

	// Convenient to avoid duplicates
	@Column(precision = 1)
	@Min(value = 1)
	@Max(value = 9)
	private Integer ordinal;

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getName() {
		return name;
	}

	public String getSurename() {
		return surename;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurename(String surename) {
		this.surename = surename;
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
		Author authorToCompare = (Author) obj;
		if (name == null) {
			if (authorToCompare.name != null) {
				return false;
			}
		} else if (!name.equals(authorToCompare.name)) {
			return false;
		}
		if (surename == null) {
			if (authorToCompare.surename != null) {
				return false;
			}
		} else if (!surename.equals(authorToCompare.surename)) {
			return false;
		}
		if (ordinal == null) {
			if (authorToCompare.ordinal != null) {
				return false;
			}
		} else if (!ordinal.equals(authorToCompare.ordinal)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((surename == null) ? 0 : surename.hashCode());
		result = prime * result + ((ordinal == null) ? 0 : ordinal.hashCode());
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name).append(" ").append(surename);
		if (ordinal != null) {
			sb.append(" ").append(ordinal);
		}
		return sb.toString();
	}
}
