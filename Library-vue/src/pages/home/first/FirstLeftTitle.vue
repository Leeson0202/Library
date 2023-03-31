<!-- FirstLeftTitle -->
<template>
    <div class="FirstLeftTitle">
        <div
            style="
                font-size: 36px;
                font-weight: 500;
                color: #666;
                margin-top: 0px;
                display: inline-block;
                position: relative;
                top: 6px;
                margin-right: 20px;
            "
        >
            CQUPT
        </div>
        <div style="display: inline-block">
            <el-input
                class="search-block"
                placeholder="请输入内容"
                v-model="input"
                clearable
            >
            </el-input>

            <el-button
                type="primary"
                icon="el-icon-search"
                style="margin-left: 20px"
                >搜索</el-button
            >

            <el-button
                type="primary"
                icon="el-icon-share"
                style="margin-left: 20px"
            ></el-button>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "FirstLeftTitle",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return { input: "" };
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
.FirstLeftTitle {
    width: 100%;
    height: 100%;
    padding: 10px 20px;
    user-select: none;
}

.search-block {
    width: 500px;
}
</style>
