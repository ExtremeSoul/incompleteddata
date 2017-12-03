package pl.sdacademy.rozkocha.szymon.incompleteddata;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("incompleted_people.txt");

        Scanner scanner = new Scanner(is);

        Collection<Person> people = new LinkedList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            List<String> data = split(line);

            Person person = new Person(data.get(0), data.get(1));

            if(!data.get(2).isEmpty()) {
                person.setHeight(Double.valueOf(data.get(2)));
            }

            if(!data.get(3).isEmpty()) {
                person.setWeight(Double.valueOf(data.get(3)));
            }

            people.add(person);

            System.out.println(person);
        }

        double heightAvg = 0;
        int heightCount = 0;
        double weightAvg = 0;
        int weightCount = 0;

        for(Person person : people) {
            Optional<Double> heightOptional = person.getHeight();

            if(heightOptional.isPresent()) {
                Double height = heightOptional.get();
                heightAvg += height;
                ++heightCount;
            }

            if(person.getWeight().isPresent()) {
                weightAvg += person.getWeight().get();
                ++weightCount;
            }
        }

        System.out.println("Height avg: " + (heightAvg / heightCount));
        System.out.println("Weight avg: " + (weightAvg / weightCount));

        Double avg = people.stream()
                .map(Person::getHeight) //39
                .filter(Optional::isPresent) //41
                //.map(Optional::get) //43
                //.collect(Collectors.averagingDouble(d -> d));
                .collect(Collectors.averagingDouble(Optional::get));

        System.out.println("Height avg: " + avg);
    }

    private static List<String> split(String string) {
        List<String> list = new LinkedList<>();

        int first = string.indexOf(";", 0);
        int second = string.indexOf(";", first + 1);
        int third = string.indexOf(";", second + 1);

        list.add(string.substring(0, first));
        list.add(string.substring(first + 1, second));
        list.add(string.substring(second + 1, third));
        list.add(string.substring(third + 1));

        return list;
    }

    private static void generateIncopleted() {
        InputStream is = Main.class.getResourceAsStream("people.txt");

        Scanner scanner = new Scanner(is);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();

            String[] data = line.split(";");

            String name = data[0];
            String surname = data[1];
            String height = data[2];
            String weight = data[3];

            if(Math.random() <= 0.08) {
                height = "";
            }

            if(Math.random() <= 0.5) {
                weight = "";
            }

            System.out.println(String.format("%s;%s;%s;%s",
                    name,
                    surname,
                    height,
                    weight));
        }
    }
}
