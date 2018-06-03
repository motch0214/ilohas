<template lang="pug">
#app
  router-view
</template>

<script>
export default {
  name: 'App',
};
</script>

<style lang="scss">
@import './styles/colors';

html,
body,
#app {
  height: 100%;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: $default-text-color;
}
</style>
