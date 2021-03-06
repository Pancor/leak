package pl.pancordev.leak.two.nine;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.pancordev.leak.services.Service2;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@WebAppConfiguration
@RunWith(SpringRunner.class)
public class DummyControllerTest2 {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private Service2 service2;

    private MockMvc mvc;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .defaultRequest(delete("/").contentType(MediaType.APPLICATION_JSON))
                .alwaysDo(print())
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldReturnProperResponseFromIndexPage() throws Exception {
        mvc.perform(delete("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("It's dummy response from server"));
    }
}
