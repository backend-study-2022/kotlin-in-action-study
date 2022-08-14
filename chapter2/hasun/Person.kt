// 코틀린으로 변환한 Person 클래스
// 이런 유형의 클래스(코드가 없이 데이터만 저장하는 클래스)를 값 객체(value object)
// 자바 -> 코틀린 변환 결과 public 가시성 변경자(visibility modifier)가 사라졌음
class Person(
    val name: String, // 읽기 전용 프로퍼티 -> field, getter
    var isMarried: Boolean // 공개 프로퍼티 -> field, getter, setter
)