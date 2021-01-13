package edu.trs.repository.impl;

import edu.trs.jpa.JpaApplicationUserRepository;
import edu.trs.model.ApplicationUser;
import edu.trs.repository.ApplicationUserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApplicationUserRepositoryImpl implements ApplicationUserRepository {

    private final JpaApplicationUserRepository jpaApplicationUserRepository;

    public ApplicationUserRepositoryImpl(JpaApplicationUserRepository jpaApplicationUserRepository) {
        this.jpaApplicationUserRepository = jpaApplicationUserRepository;
    }

    @Override
    public void addUser(ApplicationUser user) {
        jpaApplicationUserRepository.save(user);
    }

    @Override
    public ApplicationUser findByUserName(String username) {
        return jpaApplicationUserRepository.findByUsername(username);
    }
}
