package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Client {

    private String name;
    private String password;
    private String m_strTIN;
    private String m_strTelephone;
    private String m_strEmail;
    private List<PostalAddress> m_lstAddresses = new ArrayList<PostalAddress>();

    public Client(String strName, String strTIN, String strTelephone, String strEmail) {
        if ((strName == null) || (strTIN == null) || (strTelephone == null)
                || (strEmail == null) || (strName.isEmpty()) || (strTIN.isEmpty())
                || (strTelephone.isEmpty()) || (strEmail.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.name = strName;
        this.m_strEmail = strEmail;
        this.m_strTIN = strTIN;
        this.m_strTelephone = strTelephone;
    }

    public Client(String strName, String strTIN, String strTelephone, String strEmail, PostalAddress adress) {
        if ((strName == null) || (strTIN == null) || (strTelephone == null)
                || (strEmail == null) || (adress == null)
                || (strName.isEmpty()) || (strTIN.isEmpty()) || (strTelephone.isEmpty())
                || (strEmail.isEmpty())) {
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        }

        this.name = strName;
        this.m_strEmail = strEmail;
        this.m_strTIN = strTIN;
        this.m_strTelephone = strTelephone;
        m_lstAddresses.add(adress);
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return this.m_strEmail;
    }

    public String getTIN() {
        return this.m_strTIN;
    }

    public String getTelephone() {
        return this.m_strTelephone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean hasEmail(String strEmail) {
        return this.m_strEmail.equalsIgnoreCase(strEmail);
    }
    
    public boolean hasTIN(String tin) {
        return this.m_strTIN.equalsIgnoreCase(tin);
    }

    public boolean addPostalAddress(PostalAddress address) {
        return this.m_lstAddresses.add(address);
    }

    private boolean validateAdress(PostalAddress address) {
        return true;
    }

    public boolean associateNewAddress(PostalAddress address) {
        if (validateAdress(address)) {
            return addPostalAddress(address);
        }
        return true;
    }

    public boolean removePostalAddress(PostalAddress address) {
        return this.m_lstAddresses.remove(address);
    }

    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/

        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Client obj = (Client) o;
        return (Objects.equals(m_strEmail, obj.m_strEmail) || Objects.equals(m_strTIN, obj.m_strTIN));
    }

    @Override
    public String toString() {
        String str = String.format("%s - %s - %s - %s", this.name, this.m_strTIN, this.m_strTelephone, this.m_strEmail);
        for (PostalAddress address : this.m_lstAddresses) {
            str += "\nAddress:\n" + address.toString();
        }
        return str;
    }

//    public String toStringPostalAddresses() {
//        String str = "";
//        for (PostalAddress add : this.m_lstAddresses) {
//            if (!"".equals(str)) {
//                str += ";";
//            }
//            str += (add.getAddress() + "/" + add.getZipCode().toString() + "/" + add.getTown());
//        }
//        return str;
//    }

    public static PostalAddress newPostalAddress(String strAddress, String strZipCode, String strTown) {
        return new PostalAddress(strAddress, strZipCode, strTown);
    }

    public List<PostalAddress> getPostalAddresses() {
        return m_lstAddresses;
    }

    /**
     * Searches and returns a postal address equal to the one sent by parameter.
     *
     * @param addr
     * @param zipCode
     * @param town
     * @return
     */
    public PostalAddress getPostalAddress(String addr, ZipCode zipCode, String town) {
        PostalAddress test = new PostalAddress(addr, zipCode, town);
        for (PostalAddress postAddr : m_lstAddresses) {
            if (postAddr.equals(test)) {
                return postAddr;
            }
        }
        return null;
    }

}
