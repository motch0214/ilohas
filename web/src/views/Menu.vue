<template lang="pug">
#menu
  el-menu(router=true, :default-active="this.$route.path")
    el-menu-item(index="/hello", :router="this.$router") Welcome
    el-menu-item(index="/market", :router="this.$router") Market data
</template>

<script>
export default {
  name: 'Menu',
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
#menu,
.el-menu {
  height: 100%;
}

.is-active {
  font-weight: bold;
}
</style>
