package dev.flaviojunior.library.author;

import java.io.Serializable;
import java.util.Objects;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

public class AuthorCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter firstName;

	private StringFilter lastName;

	private LongFilter bookId;

	public AuthorCriteria() {
	}

	public AuthorCriteria(AuthorCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.firstName = other.firstName == null ? null : other.firstName.copy();
		this.lastName = other.lastName == null ? null : other.lastName.copy();
		this.bookId = other.bookId == null ? null : other.bookId.copy();
	}

	@Override
	public AuthorCriteria copy() {
		return new AuthorCriteria(this);
	}

	public LongFilter getId() {
		return id;
	}

	public LongFilter id() {
		if (id == null) {
			id = new LongFilter();
		}
		return id;
	}

	public void setId(LongFilter id) {
		this.id = id;
	}

	public StringFilter getFirstName() {
		return firstName;
	}

	public StringFilter firstName() {
		if (firstName == null) {
			firstName = new StringFilter();
		}
		return firstName;
	}

	public void setFirstName(StringFilter firstName) {
		this.firstName = firstName;
	}

	public StringFilter getLastName() {
		return lastName;
	}

	public StringFilter lastName() {
		if (lastName == null) {
			lastName = new StringFilter();
		}
		return lastName;
	}

	public void setLastName(StringFilter lastName) {
		this.lastName = lastName;
	}

	public LongFilter getBookId() {
		return bookId;
	}

	public LongFilter bookId() {
		if (bookId == null) {
			bookId = new LongFilter();
		}
		return bookId;
	}

	public void setBookId(LongFilter bookId) {
		this.bookId = bookId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final AuthorCriteria that = (AuthorCriteria) o;
		return (Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName)
				&& Objects.equals(lastName, that.lastName) && Objects.equals(bookId, that.bookId));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, firstName, lastName, bookId);
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "AuthorCriteria{" + (id != null ? "id=" + id + ", " : "")
				+ (firstName != null ? "firstName=" + firstName + ", " : "")
				+ (lastName != null ? "lastName=" + lastName + ", " : "")
				+ (bookId != null ? "bookId=" + bookId + ", " : "") + "}";
	}
}
