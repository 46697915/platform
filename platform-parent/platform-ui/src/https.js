import axios from 'axios'
import qs from 'qs'

axios.defaults.timeout = 5000;                        //响应时间
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';        //配置请求头
axios.defaults.baseURL = '';   //配置接口地址
//默认当axios进行跨域请求的时候是不会携带Cookies的
//设置vueAxios.defaults.withCredentials的值为true则跨域的时候会带上cookie
axios.defaults.withCredentials = true;
//将axios存储在全局的VUe中然后就可以用this.的方式去掉用
//Vue.prototype.$axios=axios

//POST传参序列化(添加请求拦截器)
axios.interceptors.request.use((config) => {
    //在发送请求之前做某件事
    if(config.method  === 'post'){
        config.data = qs.stringify(config.data);
    }
    return config;
},(error) =>{
    console.log('错误的传参')
    return Promise.reject(error);
});

//返回状态判断(添加响应拦截器)
axios.interceptors.response.use((res) =>{
    //对响应数据做些事
    if(!res.data.success){
        return Promise.resolve(res);
    }
    return res;
}, (error) => {
    console.log('网络异常')
    return Promise.reject(error);
});

//返回一个Promise(发送post请求)
export function fetchPost(url, params) {
    return new Promise((resolve, reject) => {
        axios.post(url, params)
            .then(response => {
                resolve(response);
            }, err => {
                reject(err);
            })
            .catch((error) => {
                reject(error)
            })
    })
}
////返回一个Promise(发送get请求)
export function fetchGet(url, param) {
    return new Promise((resolve, reject) => {
        axios.get(url, {params: param})
            .then(response => {
                resolve(response)
            }, err => {
                reject(err)
            })
            .catch((error) => {
                reject(error)
            })
    })
}
export default {
    fetchPost,
    fetchGet,
}
/**
 * 实例如下：
 import https from '../https.js' 　　// 注意用自己的路径

 // 请求后台数据==================
 loginPost: function () {
                let params ={'username': 'admin', 'password': 'admin123', 'rememberMe': 'true','isMobile':'1'}
                https.fetchPost('/login',params ).then((data) => {
                    this.base.token = data.data.token　　　　
                    // console.log("this.base.tokenthis.base.token",this.base.token)
                    this.indexPost2(this.rres)
                }).catch(err=>{
                        console.log(err)
                    }
                )
            },
 indexPost2:function (date) {
                var this_ = this
                this_.check = false
                var jobj ={data:{'menuDate': date,'token':this.base.token}}
                let string  = JSON.stringify(jobj)
                let params = {dailyInfo:string}
                https.fetchPost('/meals/mobile/getDailyMenuByDate', params)
                .then((data) => {
                    this_.base.indexData = data
                    this_.check = true
                    // console.log('thenthenthenthen',data)
                })
                .catch((err)=>{
                    console.log(err)

                })
            },
 // ================================================
 },
 */
