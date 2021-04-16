package cn.gsdx.Springmvc.entity;

/**
 * @author JackWen
 */
public class User {
    private String username;
    private String age;
    private String gender;

    /**
     * 级联属性
     */
    private Address address;

    public User(){}

    public User(String username, String age, String gender, Address address) {
        this.username = username;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age='" + age + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                '}';
    }
}
