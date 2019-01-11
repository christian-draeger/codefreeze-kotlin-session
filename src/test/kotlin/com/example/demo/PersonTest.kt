package com.example.demo

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class PersonTest {

    @Test
    fun `my extension function should codefreeze to strings`() {
        assertThat("Eva".toCodeFreeze()).isEqualTo("codefreeze - Eva")
    }
}