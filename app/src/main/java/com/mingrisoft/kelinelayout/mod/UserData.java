package com.mingrisoft.kelinelayout.mod;

public class UserData {

    /**
     * result : {"code":100,"msg":"登录成功"}
     * data : {"list":{"USERID":"c0c8fcd891c3a1e8ebc2faf32db87515","USERNAME":"手机用户c9ydf","NICKNAME":"手机用户c9ydf","ICON":"","SEX":2,"MOBILE":"15555182321","HASPASSWORD":true,"REGISTER":0,"REALUID":"1042694","USERNUM":"22014310","UIDTOKEN":"2cbc28051679aea201e79fe6a9f2dbc0"}}
     */

    private ResultBean result;
    private DataBean data;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class ResultBean {
        /**
         * code : 100
         * msg : 登录成功
         */

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    public static class DataBean {
        /**
         * list : {"USERID":"c0c8fcd891c3a1e8ebc2faf32db87515","USERNAME":"手机用户c9ydf","NICKNAME":"手机用户c9ydf","ICON":"","SEX":2,"MOBILE":"15555182321","HASPASSWORD":true,"REGISTER":0,"REALUID":"1042694","USERNUM":"22014310","UIDTOKEN":"2cbc28051679aea201e79fe6a9f2dbc0"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * USERID : c0c8fcd891c3a1e8ebc2faf32db87515
             * USERNAME : 手机用户c9ydf
             * NICKNAME : 手机用户c9ydf
             * ICON :
             * SEX : 2
             * MOBILE : 15555182321
             * HASPASSWORD : true
             * REGISTER : 0
             * REALUID : 1042694
             * USERNUM : 22014310
             * UIDTOKEN : 2cbc28051679aea201e79fe6a9f2dbc0
             */

            private String USERID;
            private String USERNAME;
            private String NICKNAME;
            private String ICON;
            private int SEX;
            private String MOBILE;
            private boolean HASPASSWORD;
            private int REGISTER;
            private String REALUID;
            private String USERNUM;
            private String UIDTOKEN;

            public String getUSERID() {
                return USERID;
            }

            public void setUSERID(String USERID) {
                this.USERID = USERID;
            }

            public String getUSERNAME() {
                return USERNAME;
            }

            public void setUSERNAME(String USERNAME) {
                this.USERNAME = USERNAME;
            }

            public String getNICKNAME() {
                return NICKNAME;
            }

            public void setNICKNAME(String NICKNAME) {
                this.NICKNAME = NICKNAME;
            }

            public String getICON() {
                return ICON;
            }

            public void setICON(String ICON) {
                this.ICON = ICON;
            }

            public int getSEX() {
                return SEX;
            }

            public void setSEX(int SEX) {
                this.SEX = SEX;
            }

            public String getMOBILE() {
                return MOBILE;
            }

            public void setMOBILE(String MOBILE) {
                this.MOBILE = MOBILE;
            }

            public boolean isHASPASSWORD() {
                return HASPASSWORD;
            }

            public void setHASPASSWORD(boolean HASPASSWORD) {
                this.HASPASSWORD = HASPASSWORD;
            }

            public int getREGISTER() {
                return REGISTER;
            }

            public void setREGISTER(int REGISTER) {
                this.REGISTER = REGISTER;
            }

            public String getREALUID() {
                return REALUID;
            }

            public void setREALUID(String REALUID) {
                this.REALUID = REALUID;
            }

            public String getUSERNUM() {
                return USERNUM;
            }

            public void setUSERNUM(String USERNUM) {
                this.USERNUM = USERNUM;
            }

            public String getUIDTOKEN() {
                return UIDTOKEN;
            }

            public void setUIDTOKEN(String UIDTOKEN) {
                this.UIDTOKEN = UIDTOKEN;
            }
        }
    }
}
