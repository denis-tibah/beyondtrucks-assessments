<template>
  <div class="container">
    <StudentTable />
  </div>
</template>
  
  <script lang="ts">
  import { defineComponent } from 'vue';
  import StudentTable from '@/components/StudentTable.vue';
  
  export default defineComponent({
    components: {
      StudentTable,
    },
  });
  </script>
  
  <style scoped>
  .container {
    display: flex;
    flex-direction: column;
    height: 100vh;
  }
  
  .content {
    display: flex;
    flex: 1;
  }
  
  header {
    flex-basis: 50px;
  }
  </style>
  