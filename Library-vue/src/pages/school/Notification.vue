<!-- Notification -->
<template>
    <div class="Notification">
        <div class="operate">
            <el-button
                type="danger"
                size="mini"
                @click="clearFilter"
                style="margin-left: 10px"
                >删除
            </el-button>

            <el-button
                size="mini"
                style="margin-left: 16px"
                @click="clearFilter"
                >添加
            </el-button>
        </div>

        <el-table
            :data="tableData"
            :stripe="true"
            style="width: calc(100% - 40px); margin: 0 20px"
            @selection-change="handleSelectionChange"
        >
            <el-table-column type="selection" width="55"> </el-table-column>
            <el-table-column fixed label="日期" width="230">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span style="margin-left: 10px">{{ scope.row.date }}</span>
                </template>
            </el-table-column>
            <el-table-column prop="name" label="管理员" width="120">
            </el-table-column>

            <el-table-column label="标题" width="380">
                <template slot-scope="scope">
                    <el-popover trigger="hover" placement="top">
                        <p>{{ scope.row.context }}</p>
                        <div slot="reference" class="name-wrapper">
                            {{ scope.row.title }}
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column
                label="已读"
                prop="view"
                width="100"
            ></el-table-column>
            <el-table-column align="right" style="align-items: right">
                <template slot="header">
                    <el-input
                        v-model="searchValue"
                        size="mini"
                        placeholder="输入关键字搜索"
                        style="max-width: 300px; float: left"
                    />
                </template>
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        @click="handleEdit(scope.$index, scope.row)"
                        >编辑</el-button
                    >
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "Notification",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            searchValue: "",
            tableData: [
                {
                    id: 210813,
                    date: "2023-04-02 13:00:00",
                    name: "王小虎",
                    title: "关于劳动节闭馆通知",
                    context:
                        "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。",
                    view: "23",
                },
                {
                    id: 211813,
                    date: "2022-04-02 13:00:00",
                    name: "王小虎",
                    title: "关于劳动节闭馆通知",
                    context:
                        "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。",
                    view: "2333",
                },
                {
                    id: 210833,
                    date: "2021-04-02 13:00:00",
                    name: "王小虎",
                    title: "关于劳动节闭馆通知",
                    context:
                        "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。",
                    view: "8033",
                },
                {
                    id: 210853,
                    date: "2020-04-02 13:00:00",
                    name: "王阿姨",
                    title: "关于劳动节闭馆通知",
                    context:
                        "劳动节放假通知，本馆将于10.1正式放假5天，放假时间为10.1-10.5，10.6正常开馆。",
                    view: "1333",
                },
            ],
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        toggleSelection(rows) {
            if (rows) {
                rows.forEach((row) => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        clearFilter(e, c) {},
        search(e) {},
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            console.log(index, row);
        },
    },
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
    user-select: text;
    line-height: 20px;
}
.Notification {
    padding: 20px 0;
    height: 100%;
    background-color: #fff;

    .operate {
        height: 50px;
        padding-left: 10px;
    }
}

.el-button {
    float: left;
}
</style>
