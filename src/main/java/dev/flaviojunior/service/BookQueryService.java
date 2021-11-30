package dev.flaviojunior.service;

import dev.flaviojunior.common.service.QueryService;
import dev.flaviojunior.domain.Author_;
import dev.flaviojunior.domain.Book;
import dev.flaviojunior.domain.Book_;
import dev.flaviojunior.domain.Category_;
import dev.flaviojunior.repository.BookRepository;
import dev.flaviojunior.service.criteria.BookCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookQueryService extends QueryService<Book> {

    private final Logger log = LoggerFactory.getLogger(BookQueryService.class);

    private final BookRepository bookRepository;

    public BookQueryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findByCriteria(BookCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Book> specification = createSpecification(criteria);
        return bookRepository.findAll(specification);
    }

    @Transactional(readOnly = true)
    public Page<Book> findByCriteria(BookCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Book> specification = createSpecification(criteria);
        return bookRepository.findAll(specification, page);
    }

    @Transactional(readOnly = true)
    public long countByCriteria(BookCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Book> specification = createSpecification(criteria);
        return bookRepository.count(specification);
    }

    protected Specification<Book> createSpecification(BookCriteria criteria) {
        Specification<Book> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Book_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Book_.title));
            }
            if (criteria.getIsbn() != null) {
                specification = specification.and(buildStringSpecification(criteria.getIsbn(), Book_.isbn));
            }
            if (criteria.getImagePath() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImagePath(), Book_.imagePath));
            }
            if (criteria.getPublisher() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPublisher(), Book_.publisher));
            }
            if (criteria.getDateOfPublication() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateOfPublication(), Book_.dateOfPublication));
            }
            if (criteria.getAuthorId() != null) {
                specification =
                        specification.and(
                                buildSpecification(criteria.getAuthorId(), root -> root.join(Book_.authors, JoinType.LEFT).get(Author_.id))
                        );
            }
            if (criteria.getCategoryId() != null) {
                specification =
                        specification.and(
                                buildSpecification(criteria.getCategoryId(), root -> root.join(Book_.categories, JoinType.LEFT).get(Category_.id))
                        );
            }
        }
        return specification;
    }
}
