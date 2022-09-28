package com.example.propina

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun generate_numbers() {
        /*val dice = Dice(6)
        val rollResult = dice.roll()
        assertTrue("Valor de rollResult no esta entre 1 and 6", rollResult in 1..6 )
         */
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}

/**
 * common asserts
 * assertEquals(), assertNotEquals(), assertThat(), assertTrue(), assertFalse(),
 * assertNull(), assertNotNull()
 * more assert => https://developer.android.com/reference/junit/framework/Assert
 * */