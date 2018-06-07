<template lang="pug">
doctype html
#system-log.match-height
  el-container
    el-header(height="auto").main-header
      h1 System Log
    el-table(:data="logList" stripe height="100%" style="width: 100%")
      
</template>

<script>
export default {
  name: 'SystemLog',
  data() {
    return {
      logList: [],
    };
  },
};
</script>

<style lang="scss" scoped>
</style>
