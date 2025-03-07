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
                <i class="el-icon-refresh" @click="queryReceiveAll()"></i>
            </template>
        </div>
        <div class="receive-show">
            <el-empty v-if="roomId == ''" :image-size="300"> </el-empty>
            <div v-else style="width: 100%; height: 100%">
                <el-table
                    :data="
                        receives.filter(
                            (data) =>
                                !search ||
                                data.nickName
                                    .toLowerCase()
                                    .includes(search.toLowerCase())
                        )
                    "
                    :stripe="true"
                    style="width: calc(100% - 30px)"
                    @selection-change="handleSelectionChange"
                >
                    <el-table-column type="selection" width="55">
                    </el-table-column>
                    <el-table-column
                        fixed
                        prop="nickName"
                        label="姓名"
                        width="120"
                    >
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
                                                : '#f2c060',
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
                    <el-table-column prop="roomName" label="房间" width="90">
                    </el-table-column>
                    <el-table-column prop="seatName" label="座位" width="70">
                    </el-table-column>

                    <el-table-column prop="date" label="日期" width="80">
                    </el-table-column>

                    <el-table-column prop="timeIdx" label="时间" width="100">
                        <template slot-scope="scope">
                            {{
                                8 +
                                2 * scope.row.timeIdx +
                                ":00-" +
                                (10 + 2 * scope.row.timeIdx) +
                                ":00"
                            }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="time" label="创建时间" width="90">
                        <template slot-scope="scope">
                            {{ scope.row.time.slice(0, 19) }}
                        </template>
                    </el-table-column>

                    <el-table-column align="left" style="align-items: right">
                        <template slot="header">
                            <el-input
                                v-model="search"
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
                <el-pagination
                    v-if="roomId != ''"
                    class="pagination"
                    background
                    layout="prev, pager, next"
                    :page-size="pageSize"
                    :total="totalElements"
                    @current-change="pageChange"
                >
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;


import { Message } from "element-ui";

export default {
    name: "Receive",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            pageSize: 10,
            totalElements: 0,
            pageIdx: 0,
            search: "",
            roomId: "",
            libraryId: "",
            receives: [],
        };
    },
    //监听属性 类似于data概念
    computed: {
        options() {
            return this.$store.state.school.options;
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        handleChange(value) {
            this.libraryId = value[0];
            this.roomId = value[1];
            this.pageIdx = 0;
            this.queryReceiveAll();
        },
        pageChange(val) {
            this.pageIdx = val - 1;
            this.queryReceiveAll();
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        handleEdit(index, row) {
            console.log(index, row);
        },
        handleDelete(index, row) {
            this.$confirm("确定删除？")
                .then((_) => {
                    this.$api.ReservationService.deleteReceive(row.receiveId, row.userId).then(
                        (data) => {
                            Message.success("删除成功");
                            this.queryReceiveAll();
                        }
                    );
                    done();
                })
                .catch((_) => {});
        },
        // 查询
        queryReceiveAll() {
            if (this.roomId == null || this.roomId == "") return;
            let form = {
                roomId: this.roomId,
                page: this.pageIdx,
                size: this.pageSize,
            };
            this.$api.ReservationService.queryReceiveAll(form).then((data) => {
                this.receives = data.data.content;
                data.data.content.forEach((el) => {
                    el.time = new Date(el.time).toLocaleString();
                });
                this.totalElements = data.data.totalElements;
            });
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
    .el-icon-refresh {
        position: relative;
        margin-left: 20px;
        top: 6px;
        font-size: 30px;
        font-weight: 500;
        color: #999;
        cursor: pointer;
    }
}
.receive-show {
    height: calc(100% - 60px);
    width: 100%;
    .pagination {
        margin-top: 26px;
    }
}
.online {
    margin: auto;
    border-radius: 10px;
    width: 70px;
    font-size: 10px;
}
</style>
