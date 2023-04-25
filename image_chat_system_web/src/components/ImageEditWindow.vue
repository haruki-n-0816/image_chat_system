<template>
    <div>
        <input type="file" @change="handleImageUpload" />
        <br>
        <canvas ref="canvas" @mousedown="startCrop" @mousemove="crop" @mouseup="endCrop"></canvas>
        <br>
        <label for="toggle">トリミング</label>
        <input type="checkbox" id="toggle" v-model="toggle" />
        <button @click="addRect">マスキング</button>
        <button @click="allDelete">全削除</button>
        <button @click="exportImage">画像出力</button>
    </div>
</template>

<script>
import { fabric } from 'fabric';

export default {
    data() {
        return {
            imageUrl: null,
            cropStarted: false,
            cropRect: null,
            cropStartX: null,
            cropStartY: null,
            cropEndX: null,
            cropEndY: null,
            toggle: false,
        };
    },
    mounted() {
        this.canvas = new fabric.Canvas(this.$refs.canvas);
        this.canvas.selection = false;
        this.canvas.on('mouse:down', this.startCrop);
        this.canvas.on('mouse:move', this.crop);
        this.canvas.on('mouse:up', this.endCrop);
    },
    methods: {
        handleImageUpload(e) {
            const file = e.target.files[0];
            const reader = new FileReader();
            reader.onload = (event) => {
                const imageUrl = event.target.result;
                this.setBackgroundImage(imageUrl);
            };
            reader.readAsDataURL(file);
        },
        setBackgroundImage(imageUrl) {
            fabric.Image.fromURL(imageUrl, (img) => {
                this.canvas.setWidth(img.width);
                this.canvas.setHeight(img.height);
                this.canvas.setBackgroundImage(img, this.canvas.renderAll.bind(this.canvas));
            });
        },
        startCrop(event) {
            if (!this.toggle) return;
            this.canvas.remove(this.cropRect);
            this.cropStarted = true;
            const pointer = this.canvas.getPointer(event);
            this.cropStartX = pointer.x;
            this.cropStartY = pointer.y;
            this.cropRect = new fabric.Rect({
                left: this.cropStartX,
                top: this.cropStartY,
                width: 0,
                height: 0,
                fill: 'rgba(0,0,0,0.0)',
                stroke: '#000',
                strokeWidth: 1
            });
            this.canvas.add(this.cropRect);
        },
        crop(event) {
            if (!this.cropStarted || !this.toggle) return;
            const pointer = this.canvas.getPointer(event);
            this.cropEndX = pointer.x;
            this.cropEndY = pointer.y;
            const width = this.cropEndX - this.cropStartX;
            const height = this.cropEndY - this.cropStartY;
            this.cropRect.set('width', width).set('height', height);
            this.canvas.renderAll();
        },
        endCrop() {
            this.cropStarted = false;
        },
        addRect() {
            const canvas = this.canvas;
            const rect = new fabric.Rect({
                left: 100,
                top: 100,
                width: 50,
                height: 50,
                fill: 'black'
            });
            canvas.add(rect);
        },
        allDelete() {
            const canvas = this.canvas;
            const objects = canvas.getObjects();
            objects.forEach((obj) => {
                canvas.remove(obj);
            });
        },
        exportImage() {
            const croppedImage = this.canvas.toDataURL({
                left: this.cropStartX,
                top: this.cropStartY,
                width: this.cropEndX - this.cropStartX,
                height: this.cropEndY - this.cropStartY
            });
            const link = document.createElement('a');
            link.href = croppedImage;
            link.download = 'cropped.png';
            document.body.appendChild(link);
            link.click();
            document.body.removeChild(link);
        }
    }
};
</script>