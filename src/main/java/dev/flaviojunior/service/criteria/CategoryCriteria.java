package dev.flaviojunior.service.criteria;

import dev.flaviojunior.common.service.Criteria;
import dev.flaviojunior.common.service.filter.LongFilter;
import dev.flaviojunior.common.service.filter.StringFilter;

import java.io.Serializable;
import java.util.Objects;

public class CategoryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private LongFilter bookId;

    public CategoryCriteria() {
    }

    public CategoryCriteria(CategoryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.bookId = other.bookId == null ? null : other.bookId.copy();
    }

    @Override
    public CategoryCriteria copy() {
        return new CategoryCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter name() {
        if (name == null) {
            name = new StringFilter();
        }
        return name;
    }

    public LongFilter getBookId() {
        return bookId;
    }

    public void setBookId(LongFilter bookId) {
        this.bookId = bookId;
    }

    public LongFilter bookId() {
        if (bookId == null) {
            bookId = new LongFilter();
        }
        return bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CategoryCriteria that = (CategoryCriteria) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bookId);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (bookId != null ? "bookId=" + bookId + ", " : "") +
                "}";
    }
}
