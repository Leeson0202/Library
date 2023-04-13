<!-- EditSeat -->
<template>
    <div class="EditSeat">
        <!-- menu -->
        <div class="menu">
            <div class="menu-item back" @click="back()">
                <i class="el-icon-arrow-left"></i>返回
            </div>
            <div class="menu-item room-name">{{ room.name }}</div>
            <div class="menu-item submit">保存</div>
        </div>
        <div class="main">
            <div class="main-left"></div>
            <div class="main-right">
                <div class="oprate-add">
                    <el-button size="mini" round>添加椅子</el-button>
                    <el-button size="mini" round>添加桌子</el-button>
                </div>
                <div class="oprate-show">
                    <div class="item">
                        <div class="item-name">{{ form.name }}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import api from "@/utils/api";
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "EditSeat",
    components: {},
    props: ["roomId"],
    data() {
        //这里存放数据
        return {
            room: {},
            form: { name: "A1001", repair: false },
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        // 获取房间信息
        queryRoom() {
            api.queryRoom(this.roomId).then((data) => {
                console.log(data);
                this.room = data.data;
            });
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
    height: 30px;
    font-size: 18px;
    line-height: 30px;
    padding-bottom: 6px;
    border-bottom: 1px solid rgb(210, 208, 208);
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
        font-size: 14px;
        padding: 0px 26px;
        background: #8ab2ed;
        border-radius: 15px;
        color: #fff;
    }
}
.main {
    padding-top: 10px;
    height: calc(100% - 10px);
    width: 100%;
}
.main-left {
    float: left;
    width: calc(100% - 240px);
    height: 100%;
    background: #ccc;
}
.main-right {
    height: 100%;
    background: #e6eef4;
    .oprate-add {
        padding: 20px;
        border-bottom: 1px solid #cacaca;
    }
}
</style>
