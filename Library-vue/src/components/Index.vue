<!-- Index -->
<template>
    <div class="Index">
        <el-container style="height: 100%">
            <el-header><Header></Header></el-header>
            <el-container id="main-container">
                <el-aside width="180px"><Aside></Aside></el-aside>
                <el-container>
                    <el-main>
                        <router-view></router-view>
                    </el-main>
                </el-container>
            </el-container>
        </el-container>
    </div>
</template>

<script>
import AsideVue from "./Aside.vue";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;
import Header from "./Header.vue";
import Aside from "./Aside.vue";

export default {
    name: "Index",
    components: { Header, Aside },
    props: [],
    data() {
        //这里存放数据
        return {};
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {},
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        // 获取用户和学校信息
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
<style scoped>
.Index {
    height: 100%;
    width: 100%;
}
#main-container {
    width: 100%;
    transition: 0.4s linear;
}
@media screen and (min-width: 1920px) {
    #main-container {
        width: 90%;
        transition: 0.4s linear;
    }
}
@media screen and (min-width: 2222px) {
    #main-container {
        width: 80%;
        transition: 0.4s linear;
    }
}
.el-header {
    background-color: #2d72cca1;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #d3dce6;
    color: #333;
    text-align: center;
    line-height: 200px;
    overflow-x: hidden;
    overflow: -Scroll;
    padding-bottom: 200px;
    border-right: 1px solid #d3dce6 !important;
}

.el-main {
    margin: 0;
    padding: 7px;
    background-color: #e9eef3;
    height: 100%;
    color: #333;
    text-align: center;
    line-height: 360px;
}

.el-container {
    height: 100%;
    margin: auto;
}
</style>
