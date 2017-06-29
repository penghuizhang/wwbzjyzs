package cn.wechat.oa.domain;

public class User {
	/**
	 * 用户录入的信息
	 */
	private String uid; // 用户ID
	private String uname; // 用户名
	private String telephone; // 电话
	private String pwd; // 登录密码
	private String email; // 邮箱
	private int age; // 年龄
	/**
	 * 微信获取数据
	 */
	private String avatarUrl; // 用户微信头像
	private String nickName; // 昵称
	private String gender; // 性别 0未知 1男 2女
	private String city; // 用户所在城市
	private String province; // 用户所在省份
	private String country; // 用户所在国家
	
	private String openid; //微信用户的openid

	
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		System.out.println(
							uid + " " 
						+ uname + " " 
						+ telephone + " " 
						+ pwd + " " 
						+ avatarUrl + " " 
						+ nickName + " " 
						+ email + " "
						+ gender + " " 
						+ age + " " 
						+ city + " " 
						+ province + " " 
						+ country + " "
						+ openid );
		return null;
	}

}
