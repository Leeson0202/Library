<!-- EditSeat -->
<template>
    <div class="EditSeat">
        <!-- menu -->
        <div class="menu">
            <div class="menu-item back" @click="back()">
                <i class="el-icon-arrow-left"></i>返回
            </div>
            <div class="menu-item room-name">{{ room.name }}</div>

            <!-- 保存 -->
            <div class="menu-item submit" @click="submit()">保存</div>
        </div>
        <div class="main">
            <div class="main-left">
                <div id="panzoom" :style="{ 'padding-top': paddingTop + 'px' }">
                    <!-- 椅子 -->
                    <img
                        class="yizi"
                        @click="onClick($event, idx)"
                        v-for="(seat, idx) in seats"
                        :key="seat.seatId"
                        :id="seat.seatId"
                        :src="require('@/assets/img/library/yizi-normal.svg')"
                        alt=""
                        :style="{
                            width: itemWidth + 'px',
                            left: seat.x * itemWidth + 'px',
                            top: paddingTop + seat.y * itemWidth + 'px',
                            '-webkit-transform':
                                'rotate(' + seat.direction * 90 + 'deg)',
                        }"
                    />
                    <!-- 桌子 -->
                    <div
                        class="zhuozi"
                        @click="onClick($event, idex)"
                        v-for="(table, idex) in tables"
                        :key="table.tableId"
                        :id="table.tableId"
                        :style="{
                            width: itemWidth + 'px',
                            height: itemWidth * 2 + 'px',
                            left:
                                (table.x + table.direction * 2) * itemWidth +
                                'px',
                            top: paddingTop + table.y * itemWidth + 'px',
                            '-webkit-transform':
                                'rotate(' + table.direction * 90 + 'deg)',
                        }"
                    ></div>
                </div>
            </div>
            <div class="main-right">
                <div class="oprate-add">
                    <el-button size="mini" round>添加椅子</el-button>
                    <el-button size="mini" round>添加桌子</el-button>
                </div>
                <div class="oprate-show" v-if="itemId != ''">
                    <div class="item">
                        <label
                            style="
                                width: 50px;
                                display: inline-block;
                                text-align: center;
                            "
                            :for="form.name"
                            >名字:</label
                        >
                        <el-input
                            style="width: calc(100% - 80px)"
                            v-model="form.name"
                        ></el-input>
                    </div>
                    <div class="item">
                        <label
                            style="
                                width: 50px;
                                display: inline-block;
                                text-align: center;
                            "
                            :for="form.name"
                            >方向:</label
                        >
                        <div
                            style="
                                display: inline-block;
                                width: calc(100% - 80px);
                                text-align: left;
                            "
                        >
                            <el-button @click="changeDirection()" size="mini"
                                >改变</el-button
                            >
                        </div>
                    </div>
                    <!-- 位置 -->
                    <div class="item">
                        <div
                            style="
                                position: relative;
                                width: 58px;
                                height: 100px;
                                display: inline-block;
                                text-align: center;
                                float: left;
                            "
                            :for="form.name"
                        >
                            <div style="float: right">位置:</div>
                        </div>
                        <div
                            style="
                                display: inline-block;
                                width: calc(100% - 80px);
                                text-align: left;
                            "
                        >
                            <el-button
                                style="margin-left: 40px"
                                size="mini"
                                @click="changePosition('y', -1)"
                            >
                                上
                            </el-button>

                            <div style="height: 6px"></div>
                            <el-button
                                size="mini"
                                @click="changePosition('x', -1)"
                            >
                                左
                            </el-button>
                            <el-button
                                style="margin-left: 30px"
                                size="mini"
                                @click="changePosition('x', 1)"
                            >
                                右
                            </el-button>
                            <div style="height: 6px"></div>
                            <el-button
                                style="margin-left: 40px"
                                size="mini"
                                @click="changePosition('y', 1)"
                            >
                                下
                            </el-button>
                        </div>
                    </div>
                    <!-- 取消 -->
                    <div class="cancel" @click="cancel()">取消</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import api from "@/utils/api";
