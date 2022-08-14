package chapter2

import java.io.BufferedReader

// try 를 식으로

fun readNumber2(reader: BufferedReader) {

    val number = try {
        Integer.parseInt(reader.readLine())
    } catch (e: NumberFormatException) {
        null
    }
    println(number)

}