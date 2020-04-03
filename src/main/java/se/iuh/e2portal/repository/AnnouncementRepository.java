package se.iuh.e2portal.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import se.iuh.e2portal.model.Announcement;

@RepositoryRestResource(itemResourceRel = "announcement", collectionResourceRel = "announcements", path = "announcements")
public interface AnnouncementRepository extends PagingAndSortingRepository<Announcement,Long> {
}
