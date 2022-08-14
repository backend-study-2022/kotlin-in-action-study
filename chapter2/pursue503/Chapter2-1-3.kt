package chapter2

import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * 변수
 *
 */


/*
    자바에서는 변수를 선언할 때 타입이 맨 앞에 옵니다.

    하지만 코틀린에서는 타입 지정을 생략하거나 변수 이름뒤에 타입을 명시 할 수 있습니다.

    그런 이유로 코틀린에서는 키워드로 변수 선언을 시작하는 대신 변수 이름 뒤에

    타입을 명시하거나 생략할수 있다.
 */



/*

    변경이 가능한 변수와 변경이 불가능한 변수

    val (value) - 변경 불가능한 참조를 저장하는 변수다
                  val 로  선언된 변수는 일단 초기화하고 나면 재대입이 불가능하다.
                  자바로 말하자면 final 변수에 해당한다.

    var (variable) - 변경 가능한 참조다
                     이런 변수의 값은 바뀔 수 있다 자바의 일반 변수에 해당한다.

    기본적으로 모든 변수를 val 키워드를 사용하고 나중에 필요하면 var 로 변경하도록 하자.

 */

/*

    val 변수는 블록을 실행할 때 정확히 한 번만 초기화해야하지만 어떤 블록이 실행될 때 오직 한 초기화

    문장만 실행됨을 컴파일러가 확인 할 수 있다면 조건에 따라 여러 값으로 초기화가 가능하다.

    val 변수의 값이 참조 자체는 불변일지라도 그 참조가 가리키는 객체의 내부 값은 변경 될 수 있다.

 */

// 몇개의 변수를 선언해보자

fun main() {
    val question = "삶 우주, ... ETC"

    val answer = 42

    // 타입 명시하기

    val number: Int = 10

    //초기화를 하지 않고 변수를 사용하려면 타입을 반드시 명시해야 한다
    val num: Int

    num = 42


    // 구분을 통하여 val 변수의 값을 여러개로 초기화 시킨다.
    val dynamicValue: String = if(getRandomNumber() > 5) "5초과의 변수 값" else "5 이하의 값"

    // val 변수의 값이 참조 자체는 불변일지라도 그 참조가 가리키는 객체의 내부 값은 변경 될 수 있다.
    val stringList: ArrayList<String> = arrayListOf("Java")
    stringList.add("kotlin")


}


fun getRandomNumber(): Int = Random().nextInt(10)


