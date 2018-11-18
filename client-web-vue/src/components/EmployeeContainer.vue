<template>
  <div>
    <CreateEmployeeForm
      v-bind:create="create"
    />
    <v-container>
    <v-layout row wrap justify-space-around>
        <v-flex xs12 sm6>
    <v-text-field
      v-model="filterInput"
      label="filter"
    />
          </v-flex>
        <v-flex xs12 sm6>
          <v-btn
            v-on:click="sortField = 'name'; isSortDesc = !isSortDesc"
          >
          <span>Name</span>
          <span v-if="sortField === 'name'">
            <v-icon v-if="isSortDesc">keyboard_arrow_up</v-icon>
            <v-icon v-if="!isSortDesc">keyboard_arrow_down</v-icon>
          </span>
          </v-btn>
          <v-btn
            v-on:click="sortField = 'age'; isSortDesc = !isSortDesc"
          >
          <span>Age</span>
          <span v-if="sortField === 'age'">
            <v-icon v-if="isSortDesc">keyboard_arrow_up</v-icon>
            <v-icon v-if="!isSortDesc">keyboard_arrow_down</v-icon>
          </span>
          </v-btn>
          </v-flex>
      </v-layout>
    </v-container>
    <EmployeeList
      v-bind:employees="sortedEmployees"
      v-bind:deleteEmployee="deleteEmployee"
      v-bind:update="update"
    />
    <NotificationMessages
      v-if="message"
      v-bind:messageText="message.text"
      v-bind:messageType="message.type"
    />
  </div>
</template>


<script>
import _ from "lodash";

import EmployeeList from "./EmployeeList";
import CreateEmployeeForm from "./CreateEmployeeForm";
import NotificationMessages from "./NotificationMessages";
import { create, list, deleteEmployee, update } from "../api/employees";
export default {
  name: "EmployeeContainer",
  components: {
    EmployeeList,
    CreateEmployeeForm,
    NotificationMessages
  },
  data: function() {
    return {
      employees: {},
      message: {},
      filterInput: "",
      sortField: "age",
      isSortDesc: true,
      filterFunction: () => true,
      sortFunction: () => 0
    };
  },
  created: async function() {
    const { ok, data: employees } = await list();
    if (ok) {
      this.employees = employees;
      this.filterEmployees();
      this.sortEmployees();
    }
  },
  computed: {
    filteredEmployees: function() {
      return Object.values(this.employees).filter(this.filterFunction);
    },
    sortedEmployees: function() {
      console.log("sorting employees");
      return this.isSortDesc
        ? _.sortBy(this.filteredEmployees, this.sortField)
        : _.reverse(_.sortBy(this.filteredEmployees, this.sortField));
    }
  },
  watch: {
    filterInput: function(newFilter, oldFilter) {
      if (newFilter && newFilter.length > 0 && newFilter !== oldFilter) {
        this.filterEmployees(newFilter);
      } else {
        this.filterEmployees("");
      }
    },
    sortField: function(newField, oldField) {
      this.sortEmployees("age", true);
    }
  },
  methods: {
    create: async function(employee) {
      const { ok, data: newEmployee, error } = await create(employee);
      if (ok) {
        this.employees[newEmployee.id] = newEmployee;
        this.employees = {
          ...this.employees,
          [newEmployee.id]: newEmployee
        };
        this.message = { text: `Created ${newEmployee.name}` };
      } else {
        this.message = { text: `Could not create ${newEmployee.name}` };
      }
    },
    update: async function(updatedEmployee) {
      const { ok, data, error } = await update(updatedEmployee);
      if (ok) {
        this.employees = {
          ...this.employees,
          [updatedEmployee.id]: updatedEmployee
        };
        this.message = { text: `Updated ${updatedEmployee.name}` };
      } else {
        this.message = { text: `Could not update ${updatedEmployee.name}` };
      }
    },
    deleteEmployee: async function({ id, name }) {
      const { ok } = await deleteEmployee(id);
      if (ok) {
        const newEmployees = { ...this.employees };
        delete newEmployees[id];
        this.employees = { ...newEmployees };
        this.message = { text: `Deleted ${name}` };
      } else {
        this.message = { text: `Could not delete ${name}` };
      }
    },
    sortEmployees: function(field, desc) {
      this.sortFunction = (a, b) =>
        desc ? a[field] - b[field] : b[field] - a[field];
      console.log(this.sortFunction);
    },
    filterEmployees: function(filter) {
      this.filterFunction = employee =>
        employee.name.match(new RegExp(filter, "ig"));
    }
  }
};
</script>
