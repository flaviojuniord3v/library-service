package dev.flaviojunior.web.rest;

import dev.flaviojunior.common.web.rest.errors.BadRequestAlertException;
import dev.flaviojunior.common.web.util.HeaderUtil;
import dev.flaviojunior.common.web.util.PaginationUtil;
import dev.flaviojunior.common.web.util.ResponseUtil;
import dev.flaviojunior.domain.Author;
import dev.flaviojunior.repository.AuthorRepository;
import dev.flaviojunior.service.AuthorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * REST controller for managing {@link Author}.
 */
@RestController
@RequestMapping("/api")
public class AuthorResource {

    private final Logger log = LoggerFactory.getLogger(AuthorResource.class);

    private static final String ENTITY_NAME = "author";

    @Value("${spring.application.name}")
    private String applicationName;

    private final AuthorService authorService;

    private final AuthorRepository authorRepository;

    public AuthorResource(AuthorService authorService, AuthorRepository authorRepository) {
        this.authorService = authorService;
        this.authorRepository = authorRepository;
    }

    /**
     * {@code POST  /authors} : Creates a new author.
     *
     * @param author A author to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new author, or with status {@code 400 (Bad Request)} if the author has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/authors")
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody Author author) throws URISyntaxException {
        log.debug("REST request to save Author : {}", author);
        if (author.getId() != null) {
            throw new BadRequestAlertException("A new author cannot already have an ID", ENTITY_NAME);
        }
        Author result = authorService.save(author);
        return ResponseEntity
                .created(new URI("/api/authors/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT  /authors/:id} : Updates an existing author.
     *
     * @param id     the id of the author to save.
     * @param author the author to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated author,
     * or with status {@code 400 (Bad Request)} if the author is not valid,
     * or with status {@code 500 (Internal Server Error)} if the author couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/authors/{id}")
    public ResponseEntity<Author> updateAuthor(
            @PathVariable(value = "id", required = false) final Long id,
            @Valid @RequestBody Author author
    ) throws URISyntaxException {
        log.debug("REST request to update Author : {}, {}", id, author);
        if (author.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME);
        }
        if (!Objects.equals(id, author.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME);
        }

        if (!authorRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME);
        }

        Author result = authorService.save(author);
        return ResponseEntity
                .ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, ENTITY_NAME, author.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET  /authors} : get all the authors.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of authors in body.
     */
    @GetMapping("/authors")
    public ResponseEntity<List<Author>> getAllAuthors(Pageable pageable) {
        log.debug("REST request to get a page of Authors");
        Page<Author> page = authorService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
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

    /**
     * {@code DELETE  /authors/:id} : delete the "id" author.
     *
     * @param id the id of the author to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/authors/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        log.debug("REST request to delete Author : {}", id);
        authorService.delete(id);
        return ResponseEntity
                .noContent()
                .headers(HeaderUtil.createEntityDeletionAlert(applicationName, ENTITY_NAME, id.toString()))
                .build();
    }
}
