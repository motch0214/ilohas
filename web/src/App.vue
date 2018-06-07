<template lang="pug">
#app.match-height
  router-view
</template>

<script>
export default {
  name: 'App',
};
</script>

<style lang="scss">
html,
body,
section,
.match-height {
  height: 100%;
}

#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.main-header > h1 {
  margin: 0;
  padding: 10px 0;
  font-size: 1.5em;
}
</style>
