package chapter2

// Function 함수

/*

    코틀린의 함수 선언은 fun 키워드로 시작합니다.

    fun 다음에 함수 이름이오고  그뒤 괄호 안에 파라미터 목록이 옵니다.

    함수의 반환 타입은 파라미토 목록의 닫는 괄호 다음에 오는데 괄호와 반환 타입 사이를 콜론(:) 으로 구분해야 합니다.

    함수 이름   파라미터        리턴타입
    fun max(a: Int, b: Int): Int {

    }

 */

fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

//간결하게 max 표현하기
fun max2(a: Int, b: Int): Int = if (a > b) a else b

// 반환 타입 까지 생략하기
fun max3(a: Int, b: Int) = if (a > b) a else b

fun main() {
    println(max(1,10)) // 10
}
