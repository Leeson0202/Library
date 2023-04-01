<!-- SecondRight -->
<template>
    <div class="SecondRight">
        <div class="card">
            <div class="all-count">
                <div
                    style="font-weight: 500; font-size: 16px; line-height: 40px"
                >
                    图书馆
                </div>
                <div style="">自从2010年以来进馆人数达</div>
                <div class="num-show">139k</div>
                共计
                <div class="num-show" style="display: inline-block">3,719k</div>
                人次
            </div>
        <div class="go"></div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "SecondRight",
    components: {},
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
.SecondRight {
    width: 100%;
    height: 100%;
    padding: 20px 10px 10px;
}

.card {
    padding: 20px 20px;
    background-color: #1076fc18;
    vertical-align: top;
    text-align: left;
}
.all-count {
    float: left;
    width: auto;
    margin-left: 40px;
}
.all-count * {
    font-size: 11px;
}
.num-show {
    font-size: 30px;
    font-weight: 500;
    color: #6ca3db;
    margin-left: 20px;
    line-height: 60px;
}
.go{
    display: inline;
    position: relative;
    float: right;
    margin-right: 50px;
    top: 40px;
    height: 100px;
    width: 100px;
    border-top: 20px solid #fff;
    border-right: 20px solid #fff;
    transform: rotate(45deg);
}
</style>
