package com.example.demo

import io.restassured.RestAssured.given
import io.restassured.response.Response
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions
import org.hamcrest.Matchers.equalTo
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner::class)
class DummyServiceIT {

    @LocalServerPort
    private var port: Int = 0

    @Test
    fun `iam endpoint recognizes children`() {
        val response = given().port(port)
                .param("age", 0)
                .`when`().get("/iam")
        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.bodyString()).isEqualTo("you are a child")
    }

    @Test
    fun `iam endpoint recognizes teenagers`() {
        val response = given().port(port)
                .param("age", 15)
                .`when`().get("/iam")
        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.bodyString()).isEqualTo("you are a teenager")
    }

    @Test
    fun `iam endpoint recognizes adult`() {
        val response = given().port(port)
                .param("age", 42)
                .`when`().get("/iam")
        assertThat(response.statusCode).isEqualTo(200)
        assertThat(response.bodyString()).isEqualTo("you are adult")
    }

    @Test
    fun `iam endpoint recognizes invalid age`() {
        val response = given().port(port)
                .param("age", -1)
                .`when`().get("/iam")

        val softly = SoftAssertions()
        softly.assertThat(response.statusCode).isEqualTo(200)
        softly.assertThat(response.bodyString()).isEqualTo("this is strange")
        softly.assertAll()
    }

    @Test
    fun `will return 400 on invalid age type`() {
        val response = given().port(port)
                .param("age", "foo")
                .`when`().get("/iam")
        assertThat(response.statusCode).isEqualTo(400)
    }

    @Test
    fun `will return dummy data`() {
        val response = given()
                        .port(port)
                        .`when`()
                        .get("/")
                .then()
                .statusCode(200)
        val softly = SoftAssertions()
        softly.assertThat(response.body("name", equalTo("codefreeze - Eva")))
        softly.assertThat(response.body("age", equalTo(18)))
        softly.assertThat(response.body("comment", equalTo("")))
    }
}

fun Response.bodyString(): String? = this.body.asString()