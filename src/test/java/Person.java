public class Person implements Comparable {
    String name;
    int age;

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person person = (Person)o;
        if(this.age < person.age) {
            return -1;
        } else if(this.age > person.age) {
            return 1;
        } else {
            return 0;
        }
    }
}
