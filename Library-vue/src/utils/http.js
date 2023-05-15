/****   http.js   ****/
// 导入axios
import axios from "axios";
// 导入封装好的axios实例
import request from "./request";
import { data } from "jquery";

const http = {
    /**
     * methods: 请求
     * @param url 请求地址
     * @param params 请求参数
     */
    get(url, params) {
        if (url[0] != "/") url = "/" + url;
        const config = {
            method: "get",
            url: url,
        };
        if (params) config.params = params;
        return request(config);
    },

    post(url, params) {
        if (url[0] != "/") url = "/" + url;
        const config = {
            method: "post",
            url: url,
        };
        if (params) config.data = params;
        return request(config);
    },

    put(url, params) {
        if (url[0] != "/") url = "/" + url;
        const config = {
            method: "put",
            url: url,
        };
        if (params) config.params = params;
        return request(config);
    },

    delete(url, params) {
        if (url[0] != "/") url = "/" + url;
        const config = {
            method: "delete",
            url: url,
        };
        if (params) config.params = params;
        return request(config);
    },
    // 上传
    upload(url, form) {
        const config = {
            headers: {
                token: window.localStorage.getItem("token"),
                "Content-Type": "multipart/form-data;charset=utf-8",
            },
        };
        // console.log(config);
        return new Promise((resolve, reject) => {
            axios
                .post(
                    url,
                    (data = form),
                    config
                )
                .then((res) => {
                    if (res.data.code === 200) {
                        resolve(res.data);
                    }
                });
        });
    },
};
//导出
export default http;
