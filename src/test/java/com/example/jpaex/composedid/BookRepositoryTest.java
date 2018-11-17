package com.example.jpaex.composedid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldFindById() {
        //Given
        Book book1 = Book.builder().author("author1").title("title1").publisher("publisher1").build();
        Book book2 = Book.builder().author("author1").title("title2").publisher("publisher2").build();
        Stream.of(book1, book2).forEach(testEntityManager::persistAndFlush);

        //When
        Optional<Book> found = bookRepository.findById(new BookId("author1", "title1"));

        //Then
        assertThat(found).isPresent();
        assertThat(found.get()).isEqualTo(book1);
    }

    @Test
    public void shouldNotFindById() {
        //Given
        Book book1 = Book.builder().author("author1").title("title1").publisher("publisher1").build();
        Book book2 = Book.builder().author("author1").title("title2").publisher("publisher2").build();
        Stream.of(book1, book2).forEach(testEntityManager::persistAndFlush);

        //When && Then
        assertThat(bookRepository.findById(new BookId("author1", null))).isNotPresent();
        assertThat(bookRepository.findById(new BookId("author1", "title3"))).isNotPresent();
    }
}