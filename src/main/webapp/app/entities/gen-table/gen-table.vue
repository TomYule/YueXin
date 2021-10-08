<template>
    <div>
        <!--        <h2 id="page-heading">-->
        <!--            <span v-text="$t('yueXinApp.genTable.home.title')" id="gen-table-heading">Gen Tables</span>-->
        <!--            <router-link :to="{name: 'GenTableCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-gen-table">-->
        <!--                <font-awesome-icon icon="plus"></font-awesome-icon>-->
        <!--                <span  v-text="$t('yueXinApp.genTable.home.createLabel')">-->
        <!--                    Create a new Gen Table-->
        <!--                </span>-->
        <!--            </router-link>-->
        <!--        </h2>-->
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
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
            <el-form-item label="创建时间">
                <el-date-picker
                    v-model="dateRange"
                    type="daterange"
                    :picker-options="pickerOptions"
                    align="right"
                    unlink-panels
                    range-separator="至"
                    start-placeholder="开始日期"
                    end-placeholder="结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="transition()">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button
                    type="primary"
                    plain
                    icon="el-icon-download"
                    size="mini"
                    @click="handleGenTable"
                >生成
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="info"
                    plain
                    icon="el-icon-upload"
                    size="mini"
                    @click="openImportTable"
                >导入
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="success"
                    plain
                    icon="el-icon-edit"
                    size="mini"
                    @click="handleEditTable"
                >修改
                </el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="danger"
                    plain
                    icon="el-icon-delete"
                    size="mini"
                    @click="handleDelete"
                >删除
                </el-button>
            </el-col>
<!--            <right-toolbar :showSearch.sync="showSearch" @queryTable="transition()"></right-toolbar>-->
        </el-row>

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

            <el-table :data="genTables">
                <el-table-column type="selection" align="center" width="55"></el-table-column>

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
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                        <div class="btn-group">
                            <el-button
                                type="primary"
                                size="small"
                                icon="el-icon-view"
                                @click="handlePreview(scope.row)"
                            >预览</el-button>
                            <router-link :to="{name: 'GenTableView', params: {genTableId: scope.row.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'GenTableEdit', params: {genTableId: scope.row.id}}" tag="button" class="btn btn-primary btn-sm edit">
                                <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                            </router-link>
                            <b-button v-on:click="prepareRemove(scope.row)"
                                      variant="danger"
                                      class="btn btn-sm"
                                      v-b-modal.removeEntity>
                                <font-awesome-icon icon="times"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                            </b-button>

                            <!--                        <el-button-->
                            <!--                            type="text"-->
                            <!--                            size="small"-->
                            <!--                            icon="el-icon-edit"-->
                            <!--                            @click="handleEditTable(scope.row)"-->
                            <!--                            v-hasPermi="['tool:gen:edit']"-->
                            <!--                        >编辑</el-button>-->
                            <!--                        <el-button-->
                            <!--                            type="text"-->
                            <!--                            size="small"-->
                            <!--                            icon="el-icon-delete"-->
                            <!--                            @click="handleDelete(scope.row)"-->
                            <!--                            v-hasPermi="['tool:gen:remove']"-->
                            <!--                        >删除</el-button>-->
                            <!--                        <el-button-->
                            <!--                            type="text"-->
                            <!--                            size="small"-->
                            <!--                            icon="el-icon-refresh"-->
                            <!--                            @click="handleSynchDb(scope.row)"-->
                            <!--                            v-hasPermi="['tool:gen:edit']"-->
                            <!--                        >同步</el-button>-->
                            <!--                        <el-button-->
                            <!--                            type="text"-->
                            <!--                            size="small"-->
                            <!--                            icon="el-icon-download"-->
                            <!--                            @click="handleGenTable(scope.row)"-->
                            <!--                            v-hasPermi="['tool:gen:code']"-->
                            <!--                        >生成代码</el-button>-->
                        </div>
                    </template>

                </el-table-column>
            </el-table>

        </div>
        <b-modal ref="removeEntity" id="removeEntity">
            <span slot="modal-title"><span id="yueXinApp.genTable.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-genTable-heading" v-text="$t('yueXinApp.genTable.delete.question', {'id': removeId})">Are you sure you want to delete this Gen Table?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-genTable" v-text="$t('entity.action.delete')" v-on:click="removeGenTable()">Delete</button>
            </div>
        </b-modal>

        <!--        <div v-show="genTables && genTables.length > 0">-->
        <!--            <div class="row justify-content-center">-->
        <!--                <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>-->
        <!--            </div>-->
        <!--            <div class="row justify-content-center">-->
        <!--                <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>-->
        <!--            </div>-->
        <!--        </div>-->
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


        <!-- 预览界面 -->
        <el-dialog :title="preview.title" :visible.sync="preview.open" width="80%" top="5vh" append-to-body class="scrollbar">
            <el-tabs v-model="preview.activeName">
                <el-tab-pane
                    v-for="(value, key) in preview.data"
                    :label="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
                    :name="key.substring(key.lastIndexOf('/')+1,key.indexOf('.vm'))"
                    :key="key"
                >
                    <pre><code class="hljs" v-html="highlightedCode(value, key)"></code></pre>
                </el-tab-pane>
            </el-tabs>
        </el-dialog>
        <import-table ref="importTable" @ok="transition()" />
    </div>
</template>

<script lang="ts" src="./gen-table.component.ts">
</script>
