<template>
    <div class="row justify-content-center">
        <div class="col-8">
            <div v-if="sysOperLog">
                <h2 class="jh-entity-heading"><span v-text="$t('yueXinApp.sysOperLog.detail.title')">SysOperLog</span> {{sysOperLog.id}}</h2>
                <dl class="row jh-entity-details">
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.title')">Title</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.title}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.businessType')">Business Type</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.businessType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.method')">Method</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.method}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.requestMethod')">Request Method</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.requestMethod}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operatorType')">Operator Type</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operatorType}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operName')">Oper Name</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.deptName')">Dept Name</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.deptName}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operUrl')">Oper Url</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operUrl}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operIp')">Oper Ip</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operIp}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operLocation')">Oper Location</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operLocation}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operParam')">Oper Param</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operParam}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.jsonResult')">Json Result</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.jsonResult}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.status')">Status</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.status}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.errorMsg')">Error Msg</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.errorMsg}}</span>
                    </dd>
                    <dt>
                        <span v-text="$t('yueXinApp.sysOperLog.operTime')">Oper Time</span>
                    </dt>
                    <dd>
                        <span>{{sysOperLog.operTime}}</span>
                    </dd>
                </dl>
                <button type="submit"
                        v-on:click.prevent="previousState()"
                        class="btn btn-info">
                    <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
                </button>
                <router-link v-if="sysOperLog.id" :to="{name: 'SysOperLogEdit', params: {sysOperLogId: sysOperLog.id}}" tag="button" class="btn btn-primary">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script lang="ts" src="./sys-oper-log-details.component.ts">
</script>
