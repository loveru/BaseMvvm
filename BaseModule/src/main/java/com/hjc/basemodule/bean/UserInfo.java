package com.hjc.basemodule.bean;

import java.io.Serializable;

/**
 * @Describe: 登录之后的用户信息
 * @Author: huangjc
 * @Email: 252431193@qq.com
 * @Date: 2018/3/19
 */


public class UserInfo implements Serializable {


    /**
     * code : 200
     * msg : 成功
     * sign : null
     * serialNo : null
     * data : {"userInfo":{"custComPanyName":"皇朝有限公司","realName":"朱国治","custId":13,"custAddress":"北京市东城区景山前街4号紫禁城乾清宫","userType":0,"userName":"13605154259","custName":"苏苏111","userId":3},"token":{"access_token":"6571f18d-119f-49a2-af63-30ca38a264a3","token_type":"bearer","refresh_token":"f75db734-3d17-4f8c-a22d-6c8d17d100f8","expires_in":257790,"scope":"all"}}
     */

    public int code;
    public String msg;
    public Object sign;
    public Object serialNo;
    public DataBean data;


    public static class DataBean implements Serializable {
        /**
         * userInfo : {"custComPanyName":"皇朝有限公司","realName":"朱国治","custId":13,"custAddress":"北京市东城区景山前街4号紫禁城乾清宫","userType":0,"userName":"13605154259","custName":"苏苏111","userId":3}
         * token : {"access_token":"6571f18d-119f-49a2-af63-30ca38a264a3","token_type":"bearer","refresh_token":"f75db734-3d17-4f8c-a22d-6c8d17d100f8","expires_in":257790,"scope":"all"}
         */

        public UserInfoBean userInfo;
        public TokenBean token;
        public RoleInfoBean roleInfo;


        public static class UserInfoBean implements Serializable {
            /**
             * custComPanyName : 皇朝有限公司
             * realName : 朱国治
             * custId : 13
             * custAddress : 北京市东城区景山前街4号紫禁城乾清宫
             * userType : 0
             * userName : 13605154259
             * custName : 苏苏111
             * userId : 3
             */

            public String custComPanyName;
            public String realName;
            public String custId;
            public String dotCode;
            public String dotId;
            public String branchId;
            public String dotName;
            public String custAddress;
            public int userType;
            public String userName;
            public String userPhone;
            public String custName;
            public int userId;
            public String custCode;
            public double tax;
            public double serviceCharge;
            public String userEmail;
            public String userBirthday;
            public String headUrl;


            @Override
            public String toString() {
                return "UserInfoBean{" +
                        "custComPanyName='" + custComPanyName + '\'' +
                        ", realName='" + realName + '\'' +
                        ", custId=" + custId +
                        ", custAddress='" + custAddress + '\'' +
                        ", userType=" + userType +
                        ", userName='" + userName + '\'' +
                        ", custName='" + custName + '\'' +
                        ", userId=" + userId +
                        '}';
            }
        }

        public static class TokenBean implements Serializable {
            /**
             * access_token : 6571f18d-119f-49a2-af63-30ca38a264a3
             * token_type : bearer
             * refresh_token : f75db734-3d17-4f8c-a22d-6c8d17d100f8
             * expires_in : 257790
             * scope : all
             */

            public String access_token;
            public String token_type;
            public String refresh_token;
            public int expires_in;
            public String scope;

            @Override
            public String toString() {
                return "TokenBean{" +
                        "access_token='" + access_token + '\'' +
                        ", token_type='" + token_type + '\'' +
                        ", refresh_token='" + refresh_token + '\'' +
                        ", expires_in=" + expires_in +
                        ", scope='" + scope + '\'' +
                        '}';
            }
        }

        public static class RoleInfoBean implements Serializable {
            /**
             * roleId : 16
             * rolePid : 17
             * orgCode : Bec0860123456789
             * roleName : Supervisor
             * roleCode : 2
             * roleStatus : 0
             * delStatus : 0
             * recordTime : null
             * entryUserId : 1
             * operationUserId : 1
             * operationTime : 1529405587623
             */

            public int roleId;
            public int rolePid;
            public String orgCode;
            public String roleName;
            public String roleCode;
            public int roleStatus;
            public int delStatus;
            public Object recordTime;
            public int entryUserId;
            public int operationUserId;
            public long operationTime;
        }

    }
}

