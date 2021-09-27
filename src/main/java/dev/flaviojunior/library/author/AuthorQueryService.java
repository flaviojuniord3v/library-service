package dev.flaviojunior.library.author;

import java.util.List;

import javax.persistence.criteria.JoinType;

import dev.flaviojunior.domain.Author_;
import dev.flaviojunior.domain.Book_;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.flaviojunior.common.service.QueryService;
// for static metamodels
import dev.flaviojunior.domain.Author;

@Service
@Transactional(readOnly = true)
public class AuthorQueryService extends QueryService<Author> {

	private final Logger log = LoggerFactory.getLogger(AuthorQueryService.class);

	private final AuthorRepository authorRepository;

	public AuthorQueryService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Transactional(readOnly = true)
	public List<Author> findByCriteria(AuthorCriteria criteria) {
		log.debug("find by criteria : {}", criteria);
		final Specification<Author> specification = createSpecification(criteria);
		return authorRepository.findAll(specification);
	}

	@Transactional(readOnly = true)
	public Page<Author> findByCriteria(AuthorCriteria criteria, Pageable page) {
		log.debug("find by criteria : {}, page: {}", criteria, page);
		final Specification<Author> specification = createSpecification(criteria);
		return authorRepository.findAll(specification, page);
	}

	@Transactional(readOnly = true)
	public long countByCriteria(AuthorCriteria criteria) {
		log.debug("count by criteria : {}", criteria);
		final Specification<Author> specification = createSpecification(criteria);
		return authorRepository.count(specification);
	}

	protected Specification<Author> createSpecification(AuthorCriteria criteria) {
		Specification<Author> specification = Specification.where(null);
		if (criteria != null) {
			if (criteria.getId() != null) {
				specification = specification.and(buildRangeSpecification(criteria.getId(), Author_.id));
			}
			if (criteria.getFirstName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getFirstName(), Author_.firstName));
			}
			if (criteria.getLastName() != null) {
				specification = specification.and(buildStringSpecification(criteria.getLastName(), Author_.lastName));
			}
			if (criteria.getBookId() != null) {
				specification = specification.and(buildSpecification(criteria.getBookId(),
						root -> root.join(Author_.books, JoinType.LEFT).get(Book_.id)));
			}
		}
		return specification;
	}
}