import Panzoom from "@panzoom/panzoom";
import { Message } from "element-ui";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "EditSeat",
    components: {},
    props: ["roomId"],
    data() {
        //这里存放数据
        return {
            // 房间信息
            room: {},
            // panZoom的上白部分
            paddingTop: 0,
            // 图标宽度
            itemWidth: 100,
            // 视图宽度
            viewWidth: 0,
            // 选中id
            itemType: 0,
            itemId: "",
            // 选中表单
            form: { name: "A1001", repair: false },
            oldForm: {},
            seats: [],
            tables: [],
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        init() {
            var elem = document.getElementById("panzoom");
            const panzoom = Panzoom(elem, {
                maxScale: 3.5,
                minScale: 1,
            });
            panzoom.pan(10, 10);
            panzoom.zoom(1, { animate: true });
            elem.parentElement.addEventListener("wheel", panzoom.zoomWithWheel);
            let f = {
                target: { id: "A1227" },
            };
        },
        // 改变位置
        changePosition(tag, tt) {
            console.log("changePosition");
            this.form[tag] += tt;
        },
        // 改变方向
        changeDirection() {
            // 椅子
            this.form.direction += 1;
            if (this.itemType == 0) {
                if (this.form.direction == 4) this.form.direction = 0;
            } else {
                if (this.form.direction == 2) this.form.direction = 0;
            }
        },
        // 点击
        onClick(e, idx) {
            console.log(e.target.id, idx);
            // 获取id 修改背景色
            if (this.itemId != "") {
                document.getElementById(this.itemId).style.background = "";
            }
            if (this.itemId != e.target.id) {
                document.getElementById(e.target.id).style.background =
                    "rgba(243, 213, 222,0.7)";
                this.itemId = e.target.id;
            } else {
                this.itemId = "";
                this.form = {};
            }

            // 获取类型和form
            this.seats.forEach((el) => {
                if (el.seatId == this.itemId) {
                    this.itemType = 0;
                    this.form = el;
                    this.oldForm = { ...el };
                }
            });
            this.tables.forEach((el) => {
                if (el.tableId == this.itemId) {
                    this.itemType = 1;
                    this.form = el;
                    this.oldForm = { ...el };
                }
            });
        },
        // 取消
        cancel() {
            console.log("cancle");
            Object.keys(this.oldForm).forEach((key) => {
                this.form[key] = this.oldForm[key];
            });
        },
        // 保存
        submit() {
            api.insertOrUpdateSeat(this.seats).then((data) => {
                Message.success("座位保存成功");
            });
            api.insertOrUpdateTable(this.tables).then((data) => {
                Message.success("座位保存成功");
            });
        },

        // 获取房间信息
        queryRoom() {
            api.queryRoom(this.roomId).then((data) => {
                console.log(data);
                this.room = data.data;
                // 获取后计算宽度
                this.cacul(data.data);
            });
        },
        // 计算宽高
        cacul(data) {
            this.seats = data.librarySeats;
            this.tables = data.libraryTables;
            let maxWidth = 0;
            let maxHeight = 0;
            this.seats.forEach((el) => {
                maxWidth = el.x > maxWidth ? el.x : maxWidth;
                maxHeight = el.y > maxHeight ? el.x : maxHeight;
            });
            this.tables.forEach((el) => {
                maxWidth =
                    el.x + (el.direction + 1) > maxWidth
                        ? el.x + (el.direction + 1)
                        : maxWidth;
                maxHeight =
                    el.y + (1 - el.direction) > maxHeight
                        ? el.x(1 - el.direction)
                        : maxHeight;
            });
            let maxX = maxWidth > maxHeight ? maxWidth : maxHeight;
            // 得到width
            this.itemWidth =
                document.getElementById("panzoom").clientWidth / (maxX + 1);

            this.paddingTop =
                (document.getElementById("panzoom").clientHeight -
                    this.itemWidth * (maxHeight + 1)) /
                2;
            // console.log(this.itemWidth, this.paddingTop);
        },
        // 返回到房间管理
        back() {
            this.$emit("back");
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        this.queryRoom();
        this.init();
        // 全局监听键盘左右键事件
        var that = this;
        document.onkeyup = function (e) {
            if (e.code === "ArrowLeft") {
                console.log("arrowleft");
                that.changePosition("x", -1);
            } else if (e.code === "ArrowRight") {
                console.log("arrowright");
                that.changePosition("x", 1);
            } else if (e.code === "ArrowUp") {
                console.log("ArrowUp");
                that.changePosition("y", -1);
            } else if (e.code === "ArrowDown") {
                console.log("ArrowDown");
                that.changePosition("y", 1);
            }
        };
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
.EditSeat {
    width: 100%;
    height: 100%;
}

.menu {
    height: 40px;
    font-size: 18px;
    line-height: 30px;
    .menu-item {
        float: left;
    }
    .back {
        cursor: pointer;
        user-select: none;
    }
    .back:hover {
        font-weight: 800;
    }
    .room-name {
        margin-left: 30px;
        font-size: 16px;
        font-weight: 500;
        color: #666;
    }

    .submit {
        float: right;
        margin-right: 20px;
        width: 100px;
        font-size: 14px;
        line-height: 30px;
        padding: 0px 26px;
        background: #8ab2ed;
        border-radius: 15px;
        color: #fff;
        cursor: pointer;
        user-select: none;
    }
}
.main {
    height: calc(100% - 40px);
    width: 100%;
}
.main-left {
    float: left;
    width: calc(100% - 240px);
    height: 100%;
    background: #f2f2f2;
    #panzoom {
        margin: 5%;
        width: 90%;
        height: 90%;
        // background-color: #fff;
        .yizi {
            display: inline-block;
            position: absolute;
            left: 0;
            cursor: pointer;
        }
        .zhuozi {
            transform-origin: left top;
            display: inline-block;
            position: absolute;
            cursor: pointer;
            height: 200px;
            width: 100px;
            background: rgba(165, 218, 252, 0.9);
        }
    }
}
.main-right {
    float: right;
    height: 100%;
    width: 240px;
    background: #e6eef4;
    .oprate-add {
        padding: 20px;
        border-bottom: 1px solid #cacaca;
    }
    .oprate-show {
        margin-top: 20px;
        .item {
            margin-bottom: 10px;
        }
    }

    .cancel {
        margin: auto;
        margin-top: 30px;
        width: 140px;
        font-size: 14px;
        line-height: 30px;
        padding: 0px 26px;
        background: #ccc;
        border-radius: 15px;
        color: #fff;
        cursor: pointer;
        user-select: none;
    }
}
</style>
