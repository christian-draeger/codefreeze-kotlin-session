package com.example.demo

import org.assertj.core.api.SoftAssertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import paco.annotations.Fetch
import paco.runner.Paco

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringRunner::class)
class MarkupServiceIT : Paco() {

    @Test
    @Fetch(url = "localhost:8080/html")
    fun name() {
        val response = page.get()

        val softly = SoftAssertions()

        softly.assertThat(response.statusCode).isEqualTo(200)

        softly.assertThat(response.getElement("title").text())
                .isEqualTo("codefreeze-kotlin-session")

        softly.assertThat(response.getElement("div h1").text())
                .isEqualTo("This HTML markup has been written with the kotlin HTML DSL.")

        softly.assertAll()
    }
}
