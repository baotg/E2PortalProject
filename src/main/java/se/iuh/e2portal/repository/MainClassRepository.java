package se.iuh.e2portal.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import se.iuh.e2portal.model.MainClass;
@RepositoryRestResource(itemResourceRel = "mainClass", collectionResourceRel = "mainClasss", path = "mainClasss")
public interface MainClassRepository extends PagingAndSortingRepository<MainClass, String> {
}
