package org.cretal.expense.manager.api.types;

import org.cretal.expense.manager.config.ExpenseManagerDbConfig;
import org.cretal.expense.manager.spi.db.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { ExpenseManagerDbConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
class UserTest {


    @Resource
    private UserRepository userRepository;

    @Test
    public void testCreateUser(){
        System.out.println("(testCreateUser)");
        User user = new User("someId", "someName");
        userRepository.save(user);

        User user2 = userRepository.findById("someId").orElseThrow(RuntimeException::new);
        Assertions.assertEquals(user.getName(), user2.getName());


//    private static final Path DB_DIR = Paths.get("/tmp").resolve("expense-access-test-data");
//    private static final Path DB_PATH = DB_DIR.resolve("expense-access-test.db");
//    private static H2Database h2Database;
//
//    @BeforeAll
//    public void setup(){
//
//        if (Files.exists(DB_DIR)) {
//            deleteAll(DB_DIR);
//        }
//
//        h2Database = new H2Database(DB_PATH, "expense-test-ddl.sql");




    }


}