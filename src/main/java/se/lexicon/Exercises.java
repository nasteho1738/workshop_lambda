package se.lexicon;
import se.lexicon.data.DataStorage;
import se.lexicon.model.Person;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       TODO:  1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> erikPersons = storage.findMany(person -> person.getFirstName().equals("Erik"));
        erikPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> femalePersons = storage.findMany(person -> person.getFirstName().equals("Female"));
        femalePersons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> bornAfter2000 = storage.findMany(person -> person.getBirthDate().compareTo(LocalDate.of(2000, 1, 1)) >= 0);
        bornAfter2000.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO: 4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message) {
        System.out.println(message);
        //Write your code here
        Person person = storage.findOne(person1 -> {
            return person.getId() == 123;
        });
        if (person != null) {
            System.out.println(person);
        } else {
            System.out.println("Person not found");
        }
        System.out.println("----------------------");
    }

    /*
        TODO:  5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message) {
        System.out.println(message);
        //Write your code here
        String personInfo = storage.findOneAndMapToString(
                person -> person.getId() ==456,
                person -> "Name: " + person.getFirstName() + " " + person.getLastName() + " born " + person.getBirthDate().toString()
        );
        System.out.println(personInfo);
        System.out.println("----------------------");
    }

    /*
        TODO:  6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message) {
        System.out.println(message);
        //Write your code here
        List<String> maleNamesStartingWithE = storage.findManyAndMapEachToString(
                person -> person.getGender().equals("Male") && person.getFirstName().startsWith("E"),
                person -> person.getFirstName() + " " + person.getLastName()
        );
        maleNamesStartingWithE.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message, Duration Period) {
        System.out.println(message);
        //Write your code here
        LocalDate today = LocalDate.now();
        List<String> youngPersons = storage.findManyAndMapEachToString(
                person -> {
                    final boolean b;
                    if (10 <= Period.between(person.getBirthDate(), today).getYears()) {
                        b = false;
                    } else {
                        b = true;
                    }
                    return b;
                },
                person -> person.getFirstName() + " " + person.getLastName() + " " + Period.between(person.getBirthDate(), today).getYears() + "years"
        );
        youngPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }

    /*
        TODO:  8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndDo(
                person -> person.getFirstName().equals("Ulf"),
                System.out::println
        );
        System.out.println("----------------------");
    }

    /*
        TODO:  9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndDo(
                person -> person.getFirstName().contains(person.getFirstName()),
                System.out::println
        );
        System.out.println("----------------------");
    }

    /*
        TODO:  10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message) {
        System.out.println(message);
        //Write your code here
        storage.findAndDo(
                person -> person.getFirstName().equals(new StringBuilder(Person.getFirstName()).reverse().toString()),
                person ->  System.out.println(person.getFirstName() + " " + person.getLastName())
        );

        System.out.println("----------------------");
    }

    /*
        TODO:  11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message) {
        System.out.println(message);
        //Write your code here
        List<String> sortedPersons = storage.findAndSort(
                Comparator.comparing(Person::getFirstName).thenComparing(Person::getBirthDate)
        );

        System.out.println("----------------------");
    }

    /*
        TODO:  12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message) {
        System.out.println(message);
        //Write your code here
        List<Person> sortedPersons = storage.findAndSort(
                Comparator.comparing(Person::getBirthDate).reversed()
        );
        sortedPersons.forEach(Person::getBirthDate);
        System.out.println("----------------------");
    }

    /*
        TODO:  13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message) {
        System.out.println(message);
        //Write your code here
        List<String> sortedPersons = storage.findAndSort(
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName).thenComparing(Person::getBirthDate)
        );
        sortedPersons.forEach(System.out::println);
        System.out.println("----------------------");
    }
}

