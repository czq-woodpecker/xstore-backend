package com.woodpecker.czq.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.util.function.Consumer;

@AutoConfigureMockMvc
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public abstract class ApiTestBase {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EntityManager entityManager;

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    protected void clear(Consumer<EntityManager> consumer) {
        consumer.accept(entityManager);
        entityManager.clear();
    }
}