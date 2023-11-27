package com.gdsc.springstudy;

import com.gdsc.springstudy.web.HelloController;
import org.junit.jupiter.api.Test;
// 현재 Gradle 설정에서 JUnit 5를 사용하고 있기 때문
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith(SpringRunner.class) 제거 (JUnit 5에서는 필요 없음)
@WebMvcTest(controllers= HelloController.class) // @ExtendWith(SpringExtension.class) 포함
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
//    @WithMockUser // 테스트 메서드를 실행하는 동안 Mock 사용자로 인증되도록 함.
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}
