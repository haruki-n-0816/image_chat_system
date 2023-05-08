<template>
    <div class="image-edit-window">
        <b-form-group>
            <b-form-radio v-model="selectedMode" name="selectedMode" value="0"
                @change="optionChanged">マスキングの移動</b-form-radio>
            <b-form-radio v-model="selectedMode" name="selectedMode" value="1" @change="optionChanged">トリミング</b-form-radio>
            <b-form-radio v-model="selectedMode" name="selectedMode" value="2" @change="optionChanged">マスキング</b-form-radio>
            <!-- <b-form-radio v-model="selectedMode" name="selectedMode" value="3" @change="optionChanged">ペン</b-form-radio> -->
        </b-form-group>
        <b-button @click="undo">戻る</b-button>
        <b-button @click="redo">進む</b-button>
        <b-button variant="danger" @click="allDelete">全削除</b-button>
        <b-button variant="primary" @click="exportImage">画像出力</b-button>
        <!-- デバッグ用の情報出力 -->
        <!-- <button @click="allInfo">データ出力</button> -->
        <br>
        <input type="file" @change="handleImageUpload" />
        <canvas ref="canvas" @mousedown="startAction" @mousemove="action" @mouseup="endAction"></canvas>
        <br>
    </div>
</template>

<script>
import { fabric } from 'fabric';

export default {
    data() {
        return {
            imageUrl: null,
            selectedMode: 0,
            history: [],
            historyIndex: 0,
            cropStarted: false,
            cropRect: null,
            cropStartX: null,
            cropStartY: null,
            cropEndX: null,
            cropEndY: null,
            maskingStarted: false,
            maskingRect: null,
            maskingStartX: null,
            maskingStartY: null,
            maskingEndX: null,
            maskingEndY: null,

        };
    },
    mounted() {
        this.canvas = new fabric.Canvas(this.$refs.canvas);
        this.canvas.selection = true;
        this.canvas.on('mouse:down', this.startAction);
        this.canvas.on('mouse:move', this.action);
        this.canvas.on('mouse:up', this.endAction);
        this.canvas.on('object:modified', this.saveHistory);
        this.canvas.on('object:added', function (e) {
            e.target.selectable = false;
        });
    },
    methods: {
        saveHistory() {
            const data = JSON.stringify(this.canvas.toJSON());
            this.history.splice(this.historyIndex);
            this.history.push(data);
            this.historyIndex++;
        },
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
            this.saveHistory();
        },
        allDelete() {
            const canvas = this.canvas;
            const objects = canvas.getObjects();
            objects.forEach((obj) => {
                canvas.remove(obj);
            });
            this.saveHistory();
        },
        undo() {
            if (this.historyIndex > 1) {
                this.historyIndex--;
                this.canvas.loadFromJSON(this.history[this.historyIndex - 1]);
                this.canvas.renderAll();
            }
        },
        redo() {
            if (this.historyIndex < this.history.length) {
                this.historyIndex++;
                this.canvas.loadFromJSON(this.history[this.historyIndex - 1]);
                this.canvas.renderAll();
            }
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
        },
        optionChanged() {
            if (this.selectedMode == 0) {
                this.canvas.forEachObject(function (obj) {
                    obj.selectable = true;
                });
            } else {
                this.canvas.forEachObject(function (obj) {
                    obj.selectable = false;
                });
                this.canvas.discardActiveObject();
                this.canvas.requestRenderAll();
            }
        },
        startAction(event) {
            switch (this.selectedMode) {
                case '0':
                    this.canvas.forEachObject(function (obj) {
                        obj.selectable = true;
                    });
                    break;
                case '1':
                    this.startCrop(event);
                    break;
                case '2':
                    this.canvas.forEachObject(function (obj) {
                        obj.selectable = false;
                    });
                    this.startMasking(event);
                    break;
                case '3':
                    this.startPen(event);
                    break;
            }
        },
        action(event) {
            switch (this.selectedMode) {
                case '1':
                    this.crop(event);
                    break;
                case '2':
                    this.masking(event);
                    break;
                case '3':
                    this.pen(event);
                    break;
            }
        },
        endAction(event) {
            switch (this.selectedMode) {
                case '1':
                    this.endCrop(event);
                    break;
                case '2':
                    this.endMasking(event);
                    break;
                case '3':
                    this.endPen(event);
                    break;
            }
        },
        startCrop(event) {
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
            this.saveHistory();
        },
        crop(event) {
            if (!this.cropStarted) return;
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
        startMasking(event) {
            this.maskingStarted = true;
            const pointer = this.canvas.getPointer(event);
            this.maskingStartX = pointer.x;
            this.maskingStartY = pointer.y;
            this.maskingRect = new fabric.Rect({
                left: this.maskingStartX,
                top: this.maskingStartY,
                width: 0,
                height: 0,
                fill: 'black'
            });
            this.canvas.add(this.maskingRect);
        },
        masking(event) {
            if (!this.maskingStarted) return;
            const pointer = this.canvas.getPointer(event);
            this.maskingEndX = pointer.x;
            this.maskingEndY = pointer.y;
            const width = this.maskingEndX - this.maskingStartX;
            const height = this.maskingEndY - this.maskingStartY;
            this.maskingRect.set('width', width).set('height', height);
            this.canvas.renderAll();
        },
        endMasking() {
            this.canvas.discardActiveObject();
            this.saveHistory();
            this.maskingStarted = false;
            this.maskingRect = null;
            this.maskingStartX = null;
            this.maskingStartY = null;
            this.maskingEndX = null;
            this.maskingEndY = null;
        },
        startPen(event) {// 修正中
            this.canvas.isDrawingMode = true;
            this.canvas.freeDrawingBrush.color = 'black';
            this.canvas.freeDrawingBrush.width = 3;
            this.canvas.freeDrawingBrush.shadow = new fabric.Shadow({
                blur: 0,
                offsetX: 0,
                offsetY: 0,
                color: 'black'
            });
            this.canvas.freeDrawingBrush.set({
                strokeLineCap: 'round',
                strokeLineJoin: 'round',
                strokeUniform: true,
                shadowUniform: true,
                dirty: true
            });
            this.canvas.freeDrawingBrush.initialize(this.canvas);
            const point = this.canvas.getPointer(event.e);
            const x = point.x;
            const y = point.y;
            this.canvas.freeDrawingBrush.onMouseDown({ x, y });
        },
        pen(event) {// 修正中
            const point = this.canvas.getPointer(event.e);
            const x = point.x;
            const y = point.y;
            this.canvas.freeDrawingBrush.onMouseMove({ x, y });
        },
        endPen() {// 修正中
            this.canvas.freeDrawingBrush.onMouseUp();
            this.canvas.isDrawingMode = false;
            this.saveHistory();
        },
        allInfo() {// デバッグ用
            this.canvas.forEachObject(function (obj) {
                console.log(obj);
            });
        }
    }
};
</script>

<style>
.image-edit-window{
    text-align: center;
}
canvas {
    display: block;
    margin: auto;
}
</style>