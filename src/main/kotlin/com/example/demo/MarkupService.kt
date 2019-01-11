package com.example.demo

import kotlinx.html.*
import kotlinx.html.dom.createHTMLDocument
import kotlinx.html.dom.serialize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MarkupService {

    @GetMapping("/html")
    fun renderWebPage(): String {
        return renderMarkup()
    }
}

// you can even write html markup by using the kotlin html dsl
fun renderMarkup(): String =
        createHTMLDocument().html {

            head {
                title { +"codefreeze-kotlin-session" }
                meta(charset = "utf-8")
            }

            body {
                div {
                    id = "mainContainer"
                    h1 {
                        +"This HTML markup has been written with the kotlin HTML DSL."
                    }
                }
            }
        }.serialize(true)