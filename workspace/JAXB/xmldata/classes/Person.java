import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {

    private int age;
    private String name;
    private Address address;

    public int getAge() {
        return age;
    }

    public void setAge(int value) {
        this.age = value;
    }


    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address value) {
        this.address = value;
    }

}
