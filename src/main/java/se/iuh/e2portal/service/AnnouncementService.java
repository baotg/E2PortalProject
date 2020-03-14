package se.iuh.e2portal.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iuh.e2portal.model.Announcement;
import se.iuh.e2portal.repository.AnnouncementRepository;

import java.util.List;
@Service
public class AnnouncementService implements GenericTemplate<Announcement, Long> {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public Announcement save(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

    @Override
    public Announcement findById(Long id) {
        return announcementRepository.findById(id).get();
    }

    @Override
    public List<Announcement> findAll() {
        return (List<Announcement>) announcementRepository.findAll();
    }

    @Override
    public boolean existsById(Long id) {

        return announcementRepository.existsById(id);
    }

    @Override
    public long count() {
        return announcementRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public void delete(Announcement object) {
        announcementRepository.delete(object);
    }
}
