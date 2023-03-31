<!-- Login -->
<template>
    <div class="Login">
        <div class="card login-block">
            <div class="banner">Leeson</div>
            <div v-if="loginMethod == 0" class="line-item">
                <el-image
                    class="label-icon"
                    :src="require('@/assets/img/tel.svg')"
                ></el-image>
                <input
                    class="tel-input"
                    type="text"
                    v-model="tel"
                    placeholder="请输入手机号"
                />
            </div>
            <div v-if="loginMethod == 1" class="line-item">
                <el-image
                    class="label-icon"
                    :src="require('@/assets/img/email.svg')"
                ></el-image>
                <input
                    class="tel-input"
                    type="email"
                    v-model="email"
                    placeholder="请输入邮箱"
                />
            </div>
            <div class="line-item">
                <input
                    class="yanz"
                    type="text"
                    v-model="code"
                    placeholder="输入验证码"
                />
                <div class="button timer" @click="login">
                    {{ codeButtonText }}
                </div>
            </div>

            <div class="submit" @click="confirm">登陆</div>
            <div class="change-login">
                <div
                    class="change-button"
                    v-if="loginMethod == 0"
                    @click="changeMethod(1)"
                >
                    切换为邮箱登陆
                </div>
                <div
                    class="change-button"
                    v-if="loginMethod == 1"
                    @click="changeMethod(0)"
                >
                    切换为手机号登陆
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;
import api from "@/utils/api";
import { Message } from "element-ui";

export default {
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            loginMethod: 0,
            tel: null,
            email: null,
            code: null,
            codeButtonText: "获取验证码",
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        // 获取验证码
        login() {
            let that = this;
            if (this.loginMethod == 0 && this.tel != null && this.tel != "") {
                api.loginTel(this.tel).then((data) => {
                    this.loginSuccess(data);
                });
            } else if (
                this.loginMethod == 1 &&
                this.email != null &&
                this.email != ""
            ) {
                api.loginEmail(this.email).then((data) => {
                    this.loginSuccess(data);
                });
            } else {
                Message.error(
                    "请输入" + (this.loginMethod == 0 ? "手机号" : "邮箱")
                );
                return;
            }

            var t = 60;
            that.codeButtonText = "" + t + "s";
            var timer = setInterval(function () {
                if (--t < 0) {
                    clearInterval(timer);
                }
                that.codeButtonText = "" + t + "s";
            }, 1000);
        },
        loginSuccess(data) {
            if (data.code == 200) {
                Message.success("发送验证码成功");
            }
        },
        changeMethod(value) {
            this.loginMethod = value;
        },
        confirm() {
            // test登陆
            if (this.tel == "test" || this.email == "test") {
                api.loginTest().then((data) => {
                    this.confirmSuccess(data);
                });
                return;
            }
            // 正常登陆
            if (this.code == null) {
                Message.warning("请输入手机号和验证码");
                return;
            }
            if (this.loginMethod == 0) {
                api.confirmTel(this.tel, this.code).then((data) => {
                    this.confirmSuccess(data);
                });
            } else if (this.loginMethod == 1) {
                api.confirmEmail(this.email, this.code).then((data) => {
                    this.confirmSuccess(data);
                });
            }
        },
        confirmSuccess(data) {
            // console.log(data);
            if (data.code != 200) {
                Message.error("登陆失败");
                return;
            }
            Message.success("登陆成功");
            this.$store.commit("updateLogin", true);
            window.localStorage.setItem("token", data.token);
            this.$router.replace({
                path: "/",
            });
        },
        init() {
            if (this.$store.dispatch("Launch")) {
                // 登陆成功
                this.$router.replace({ path: "/" });
            }
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        setTimeout(this.init, 200);
    },
    beforeCreate() {}, //生命周期 - 创建之前
    beforeMount() {}, //生命周期 - 挂载之前
    beforeUpdate() {}, //生命周期 - 更新之前
    updated() {}, //生命周期 - 更新之后
    beforeDestroy() {}, //生命周期 - 销毁之前
    destroyed() {}, //生命周期 - 销毁完成
    activated() {}, //如果页面有keep-alive缓存功能，这个函数会触发
};
</script>
<style lang="less" scoped>
.Login {
    display: block;
    position: relative;
    padding: 10px;
    height: 100%;
    width: 100%;
    background-color: #2d72cca1;
    user-select: none;
}
.line-item {
    margin: 10px 0;
}
.login-block {
    position: absolute;
    left: calc(50% - 170px);
    top: calc(50% - 210px);
    width: 340px;
    height: auto;
    border-radius: 10px;
    padding: 10px;
    background-color: #fff;
    .banner {
        margin-bottom: 20px;
        line-height: 60px;
        font-size: 25px;
        font-weight: 500;
        border-bottom: 1px solid #eee;
        text-align: center;
    }
    .label-icon {
        display: inline-block;
        position: absolute;
        width: 30px;
        top: 96px;
        left: 32px;
        user-select: none;
    }
    .tel-input {
        display: inline-block;
        margin-left: 5%;
        padding-left: 40px;
        width: 90%;
    }
    .yanz {
        display: inline-block;
        margin-left: 5%;
        width: 176px;
        padding-left: 10px;
    }
    .timer {
        width: 100px;
        float: right;
        margin-right: 5%;
        text-align: center;
    }
    .submit {
        margin: auto;
        margin-top: 20px;
        width: 90%;
        height: 36px;
        line-height: 36px;
        text-align: center;
        background: #3370ffc0;
        color: #fff;
        border-radius: 6px;
        cursor: pointer;
    }
    .change-login {
        margin: 10px 0;

        text-align: right;
        .change-button {
            font-size: 13px;
            display: inline;
            height: 30px;
            line-height: 30px;
            padding: 0 16px;
            color: rgb(7, 95, 248);
            cursor: pointer;
        }
    }
}
</style>
