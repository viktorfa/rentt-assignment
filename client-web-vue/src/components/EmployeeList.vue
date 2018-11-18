<template>
  <div>
    <EmployeeListItem
      v-for="employee in employees"
      v-bind:key="employee.id"
      v-bind:employee="employee"
      v-bind:deleteEmployee="deleteEmployee"
      v-bind:update="update"
    />
  </div>
</template>


<script>
import EmployeeListItem from "./EmployeeListItem";
export default {
  name: "EmployeeList",
  components: {
    EmployeeListItem
  },
  props: {
    employees: Array,
    deleteEmployee: Function,
    update: Function,
  }
};
</script>
