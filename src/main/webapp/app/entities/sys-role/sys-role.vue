<template>
    <div>
        <h2 id="page-heading">
            <span v-text="$t('yueXinApp.sysRole.home.title')" id="sys-role-heading">Sys Roles</span>
            <router-link :to="{name: 'SysRoleCreate'}" tag="button" id="jh-create-entity" class="btn btn-primary float-right jh-create-entity create-sys-role">
                <font-awesome-icon icon="plus"></font-awesome-icon>
                <span  v-text="$t('yueXinApp.sysRole.home.createLabel')">
                    Create a new Sys Role
                </span>
            </router-link>
        </h2>
        <b-alert :show="dismissCountDown"
            dismissible
            :variant="alertType"
            @dismissed="dismissCountDown=0"
            @dismiss-count-down="countDownChanged">
            {{alertMessage}}
        </b-alert>
        <br/>
        <div class="alert alert-warning" v-if="!isFetching && sysRoles && sysRoles.length === 0">
            <span v-text="$t('yueXinApp.sysRole.home.notFound')">No sysRoles found</span>
        </div>
        <template>
            <el-table
                :data="sysRoles"
                style="width: 100%"
                stripe
                @sort-change="changeOrder">
                <el-table-column
                    prop="id"
                    label="id"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    prop="roleName"
                    label="角色名称"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    prop="roleKey"
                    label="roleKey"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    prop="roleSort"
                    label="roleSort"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    prop="status"
                    label="status"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    prop="createTime"
                    label="createTime"
                    sortable
                    width="">
                </el-table-column>
                <el-table-column
                    fixed="right"
                    header-align="center"
                    align="center"
                    label="操作"
                >
                    <template slot-scope="scope">
                        <div class="btn-group">
                            <router-link :to="{name: 'SysRoleView',  params: {sysRoleId: scope.row.id}}" tag="button" class="btn btn-info btn-sm details">
                                <font-awesome-icon icon="eye"></font-awesome-icon>
                                <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                            </router-link>
                            <router-link :to="{name: 'SysRoleEdit', params:  {sysRoleId: scope.row.id}}" tag="button" class="btn btn-primary btn-sm edit">
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
                        </div>
                    </template>
                </el-table-column>
            </el-table>
        </template>
        <!-- 删除弹框  开始     -->
        <b-modal ref="removeEntity" id="removeEntity" >
            <span slot="modal-title"><span id="yueXinApp.sysRole.delete.question" v-text="$t('entity.delete.title')">Confirm delete operation</span></span>
            <div class="modal-body">
                <p id="jhi-delete-sysRole-heading" v-text="$t('yueXinApp.sysRole.delete.question', {'id': removeId})">Are you sure you want to delete this Sys Role?</p>
            </div>
            <div slot="modal-footer">
                <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
                <button type="button" class="btn btn-primary" id="jhi-confirm-delete-sysRole" v-text="$t('entity.action.delete')" v-on:click="removeSysRole()">Delete</button>
            </div>
        </b-modal>
        <!-- 删除弹框 结束      -->
        <!-- 分页    开始   -->
<!--        <div v-show="sysRoles && sysRoles.length > 0">-->
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
        <!-- 分页   结束    -->
    </div>
</template>

<script lang="ts" src="./sys-role.component.ts">
</script>
