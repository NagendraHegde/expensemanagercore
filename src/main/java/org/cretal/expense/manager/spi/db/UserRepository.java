package org.cretal.expense.manager.spi.db;

import org.cretal.expense.manager.api.types.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
