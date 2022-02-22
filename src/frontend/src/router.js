import Vue from "vue";
import Router from "vue-router";
Vue.use(Router);
export default new Router({
  mode: "history",
  routes: [
    {
      path: "/itens",
      alias: "/",
      name: "itens",
      component: () => import("./components/ItensList")
    },
    {
      path: "/itens/:id",
      name: "item-details",
      component: () => import("./components/Item")
    },
    {
      path: "/add",
      name: "add",
      component: () => import("./components/AddItem")
    },
    {
      path: "/about",
      name: "about",
      component: () => import("./components/About")
    }
  ]
});