package dev.flaviojunior.library.author;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.flaviojunior.domain.Author;

/**
 * Service Implementation for managing {@link Author}.
 */
@Service
@Transactional
public class AuthorService {

	private final Logger log = LoggerFactory.getLogger(AuthorService.class);

	private final AuthorRepository authorRepository;

	public AuthorService(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	/**
	 * Save a author.
	 *
	 * @param author the entity to save.
	 * @return the persisted entity.
	 */
	public Author save(Author author) {
		log.debug("Request to save Author : {}", author);
		return authorRepository.save(author);
	}

	/**
	 * Partially update a author.
	 *
	 * @param author the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<Author> partialUpdate(Author author) {
		log.debug("Request to partially update Author : {}", author);

		return authorRepository.findById(author.getId()).map(existingAuthor -> {
			if (author.getFirstName() != null) {
				existingAuthor.setFirstName(author.getFirstName());
			}
			if (author.getLastName() != null) {
				existingAuthor.setLastName(author.getLastName());
			}

			return existingAuthor;
		}).map(authorRepository::save);
	}

	/**
	 * Get all the authors.
	 *
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public List<Author> findAll() {
		log.debug("Request to get all Authors");
		return authorRepository.findAll();
	}

	/**
	 * Get one author by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<Author> findOne(Long id) {
		log.debug("Request to get Author : {}", id);
		return authorRepository.findById(id);
	}

	/**
	 * Delete the author by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete Author : {}", id);
		authorRepository.deleteById(id);
	}
}
