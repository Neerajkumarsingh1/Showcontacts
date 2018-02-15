package samplesharedpreferences.com.showcontacts;

/**
 * Created by neeraj on 12/2/18.
 */

public class ContactCardDeta {

    private String ContactName;
    private String ContactNumber;

    private String ContactImage;

    public ContactCardDeta(String contactName, String contactNumber) {

    }

    public ContactCardDeta() {


    }

    public String getContactImage() {
        return ContactImage;
    }

    public void setContactImage(String contactImage) {
        ContactImage = contactImage;
    }

    public String getContactName() {
        return ContactName;
    }

    public void setContactName(String contactName) {
        ContactName = contactName;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }
}