
import {mixins} from 'vue-class-component';

import {Component, Vue, Inject} from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import {IGenTable} from '@/shared/model/gen-table.model';
import AlertMixin from '@/shared/alert/alert.mixin';
import JhiDataUtils from '@/shared/data/data-utils.service';
import GenTableService from './gen-table.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class ImportTable extends mixins(JhiDataUtils, AlertMixin) {
  @Inject('genTableService') private genTableService: () => GenTableService;
  private removeId: number = null;
  public itemsPerPage = 20;
  public queryCount: number = null;
  public page = 1;
  public previousPage = 1;
  public propOrder = 'id';
  public reverse = false;
  public totalItems = 0;
  // 显示搜索条件
  public showSearch = true;
  public multipleSelection = [];


  public genTables: IGenTable[] = [];

  public isFetching = false;
  // 遮罩层
  public visible = false;

  data() {
    return {
      queryParams: {
        tableName: undefined,
        tableComment: undefined
      },

    };
  }

  // 显示弹框
  public show() {
    this.transition();
    this.visible = true;
  }

  public mounted(): void {
    this.retrieveAllGenTables();
  }

  public clear(): void {
    this.page = 1;
    this.retrieveAllGenTables();
  }

  public clickRow(row) {
    // this.$refs.table.toggleRowSelection(row);
  }
  // 多选框选中数据
  public handleSelectionChange(selection) {
    this.genTables = selection.map(item => item.tableName);
  }

  public retrieveAllGenTables(): void {
    this.isFetching = true;

    const paginationQuery = {
      page: this.page - 1,
      size: this.itemsPerPage,
      sort: this.sort(),
    };
    this.genTableService()
      .retrieve(paginationQuery)
      .then(
        res => {
          this.genTables = res.data;
          this.totalItems = Number(res.headers['x-total-count']);
          this.queryCount = this.totalItems;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public prepareRemove(instance: IGenTable): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public sort(): Array<any> {
    const result = [this.propOrder + ',' + (this.reverse ? 'asc' : 'desc')];
    if (this.propOrder !== 'id') {
      result.push('id');
    }
    return result;
  }

  public loadPage(page: number): void {
    if (page !== this.previousPage) {
      this.previousPage = page;
      this.transition();
    }
  }

  public handleSizeChange(val) {
    console.log(`每页 ${val} 条`);
    this.itemsPerPage = val;
    this.transition();
  }

  public handleCurrentChange(val) {
    console.log(`当前页: ${val}`);
    this.page = val;
    this.transition();
  }


  /** 重置按钮操作 */
  public resetQuery() {
    // this.dateRange = [];
    // this.resetForm("queryForm");
    this.transition();
  }

  public transition(): void {
    this.retrieveAllGenTables();
  }

  public changeOrder(propOrder): void {
    this.propOrder = propOrder;
    this.reverse = !this.reverse;
    this.transition();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }

  /** 导入按钮操作 */
  public handleImportTable() {
    this.genTableService()
      .importTable({tables: this.genTables.join(",")})
      .then(res => {
        this.alertService().showAlert(res.msg, 'danger');
        if (res.code === 200) {
          this.visible = false;
          this.$emit("ok");
        }
      });
  }
}
