// loginpages/login/login.js
var app = getApp();
var md5 = require("../../data/md5.js");
Page({
  data: {
    timestamp: "",
    islogin: "false"
  },

  onLoad: function (options) {
    var timestamp = wx.getStorageSync('timestamp');
    if (timestamp != "") {
      wx.redirectTo({
        url: '/user/userhome/userhome'
      })
    }
  },
  /**
   * 表单提交
   */
  formsubmit: function (e) {
    var that = this;
    var telephone = e.detail.value.telephone;
    var password = md5.hexMD5(e.detail.value.password);
   
    if (telephone == "") {
      wx.showToast({
        title: '手机号为空！',
        image: "/icon/warning.png",
      })
      return;
    } else if (password === "") {
      wx.showToast({
        title: '密码为空！',
        image: "/icon/warning.png",
      })
      return;
    }
    /**
     * 提交登录信息
     */
    wx.request({
      url: app.globalData.ServerUrl + 'loginUser',
      data: {
        telephone: telephone,
        pwd: password,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      }, // 设置请求的 header
      success: function (res) {
        if (res.data.flag === "success") {
          wx.setStorageSync("uid", res.data.uid); //存储返回的用户ID
          wx.reLaunch({
            url: '/user/userhome/userhome?uid=' + res.data.uid,
          })
        } else {
          wx.showToast({
            title: '账号或密码错误！',
            image: "/icon/warning.png",
          })
        }
      },
    })
  },
  /**
   * 跳转到注册页面
   */
  register: function (){
    wx.navigateTo({
      url: '/login/register/register',
    })
  }
})