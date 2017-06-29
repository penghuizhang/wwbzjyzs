// loginpages/login/login.js
var app = getApp();
var md5 = require("../../data/md5.js");
Page({
  data: {
  },

  onLoad: function (options) {
    if(wx.getStorageSync("adminAccount")){
      wx.redirectTo({
        url: '/homepage/scanner/scanner',
      })
    }
  },

  formsubmit: function (e) {
    var that = this;
    var account = e.detail.value.account;
    var password = e.detail.value.password;
    password = md5.hexMD5(password)
    if (account == "") {
      wx.showToast({
        title: '账号为空！',
        image: "/images/warning.png"
      })
      return;
    } else if (password == "") {
      wx.showToast({
        title: '密码为空！',
        image: "/images/warning.png"
      })
      return;
    }
    wx.request({
      url: app.globalData.ServerUrl + 'loginAdmin',
      data: {
        adminAccount: account,
        adminPwd: password,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json'
      }, // 设置请求的 header
      success: function (res) {
        if (res.data.flag == "success") {
          that.setData({
            islogin: true
          })
          wx.setStorageSync("adminAccount", account);
          wx.showToast({
            title: '提交成功',
            icon: 'success',
            duration: 2000
          }),
            wx.reLaunch({
              url: '/homepage/scanner/scanner',
            })
        } else {
          wx.showToast({
            title: '登录失败',
            image: "/images/warning.png",
            duration: 2000
          })
        }
      },
    })
  },
})