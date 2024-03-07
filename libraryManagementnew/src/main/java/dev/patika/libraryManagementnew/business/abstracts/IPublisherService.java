package dev.patika.libraryManagementnew.business.abstracts;

import dev.patika.libraryManagementnew.entities.Publisher;
import org.springframework.data.domain.Page;

public interface IPublisherService {

    Publisher save(Publisher publisher);

    Publisher get(int id);

    Page<Publisher> cursor(int page, int size);

    Publisher update(Publisher publisher);

    boolean delete(int id);
}
