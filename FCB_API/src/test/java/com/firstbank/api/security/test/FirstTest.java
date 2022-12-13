package com.firstbank.api.security.test;

import com.firstbank.api.SpringAppBootstrapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringAppBootstrapper.class)
public class FirstTest {

    @Test
    public void mvcTest() {

    }

}
