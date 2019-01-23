package com.example.demo

import it.skrape.core.skrape
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class MarkupServiceIT {

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun `use skrapeit to test markup`() {

        skrape {
            url = "http://localhost:$port/html"

            response {
                assertThat(statusCode).isEqualTo(200)

                element("title") {
                    assertThat(text()).isEqualTo("codefreeze-kotlin-session")
                }

                element("div h1") {
                    assertThat(text()).isEqualTo("This HTML markup has been written with the kotlin HTML DSL.")
                }
            }
        }
    }
}
