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
        <br><br>
        <input class="a" type="file" @change="handleImageUpload" />
        <canvas ref="canvas" @mousedown="startAction" @mousemove="action" @mouseup="endAction"></canvas>
        <br>
    </div>
</template>

<script>
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8081';
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
    canvasWidth: 0,
    canvasHeight: 0
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
        this.userId = this.$store.getters.userId;
        this.userName = this.$store.getters.userName;
        this.chatRoomId = this.$route.params.roomId;
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
            const canvas = this.canvas;
            fabric.Image.fromURL(imageUrl, (img) => {
                const aspectRatio = img.width / img.height;
                const maxWidth = 330; // 最大表示幅を指定
                const maxHeight = maxWidth / aspectRatio;

                img.scaleToWidth(maxWidth);
                img.scaleToHeight(maxHeight);

                canvas.setBackgroundImage(img, canvas.renderAll.bind(canvas), {
                    scaleX: img.scaleX,
                    scaleY: img.scaleY
                });

                canvas.setWidth(maxWidth);
                canvas.setHeight(maxHeight);
                canvas.renderAll();
            });
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
        sendImage: async function () {
            const croppedImage = this.canvas.toDataURL({
                left: this.cropStartX,
                top: this.cropStartY,
                width: this.cropEndX - this.cropStartX,
                height: this.cropEndY - this.cropStartY
            });
            const base64Data = croppedImage.replace(/^data:image\/(png|jpeg);base64,/, "");//実際のBase64エンコードされた画像データの部分だけにする
            const binaryData = atob(base64Data);    //デコードしてバイナリデータへ（atobでBase64エンコードを逆変換するために使用）
            const arrayBuffer = new ArrayBuffer(binaryData.length);// ArrayBufferバイナリデータの格納に使用される(サイズ固定)
            const uint8Array = new Uint8Array(arrayBuffer);//整数値を格納するための固定サイズの配列（8ビット符号なし）、元の画像データをバイト単位で格納した配列

            for (let i = 0; i < binaryData.length; i++) {
                uint8Array[i] = binaryData.charCodeAt(i);
            }
            
            const blob = new Blob([arrayBuffer], { type: "image/jpeg" }); //Blobオブジェクトに変換(Blobオブジェクトはファイルのように扱える)

            const formData = new FormData();
            formData.append("image", blob, "image.jpg");
            formData.append("chatRoomId", this.chatRoomId); 
            formData.append("chatPoster", sessionStorage.getItem('userName')); 
            formData.append("userId", sessionStorage.getItem('userId')); 
            try {
                const response = await axios.post('/chatImagePost', formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'  //送信されるデータの種類
                    }
                });
                console.log(response);
            } catch (error) {
                console.error(error);
            }
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
.image-edit-window {
    text-align: center;
}

.a {
    width: 50vw;
}

canvas {
    display: block;
    margin: auto;
    padding: 5% 0 0 0;
    max-width: 100%;
    height: auto;
    text-align: center;
}

</style>