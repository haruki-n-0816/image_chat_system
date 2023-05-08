<template>
     <form style="text-align: center;">
        <div>
            <h1>チャットアプリ</h1>
            <button class="startButton" @click="$router.push('/Login')">チャットへ</button>
        </div>
    </form>
</template>

<style>
.startButton{
    margin-top: 15px;
}
</style>
