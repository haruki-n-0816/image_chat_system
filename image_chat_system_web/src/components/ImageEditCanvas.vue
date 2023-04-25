<template>
    <div>
        <input type="file" @change="handleImageUpload" />
        <br>
        <button @click="addRect">addRect</button>
        <button @click="exportImage">画像出力</button>
        <canvas ref="canvas" />
    </div>
</template>
<script>
import { fabric } from 'fabric';

export default {
    data() {
        return {
            imageUrl: null
        };
    },
    mounted() {
        const canvas = new fabric.Canvas(this.$refs.canvas);
        this.instance = canvas;
    },
    methods: {
        handleImageUpload(e) {
            const file = e.target.files[0];
            const reader = new FileReader();
            reader.onload = (event) => {
                const imageUrl = event.target.result;
                fabric.Image.fromURL(imageUrl, (img) => {
                    this.setBackgroundImage(img);
                });
            };
            reader.readAsDataURL(file);
        },
        setBackgroundImage(image) {
            const instance = this.instance;
            instance.setBackgroundImage(image, () => {
                instance.renderAll();
                instance.setWidth(image.width);
                instance.setHeight(image.height);
            });
        },
        addRect() {
            const canvas = this.instance;
            const rect = new fabric.Rect({
                left: 100,
                top: 100,
                width: 50,
                height: 50,
                fill: 'black'
            });
            canvas.add(rect);
        },
        exportImage() {
            const dataUrl = this.instance.toDataURL({
                format: 'png',
                quality: 1
            });
            const link = document.createElement('a');
            link.href = dataUrl;
            link.download = 'export.png';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
};
</script>