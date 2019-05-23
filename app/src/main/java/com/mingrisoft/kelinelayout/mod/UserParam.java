package com.mingrisoft.kelinelayout.mod;

public class UserParam {

    private String UNAME;
    private String PWORD;

    public UserParam(String UNAME, String PWORD) {
        this.UNAME = UNAME;
        this.PWORD = PWORD;
    }

    public String getUNAME() {

        return UNAME;
    }

    public void setUNAME(String UNAME) {
        this.UNAME = UNAME;
    }

    public String getPWORD() {
        return PWORD;
    }

    public void setPWORD(String PWORD) {
        this.PWORD = PWORD;
    }
}
