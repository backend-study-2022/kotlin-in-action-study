package chapter2

/*

    커스텀 접근자

    이번 장은 프로퍼티의 접근자를 직접 작성하는 방법을 알아보자.

    직사각형 클래스인 Rectangle 을 정의하면서 자신이 정사격힝인지 알려주는 기능을 만들어 보자.

    직사각형이 정사각형인지를 별도의 필드에 저장할 필요가 없다. 사각혀으이 너비와 높이가 같은지

    검사하면 정사각형 여부를 그때그때 알 수 있다.
 */

class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean get() { return height == width }
}

fun main() {

    val rectangle1 = Rectangle(50, 50)
    val rectangle2 = Rectangle(50, 30)

    println(rectangle1.isSquare) // true
    println(rectangle2.isSquare) // false
}
