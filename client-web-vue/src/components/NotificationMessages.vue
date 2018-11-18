<template>
  <v-snackbar
    v-model="snackbar"
    :bottom="y === 'bottom'"
    :left="x === 'left'"
    :multi-line="mode === 'multi-line'"
    :right="x === 'right'"
    :timeout="6000"
    :top="y === 'top'"
    :vertical="mode === 'vertical'"
  >
    {{ messageText }}
    <v-btn
      color="pink"
      flat
      @click="snackbar = false"
    >
      Close
    </v-btn>
  </v-snackbar>
</template>


<script>
export default {
  name: "NotificationMessages",
  props: {
    messageText: String,
    messageType: String
  },
  data: function() {
    return { snackbar: false, y: 'bottom', x: '', mode: 'vertical' };
  },
  watch: {
    messageText: function() {
      this.snackbar = true
    }
  }
};
</script>

