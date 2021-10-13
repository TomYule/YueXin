<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('yueXinApp.sysDept.home.title')" id="sys-dept-heading">Sys Depts</span>

        </h2>
        <b-alert :show="dismissCountDown"
                 dismissible
                 :variant="alertType"
                 @dismissed="dismissCountDown=0"
                 @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch">
            <el-form-item label="部门名称" prop="deptName">
                <el-input
                    v-model="queryParams.deptName"
                    placeholder="请输入部门名称"
                    clearable
                    size="small"
                    @keyup.enter.native="retrieveAllSysDepts"
                />
            </el-form-item>
            <el-form-item label="状态" prop="status">
                <el-select v-model="queryParams.status" placeholder="部门状态" clearable size="small">
                    <!--                    <el-option-->
                    <!--                        v-for="dict in dict.type.sys_normal_disable"-->
                    <!--                        :key="dict.value"-->
                    <!--                        :label="dict.label"-->
                    <!--                        :value="dict.value"-->
                    <!--                    />-->
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="retrieveAllSysDepts">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>
        <div class="alert alert-warning" v-if="!isFetching && sysDepts && sysDepts.length === 0">
            <span v-text="$t('yueXinApp.sysDept.home.notFound')">No sysDepts found</span>
        </div>
        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <router-link :to="{name: 'SysDeptCreate'}" id="jh-create-entity">
                    <el-button
                        type="primary"
                        plain
                        icon="el-icon-plus"
                        size="mini">
                        <span v-text="$t('yueXinApp.sysDept.home.createLabel')">  Create a new Sys Dept </span>
                    </el-button>
                </router-link>
            </el-col>
            <el-col :span="1.5">
                <el-button
                    type="info"
                    plain
                    icon="el-icon-sort"
                    size="mini"
                    @click="toggleExpandAll"
                >展开/折叠
                </el-button>
            </el-col>
<!--            <right-toolbar :showSearch.sync="showSearch" @queryTable="retrieveAllSysDepts"></right-toolbar>-->
        </el-row>

        <el-table
            v-if="refreshTable"
            v-loading="isFetching"
            :data="sysDepts"
            row-key="id"
            :default-expand-all="isExpandAll"
            :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
        >
            <el-table-column prop="deptName" label="部门名称"></el-table-column>
            <el-table-column prop="orderNum" label="排序"></el-table-column>
            <el-table-column prop="status" label="状态">
                <template slot-scope="scope">
                    <!--                    <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>-->
                </template>
            </el-table-column>
            <el-table-column label="创建时间" align="center" prop="createTime" width="200">
                <template slot-scope="scope">
                    <span>{{ parseTime(scope.row.createTime) }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <router-link :to="{name: 'SysDeptEdit', params: {sysDeptId: scope.row.id}}" tag="button" class="btn btn-primary btn-sm edit">
                        <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                        <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                    </router-link>
                    <el-button
                        class="btn btn-sm btn-info"
                        size="small"
                        type="text"
                        icon="el-icon-plus"
                        @click="handleAdd(scope.row)"
                    >新增
                    </el-button>

                    <b-button v-on:click="prepareRemove(scope.row)"
                              variant="danger"
                              class="btn btn-sm"
                              v-b-modal.removeEntity>
                        <font-awesome-icon icon="times"></font-awesome-icon>
                        <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                    </b-button>
                </template>
            </el-table-column>
        </el-table>

        <b-modal ref="removeEntity" id="removeEntity">
            <span slot="modal-title"><span id="yueXinApp.sysDept.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysDept-heading" v-text="$t('yueXinApp.sysDept.delete.question', {'id': removeId})">Are you sure you want to delete this Sys Dept?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysDept" v-text="$t('entity.action.delete')" v-on:click="removeSysDept()">Delete</button>
            </div>
        </b-modal>
    </div>
</template>

<script lang="ts" src="./sys-dept.component.ts">
</script>
