package com.toasttab.pulseman.test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SampleTestClassITest {
    @Test
    fun `Sample integration test 1`() {
        assertThat(SampleTestClass().testFunction()).isTrue
    }
}
