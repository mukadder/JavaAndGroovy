package com.nick
import java.io.PrintStream;

import org.groovykoans.koan03.GroovyPerson
import org.groovykoans.koan03.JavaPerson
import org.groovykoans.koan08.Cartoon
import org.groovykoans.koan08.Person
import org.groovykoans.koan08.Feeling
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import static org.mockito.Mockito.times
import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.initMocks

class paduTest {

	private Developer groovyDeveloper

	@Mock PrintStream output

	@Before void beforeTest() {
		initMocks(this)
		groovyDeveloper = new GroovyDeveloper(output)
	}

	@Test void shouldDoSomethingGroovy() {
		groovyDeveloper.doStuff()
		verify(output, times(1)).println("I am developing with Java.")
		verify(output, times(1)).println("I'm adding some groovy goodness.")
	}

	@Test void test01_IntroToGroovyBeans() {
		// JavaBeans (or POJOs) are simple Java objects with getters (getX) and setters (setX) for its members
		JavaPerson javaPerson = new JavaPerson("Argus", "Filch", "1234");

		// Groovy introduces an easier way to create JavaBeans. They're called GroovyBeans.
		// Have a read here: http://groovy.codehaus.org/Groovy+Beans
		GroovyPerson groovyPerson = new GroovyPerson('Harry', 'Potter', '3322')

		// Explore the differences between JavaPerson and GroovyPerson and read some of the user guide above.
		// When you're done, add the necessary getters to get the respective first names
		// Hint: The reason you don't have a place to add code in Groovy is because you don't have to!
		def javaFirstName
		def groovyFirstName
		// ------------ START EDITING HERE ----------------------
		javaFirstName = javaPerson.getFirstName();
		groovyFirstName = groovyPerson.firstName
		// ------------ STOP EDITING HERE  ----------------------

		assert javaFirstName == 'Argus'
		assert groovyFirstName == 'Harry'
	}
	
	@Test void test04_Lists() {
		// In Java, list creation can be somewhat cumbersome:
		List<String> javaList = new ArrayList<String>();
		javaList.add("King");
		javaList.add("Queen");
		javaList.add("Prince");

		// In Groovy, this is simplified to:
		// (See http://groovy.codehaus.org/JN1015-Collections
		// and http://groovy.codehaus.org/groovy-jdk/java/util/List.html)
		def groovyList = ['King', 'Prince']

		// Add the missing item to the Groovy list. Pay attention to the order of the items.
		// Hint: you can use either Java's add(int, String) or Groovy's plus() method.
		// ------------ START EDITING HERE ----------------------
		groovyList = groovyList.plus(1, 'Queen')
		// ------------ STOP EDITING HERE  ----------------------

		// Note how Groovy allows you to compare the *content* of the lists
		assert groovyList == javaList
	}
	@Test void test03_MultiAssignment() {
		// Sometimes you want to return more than one variable from a method. Yes, you could do it with an enclosing
		// class, but it could be an overkill. Groovy calls it Multiple Assignments.
		// http://groovy.codehaus.org/Multiple+Assignment

		// Create a closure that returns two random integers in a given range
		def generateTwoRandomInts=  { int maxInt ->
			// ------------ START EDITING HERE ----------------------
			def random = new Random()
			[random.nextInt(maxInt), random.nextInt(maxInt)]
			// ------------ STOP EDITING HERE  ----------------------
		}

		def (intA, intB) = generateTwoRandomInts(10)
		assert intA in 0..10
		assert intB in 0..10
	}
	@Test void test02_IsCaseInSwitch() {
		// What the switch-case clause basically does in the following:

		// switch (test) {
		//   case candidate: break;
		// }
		//
		// candidate.isCase(test)

		// This means that if we implement our own isCase(), we can take advantage of it in switch statements.
		// For this exercise we have a Cartoon object that has one single feeling.
		// Let's implement a isCase() in Feeling so we can use switch-case on a Cartoon.

		def cartoon = new Cartoon(name: 'Mickey Mouse', feeling: Feeling.Guilty)

		switch (cartoon) {
			case Feeling.Guilty:
				// process guilty feeling cartoon
				break
			default:
				fail()
		}

		// Suppose people may have more than one Feeling. Implement the appropriate isCase to allow switching on them
		def person = new Person(name: 'Jack Bauer', feelings: [Feeling.Suicidal, Feeling.Relaxed])

		switch (person) {
			case Feeling.Anticipation:
				break
			case [Feeling.Happy, Feeling.Sad]:
				// Note how we can also use Lists here. In this case the Feeling.isCase() is triggered on all items
				break
			case Feeling.Suicidal:
				// process suicidal person
				break
			default:
				fail()
		}

	}
}
