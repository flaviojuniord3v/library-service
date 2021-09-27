package dev.flaviojunior.library.book;

import dev.flaviojunior.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Book}.
 */
@Service
@Transactional
public class BookService {

	private final Logger log = LoggerFactory.getLogger(BookService.class);

	private final BookRepository bookRepository;

	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	/**
	 * Save a book.
	 *
	 * @param book the entity to save.
	 * @return the persisted entity.
	 */
	public Book save(Book book) {
		log.debug("Request to save Book : {}", book);
		return bookRepository.save(book);
	}

	/**
	 * Partially update a book.
	 *
	 * @param book the entity to update partially.
	 * @return the persisted entity.
	 */
	public Optional<Book> partialUpdate(Book book) {
		log.debug("Request to partially update Book : {}", book);

		return bookRepository.findById(book.getId()).map(existingBook -> {
			if (book.getTitle() != null) {
				existingBook.setTitle(book.getTitle());
			}
			if (book.getIsbn() != null) {
				existingBook.setIsbn(book.getIsbn());
			}
			if (book.getImagePath() != null) {
				existingBook.setImagePath(book.getImagePath());
			}
			if (book.getPublisher() != null) {
				existingBook.setPublisher(book.getPublisher());
			}
			if (book.getDateOfPublication() != null) {
				existingBook.setDateOfPublication(book.getDateOfPublication());
			}

			return existingBook;
		}).map(bookRepository::save);
	}

	/**
	 * Get all the books.
	 *
	 * @return the list of entities.
	 */
	@Transactional(readOnly = true)
	public List<Book> findAll() {
		log.debug("Request to get all Books");
		return bookRepository.findAllWithEagerRelationships();
	}

	/**
	 * Get all the books with eager load of many-to-many relationships.
	 *
	 * @return the list of entities.
	 */
	public Page<Book> findAllWithEagerRelationships(Pageable pageable) {
		return bookRepository.findAllWithEagerRelationships(pageable);
	}

	/**
	 * Get one book by id.
	 *
	 * @param id the id of the entity.
	 * @return the entity.
	 */
	@Transactional(readOnly = true)
	public Optional<Book> findOne(Long id) {
		log.debug("Request to get Book : {}", id);
		return bookRepository.findOneWithEagerRelationships(id);
	}

	/**
	 * Delete the book by id.
	 *
	 * @param id the id of the entity.
	 */
	public void delete(Long id) {
		log.debug("Request to delete Book : {}", id);
		bookRepository.deleteById(id);
	}
}
