<!-- ReceiveRule -->
<template>
    <div class="ReceiveRule">
        <div class="rule-show" v-if="!editTag">
            <div class="title">
                预约规则
                <div class="edit-btu" @click="toEdit">编辑</div>
            </div>
            <div class="rule-time">上次编辑时间：{{ date }}</div>
            <div v-for="(item, index) in t" :key="index" class="context">
                {{ item }}
            </div>
        </div>
        <div class="rule-edit" v-if="editTag">
            <div class="title">编辑预约规则</div>
            <div>
                <div class="rule-input">
                    <el-input
                        type="textarea"
                        :rows="26"
                        placeholder="请输入内容"
                        v-model="eContext"
                        style="line-height: 30px"
                    >
                    </el-input>
                </div>
                <div style="margin-top: 40px">
                    <el-button @click="editTag = false">取消</el-button
                    ><el-button type="primary" @click="success">保存</el-button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
//这里可以导入其他文件（比如：组件，工具js，第三方插件js，json文件，图片文件等等）
//例如：import 《组件名称》 from ‘《组件路径》‘;

export default {
    name: "ReceiveRule",
    components: {},
    props: [],
    data() {
        //这里存放数据
        return {
            t: [],
            context:
                "1、每天7：00开始，读者可以预约当日或次日的座位，预约成功后读者可以使用该座位至当日闭馆。预约系统登录用户名为学号/工号，密码为公共数据库密码。\n2、读者可通过以下三种方式预约并使用当日座位 \n(1)8：00之前预约当日座位，并在8：30之前通过门禁闸机刷卡入馆，系统即可自动完成签到。 \n(2)当日其他开放时段，在馆外通过网络预约当日座位，并在预约成功后30分钟内前往图书馆通过门禁闸机刷卡入馆，系统即可自动完成签到。 \n(3)通过图书馆门禁闸机刷卡入馆，再通过网络或现场预约机预约当日座位，预约成功后即自动完成签到。 \n3、预约次日座位的读者，需在次日8：30前通过门禁闸机刷卡入馆(系统自动完成签到)。 \n4、取消预约：读者预约次日座位后，可在次日7：00之前取消预约(不限次数)。预约当日座位后，限每天取消1次。具体操作：点击我的中心--我的预约--取消预约。 \n5、“未签到”违规：预约成功后未在第2、第3条规定时间内签到，也未在第4条规定时间内取消预约的读者将被记“未签到”违规1次。 \n6、临时离开：在选座机上刷卡或从网页或从微信端选择“临时离开”，座位将保留60分钟(中午10:30-13:00和下午16:30-19:00期间离开分别保留120分钟和90分钟，从离开时间起算)。已选择“临时离开”的读者在保留时间内返回时，通过门禁闸机入馆即自动完成签到。若选择了“临时离开”，但实际又没离馆，也需在规定时间内在选座机上刷卡或通过网页端操作完成签到。读者未在保留时间内返回签到，系统将自动释放座位供他人选用，并记“临时离开超时”违规1次。 \n7、离馆：读者每次离馆(含临时离馆)均需刷卡从门禁闸机出馆，否则将被记“离开未刷卡”违规。读者未选择“临时离开”，通过门禁闸机出馆时，座位将自动释放。 \n8、没有公共数据库账号的读者(校友等)，不能通过网上提前预约座位，请入馆后在现场选座机上选择预约座位。 \n9、请读者自觉维护馆内秩序，勿随意移动桌椅，个人物品请自行妥善保管。每天闭馆时，请带走所有个人物品(长期存包箱内物品除外)。如被清理，产生的损失由读者本人自负。",
            date: "2023-03-29",
            editTag: false,
            eContext: "",
        };
    },
    //监听属性 类似于data概念
    computed: {},
    //监控data中的数据变化
    watch: {},
    //方法集合
    methods: {
        toEdit() {
            this.editTag = true;
            this.eContext = this.context;
        },
        success() {
            this.context = this.eContext;
            this.t = this.context.split("\n");
            this.editTag = false;
        },
    },
    //生命周期 - 创建完成（可以访问当前this实例）
    created() {},
    //生命周期 - 挂载完成（可以访问DOM元素）
    mounted() {
        this.t = this.context.split("\n");
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
.ReceiveRule {
    width: 100%;
    height: 100%;
    padding: 20px 80px;
    background-color: #fff;
    overflow-y: scroll;
}
.title {
    font-size: 20px;
    line-height: 40px;
    margin-bottom: 20px;
}
.rule-show {
    padding-bottom: 80px;
    .title {
        .edit-btu {
            margin-right: 40px;
            float: right;
            color: rgb(131, 160, 240);
            cursor: pointer;
        }
    }
    .rule-time {
        text-align: left;
        font-size: 13px;
        margin-bottom: 10px;
    }
    .context {
        text-align: left;
        text-indent: 2em;
        line-height: 30px;
    }
}

.rule-edit {
    .rule-input {
        min-height: 400px;
    }
}
</style>
