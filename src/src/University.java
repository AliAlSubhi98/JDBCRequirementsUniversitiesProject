package src;

import java.io.Serializable;

public class University implements Serializable{
    String name;
    String country;
    String state_province; 
    String[] domains;
    String[] web_pages;
    String alpha_two_code;
   
    @Override
    public String toString() {
        return name + " (" + country + ", " + state_province + ")";
    }
}

