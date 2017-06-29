// library/libraryhome/libraryhome.js
var app = getApp();
var homedatas = require("../../data/homedata.js")

Page({
  data: {
    contentShow: true,
    searchShow: false,
    searchKey: "",

  },
  onLoad: function (options) {
    var data = app.globalData.wxData;//这里存储了appid、secret、token串 
    // //获取token  
    // var token = wx.getStorageSync('token') || {};
    // if (!token.access_token || (token.expires_in < Date.now())) {
    //   wx.login({
    //     success: function (res) {
    //       // success  
    //       var url = 'https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=' + data.appId + '&secret=' + data.appSecret;
    //       wx.request({
    //         url: url,
    //         data: {},
    //         method: 'GET',
    //         success: function (res) {
    //           var obj = {};
    //           obj.access_token = res.data.access_token;
    //           obj.expires_in = Date.now() + res.data.expires_in * 1000 - 600000; //提前十分钟更新
    //           wx.setStorageSync('token', obj); //存储token
    //         }
    //       });
    //     }
    //   });
    // }

    //获取openid  
    var user = wx.getStorageSync('user') || {};
    if (!user.openid || (user.expires_in < Date.now())) {

      wx.login({
        success: function (res) {
          // success  
          var url = 'https://api.weixin.qq.com/sns/jscode2session?appid=' + data.appId + '&secret=' + data.appSecret + '&js_code=' + res.code + '&grant_type=authorization_code';
          
          wx.request({
            url: url,
            data: {},
            method: 'GET',
            success: function (res) {
              console.log(res)
              var obj = {};
              obj.openid = res.data.openid;
              obj.expires_in = Date.now() + res.data.expires_in * 1000 - 600000; //提前十分钟更新
              obj.session_key = res.data.session_key;
              console.log(obj);
              wx.setStorageSync('user', obj);//存储openid  
            },
            fail: function(res){
              console.log(res)
            },
            complate: function(res){
              console.log(res)
            }
          });
        }
      });
    }
    /**获取用户信息 */
    wx.getUserInfo({
      success: function (res) {
        wx.setStorageSync("userInfo", res.userInfo);
      }
    })

    /**初始化搜索记录缓存 */
    var history = wx.getStorageSync('history');
    if (history) {
    } else {
      var history = new Array();
      wx.setStorageSync('history', history);
    };

    /**获取分类数据 */
    this.setData({
      homeData: homedatas.categoryList,
    });
  },
  /**二维码扫描 */
  scan: function () {
    wx.scanCode({
      success: function (res) {
        // success
        var key = res.result; //得到扫描结果
        wx.navigateTo({
          url: '/library/searchshow/searchshow?key=' + key,
        })
      },
    })
  },

  /**书类点击事件 */
  categoryTap: function (event) {
    var category = event.currentTarget.dataset.category;
    wx.navigateTo({
      url: '/library/categorybooks/booklist?category=' + category,
    })
  },

  /**搜索栏聚焦 */
  onBindFocus: function (event) {
    var history = wx.getStorageSync('history');
    this.setData({
      contentShow: false,
      searchShow: true,
      history: history,
    })
  },

  /** 取消图标点击事件 */
  onCancelImgTap: function (event) {
    this.setData({
      contentShow: true,
      searchShow: false,
    })
  },

  /**搜索栏失去光标事件 */
  onBindBlur: function (event) {
    var text = event.detail;
    if (!text) {
      return;
    } else {
      this.setData({
        searchKey: text,
      });
      console.log(text);
      var history = new Array();
      history = wx.getStorageSync("history");
      history.push(text);
      wx.setStorageSync('history', history);
      this.setData({
        history: history,
      })
    }

  },

  /**点击搜索图标事件 */
  search: function (event) {
    var key = this.data.searchKey.value;
    if (key) {
      wx.navigateTo({
        url: '/library/searchshow/searchshow?key=' + key,
      })
    }
  },

  /**清除所有的搜索记录 */
  clearRecord: function (event) {
    var history = new Array();
    wx.setStorageSync('history', history);
    this.setData({
      history: history,
    })
  },

  /**点击推荐书籍 */
  bookTap: function (event) {
    var bookid = event.currentTarget.dataset.id;
    wx.navigateTo({
      url: '/library/bookinformation/bookInformation?bookid=' + bookid,
    })
  }

})