package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.util.PaginationUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Author;
import dev.flaviojunior.domain.Book;
import dev.flaviojunior.domain.Category;
import dev.flaviojunior.service.AuthorService;
import dev.flaviojunior.service.BookQueryService;
import dev.flaviojunior.service.BookService;
import dev.flaviojunior.service.CategoryService;
import dev.flaviojunior.service.criteria.BookCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/library")
public class LibraryResource {

    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);

    @Value("${spring.application.name}")
    private String applicationName;

    private final CategoryService categoryService;

    private final AuthorService authorService;

    private final BookQueryService bookQueryService;

    private final BookService bookService;

    public LibraryResource(CategoryService categoryService, AuthorService authorService, BookQueryService bookQueryService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookQueryService =bookQueryService;
        this.bookService = bookService;
    }

    /**
     * {@code GET  /books} : get all the books.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of books in body.
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks(BookCriteria criteria, Pageable pageable) {
        log.debug("REST request to get Books by criteria: {}", criteria);
        Page<Book> page = bookQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /books/:isbn/isbn} : get the "isbn" book.
     *
     * @param isbn the isbn of the book to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the book, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/books/{isbn}/isbn")
    public ResponseEntity<Book> getBook(@PathVariable String isbn) {
        log.debug("REST request to get Book : {}", isbn);
        Optional<Book> book = bookService.findOneByIsbn(isbn);
        return ResponseUtil.wrapOrNotFound(book);
    }

    /**
     * {@code GET  /categories/:id} : get the "id" category.
     *
     * @param id the id of the category to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the category, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        Optional<Category> category = categoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category);
    }

    /**
     * {@code GET  /authors/:id} : get the "id" author.
     *
     * @param id the id of the author to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the author, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/authors/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        log.debug("REST request to get Author : {}", id);
        Optional<Author> author = authorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(author);
    }
}
