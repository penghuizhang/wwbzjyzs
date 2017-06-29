var app = getApp();
var md5 = require("../../data/md5.js")
Page({
  data: {
    toast1Hidden: true,
    modalHidden: true,
    modalHidden2: true,
    notice_str: '',
    index: 0,
    isImg: false,
  },


  /**表单提交 */
  formSubmit: function (e) {
    var that = this;
    var formData = e.detail.value;
    /**
     * 检验信息是否完整
     */
    if (formData.telephone === "") {
      wx.showToast({
        title: '手机号为空！',
        image: "/icon/warning.png",
      })
      return;
    } else if (formData.pwd === "" || formData.pwd2 === "") {
      wx.showToast({
        title: '密码为空！',
        image: "/icon/warning.png",
      })
      return;
    } else if (!(formData.pwd === formData.pwd2)) {
      wx.showToast({
        title: '两次输入密码不同！',
        image: "/icon/warning.png",
      })
      return;
    } else if (formData.email === "") {
      wx.showToast({
        title: '邮箱输入错误为空！',
        image: "/icon/warning.png",
      })
      return;
    } else {
      var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
      if (!myreg.test(formData.email)) {
        wx.showToast({
          title: '邮箱错误！',
          image: "/icon/warning.png",
        })
        return;
      }
    }
    /** 
     * 获取用户微信数据
    */
    var userInfo = wx.getStorageSync("userInfo");
    var user = wx.getStorageSync("user");
    var gender = '男';
    if(userInfo.gender == 0)
    {
      gender = '女';
    }
    /**
     * 组装用户信息
     */
    var user = {
      telephone:formData.telephone,
      uname: formData.uname,
      pwd:md5.hexMD5(formData.pwd),
      age:formData.age,
      email:formData.email,
      openid:user.openid,
      nickName:userInfo.nickName,
      gender:gender,
      city:userInfo.city,
      province:userInfo.province,
      country:userInfo.country,
      avatarUrl:userInfo.avatarUrl
    }

    /**
     * 将用户的信息提交到后台
     */
    wx.request({
      url: app.globalData.ServerUrl + 'register',
      data: user,
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded;charset=UTF-8'
      },
      success: function (res) {
        var flag = res.data.flag;
        var message = res.data.message;
        if (flag == "success") {
          wx.showToast({
            title: '注册成功！',
            icon:'success'
          })
          wx.redirectTo({
            url: '/login/login/login',
          })
        } else {
          wx.showToast({
            title: '注册失败！',
            image: "/icon/warning.png",
            duration: 2000
          })
        }
      },
    })
  },

})