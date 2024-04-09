package spark.india.numerologysolutions;

public class UserData {

    private String userName;
    private String dateOfBirth;
    private String Gender;
    private Integer userImage;

    public UserData(String userName, String dateOfBirth, Integer userImage, String Gender) {
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.userImage = userImage;
        this.Gender = Gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public Integer getUserImage() {
        return userImage;
    }

    public void setUserImage(Integer userImage) {
        this.userImage = userImage;
    }
}