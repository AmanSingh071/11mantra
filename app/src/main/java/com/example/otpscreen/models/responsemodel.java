package com.example.otpscreen.models;

public class responsemodel {
/*
   {"ascendant":"Taurus","ascendant_lord":"Venus","Varna":"Kshatriya","Vashya":"Chatuspad","Yoni":"Ashwa","Gan":"Dev","Nadi":"Adi","SignLord":"Mars","sign":"Aries","Naksahtra":"Ashwini","NaksahtraLord":"Ketu","Charan":3,"Yog":"Dand","Karan":"Vanija","Tithi":"Krishna Shashthi","yunja":"Poorva","tatva":"Fire","name_alphabet":"Cho","paya":"Gold"}
*/
    String ascendant;
    String ascendant_lord;
    String Varna;
    String Vashya;
    public responsemodel(){

    }

    public responsemodel(String ascendant, String ascendant_lord, String varna, String vashya) {
        this.ascendant = ascendant;
        this.ascendant_lord = ascendant_lord;
        Varna = varna;
        Vashya = vashya;
    }

    public String getAscendant() {
        return ascendant;
    }

    public void setAscendant(String ascendant) {
        this.ascendant = ascendant;
    }

    public String getAscendant_lord() {
        return ascendant_lord;
    }

    public void setAscendant_lord(String ascendant_lord) {
        this.ascendant_lord = ascendant_lord;
    }

    public String getVarna() {
        return Varna;
    }

    public void setVarna(String varna) {
        Varna = varna;
    }

    public String getVashya() {
        return Vashya;
    }

    public void setVashya(String vashya) {
        Vashya = vashya;
    }
}
