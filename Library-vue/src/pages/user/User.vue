<!-- User -->
<template>
    <div class="User">
        <div style="float: left">
            <el-button type="danger">删除</el-button>
            <el-button @click="addUser()">添加</el-button>
            <el-button>批量添加</el-button>
        </div>
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
                        label="用户名"
                        width="120"
                    >
                    </el-table-column>

                    <el-table-column prop="phoneNum" label="手机号" width="200">
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱" width="300">
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
                            <el-button
                                size="mini"
                                @click="handleEdit(scope.$index, scope.row)"
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

        <!-- 对话框 -->
        <el-dialog title="编辑" :visible.sync="dialogFormVisible">
            <el-form :model="form" style="width: 90%">
                <el-form-item label="用户名" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.nickName"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item label="电话" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.phoneNum"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
                <el-form-item label="描述" :label-width="formLabelWidth">
                    <el-input
                        v-model="form.email"
                        autocomplete="off"
                    ></el-input>
                </el-form-item>
            </el-form>
            <!-- 下方确定按钮 -->
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitUser">保 存</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>

import { Message } from "element-ui";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "User",
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
            // 对话框
            dialogFormVisible: false,
            formIdx: -1,
            // 对话框数据
            form: {},
            formLabelWidth: "80px",
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
        // 保存修改
        submitUser() {
            if (this.form.userId == null) {
                this.$api.UserService.insertUser(this.form).then((data) => {
                    Message.success("添加成功");
                    this.dialogFormVisible = false;
                    this.queryUserAll();
                });
            } else {
                this.$api.UserService.updateUser(this.form).then((data) => {
                    Message.success("修改成功");
                    this.dialogFormVisible = false;
                    this.queryUserAll();
                });
            }
        },
        // 获取所有用户
        queryUserAll() {
            this.$api.UserService.queryUserAll(this.schoolId).then((data) => {
                this.list = data.data;
            });
        },
        // 页面改版
        pageChange(val) {
            this.pageIdx = val - 1;
            this.queryUserAll();
        },
        // 选择变化
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        addUser() {
            this.dialogFormVisible = true;
            this.form = { nickName: "", phoneNum: "", email: "" };
        },
        handleEdit(index, row) {
            this.dialogFormVisible = true;
            this.form = { ...row };
        },
        handleDelete(index, row) {
            let that = this;
            this.$confirm("确定删除？")
                .then((_) => {
                    console.log("yes");
                    //
                    this.$api.UserService.deleteUser(row.userId).then((data) => {
                        Message.success("删除成功");
                        that.queryUserAll();
                    });
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
        setTimeout(() => that.queryUserAll(), 300);
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
.User {
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
