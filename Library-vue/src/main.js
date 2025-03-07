import Vue from "vue";
// 引入 less
import "@/assets/css/theme.less";
import "@/assets/css/common.css";

//引入store
import store from "./store";
// router
import router from "./router";
// element
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import plugin from "./plugins/plugin";
// 组件
import App from "./App.vue";
import * as api from '@/utils/services'

Vue.use(plugin); // 使用插件
Vue.use(ElementUI);

Vue.config.productionTip = false;

Vue.prototype.$api = api;

new Vue({
    store,
    router,
    render: (h) => h(App),
}).$mount("#app");
