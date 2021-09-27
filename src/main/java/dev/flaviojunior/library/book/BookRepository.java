package dev.flaviojunior.library.book;

import dev.flaviojunior.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data SQL repository for the Book entity.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
	@Query(value = "select distinct book from Book book left join fetch book.authors left join fetch book.categories", countQuery = "select count(distinct book) from Book book")
	Page<Book> findAllWithEagerRelationships(Pageable pageable);

	@Query("select distinct book from Book book left join fetch book.authors left join fetch book.categories")
	List<Book> findAllWithEagerRelationships();

	@Query("select book from Book book left join fetch book.authors left join fetch book.categories where book.id =:id")
	Optional<Book> findOneWithEagerRelationships(@Param("id") Long id);
}
