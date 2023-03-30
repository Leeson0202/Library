<!-- Aside -->
<template>
    <div class="Aside">
        <el-menu
            :default-openeds="['1']"
            background-color="transparent"
            active-text-color="#2d72cc"
        >
            <el-menu-item index="0">
                <img
                    class="label-icon"
                    :src="require('@/assets/img/home.svg')"
                    alt=""
                />
                首页
            </el-menu-item>
            <el-submenu index="1">
                <template slot="title">
                    <img
                        class="label-icon"
                        :src="require('@/assets/img/school.svg')"
                        alt=""
                    />学校管理
                </template>
                <el-menu-item index="1-1">通知管理</el-menu-item>
                <el-menu-item index="1-2">图书馆管理</el-menu-item>
                <el-menu-item index="1-3">座位管理</el-menu-item>
            </el-submenu>
            <el-submenu index="2">
                <template slot="title">
                    <i class="el-icon-date"></i>预约管理
                </template>
                <el-menu-item index="2-1">预约管理</el-menu-item>
                <el-menu-item index="2-2">预约查询</el-menu-item>
                <el-menu-item index="2-3">选项2</el-menu-item>
            </el-submenu>

            <el-submenu index="3">
                <template slot="title">
                    <i class="el-icon-bank-card"></i>信用管理
                </template>
                <el-menu-item index="3-1">信用查询</el-menu-item>
                <el-menu-item index="3-2">用户查询</el-menu-item>
            </el-submenu>
            <el-submenu index="4">
                <template slot="title">
                    <i class="el-icon-setting"></i>设置
                </template>
                <el-menu-item index="3-1">我的信息</el-menu-item>
                <el-menu-item index="3-2">学校绑定</el-menu-item>
            </el-submenu>
        </el-menu>
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
* {
    text-align: left;
    color: #000;
    user-select: none;
}
.label-icon {
    position: relative;
    display: inline-block;
    padding: 0 3px;
    margin-right: 5px;
    width: 24px;
}

//子级的类名

.el-menu-item:hover {
    background-color: #eee !important;
}

.el-submenu /deep/ .el-submenu__title:hover {
    background-color: #eee !important;
}
</style>
