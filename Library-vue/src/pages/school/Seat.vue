<!-- Seat -->
<template>
    <div class="Seat">
        <!-- 房间页面 -->
        <div v-if="!editRoomTag">
            <!-- menu -->
            <div class="menu-block">
                <template>
                    <el-select
                        v-model="libraryId"
                        clearable
                        placeholder="请选择"
                    >
                        <el-option
                            v-for="item in libraryList"
                            size="mini"
                            :key="item.value"
                            :label="item.label"
                            :value="item.value"
                        >
                        </el-option>
                    </el-select>
                    <el-button
                        style="margin-left: 16px"
                        @click="editRoom(-1, null)"
                        >添加
                    </el-button>
                </template>
            </div>
            <!-- main -->
            <div class="main-block">
                <el-empty v-if="libraryId == ''" :image-size="200"> </el-empty>
                <div v-else>
                    <el-table
                        :data="rooms"
                        :stripe="true"
                        style="width: calc(100% - 30px)"
                        @selection-change="handleSelectionChange"
                    >
                        <el-table-column type="selection" width="55">
                        </el-table-column>
                        <el-table-column
                            fixed
                            prop="name"
                            label="房间名"
                            width="160"
                        >
                        </el-table-column>

                        <el-table-column prop="descs" label="描述" width="350">
                        </el-table-column>

                        <el-table-column
                            prop="seatNum"
                            label="座位数量"
                            align="center"
                            width="160"
                        >
                        </el-table-column>

                        <el-table-column
                            align="left"
                            style="align-items: right"
                        >
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
                                    @click="editRoom(scope.$index, scope.row)"
                                    >编辑</el-button
                                >
                                <el-button
                                    size="mini"
                                    type="primary"
                                    @click="editSeat(scope.$index, scope.row)"
                                    >编辑座位</el-button
                                >
                                <el-button
                                    size="mini"
                                    type="danger"
                                    @click="
                                        handleDelete(scope.$index, scope.row)
                                    "
                                    >删除</el-button
                                >
                            </template>
                        </el-table-column>
                    </el-table>
                </div>
            </div>

            <!-- 对话框 -->
            <el-dialog title="编辑" :visible.sync="dialogFormVisible">
                <el-form :model="form" style="width: 90%">
                    <el-form-item label="图书馆" :label-width="formLabelWidth">
                        <el-select
                            v-model="form.libraryId"
                            placeholder="请选择图书馆"
                            style="width: 100%"
                        >
                            <el-option
                                v-for="item in libraryList"
                                size="mini"
                                :key="item.value"
                                :label="item.label"
                                :value="item.value"
                            >
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="房间名" :label-width="formLabelWidth">
                        <el-input
                            v-model="form.name"
                            autocomplete="off"
                        ></el-input>
                    </el-form-item>
                    <el-form-item label="描述" :label-width="formLabelWidth">
                        <el-input
                            type="textarea"
                            rows="5"
                            v-model="form.descs"
                            autocomplete="off"
                        ></el-input>
                    </el-form-item>
                </el-form>
                <!-- 下方确定按钮 -->
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false"
                        >取 消</el-button
                    >
                    <el-button type="primary" @click="submitRoom"
                        >保 存</el-button
                    >
                </div>
            </el-dialog>
        </div>
        <!-- 座位编辑 -->
        <EditSeat v-if="editRoomTag" :roomId="roomId" @back="back"></EditSeat>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

import api from "@/utils/api";
import { Message } from "element-ui";
import EditSeat from "./EditSeat.vue";

export default {
    name: "Seat",
    components: { EditSeat },
    props: [],
    data() {
        //这里存放数据
        return {
            searchValue: "",
            libraryId: "",
            rooms: [],
            // 对话框
            dialogFormVisible: false,
            formIdx: -1,
            // 对话框数据
            form: {},
            formLabelWidth: "80px",
            editRoomTag: false,
            roomId: "",
        };
    },
    //监听属性 类似于data概念
    computed: {
        libraryList() {
            let libraryList = [];
            if (this.$store.state.school.libraries == null) return null;
            this.$store.state.school.libraries.forEach((el) => {
                libraryList.push({ value: el.libraryId, label: el.name });
            });
            return libraryList;
        },
    },
    //监控data中的数据变化
    watch: {
        libraryId(newV, oldV) {
            let that = this;
            if (newV != null || newV != "") {
                that.queryLibrary();
            }
        },
    },
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
        // 编辑房间信息
        editRoom(index, row) {
            this.formIdx = index;
            if (index != -1) this.form = { ...row };
            else
                this.form = {
                    libraryId: "",
                    roomId: "",
                    name: "",
                    descs: "",
                    seatNum: "",
                };
            this.dialogFormVisible = true;
        },
        // 编辑座位
        editSeat(index, row) {
            this.roomId = row.roomId;
            this.editRoomTag = true;
        },
        // 返回到房间管理
        // 子组件调用
        back() {
            this.editRoomTag = false;
        },
        // 删除
        handleDelete(index, row) {
            this.$confirm("确定删除" + row.name + "？")
                .then((_) => {
                    api.deleteRoom(row.roomId).then((data) => {
                        this.queryLibrary();
                    });
                    done();
                })
                .catch((_) => {});
        },
        // 提交
        submitRoom() {
            let that = this;
            if (this.formIdx == -1) {
                //新的
                // console.log(this.form);
                api.insertRoom(this.form).then((data) => {
                    that.submitSuccess(data);
                });
            } else {
                //更新
                api.updateRoom(this.form).then((data) => {
                    that.submitSuccess(data);
                });
            }
        },
        // 提交成功
        submitSuccess(data) {
            // console.log(data);
            if (data.code == 200) {
                this.dialogFormVisible = false;
                Message.success("保存成功");
                this.queryLibrary();
            }
        },
        // 获取图书馆信息
        queryLibrary() {
            let that = this;
            api.queryLibrary(this.libraryId).then((data) => {
                // console.log(data);
                if (data.code == 200) {
                    that.rooms = data.data.libraryRooms;
                }
            });
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        this.libraryId = "jdgchvauajkuvbh";
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
.Seat {
    padding: 20px;
    height: 100%;
    background-color: #fff;
    overflow-y: scroll;
}
.menu-block {
    display: block;
    height: 50px;
    width: 100%;
    text-align: left;
}
.main-block {
    width: 100%;
}
</style>
