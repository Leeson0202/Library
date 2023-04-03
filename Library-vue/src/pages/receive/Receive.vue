<!-- Receive -->
<template>
    <div class="Receive">
        <div class="receive-nav">
            <template>
                <el-cascader
                    v-model="roomId"
                    :options="options"
                    :props="{ expandTrigger: 'hover' }"
                    @change="handleChange"
                    style="width: 30%; min-width: 360px"
                ></el-cascader>
            </template>
        </div>
        <div class="receive-show">
            <el-empty v-if="roomId == ''" :image-size="300"> </el-empty>
            <div v-else>
                <el-table
                    :data="users"
                    :stripe="true"
                    style="width: calc(100% - 30px)"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column fixed prop="name" label="姓名" width="120">
                    </el-table-column>
                    <el-table-column label="状态" width="120" align="center">
                        <template slot-scope="scope">
                            <div>
                                <div
                                    class="online"
                                    :style="{
                                        'background-color':
                                            scope.row.online == 0
                                                ? '#cfc8c8aa'
                                                : scope.row.online == 1
                                                ? '#6cf20baa'
                                                : '#f2c075aa',
                                    }"
                                >
                                    {{
                                        scope.row.online == 0
                                            ? "未入座"
                                            : scope.row.online == 1
                                            ? "已入座"
                                            : "暂离"
                                    }}
                                </div>
                            </div>
                        </template>
                    </el-table-column>

                    <el-table-column
                        prop="libraryName"
                        label="图书馆"
                        width="120"
                    >
                    </el-table-column>
                    <el-table-column prop="roomName" label="房间" width="130">
                    </el-table-column>
                    <el-table-column prop="seatName" label="座位" width="100">
                    </el-table-column>

                    <el-table-column prop="date" label="时间" width="100">
                    </el-table-column>

                    <el-table-column prop="timeIdx" label="时间" width="100">
                        <template slot-scope="scope">
                            {{
                                8 +
                                2 * scope.row.timeIdx +
                                ":00 - " +
                                (10 + 2 * scope.row.timeIdx) +
                                ":00"
                            }}
                        </template>
                    </el-table-column>

                    <el-table-column align="left" style="align-items: right">
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
                                @click="dialogFormVisible = true"
                                >修改</el-button
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
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import { color } from "echarts";

export default {
    name: "Receive",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            searchValue: "",
            roomId: "shejiyuanze",
            libraryId: "wajhvcgwavc",
            options: [
                {
                    value: "avajhgvwab",
                    label: "老图书馆",
                    children: [
                        {
                            value: "sheji5yuanze",
                            label: "一楼电脑室",
                        },
                        {
                            value: "she3jiyuanze",
                            label: "二楼电脑室",
                        },
                    ],
                },
                {
                    value: "wajhvcgwavc",
                    label: "数字图书馆",
                    children: [
                        {
                            value: "shejiyuanze",
                            label: "一楼阅览室",
                        },
                        {
                            value: "shejiy5uanze",
                            label: "二楼阅览室",
                        },
                    ],
                },
            ],
            users: [
                {
                    userId: "afgyawu",
                    name: "Leeson",
                    online: 0,
                    libraryId: "jadbchw",
                    libraryName: "数字图书馆",
                    roomId: "asucbw",
                    roomName: "一楼阅览室",
                    seatId: "A1001",
                    seatName: "A1001",
                    date: "2023-3-11",
                    timeIdx: "0",
                },
                {
                    userId: "afgyawu",
                    name: "Leeson",
                    online: 1,
                    libraryId: "jadbchw",
                    libraryName: "数字图书馆",
                    roomId: "asucbw",
                    roomName: "一楼阅览室",
                    seatId: "A1001",
                    seatName: "A1001",
                    date: "2023-3-11",
                    timeIdx: "0",
                },
                {
                    userId: "afgyawu",
                    name: "Leeson",
                    online: 2,
                    libraryId: "jadbchw",
                    libraryName: "数字图书馆",
                    roomId: "asucbw",
                    roomName: "一楼阅览室",
                    seatId: "A1001",
                    seatName: "A1001",
                    date: "2023-3-11",
                    timeIdx: "0",
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
        handleChange(value) {
            this.libraryId = value[0];
            this.roomId = value[1];
            console.log(value);
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
        addLibrary() {},
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
.Receive {
    padding: 20px;
    height: 100%;
    background-color: #fff;
    overflow-y: scroll;
}
.receive-nav {
    padding: 10px;
    height: 60px;
    width: 100%;
    display: block;
    text-align: left;
}
.receive-show {
    height: 100%;
    width: 100%;
}
.online {
    margin: auto;
    border-radius: 10px;
    width: 70px;
    font-size: 10px;
}
</style>
