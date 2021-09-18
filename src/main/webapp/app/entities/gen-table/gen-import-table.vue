<template>
    <!-- 导入表 -->
    <el-dialog title="导入表" :visible.sync="visible" width="800px" top="5vh" append-to-body>
        <el-form :model="queryParams" ref="queryForm" :inline="true">
            <el-form-item label="表名称" prop="tableName">
                <el-input
                    v-model="queryParams.tableName"
                    placeholder="请输入表名称"
                    clearable
                    size="small"
                    @keyup.enter.native="transition()"
                />
            </el-form-item>
            <el-form-item label="表描述" prop="tableComment">
                <el-input
                    v-model="queryParams.tableComment"
                    placeholder="请输入表描述"
                    clearable
                    size="small"
                    @keyup.enter.native="transition()"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="transition()">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
        <el-row>
            <b-alert :show="dismissCountDown"
                     dismissible
                     :variant="alertType"
                     @dismissed="dismissCountDown=0"
                     @dismiss-count-down="countDownChanged">
                {{alertMessage}}
            </b-alert>
            <br/>
            <div class="alert alert-warning" v-if="!isFetching && genTables && genTables.length === 0">
                <span v-text="$t('yueXinApp.genTable.home.notFound')">No genTables found</span>
            </div>
            <div class="table-responsive" v-if="genTables && genTables.length > 0">
                <el-table :data="genTables" @row-click="clickRow" ref="table" @selection-change="handleSelectionChange" height="260px">
                    <el-table-column type="selection" width="55"></el-table-column>
                    <el-table-column
                        label="表名称"
                        align="center"
                        prop="tableName"
                        :show-overflow-tooltip="true"
                        sortable
                    />
                    <el-table-column
                        label="表描述"
                        align="center"
                        prop="tableComment"
                        :show-overflow-tooltip="true"
                        sortable
                    />
                    <el-table-column
                        label="实体"
                        align="center"
                        prop="className"
                        :show-overflow-tooltip="true"
                        sortable
                    />
                    <el-table-column label="创建时间" align="center" prop="createTime" width="180" sortable/>
                    <el-table-column label="更新时间" align="center" prop="updateTime" width="180" sortable/>

                </el-table>
                <div class="row justify-content-center">
                    <el-pagination
                        @size-change="handleSizeChange"
                        @current-change="handleCurrentChange"
                        :current-page="page"
                        :page-sizes="[20,100, 200, 300, 400]"
                        :page-size="itemsPerPage"
                        layout="total, sizes, prev, pager, next, jumper"
                        :total="queryCount">
                    </el-pagination>
                </div>
            </div>
        </el-row>
        <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="handleImportTable">确 定</el-button>
            <el-button @click="visible = false">取 消</el-button>
        </div>
    </el-dialog>
</template>
<script lang="ts" src="./gen-import-table.compoent.ts">
</script>
