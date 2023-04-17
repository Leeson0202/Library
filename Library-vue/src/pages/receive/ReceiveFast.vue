<!-- ReceiveFast -->
<template>
    <div class="ReceiveFast">
        <div class="receive-show">
            <el-empty v-if="list == []" :image-size="300"> </el-empty>
            <div v-else style="width: 100%; height: 100%">
                <el-table
                    :data="
                        list.filter(
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

                    <el-table-column
                        prop="libraryName"
                        label="图书馆"
                        width="200"
                    >
                    </el-table-column>
                    <el-table-column prop="roomName" label="房间" width="150">
                    </el-table-column>
                    <el-table-column prop="seatName" label="座位" width="150">
                    </el-table-column>

                    <el-table-column align="left" style="align-items: right">
                        <template slot="header" slot-scope="scope">
                            <el-input
                                v-model="search"
                                size="mini"
                                placeholder="输入关键字搜索"
                                style="max-width: 300px; float: left"
                                clearable=""
                            />
                        </template>
                        <template slot-scope="scope">
                            <el-switch
                                v-model="scope.row.open"
                                active-color="#13ce66"
                                inactive-color="#ccc"
                                @change="changeOpen(scope.$index, scope.row)"
                            >
                            </el-switch>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination
                    v-if="list != []"
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
import api from "@/utils/api";
import { Message } from "element-ui";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "ReceiveFast",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            pageSize: 10,
            totalElements: 0,
            pageIdx: 0,
            search: "",
            list: [],
        };
    },
    //监听属性 类似于data概念
    computed: {
        schoolId() {
            return this.$store.state.school.schoolId;
        },
    },
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        queryReceiveFastAll() {
            let form = {
                schoolId: this.schoolId,
                page: this.pageIdx,
                size: this.pageSize,
            };
            api.queryReceiveFastAll(form).then((data) => {
                console.log(data);
                this.list = data.data.content;
            });
        },
        changeOpen(idx, item) {
            api.updateReceiveFast(item).then((data) => {
                Message.success("修改成功");
            });
            console.log(item);
        },
        pageChange(val) {
            this.pageIdx = val - 1;
            this.queryReceiveFastAll();
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
                    api.deleteReceive(row.receiveId, row.userId).then(
                        (data) => {
                            Message.success("删除成功");
                            this.queryReceiveAll();
                        }
                    );
                    done();
                })
                .catch((_) => {});
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        let that = this;
        setTimeout(() => {
            that.queryReceiveFastAll();
        }, 300);
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
<style lang="less" scoped>
.ReceiveFast {
    padding: 20px;
    background-color: #fff;
    width: 100%;
    height: 100%;
}
.receive-show {
    height: calc(100% - 60px);
    width: 100%;
    .pagination {
        margin-top: 26px;
    }
}
</style>
