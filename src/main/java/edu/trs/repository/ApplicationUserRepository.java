package edu.trs.repository;

import edu.trs.model.ApplicationUser;

public interface ApplicationUserRepository {
    void addUser(ApplicationUser user);

    ApplicationUser findByUserName(String username);
}
