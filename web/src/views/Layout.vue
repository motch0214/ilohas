<template lang="pug">
#layout
  el-container
    el-header.title-header(height="auto")
      h1 ilohas
    el-container
      el-aside(width="200px")
        menu-list
      el-container
        el-header.breadcrumb(height="auto")
          el-breadcrumb(separator-class="el-icon-arrow-right")
            el-breadcrumb-item(v-for="item in this.$route.meta.breadcrumbs")
              span {{ item.title }}
        el-main
          router-view
        el-footer(height="auto")
          .copyright Copyright © 2018 eighthours All Rights Reserved.
</template>

<script>
import Menu from './Menu';

export default {
  name: 'Layout',
  components: {
    'menu-list': Menu,
  },
  data() {
    return {};
  },
};
</script>

<style lang="scss" scoped>
@import '../styles/colors';

#layout,
section {
  height: 100%;
}

.title-header {
  padding: 10px 20px;
  color: white;
  background-color: $primary-color;

  h1 {
    margin: 8px 0;
  }
}

.breadcrumb {
  padding: 7px 20px;
}

.el-main {
  padding: 0;
}

.copyright {
  text-align: right;
  font-size: 0.75em;
  margin: 5px 0;
}
</style>
