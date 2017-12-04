<template>
    <div>
        <el-row>
            <el-col :span="4">
                <el-row>
                    <el-col><div style="min-width: 100%;text-align: center">{{logo}}</div></el-col>
                </el-row>
                <el-row>
                    <el-menu default-active="2" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
                        <el-submenu index="1">
                            <template slot="title"><i class="el-icon-message"></i>导航一</template>
                            <el-menu-item-group>
                                <template slot="title">分组一</template>
                                <el-menu-item index="1-1">选项1</el-menu-item>
                                <el-menu-item index="1-2">选项2</el-menu-item>
                            </el-menu-item-group>
                            <el-menu-item-group title="分组2">
                                <el-menu-item index="1-3">选项3</el-menu-item>
                            </el-menu-item-group>
                            <el-submenu index="1-4">
                                <template slot="title">选项4</template>
                                <el-menu-item index="1-4-1">选项1</el-menu-item>
                            </el-submenu>
                        </el-submenu>
                        <el-menu-item index="2"><i class="el-icon-menu"></i>导航二</el-menu-item>
                        <el-menu-item index="3"><i class="el-icon-setting"></i>导航三</el-menu-item>
                    </el-menu>
                </el-row>
            </el-col>
            <el-col :span="20">
                <el-row>
                    <el-col :span="1">
                        <i class="fa fa-reorder" style="cursor:pointer" @click="collapseMenu"></i>
                    </el-col>
                    <el-col :span="23" style="text-align: right; padding-right: 10px">
                        <el-button @contextmenu.prevent="show()">退出</el-button>
                    </el-col>
                </el-row>
                <el-row>
                    <el-tabs v-model="editableTabsValue" type="card" editable @edit="handleTabsEdit">
                        <el-tab-pane
                                :key="item.name"
                                v-for="(item, index) in editableTabs"
                                :label="item.title"
                                :name="item.name"
                        >
                            {{item.content}}
                        </el-tab-pane>
                    </el-tabs>
                </el-row>
            </el-col>
        </el-row>
    </div>
</template>
<script>

    export default {
        methods: {
            collapseMenu() {

                this.collapse = !this.collapse;
            }
        }
    }
</script>
<style>
    @import "~font-awesome/css/font-awesome.css";

    .cursor-pointer {
        cursor: pointer
    }

    .header {
        background: #1870b4;
        color: #fff;
        line-height: 45px;
        height: 45px;
    }
</style>