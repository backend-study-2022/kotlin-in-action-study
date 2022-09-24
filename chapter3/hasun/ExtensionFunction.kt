import java.awt.Button

// 어떤 문자열의 마지막 문자를 돌려주는 메소드
// fun String.lastChar(): Char = this.get(this.length - 1) -> this 생략 가음
fun String.lastChar(): Char = get(length - 1)
val String.lastChar: Char
    get() = get(length - 1)
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        this.setCharAt(length - 1, value)
    }

// 3.3.3 확장 함수로 유틸리티 함수 정의
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) { // this 는 수신 객체를 가리킴. 여기서는 T 타입의 원소로 이뤄진 컬렉션
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = joinToString(separator, prefix, postfix)

open class View {
    open fun click() = println("View clicked")
}

class Button: View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")