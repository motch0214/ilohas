<template lang="pug">
doctype html
#market-data.match-height
  el-container
    el-header(height="auto").main-header
      h1 Market Data
    el-table(:data="marketDataList" stripe height="100%" style="width: 100%")
      el-table-column(type="selection")
      el-table-column(prop="baseDate" label="Base Date" sortable width="120")
      el-table-column(prop="name" label="Name"
        :filters="[{text:'Official', value:'Official'}, {text:'Test',value:'Test'}]"
        :filter-method="filter('name')")
</template>

<script>
export default {
  name: 'MarketData',
  data() {
    return {
      marketDataList: [
        {
          baseDate: '2018/04/01',
          name: 'Official',
        },
        {
          baseDate: '2018/05/01',
          name: 'Official',
        },
        {
          baseDate: '2018/05/01',
          name: 'Test',
        },
        {
          baseDate: '2018/06/01',
          name: 'Official',
        },
        {
          baseDate: '2018/07/01',
          name: 'Official',
        },
        {
          baseDate: '2018/08/01',
          name: 'Official',
        },
        {
          baseDate: '2018/09/01',
          name: 'Test',
        },
        {
          baseDate: '2018/10/01',
          name: 'Official',
        },
        {
          baseDate: '2018/11/01',
          name: 'Official',
        },
        {
          baseDate: '2018/12/01',
          name: 'Official',
        },
        {
          baseDate: '2019/01/01',
          name: 'Test',
        },
        {
          baseDate: '2019/02/01',
          name: 'Official',
        },
        {
          baseDate: '2019/03/01',
          name: 'Test',
        },
        {
          baseDate: '2019/04/01',
          name: 'Official',
        },
        {
          baseDate: '2019/05/01',
          name: 'Test',
        },
        {
          baseDate: '2019/06/01',
          name: 'Official',
        },
      ],
    };
  },
  methods: {
    filter(column) {
      return (value, row) => {
        return row[column] === value;
      };
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
