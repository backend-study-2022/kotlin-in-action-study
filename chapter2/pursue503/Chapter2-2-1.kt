package chapter2

/*
    클래스라는 개념의 목적은 데이터를 캡슐화 하고 캡슐화한 데이터를 다루는 코드를 한 주체 아래 가두는 것이다.

    자바에서는 데이터를 필드에 저장하며, 멤버 필드의 가시성은 보통 private 다.

    클래스는 자신을 사용하는 클라이언트가 그 데이터에 접근하는 통로로 쓸 수 있는 접근다 메소드를 제공한다.

    보통은 필드를 읽기 위한 getter 를 제공하고 필드를 변경하게 허용해야 할 경우 setter 를 제공 한다.

    이런 예를 Person 클래스에서도 볼 수 있다. 세터는 자신이 받은 값을 검증하거나 필드 변경을 다른 곳에

    통지하는 등의 로직을 더 가질 수 있다.

    자바에서는 필드와 접근자를 한데 묶어 프로퍼티라고 부르며, 프로퍼티 개념을 활용한 프레임워크가 많다.

    코틀린은 프로퍼티를 언어 기본 기능으로 제공하며, 코틀린 프로퍼티는 자바의 필드와 접근자 메소드를 완전히

    대신 하였다. 클래스에서 프로퍼티를 선언할 때는 앞에서 살펴본 변수를 선언하는 방법과 마찬가지로

    val 이나 var 를 사용한다 val 로 선언하면 읽기 전용이며 var 로 선언한 프로퍼티는 변경이 가능하다.

    - 코틀린에서는 프로퍼티를 선언할때 val 이나 var 를 사용한다.
    - val 로 선언하면 읽기 전용이 된다
    - var 로 선언하면 getter 와 setter 가 둘다 생선하는것과 같다.

 */

// 코틀린에서의 getter setter 사용법

class Person(val name: String, var isMarried: Boolean) {

}

fun main() {

    val person = Person("Bob", true)

    // name 호출
    println(person.name)

    // isMarried 호출
    println(person.isMarried)

    //변경
    person.isMarried = false

    println(person.isMarried)

}