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
// 组件
import App from "./App.vue";

Vue.use(ElementUI);

Vue.config.productionTip = false;

new Vue({
    store,
    router,
    render: (h) => h(App),
}).$mount("#app");
