package funsets

import org.scalatest.FunSuite


import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  // test("string take") {
  //   val message = "hello, world"
  //   assert(message.take(5) == "hello")
  // }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  // test("adding ints") {
  //   assert(1 + 2 === 3)
  // }


  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }

  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   *
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   *
   *   val s1 = singletonSet(1)
   *
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   *
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   *
   */

  trait TestSets {
    val emptySet = (x:Int) => false
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   *
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {

    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3".
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements of each set") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")

      val empty = union(s1, emptySet)
      assert(contains(empty, 1))
      assert(!contains(empty, 2))
      assert(!contains(empty, 3))
    }
  }

  test("intersect contains all elements that are in both sets") {
    new TestSets {
      val s12 = union(s1, s2)
      val s23 = union(s2, s3)

      val i2 = intersect(s12, s23)

      assert(contains(i2, 2), "Contains 2")
      assert(!contains(i2, 1), "Not contains 1")
      assert(!contains(i2, 3), "Not contains 3")

      val i0 = intersect(s1, emptySet)
      assert(!contains(i0, 1), "Contains nothing")
      assert(!contains(i0, 2), "Contains nothing")
      assert(!contains(i0, 3), "Contains nothing")
    }
  }

  test("diff contains elements from a set that are not in another") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)

      assert(contains(s123, 1))
      assert(contains(s123, 2))
      assert(contains(s123, 3))

      val s13 = diff(s123, s2)

      assert(contains(s13, 1))
      assert(!contains(s13, 2))
      assert(contains(s13, 3))
    }
  }

  test("filter applies a filter to the set") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)

      val f = filter(s123, x => x > 2)

      assert(!contains(f, 1))
      assert(!contains(f, 2))
      assert(contains(f, 3))

    }
  }

  test("forall verifies if all set's elements satisfies a function") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)

      assert(forall(s123, x => x > 0))
      assert(!forall(s123, x => x > 10))
      assert(!forall(s123, x => x == 2))

    }
  }

  test("exists verifies if exists an element that satisfies a condition") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)

      assert(exists(s123, x => x == 1))
      assert(exists(s123, x => x == 2))
      assert(exists(s123, x => x == 3))
      assert(exists(s123, x => x > 0))
      assert(!exists(s123, x => x == 4))
    }
  }

  test("map transforms a set in another") {
    new TestSets {
      val s123 = union(union(s1, s2), s3)

      val mapped = map(s123, x => x * 10)

      assert(!exists(mapped, x => x == 3))
      assert(exists(mapped, x => x == 10))
      assert(exists(mapped, x => x == 20))
      assert(exists(mapped, x => x == 30))
      assert(!exists(mapped, x => x == 40))
    }
  }


}
