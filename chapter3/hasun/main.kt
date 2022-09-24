import kotlin.collections.joinToString

fun main() {
    // 3.1 코틀린에서 컬렉션 만들기
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    // to 언어 -> 일반 함수
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)

    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())
    val numbers = setOf(1, 14, 2)
    println(numbers.maxOrNull())

    // 3.2 함수를 호출하기 쉽게 만들기
    val list2 = listOf(1, 2, 3)
    println(joinToString(list2, "; ", "(", ")"))
    // 코틀린에서는 함수에 전달하는 인자 중 일부의 이름을 명시할 수 있다
    // 호출 시 인자 중 어느 하나라도 이름을 명시하고 나면 그 뒤에 오는 모든 인자는 이름을 명시해야
    println(joinToString(list2, separator = " ", prefix = " ", postfix = "."))

    // 디폴트 파라미터 값을 정의
    println(joinToStringSetDefault(list2, ", ", "", ""))
    // separator, prefix, postfix 생략
    println(joinToStringSetDefault(list2))
    // separator 를 "; "로 지정, prefix 와 postfix 생략
    println(joinToStringSetDefault(list2, "; "))

    // 리스트 3.3 메소드를 다른 클래스에 추가: 확장 함수와 확장 프로퍼티
    println("Kotlin".lastChar())

    // 3.3.3 확장 함수로 유틸리티 함수 정의
    val list3 = listOf(1, 2, 3)
    println(list3.joinToString(separator = "; ", prefix = "(", postfix = ")"))

    // 클래스가 아닌 더 구체적인 타입을 수신 객체 타입으로 지정할 수 있음
    // join -> 문자열의 컬렉션에 대해서만 호출할 수 있는 join 함수
    println(listOf("one", "two", "eight").join(""))

    // 리스트 3.6 확장 함수는 오버라이드 할 수 없다
    val view: View = Button()
    view.showOff()

    // 3.3.5 확장 프로퍼티
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}

fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 3.2 디폴트 파라미터 값을 사용해 정의하기
fun <T> joinToStringSetDefault(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}