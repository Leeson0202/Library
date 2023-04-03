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
            <div class="go">
                <img
                    :src="require('@/assets/img/library.jpg')"
                    alt=""
                    style="height: 160px"
                />
            </div>
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
    // background-color: #1076fc18;
    background-color: #fff;
    vertical-align: top;
    text-align: left;
}
.all-count {
    position: relative;
    float: left;
    width: auto;
    margin-left: 20px;
    height: 140px;
    top: calc(50% - 70px);
}
.all-count * {
    font-size: 11px;
}
.num-show {
    font-size: 30px;
    font-weight: 500;
    color: #6ca3db;
    margin-left: 20px;
    margin-top: 10px;
    line-height: 30px;
}
.go {
    float: right;
    position: relative;
    margin-right: 20px;
    float: right;
    height: 160px;
    width: 160px;
    top: calc(50% - 80px);
}
</style>
