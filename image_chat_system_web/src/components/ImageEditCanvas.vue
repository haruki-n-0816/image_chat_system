<template>
    <div>
        <!-- <img src="@/assets/menkyo_gold.png" alt=""> -->
        <canvas ref="canvas"></canvas>
        <h4>画像加工</h4>
        <button @click="exportImage">画像出力</button>
    </div>
</template>
<script>
import { fabric } from 'fabric';

export default {
    data(){
        return{
            imagePath:'/img/menkyo_gold.c9cf8195.png'
        }
    },
    mounted() {
        // 画像の読み込み
        fabric.Image.fromURL(this.imagePath, (img) => {
            // キャンバスの作成
            const canvas = new fabric.Canvas(this.$refs.canvas, {
                width: img.width,
                height: img.height
            });
            canvas.setBackgroundImage(img);

            // 図形の描写
            const rect = new fabric.Rect({
                left: 100,
                top: 100,
                width: 50,
                height: 50,
                fill: 'black'
            });
            canvas.add(rect);
        });
    },
    methods: {
        // キャンバス上の描写結果を画像としてエクスポートする
        exportImage() {
            const dataUrl = this.$refs.canvas.toDataURL({
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
