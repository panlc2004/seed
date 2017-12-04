<template>
    <div class="content">
        <router-view class="fullParent clean"></router-view>
    </div>
</template>
<script>

</script>
<style>
    html, body {
        height: 100%;
        margin: 0 0;
        padding: 0 0;
    }
    .fullParent {
        height: 100%;
        width: 100%;
    }
    .clean {
        margin: 0 0;
        padding: 0 0;
    }
    .content {
        width: 100%;
        position: absolute;
        top: 0;
        bottom: 0;
        left: 0;
        right: 0;
    }
</style>