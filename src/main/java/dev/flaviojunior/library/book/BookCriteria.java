package dev.flaviojunior.library.book;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LocalDateFilter;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class BookCriteria implements Serializable, Criteria {

	private static final long serialVersionUID = 1L;

	private LongFilter id;

	private StringFilter title;

	private StringFilter isbn;

	private StringFilter imagePath;

	private StringFilter publisher;

	private LocalDateFilter dateOfPublication;

	private LongFilter authorId;

	private LongFilter categoryId;

	public BookCriteria() {
	}

	public BookCriteria(BookCriteria other) {
		this.id = other.id == null ? null : other.id.copy();
		this.title = other.title == null ? null : other.title.copy();
		this.isbn = other.isbn == null ? null : other.isbn.copy();
		this.imagePath = other.imagePath == null ? null : other.imagePath.copy();
		this.publisher = other.publisher == null ? null : other.publisher.copy();
		this.dateOfPublication = other.dateOfPublication == null ? null : other.dateOfPublication.copy();
		this.authorId = other.authorId == null ? null : other.authorId.copy();
		this.categoryId = other.categoryId == null ? null : other.categoryId.copy();
	}

	@Override
	public BookCriteria copy() {
		return new BookCriteria(this);
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

	public StringFilter getTitle() {
		return title;
	}

	public StringFilter title() {
		if (title == null) {
			title = new StringFilter();
		}
		return title;
	}

	public void setTitle(StringFilter title) {
		this.title = title;
	}

	public StringFilter getIsbn() {
		return isbn;
	}

	public StringFilter isbn() {
		if (isbn == null) {
			isbn = new StringFilter();
		}
		return isbn;
	}

	public void setIsbn(StringFilter isbn) {
		this.isbn = isbn;
	}

	public StringFilter getImagePath() {
		return imagePath;
	}

	public StringFilter imagePath() {
		if (imagePath == null) {
			imagePath = new StringFilter();
		}
		return imagePath;
	}

	public void setImagePath(StringFilter imagePath) {
		this.imagePath = imagePath;
	}

	public StringFilter getPublisher() {
		return publisher;
	}

	public StringFilter publisher() {
		if (publisher == null) {
			publisher = new StringFilter();
		}
		return publisher;
	}

	public void setPublisher(StringFilter publisher) {
		this.publisher = publisher;
	}

	public LocalDateFilter getDateOfPublication() {
		return dateOfPublication;
	}

	public LocalDateFilter dateOfPublication() {
		if (dateOfPublication == null) {
			dateOfPublication = new LocalDateFilter();
		}
		return dateOfPublication;
	}

	public void setDateOfPublication(LocalDateFilter dateOfPublication) {
		this.dateOfPublication = dateOfPublication;
	}

	public LongFilter getAuthorId() {
		return authorId;
	}

	public LongFilter authorId() {
		if (authorId == null) {
			authorId = new LongFilter();
		}
		return authorId;
	}

	public void setAuthorId(LongFilter authorId) {
		this.authorId = authorId;
	}

	public LongFilter getCategoryId() {
		return categoryId;
	}

	public LongFilter categoryId() {
		if (categoryId == null) {
			categoryId = new LongFilter();
		}
		return categoryId;
	}

	public void setCategoryId(LongFilter categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final BookCriteria that = (BookCriteria) o;
		return (Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(isbn, that.isbn)
				&& Objects.equals(imagePath, that.imagePath) && Objects.equals(publisher, that.publisher)
				&& Objects.equals(dateOfPublication, that.dateOfPublication) && Objects.equals(authorId, that.authorId)
				&& Objects.equals(categoryId, that.categoryId));
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, isbn, imagePath, publisher, dateOfPublication, authorId, categoryId);
	}

	// prettier-ignore
	@Override
	public String toString() {
		return "BookCriteria{" + (id != null ? "id=" + id + ", " : "") + (title != null ? "title=" + title + ", " : "")
				+ (isbn != null ? "isbn=" + isbn + ", " : "")
				+ (imagePath != null ? "imagePath=" + imagePath + ", " : "")
				+ (publisher != null ? "publisher=" + publisher + ", " : "")
				+ (dateOfPublication != null ? "dateOfPublication=" + dateOfPublication + ", " : "")
				+ (authorId != null ? "authorId=" + authorId + ", " : "")
				+ (categoryId != null ? "categoryId=" + categoryId + ", " : "") + "}";
	}
}
