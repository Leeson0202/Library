<!-- Header -->
<template>
    <div class="Header">
        <div class="web-title">Leeson Library</div>

        <div class="avatar-block">
            <img
                v-if="hasUserInfo == true"
                class="avatar"
                :src="userInfo.avatarUrl"
                alt="头像"
            />
            <router-link v-if="hasUserInfo == false" :to="{ name: 'login' }">
                <div class="login-text">登录</div>
            </router-link>
            <div class="hover-avatar">
                <div>我的资料</div>
                <div>退出登陆</div>
            </div>
        </div>

        <div class="school-block">
            <div v-if="hasSchool" style="display: inline">重庆邮电大学</div>
            <div v-if="!hasSchool" style="display: inline; cursor: pointer">
                请绑定学校
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {};
    },
    //监听属性 类似于data概念
    computed: {
        userInfo() {
            return this.$store.state.user.userInfo;
        },
        hasUserInfo() {
            return this.$store.state.user.hasUserInfo;
        },
        hasSchool() {
            return this.$store.state.school.hasSchool;
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {},
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {},
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
.Header {
    display: block;
    margin: auto;
    width: 100%;
    transition: 0.4s linear;
}
@media screen and (min-width: 1400px) {
    .Header {
        width: 90%;
        transition: 0.4s linear;
    }
}

@media screen and (min-width: 1720px) {
    .Header {
        width: 80%;
        transition: 0.4s linear;
    }
}
.web-title {
    float: left;
    margin-left: 20px;
    font-size: 20px;
    font-weight: 600;
    color: #fff;
}
.school-block {
    display: inline;
    width: 100px;
    float: right;
    margin-right: 20px;
    color: #ddd;
    font-weight: 600;
}
.avatar-block {
    position: relative;
    margin-top: 10rpx;
    margin-right: 30px;
    float: right;

    .avatar {
        position: relative;
        margin-top: 7px;
        height: 46px;
        border-radius: 50%;
    }

    .login-text {
        float: right;
        width: 50px;
        cursor: pointer;
    }
    .hover-avatar {
        display: none;
        width: 100px;
        background-color: #fff;
    }
}
</style>
