<template>
    <div>
        <div class="row mb-3">
            <div class="col-md-6">
                <img :src="imgSrc" alt="Original Image" class="img-fluid">
            </div>
            <div class="col-md-6">
                <vue-cropper ref="cropper" :guides="true" :view-mode="1" drag-mode="crop" :background="true"
                    :rotatable="true" :src="imgSrc" @cropend="cropImage" :responsive="true">
                </vue-cropper>
            </div>
        </div>
        <div class="row">
            <div class="col-md-6">
                <h4>Original Image</h4>
            </div>
            <div class="col-md-6">
                <h4>Cropped Image</h4>
                <img :src="croppedImg" alt="Cropped Image" class="img-fluid">
            </div>
        </div>
    </div>
</template>

<script>
import VueCropper from 'vue-cropperjs'
import 'cropperjs/dist/cropper.css'

export default {
    name: 'ImageEdit',
    components: {
        VueCropper
    },
    data() {
        return {
            imgSrc: 'https://1.bp.blogspot.com/-WTPRRb5Chss/WtRzOD8UNGI/AAAAAAABLk0/fbJ7_WLTJu01w7gRG4zHGFUIdiGl1L4hwCLcBGAs/s800/menkyo_blue_man.png',
            croppedImg: ''
        }
    },
    methods: {
        cropImage() {
            this.croppedImg = this.$refs.cropper.getCroppedCanvas().toDataURL()
        }
    }
}
</script>