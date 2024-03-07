package dev.patika.libraryManagementnew.business.concretes;

import dev.patika.libraryManagementnew.business.abstracts.IPublisherService;
import dev.patika.libraryManagementnew.core.exception.NotFoundException;
import dev.patika.libraryManagementnew.core.utilies.Msg;
import dev.patika.libraryManagementnew.dao.PublisherRepo;
import dev.patika.libraryManagementnew.entities.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements IPublisherService {

    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.publisherRepo.findAll(pageable);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);
        return true;
    }
}
