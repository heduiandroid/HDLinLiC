package com.linli.consumer.domain;

/**
 * 用户信息实体
 * Created by Administrator on 2016/1/12.
 */
public class  User {
    private String id;
    private String username;
    private String password;
    private String nickName;
    private String realName;
    private String sex;
    private String birthday;//生日
    private String phone;
    private String qq;
    private String myAddress;
    private Integer totalPoints;//总积分
    private Integer usePoints;//可用积分
    private String headPath;//头像地址
    private Double balance;//余额
    private Integer userType;//用户类型
    private String userCity;//用户所在城市
    private String email;
    private Integer regionId;
    private Long orderNo;
    private String thirdWXid;
    private String inviteCode;
    private String idCardNum;
    private Long shopId;//用于判断是否扫描了某个网咖的标识
    //即时通讯条数记录备用
    private Integer unreadMessageCount;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                ", totalPoints='" + totalPoints + '\'' +
                ", usePoints='" + usePoints + '\'' +
                ", headPath='" + headPath + '\'' +
                ", balance=" + balance +
                ", userType=" + userType +
                '}';
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }

    public String getThirdWXid() {
        return thirdWXid;
    }

    public void setThirdWXid(String thirdWXid) {
        this.thirdWXid = thirdWXid;
    }
    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getRegionId() {
        return regionId;
    }

    public void setRegionId(Integer regionId) {
        this.regionId = regionId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    public String getMyAddress() {
        return myAddress;
    }

    public void setMyAddress(String myAddress) {
        this.myAddress = myAddress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getUsePoints() {
        return usePoints;
    }

    public void setUsePoints(Integer usePoints) {
        this.usePoints = usePoints;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getHeadPath() {
        return headPath;
    }

    public void setHeadPath(String headPath) {
        this.headPath = headPath;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
