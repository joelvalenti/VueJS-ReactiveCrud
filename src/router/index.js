import Vue from "vue";
import VueRouter from "vue-router";
import Productos from "../views/Productos.vue";
import Home from "../views/Home.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/productos",
    name: "Productos",
    component: Productos
  },
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/ubicacion",
    name: "DondeEstamos",
    //esta forma permite lazy-load es decir que la carga del html + el java script se cargue al
    //momento de llamar a la pagina, si lo hacemos como en Home, se carga al iniciar la aplicación
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/DondeEstamos.vue")
  },
  {
    path: "/detalle/:id",
    name: "DetalleInstrumento",
    component: () => import("../views/DetalleInstrumento.vue")
  },
  {
    path: "/abm",
    name: "ABM",
    component: () => import("../views/ABM.vue")
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
