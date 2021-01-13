package edu.trs.jpa;

import edu.trs.model.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaAlbumRepository extends CrudRepository<Album, Long> {

    List<Album> findByAlbumTitleIgnoreCaseContaining(String term);
}
