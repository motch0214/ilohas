<template lang="pug">
#menu
  el-menu
    el-menu-item(index="1") Welcome
    el-menu-item(index="2") Market Data
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
</style>
