// welcome/welcome.js
Page({
  data: {},
  onLoad: function (options) {
    
  },
 
  bindViewTap: function () {
    wx.redirectTo({
      url: '/welcomepage/login/login',
    })
  }
})