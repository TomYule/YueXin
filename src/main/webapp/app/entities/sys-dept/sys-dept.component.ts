import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { ISysDept } from '@/shared/model/sys-dept.model';
import AlertMixin from '@/shared/alert/alert.mixin';

import SysDeptService from './sys-dept.service';
import {handleTree, resetForm} from "@/shared/utils/common";

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class SysDept extends mixins(AlertMixin) {

  @Inject('sysDeptService') private sysDeptService: () => SysDeptService;
  private removeId: number = null;

  public sysDepts: ISysDept[] = [];
  // 遮罩层
  public isFetching = false;
  // 重新渲染表格状态
  public refreshTable = true;
  // 显示搜索条件
  public showSearch = true;
  // 是否展开，默认全部展开
  public isExpandAll = true;
  // 查询参数
  public queryParams = {
    deptName: undefined,
    status: undefined
  };

  public mounted(): void {
    this.retrieveAllSysDepts();
  }

  public clear(): void {
    this.retrieveAllSysDepts();
  }

  public retrieveAllSysDepts(): void {
    this.isFetching = true;

    this.sysDeptService()
      .retrieve()
      .then(
        res => {
          // this.sysDepts = res.data;
          this.sysDepts = handleTree(res.data, "id",null,null);
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  /** 重置按钮操作 */
  public resetQuery() {
    resetForm("queryForm");
    this.retrieveAllSysDepts();
  }
  /** 新增按钮操作 */
  public handleAdd(row) {
    // this.reset();
    // if (row != undefined) {
    //   this.form.parentId = row.deptId;
    // }
    // this.open = true;
    // this.title = "添加部门";
    // listDept().then(response => {
    //   this.deptOptions = this.handleTree(response.data, "deptId");
    // });
  }

  /** 展开/折叠操作 */
  public toggleExpandAll() {
    this.refreshTable = false;
    this.isExpandAll = !this.isExpandAll;
    this.$nextTick(() => {
      this.refreshTable = true;
    });
  }

  public prepareRemove(instance: ISysDept): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeSysDept(): void {
    this.sysDeptService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('yueXinApp.sysDept.deleted', { param: this.removeId });
        this.alertService().showAlert(message, 'danger');
        this.getAlertFromStore();
        this.removeId = null;
        this.retrieveAllSysDepts();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
