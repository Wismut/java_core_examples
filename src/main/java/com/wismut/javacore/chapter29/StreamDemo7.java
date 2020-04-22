package main.java.com.wismut.javacore.chapter29;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo7 {
    public static void main(String[] args) {
        ArrayList<NamePhoneEmail> myList = new ArrayList<>();

        myList.add(new NamePhoneEmail("Larry", "555-5555",
                "Larry@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("James", "555-4444",
                "James@HerbSchildt.com"));
        myList.add(new NamePhoneEmail("Mary", "555-3333",
                "Mary@HerbSchildt.com"));

        Stream<NamePhone> nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));

        List<NamePhone> npList = nameAndPhone.collect(
                LinkedList::new,
                LinkedList::add,
                LinkedList::addAll);

        System.out.println("Name and phone numbers in a list:");
        for (NamePhone e : npList) {
            System.out.println(e.name + ": " + e.phonenum);
        }

        nameAndPhone = myList.stream().map((a) -> new NamePhone(a.name, a.phonenum));

        Set<NamePhone> npSet = nameAndPhone.collect(Collectors.toSet());

        System.out.println("\nNames and phone numbers in a set:");
        for (NamePhone e : npSet) {
            System.out.println(e.name + ": " + e.phonenum);
        }
    }
}
