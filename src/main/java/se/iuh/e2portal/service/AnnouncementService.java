package se.iuh.e2portal.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Announcement;
import se.iuh.e2portal.repository.AnnouncementRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService implements AnnouncementRepository{

    @Autowired
    private AnnouncementRepository announcementRepository;


    @Override
    public Iterable<Announcement> findAll(Sort sort) {
        return announcementRepository.findAll(sort);
    }

    @Override
    public Page<Announcement> findAll(Pageable pageable) {
        return announcementRepository.findAll(pageable);
    }

    @Override
    public <S extends Announcement> S save(S entity) {
        return announcementRepository.save(entity);
    }

    @Override
    public <S extends Announcement> Iterable<S> saveAll(Iterable<S> entities) {
        return announcementRepository.saveAll(entities);
    }

    @Override
    public Optional<Announcement> findById(Long aLong) {
        return announcementRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return announcementRepository.existsById(aLong);
    }

    @Override
    public Iterable<Announcement> findAll() {
        return announcementRepository.findAll();
    }

    @Override
    public Iterable<Announcement> findAllById(Iterable<Long> longs) {
        return announcementRepository.findAllById(longs);
    }

    @Override
    public long count() {
        return announcementRepository.count();
    }

    @Override
    public void deleteById(Long aLong) {
        announcementRepository.deleteById(aLong);
    }

    @Override
    public void delete(Announcement entity) {
        announcementRepository.delete(entity);
    }

    @Override
    public void deleteAll(Iterable<? extends Announcement> entities) {
        announcementRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        announcementRepository.deleteAll();
    }
}
