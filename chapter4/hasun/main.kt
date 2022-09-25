package chapter4

import java.io.File
import javax.naming.Context
import javax.swing.text.AttributeSet

fun main() {
    Button().click()    // I was clicked
    Button().showOff()

    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()

    val hyun = User("현석")
    println(hyun.isSubscribed)  // isSubscribed 파라미터에는 디폴트 값이 쓰인다

    val gye = User("계명", false)
    println(gye.isSubscribed)   // 모든 인자를 파라미터 선언 순서대로 지정할 수도 있다

    val hey = User("혜원", isSubscribed = false)
    println(hey.isSubscribed)   // 생성자 인자 중 일부에 대해 이름을 지정할 수도 있다

    println(Privateuser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
    println(FacebookUser(12345).nickname)

    val customer = Customer("Alice")
    customer.address = "Elsenheimerstrasse 47, 80687 Muenchen"

    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)

    val client1 = Client("오현석", 4122)
    println(client1)

    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))

    A.bar()
    val p = Person2.fromJSON("json")
}

// 4.1.1 코틀린 인터페이스
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!") // 디폴트 구현이 있는 메소드
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked")
    override fun showOff() = super<Clickable>.showOff()
}

interface Focusable {
    fun setFocus (b: Boolean) =
        println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

open class RichButton : Clickable { // 이 클래스는 열려있다. 다른 클래스가 이 클래스를 상속할 수 있음
    fun disable() {} // 이 함수는 파이널이다. 하위 클래스가 이 메소드를 오버라이드 할 수 없다
    open fun animate() {} // 이 함수는 열려있다. 하위 클래스에서 이 메소드를 오버라이드해도 된다
    override fun click() {} // 이 함수는 (상위 클래스에서 선언된) 열려있는 메소드를 오버라이드 한다
}

abstract class Animated { // 이 클래스는 추상클래스. 이 클래스의 인스턴스를 만들 수 없다
    abstract fun animate() // 이 함수는 추상 함수. 이 함수에는 구현이 없음. 하위 클래스에서 반드시 오버라이드 해야함
    open fun stopAnimating() {}
    fun animateTwice() {}       // 추상 클래스에 속했더라도 비추상 함수는 기본적으로 파이널이지만 open 으로 오버라이드를 허용할 수 있음
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")
    protected fun whisper() = println("Let's talk!")
}

interface State: java.io.Serializable

interface View {
    fun getCurrentState(): State
    fun restoreState (state: State) {}
}

class Button2 : View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {
        super.restoreState(state)
    }
    class ButtonState : State {}
}

class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

/*
interface Expr
class Num(val value:Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr
fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.right) + eval(e.left)
        else -> // else 분기가 꼭 있어야 한다
            throw java.lang.IllegalArgumentException("Unknown expression")
    }
*/

sealed class Expr { // 기반 클래스를 sealed 로 봉인한다
    class Num(val value: Int) : Expr()  // 기반 클래스의 모든 하위 클래스를 중첩 클래스로 나열한다
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) {      // when 식이 모든 하위 클래스를 검사하므로 별도의 else 분기가 없어도 된다
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.right) + eval(e.left)
    }

open class User(val nickname: String,    // 생성자 파라미터에 대한 디폴트 값을 제공한다
            val isSubscribed: Boolean = true)

class TwitterUser(nickname: String) : User(nickname) {}

open class View2 {
    constructor(ctx: Context) {
        // 코드
    }
    constructor(ctx: Context, attr: AttributeSet) {
        // 코드
    }
}

class MyButton : View2 {
    // 상위 클래스의 생성자를 호출한다
    constructor(ctx: Context) : super(ctx) {
        // ..
    }
    constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr) {
        // ...
    }
}

interface Member {
    val nickname: String
}

// 주 생성자 안에 프로퍼티를 직접 선언
class Privateuser(override val nickname: String) : Member

// 커스텀 게터로 nickname 프로퍼티를 설정
class SubscribingUser(val email: String) : Member {
    override val nickname: String
        get() = email.substringBefore('@') // 커스텀 게터
}

// 초기화 식으로 nickname 값을 초기화
// 매번 getter 로 가져올 때 비용이 많이 든다면,
// 객체 조기화 방법으로 한번만 호출하게 할 수도 있다
class FacebookUser(val accountId: Int) : Member {
    override val nickname = getFacebookName(accountId)

    private fun getFacebookName(accountId: Int) : String {
        return "test$accountId"
    }
}

class Customer(val name: String) {
    var address: String = "unspecified"
        set (value: String) {
        println("""
            Address was changed for $name:
            "$field" -> "$value".""".trimIndent())
        field = value
    }
}

class LengthCounter {
    var counter: Int = 0
        private set     // 이 클래스 밖에서 이 프로퍼티의 값을 바꿀 수 없다
    fun addWord(word: String) {
        counter += word.length
    }
}

class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client)
            return false
        return name == other.name &&
                postalCode == other.postalCode
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + postalCode
        return result
    }
}

object Payroll {
    val allEmployees = arrayListOf<Person>()
    fun calculateSalary() {
        for (person in allEmployees) {
            // ,,,
        }
    }
}

object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path,
            ignoreCase = true)
    }
}

data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int =
            o1.name.compareTo(o2.name)
    }
}

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

/*
class User2 {
    val nickname: String
    constructor(email: String) {
        nickname = email.substringBefore('@')
    }
    constructor(facebookAccountId: Int) {
        nickname = getFacebookName(facebookAccountId)
    }
}

class User3 private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
            User3(email.substringBefore('@'))
        fun newFacebookUser(accountId: Int) =
            User3(getFacebookName(accountId))
    }
}
*/

interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}
class Board(val name: String) {
    companion object : JSONFactory<Board> {
        override fun fromJSON(jsonText: String): Board =
            Board(jsonText.substringBefore("@"))
    }
}

// 4.29 동반 객체에 대한 확장 함수 정의하기
// 비즈니스 로직 모듈
class Person2(val firstName: String, val lastName: String) {
    companion object {
        // 비어있는 동반 객체를 선언한다
    }
}
// 클라이언트/서버 통신 모듈
fun Person2.Companion.fromJSON(json: String): Person { // 확장 함수를 선언한다
    return Person("test")
}
