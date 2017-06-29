// homepage/scanner/scanner.js
var app = getApp();


Page({
  /**
   * 页面的初始数据
   */
  data: {
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var adminAccount = wx.getStorageSync("adminAccount");
    this.setData({
      adminAccount: adminAccount
    })

    //获取token  
    var token = wx.getStorageSync('token') || {};
    if (!token.access_token || (token.expires_in < Date.now())) {
      wx.login({
        success: function (res) {
          // success  
          var url = 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=' + data.appId + '&secret=' + data.appSecret;
          wx.request({
            url: url,
            data: {},
            method: 'GET',
            success: function (res) {
              var obj = {};
              obj.access_token = res.data.access_token;
              obj.expires_in = Date.now() + res.data.expires_in * 1000 - 600000; //提前十分钟更新
              wx.setStorageSync('token', obj); //存储token
            }
          });
        }
      });
    }
  },
  
  /**
   * 扫描
   */
  scan: function (event) {
    var that = this;
    var orderInfo;
    wx.scanCode({
      success: function (res) {
        orderInfo = res.result;
        that.setData({
          orderInfo: orderInfo,
        }),
          /**
           * 后台请求数据
           */
          wx.request({
            url: app.globalData.ServerUrl + 'getShopCarOrdersByOrderIds',
            data: {
              orderIds: orderInfo
            },
            method: "GET",
            success: function (res) {
              console.log(res)
              var booklist = res.data.list;
              var operate = res.data.operate;
              that.setData({
                booklist: booklist,
                operate: operate
              })
            }
          })
      }
    })
  },

  /**
   * 确认
   */
  confirm: function (event) {
    var orderInfo = this.data.orderInfo;  //订单信息
    var adminAccount = wx.getStorageSync("adminAccount"); //管理员工号
    var operate = this.data.operate; //获取操作类型

    console.log("确认");
    console.log(operate);
    var book = "";
    var booklist = this.data.booklist;
    for (var i = 0; i < booklist.length; i++) {
      book += booklist[i].title+"\n";
    }
    console.log(book);

    var that = this;
    if(operate == "借阅图书"){
      wx.request({
        url: app.globalData.ServerUrl + 'borrowbook',
        data: {
          orderInfo:orderInfo,
          adminAccount : adminAccount
        },
        success:function(res){
            
            wx.showToast({
              title: '',
              icon: 'success'
            }),
            that.setData({
              booklist: "",
              operate: ""
            })
        }
      })
    } else if(operate == "归还图书"){
      wx.request({
        url: app.globalData.ServerUrl + 'returnbook',
        data:{
          orderInfo: orderInfo,
          adminAccount: adminAccount
        },
        success:function(res){
          console.log("成功"),
          wx.showToast({
            title: '',
            icon:'success'
          }),
          that.setData({
            booklist:"",
            operate: ""
          })
        }
      })
    }
   
  },
  /**
   * 退出登录
   */
  logout: function (event) {
    wx.setStorageSync("adminAccount", "");
    wx.redirectTo({
      url: '/welcomepage/login/login',
    })
  }
})