package org.launchCode.codingevents.data;

import org.launchCode.codingevents.models.Tag;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<Tag,Integer> {
}
