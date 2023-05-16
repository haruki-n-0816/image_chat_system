<template>
  <div>
    <button @click="imageUpLoad">画像</button>
    <b-modal ref="modal" title="画像編集" @hide="hideModal">
      <template v-slot:default>
        <image-edit-window></image-edit-window>
      </template>
    </b-modal>
  </div>
</template>

<script>
import Vue from 'vue'
import ImageEditWindow from '../components/ImageEditWindow.vue'

Vue.component('image-edit-window', ImageEditWindow);
export default {
  components: {
    ImageEditWindow
  },
  methods: {
    imageUpLoad() {
      this.$refs.modal.show();
    },
    hideModal() {
      this.$refs.modal.hide();
    },
  },
}
</script>



