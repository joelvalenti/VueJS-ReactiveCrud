<template>
  <div id="app">
    <div id="nav">
      <top-nav-bar></top-nav-bar>
    </div>
    <router-view />
  </div>
</template>

<script>
import NavBarTop from "@/components/NavBarTop.vue";
export default {
  components: {
    "top-nav-bar": NavBarTop
  }
};
</script>
