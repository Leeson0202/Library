<!-- Home -->
<template>
    <div class="Home" id="home">
        <!-- 上半部分 -->
        <div class="first-content">
            <!-- 上左 -->
            <div class="first-left">
                <div class="first-left-title"><FirstLeftTitle /></div>
                <div class="first-left-body"><FirstLeftBody /></div>
                <div class="first-left-end"><FirstLeftEnd /></div>
            </div>
            <!-- 上右 -->
            <div class="first-right"><FirstRight /></div>
        </div>

        <!-- 下半部分 -->
        <div class="second-content">
            <div class="second-left">
                <SecondLeft />
            </div>
            <div class="second-right">
                <SecondRight />
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import FirstLeftTitle from "./first/FirstLeftTitle.vue";
import FirstLeftBody from "./first/FirstLeftBody.vue";
import FirstLeftEnd from "./first/FirstLeftEnd.vue";
import FirstRight from "./first/FirstRight.vue";
import SecondLeft from "./second/SecondLeft.vue";
import SecondRight from "./second/SecondRight.vue";

export default {
    name: "Home",
    components: {
        FirstLeftTitle,
        FirstLeftBody,
        FirstLeftEnd,
        FirstRight,
        SecondLeft,
        SecondRight,
    },
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
.Home {
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
}

.first-content {
    width: 100%;
    height: 67%;
    text-align: left;
    .first-left {
        height: 100%;
        width: calc(100% - 300px);
        float: left;
        .first-left-title {
            width: 100%;
            height: 70px;
            // background-color: #fff;
        }
        .first-left-body {
            width: 100%;
            height: calc((100% - 70px) * 0.45);
            // background-color: #fff;
        }
        .first-left-end {
            width: 100%;
            height: calc((100% - 70px) * 0.55);
            background-color: #eee;
        }
    }
    .first-right {
        height: 100%;
        width: 300px;
        float: left;
        padding: 20px 10px 0;
        // background-color: #fff;
    }
}

.second-content {
    width: 100%;
    height: 33%;
    .second-left {
        float: left;
        height: 100%;
        width: 60%;
        // background-color: #fff;
    }
    .second-right {
        float: left;
        height: 100%;
        width: 40%;
    }
}
</style>
