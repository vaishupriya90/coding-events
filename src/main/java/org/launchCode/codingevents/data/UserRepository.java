package org.launchCode.codingevents.data;

import org.launchCode.codingevents.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,IncompatibleClassChangeError>{
    User findByUserName(String userName);
}
