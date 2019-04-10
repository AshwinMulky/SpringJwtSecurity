package com.ashwinmulky.security.config.dbmigrations;

import com.ashwinmulky.security.model.User;
import com.ashwinmulky.security.model.enums.Role;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Creates the initial database setup
 */
@ChangeLog(order = "001")
public class InitialSetupMigration {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;

    @ChangeSet(order = "01", author = "initiator", id = "01-addUsers")
    public void addUsers(MongoTemplate mongoTemplate) {

        User adminUser = new User();
        adminUser.setId("user-1");
        adminUser.setName("admin");
        adminUser.setPassword(this.passwordEncoder.encode("admin"));
        adminUser.getRoles().add(Role.ROLE_ADMIN);
        mongoTemplate.save(adminUser);

        User user = new User();
        user.setId("user-2");
        user.setName("user");
        user.setPassword(this.passwordEncoder.encode("user"));
        user.getRoles().add(Role.ROLE_USER);
        mongoTemplate.save(user);
    }

}
